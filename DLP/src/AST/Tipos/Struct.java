package AST.Tipos;

import java.util.ArrayList;
import java.util.List;

import AST.Campo;
import AST.Definicion;
import AST.NodoASTAbstract;
import visitor.Visitor;

public class Struct  extends AbstractTipo{

	public List<Campo> campos = new ArrayList<Campo>();
	public Struct(int linea, int columna,List<Campo> campos) {
		super(linea, columna);
		this.campos=campos;
		tamaño=calcularTamaño();
	}

	private int calcularTamaño()
	{int contador=0;
		for (Campo campo : campos) {
		contador= contador+campo.tipo.getTamaño();
	}
		return contador;
	}
	public Object accept(Visitor v, Object param)
	{
		return v.visit(this, param);
	}
	public Tipo punto(String nombre) {
		for (Campo campo : campos) {
			if(campo.nombre.equals(nombre))
				return campo.tipo;
		}
		return null;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "struct";
	}
	public Campo getCampo(String id)
	{
		for (Campo campo : campos) {
			if(campo.nombre.equals(id))
				return campo;
		}
		return null;
	}
}
