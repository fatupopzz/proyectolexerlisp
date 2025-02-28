import java.util.ArrayList;
import java.util.Scanner;

/* 
 * AnalizadorLisp.java
 * Este programa revisa expresiones LISP para ver si están bien escritas
 * y separa todos sus elementos.
 * 
 * Creado por: Fatima Navarro 
 * 
 * Referencias:
 * - Me basé en cosas que aprendí en clase sobre tokens y análisis léxico
 * - Claude 3.7 Sonnet (IA de Anthropic) me ayudó con este código.
 * 
 */
public class AnalizadorLisp {
    
    public static void main(String[] args) {
        // Para leer lo que escribe el usuario
        Scanner lector = new Scanner(System.in);
        
        // Leemos la expresión LISP
        System.out.println("Escribe tu expresión LISP (puedes usar varias líneas):");
        System.out.println("Cuando termines, escribe 'fin' en una línea nueva");
        
        StringBuilder expresion = new StringBuilder();
        String linea;
        
        // Seguimos leyendo hasta que escriban "fin"
        while (!(linea = lector.nextLine()).equals("fin")) {
            expresion.append(linea);
        }
        
        lector.close();
        
        String textoLisp = expresion.toString();
        
        // Checamos si los paréntesis están bien
        boolean estaCorrecta = revisarParentesis(textoLisp);
        
        // Sacamos todos los elementos de la expresión
        ArrayList<String> elementos = sacarElementos(textoLisp);
        
        // Mostramos si está bien y cuáles son los elementos
        mostrarResultado(estaCorrecta, elementos);
    }
    
    /**
     * Revisa si hay la misma cantidad de paréntesis que abren y cierran
     */
    public static boolean revisarParentesis(String texto) {
        int cuenta = 0;
        
        // Vemos cada caracter
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            
            if (c == '(') {
                cuenta++;
            }
            else if (c == ')') {
                cuenta--;
                
                // Si hay más que cierran que abren, está mal
                if (cuenta < 0) {
                    return false;
                }
            }
        }
        
        // Si al final la cuenta es 0, todo bien
        return cuenta == 0;
    }
    
    /**
     * Saca todos los elementos (tokens) de la expresión
     */
    public static ArrayList<String> sacarElementos(String texto) {
        ArrayList<String> elementos = new ArrayList<>();
        StringBuilder elementoActual = new StringBuilder();
        
        // Recorremos cada letra o símbolo
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            
            // Ignoramos espacios y saltos de línea
            if (Character.isWhitespace(c)) {
                if (elementoActual.length() > 0) {
                    elementos.add(elementoActual.toString());
                    elementoActual = new StringBuilder();
                }
                continue;
            }
            
            // Los paréntesis son elementos por sí solos
            if (c == '(' || c == ')') {
                if (elementoActual.length() > 0) {
                    elementos.add(elementoActual.toString());
                    elementoActual = new StringBuilder();
                }
                
                elementos.add(String.valueOf(c));
            } 
            // Cualquier otra cosa forma parte de un elemento
            else {
                elementoActual.append(c);
                
                // Vemos si el siguiente caracter termina este elemento
                if (i + 1 < texto.length()) {
                    char siguiente = texto.charAt(i + 1);
                    if (siguiente == '(' || siguiente == ')' || 
                        Character.isWhitespace(siguiente)) {
                        elementos.add(elementoActual.toString());
                        elementoActual = new StringBuilder();
                    }
                }
            }
        }
        
        // Por si queda algo pendiente
        if (elementoActual.length() > 0) {
            elementos.add(elementoActual.toString());
        }
        
        return elementos;
    }
    
    /**
     * Muestra si la expresión está bien y cuáles son sus elementos
     */
    public static void mostrarResultado(boolean correcta, ArrayList<String> elementos) {
        // Decimos si está bien o mal
        if (correcta) {
            System.out.println("a) YIPPIEEE! La expresión está correcta! Tiene la misma cantidad de paréntesis que abren y cierran.");
        } else {
            System.out.println("a) womp womp... La expresión está incorrecta. No tiene la misma cantidad de paréntesis que abren y cierran.");
        }
        
        // Mostramos todos los elementos encontrados
        System.out.print("b) Los elementos que encontré son: ");
        
        if (!elementos.isEmpty()) {
            System.out.print(elementos.get(0));
            for (int i = 1; i < elementos.size(); i++) {
                System.out.print("," + elementos.get(i));
            }
        }
        System.out.println();
    }
}