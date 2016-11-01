//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package sintactico;



//#line 2 "../../src/sintactico/sintactico.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import lexico.Lexico;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import AST.*;
import AST.Tipos.*;
import AST.Tipos.Void;
//#line 28 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CTE_ENTERA=257;
public final static short ID=258;
public final static short READ=259;
public final static short WRITE=260;
public final static short WHILE=261;
public final static short IF=262;
public final static short ELSE=263;
public final static short INT=264;
public final static short DOUBLE=265;
public final static short CHAR=266;
public final static short IGUALDAD=267;
public final static short DISTINTO=268;
public final static short MAYORIGUAL=269;
public final static short MENORIGUAL=270;
public final static short CTE_REAL=271;
public final static short CTE_CARACTER=272;
public final static short O=273;
public final static short Y=274;
public final static short STRUCT=275;
public final static short RETURN=276;
public final static short INPUT=277;
public final static short PRINT=278;
public final static short AS=279;
public final static short AND=280;
public final static short OR=281;
public final static short DEF=282;
public final static short CAST=283;
public final static short REGLA_IDENT=284;
public final static short MUNARIO=285;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    3,    3,    8,    8,    7,
    7,    4,   10,   10,    5,    5,   11,   11,   12,   12,
   13,   13,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   16,   16,
   16,   16,   15,   15,   15,   15,   15,   15,   17,   17,
   18,   18,   19,    9,    9,    9,    9,    9,    9,    9,
   20,   20,   21,   21,   22,   22,   23,   23,   24,   24,
    6,    6,    6,    6,    6,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,   11,    9,    2,    0,    2,
    0,    4,    3,    1,    3,    1,    3,    0,    3,    1,
    1,    0,    3,    3,    3,    3,    3,    3,    3,    4,
    3,    1,    1,    1,    1,    4,    1,    2,    3,    3,
    2,    3,    3,    3,    3,    3,    3,    3,    1,    1,
    2,    1,    4,    3,    3,    4,    3,    5,    1,    1,
    3,    1,    5,    3,    3,    5,    3,    1,    3,    1,
    1,    1,    1,    4,    4,
};
final static short yydefred[] = {                         0,
   14,    0,    0,    0,    3,    4,    5,    0,    0,    2,
    0,    0,    0,   71,   72,   73,    0,    0,   13,    0,
    0,   16,    0,    0,   12,    0,    0,    0,    0,    0,
   52,    0,    0,    0,   11,   15,    0,   75,   51,   74,
    0,    0,    0,   11,   32,    0,    0,    0,   33,   34,
    0,    0,    0,    0,    0,   10,    0,    0,    0,   37,
   59,   60,   53,    0,    0,    0,    0,    0,    0,   50,
    0,    0,    0,    0,    0,    0,    0,    0,    7,    0,
    8,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   20,    0,
    0,    0,    0,    0,    0,    0,    0,   64,    0,   68,
    0,   57,   55,    0,   54,   31,    0,    0,    0,    0,
   28,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   29,    6,    0,    0,    0,   42,   40,   39,    0,    0,
    0,    0,   56,   36,   58,   19,   30,   63,   67,    0,
   70,   66,    0,   69,
};
final static short yydgoto[] = {                          3,
    4,    5,    6,    7,   21,   18,   42,   57,   58,    8,
   22,   98,   99,   59,   60,   70,   74,   30,   31,   75,
   61,   62,  111,  152,
};
final static short yysindex[] = {                      -223,
    0, -238,    0, -223,    0,    0,    0,  -41,   -8,    0,
  -63, -222, -217,    0,    0,    0,  -70,  -42,    0, -187,
   72,    0, -164, -159,    0,  -63, -121, -217,  -32, -111,
    0,    7,   21,  -63,    0,    0,  -63,    0,    0,    0,
  -52,  244,  -19,    0,    0,   74,   24,   24,    0,    0,
   -2,   24,   24,   -2,   -2,    0,   -5,  915,    5,    0,
    0,    0,    0,  244,   -2,   81,   -2,   24,  127,    0,
  740,  773,   31, -255,  -43,   11,  -33,   42,    0,   74,
    0,   -2,   -2,   -2,   -2,  -63,   -2,   -2,   -2,   -2,
   -2,   -2,   -2,   -2,   -2, -128,    6,  112,    0,  127,
   -2,  127,   42,  -30,   24,   24,  915,    0,  915,    0,
 -131,    0,    0,   24,    0,    0,  151,  151,  151,  151,
    0,   64,  151,  151,  158,  158,  -33,  -33,  -33,   92,
    0,    0,   84,   -2,  134,    0,    0,    0,   15,   26,
  892, -255,    0,    0,    0,    0,    0,    0,    0,  915,
    0,    0,   43,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,  149,    0,    0,    0,    0,    0,    0,
    0,    0,  135,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  135,    0,    0,
    0,    0,  165,    0,    0,    0,    0,    0,    0,    0,
    0,   46,    0,    0,    0,  -16,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   46,    0,    0,
    0,    0,    0,   46,  166,  -37,    0,    0,  673,    0,
    0,    0,    0,   36,    0,    0,  279,    0,    0,   99,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  172,
  166,  765, -211,    0,    0,    0,   46,    0,   46,    0,
  334,    0,    0,    0,    0,    0,  558,  710,  797,  820,
    0,    0,  843,  866,  467,  521,  364,  402,  431,    0,
    0,    0,  120,  166,    0,    0,    0,    0,    0,    0,
    0,   38,    0,    0,    0,    0,    0,    0,    0,   46,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  180,    0,  -27,    0,   91,  142,   41,  -38,   85,
  164,   98,   80, 1147,    0,    0,   13,    0,  178,  162,
    0,    0,    0,    0,
};
final static int YYTABLESIZE=1281;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         35,
  114,   35,   12,   35,   35,   35,   35,   35,   35,   35,
  136,   12,   96,   38,   56,  113,   25,  105,  106,    9,
   35,   35,   35,   35,   35,   35,   35,   14,   35,   35,
   35,   13,  108,  110,    1,   19,   56,   55,   24,   63,
   20,   94,   54,   35,   35,   35,   92,   90,   24,   91,
   96,   93,   23,   35,  114,   35,   67,   95,    2,   71,
   72,   49,   49,   68,   89,   87,   88,   94,   54,  115,
   44,   24,   92,   90,   35,   91,   96,   93,   94,   62,
  104,   61,  116,   92,   90,   35,   91,   96,   93,  112,
   89,   26,   88,    1,   62,   95,   61,   32,   81,   40,
   94,   89,  151,   88,   97,   92,   90,   29,   91,   96,
   93,   24,   27,   65,   29,   28,   33,  137,  138,   79,
  101,   95,  143,   89,   41,   88,  142,   43,   94,  131,
  132,  141,   95,   92,   90,   35,   91,   96,   93,  148,
   35,   35,  145,   35,   35,   35,    1,  139,    1,  140,
  149,   89,  133,   88,   95,  134,   30,   34,   35,   35,
   35,   30,   30,   94,   30,   30,   30,  154,   92,   90,
    9,   91,   96,   93,  147,   18,  121,  134,   18,   30,
   30,   30,   95,   10,  144,   64,   89,   94,   88,   35,
  153,   36,   92,   90,   94,   91,   96,   93,  135,   92,
   14,   15,   16,   96,   93,   17,   22,   39,   17,   22,
   30,   17,   21,  146,   76,   21,    0,   95,    0,   35,
   35,    0,    0,   35,   35,    0,    0,    0,    0,   35,
   35,   35,   35,   35,   35,   35,   35,   11,   35,   35,
   35,   95,  105,  106,    0,   35,   37,    0,   95,   86,
   35,   35,   35,   35,   45,   66,    0,    0,    0,    0,
    0,    0,   14,    0,    0,    0,   35,    0,   49,   50,
    0,   82,   83,   84,   85,    0,    0,    0,    0,    0,
   45,   66,    0,   55,    0,    0,    0,   86,   54,    0,
    0,    0,    0,    0,   49,   50,    0,   82,   83,   84,
   85,    0,    0,    0,    0,    0,    0,    0,   82,   83,
   84,   85,    0,   86,    0,   38,    0,    0,   38,   38,
   38,   38,   38,   38,   86,   38,    0,    0,    0,    0,
   82,   83,   84,   85,    0,    0,    0,   38,   38,   38,
   38,    0,    0,    0,    0,    0,   86,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   82,   83,
   84,   85,    0,    0,    0,   35,   35,   35,   35,    0,
    0,   38,    0,   65,   86,    0,    0,    0,   65,    0,
    0,   35,    0,    0,    0,    0,   30,   30,   30,   30,
    0,    0,    0,   82,   83,   84,   85,    0,    0,    0,
   25,   38,   30,   25,   25,   25,   25,   25,   25,   86,
   25,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   25,   25,   25,   25,    0,    0,    0,    0,
    0,    0,    0,   86,    0,    0,    0,    0,   26,    0,
   86,   26,   26,   26,   26,   26,   26,    0,   26,    0,
    0,    0,    0,    0,    0,    0,   25,    0,   65,    0,
   26,   26,   26,   26,    0,    0,    0,   27,    0,    0,
   27,   27,   27,   27,   27,   27,    0,   27,    0,    0,
    0,    0,    0,    0,    0,    0,   25,    0,    0,   27,
   27,   27,   27,    0,   26,    0,    0,    0,    0,    0,
   45,   46,    0,    0,   47,   48,   23,   23,    0,   23,
   23,   23,    0,    0,   49,   50,    0,    0,    0,   51,
   52,   53,    0,   27,   26,   23,   23,   23,   23,    0,
    0,    0,    0,    0,    0,   38,   38,    0,    0,   38,
   38,    0,    0,    0,    0,   38,   38,   38,   38,   38,
   38,   38,   38,   27,   38,   38,   38,    0,    0,   23,
   24,   24,    0,   24,   24,   24,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   24,
   24,   24,   24,    0,    0,    0,    0,    0,    0,   23,
   65,   65,    0,    0,   65,   65,    0,   48,   48,    0,
    0,   48,    0,    0,   65,   65,    0,    0,    0,   65,
   65,   65,    0,   24,    0,    0,   48,   48,   48,   48,
   25,   25,    0,    0,   25,   25,    0,    0,    0,    0,
   25,   25,   25,   25,   25,   25,   25,   25,    0,   25,
   25,   25,    0,   24,    0,    0,    0,    0,    0,    0,
   48,    0,    0,    0,    0,    0,    0,    0,   26,   26,
    0,    0,   26,   26,    0,    0,    0,    0,   26,   26,
   26,   26,   26,   26,   26,   26,    0,   26,   26,   26,
   48,    0,    0,    0,    0,    0,    0,   27,   27,    0,
    0,   27,   27,    0,    0,    0,    0,   27,   27,   27,
   27,   27,   27,   27,   27,    0,   27,   27,   27,    0,
    0,    0,   49,   49,    0,    0,   49,    0,    0,    0,
    0,    0,    0,   23,   23,    0,    0,   23,   23,    0,
    0,   49,    0,   23,   23,   23,   23,   23,   23,   23,
   23,    0,   23,   23,   23,    0,    0,    0,    0,   47,
   47,    0,    0,   47,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   47,   47,
   47,   47,    0,    0,    0,    0,    0,   24,   24,   55,
    0,   24,   24,    0,   54,    0,    0,   24,   24,   24,
   24,   24,   24,   24,   24,   49,   24,   24,   24,    0,
    0,    0,   47,    0,   41,   41,    0,    0,   41,    0,
    0,    0,   55,    0,   48,   48,    0,   54,   48,   48,
    0,    0,    0,   41,   48,   48,   48,   48,   48,   48,
   48,   48,   47,   48,   48,   48,   45,   45,    0,    0,
   45,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   45,   45,   45,   45,   46,
   46,    0,  107,   46,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   46,   46,
   46,   46,   43,   43,    0,    0,   43,   41,    0,   45,
    0,    0,    0,    0,    0,  109,    0,    0,    0,    0,
    0,   43,   43,   43,   43,   44,   44,    0,    0,   44,
    0,    0,   46,    0,    0,    0,    0,    0,    0,   45,
    0,    0,    0,    0,   44,   44,   44,   44,    0,   49,
   49,   55,    0,   49,   49,   43,   54,    0,    0,    0,
    0,    0,   46,   49,   49,   49,   49,    0,   49,   49,
   49,    0,    0,    0,   55,    0,    0,    0,   44,   54,
    0,    0,    0,    0,    0,   43,   47,   47,    0,    0,
   47,   47,    0,    0,    0,    0,   47,   47,   47,   47,
   47,   47,   47,   47,    0,   47,   47,   47,   44,    0,
    0,    0,    0,    0,    0,    0,   45,   80,    0,    0,
   47,   48,    0,    0,    0,    0,    0,    0,    0,    0,
   49,   50,  105,  106,  150,   51,   52,   53,    0,    0,
    0,   41,   41,    0,    0,   41,   41,    0,    0,   45,
   80,    0,    0,   47,   48,   41,   41,   41,   41,    0,
   41,   41,   41,   49,   50,  105,  106,    0,   51,   52,
   53,    0,    0,   45,   45,    0,    0,   45,   45,    0,
    0,    0,    0,   45,   45,   45,   45,   45,   45,   45,
   45,    0,   45,   45,   45,    0,   46,   46,    0,    0,
   46,   46,    0,    0,    0,    0,   46,   46,   46,   46,
   46,   46,   46,   46,    0,   46,   46,   46,    0,   43,
   43,    0,    0,   43,   43,    0,    0,    0,    0,   43,
   43,   43,   43,   43,   43,   43,   43,    0,   43,   43,
   43,    0,   44,   44,    0,    0,   44,   44,    0,    0,
    0,    0,   44,   44,   44,   44,   44,   44,   44,   44,
    0,   44,   44,   44,    0,    0,    0,    0,   45,   80,
    0,    0,   47,   48,    0,    0,    0,    0,    0,    0,
    0,    0,   49,   50,    0,    0,    0,   51,   52,   53,
    0,   45,   80,    0,    0,   47,   48,    0,    0,    0,
    0,    0,    0,    0,    0,   49,   50,    0,    0,    0,
   51,   52,   53,   69,   69,    0,    0,   73,   69,   69,
   77,   78,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  100,    0,  102,  103,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  117,  118,
  119,  120,    0,  122,  123,  124,  125,  126,  127,  128,
  129,  130,    0,    0,    0,    0,    0,  100,    0,    0,
    0,   69,   69,    0,    0,    0,    0,    0,    0,    0,
   69,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  100,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   44,  123,   44,   41,   42,   43,   44,   45,   46,   47,
   41,   44,   46,  125,   42,   59,   59,  273,  274,  258,
   37,   59,   60,   61,   62,   42,   43,   44,   45,   46,
   47,   40,   71,   72,  258,  258,   64,   40,   91,   59,
  258,   37,   45,   60,   61,   62,   42,   43,   91,   45,
   46,   47,  123,   91,   44,   93,   33,   91,  282,   47,
   48,  273,  274,   40,   60,   61,   62,   37,   45,   59,
  123,   91,   42,   43,   91,   45,   46,   47,   37,   44,
   68,   44,   41,   42,   43,  123,   45,   46,   47,   59,
   60,  279,   62,  258,   59,   91,   59,  257,   58,   93,
   37,   60,  141,   62,   64,   42,   43,   23,   45,   46,
   47,   91,   41,   40,   30,   44,   26,  105,  106,  125,
   40,   91,   59,   60,   34,   62,  114,   37,   37,  258,
  125,  263,   91,   42,   43,   37,   45,   46,   47,  125,
   42,   43,   59,   45,   46,   47,  258,  107,    0,  109,
  125,   60,   41,   62,   91,   44,   37,  279,   60,   61,
   62,   42,   43,   37,   45,   46,   47,  125,   42,   43,
  125,   45,   46,   47,   41,   41,   86,   44,   44,   60,
   61,   62,   91,    4,   93,   44,   60,   37,   62,   91,
  150,   28,   42,   43,   37,   45,   46,   47,  101,   42,
  264,  265,  266,   46,   47,   41,   41,   30,   44,   44,
   91,  275,   41,  134,   53,   44,   -1,   91,   -1,  257,
  258,   -1,   -1,  261,  262,   -1,   -1,   -1,   -1,  267,
  268,  269,  270,  271,  272,  273,  274,  279,  276,  277,
  278,   91,  273,  274,   -1,  283,  279,   -1,   91,  283,
  267,  268,  269,  270,  257,  258,   -1,   -1,   -1,   -1,
   -1,   -1,  279,   -1,   -1,   -1,  283,   -1,  271,  272,
   -1,  267,  268,  269,  270,   -1,   -1,   -1,   -1,   -1,
  257,  258,   -1,   40,   -1,   -1,   -1,  283,   45,   -1,
   -1,   -1,   -1,   -1,  271,  272,   -1,  267,  268,  269,
  270,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,  268,
  269,  270,   -1,  283,   -1,   37,   -1,   -1,   40,   41,
   42,   43,   44,   45,  283,   47,   -1,   -1,   -1,   -1,
  267,  268,  269,  270,   -1,   -1,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,  283,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,  268,
  269,  270,   -1,   -1,   -1,  267,  268,  269,  270,   -1,
   -1,   93,   -1,   40,  283,   -1,   -1,   -1,   45,   -1,
   -1,  283,   -1,   -1,   -1,   -1,  267,  268,  269,  270,
   -1,   -1,   -1,  267,  268,  269,  270,   -1,   -1,   -1,
   37,  123,  283,   40,   41,   42,   43,   44,   45,  283,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  283,   -1,   -1,   -1,   -1,   37,   -1,
  283,   40,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,  125,   -1,
   59,   60,   61,   62,   -1,   -1,   -1,   37,   -1,   -1,
   40,   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,   -1,   59,
   60,   61,   62,   -1,   93,   -1,   -1,   -1,   -1,   -1,
  257,  258,   -1,   -1,  261,  262,   40,   41,   -1,   43,
   44,   45,   -1,   -1,  271,  272,   -1,   -1,   -1,  276,
  277,  278,   -1,   93,  123,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,   -1,   -1,  261,
  262,   -1,   -1,   -1,   -1,  267,  268,  269,  270,  271,
  272,  273,  274,  123,  276,  277,  278,   -1,   -1,   93,
   40,   41,   -1,   43,   44,   45,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,  123,
  257,  258,   -1,   -1,  261,  262,   -1,   40,   41,   -1,
   -1,   44,   -1,   -1,  271,  272,   -1,   -1,   -1,  276,
  277,  278,   -1,   93,   -1,   -1,   59,   60,   61,   62,
  257,  258,   -1,   -1,  261,  262,   -1,   -1,   -1,   -1,
  267,  268,  269,  270,  271,  272,  273,  274,   -1,  276,
  277,  278,   -1,  123,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
   -1,   -1,  261,  262,   -1,   -1,   -1,   -1,  267,  268,
  269,  270,  271,  272,  273,  274,   -1,  276,  277,  278,
  123,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,   -1,
   -1,  261,  262,   -1,   -1,   -1,   -1,  267,  268,  269,
  270,  271,  272,  273,  274,   -1,  276,  277,  278,   -1,
   -1,   -1,   40,   41,   -1,   -1,   44,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,   -1,   -1,  261,  262,   -1,
   -1,   59,   -1,  267,  268,  269,  270,  271,  272,  273,
  274,   -1,  276,  277,  278,   -1,   -1,   -1,   -1,   40,
   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,  257,  258,   40,
   -1,  261,  262,   -1,   45,   -1,   -1,  267,  268,  269,
  270,  271,  272,  273,  274,  123,  276,  277,  278,   -1,
   -1,   -1,   93,   -1,   40,   41,   -1,   -1,   44,   -1,
   -1,   -1,   40,   -1,  257,  258,   -1,   45,  261,  262,
   -1,   -1,   -1,   59,  267,  268,  269,  270,  271,  272,
  273,  274,  123,  276,  277,  278,   40,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   59,   60,   61,   62,   40,
   41,   -1,  123,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,
   61,   62,   40,   41,   -1,   -1,   44,  123,   -1,   93,
   -1,   -1,   -1,   -1,   -1,  123,   -1,   -1,   -1,   -1,
   -1,   59,   60,   61,   62,   40,   41,   -1,   -1,   44,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,  123,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   -1,  257,
  258,   40,   -1,  261,  262,   93,   45,   -1,   -1,   -1,
   -1,   -1,  123,  271,  272,  273,  274,   -1,  276,  277,
  278,   -1,   -1,   -1,   40,   -1,   -1,   -1,   93,   45,
   -1,   -1,   -1,   -1,   -1,  123,  257,  258,   -1,   -1,
  261,  262,   -1,   -1,   -1,   -1,  267,  268,  269,  270,
  271,  272,  273,  274,   -1,  276,  277,  278,  123,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,   -1,   -1,
  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  271,  272,  273,  274,  123,  276,  277,  278,   -1,   -1,
   -1,  257,  258,   -1,   -1,  261,  262,   -1,   -1,  257,
  258,   -1,   -1,  261,  262,  271,  272,  273,  274,   -1,
  276,  277,  278,  271,  272,  273,  274,   -1,  276,  277,
  278,   -1,   -1,  257,  258,   -1,   -1,  261,  262,   -1,
   -1,   -1,   -1,  267,  268,  269,  270,  271,  272,  273,
  274,   -1,  276,  277,  278,   -1,  257,  258,   -1,   -1,
  261,  262,   -1,   -1,   -1,   -1,  267,  268,  269,  270,
  271,  272,  273,  274,   -1,  276,  277,  278,   -1,  257,
  258,   -1,   -1,  261,  262,   -1,   -1,   -1,   -1,  267,
  268,  269,  270,  271,  272,  273,  274,   -1,  276,  277,
  278,   -1,  257,  258,   -1,   -1,  261,  262,   -1,   -1,
   -1,   -1,  267,  268,  269,  270,  271,  272,  273,  274,
   -1,  276,  277,  278,   -1,   -1,   -1,   -1,  257,  258,
   -1,   -1,  261,  262,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  271,  272,   -1,   -1,   -1,  276,  277,  278,
   -1,  257,  258,   -1,   -1,  261,  262,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  271,  272,   -1,   -1,   -1,
  276,  277,  278,   47,   48,   -1,   -1,   51,   52,   53,
   54,   55,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   65,   -1,   67,   68,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   82,   83,
   84,   85,   -1,   87,   88,   89,   90,   91,   92,   93,
   94,   95,   -1,   -1,   -1,   -1,   -1,  101,   -1,   -1,
   -1,  105,  106,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  114,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  134,
};
}
final static short YYFINAL=3;
final static short YYMAXTOKEN=285;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CTE_ENTERA","ID","READ","WRITE",
"WHILE","IF","ELSE","INT","DOUBLE","CHAR","IGUALDAD","DISTINTO","MAYORIGUAL",
"MENORIGUAL","CTE_REAL","CTE_CARACTER","O","Y","STRUCT","RETURN","INPUT",
"PRINT","AS","AND","OR","DEF","CAST","REGLA_IDENT","MUNARIO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : lista_definiciones",
"lista_definiciones : lista_definiciones definiciones",
"lista_definiciones : definiciones",
"definiciones : defFuncion",
"definiciones : declaracion",
"defFuncion : DEF ID '(' parametros ')' AS tipo '{' lista_de_declaraciones lista_de_sentencias '}'",
"defFuncion : DEF ID '(' parametros ')' '{' lista_de_declaraciones lista_de_sentencias '}'",
"lista_de_sentencias : sentencia lista_de_sentencias",
"lista_de_sentencias :",
"lista_de_declaraciones : lista_de_declaraciones declaracion",
"lista_de_declaraciones :",
"declaracion : lista_indentificadores AS tipo ';'",
"lista_indentificadores : lista_indentificadores ',' ID",
"lista_indentificadores : ID",
"parametros : parametros ',' parametro",
"parametros : parametro",
"parametro : ID AS tipo",
"parametro :",
"argumentos_InvocFuncion : argumentos_InvocFuncion ',' InvocFuncion",
"argumentos_InvocFuncion : InvocFuncion",
"InvocFuncion : expresion",
"InvocFuncion :",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '%' expresion",
"expresion : expresion CAST tipo",
"expresion : expresion '.' ID",
"expresion : ID '(' argumentos_InvocFuncion ')'",
"expresion : '(' expresion ')'",
"expresion : CTE_ENTERA",
"expresion : CTE_REAL",
"expresion : CTE_CARACTER",
"expresion : ID",
"expresion : expresion '[' expresion ']'",
"expresion : reglas_comparacion",
"expresion : '-' expresion",
"reglas_logica : exp_bool Y exp_bool",
"reglas_logica : exp_bool O exp_bool",
"reglas_logica : '!' expresion",
"reglas_logica : '(' exp_bool ')'",
"reglas_comparacion : expresion '>' expresion",
"reglas_comparacion : expresion '<' expresion",
"reglas_comparacion : expresion MAYORIGUAL expresion",
"reglas_comparacion : expresion MENORIGUAL expresion",
"reglas_comparacion : expresion DISTINTO expresion",
"reglas_comparacion : expresion IGUALDAD expresion",
"exp_bool : expresion",
"exp_bool : reglas_logica",
"lista_de_campos : lista_de_campos campo",
"lista_de_campos : campo",
"campo : lista_indentificadores AS tipo ';'",
"sentencia : PRINT expresiones ';'",
"sentencia : INPUT expresiones ';'",
"sentencia : expresion '=' expresion ';'",
"sentencia : RETURN expresion ';'",
"sentencia : ID '(' argumentos_InvocFuncion ')' ';'",
"sentencia : while",
"sentencia : if",
"expresiones : expresiones ',' exp_bool",
"expresiones : exp_bool",
"while : WHILE exp_bool '{' lista_de_sentencias '}'",
"while : WHILE exp_bool sentencia",
"if : IF exp_bool sentencias_if",
"if : IF exp_bool sentencias_if ELSE sentencias_else",
"sentencias_if : '{' lista_de_sentencias '}'",
"sentencias_if : sentencia",
"sentencias_else : '{' lista_de_sentencias '}'",
"sentencias_else : sentencia",
"tipo : INT",
"tipo : DOUBLE",
"tipo : CHAR",
"tipo : tipo '[' CTE_ENTERA ']'",
"tipo : STRUCT '{' lista_de_campos '}'",
};

