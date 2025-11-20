package data;

public class Student implements Node {
    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public int getKey() {
        return grade;
    }
}