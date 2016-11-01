package GeneradorDeCodigo;

import java.io.IOException;

import javax.jws.Oneway;

import AST.Asignacion;
import AST.DefFuncion;
import AST.Definicion;
import AST.Escritura;
import AST.Expresion;
import AST.If;
import AST.InvocFuncion;
import AST.Lectura;
import AST.Programa;
import AST.Retorno;
import AST.Sentencia;
import AST.While;
import AST.Tipos.Entero;
import AST.Tipos.Funcion;
import AST.Tipos.Void;

public class VisitorGCEjecutar extends VisitorGCAbstract {
	VisitorGCValor vv = new VisitorGCValor();
	VisitorGCDireccion vd = new VisitorGCDireccion(vv);
	public VisitorGCEjecutar() throws IOException {
		super();
	}

	@Override
	public Object visit(Programa programa, Object object) {
		GC.getInstance().call("main");
		GC.getInstance().halt();
		for (Definicion def : programa.definiciones) {
			if(def instanceof DefFuncion)
				def.accept(this, null);
		}
		return null;
	}
	@Override
	public Object visit(Escritura escritura, Object object) {
		for (Expresion exp : escritura.expresiones) {
			exp.accept(vv, null);
			GC.getInstance().out(exp.getTipo());
		}
		return null;
	}
	@Override
	public Object visit(Lectura lectura, Object object) {
		for (Expresion exp : lectura.expresiones) {
			exp.accept(vd, null);
			GC.getInstance().in(exp.getTipo());
			GC.getInstance().store(exp.getTipo());
		}
		return null;
	}
	
	@Override
	public Object visit(Asignacion asignacion, Object object) {
		asignacion.expresion1.accept(vd, null);
		asignacion.exprresion2.accept(vv, null);
		if(asignacion.expresion1.getTipo().getTamaño()!= asignacion.exprresion2.getTipo().getTamaño())
		GC.getInstance().Cast(asignacion.exprresion2.getTipo(), asignacion.expresion1.getTipo());
		GC.getInstance().store(asignacion.expresion1.getTipo());
		return null;
	}
	
	
	@Override
	public Object visit(DefFuncion defFcuncion, Object object) {
		GC.getInstance().etiquetaFun(defFcuncion.nombre);
		GC.getInstance().enter(defFcuncion.getTamañoLocales());
		for (Sentencia sent : defFcuncion.sentencias) {
			sent.accept(this, defFcuncion);
		}
		if(((Funcion)defFcuncion.getTipo()).retorno instanceof Void){
		GC.getInstance().ret(((Funcion)defFcuncion.getTipo()).retorno,
				defFcuncion.getTamañoLocales(), ((Funcion)defFcuncion.getTipo()).getTamañoParametros());
		}
		return null;
	}
	
	@Override
	public Object visit(While whilee, Object object) {
		int contadorEtiquetas = GC.getInstance().reservarEtiquetas(2);
		GC.getInstance().etiqueta((contadorEtiquetas-1)+"");
		whilee.expresion.accept(vv, object);
		if(whilee.expresion.getTipo().getTamaño()!=2)
		GC.getInstance().Cast(whilee.expresion.getTipo(), new Entero(whilee.linea, whilee.columna));
		GC.getInstance().jz(contadorEtiquetas+"");
		for (Sentencia sent : whilee.cuerpo) {
			sent.accept(this, object);
		}
		GC.getInstance().jmp((contadorEtiquetas-1)+"");
		GC.getInstance().etiqueta(contadorEtiquetas+"");
		return null;
	}
	
	@Override
	public Object visit(If iff, Object object) {
		int contadorEtiquetas = GC.getInstance().reservarEtiquetas(2);
		iff.condicion.accept(vv, object);
		if(iff.condicion.getTipo().getTamaño()!=2)
		GC.getInstance().Cast(iff.condicion.getTipo(), new Entero(iff.linea, iff.columna));
		GC.getInstance().jz((contadorEtiquetas-1)+"");
		for (Sentencia sent : iff.cuerpo_if) {
			sent.accept(this, object);
		}
		GC.getInstance().jmp(contadorEtiquetas+"");
		GC.getInstance().etiqueta((contadorEtiquetas-1)+"");
		for (Sentencia sent : iff.cuerpo_else) {
			sent.accept(this, object);
		}
		GC.getInstance().etiqueta(contadorEtiquetas+"");
		return null;
	}
	@Override
	public Object visit(InvocFuncion invocFuncion, Object object) {
		invocFuncion.accept(vv, object);
		if(!(invocFuncion.getTipo() instanceof Void))
		GC.getInstance().Pop(invocFuncion.getTipo());
		return null;
	}
	
	@Override
	public Object visit(Retorno retorno, Object object) {
		retorno.expresion.accept(vv, object);
		if(retorno.expresion.getTipo().getTamaño()!=2)
		GC.getInstance().Cast(retorno.expresion.getTipo(), ((Funcion)((DefFuncion)object).tipo).retorno);
		GC.getInstance().ret(((Funcion)((DefFuncion)object).tipo).retorno, 
				((DefFuncion)object).getTamañoLocales(), ((Funcion)((DefFuncion)object).tipo).getTamañoParametros());
		return null;
	}

	
}





















