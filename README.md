# Analizador Léxico para LISP

## ¿Qué hace este programa?
Este programa analiza expresiones LISP y hace dos cosas:
1. Comprueba si los paréntesis están balanceados (igual número de paréntesis que abren y cierran)
2. Identifica todos los elementos (tokens) de la expresión

## Video del Intérprete
https://youtu.be/wP1BSGfdbrQ

## Cómo funciona
Funciona de esta manera:

1. **Para verificar los paréntesis:**
   - Cada vez que encuentra un "(" suma 1 a un contador
   - Cada vez que encuentra un ")" resta 1 del contador
   - Si al final el contador es 0, la expresión está correcta
   - Si en algún momento hay más ")" que "(", o si al final sobran paréntesis, está mal

2. **Para identificar los tokens:**
   - Considera cada paréntesis como un token independiente
   - Ignora espacios en blanco
   - Agrupa letras y números hasta encontrar un espacio o paréntesis

## Cómo usarlo
1. Ejecuta el programa: `java AnalizadorLisp`
2. Escribe tu expresión LISP
3. Cuando termines, escribe "fin" en una línea nueva
4. El programa te dirá si la expresión es correcta y te mostrará todos los tokens

## Ejemplo sencillo
Si escribes: `(+ 2 3)`

Te dirá:
- YIPPIEEE! La expresión está correcta!
- Los elementos son: (,+,2,3,)

## Se utiliza
- Un enfoque simple
- Separamos las diferentes tareas en métodos independientes
- Se incluyen comentarios para explicar lo que hace cada parte

