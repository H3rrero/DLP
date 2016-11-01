package AST.Tipos;

import java.util.List;

import visitor.Visitor;

public class AbstractTipo implements Tipo{

	public int linea;
	public int columna;
	protected int tama�o;
	
	
	public AbstractTipo(int linea, int columna) {
		super();
		this.linea = linea;
		this.columna = columna;
	}
	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return linea;
	}
	@Override
	public int getLine() {
		// TODO Auto-generated method stub
		return columna;
	}
	@Override
	public Object accept(Visitor v, Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean esLogico() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean esBasico() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Tipo aritmetica(Tipo tipo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tipo aritmetica() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tipo asignacion(Tipo tipo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tipo comparacion(Tipo tipo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tipo logica(Tipo tipo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tipo logica() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tipo punto(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tipo corchete(Tipo tipo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tipo parentesis(List<Tipo> tipo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tipo cast(Tipo tipo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getTama�o() {
		return tama�o;
	}
	@Override
	public String sufijo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int preferencia() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setTama�o(int tama�o2) {
		tama�o=tama�o2;
		
	}
	
	
}
