package semantico;

import AST.AccesoACampo;
import AST.AccesoArray;
import AST.Aritmetica;
import AST.Asignacion;
import AST.Campo;
import AST.Cast;
import AST.Comparacion;
import AST.DeclaracionVariable;
import AST.DefFuncion;
import AST.Definicion;
import AST.Escritura;
import AST.Expresion;
import AST.If;
import AST.InvocFuncion;
import AST.Lectura;
import AST.LiteralCaracter;
import AST.LiteralEntero;
import AST.LiteralReal;
import AST.Logica;
import AST.LogicaUnaria;
import AST.MenosUnario;
import AST.Programa;
import AST.Retorno;
import AST.Sentencia;
import AST.Variable;
import AST.While;
import AST.Tipos.Array;
import AST.Tipos.Caracter;
import AST.Tipos.Entero;
import AST.Tipos.Funcion;
import AST.Tipos.Real;
import AST.Tipos.Struct;
import AST.Tipos.TipoError;
import AST.Tipos.Void;
import tablasimbolos.TablaSimbolos;
import visitor.Visitor;

public class VisitorIdentificacion implements Visitor{
	
	TablaSimbolos tabla;
	
	
	public VisitorIdentificacion() {
		this.tabla = new TablaSimbolos();
	}


	@Override
	public Object visit(Programa programa, Object object) {
		for (Definicion  def: programa.definiciones) {
			def.accept(this,object);
		}
		return null;
	}

	
	@Override
	public Object visit(Array array, Object object) {
		return null;
	}

	@Override
	public Object visit(Caracter caracter, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Entero entero, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Funcion funcion, Object object) {
		for (DeclaracionVariable dec : funcion.parametros) {
			dec.accept(this, object);
		}
		
		return null;
	}

	@Override
	public Object visit(Real real, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Struct struct, Object object) {
		for (Campo dec : struct.campos) {
			dec.accept(this, object);
		}
		return null;
	}

	@Override
	public Object visit(Void voidd, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AccesoACampo accesoAcampo, Object object) {
		accesoAcampo.expresion.accept(this, object);
		return null;
	}

	@Override
	public Object visit(AccesoArray accesoArray, Object object) {
		accesoArray.nombre.accept(this, object);
		accesoArray.posicion.accept(this, object);
		
		return null;
	}

	@Override
	public Object visit(Aritmetica aritmetica, Object object) {
		aritmetica.operando1.accept(this, object);
		aritmetica.operando2.accept(this, object);
		return null;
	}

	@Override
	public Object visit(Asignacion asignacion, Object object) {
		asignacion.expresion1.accept(this, object);
		asignacion.exprresion2.accept(this, object);
		return null;
	}

	@Override
	public Object visit(Campo campo, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Cast cast, Object object) {
		cast.operando1.accept(this, object);
		return null;
	}

	@Override
	public Object visit(Comparacion comparacion, Object object) {
		comparacion.operando1.accept(this, object);
		comparacion.operando2.accept(this, object);
		return null;
	}

	@Override
	public Object visit(DeclaracionVariable declaracion, Object object) {
		if(tabla.buscarAmbitoActual(declaracion.nombre)!=null){
			new TipoError(declaracion.getColumn(),declaracion.getLine() , "La variable ya esta definida");
			return null;
		}
		declaracion.setAmbito(tabla.getAmbito());
		tabla.insertar(declaracion);
		return null;
	}

	@Override
	public Object visit(DefFuncion defFcuncion, Object object) {
		if(tabla.buscarAmbitoActual(defFcuncion.nombre)!=null)
		{new TipoError(defFcuncion.getColumn(),defFcuncion.getLine() , "La funcion ya esta definida");
		return null;}
		defFcuncion.setAmbito(tabla.getAmbito());
		tabla.insertar(defFcuncion);
		tabla.set();
		defFcuncion.tipo.accept(this, object);
		for (DeclaracionVariable dec : defFcuncion.declaraciones) {
			dec.accept(this, object);
		}
		for (Sentencia sen : defFcuncion.sentencias) {
			sen.accept(this, object);
		}
		tabla.reset();
		return null;
	}

	@Override
	public Object visit(Escritura escritura, Object object) {
		for (Expresion dec : escritura.expresiones) {
			dec.accept(this, object);
		}
		return null;
	}

	@Override
	public Object visit(If iff, Object object) {
		iff.condicion.accept(this, object);
		for (Sentencia sen : iff.cuerpo_if) {
			sen.accept(this, object);
		}
		for (Sentencia sen : iff.cuerpo_else) {
			sen.accept(this, object);
		}
		return null;
	}

	@Override
	public Object visit(InvocFuncion invocFuncion, Object object) {		
		if(tabla.buscar(invocFuncion.nombre)==null)
			new TipoError(invocFuncion.getLine(),invocFuncion.getColumn() , "La funcion no esta definida");
		else{
			invocFuncion.setFuncion(tabla.buscar(invocFuncion.nombre).getTipo());
		}
		for (Expresion argumento : invocFuncion.argumentos) {
			argumento.accept(this, null);
		}
		return null;
	}

	@Override
	public Object visit(Lectura lectura, Object object) {
		for (Expresion dec : lectura.expresiones) {
			dec.accept(this, object);
		}
		
		return null;
	}

	@Override
	public Object visit(LiteralCaracter literal, Object object) {
		
		return null;
		
	}

	@Override
	public Object visit(LiteralEntero literalEntero, Object object) {
		
		return null;
	}

	@Override
	public Object visit(LiteralReal literalReal, Object object) {
		
		return null;
	}

	@Override
	public Object visit(Logica logica, Object object) {
	logica.operando1.accept(this, object);
	logica.operando2.accept(this, object);
		return null;
	}

	@Override
	public Object visit(LogicaUnaria unaria, Object object) {
		unaria.operando1.accept(this, object);
		return null;
	}

	@Override
	public Object visit(MenosUnario menosUnario, Object object) {
		menosUnario.variable.accept(this, object);
		return null;
	}

	
	@Override
	public Object visit(Retorno retorno, Object object) {
		retorno.expresion.accept(this, object);
		return null;
	}

	@Override
	public Object visit(Variable variable, Object object) {
		
		
		if(tabla.buscar(variable.nombre)==null){
			variable.setTipo(new TipoError(variable.getColumn(),variable.getLine() , "La variable no esta definida"));
			}
		else{
		variable.setTipo(tabla.buscar(variable.nombre).getTipo());
		variable.setDecv((DeclaracionVariable)tabla.buscar(variable.nombre));
		}
		return null;
	}

	@Override
	public Object visit(While whilee, Object object) {
		whilee.expresion.accept(this, object);
		for (Sentencia sen : whilee.cuerpo) {
			sen.accept(this, object);
		}
		return null;
	}

}
