package GeneradorDeCodigo;

import java.io.IOException;

import AST.AccesoACampo;
import AST.AccesoArray;
import AST.Variable;
import AST.Tipos.Entero;
import AST.Tipos.Struct;

public class VisitorGCDireccion extends VisitorGCAbstract{
	 VisitorGCValor vv;
	public VisitorGCDireccion(VisitorGCValor vv) {
		super();
		this.vv = vv;
	}
	@Override
	public Object visit(Variable variable, Object object) {
		if(variable.getDecv().getAmbito()==0)
			GC.getInstance().pusha(Integer.toString(variable.getDecv().getOffset()));
		else{
			GC.getInstance().push("BP");
			GC.getInstance().push(new Entero(variable.linea, variable.columna), Integer.toString(variable.getDecv().getOffset()));
			GC.getInstance().add(new Entero(variable.linea, variable.columna));
		}
		return null;
	}
	@Override
	public Object visit(AccesoArray accesoArray, Object object) {
		accesoArray.nombre.accept(this, null);
		accesoArray.posicion.accept(vv, null);
		if(accesoArray.posicion.getTipo().getTamaño()!=2)
		GC.getInstance().Cast(accesoArray.posicion.getTipo(), new Entero(accesoArray.linea, accesoArray.linea));
		GC.getInstance().push(new Entero(accesoArray.linea, accesoArray.linea), ""+accesoArray.getTipo().getTamaño());
		GC.getInstance().Mul(new Entero(accesoArray.linea, accesoArray.linea));
		GC.getInstance().add(new Entero(accesoArray.linea, accesoArray.linea));
		return null;
	}
	
	@Override
	public Object visit(AccesoACampo accesoAcampo, Object object) {
		accesoAcampo.expresion.accept(this, null);
		GC.getInstance().push(new Entero(accesoAcampo.linea, accesoAcampo.columna), 
				((Struct)accesoAcampo.expresion.getTipo()).getCampo(accesoAcampo.nombre).getOffset()+"");
		GC.getInstance().add(new Entero(accesoAcampo.linea, accesoAcampo.linea));
		return null;
	}
}