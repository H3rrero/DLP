package tablasimbolos;

import java.util.*;
import AST.Definicion;
import AST.Tipos.TipoError;

public class TablaSimbolos {
	
	private int ambito=0;
	private List<Map<String,Definicion>> tabla;
	private Map<String, Definicion> ambito0 = new HashMap<String,Definicion>();
	public TablaSimbolos()  {
		tabla = new ArrayList<Map<String,Definicion>>();
		tabla.add(ambito0);
	}

	public void set() {
		Map<String, Definicion> ambito1 = new HashMap<String,Definicion>();
		tabla.add(ambito1);
		ambito++;
	}
	
	public void reset() {
		tabla.remove(ambito);
		ambito--;
	}
	
	public int getAmbito() {
		return ambito;
	}

	public boolean insertar(Definicion simbolo) {
		if(!tabla.get(ambito).containsKey(simbolo.getNombre())){
		tabla.get(ambito).put(simbolo.getNombre(), simbolo);
		return true;}
		else{
			return false;
		}
	}
	
	public Definicion buscar(String id) {
		for (int i = tabla.size()-1; i >= 0; i--) {
			if(tabla.get(i).containsKey(id))
				return tabla.get(i).get(id);
			
				
		}
		return null;
	}

	public Definicion buscarAmbitoActual(String id) {
		if(tabla.get(ambito).containsKey(id))
			return tabla.get(ambito).get(id);
		return null;
	}
}
