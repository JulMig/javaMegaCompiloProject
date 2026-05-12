ant generate
ant compile

#Execution des tests ok

java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testOk/exempleExpression.txt

java -jar runtam.jar exemple/testOk/exempleExpression.tam
#resultat : 32-27
#///////////////////////////////////////////////////////////

java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testOk/exempleInstruction.txt

java -jar runtam.jar exemple/testOk/exempleInstruction.tam
#resultat : 12345018102
#///////////////////////////////////////////////////////////

java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testOk/exemplePaire.txt

java -jar runtam.jar exemple/testOk/exemplePaire.tam
#resultat : 343406
#///////////////////////////////////////////////////////////

java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testOk/exempleTableau.txt

java -jar runtam.jar exemple/testOk/exempleTableau.tam
#resultat : 1234
#///////////////////////////////////////////////////////////

java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testOk/exemplePointeur.txt

java -jar runtam.jar exemple/testOk/exemplePointeur.tam
#resultat : 568932
#///////////////////////////////////////////////////////////

java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testOk/pgcd.txt

java -jar runtam.jar exemple/testOk/pgcd.tam
#resultat : 1

#///////////////////////////////////////////////////////////

java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testOk/pointeur_sur_tab.txt

java -jar runtam.jar exemple/testOk/pointeur_sur_tab.tam
#resultat : 3

#///////////////////////////////////////////////////////////
#Execution des tests ko

#Expression
java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testKo/exemple1.txt
#resultat : erreur typage

#Expression
java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testKo/exemple2.txt
#resultat : erreur i déjà défini

#Paire
java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testKo/exemple3.txt
#resultat : erreur typage

#Paire
java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testKo/exemple4.txt
#resultat : erreur typage

#If
java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testKo/exemple5.txt
#resultat : erreur typage

#While
java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testKo/exemple6.txt
#resultat : erreur typage

#Typedef
java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testKo/exemple7.txt
#resultat : erreur Entier déjà défini

#Tableau
java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testKo/exemple8.txt
#resultat : erreur typage

#Pointeur
java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testKo/exemple9.txt
#resultat : erreur typage

#PGCD
java -cp "bin/cls:tools/commons-lang3-3.7.jar:tools/commons-text-1.2.jar:tools/antlr-4.13.1-complete.jar:$CLASSPATH" fr.n7.stl.minic.Driver exemple/testKo/pgcd.txt
#resultat : erreur collect : print des variables qui ne sont pas définit dans le même bloc

#///////////////////////////////////////////////////////////

