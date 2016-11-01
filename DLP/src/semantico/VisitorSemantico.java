package semantico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import AST.Tipos.Tipo;
import AST.Tipos.TipoError;
import AST.Tipos.Void;
import visitor.Visitor;
public class VisitorSemantico implements Visitor {

	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Real real, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Struct struct, Object object) {
		// TODO Auto-generated method stub
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
		accesoAcampo.setValue(true);
		
		if(accesoAcampo.expresion.getTipo().punto(accesoAcampo.nombre)!=null){
			
			accesoAcampo.setTipo(accesoAcampo.expresion.getTipo().punto(accesoAcampo.nombre));}
		else 
			accesoAcampo.setTipo(new TipoError(accesoAcampo.getLine(), accesoAcampo.getColumn(), "el campo al que intentas acceder no existe"));
		return null;
	}

	@Override
	public Object visit(AccesoArray accesoArray, Object object) {
		accesoArray.nombre.accept(this, object);
		accesoArray.posicion.accept(this, object);
		accesoArray.setValue(true);
		if(accesoArray.nombre.getTipo().corchete(accesoArray.posicion.getTipo())!=null){
			accesoArray.setTipo(accesoArray.nombre.getTipo().corchete(accesoArray.posicion.getTipo()));}
		else {
			accesoArray.setTipo(new TipoError(accesoArray.getColumn(), accesoArray.getLine(),
					"error en el tipo, se esperaba un entero o un caracter y se ha recibido un: "+accesoArray.posicion.getTipo()));}
		return null;
	}

	@Override
	public Object visit(Aritmetica aritmetica, Object object) {
		aritmetica.operando1.accept(this, object);
		aritmetica.operando2.accept(this, object);
		aritmetica.setValue(false);
		Tipo tr = aritmetica.operando1.getTipo().aritmetica(aritmetica.operando2.getTipo());
		if(tr!=null)
			aritmetica.setTipo(tr);
		else
			aritmetica.setTipo(new TipoError(aritmetica.getColumn(), aritmetica.getLine(), "los tipos de los operandos de la operacion no son compatible"));
		return null;
	}

	@Override
	public Object visit(Asignacion asignacion, Object object) {
		asignacion.expresion1.accept(this, object);
		asignacion.exprresion2.accept(this, object);
		if(!asignacion.expresion1.getValue())
		{
			asignacion.setTipo(new TipoError(asignacion.getColumn(), asignacion.getLine(), "La asignacion debe contener una expresion valida"));
		}
		Tipo tr = asignacion.expresion1.getTipo().asignacion(asignacion.exprresion2.getTipo());
		if(tr!=null)
			asignacion.setTipo(tr);
		else
			asignacion.setTipo(new TipoError(asignacion.getColumn(), asignacion.getLine(), "los tipos de los operandos de la operacion no son compatible"));
		
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
		cast.setValue(false);
		if(cast.Tipo.cast(cast.operando1.getTipo())!=null)
		cast.setTipo(cast.Tipo.cast(cast.operando1.getTipo()));
		else
			cast.setTipo(new TipoError(cast.linea, cast.columna, "No se puede hacer cast de "+cast.operando1.getTipo().toString()+" a "+cast.Tipo.toString()));
		return null;
	}

	@Override
	public Object visit(Comparacion comparacion, Object object) {
		comparacion.operando1.accept(this, object);
		comparacion.operando2.accept(this, object);
		comparacion.setValue(true);
		Tipo tr = comparacion.operando1.getTipo().comparacion(comparacion.operando2.getTipo());
		if(tr!=null)
			comparacion.setTipo(tr);
		else
			comparacion.setTipo(new TipoError(comparacion.getColumn(), comparacion.getLine(), "los tipos de los operandos de la operacion no son compatible"));
		return null;
	}

	@Override
	public Object visit(DeclaracionVariable declaracion, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DefFuncion defFcuncion, Object object) {
		for (DeclaracionVariable dec : defFcuncion.declaraciones) {
			dec.accept(this, object);
		}
		for (Sentencia sen : defFcuncion.sentencias) {
			sen.accept(this, ((Funcion)defFcuncion.tipo).retorno);
		}
		return null;
	}

	@Override
	public Object visit(Escritura escritura, Object object) {
		for (Expresion exp : escritura.expresiones) {
			exp.accept(this, object);
			if(!exp.getTipo().esBasico())
			{
				new TipoError(escritura.getColumn(),escritura.getLine() , "La escritura debe contener una expresion valida");
				
			}
		}
		return null;
	}

	@Override
	public Object visit(If iff, Object object) {
		iff.condicion.accept(this, object);
		if(!iff.condicion.getTipo().esLogico())
			iff.condicion.setTipo(new TipoError(iff.getColumn(),iff.getLine() , "El tipo de la condicion debe ser un tipo logico"));
		for (Sentencia s : iff.cuerpo_if) {
			s.accept(this, object);
		}
		for (Sentencia s : iff.cuerpo_else) {
			s.accept(this, object);
		}
		return null;
	}

	@Override
	public Object visit(InvocFuncion invocFuncion, Object object) {
		List<Tipo> tipos = new ArrayList<Tipo>();
		for (Expresion exp : invocFuncion.argumentos) {
			exp.accept(this, object);
			tipos.add(exp.getTipo());
		}
		invocFuncion.setValue(true);
		
		invocFuncion.setTipo(invocFuncion.getFuncion().parentesis(tipos));
		return null;
	}

	@Override
	public Object visit(Lectura lectura, Object object) {
		for (Expresion exp : lectura.expresiones) {
			exp.accept(this, object);
			if(!exp.getTipo().esBasico())
			{
				new TipoError(lectura.getColumn(),lectura.getLine() , "La lectura debe contener una expresion valida");
				
			}
		}
		
		return null;
	}

	@Override
	public Object visit(LiteralCaracter literal, Object object) {
		literal.setTipo(new Caracter(literal.linea, literal.columna));
		literal.setValue(true);
		return null;
		
	}

	@Override
	public Object visit(LiteralEntero literalEntero, Object object) {
		literalEntero.setTipo(new Entero(literalEntero.linea, literalEntero.columna));
		literalEntero.setValue(true);
		return null;
	}

	@Override
	public Object visit(LiteralReal literalReal, Object object) {
		literalReal.setTipo(new Real(literalReal.linea, literalReal.columna));
		literalReal.setValue(false);
		return null;
	}

	@Override
	public Object visit(Logica logica, Object object) {
		logica.operando1.accept(this, object);
		logica.operando2.accept(this, object);
		logica.setValue(true);
		Tipo tr = logica.operando1.getTipo().logica(logica.operando2.getTipo());
		if(tr!=null)
			logica.setTipo(tr);
		else
			logica.setTipo(new TipoError(logica.getColumn(), logica.getLine(), "los tipos de los operandos de la operacion no son compatible"));
		return null;
	}

	@Override
	public Object visit(LogicaUnaria unaria, Object object) {
		unaria.operando1.accept(this, object);
		unaria.setValue(true);
		if(unaria.operando1.getTipo().logica()!=null)
			unaria.setTipo(unaria.operando1.getTipo().logica());
		else
			unaria.setTipo(new TipoError(unaria.getColumn(), unaria.getLine(), "los tipos de los operandos de la operacion no son compatibles"));
		return null;
	}

	@Override
	public Object visit(MenosUnario menosUnario, Object object) {
		menosUnario.setValue(false);
		menosUnario.variable.accept(this, object);
		if(menosUnario.variable.getTipo().aritmetica()!=null)
			menosUnario.setTipo(menosUnario.variable.getTipo().aritmetica());
		else
			menosUnario.setTipo(new TipoError(menosUnario.getColumn(), menosUnario.getLine(), "los tipos de los operandos de la operacion no son compatibles"));
		
		return null;
	}

	
	@Override
	public Object visit(Retorno retorno, Object object) {
		retorno.expresion.accept(this, object);
		if(((Tipo)object).toString()!=retorno.expresion.getTipo().toString())
			retorno.expresion.setTipo(new TipoError(retorno.expresion.getColumn(), retorno.expresion.getLine(), "El tipo del retorno no coincide con el indicado en la funcion"));
			return null;
	}

	@Override
	public Object visit(Variable variable, Object object) {
		variable.setValue(true);
		return null;
	}

	@Override
	public Object visit(While whilee, Object object) {
		whilee.expresion.accept(this, object);
		if(!whilee.expresion.getTipo().toString().equals("entero"))
			whilee.expresion.setTipo(new TipoError(whilee.expresion.getColumn(),whilee.expresion.getLine() , "El tipo de la condicion debe ser entero"));
		for (Sentencia s : whilee.cuerpo) {
			s.accept(this, object);
		}
		return null;
	}

}
