package util;

public enum PageName {
    HOME_PAGE("Home Page"),
    TRIG_PAGE("Trig Page"),
    COMPANY_STATS_PAGE("Company Stats Page");

    private String value;

    PageName(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }
}
