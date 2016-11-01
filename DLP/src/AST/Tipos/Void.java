package AST.Tipos;

import AST.NodoASTAbstract;
import visitor.Visitor;

public class Void extends AbstractTipo{

	public Void(int linea, int columna) {
		super(linea, columna);
		tamaño=0;
	}


	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "void";
	}
}
