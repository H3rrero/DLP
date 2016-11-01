package AST;

import AST.Tipos.Tipo;
import visitor.Visitor;

public class Asignacion extends NodoASTAbstract implements Sentencia{

	
	public Expresion expresion1;
	public Expresion exprresion2;
	private Tipo tipo;
	public Asignacion(int fila, int columna, Expresion expresion1, Expresion exprresion2) {
		super(fila, columna);
		this.expresion1 = expresion1;
		this.exprresion2 = exprresion2;
	}

	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo=tipo;
		
	}


	
	public Tipo getTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}
}
