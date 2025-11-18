package drawingTool;

import util.Student;
import util.Node;
import util.QuickSort;
import util.RandomStudentsGenerator;
import java.util.ArrayList;

public class SortTest {
	public void start() {
        // --- Step (d): Store 10 meaningful data sets ---
        ArrayList<Node> studentList = new ArrayList<>();
        studentList.add(new Student("student_A", 8));
        studentList.add(new Student("student_B", 3));
        studentList.add(new Student("student_C", 12));
        studentList.add(new Student("student_D", 1));
        studentList.add(new Student("student_E", 8));
        studentList.add(new Student("student_F", 5));
        studentList.add(new Student("student_G", 9));
        studentList.add(new Student("student_H", 15));
        studentList.add(new Student("student_I", 0));
        studentList.add(new Student("student_J", 7));

        // --- Print before ---
        System.out.println("--- BEFORE SORTING ---");
        for (Node s : studentList) {
        	Student student=(Student) s;
            System.out.println(student.getName()+":  "+s.getKey());
        }
        //Sorting test data
        System.out.println("\nAfter Sorting using QuickSort:\n");
        QuickSort sorter=new QuickSort(studentList);
        sorter.start(1);
        System.out.println("Comaparisons: "+sorter.getComparisons());
        System.out.println("Swaps: "+sorter.getSwaps());

        // --- Print after ---
        for (Node s : studentList) {
        	Student student=(Student) s;
        	System.out.println(student.getName()+":  "+s.getKey());
        }
        for(int j=1;j<5;j++) {
        	long totalComparisons=0;
            long totalSwaps = 0;
            int numberOfRuns = 1000;
            int numberOfStudents=1000;
            int method=j;
            for (int i=1;i<=numberOfRuns;i++) {
            ArrayList<Node> studentData=RandomStudentsGenerator.RandomStudents(numberOfStudents);
           // 2. Create a NEW sorter instance for this run
                QuickSort newSorter = new QuickSort(studentData);
            // 3. Sort it
                newSorter.start(method);
            // 4. Add this run's stats to the total
                totalComparisons += newSorter.getComparisons();
                totalSwaps += newSorter.getSwaps();
            }
         // --- Part 4: Calculate and Print Averages ---
            double avgComparisons = (double) totalComparisons / numberOfRuns;
            double avgSwaps = (double) totalSwaps / numberOfRuns;
            System.out.println("Finished " + numberOfRuns + " runs using median method "+method);
            System.out.println("Average Comparisons: " + avgComparisons);
            System.out.println("Average Swaps:       " + avgSwaps+"\n\n");
        	}
    	}
}