//#line 210 "../../src/sintactico/sintactico.y"

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
//#line 632 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 32 "../../src/sintactico/sintactico.y"
{ast= new Programa (1,1,(List<Definicion>)val_peek(0));}
break;
case 2:
//#line 35 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); 
																for (Definicion def : (List<Definicion>)val_peek(0)) {
																((List<Definicion>)val_peek(1)).add(def);
																}
																	 }
break;
case 3:
//#line 40 "../../src/sintactico/sintactico.y"
{ yyval =(List<Definicion>)val_peek(0); }
break;
case 4:
//#line 43 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DefFuncion>();((List<DefFuncion>)yyval).add((DefFuncion)val_peek(0)); }
break;
case 5:
//#line 44 "../../src/sintactico/sintactico.y"
{yyval = (List<DeclaracionVariable>)val_peek(0); }
break;
case 6:
//#line 47 "../../src/sintactico/sintactico.y"
{    Funcion f = new Funcion(lexico.getYycolumn(),lexico.getYyline(),(List<DeclaracionVariable>)val_peek(7),(Tipo)val_peek(4));
																										 yyval= new DefFuncion (lexico.getYycolumn(),lexico.getYyline(), (String)val_peek(9),f,(List<DeclaracionVariable>)val_peek(2),(List<Sentencia>)val_peek(1));}
