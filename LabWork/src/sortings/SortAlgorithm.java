package sortings;

public enum SortAlgorithm {
    QUICK_SORT("Quick Sort"),
    SELECTION_SORT("Selection Sort");

    private String name;

    SortAlgorithm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SortAlgorithm fromName(String name) {
        SortAlgorithm value = null;
        for (SortAlgorithm algorithm : SortAlgorithm.values()) {
            if (algorithm.name.equals(name)) {
                value = algorithm;
            }
        }
        return value;
    }
}
