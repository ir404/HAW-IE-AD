package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStudentsGenerator {
	public static ArrayList<Node> RandomStudents(int numStudents){
	ArrayList<Node> studentData=new ArrayList<>();
	Random rand = new Random();
    int maxGrade = 15; // Max grade (so random is 0 to 15) 
    for (int i = 0; i < numStudents; i++) {
        String studentName = "student_" + i;
        int grade = rand.nextInt(maxGrade + 1); // Generates grade 0-15
        studentData.add(new Student(studentName, grade));
    	}
    return studentData;
	}
}
