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
    private StudentGradesPageControlPanel studentGradesPageControlPanel;
    private ArrayList<Node> studentData;
    private ArrayList<Node> tempOldData;
    private AbstractSort sorter;

    public StudentGradesPage(AppFrame appFrame, int width, int height) {
        points = new ArrayList<>();

        graphPanel = new GraphPanel((int) (width * 0.80), height, -450, 250);
        studentGradesPageControlPanel = new StudentGradesPageControlPanel(appFrame);
        studentGradesPageControlPanel.setPreferredSize(new Dimension((int) (width * 0.20), height));
        studentGradesPageControlPanel.getGenerateBtn().addActionListener(this);
        studentGradesPageControlPanel.getSortBtn().addActionListener(this);
        studentGradesPageControlPanel.getRevertToOriginalBtn().addActionListener(this);
        studentGradesPageControlPanel.getResetViewBtn().addActionListener(this);

        super.setLayout(new BorderLayout());
        super.add(graphPanel, BorderLayout.CENTER);
        super.add(studentGradesPageControlPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentGradesPageControlPanel.getResetViewBtn()) {
            graphPanel.getScene().resetView();
            graphPanel.repaint();
        } else if (e.getSource() == studentGradesPageControlPanel.getGenerateBtn()) {
            generateStudentData(studentGradesPageControlPanel.getStudentCount());
            updatePlot();
        } else if (e.getSource() == studentGradesPageControlPanel.getSortBtn()) {
            if (studentData != null) {
                SortAlgorithm selectedSortAlgorithm = studentGradesPageControlPanel.getSortingAlgorithm();
                if (selectedSortAlgorithm == SortAlgorithm.QUICK_SORT) {
                    sorter = new QuickSort(studentData, 1);
                } else {
                    sorter = new SelectionSort(studentData);
                }
                
                sorter.setListener(() -> {
                	SwingUtilities.invokeLater(() -> {
                        studentGradesPageControlPanel.setComparisonCount((int) sorter.getComparisons());
                        studentGradesPageControlPanel.setSwapCount((int) sorter.getSwaps());
                        updatePlot();
                    });
                });
                
                // 3. Run the sort in a separate Thread to prevent UI freezing
                Thread sortingThread = new Thread(() -> {
                    // Disable buttons so user doesn't click sort twice while running
                    studentGradesPageControlPanel.getSortBtn().setEnabled(false);
                    sorter.start();
                    SwingUtilities.invokeLater(() -> {
                         studentGradesPageControlPanel.getSortBtn().setEnabled(true);
                    });
                });
                sortingThread.start();
            }
        } else if (e.getSource() == studentGradesPageControlPanel.getRevertToOriginalBtn()) {
            copyInto(tempOldData, studentData);
            updatePlot();
        }
    }

    private void generateStudentData(int length) {
        studentData = RandomStudentsGenerator.randomStudents(length);
        tempOldData = new ArrayList<>(length);
        copyInto(studentData, tempOldData);
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
