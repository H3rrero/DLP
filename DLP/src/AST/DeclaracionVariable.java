package AST;

import java.util.ArrayList;
import java.util.List;

import AST.Tipos.Array;
import AST.Tipos.Tipo;
import visitor.Visitor;

public class DeclaracionVariable extends NodoASTAbstract implements Definicion{

	public String nombre;
	public Tipo tipo;
	private int ambito;
	private int offset;
	public DeclaracionVariable(int linea, int columna,String nombre,Tipo tipo) {
		super(linea, columna);
		this.nombre=nombre;
		
		
		if(tipo instanceof Array)
		{   Array myArray = (Array) tipo;
			Array temp = null;
		while(myArray.tipo != null && myArray.tipo instanceof Array)
		{
			temp = (Array) myArray.tipo;
			myArray.tipo=temp.tipo;
			temp.tipo = tipo;
			tipo = temp;
			
		}
			
			
		}
		this.tipo=tipo;
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
		// TODO Auto-generated method stub
		return ambito;
	}

	@Override
	public void setAmbito(int ambito) {
		
		this.ambito=ambito;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
