%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexico.Lexico;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import AST.*;
import AST.Tipos.*;
import AST.Tipos.Void;
%}

// * Declaraciones Yacc
%token CTE_ENTERA ID  READ WRITE WHILE IF ELSE INT DOUBLE CHAR IGUALDAD DISTINTO MAYORIGUAL MENORIGUAL    
%token CTE_REAL CTE_CARACTER O Y STRUCT RETURN INPUT PRINT AS AND OR DEF CAST

//cuanto mas abajo mas prioridad
%right IF ELSE 
%right REGLA_IDENT
%right '='
%left Y O '!'
%left '>' '<'  IGUALDAD DISTINTO MAYORIGUAL MENORIGUAL 
%left '+' '-'
%left '*' '/' '%'
%right MUNARIO
%left '[' ']' '.'
%left CAST
%nonassoc '(' ')'
%%

programa : lista_definiciones   {ast= new Programa (1,1,(List<Definicion>)$1);}
		   ;

lista_definiciones : lista_definiciones definiciones	{ $$ = $1; 
																for (Definicion def : (List<Definicion>)$2) {
																((List<Definicion>)$1).add(def);
																}
																	 }
					  | definiciones					{ $$ =(List<Definicion>)$1; }			
					  ;	   
		   
definiciones : defFuncion        { $$ = new ArrayList<DefFuncion>();((List<DefFuncion>)$$).add((DefFuncion)$1); }
			   | declaracion     {$$ = (List<DeclaracionVariable>)$1; } 							
			   ;		  		 		  
		  
defFuncion : DEF ID '(' parametros ')' AS tipo '{' lista_de_declaraciones lista_de_sentencias '}'	{    Funcion f = new Funcion(lexico.getYycolumn(),lexico.getYyline(),(List<DeclaracionVariable>)$4,(Tipo)$7);
																										 $$= new DefFuncion (lexico.getYycolumn(),lexico.getYyline(), (String)$2,f,(List<DeclaracionVariable>)$9,(List<Sentencia>)$10);}
             |DEF ID '(' parametros ')' '{' lista_de_declaraciones lista_de_sentencias  '}' {			Void v = new Void(lexico.getYycolumn(),lexico.getYyline());
             																							Funcion f = new Funcion(lexico.getYycolumn(),lexico.getYyline(),(List<DeclaracionVariable>)$4,v);
																										 $$= new DefFuncion (lexico.getYycolumn(),lexico.getYyline(), (String)$2,f,(List<DeclaracionVariable>)$7,(List<Sentencia>)$8);}
	  

lista_de_sentencias : sentencia lista_de_sentencias 		{ $$ = $2; ((List)$2).add((Sentencia)$1); }		
					  | 						        { $$ = new ArrayList<Sentencia>(); }
					  ;

lista_de_declaraciones : lista_de_declaraciones declaracion  
					 											 { $$ = $1; 
																for (DeclaracionVariable def : (List<DeclaracionVariable>)$2) {
																((List<DeclaracionVariable>)$1).add(def);
																}
																	 }
					  | 										{ $$ = new ArrayList<DeclaracionVariable>(); } 									
					  ;								
					  	
declaracion : lista_indentificadores AS tipo ';'  				{   $$ = new ArrayList<DeclaracionVariable>();
																	for (String string : (List<String>)$1) {
																	DeclaracionVariable def = new DeclaracionVariable (lexico.getYyline(),lexico.getYycolumn(),string,(Tipo)$3);
																	 ((List<DeclaracionVariable>)$$).add(def);
																									}
																 }
													 
			  ;	 

lista_indentificadores : lista_indentificadores ',' ID			{ $$ = $1;
																for (String iden : (List<String>)$1) {
																	if(iden.equals((String)$3))
																	{
																	TipoError error = new TipoError(lexico.getYyline(),lexico.getYycolumn(),"Error:Ya existe un identificador con ese nombre");
																	}
																									}  	
																		((List<String>)$1).add((String)$3);	    				   }
						 | ID									{ $$ = new ArrayList<String>();((List<String>)$$).add((String)$1); }
						 ;
	  
parametros :  parametros ',' parametro 							{ $$ = $1; 
																for (DeclaracionVariable def : (List<DeclaracionVariable>)$3) {
																((List<DeclaracionVariable>)$1).add(def);
																}
																	 }
			| parametro											{ $$ = (List<DeclaracionVariable>)$1; }
			;
   
			
