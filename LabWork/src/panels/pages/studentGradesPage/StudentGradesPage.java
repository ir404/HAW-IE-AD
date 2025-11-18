package panels.pages.studentGradesPage;

import drawingTool.AppFrame;
import graphs.Point;
import graphs.ScatterPlot;
import panels.GraphPanel;
import util.Node;
import util.QuickSort;
import util.RandomStudentsGenerator;
import util.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class StudentGradesPage extends JPanel implements ActionListener {
    private GraphPanel graphPanel;
    private StudentGradesPageControlPanel studentGradesPageControlPanel;
    // ArrayList to hold Student data
    private ArrayList<Node> studentData;

    public StudentGradesPage(AppFrame appFrame, int width, int height) {
        // initialise student data and randomly generate grades (assign element index as name eg. "student_1", "student_2", etc)
    	int numStudents=10;// for now 10 bcus 1000 students when sorted makes the results too broad like 0000000,11111111,222222 etc
    	studentData = RandomStudentsGenerator.RandomStudents(numStudents);
        graphPanel = new GraphPanel((int) (width * 0.80), height, -450, 250);
        studentGradesPageControlPanel = new StudentGradesPageControlPanel(appFrame);
        studentGradesPageControlPanel.setPreferredSize(new Dimension((int) (width * 0.20), height));
        studentGradesPageControlPanel.getShowBtn().addActionListener(this);
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
        } else if (e.getSource() == studentGradesPageControlPanel.getShowBtn()) {
            ArrayList<Point> points = mapStudentDataToGraphPoints();
            ScatterPlot scatterPlot = new ScatterPlot(Color.RED, points);
            graphPanel.getScene().addGraph(scatterPlot);
            graphPanel.repaint();
        }else if (e.getSource() == studentGradesPageControlPanel.getSortBtn()) {
        	graphPanel.getScene().clearGraph();
        	sort();
        	ArrayList<Point> points = mapStudentDataToGraphPoints();
        	ScatterPlot scatterPlot = new ScatterPlot(Color.RED, points);
            graphPanel.getScene().addGraph(scatterPlot);
            graphPanel.repaint();
        }
    }

    private void sort() {
    	QuickSort sorter=new QuickSort(studentData);
    	sorter.start(1);
    	
    }
    private ArrayList<Point> mapStudentDataToGraphPoints() {
        ArrayList<Point> points = new ArrayList<>();
        // TODO: loop through the student-data list and map each grade to a point's y and index to x. Then remove the sample below.
        points.add(new Point(0, 0));
        if (studentData != null) {
            for (int i = 0; i < studentData.size(); i++) {
                // Map index to x and grade to y
                int x = i+1;
                int y = studentData.get(i).getKey();
                points.add(new Point(x, y));
            }
        }
        return points;
    }
}
