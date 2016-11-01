package AST;

import AST.Tipos.Tipo;
import visitor.Visitor;

public interface Expresion extends NodoAST {

	Object accept(Visitor v, Object param);
	boolean getValue();
	void setValue(boolean valor);
	void setTipo(Tipo tipo);
	Tipo getTipo();
	boolean isAccessoArray();
}