parametro : ID AS tipo											{$$ =new ArrayList<DeclaracionVariable>(); ((List<DeclaracionVariable>)$$).add(new DeclaracionVariable (lexico.getYyline(),lexico.getYycolumn(),(String)$1,(Tipo)$3));}
			|   												{$$ = new ArrayList<DeclaracionVariable>();}
			  ;	
     
argumentos_InvocFuncion :  argumentos_InvocFuncion ',' InvocFuncion   { $$ = $1; 
																			for (Expresion def : (List<Expresion>)$3) {
																			((List<Expresion>)$1).add(def);
																			}
																		 }
			| InvocFuncion											  { $$ = (List<Expresion>)$1; }
			;
			
InvocFuncion : expresion 												{$$ = new ArrayList<Expresion>();((List<Expresion>)$$).add((Expresion)$1); }
			| 															{$$ = new ArrayList<Expresion>(); }
			;				  
		  
expresion: expresion '+' expresion 								{$$= new Aritmetica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"+",(Expresion)$3);}
		 | expresion '-' expresion 								{$$= new Aritmetica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"-",(Expresion)$3);}
         | expresion '*' expresion 								{$$= new Aritmetica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"*",(Expresion)$3);}
         | expresion '/' expresion 								{$$= new Aritmetica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"/",(Expresion)$3);}
         | expresion '%' expresion 								{$$= new Aritmetica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"%",(Expresion)$3);}
         | expresion CAST tipo									{$$= new Cast (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,(Tipo)$3);}
         | expresion '.' ID										{$$= new AccesoACampo (lexico.getYycolumn(),lexico.getYyline(), (String)$3,(Expresion)$1);}
         | ID '(' argumentos_InvocFuncion ')'					{$$= new InvocFuncion (lexico.getYycolumn(),lexico.getYyline(), (List<Expresion>)$3,(String)$1);}
         | '(' expresion ')'       								{ $$ = (Expresion)$2; }
         | CTE_ENTERA              								{$$= new LiteralEntero (lexico.getYyline(),lexico.getYycolumn(), (Integer)$1);}
         | CTE_REAL				   								{$$= new LiteralReal (lexico.getYyline(),lexico.getYycolumn(), (Double)$1);}
         | CTE_CARACTER			   								{$$= new LiteralCaracter (lexico.getYyline(),lexico.getYycolumn(), (String)$1);}
         | ID %prec REGLA_IDENT		   								{$$= new Variable (lexico.getYyline(),lexico.getYycolumn(), (String)$1);}
         | expresion '[' expresion ']'		   					{$$= new AccesoArray (lexico.getYyline(),lexico.getYycolumn(), (Expresion)$1,(Expresion)$3 );}
         | reglas_comparacion									{$$=(Expresion)$1;}
         |'-' expresion      %prec MUNARIO  					{$$= new MenosUnario (lexico.getYyline(),lexico.getYycolumn(),(Expresion)$2);}
         ;
         
reglas_logica:   exp_bool  Y   exp_bool 							{$$= new Logica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"&&",(Expresion)$3);}
         		| exp_bool O exp_bool 								{$$= new Logica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"||",(Expresion)$3);}
        		| '!' expresion										{$$= new LogicaUnaria (lexico.getYycolumn(),lexico.getYyline(),(Expresion)$2);}
        		|'('exp_bool')'									{ $$ = (Expresion)$2; }
         ;
         
reglas_comparacion:  expresion '>' expresion								{$$= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,">",(Expresion)$3);}
         | expresion '<' expresion								{$$= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"<",(Expresion)$3);}
         | expresion MAYORIGUAL expresion						{$$= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,">=",(Expresion)$3);}
         | expresion MENORIGUAL expresion 						{$$= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"<=",(Expresion)$3);}
         | expresion DISTINTO expresion 						{$$= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"!=",(Expresion)$3);}
         | expresion IGUALDAD expresion							{$$= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)$1,"==",(Expresion)$3);}
         ;

 											
exp_bool: expresion		%prec REGLA_IDENT		  								{$$=(Expresion)$1;}
		 |reglas_logica									{$$=(Expresion)$1;}
					 
lista_de_campos : lista_de_campos campo  					{ $$ = $1; 
																for (Campo def : (List<Campo>)$2) {
																for (Campo campos : (List<Campo>)$1) {
																if(def.nombre.equals(campos.nombre))
																	{
																	TipoError error = new TipoError(lexico.getYyline(),lexico.getYycolumn(),"Error:Ya existe un campo con el mismo identificador");
																	}
																
																}
																((List<Campo>)$1).add(def);
																}
																	 }
					  | campo								{ $$ = $1; }	
					  ;

