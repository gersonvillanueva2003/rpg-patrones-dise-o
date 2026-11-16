#!/bin/bash
# Compila y genera EJECUCION.txt con 4 corridas. Requiere JDK 17+ (javac).
set -e
mkdir -p out
javac -encoding UTF-8 -d out src/*.java
run() { # titulo, entrada simulada
  echo "################################################################"
  echo "# CORRIDA $1"
  echo "################################################################"
  printf "$2" | java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out Main
  echo; echo
}
{
run "1: Mago + Espada, Veneno, Bendición vs Goblin (modo automático)"            'Aria\n2\n1,3,5\n1\n2\n'
run "2: Mago + Bendición, Amuleto vs Orco (modo MANUAL: ataca/defiende/poción)"   'Merlin\n2\n5,4\n2\n1\n1\n2\n1\n1\n3\n1\n1\n1\n1\n'
run "3: Pícaro + Espada, Veneno, Armadura vs Orco (automático)"                       'Shade\n4\n1,3,2\n2\n2\n'
run "4: Arquero + Bendición, Veneno, Amuleto vs Dragón (automático, derrota)"     'Lyra\n3\n5,3,4\n3\n2\n'
} > EJECUCION.txt
echo "EJECUCION.txt generado"
