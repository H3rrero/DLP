package ME;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import AST.Tipos.*;
public class ManejadorErrores {
	private static ManejadorErrores INSTANCE = null;

	private static List<TipoError> errores = new ArrayList<TipoError>();
	
	 private ManejadorErrores(List<TipoError> errores) {
		super();
		ManejadorErrores.errores = errores;
	}
	public synchronized static void createInstance() {
	        if (INSTANCE == null) { 
	            INSTANCE = new ManejadorErrores(errores);
	        }
	    }
	 public  static ManejadorErrores  getInstance() {
		 if (INSTANCE == null) createInstance();
	        return INSTANCE;
	    }
	 
	 public void addError(TipoError error)
	 {
		errores.add(error);
	 }
	 public boolean huboErrores()
	 {
		 if(errores.isEmpty())
			 return false;
		 else
			 return true;
	 }
	 public void mostrarError(PrintStream pr)
	 {
		 for (TipoError tipoError : errores) {
			pr.println(tipoError.toString());
		}
	 }
}
