package GeneradorDeCodigo;

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
public class VisitorOffset implements Visitor {

	private int contadorGlobales=0;
	private int contadorLocales;
	private int contadorParametros;
	private int contadorCampos=0;
	@Override
	public Object visit(Programa programa, Object object) {
		for (Definicion  def: programa.definiciones) {
			def.accept(this,object);
		}
		return null;
	}

	
	@Override
	public Object visit(Array array, Object object) {
		array.tipo.accept(this, object);
		array.calcularTamaño();
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
		contadorParametros=0;
		List<DeclaracionVariable> params = new ArrayList<DeclaracionVariable>();
		for (int i = funcion.parametros.size()-1; i >=0; i--) {
			params.add(funcion.parametros.get(i));
		}
		for (DeclaracionVariable declaracionVariable : params) {
			declaracionVariable.setOffset(4+contadorParametros);
			contadorParametros=contadorParametros+declaracionVariable.tipo.getTamaño();
				}
		funcion.parametros=params;
		funcion.setTamañoParametros(contadorParametros);
		return null;
	}

	@Override
	public Object visit(Real real, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Struct struct, Object object) {
		
		for (Campo campo : struct.campos) {
			campo.accept(this, null);
		}
		struct.setTamaño(contadorCampos);
		contadorCampos=0;
		return null;
	}

	@Override
	public Object visit(Void voidd, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AccesoACampo accesoAcampo, Object object) {
		return null;
	}

	@Override
	public Object visit(AccesoArray accesoArray, Object object) {
		return null;
	}

	@Override
	public Object visit(Aritmetica aritmetica, Object object) {
		return null;
	}

	@Override
	public Object visit(Asignacion asignacion, Object object) {
		return null;
	}

	@Override
	public Object visit(Campo campo, Object object) {
		campo.setOffset(contadorCampos);
		campo.tipo.accept(this, object);
		contadorCampos = contadorCampos+campo.tipo.getTamaño();
return null;
	}

@Override
	public Object visit(Cast cast, Object object) {
		return null;
	}

	@Override
	public Object visit(Comparacion comparacion, Object object) {
		return null;
	}

	@Override
	public Object visit(DeclaracionVariable declaracion, Object object) {
		declaracion.tipo.accept(this, null);
	if(declaracion.getAmbito()==0)
		{
			declaracion.setOffset(contadorGlobales);
			contadorGlobales= contadorGlobales+declaracion.getTipo().getTamaño();
		}
		if(declaracion.getAmbito()==1)
		{
			contadorLocales= contadorLocales-declaracion.getTipo().getTamaño();
			declaracion.setOffset(contadorLocales);
			
		}
		return null;
	}

	@Override
	public Object visit(DefFuncion defFcuncion, Object object) {
		contadorLocales=0;
		defFcuncion.tipo.accept(this, object);
		for (DeclaracionVariable dec : defFcuncion.declaraciones) {
			dec.accept(this, object);
			
		}
		for (Sentencia sen : defFcuncion.sentencias) {
			sen.accept(this, object);
		}
		defFcuncion.setTamañoLocales(contadorLocales);
		return null;
	}

	@Override
	public Object visit(Escritura escritura, Object object) {
		
		return null;
	}

	@Override
	public Object visit(If iff, Object object) {
		return null;
	}

	@Override
	public Object visit(InvocFuncion invocFuncion, Object object) {
		return null;
	}

	@Override
	public Object visit(Lectura lectura, Object object) {
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
		return null;
	}

	@Override
	public Object visit(LogicaUnaria unaria, Object object) {
		return null;
	}

	@Override
	public Object visit(MenosUnario menosUnario, Object object) {
		return null;
	}

	
	@Override
	public Object visit(Retorno retorno, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Variable variable, Object object) {
		return null;
	}

	@Override
	public Object visit(While whilee, Object object) {
		return null;
	}

}
