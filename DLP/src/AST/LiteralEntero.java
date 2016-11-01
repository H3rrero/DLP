package AST;

import AST.Tipos.Tipo;
import visitor.Visitor;

public class LiteralEntero extends NodoASTAbstract implements Expresion{
	
	public int valor;
	private boolean valorb=false;
	private Tipo tipo;
	public LiteralEntero(int linea, int columna, int valor) {
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
		return valorb;
	}


	@Override
	public void setValue(boolean valor) {
		this.valorb=valor;
		
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
