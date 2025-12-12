package tree;

import data.Student;

public class StudentItem extends Node {
    private Student studentData;

    public StudentItem(Student student) {
        super(student.getKey());
        this.studentData = student;
    }

    @Override
    public String toString() {
        return studentData.getName() + " (" + getKey() + ")";
    }
}
