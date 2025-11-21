package panels.pages.studentGradesPage;

import data.Student;
import drawingTool.AppFrame;
import graphs.Point;
import graphs.ScatterPlot;
import panels.GraphPanel;
import data.Node;
import sortings.AbstractSort;
import sortings.QuickSort;
import sortings.SelectionSort;
import sortings.SortAlgorithm;
import util.RandomStudentsGenerator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentGradesPage extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
	private GraphPanel graphPanel;
    private ScatterPlot scatterPlot;
    private ArrayList<Point> points;
    private StudentGradesPageControlPanel controlPanel;
    private ArrayList<Node> studentData;
    private ArrayList<Node> tempOldData;
    private AbstractSort sorter;

    public StudentGradesPage(AppFrame appFrame, int width, int height) {
        points = new ArrayList<>();

        graphPanel = new GraphPanel((int) (width * 0.80), height, -500, 250);
        controlPanel = new StudentGradesPageControlPanel(appFrame);
        controlPanel.setPreferredSize(new Dimension((int) (width * 0.20), height));
        controlPanel.getGenerateBtn().addActionListener(this);
        controlPanel.getSortBtn().addActionListener(this);
        controlPanel.getRevertToOriginalBtn().addActionListener(this);
        controlPanel.getResetViewBtn().addActionListener(this);

        super.setLayout(new BorderLayout());
        super.add(graphPanel, BorderLayout.CENTER);
        super.add(controlPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == controlPanel.getResetViewBtn()) {
            graphPanel.getScene().resetView();
            graphPanel.repaint();
        } else if (e.getSource() == controlPanel.getGenerateBtn()) {
            generateStudentData(controlPanel.getStudentCount());
            updatePlot();
        } else if (e.getSource() == controlPanel.getSortBtn()) {
            if (studentData != null) {
                SortAlgorithm selectedSortAlgorithm = controlPanel.getSortingAlgorithm();
                if (selectedSortAlgorithm == SortAlgorithm.QUICK_SORT) {
                    sorter = new QuickSort(studentData, 1);
                } else {
                    sorter = new SelectionSort(studentData);
                }

                // add a swap listener to update the plot after a swap is made during the sort algorithm
                sorter.setSwapListener(() -> {
                	SwingUtilities.invokeLater(() -> {
                        controlPanel.setComparisonCount((int) sorter.getComparisons());
                        controlPanel.setSwapCount((int) sorter.getSwaps());
                        updatePlot();
                    });
                });

                // run the sorting algorithm on a separate thread from the UI-thread
                Thread sortingThread = new Thread(() -> {
                    // disable panel buttons while sorting
                    controlPanel.getGenerateBtn().setEnabled(false);
                    controlPanel.getSortBtn().setEnabled(false);
                    controlPanel.getRevertToOriginalBtn().setEnabled(false);
                    sorter.start();

                    // after sorted re-enable buttons
                    SwingUtilities.invokeLater(() -> {
                        controlPanel.getGenerateBtn().setEnabled(true);
                        controlPanel.getSortBtn().setEnabled(true);
                        controlPanel.getRevertToOriginalBtn().setEnabled(true);
                    });
                });
                sortingThread.start();
            }
        } else if (e.getSource() == controlPanel.getRevertToOriginalBtn()) {
            copyInto(tempOldData, studentData);
            updatePlot();
            controlPanel.setComparisonCount(0);
            controlPanel.setSwapCount(0);
        }
    }

    private void generateStudentData(int length) {
        studentData = RandomStudentsGenerator.randomStudents(length);
        tempOldData = new ArrayList<>(length);
        copyInto(studentData, tempOldData);
        controlPanel.setNodeCount(studentData.size());
    }

    private void copyInto(List<Node> sourceList, List<Node> targetList) {
        targetList.clear();
        for (Node node: sourceList) {
            Student sourceStudent = (Student) node;
            Student copy = new Student(sourceStudent.getName(), sourceStudent.getGrade());
            targetList.add(copy);
        }
    }

    private void mapStudentDataToGraphPoints() {
        points.clear();
        if (studentData != null) {
            for (int i = 0; i < studentData.size(); i++) {
                int x = i + 1;
                int y = studentData.get(i).getKey();
                points.add(new Point(x, y));
            }
        }
    }

    private void updatePlot() {
        mapStudentDataToGraphPoints();
        scatterPlot = new ScatterPlot(Color.RED, points, "Student i", "Grade [1 to 15]");
        graphPanel.getScene().clearGraph();
        graphPanel.getScene().addGraph(scatterPlot);
        graphPanel.repaint();
    }
}
