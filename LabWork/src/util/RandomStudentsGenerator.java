package util;

import data.Node;
import data.Student;

import java.util.ArrayList;
import java.util.Random;

public class RandomStudentsGenerator {
	public static ArrayList<Node> randomStudents(int numStudents){
        ArrayList<Node> studentData = new ArrayList<>();
        Random rand = new Random();
        int minGrade = 1;
        int maxGrade = 15;
        for (int i = 0; i < numStudents; i++) {
            String studentName = "student_" + i;
            int grade = rand.nextInt((maxGrade - minGrade) + 1) + minGrade;
            studentData.add(new Student(studentName, grade));
        }
        return studentData;
	}
}
