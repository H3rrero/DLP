package AST.Tipos;

import java.util.List;

import AST.NodoAST;
import visitor.Visitor;

public interface Tipo extends NodoAST {
	Object accept(Visitor v,Object object);
	boolean esLogico();
	boolean esBasico();
	Tipo aritmetica(Tipo tipo);
	Tipo aritmetica();
	Tipo asignacion (Tipo tipo);
	Tipo comparacion (Tipo tipo);
	Tipo logica (Tipo tipo);
	Tipo logica();
	Tipo punto(String nombre);
	Tipo corchete (Tipo tipo);
	Tipo parentesis (List<Tipo> tipo);
	Tipo cast(Tipo tipo);
	int getTamaño();
	void setTamaño(int tamaño);
	String sufijo();
	int preferencia();
}