break;
case 7:
//#line 49 "../../src/sintactico/sintactico.y"
{			Void v = new Void(lexico.getYycolumn(),lexico.getYyline());
             																							Funcion f = new Funcion(lexico.getYycolumn(),lexico.getYyline(),(List<DeclaracionVariable>)val_peek(5),v);
																										 yyval= new DefFuncion (lexico.getYycolumn(),lexico.getYyline(), (String)val_peek(7),f,(List<DeclaracionVariable>)val_peek(2),(List<Sentencia>)val_peek(1));}
break;
case 8:
//#line 54 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); ((List)val_peek(0)).add((Sentencia)val_peek(1)); }
break;
case 9:
//#line 55 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); }
break;
case 10:
//#line 59 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); 
																for (DeclaracionVariable def : (List<DeclaracionVariable>)val_peek(0)) {
																((List<DeclaracionVariable>)val_peek(1)).add(def);
																}
																	 }
break;
case 11:
//#line 64 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<DeclaracionVariable>(); }
break;
case 12:
//#line 67 "../../src/sintactico/sintactico.y"
{   yyval = new ArrayList<DeclaracionVariable>();
																	for (String string : (List<String>)val_peek(3)) {
																	DeclaracionVariable def = new DeclaracionVariable (lexico.getYyline(),lexico.getYycolumn(),string,(Tipo)val_peek(1));
																	 ((List<DeclaracionVariable>)yyval).add(def);
																									}
																 }
