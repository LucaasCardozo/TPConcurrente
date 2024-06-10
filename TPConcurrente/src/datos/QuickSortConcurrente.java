package datos;

import java.util.concurrent.*; // Importamos clases relacionadas con la concurrencia

public class QuickSortConcurrente {

    private static final int THRESHOLD = 1000; // Umbral para cambiar entre la ejecución secuencial y concurrente
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors(); // Número máximo de hilos disponibles en el sistema

    // Clase interna que representa una tarea de ordenación
    public static class SortTask extends RecursiveAction {
        private final int[] array; // Array a ordenar
        private final int left;    // Índice izquierdo del sub-array
        private final int right;   // Índice derecho del sub-array

        // Constructor para inicializar los atributos de la tarea
        public SortTask(int[] array, int left, int right) {
            this.array = array;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            
            if (right - left < THRESHOLD) { // Si el tamaño del sub-array es menor que el umbral
                // Ejecutamos el algoritmo de QuickSort secuencial
                QuickSortSecuencial.quickSort(array, left, right);
                return;
            }

            // Obtenemos el índice del pivote después de particionar el array
            int pivot = partition(array, left, right);

            // Creamos tareas para ordenar las sub-secuencias izquierda y derecha del pivote
            SortTask leftTask = new SortTask(array, left, pivot - 1);
            SortTask rightTask = new SortTask(array, pivot + 1, right);

            // Verificamos si hay suficientes hilos disponibles para dividir las tareas
            if (getSurplusQueuedTaskCount() < MAX_THREADS) {
                // Dividimos las tareas y las ejecutamos de forma concurrente
                leftTask.fork();
                rightTask.fork();
            } else {
                // Ejecutamos las tareas de forma secuencial
                leftTask.invoke();
                rightTask.invoke();
            }

            // Esperamos a que las tareas finalicen
            leftTask.join();
            rightTask.join();
        }
       
        // Método para particionar el array
        private int partition(int[] array, int left, int right) {
            int pivot = array[left + (right - left) / 2]; // Seleccionamos el elemento del medio como pivote
            int i = left - 1;
            int j = right + 1;

            while (true) {
                do {
                    i++;
                } while (array[i] < pivot);

                do {
                    j--;
                } while (array[j] > pivot);

                if (i >= j) {
                    return j;
                }

                // Intercambiamos los elementos
                swap(array, i, j);
            }
        }

        // Método para intercambiar dos elementos en el array
        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
