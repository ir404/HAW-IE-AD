package panels.pages.studentGradesPage;

import drawingTool.AppFrame;
import graphs.Point;
import graphs.ScatterPlot;
import panels.GraphPanel;

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

    public StudentGradesPage(AppFrame appFrame, int width, int height) {
        // initialise student data and randomly generate grades (assign element index as name eg. "student_1", "student_2", etc)

        graphPanel = new GraphPanel((int) (width * 0.80), height, -450, 250);
        studentGradesPageControlPanel = new StudentGradesPageControlPanel(appFrame);
        studentGradesPageControlPanel.setPreferredSize(new Dimension((int) (width * 0.20), height));
        studentGradesPageControlPanel.getShowBtn().addActionListener(this);
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
        }
    }

    private ArrayList<Point> mapStudentDataToGraphPoints() {
        ArrayList<Point> points = new ArrayList<>();
        // TODO: loop through the student-data list and map each grade to a point's y and index to x. Then remove the sample below.
        points.add(new Point(0, 0));
        for (int i = 0; i < 100; i++) {
            int y = new Random().nextInt(16);
            points.add(new Point(i, y));
        }
        return points;
    }
}
