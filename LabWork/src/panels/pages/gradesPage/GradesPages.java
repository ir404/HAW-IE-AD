package panels.pages.gradesPage;

import drawingTool.AppFrame;
import graphs.BarGraph;
import panels.GraphPanel;
import util.DataParser;
import util.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class GradesPages extends JPanel implements ActionListener {
    private GraphPanel graphPanel;
    private GradesPageControlPanel gradesPageControlPanel;
    private List<Student> studentData;

    public GradesPages(AppFrame appFrame, int width, int height) {
        graphPanel = new GraphPanel((int) (width * 0.80), height, -450, 250);
        gradesPageControlPanel = new GradesPageControlPanel(appFrame);
        gradesPageControlPanel.setPreferredSize(new Dimension((int) (width * 0.20), height));
        gradesPageControlPanel.getShowBtn().addActionListener(this);
        gradesPageControlPanel.getResetViewBtn().addActionListener(this);
        super.setLayout(new BorderLayout());
        super.add(graphPanel, BorderLayout.CENTER);
        super.add(gradesPageControlPanel, BorderLayout.EAST);

        try {
            studentData = DataParser.loadStudentData();
        } catch (Exception e) {
            System.err.println("Error when loading student data. \n" + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gradesPageControlPanel.getResetViewBtn()) {
            graphPanel.getScene().resetView();
            graphPanel.repaint();
        } else if (e.getSource() == gradesPageControlPanel.getShowBtn()) {
            BarGraph graph = getBarGraph();
            graphPanel.getScene().addGraph(graph);
            graphPanel.repaint();
        }
    }

    private BarGraph getBarGraph() {
        int[] x = new int[15];
        int[] gradeCount = new int[15];
        for (int i = 0; i < x.length; i++) {
            x[i] = i;
        }
        for (Student student: studentData) {
            gradeCount[student.getKey() - 1] += 1;
        }
        System.out.println(Arrays.toString(gradeCount));

        return new BarGraph(Color.RED, x, gradeCount);
    }
}
