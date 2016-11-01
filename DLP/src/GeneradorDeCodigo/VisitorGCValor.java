package GeneradorDeCodigo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import AST.AccesoACampo;
import AST.AccesoArray;
import AST.Aritmetica;
import AST.Cast;
import AST.Comparacion;
import AST.DeclaracionVariable;
import AST.Expresion;
import AST.InvocFuncion;
import AST.LiteralCaracter;
import AST.LiteralEntero;
import AST.LiteralReal;
import AST.Logica;
import AST.LogicaUnaria;
import AST.MenosUnario;
import AST.Variable;
import AST.Tipos.Caracter;
import AST.Tipos.Entero;
import AST.Tipos.Funcion;
import AST.Tipos.Real;
import AST.Tipos.Struct;

public class VisitorGCValor extends VisitorGCAbstract {
	VisitorGCDireccion vd = new VisitorGCDireccion(this);
	HashMap<String, Integer> caracteres =new HashMap<String, Integer>();
	public VisitorGCValor() throws IOException {
		super();
		caracteres.put("'"+"\\n"+"'", 10);
		caracteres.put("'"+"\\f"+"'", 12);
		caracteres.put("'"+"\\r"+"'", 13);
		caracteres.put("'"+"\\t"+"'", 9);
		caracteres.put("'"+"\\126"+"'",126);
	}

	@Override
	public Object visit(LiteralEntero literalEntero, Object object) {
		GC.getInstance().push(literalEntero.getTipo(), Integer.toString(literalEntero.valor));
		return null;
	}

	@Override
	public Object visit(LiteralReal literalReal, Object object) {
		GC.getInstance().push(literalReal.getTipo(), Double.toString(literalReal.valor));
		return null;
	}
	
	@Override
	public Object visit(LiteralCaracter literal, Object object) {
		if(literal.valor.length()<=3)
		GC.getInstance().push(literal.getTipo(), Integer.toString(((int)literal.valor.charAt(1))));
		else{
			GC.getInstance().push(literal.getTipo(),""+caracteres.get(""+literal.valor));}
		return null;
	}
	
	@Override
	public Object visit(Variable variable, Object object) {
		variable.accept(vd, null);
		GC.getInstance().load(variable.getTipo());
		return null;
	}

	@Override
	public Object visit(Aritmetica aritmetica, Object object) {
		boolean sonCaracter = false;
		if(aritmetica.operando1.getTipo().preferencia()==1 && aritmetica.operando2.getTipo().preferencia()==1)
			sonCaracter=true;
		aritmetica.operando1.accept(this, null);
		if(sonCaracter)
			GC.getInstance().Cast(aritmetica.operando1.getTipo(), new Entero(aritmetica.linea, aritmetica.columna));
		if(aritmetica.operando1.getTipo().preferencia()<aritmetica.operando2.getTipo().preferencia())
			GC.getInstance().Cast(aritmetica.operando1.getTipo(), aritmetica.operando2.getTipo());
		aritmetica.operando2.accept(this, null);
		if(sonCaracter)
			GC.getInstance().Cast(aritmetica.operando2.getTipo(), new Entero(aritmetica.linea, aritmetica.columna));
		if(aritmetica.operando1.getTipo().preferencia()>aritmetica.operando2.getTipo().preferencia())
			GC.getInstance().Cast(aritmetica.operando2.getTipo(), aritmetica.operando1.getTipo());
		
		switch (aritmetica.operando) {
		case "+":
			GC.getInstance().add(aritmetica.getTipo());
			break;
		case "-":
			GC.getInstance().Sub(aritmetica.getTipo());
			break;
		case "*":
			GC.getInstance().Mul(aritmetica.getTipo());
			break;
		case "/":
			GC.getInstance().Div(aritmetica.getTipo());
			break;
		case "%":
			GC.getInstance().Mod(aritmetica.getTipo());
			break;
		default:
			break;
		}
		return null;
	}
	@Override
	public Object visit(Comparacion comparacion, Object object) {
		comparacion.operando1.accept(this, null);
		if(comparacion.operando1.getTipo().preferencia()!=2)
			GC.getInstance().Cast(comparacion.operando1.getTipo(), new Entero(comparacion.linea, comparacion.columna));
		comparacion.operando2.accept(this, null);
		if(comparacion.operando2.getTipo().preferencia()!=2)
			GC.getInstance().Cast(comparacion.operando2.getTipo(), new Entero(comparacion.linea, comparacion.columna));
		switch (comparacion.operando) {
		case ">":
			GC.getInstance().Gt(comparacion.getTipo());
			break;
		case "<":
			GC.getInstance().Lt(comparacion.getTipo());
			break;
		case ">=":
			GC.getInstance().Ge(comparacion.getTipo());
			break;
		case "<=":
			GC.getInstance().Le(comparacion.getTipo());
			break;
		case "!=":
			GC.getInstance().Ne(comparacion.getTipo());
			break;
		case "==":
			GC.getInstance().Eq(comparacion.getTipo());
			break;
		default:
			break;
		}
		return null;
	}
	@Override
	public Object visit(Logica logica, Object object) {
		logica.operando1.accept(this, null);
		logica.operando2.accept(this, null);
		switch (logica.operando) {
		case "&&":
			GC.getInstance().And(logica.getTipo());
			break;
		case "||":
			GC.getInstance().Or(logica.getTipo());
			break;
		
		default:
			break;
	}
		return null;
	}

