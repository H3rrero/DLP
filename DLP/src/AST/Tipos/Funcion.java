package AST.Tipos;

import java.util.ArrayList;
import java.util.List;

import AST.Campo;
import AST.DeclaracionVariable;
import AST.NodoASTAbstract;
import visitor.Visitor;

public class Funcion  extends AbstractTipo{

	public List<DeclaracionVariable> parametros = new ArrayList<DeclaracionVariable>();
	public List<DeclaracionVariable> param = new ArrayList<DeclaracionVariable>();
	public Tipo retorno;
	private int tamañoParametros;
	public Funcion(int linea, int columna,List<DeclaracionVariable> parametros,Tipo retorno) {
		super(linea, columna);
		this.parametros = parametros;
		this.retorno=retorno;
	}


	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
	
	public Tipo parentesis(List<Tipo> tipo) {
		if(parametros.size()!= tipo.size())
		return new TipoError(linea, columna, "El numero de parameros no coincide con la definicion de la funcion");
		for (int i = 0; i < tipo.size(); i++) {
			if(!tipo.get(i).toString().equals(parametros.get(i).tipo.toString()))
				return new TipoError(linea, columna, "Los tipos d elos parametros no coincien");
		}
		return retorno;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "funcion";
	}


	public int getTamañoParametros() {
		return tamañoParametros;
	}
	
	public DeclaracionVariable getParametro(int index)
	{
		
		return parametros.get(index);
		
	}

	public DeclaracionVariable getParam(int index)
	{
		
		return param.get(index);
		
	}
	public void setTamañoParametros(int tamañoParametros) {
		this.tamañoParametros = tamañoParametros;
	}
}
