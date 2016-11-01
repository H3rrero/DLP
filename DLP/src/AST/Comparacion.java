package AST;

import AST.Tipos.Tipo;
import visitor.Visitor;

public class Comparacion extends NodoASTAbstract implements Expresion {
	
	public Expresion operando1;
	public Expresion operando2;
	public String operando;
	private boolean valor=true;
	private Tipo tipo;
	public Comparacion(int columnaa,int filaa,Expresion operando1, String operando,Expresion operando2 ) {
		super(filaa, columnaa);
		this.operando1 = operando1;
		this.operando2 = operando2;
		this.operando = operando;
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
