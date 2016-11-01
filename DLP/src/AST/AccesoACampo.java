package AST;

import AST.Tipos.Struct;
import AST.Tipos.Tipo;
import visitor.Visitor;

public class AccesoACampo extends NodoASTAbstract implements Expresion {

	
	public String nombre;
	public Expresion expresion;
	private boolean valor=true;
	private Tipo tipo;
	private Tipo struct;
	public Campo campo;
	public AccesoACampo(int linea, int columna,String nombre,Expresion expresion) {
		super(linea, columna);
		this.nombre=nombre;
		this.nombre=nombre;
		this.expresion=expresion;
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
	this.valor = valor;
		
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


	public Campo getCampo() {
		return campo;
	}


	@Override
	public boolean isAccessoArray() {
		// TODO Auto-generated method stub
		return false;
	}


	public Tipo getStruct() {
		return struct;
	}


	public void setStruct(Tipo struct) {
		this.struct = struct;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre;
	}


	
}
