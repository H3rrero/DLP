
cls
cd %~dp0
cd src
java -cp ..\tools\jflex\JFlex.jar JFlex.Main -d lexico lexico\lexico.flex
pause
cd..
cd tools\byaccj
yacc.exe -J -v -Jpackage=sintactico -Jsemantic=Object "../../src/sintactico/sintactico.y"
move Parser.java ../../src/sintactico
move y.output ../../src/sintactico
pause