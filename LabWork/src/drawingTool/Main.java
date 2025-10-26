package drawingTool;

import graphs.LineGraph;
import panels.ControlPanel;
import panels.GraphPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Main extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    
	private GraphPanel graphPanel;
    private ControlPanel controlPanel;

    public Main(String title) {
        int screenWidth = getToolkit().getScreenSize().width;
        int screenHeight = getToolkit().getScreenSize().height;
        int frameWidth = (int) (screenWidth * 0.95);
        int frameHeight = (int) (screenHeight * 0.95);
        int framePlacementX = (int) (screenWidth * 0.5 - frameWidth * 0.5);
        int framePlacementY = (int) (screenHeight * 0.5 - frameHeight * 0.5);

        graphPanel = new GraphPanel((int) (frameWidth * 0.80), frameHeight);

        controlPanel = new ControlPanel();
        controlPanel.setPreferredSize(new Dimension((int) (frameWidth * 0.20), frameHeight));
        controlPanel.initialise();
        controlPanel.getResetViewBtn().addActionListener(this);
        controlPanel.getLoadGraph1Btn().addActionListener(this);

        setupKeyBindings(graphPanel);

        super.setLayout(new BorderLayout());
        super.add(graphPanel, BorderLayout.CENTER);
        super.add(controlPanel, BorderLayout.EAST);

        super.setTitle(title);
        super.setBounds(framePlacementX, framePlacementY, frameWidth, frameHeight);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == controlPanel.getResetViewBtn()) {
            graphPanel.getScene().resetView();
            graphPanel.repaint();
        } else if (event.getSource() == controlPanel.getLoadGraph1Btn()) {
            LineGraph graph = new LineGraph(Color.RED);
            graph.generateData(50);
            graphPanel.getScene().addGraph(graph);
            graphPanel.repaint();
        }
    }

    private void setupKeyBindings(GraphPanel panel) {
        addKeyBinding(panel, KeyEvent.VK_UP, "upReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.getScene().scrollUp();
                graphPanel.repaint();
            }
        });

        addKeyBinding(panel, KeyEvent.VK_DOWN, "downReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.getScene().scrollDown();
                graphPanel.repaint();
            }
        });

        addKeyBinding(panel, KeyEvent.VK_LEFT, "leftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.getScene().scrollLeft();
                graphPanel.repaint();
            }
        });

        addKeyBinding(panel, KeyEvent.VK_RIGHT, "rightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.getScene().scrollRight();
                graphPanel.repaint();
            }
        });

        addKeyBinding(panel, KeyEvent.VK_R, "rKeyReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.getScene().resetView();
                graphPanel.repaint();
            }
        });
    }

    private void addKeyBinding(JComponent panel, int keyCode, String actionName, Action action) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, 0, true);
        inputMap.put(keyStroke, actionName);
        actionMap.put(actionName, action);
    }

    public static void main(String[] args) {
        new Main("Plotter");
    }
}