campo : lista_indentificadores AS tipo ';'  				{   $$ = new ArrayList<Campo>();
																	for (String string : (List<String>)$1) {
																	Campo def = new Campo (lexico.getYyline(),lexico.getYycolumn(),string,(Tipo)$3);
																	 ((List<Campo>)$$).add(def);
																									}
																 }										 
			  ;	


sentencia : PRINT expresiones ';'                                 {$$= new Escritura (lexico.getYyline(),lexico.getYycolumn(), (List)$2);}
			| INPUT expresiones ';'								{$$= new Lectura (lexico.getYyline(),lexico.getYycolumn(), (List)$2);}
			| expresion '=' expresion ';'						{$$= new Asignacion (lexico.getYyline(),lexico.getYycolumn(), (Expresion)$1,(Expresion)$3);}
			| RETURN expresion ';'								{$$= new Retorno (lexico.getYyline(),lexico.getYycolumn(), (Expresion)$2);}
			| ID '(' argumentos_InvocFuncion ')' ';'			{$$= new InvocFuncion (lexico.getYycolumn(),lexico.getYyline(), (List<Expresion>)$3,(String)$1);}
			|while												{$$ = (While)$1 ;}
			|if													{$$ = (If)$1 ;}
			;
			
expresiones : expresiones ',' exp_bool                         { $$ = $1; ((List)$1).add((Expresion)$3); }
			 | exp_bool										{ $$ = new ArrayList<Expresion>();((List)$$).add($1); }
			 ;				
			
while :   WHILE exp_bool '{' lista_de_sentencias '}'           { $$ = new While(lexico.getYyline(),lexico.getYycolumn(),(List<Sentencia>)$4,(Expresion)$2) ;}
	    | WHILE exp_bool  sentencia						{ List<Sentencia> f = new ArrayList<Sentencia>();
	    															f.add((Sentencia)$3);
	    														$$ = new While(lexico.getYyline(),lexico.getYycolumn(),f,(Expresion)$2) ;}
	   ;
		
if   :  IF exp_bool sentencias_if     { List<Sentencia> f = new ArrayList<Sentencia>();$$ = new If(lexico.getYyline(),lexico.getYycolumn(),(List<Sentencia>)$3,f,(Expresion)$2) ;}
	   |IF exp_bool sentencias_if  ELSE sentencias_else  { $$ = new If(lexico.getYyline(),lexico.getYycolumn(),(List<Sentencia>)$3,(List<Sentencia>)$5,(Expresion)$2) ;}
	   


sentencias_if : '{' lista_de_sentencias '}'  { $$ = (List<Sentencia>)$2; }
				| sentencia					 {List<Sentencia> f = new ArrayList<Sentencia>() ; f.add((Sentencia)$1);$$ = f; }
				;

sentencias_else : '{' lista_de_sentencias '}'  { $$ = (List<Sentencia>)$2; }
				| sentencia					 {List<Sentencia> f = new ArrayList<Sentencia>() ; f.add((Sentencia)$1);$$ = f; }
				;

tipo :  INT														{$$= new Entero (lexico.getYyline(),lexico.getYycolumn());}
	  | DOUBLE													{$$= new Real (lexico.getYyline(),lexico.getYycolumn());}
	  | CHAR													{$$= new Caracter (lexico.getYyline(),lexico.getYycolumn());}
	  | tipo '[' CTE_ENTERA ']'									{$$= new Array (lexico.getYyline(),lexico.getYycolumn(),(int)$3,(Tipo)$1);}
	  | STRUCT '{' lista_de_campos '}'							{$$= new Struct (lexico.getYycolumn(),lexico.getYyline(), (List<Campo>)$3);}
%%

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Lexico lexico;
public NodoAST ast;

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
    } catch(Throwable e) {
	    System.err.println ("Error Léxico en línea " + lexico.getYyline()+
		" y columna "+lexico.getYycolumn()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Error Sintáctico en línea " + lexico.getYyline()+
		" y columna "+lexico.getYycolumn()+":\n\t"+error);
}

// * El yylval no es un atributo público
public Object getYylval() {
    	return yylval;
}
public void setYylval(Object yylval) {
        this.yylval = yylval;
}

// * Constructor del Sintáctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
	lexico.setParser(this);
}
