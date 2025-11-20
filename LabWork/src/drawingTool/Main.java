package drawingTool;

import sortings.SortTest;

public class Main {
    public static void main(String[] args) {
        SortTest sortTest = new SortTest();
        sortTest.start();
        sortTest.generateRandomDataAndSort(1000, 1000);

        System.out.println("\n\nOPENING THE APP-----------------------");
        AppFrame appFrame = new AppFrame("Plotter - Lab 2");
        appFrame.setVisible(true);
    }
}
