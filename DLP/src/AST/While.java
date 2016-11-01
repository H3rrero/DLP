package AST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import visitor.Visitor;

public class While extends NodoASTAbstract implements Sentencia{

	public List<Sentencia> cuerpo = new ArrayList<Sentencia>();
	public Expresion expresion;
	public While(int linea, int columna, List<Sentencia> cuerpo,Expresion expresion) {
		super(linea, columna);
		
		this.expresion = expresion;
		for (int i = cuerpo.size()-1; i >=0; i--) {
			this.cuerpo.add(cuerpo.get(i));
		}
	}

	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
}
