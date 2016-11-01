package AST;

public abstract class NodoASTAbstract implements NodoAST{

	public int linea;
	public int columna;
	
	
	
	public NodoASTAbstract(int linea, int columna) {
		super();
		this.linea = linea;
		this.columna = columna;
	}
	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return linea;
	}
	@Override
	public int getLine() {
		// TODO Auto-generated method stub
		return columna;
	}
}
