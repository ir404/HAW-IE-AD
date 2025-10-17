package sortings;

public class Main {
	public static void main(String[] args) {
		System.out.println("SORTING!!");
		int[] numbers = {9, 5, 7, 1, 6, 2, 8, 4, 2};
		display(numbers);
		insertionSort(numbers);
		System.out.println("After sorting:");
		display(numbers);
		
	}
	
	// Time complexity: O(n^2)
	private static void selectionSort(int[] numbers) {
		int minValue;
		int minIndex;
		for (int i = 0; i < numbers.length - 1; i++) {
			minIndex = i;
			minValue = numbers[i];
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < minValue) {
					minIndex = j;
					minValue = numbers[j];
				}
			}
			swap(numbers, i, minIndex);
		}
	}
	
	private static void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	private static void insertionSort(int[] numbers) {
		for (int i = 1; i <= numbers.length - 1; i++) {
			int j = i - 1;
			int temp = numbers[i];
			
			while (j >= 0 && temp < numbers[j]) {
				numbers[j + 1] = numbers[j];
				j--;
			}
			
			numbers[j + 1] = temp;
		}
	}
	
	private static void display(int[] array) {
		for (int i = 0; i < array.length; i++) 
			System.out.println("array[" + i + "] = " + array[i]);
	}

}
