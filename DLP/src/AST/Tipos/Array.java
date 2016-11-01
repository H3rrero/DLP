package AST.Tipos;

import AST.Campo;
import AST.Expresion;
import AST.NodoASTAbstract;
import visitor.Visitor;

public class Array  extends AbstractTipo{

	public int tamañoRec;
	public Tipo tipo;
	public Array(int linea, int columna, int tamañoRec, Tipo tipo) {
		super(linea, columna);
		this.tamañoRec=tamañoRec;
		this.tipo = tipo;
		
	}

	
	public void calcularTamaño()
	{
		tamaño= tipo.getTamaño()*tamañoRec;
	}
	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "array";
	}
	
	public Tipo corchete(Tipo tipo) {
		if(tipo.toString().equals("entero") || tipo.toString().equals("caracter")){
			
			return this.tipo;}
		return null;
	}
}
