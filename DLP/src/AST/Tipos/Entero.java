package AST.Tipos;

import java.util.List;

import AST.NodoASTAbstract;
import visitor.Visitor;

public class Entero extends AbstractTipo{

	public Entero(int linea, int columna) {
		super(linea, columna);
		tamaño=2;
		// TODO Auto-generated constructor stub
	}


	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}


	@Override
	public boolean esLogico() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean esBasico() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public Tipo aritmetica(Tipo tipo) {
		if(tipo instanceof Real)
			return tipo;
		if(tipo instanceof Caracter || tipo instanceof Entero)
			return this;
		return null;
	}
	public Tipo aritmetica() {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	public Tipo asignacion(Tipo tipo) {
		if(tipo instanceof Real)
			return null;
		if(tipo instanceof Caracter || tipo instanceof Entero)
			return this;
		return null;
	}
	public Tipo comparacion(Tipo tipo) {
		if(tipo.esBasico())
			return new Entero(linea, columna);
		else 
			return null;
	}
	public Tipo logica(Tipo tipo) {
		if(tipo instanceof Caracter)
			return this;
		if(tipo instanceof Entero)
			return tipo;
		return null;
	}
	public Tipo logica() {
		return this;
	}
	
	public Tipo cast(Tipo tipo) {
		if(tipo.esBasico())
			return this;
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "entero";
	}
	@Override
	public String sufijo() {
		// TODO Auto-generated method stub
		return "i";
	}
	@Override
	public int preferencia() {
		// TODO Auto-generated method stub
		return 2;
	}
}
