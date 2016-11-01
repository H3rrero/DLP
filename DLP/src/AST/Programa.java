package AST;

import java.util.ArrayList;
import java.util.List;

import AST.Tipos.TipoError;
import ME.ManejadorErrores;
import visitor.Visitor;

public class Programa extends NodoASTAbstract {

	

	public List<Definicion> definiciones = new ArrayList<Definicion>();
	
	public Programa(int linea, int columna,List<Definicion> definiciones) {
		super(linea, columna);
		this.definiciones = definiciones;
		 List<DefFuncion> funciones = new ArrayList<DefFuncion>();
		 for (Definicion def : definiciones) {
			if(def instanceof DefFuncion)
				funciones.add((DefFuncion) def);
		}
		 
		if(!funciones.get(funciones.size()-1).nombre.equals("main")){
			TipoError error = new TipoError(funciones.get(funciones.size()-1).getLine(), funciones.get(funciones.size()-1).getColumn(),"Error: La ultima funcio debe ser la funcion main." );
			}
	}

	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
}
