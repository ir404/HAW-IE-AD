package panels.pages.trigPage;

import drawingTool.AppFrame;
import graphs.LineGraph;
import panels.GraphPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrigPage extends JPanel implements ActionListener {
    private GraphPanel graphPanel;
    private TrigPageControlPanel trigPageControlPanel;

    public TrigPage(AppFrame appFrame, int width, int height) {
        graphPanel = new GraphPanel((int) (width * 0.80), height, 0 ,0);

        trigPageControlPanel = new TrigPageControlPanel(appFrame);
        trigPageControlPanel.setPreferredSize(new Dimension((int) (width * 0.20), height));
        trigPageControlPanel.getResetViewBtn().addActionListener(this);
        trigPageControlPanel.getGenerateBtn().addActionListener(this);
        trigPageControlPanel.getClearBtn().addActionListener(this);
        
        super.setLayout(new BorderLayout());
        super.add(graphPanel, BorderLayout.CENTER);
        super.add(trigPageControlPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == trigPageControlPanel.getResetViewBtn()) {
            graphPanel.getScene().resetView();
            graphPanel.repaint();
        } else if (event.getSource() == trigPageControlPanel.getGenerateBtn()) {
            Color selectedColor = trigPageControlPanel.getSelectedColor();
            LineGraph graph = new LineGraph(selectedColor);
            int numPoints = trigPageControlPanel.getNumDataPoints();

            graph.generateData(numPoints);
            graphPanel.getScene().addGraph(graph);
            graphPanel.repaint();
        } else if(event.getSource() == trigPageControlPanel.getClearBtn()) {
        	graphPanel.getScene().clearGraph();
        	graphPanel.repaint();
        }
    }
}
