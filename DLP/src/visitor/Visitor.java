package visitor;

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
import AST.Tipos.*;
import AST.Tipos.Void;

public interface Visitor {

	Object visit(Array array, Object object);
	Object visit(Caracter caracter, Object object);
	Object visit(Entero entero, Object object);
	Object visit(Funcion funcion, Object object);
	Object visit(Real real, Object object);
	Object visit(Struct struct, Object object);
	Object visit(Void voidd, Object object);
	
	Object visit(AccesoACampo accesoAcampo, Object object);
	Object visit(AccesoArray accesoArray, Object object);
	Object visit(Aritmetica aritmetica, Object object);
	Object visit(Asignacion asignacion, Object object);
	Object visit(Campo campo, Object object);
	Object visit(Cast cast, Object object);
	Object visit(Comparacion comparacion, Object object);
	Object visit(DeclaracionVariable declaracion, Object object);
	Object visit(DefFuncion defFcuncion, Object object);
	Object visit(Escritura escritura, Object object);
	Object visit(If iff, Object object);
	Object visit(InvocFuncion invocFuncion, Object object);
	Object visit(Lectura lectura, Object object);
	Object visit(LiteralCaracter literal, Object object);
	Object visit(LiteralEntero literalEntero, Object object);
	Object visit(LiteralReal literalReal, Object object);
	Object visit(Logica logica, Object object);
	Object visit(LogicaUnaria unaria, Object object);
	Object visit(MenosUnario menosUnario, Object object);
	Object visit(Programa programa, Object object);
	Object visit(Retorno retorno, Object object);
	Object visit(Variable variable, Object object);
	Object visit(While whilee, Object object);
}