break;
case 13:
//#line 76 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2);
																for (String iden : (List<String>)val_peek(2)) {
																	if(iden.equals((String)val_peek(0)))
																	{
																	TipoError error = new TipoError(lexico.getYyline(),lexico.getYycolumn(),"Error:Ya existe un identificador con ese nombre");
																	}
																									}  	
																		((List<String>)val_peek(2)).add((String)val_peek(0));	    				   }
break;
case 14:
//#line 84 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<String>();((List<String>)yyval).add((String)val_peek(0)); }
break;
case 15:
//#line 87 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); 
																for (DeclaracionVariable def : (List<DeclaracionVariable>)val_peek(0)) {
																((List<DeclaracionVariable>)val_peek(2)).add(def);
																}
																	 }
break;
case 16:
//#line 92 "../../src/sintactico/sintactico.y"
{ yyval = (List<DeclaracionVariable>)val_peek(0); }
break;
case 17:
//#line 96 "../../src/sintactico/sintactico.y"
{yyval =new ArrayList<DeclaracionVariable>(); ((List<DeclaracionVariable>)yyval).add(new DeclaracionVariable (lexico.getYyline(),lexico.getYycolumn(),(String)val_peek(2),(Tipo)val_peek(0)));}
break;
case 18:
//#line 97 "../../src/sintactico/sintactico.y"
{yyval = new ArrayList<DeclaracionVariable>();}
break;
case 19:
//#line 100 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); 
																			for (Expresion def : (List<Expresion>)val_peek(0)) {
																			((List<Expresion>)val_peek(2)).add(def);
																			}
																		 }
