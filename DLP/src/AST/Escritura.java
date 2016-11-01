package AST;

import java.util.ArrayList;
import java.util.List;

import visitor.Visitor;

public class Escritura extends NodoASTAbstract implements Sentencia{

	
	public List<Expresion> expresiones = new ArrayList<Expresion>();
	public Escritura(int linea, int columna, List<Expresion> expresiones) {
		super(linea, columna);
		this.expresiones = expresiones;
	}

	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
}
