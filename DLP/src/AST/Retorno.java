package AST;

import java.util.ArrayList;
import java.util.List;

import visitor.Visitor;

public class Retorno extends NodoASTAbstract implements Sentencia{

	
	public Expresion expresion;
	public Retorno(int linea, int columna,Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
}
