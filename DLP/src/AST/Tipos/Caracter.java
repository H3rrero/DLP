package AST.Tipos;

import AST.NodoASTAbstract;
import visitor.Visitor;

public class Caracter extends AbstractTipo {

	public Caracter(int linea, int columna) {
		super(linea, columna);
		tamaño=1;
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
			return new Entero(linea, columna);
		return null;
	}
	public Tipo aritmetica() {
		// TODO Auto-generated method stub
		return new Entero(linea, columna);
	}
	public Tipo asignacion(Tipo tipo) {	
		if(tipo instanceof Caracter)
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
			return new Entero(linea, columna);
		if(tipo instanceof Entero)
			return tipo;
		return null;
	}
	public Tipo logica() {
		return new Entero(linea, columna);
	}
	public Tipo cast(Tipo tipo) {
		if(tipo.esBasico())
			return this;
		return null;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "caracter";
	}
	@Override
	public String sufijo() {
		// TODO Auto-generated method stub
		return "b";
	}
	@Override
	public int preferencia() {
		// TODO Auto-generated method stub
		return 1;
	}
}
