package test;

import java.util.Arrays; // Importamos la clase Arrays para utilizar su método copyOf
import java.util.concurrent.ForkJoinPool; // Importamos ForkJoinPool para ejecutar tareas concurrentes

import datos.QuickSortSecuencial; // Importamos la clase QuickSortSecuencial para usar su método quickSort
import datos.Funciones; // Importamos la clase Funciones para utilizar su método generarArrayAleatorio y mostrarArray
import datos.QuickSortConcurrente.SortTask; // Importamos la clase SortTask de QuickSortConcurrente para trabajar con tareas de ordenación

public class TestQuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Creamos variables para calcular el tiempo que tarda cada algoritmo en ordenar el array
		double tiempoInicial;
		double tiempoFinal;
		
		// Creamos y cargamos el arreglo 'array' con numeros aleatorios
		int[] array = Funciones.generarArrayAleatorio(1000000, 1, 10000);
		
		// Copiamos el arreglo 'array' en 'array2', manteniendo la misma longitud
		int[] array2 = Arrays.copyOf(array, array.length);
		
		
		// QUICKSORT SECUENCIAL

		tiempoInicial = System.nanoTime();	// Comenzamos a calcular el tiempo
		QuickSortSecuencial.quickSort(array, 0, array.length - 1);	// Llamamos al método 'quickSort' para ordenar el array
		tiempoFinal = System.nanoTime() - tiempoInicial;	// Terminamos de calcular el tiempo
		
		// Mostramos cuanto tiempo tardó el algoritmo secuencial
		System.out.println("- SECUENCIAL: Demore "+ tiempoFinal/1000+ " microSegundos\n");
		
		
		// QUICKSORT CONCURRENTE
		
		tiempoInicial = System.nanoTime();	// Comenzamos a calcular el tiempo
		// Creamos una instancia de ForkJoinPool para poder ejecutar tareas concurrentes
		ForkJoinPool pool = new ForkJoinPool();
		// Creamos una instancia de SortTask para poder dividir una tarea en sub-tareas
		SortTask sortTask = new SortTask(array2, 0, array2.length - 1);
		pool.invoke(sortTask);	 // Invocamos la tarea para su ejecución concurrente
		tiempoFinal = System.nanoTime() - tiempoInicial;	// Terminamos de calcular el tiempo
		
		// Mostramos cuanto tiempo tardó el algoritmo concurrente
		System.out.println("- CONCURRENTE: Demore "+ tiempoFinal/1000+ " microSegundos\n");

	}

}
