package AST;

import AST.Tipos.Tipo;
import visitor.Visitor;

public class Variable extends NodoASTAbstract implements Expresion{
	
	private boolean valor=true;
	public String nombre;
	private Tipo tipo;
	private DeclaracionVariable decv;
	public Variable(int fila, int columna, String nombre) {
		super(fila, columna);
		
		this.nombre = nombre;
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

	public DeclaracionVariable getDecv() {
		return decv;
	}

	public void setDecv(DeclaracionVariable decv) {
		this.decv = decv;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public boolean isAccessoArray() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
