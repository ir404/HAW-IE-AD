package drawingTool;

public class Main {
    public static void main(String[] args) {
        AppFrame appFrame = new AppFrame("Plotter - Lab 2");
        appFrame.setVisible(true);
        SortTest sort=new SortTest();
        sort.start();
    }
}
