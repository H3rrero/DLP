import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import AST.Programa;
import GeneradorDeCodigo.VisitorGCEjecutar;
import GeneradorDeCodigo.VisitorOffset;
import ME.ManejadorErrores;
import lexico.Lexico;
import semantico.VisitorIdentificacion;
import semantico.VisitorSemantico;
import sintactico.Parser;

/**
 * Prueba del analizador léxico.<br/>
 * Diseño de Lenguajes de Programación.<br/>
 * Escuela de Ingeniería Informática.<br/>
 * Universidad de Oviedo <br/>
 * 
 * @author Francisco Ortin
 */
 
public class Main {

	public static void main(String args[]) throws IOException {
	    if (args.length<1) {
	        System.err.println("Necesito el archivo de entrada.");
	        return;
	    }
	        
		FileReader fr=null;
		try {
			fr=new FileReader(args[0]);
		} catch(IOException io) {
			System.err.println("El archivo "+args[0]+" no se ha podido abrir.");
			return;
		}
		
		// * Creamos léxico y sintáctico
		Lexico lexico = new Lexico(fr);
		Parser parser = new Parser(lexico);
		// * "Parseamos"
		parser.run();
		VisitorSemantico v = new VisitorSemantico();
		VisitorIdentificacion vi = new VisitorIdentificacion();
		VisitorOffset vo = new VisitorOffset();
		VisitorGCEjecutar ve = new VisitorGCEjecutar();
		((Programa)parser.ast).accept(vi, null);
		((Programa)parser.ast).accept(v, null);
		if(ManejadorErrores.getInstance().huboErrores()){
			PrintStream ps = new PrintStream(System.err);
			ManejadorErrores.getInstance().mostrarError(ps);
			return ;}
		((Programa)parser.ast).accept(vo, null);
		((Programa)parser.ast).accept(ve, null);
		
		//descomentar cuanto se tenga bien gramatica con nodos asignados
		IntrospectorModel modelo=new IntrospectorModel("Programa",parser.ast);
		new IntrospectorTree("Introspector", modelo);
	}

}