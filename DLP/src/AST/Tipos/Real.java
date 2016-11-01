package AST.Tipos;

import AST.NodoASTAbstract;
import visitor.Visitor;

public class Real  extends AbstractTipo{

	public Real(int linea, int columna) {
		super(linea, columna);
		tamaño=4;
		// TODO Auto-generated constructor stub
	}


	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
	public boolean esBasico() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public Tipo aritmetica(Tipo tipo) {
		if(tipo instanceof Real||tipo instanceof Caracter || tipo instanceof Entero)
			return this;
		return null;
	}
	public Tipo aritmetica() {
		// TODO Auto-generated method stub
		return this;
	}
	public Tipo asignacion(Tipo tipo) {
		if(tipo instanceof Real||tipo instanceof Caracter || tipo instanceof Entero)
			return this;
		return null;
	}
	public Tipo comparacion(Tipo tipo) {
		if(tipo.esBasico())
			return new Entero(linea, columna);
		else 
			return null;
	}
	public Tipo cast(Tipo tipo) {
		if(tipo.esBasico())
			return this;
		return null;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "real";
	}


	@Override
	public String sufijo() {
		// TODO Auto-generated method stub
		return "f";
	}
	@Override
	public int preferencia() {
		// TODO Auto-generated method stub
		return 3;
	}
}
