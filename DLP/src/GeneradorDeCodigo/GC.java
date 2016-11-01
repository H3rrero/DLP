package GeneradorDeCodigo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import AST.Tipos.Tipo;
import AST.Tipos.TipoError;
import ME.ManejadorErrores;

public class GC {
	
	private static GC INSTANCE = null;
	private static PrintWriter  out;
	private int contadorEtiquetas =-1;
	private GC() 
	{
		try {
			GC.out = new PrintWriter("salida.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		public synchronized static void createInstance()  {
		        if (INSTANCE == null) { 
		            INSTANCE = new GC();
		        }
		    }
		 public  static GC  getInstance()  {
			 if (INSTANCE == null) createInstance();
		        return INSTANCE;
		    }

	
	public void pusha(String direccion)
	{
		out.println("PUSHA "+direccion);
		out.flush();
	}	
	public void add(Tipo tipo)
	{
		out.println("ADD"+tipo.sufijo());
		out.flush();
	}
	public void push(Tipo tipo,String valor)
	{
		out.println("PUSH"+tipo.sufijo()+" "+valor);
		out.flush();
	}
	public void push(String valor)
	{
		out.println("PUSH"+" "+valor);
		out.flush();
	}
	public void store(Tipo tipo)
	{
		out.println("STORE"+tipo.sufijo());
		out.flush();
	}
	public void in(Tipo tipo)
	{
		out.println("IN"+tipo.sufijo());
		out.flush();
	}
	public void load(Tipo tipo)
	{
		out.println("LOAD"+tipo.sufijo());
		out.flush();
	}
	public void Mul(Tipo tipo)
	{
		out.println("MUL"+tipo.sufijo());
		out.flush();
	}
	public void Sub(Tipo tipo)
	{
		out.println("SUB"+tipo.sufijo());
		out.flush();
	}
	public void Mod(Tipo tipo)
	{
		out.println("MOD"+tipo.sufijo());
		out.flush();
	}
	public void Div(Tipo tipo)
	{
		out.println("DIV"+tipo.sufijo());
		out.flush();
	}
	public void call(String etiqueta)
	{
		out.println("CALL "+etiqueta);
		out.flush();
	}
	public void halt()
	{
		out.println("HALT");
		out.flush();
	}
	public void out(Tipo tipo)
	{
		out.println("OUT"+tipo.sufijo());
		out.flush();
	}
	public void enter(int tamañoLocales)
	{
		out.println("ENTER "+Math.abs(tamañoLocales));
		out.flush();
	}
	public void ret(Tipo tipoRetorno,int tamañoLocales, int tamañoParam)
	{
		out.println("RET "+tipoRetorno.getTamaño()+","+Math.abs(tamañoLocales)+","+Math.abs(tamañoParam));
		out.flush();
	}
	public void etiquetaFun(String etiqueta)
	{
		out.println(etiqueta+":");
		out.flush();
	}
	public void Gt(Tipo tipo)
	{
		out.println("GT"+tipo.sufijo());
		out.flush();
	}
	public void Lt(Tipo tipo)
	{
		out.println("LT"+tipo.sufijo());
		out.flush();
	}
	public void Ge(Tipo tipo)
	{
		out.println("GE"+tipo.sufijo());
		out.flush();
	}
	public void Le(Tipo tipo)
	{
		out.println("LE"+tipo.sufijo());
		out.flush();
	}
	public void Ne(Tipo tipo)
	{
		out.println("NE"+tipo.sufijo());
		out.flush();
	}
	public void Eq(Tipo tipo)
	{
		out.println("EQ"+tipo.sufijo());
		out.flush();
	}
	public void And(Tipo tipo)
	{
		out.println("AND");
		out.flush();
	}
	public void Or(Tipo tipo)
	{
		out.println("OR");
		out.flush();
	}
	public void Not(Tipo tipo)
	{
		out.println("NOT");
		out.flush();
	}
	public void Pop(Tipo tipo)
	{
		out.println("POP"+tipo.sufijo());
		out.flush();
	}
	public void Cast (Tipo tipo1, Tipo tipo2)
	{
		
		if(Math.abs(tipo1.preferencia()-tipo2.preferencia())== 2)
		{
			out.println(tipo1.sufijo()+"2i");
			out.println("i2"+tipo2.sufijo());
		}
		else
		out.println(tipo1.sufijo()+"2"+tipo2.sufijo());
		
		out.flush();
		
	}
	public void jz(String etiqueta)	{
		out.println("JZ "+"etiqueta"+etiqueta);
		out.flush();
	}
	public void jnz(String etiqueta)	{
		out.println("JNZ "+"etiqueta"+etiqueta);
		out.flush();
	}
	public void jmp(String etiqueta)	{
		out.println("JMP "+"etiqueta"+etiqueta);
		out.flush();
	}
	public void etiqueta(String etiqueta)	{
		out.println("etiqueta"+etiqueta+":");
		out.flush();
	}
	
	public int reservarEtiquetas(int reservas)
	{
		contadorEtiquetas= contadorEtiquetas+reservas;
		return contadorEtiquetas;
		
	}
	
	
	

}