break;
case 20:
//#line 105 "../../src/sintactico/sintactico.y"
{ yyval = (List<Expresion>)val_peek(0); }
break;
case 21:
//#line 108 "../../src/sintactico/sintactico.y"
{yyval = new ArrayList<Expresion>();((List<Expresion>)yyval).add((Expresion)val_peek(0)); }
break;
case 22:
//#line 109 "../../src/sintactico/sintactico.y"
{yyval = new ArrayList<Expresion>(); }
break;
case 23:
//#line 112 "../../src/sintactico/sintactico.y"
{yyval= new Aritmetica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"+",(Expresion)val_peek(0));}
break;
case 24:
//#line 113 "../../src/sintactico/sintactico.y"
{yyval= new Aritmetica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"-",(Expresion)val_peek(0));}
break;
case 25:
//#line 114 "../../src/sintactico/sintactico.y"
{yyval= new Aritmetica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"*",(Expresion)val_peek(0));}
break;
case 26:
//#line 115 "../../src/sintactico/sintactico.y"
{yyval= new Aritmetica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"/",(Expresion)val_peek(0));}
break;
case 27:
//#line 116 "../../src/sintactico/sintactico.y"
{yyval= new Aritmetica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"%",(Expresion)val_peek(0));}
break;
case 28:
//#line 117 "../../src/sintactico/sintactico.y"
{yyval= new Cast (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),(Tipo)val_peek(0));}
break;
case 29:
//#line 118 "../../src/sintactico/sintactico.y"
{yyval= new AccesoACampo (lexico.getYycolumn(),lexico.getYyline(), (String)val_peek(0),(Expresion)val_peek(2));}
break;
case 30:
//#line 119 "../../src/sintactico/sintactico.y"
{yyval= new InvocFuncion (lexico.getYycolumn(),lexico.getYyline(), (List<Expresion>)val_peek(1),(String)val_peek(3));}
break;
case 31:
//#line 120 "../../src/sintactico/sintactico.y"
{ yyval = (Expresion)val_peek(1); }
break;
case 32:
//#line 121 "../../src/sintactico/sintactico.y"
{yyval= new LiteralEntero (lexico.getYyline(),lexico.getYycolumn(), (Integer)val_peek(0));}
break;
case 33:
//#line 122 "../../src/sintactico/sintactico.y"
{yyval= new LiteralReal (lexico.getYyline(),lexico.getYycolumn(), (Double)val_peek(0));}
break;
case 34:
//#line 123 "../../src/sintactico/sintactico.y"
{yyval= new LiteralCaracter (lexico.getYyline(),lexico.getYycolumn(), (String)val_peek(0));}
break;
case 35:
//#line 124 "../../src/sintactico/sintactico.y"
{yyval= new Variable (lexico.getYyline(),lexico.getYycolumn(), (String)val_peek(0));}
break;
case 36:
//#line 125 "../../src/sintactico/sintactico.y"
{yyval= new AccesoArray (lexico.getYyline(),lexico.getYycolumn(), (Expresion)val_peek(3),(Expresion)val_peek(1) );}
break;
case 37:
//#line 126 "../../src/sintactico/sintactico.y"
{yyval=(Expresion)val_peek(0);}
break;
case 38:
//#line 127 "../../src/sintactico/sintactico.y"
{yyval= new MenosUnario (lexico.getYyline(),lexico.getYycolumn(),(Expresion)val_peek(0));}
break;
case 39:
//#line 130 "../../src/sintactico/sintactico.y"
{yyval= new Logica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"&&",(Expresion)val_peek(0));}
break;
case 40:
//#line 131 "../../src/sintactico/sintactico.y"
{yyval= new Logica (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"||",(Expresion)val_peek(0));}
break;
case 41:
//#line 132 "../../src/sintactico/sintactico.y"
{yyval= new LogicaUnaria (lexico.getYycolumn(),lexico.getYyline(),(Expresion)val_peek(0));}
break;
case 42:
//#line 133 "../../src/sintactico/sintactico.y"
{ yyval = (Expresion)val_peek(1); }
break;
case 43:
//#line 136 "../../src/sintactico/sintactico.y"
{yyval= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),">",(Expresion)val_peek(0));}
break;
case 44:
//#line 137 "../../src/sintactico/sintactico.y"
{yyval= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"<",(Expresion)val_peek(0));}
break;
case 45:
//#line 138 "../../src/sintactico/sintactico.y"
{yyval= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),">=",(Expresion)val_peek(0));}
break;
case 46:
//#line 139 "../../src/sintactico/sintactico.y"
{yyval= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"<=",(Expresion)val_peek(0));}
break;
case 47:
//#line 140 "../../src/sintactico/sintactico.y"
{yyval= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"!=",(Expresion)val_peek(0));}
break;
case 48:
//#line 141 "../../src/sintactico/sintactico.y"
{yyval= new Comparacion (lexico.getYycolumn(),lexico.getYyline(), (Expresion)val_peek(2),"==",(Expresion)val_peek(0));}
break;
case 49:
//#line 145 "../../src/sintactico/sintactico.y"
{yyval=(Expresion)val_peek(0);}
break;
case 50:
//#line 146 "../../src/sintactico/sintactico.y"
{yyval=(Expresion)val_peek(0);}
break;
case 51:
//#line 148 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(1); 
																for (Campo def : (List<Campo>)val_peek(0)) {
																for (Campo campos : (List<Campo>)val_peek(1)) {
																if(def.nombre.equals(campos.nombre))
																	{
																	TipoError error = new TipoError(lexico.getYyline(),lexico.getYycolumn(),"Error:Ya existe un campo con el mismo identificador");
																	}
																
																}
																((List<Campo>)val_peek(1)).add(def);
																}
																	 }
