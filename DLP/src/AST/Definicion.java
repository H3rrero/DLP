package AST;

import AST.Tipos.Tipo;
import visitor.Visitor;

public interface Definicion extends NodoAST {

	Tipo getTipo();
	String getNombre();
	Object accept(Visitor v,Object object);
	int getAmbito();
	void setAmbito(int ambito);
}
