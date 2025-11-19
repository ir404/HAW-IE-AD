package panels.pages.studentGradesPage;

import drawingTool.AppFrame;
import graphs.Point;
import graphs.ScatterPlot;
import panels.GraphPanel;
import util.Node;
import util.QuickSort;
import util.RandomStudentsGenerator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentGradesPage extends JPanel implements ActionListener {
    private GraphPanel graphPanel;
    private StudentGradesPageControlPanel studentGradesPageControlPanel;
    private ArrayList<Node> studentData;

    public StudentGradesPage(AppFrame appFrame, int width, int height) {
        graphPanel = new GraphPanel((int) (width * 0.80), height, -450, 250);

        studentGradesPageControlPanel = new StudentGradesPageControlPanel(appFrame);
        studentGradesPageControlPanel.setPreferredSize(new Dimension((int) (width * 0.20), height));
        studentGradesPageControlPanel.getGenerateBtn().addActionListener(this);
        studentGradesPageControlPanel.getSortBtn().addActionListener(this);
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
            studentData = RandomStudentsGenerator.randomStudents(studentGradesPageControlPanel.getStudentCount());
            ArrayList<Point> points = mapStudentDataToGraphPoints();
            ScatterPlot scatterPlot = new ScatterPlot(Color.RED, points);
            graphPanel.getScene().clearGraph();
            graphPanel.getScene().addGraph(scatterPlot);
            graphPanel.repaint();
        } else if (e.getSource() == studentGradesPageControlPanel.getSortBtn()) {
            if (studentData != null) {
                graphPanel.getScene().clearGraph();
                QuickSort sorter = new QuickSort(studentData);
                sorter.start(1);
                ArrayList<Point> points = mapStudentDataToGraphPoints();
                ScatterPlot scatterPlot = new ScatterPlot(Color.RED, points);
                studentGradesPageControlPanel.setComparisonCount((int) sorter.getComparisons());
                studentGradesPageControlPanel.setSwapCount((int) sorter.getSwaps());
                graphPanel.getScene().addGraph(scatterPlot);
                graphPanel.repaint();
            }
        }
    }

    private ArrayList<Point> mapStudentDataToGraphPoints() {
        ArrayList<Point> points = new ArrayList<>();
        if (studentData != null) {
            for (int i = 0; i < studentData.size(); i++) {
                int x = i + 1;
                int y = studentData.get(i).getKey();
                points.add(new Point(x, y));
            }
        }
        return points;
    }
}