break;
case 52:
//#line 160 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 53:
//#line 163 "../../src/sintactico/sintactico.y"
{   yyval = new ArrayList<Campo>();
																	for (String string : (List<String>)val_peek(3)) {
																	Campo def = new Campo (lexico.getYyline(),lexico.getYycolumn(),string,(Tipo)val_peek(1));
																	 ((List<Campo>)yyval).add(def);
																									}
																 }
break;
case 54:
//#line 172 "../../src/sintactico/sintactico.y"
{yyval= new Escritura (lexico.getYyline(),lexico.getYycolumn(), (List)val_peek(1));}
break;
case 55:
//#line 173 "../../src/sintactico/sintactico.y"
{yyval= new Lectura (lexico.getYyline(),lexico.getYycolumn(), (List)val_peek(1));}
break;
case 56:
//#line 174 "../../src/sintactico/sintactico.y"
{yyval= new Asignacion (lexico.getYyline(),lexico.getYycolumn(), (Expresion)val_peek(3),(Expresion)val_peek(1));}
break;
case 57:
//#line 175 "../../src/sintactico/sintactico.y"
{yyval= new Retorno (lexico.getYyline(),lexico.getYycolumn(), (Expresion)val_peek(1));}
break;
case 58:
//#line 176 "../../src/sintactico/sintactico.y"
{yyval= new InvocFuncion (lexico.getYycolumn(),lexico.getYyline(), (List<Expresion>)val_peek(2),(String)val_peek(4));}
break;
case 59:
//#line 177 "../../src/sintactico/sintactico.y"
{yyval = (While)val_peek(0) ;}
break;
case 60:
//#line 178 "../../src/sintactico/sintactico.y"
{yyval = (If)val_peek(0) ;}
break;
case 61:
//#line 181 "../../src/sintactico/sintactico.y"
{ yyval = val_peek(2); ((List)val_peek(2)).add((Expresion)val_peek(0)); }
break;
case 62:
//#line 182 "../../src/sintactico/sintactico.y"
{ yyval = new ArrayList<Expresion>();((List)yyval).add(val_peek(0)); }
break;
case 63:
//#line 185 "../../src/sintactico/sintactico.y"
{ yyval = new While(lexico.getYyline(),lexico.getYycolumn(),(List<Sentencia>)val_peek(1),(Expresion)val_peek(3)) ;}
break;
case 64:
//#line 186 "../../src/sintactico/sintactico.y"
{ List<Sentencia> f = new ArrayList<Sentencia>();
	    															f.add((Sentencia)val_peek(0));
	    														yyval = new While(lexico.getYyline(),lexico.getYycolumn(),f,(Expresion)val_peek(1)) ;}
