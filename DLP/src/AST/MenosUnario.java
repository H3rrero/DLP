package AST;

import AST.Tipos.Tipo;
import visitor.Visitor;

public class MenosUnario extends NodoASTAbstract implements Expresion {

	
	public Expresion variable;
	private boolean valor=false;
	private Tipo tipo;
	public MenosUnario(int linea, int columna, Expresion variable) {
		super(linea, columna);
		this.variable = variable;
	}

	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return valor;
	}

	@Override
	public void setValue(boolean valor) {
		this.valor=valor;
		
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo=tipo;
	}

	@Override
	public Tipo getTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}

	@Override
	public boolean isAccessoArray() {
		// TODO Auto-generated method stub
		return false;
	}

}
