package datos;

public class QuickSortSecuencial {
	
	public static void quickSort(int[] arr, int low, int high) {
		// Verificamos si el índice bajo es menor que el índice alto para asegurarnos de que el array tenga al menos dos elementos
		if (low < high) {
			// Obtenemos el índice del pivote después de particionar el array
			int pi = partition(arr, low, high);
			// Llamamos recursivamente al método quickSort para ordenar la sub-secuencia antes del pivote
			quickSort(arr, low, pi - 1);
			// Llamamos recursivamente al método quickSort para ordenar la sub-secuencia después del pivote
			quickSort(arr, pi + 1, high);
		}
	}

	private static int partition(int[] arr, int low, int high) {
		// Seleccionamos el elemento más alto como pivote
		int pivot = arr[high];
		// Inicializamos el índice del elemento más pequeño
		int i = low - 1;
		// Recorremos el array desde el índice bajo hasta el índice alto
		for (int j = low; j < high; j++) {
			// Si el elemento actual es menor que el pivote, lo intercambiamos con el elemento en el índice más pequeño
			if (arr[j] < pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		// Intercambiamos el pivote con el elemento en el índice más pequeño más uno
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		// Retornamos el índice del pivote después de la partición
		return i + 1;
	}

}