break;
case 65:
//#line 191 "../../src/sintactico/sintactico.y"
{ List<Sentencia> f = new ArrayList<Sentencia>();yyval = new If(lexico.getYyline(),lexico.getYycolumn(),(List<Sentencia>)val_peek(0),f,(Expresion)val_peek(1)) ;}
break;
case 66:
//#line 192 "../../src/sintactico/sintactico.y"
{ yyval = new If(lexico.getYyline(),lexico.getYycolumn(),(List<Sentencia>)val_peek(2),(List<Sentencia>)val_peek(0),(Expresion)val_peek(3)) ;}
break;
case 67:
//#line 196 "../../src/sintactico/sintactico.y"
{ yyval = (List<Sentencia>)val_peek(1); }
break;
case 68:
//#line 197 "../../src/sintactico/sintactico.y"
{List<Sentencia> f = new ArrayList<Sentencia>() ; f.add((Sentencia)val_peek(0));yyval = f; }
break;
case 69:
//#line 200 "../../src/sintactico/sintactico.y"
{ yyval = (List<Sentencia>)val_peek(1); }
break;
case 70:
//#line 201 "../../src/sintactico/sintactico.y"
{List<Sentencia> f = new ArrayList<Sentencia>() ; f.add((Sentencia)val_peek(0));yyval = f; }
break;
case 71:
//#line 204 "../../src/sintactico/sintactico.y"
{yyval= new Entero (lexico.getYyline(),lexico.getYycolumn());}
break;
case 72:
//#line 205 "../../src/sintactico/sintactico.y"
{yyval= new Real (lexico.getYyline(),lexico.getYycolumn());}
break;
case 73:
//#line 206 "../../src/sintactico/sintactico.y"
{yyval= new Caracter (lexico.getYyline(),lexico.getYycolumn());}
break;
case 74:
//#line 207 "../../src/sintactico/sintactico.y"
{yyval= new Array (lexico.getYyline(),lexico.getYycolumn(),(int)val_peek(1),(Tipo)val_peek(3));}
break;
case 75:
//#line 208 "../../src/sintactico/sintactico.y"
{yyval= new Struct (lexico.getYycolumn(),lexico.getYyline(), (List<Campo>)val_peek(1));}
break;
//#line 1130 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
