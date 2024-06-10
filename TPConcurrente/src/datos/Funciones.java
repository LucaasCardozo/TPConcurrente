package datos;

import java.util.Random; // Importamos la clase Random para generar números aleatorios

public class Funciones {

    // Método para generar un array aleatorio de tamaño 'n' con valores en el rango [min, max]
    public static int[] generarArrayAleatorio(int n, int min, int max) {
    	
        int[] arr = new int[n];	// Declaración del array
        
        // Generación de números aleatorios
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            // Generamos un número aleatorio en el rango [min, max] y lo asignamos al array
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        
        return arr; // Devolvemos el array generado
    }
    
    // Método para mostrar los elementos de un array
    public static void mostrarArray(int[] arr) {
    	
        // Imprimimos un encabezado
        System.out.println("array ordenado:");
        
        // Recorremos el array y mostramos cada elemento
        for (int num : arr) {
            System.out.print(num + " - "); // Imprimimos el elemento
        }
        System.out.println("\n"); // Imprimimos un salto de línea al final
    }
}
