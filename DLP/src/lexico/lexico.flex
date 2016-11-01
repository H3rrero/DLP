// ************  Código a incluir ********************

package lexico;
import sintactico.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************
// * El analizador sintáctico
private Parser parser;
public void setParser(Parser parser) {
	this.parser=parser;
}

// * Para acceder al número de línea (yyline es package)
public int getYyline() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getYycolumn() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

%}

// ************  Patrones (macros) ********************
ConstanteEntera = [0-9]+
Real = ([.][0-9]+)|([0-9]+[.])|([0-9]+[.][0-9]+)|([.][0-9]+[E]+[\-]?[0-9]*)|([0-9]+[.][0-9]+[E]+[\-]?[0-9]*)|([0-9]+[E]+[\-]?[0-9]*)|([0-9]*[e][0-9]*)|([0-9]*[e]\+[0-9]*)
Identificador = [a-zA-ZñÑáéíóúÁÉÍÓÚ][a-zA-Z0-9_ñÑáéíóúÁÉÍÓÚ]*
Caracter = '(\\[0-9]+|[a-zA-ZñÑáéíóúÁÉÍÓÚ]|\\n|\\t|\\r|\\f|.)'
Basura = [\n\r\t\f ]
Comentario = "#"~\n
ComentarioLargo = "'''"~"'''"

%%
// ************  Acciones ********************



def 				{parser.setYylval("def");
         			  return Parser.DEF;  }
         			  
struct                { parser.setYylval("struct");
         			  return Parser.STRUCT;  }
         			  
as					 {parser.setYylval("as");
         			  return Parser.AS;  }

cast				  {parser.setYylval("cast");
         			  return Parser.CAST;  }

int                { parser.setYylval("int");
         			  return Parser.INT;  }
         			  
double                { parser.setYylval("double");
         			  return Parser.DOUBLE;  }
         			  
char                { parser.setYylval("char");
         			  return Parser.CHAR;  }

if                { parser.setYylval("if");
         			  return Parser.IF;  }
         			  
else                { parser.setYylval("else");
         			  return Parser.ELSE;  }

while                { parser.setYylval("while");
         			  return Parser.WHILE;  }
         			  
return 				{ parser.setYylval("return");
         			  return Parser.RETURN;  }
         			  
input                { parser.setYylval("imput");
         			  return Parser.INPUT;  } 
         			  
print				{ parser.setYylval("print");
         			  return Parser.PRINT;  }

">="				{parser.setYylval(yytext());
         			  return Parser.MAYORIGUAL;  }
         			  
"<="				{parser.setYylval(yytext());
         			  return Parser.MENORIGUAL;  }
         			  
"!="				{parser.setYylval(yytext());
         			  return Parser.DISTINTO;  }
         			  
"&&"				 {parser.setYylval(yytext());
         			  return Parser.Y;  }
         			  
"||"				 {parser.setYylval(yytext());
         			  return Parser.O;  }         			  
         			   
         			 
"=="				 {parser.setYylval(yytext());
         			  return Parser.IGUALDAD;  }         			 
         			        			  
"\\126"			{ parser.setYylval(yytext());
         			  return 126;  }          			  
// * Constante Entera
{Comentario}     {}

{ComentarioLargo}     {}

{Identificador}     { parser.setYylval(yytext());
         			  return Parser.ID;  }

{Real}				{ parser.setYylval(new Double(yytext()));
         			  return Parser.CTE_REAL;  }

{Caracter}			{ parser.setYylval(yytext());
         			  return Parser.CTE_CARACTER;  }

{ConstanteEntera}	{ parser.setYylval(new Integer(yytext()));
         			  return Parser.CTE_ENTERA;  }
         			  

"="|
","|
"("|
")"|
";"|
"+"|
"-"|
"*"|
"/"|
"%"|
"{"|
"}"|
"["|
"]"|
"."|
"!"|
"'"|
">"|
"<"|
"'"		{ parser.setYylval(yytext());
         			  return (int)yycharat(0);  }
         			  
{Basura}            {}      	
         			  
.	   { System.out.println("Error léxico en " + getYyline() + ":" + getYycolumn() + ". Lexema = [" + yytext() + "]"); }
