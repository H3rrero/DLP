package AST;

import AST.Tipos.Tipo;
import visitor.Visitor;

public class LiteralCaracter extends NodoASTAbstract implements Expresion{
	
	public String valor;
	private boolean valorB=false;
	private Tipo tipo;
	public LiteralCaracter(int linea, int columna, String valor) {
		super(linea, columna);
		this.valor = valor;
	}
	

	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}


	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return valorB;
	}


	@Override
	public void setValue(boolean valor) {
		this.valorB=valor;
		
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
