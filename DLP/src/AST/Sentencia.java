package AST;

import visitor.Visitor;

public interface Sentencia extends NodoAST{

	Object accept(Visitor v, Object param);
}
