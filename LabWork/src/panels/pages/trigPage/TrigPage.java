package panels.pages.trigPage;

import drawingTool.AppFrame;
import graphs.LineGraph;
import panels.GraphPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class TrigPage extends JPanel implements ActionListener {
    private GraphPanel graphPanel;
    private TrigPageControlPanel trigPageControlPanel;

    public TrigPage(AppFrame appFrame, int width, int height) {
        graphPanel = new GraphPanel((int) (width * 0.80), height);

        trigPageControlPanel = new TrigPageControlPanel(appFrame);
        trigPageControlPanel.setPreferredSize(new Dimension((int) (width * 0.20), height));
        trigPageControlPanel.getResetViewBtn().addActionListener(this);
        trigPageControlPanel.getLoadGraph1Btn().addActionListener(this);

        super.setLayout(new BorderLayout());
        super.add(graphPanel, BorderLayout.CENTER);
        super.add(trigPageControlPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == trigPageControlPanel.getResetViewBtn()) {
            graphPanel.getScene().resetView();
            graphPanel.repaint();
        } else if (event.getSource() == trigPageControlPanel.getLoadGraph1Btn()) {
            LineGraph graph = new LineGraph(Color.RED);
            graph.generateData(50);
            graphPanel.getScene().addGraph(graph);
            graphPanel.repaint();
        }
    }
}
