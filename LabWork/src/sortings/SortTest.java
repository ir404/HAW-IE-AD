package sortings;

import data.Student;
import data.Node;
import util.RandomStudentsGenerator;
import java.util.ArrayList;

public class SortTest {
    private ArrayList<Node> studentList;

    public SortTest() {
        studentList = new ArrayList<>();
        studentList.add(new Student("Judy", 8));
        studentList.add(new Student("Peter", 3));
        studentList.add(new Student("Max", 12));
        studentList.add(new Student("Anna", 1));
        studentList.add(new Student("Xavier", 8));
        studentList.add(new Student("Ben", 5));
        studentList.add(new Student("Cassie", 9));
        studentList.add(new Student("Leo", 15));
        studentList.add(new Student("George", 0));
        studentList.add(new Student("Stephanie", 7));
    }

	public void start() {
        System.out.println("Before sorting:");
        for (Node s : studentList) {
        	Student student=(Student) s;
            System.out.println(" " + student.getName() + ":  " + s.getKey());
        }

        AbstractSort sorter = new QuickSort(studentList, 1);
        sorter.start();
        System.out.println("\nAfter sorting using QuickSort:");
        System.out.println(" Comparisons : " + sorter.getComparisons());
        System.out.println(" Swaps       : "+ sorter.getSwaps());

        // --- Print after ---
        for (Node s : studentList) {
        	Student student=(Student) s;
        	System.out.println(" " + student.getName()+":  "+s.getKey());
        }
    }

    public void generateRandomDataAndSort(int numberOfElements, int numberOfRuns) {
        System.out.println("\nSorting " + numberOfElements + " randomly generated elements with " + numberOfRuns + " runs");
        for(int medianMethod = 1; medianMethod < 5; medianMethod++) {
            long totalComparisons = 0;
            long totalSwaps = 0;

            for (int i = 0; i < numberOfRuns; i++) {
                ArrayList<Node> studentData = RandomStudentsGenerator.randomStudents(numberOfElements);
                AbstractSort sorter = new QuickSort(studentData, medianMethod);
                sorter.start();
                totalComparisons += sorter.getComparisons();
                totalSwaps += sorter.getSwaps();
            }

            double avgComparisons = (double) totalComparisons / numberOfRuns;
            double avgSwaps = (double) totalSwaps / numberOfRuns;
            System.out.println(" Median calculation method : " + medianMethod);
            System.out.println(" Average Comparisons       : " + avgComparisons);
            System.out.println(" Average Swaps             : " + avgSwaps + "\n");
        }
    }
}

