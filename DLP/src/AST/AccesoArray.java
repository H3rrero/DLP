package AST;

import AST.Tipos.Tipo;
import visitor.Visitor;

public class AccesoArray extends NodoASTAbstract implements Expresion{

	public Expresion nombre;
	public Expresion posicion;
	private boolean valor=true;
	private Tipo tipo;
	private DeclaracionVariable decv;
	public AccesoArray(int linea, int columna,Expresion nombre,Expresion posicion) {
		super(linea, columna);
		this.nombre=nombre;
		this.posicion = posicion;
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
	public boolean isAccessoArray() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		// TODO A
		return nombre.toString();
	}
}
