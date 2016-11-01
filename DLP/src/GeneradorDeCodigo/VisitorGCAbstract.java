package GeneradorDeCodigo;

import java.io.IOException;

import AST.AccesoACampo;
import AST.AccesoArray;
import AST.Aritmetica;
import AST.Asignacion;
import AST.Campo;
import AST.Cast;
import AST.Comparacion;
import AST.DeclaracionVariable;
import AST.DefFuncion;
import AST.Escritura;
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
import AST.Variable;
import AST.While;
import AST.Tipos.Array;
import AST.Tipos.Caracter;
import AST.Tipos.Entero;
import AST.Tipos.Funcion;
import AST.Tipos.Real;
import AST.Tipos.Struct;
import AST.Tipos.Void;
import visitor.Visitor;

public abstract class VisitorGCAbstract implements Visitor {

	

	@Override
	public Object visit(Array array, Object object) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AccesoArray accesoArray, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Aritmetica aritmetica, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Asignacion asignacion, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Campo campo, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Cast cast, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Comparacion comparacion, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DeclaracionVariable declaracion, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DefFuncion defFcuncion, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Escritura escritura, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(If iff, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(InvocFuncion invocFuncion, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Lectura lectura, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LiteralCaracter literal, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LiteralEntero literalEntero, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LiteralReal literalReal, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Logica logica, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LogicaUnaria unaria, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MenosUnario menosUnario, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Programa programa, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Retorno retorno, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Variable variable, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(While whilee, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
