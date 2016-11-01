package AST;

import java.util.ArrayList;
import java.util.List;

import AST.Tipos.Tipo;
import visitor.Visitor;

public class InvocFuncion extends NodoASTAbstract implements Sentencia,Expresion{

	
	public List<Expresion> argumentos = new ArrayList<Expresion>();
	public String nombre;
	private boolean valor=false;
	private Tipo tipo;
	private Tipo	funcion;
	public InvocFuncion(int linea, int columna, List<Expresion> argumentos,String nombre) {
		super(linea, columna);
		this.argumentos = argumentos;
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
		this.valor = valor;
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
		
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

	public Tipo getFuncion() {
		return funcion;
	}

	public void setFuncion(Tipo funcion) {
		this.funcion = funcion;
	}
}
