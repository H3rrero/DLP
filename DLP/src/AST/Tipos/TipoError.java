package AST.Tipos;

import AST.NodoAST;
import AST.NodoASTAbstract;
import ME.ManejadorErrores;
import visitor.Visitor;

public class TipoError extends AbstractTipo{

	
	String mensaje;
	public TipoError(int linea, int columna,String mensaje) {
		super(linea, columna);
		this.mensaje = mensaje;
		ManejadorErrores.getInstance().addError(this);
	}
	public String getMensaje() {
		return mensaje;
	}
	@Override
	public String toString() {
		
		return mensaje+" Linea: "+linea+" Columna: "+columna;
	}
	@Override
	public Object accept(Visitor v, Object object) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
	
}
