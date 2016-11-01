package tablasimbolos;

import AST.DeclaracionVariable;

public class TablaSimbolosTest {
		
	public void testInsertar() {
		System.out.print("He llegado a la clase assertos");
		TablaSimbolos ts = new TablaSimbolos();
		DeclaracionVariable simbolo = new DeclaracionVariable(0, 0, "a", null);
		assert(ts.insertar(simbolo));
//		assert(simbolo.getAmbito()==0);
		assert(!ts.insertar(simbolo));
		ts.set();
		DeclaracionVariable simbolo2 = new DeclaracionVariable(0, 0, "a", null);
		assert(ts.insertar(simbolo2));
//		assert(simbolo2.getAmbito()==1);
		assert(!ts.insertar(simbolo2));
		ts.reset();
		assert(!ts.insertar(simbolo));
	}
	
	public void testBuscar() {
		TablaSimbolos ts = new TablaSimbolos();
		DeclaracionVariable simbolo = new DeclaracionVariable(0, 0, "a", null);
		assert(ts.insertar(simbolo));
		assert(ts.buscar("a")!=null);
		assert(ts.buscar("b")==null);
		ts.set();
		DeclaracionVariable simbolo2 = new DeclaracionVariable(0, 0, "b", null);
		assert(ts.insertar(simbolo2));
		assert(ts.buscar("b")!=null);
		assert(ts.buscar("a")!=null);
		assert(ts.buscar("c")==null);
		ts.reset();
		assert(ts.buscar("a")!=null);
		assert(ts.buscar("b")==null);
	}

	public void testBuscarAmbitoActual() {
		TablaSimbolos ts = new TablaSimbolos();
		DeclaracionVariable simbolo = new DeclaracionVariable(0, 0, "a", null);
		assert(ts.insertar(simbolo));
		assert(ts.buscarAmbitoActual("a")!=null);
		assert(ts.buscarAmbitoActual("b")==null);
		ts.set();
		DeclaracionVariable simbolo2 = new DeclaracionVariable(0, 0, "b", null);
		assert(ts.insertar(simbolo2));
		assert(ts.buscarAmbitoActual("b")!=null);
		assert(ts.buscarAmbitoActual("a")==null);
		assert(ts.buscarAmbitoActual("c")==null);
		ts.reset();
		assert(ts.buscarAmbitoActual("a")!=null);
		assert(ts.buscarAmbitoActual("b")==null);		
	}
	
	/*public static void main(String[] args) {
		TablaSimbolosTest test = new TablaSimbolosTest();
		test.testInsertar();
		test.testBuscar();
		test.testBuscarAmbitoActual();
	}*/
	
}
