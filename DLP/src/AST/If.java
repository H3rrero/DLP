package AST;

import java.util.ArrayList;
import java.util.List;

import visitor.Visitor;

public class If extends NodoASTAbstract implements Sentencia{

	
	public List<Sentencia> cuerpo_if = new ArrayList<Sentencia>();
	public List<Sentencia> cuerpo_else = new ArrayList<Sentencia>();
	public Expresion condicion;
	public If(int linea, int columna, List<Sentencia> cuerpo_if, List<Sentencia> cuerpo_else,Expresion condicion) {
		super(linea, columna);
		
		this.condicion = condicion;
		
		for (int i = cuerpo_if.size()-1; i >=0; i--) {
			this.cuerpo_if.add(cuerpo_if.get(i));
		}
		for (int i = cuerpo_else.size()-1; i >=0; i--) {
			this.cuerpo_else.add(cuerpo_else.get(i));
		}
	}

	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
}
