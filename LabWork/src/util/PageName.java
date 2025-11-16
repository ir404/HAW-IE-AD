package util;

public enum PageName {
    HOME_PAGE("Home Page"),
    TRIG_PAGE("Trig Page"),
    GRADES_PAGE("Grades Page"),
    STUDENT_GRADES_PAGE("Student Grades Page");

    private String value;

    PageName(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }
}