	@Override
	public Object visit(LogicaUnaria unaria, Object object) {
		unaria.operando1.accept(this, null);
		GC.getInstance().Not(unaria.getTipo());
		return null;
	}
	@Override
	public Object visit(Cast cast, Object object) {
		cast.operando1.accept(this, null);
		GC.getInstance().Cast(cast.operando1.getTipo(), cast.Tipo);
		return null;
	}
	@Override
	public Object visit(AccesoArray accesoArray, Object object) {
		accesoArray.accept(vd, null);
		GC.getInstance().load(accesoArray.getTipo());
	return null;
	}
	@Override
	public Object visit(AccesoACampo accesoAcampo, Object object) {
		accesoAcampo.accept(vd, null);
		GC.getInstance().load(accesoAcampo.getTipo());
		return null;
	}
	@Override
	public Object visit(InvocFuncion invocFuncion, Object object) {
		List<DeclaracionVariable> param = new ArrayList<DeclaracionVariable>();
			for (int i = ((Funcion)invocFuncion.getFuncion()).parametros.size()-1; i >=0; i--) {
				param.add(((Funcion)invocFuncion.getFuncion()).parametros.get(i));
			}
			((Funcion)invocFuncion.getFuncion()).param=param;
	  		for (int i = 0; i < invocFuncion.argumentos.size(); i++) {
	  			invocFuncion.argumentos.get(i).accept(this, object);
	  			if(invocFuncion.argumentos.get(i).getTipo().getTamaño()!=((Funcion)invocFuncion.getFuncion()).getParam(i).getTipo().getTamaño())
				GC.getInstance().Cast(invocFuncion.argumentos.get(i).getTipo(), 
						((Funcion)invocFuncion.getFuncion()).getParam(i).getTipo());
			}
				
			
		GC.getInstance().call(invocFuncion.nombre);	
		
		return null;
	}
	@Override
	public Object visit(MenosUnario menosUnario, Object object) {
		 
		if(menosUnario.getTipo().getTamaño()!= 4)
		GC.getInstance().push(new Entero(menosUnario.linea, menosUnario.columna),""+0);
		else
		GC.getInstance().push(new Real(menosUnario.linea, menosUnario.columna),""+0.0);	
		menosUnario.variable.accept(this, object);
		GC.getInstance().Sub(menosUnario.getTipo());
		return null;
	}
}
