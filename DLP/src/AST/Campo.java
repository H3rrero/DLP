package AST;

import java.util.ArrayList;
import java.util.List;

import AST.Tipos.Array;
import AST.Tipos.Tipo;
import visitor.Visitor;

public class Campo extends NodoASTAbstract {

	public String nombre;
	public Tipo tipo;
	private int offset;
	public Campo(int linea, int columna,String nombre,Tipo tipo) {
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

	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre;
	}
	

}
