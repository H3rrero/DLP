package AST;

import java.util.ArrayList;
import java.util.List;

import AST.Tipos.Tipo;
import visitor.Visitor;

public class DefFuncion extends NodoASTAbstract implements Definicion{

	public String nombre;
	public Tipo tipo;
	private int ambito;
	private int tamañoLocales;
	public List<DeclaracionVariable> declaraciones = new ArrayList<DeclaracionVariable>();
	public List<Sentencia> sentencias = new ArrayList<Sentencia>();
	public DefFuncion(int linea, int columna,String nombre,Tipo tipo,List<DeclaracionVariable> declaraciones,List<Sentencia> sentencias) {
		super(linea, columna);
		this.nombre=nombre;
		if(tipo!=null)
		this.tipo=tipo;
		this.declaraciones=declaraciones;
		for (int i = sentencias.size()-1; i >=0; i--) {
			this.sentencias.add(sentencias.get(i));
		}
		
		
	}
	@Override
	public String  getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public Tipo getTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}

	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
	@Override
	public int getAmbito() {
		
		return ambito;
	}
	@Override
	public void setAmbito(int ambito) {
		this.ambito=ambito;
		
	}
	public int getTamañoLocales() {
		return tamañoLocales;
	}
	public void setTamañoLocales(int tamañoLocales) {
		this.tamañoLocales = tamañoLocales;
	}
}
