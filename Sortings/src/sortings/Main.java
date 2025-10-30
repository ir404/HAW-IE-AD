package sortings;

public class Main {
	public static void main(String[] args) {
		System.out.println("SORTING!!");
		int[] numbers = {3, 5, 7, 1, 6, 2, 4, 9, 8};
		int[] numbers2 = {19, 15, 17, 11, 16, 12, 18, 14, 10};
		display(numbers);
		insertionSort(numbers);
		System.out.println("After sorting:");
		display(numbers);
		
		System.out.println("\n\n");
		display(numbers2);
		selectionSort(numbers2);
		System.out.println("After sorting:");
		display(numbers2);
		
	}
	
	// Time complexity: O(n^2)
	private static void selectionSort(int[] numbers) {
		int smallestValue;
        int smallestAt;
        for (int i = 0; i < numbers.length - 1; i++) {
            smallestValue = numbers[i];
            smallestAt = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < smallestValue) {
                    smallestValue = numbers[j];
                    smallestAt = j;
                }
            }
            swap(numbers, i, smallestAt);
        }
	}
	
	private static void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

    // Time complexity: O(n^2)
	private static void insertionSort(int[] numbers) {
		int key;
        int prev;
        for (int i = 1; i < numbers.length - 1; i++) {
            key = numbers[i];
            prev = i - 1;
            while (prev >= 0 && numbers[prev] > key) {
                numbers[prev + 1] = numbers[prev];
                prev--;
            }
            numbers[prev + 1] = key;
        }
	}

    private static void mergeSort(int[] numbers) {
        display(numbers);
    }
	
	private static void display(int[] array) {
        for (int element : array)
            System.out.print(element + ", ");
        System.out.println();
	}

}
