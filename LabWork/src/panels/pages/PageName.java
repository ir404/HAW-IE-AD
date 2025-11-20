package panels.pages;

public enum PageName {
    HOME_PAGE("Home Page"),
    TRIG_PAGE("Trig Page"),
    GRADES_PAGE("Grades Page"),
    STUDENT_GRADES_PAGE("Student Grades Page");

    private String name;

    PageName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
