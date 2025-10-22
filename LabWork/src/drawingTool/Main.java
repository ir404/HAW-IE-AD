package drawingTool;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * The main application window (JFrame).
 * Acts as the Controller, connecting buttons from ControlPanel
 * to actions in the Scene.
 */
public class Main extends JFrame implements ActionListener {
    private GraphPanel graphPanel;
    private ControlPanel controlPanel;
    private Scene scene; // A direct reference to the scene for easy control

    private JFileChooser fileChooser; // File chooser for loading data

    public Main(String title) {
        // Screen size
        int screenWidth = getToolkit().getScreenSize().width;
        int screenHeight = getToolkit().getScreenSize().height;
        int frameWidth = (int) (screenWidth * 0.95);
        int frameHeight = (int) (screenHeight * 0.95);
        int framePlacementX = (int) (screenWidth * 0.5 - frameWidth * 0.5);
        int framePlacementY = (int) (screenHeight * 0.5 - frameHeight * 0.5);

        // --- Set up GraphPanel and Scene ---
        graphPanel = new GraphPanel((int) (frameWidth * 0.80), frameHeight);
        scene = graphPanel.getScene(); // Get the scene reference

        // --- Set up ControlPanel ---
        controlPanel = new ControlPanel();
        controlPanel.setPreferredSize(new Dimension((int) (frameWidth * 0.20), frameHeight));
        controlPanel.initialise();

        // --- Set up File Chooser ---
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        // --- Connect buttons to this class's ActionListener ---
        addListeners();

        // --- Assemble the JFrame ---
        super.setLayout(new BorderLayout());
        super.add(graphPanel, BorderLayout.CENTER);
        super.add(controlPanel, BorderLayout.EAST);

        super.setTitle(title);
        super.setBounds(framePlacementX, framePlacementY, frameWidth, frameHeight);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    /**
     * Helper method to attach 'this' as the listener to all control buttons.
     */
    private void addListeners() {
        // Graph 1
        controlPanel.getLoadGraph1Btn().addActionListener(this);

        // Graph 2
        controlPanel.getLoadGraph2Btn().addActionListener(this);

        // View Controls
        controlPanel.getScrollLeftBtn().addActionListener(this);
        controlPanel.getScrollRightBtn().addActionListener(this);
        controlPanel.getScrollUpBtn().addActionListener(this);
        controlPanel.getScrollDownBtn().addActionListener(this);

        controlPanel.getResetViewBtn().addActionListener(this);
    }

    /**
     * Central event handler for all button clicks.
     * @param e The event that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        final int SCROLL_AMOUNT = 20; // Pixels to scroll per click

        // --- Graph 1 ---
        if (source == controlPanel.getLoadGraph1Btn()) {
            handleLoadGraph(1);

            // --- Graph 2 ---
        } else if (source == controlPanel.getLoadGraph2Btn()) {
            handleLoadGraph(2);

            // --- View Controls ---
        } else if (source == controlPanel.getScrollLeftBtn()) {
            scene.scroll(SCROLL_AMOUNT, 0);
        } else if (source == controlPanel.getScrollRightBtn()) {
            scene.scroll(-SCROLL_AMOUNT, 0);
        } else if (source == controlPanel.getScrollUpBtn()) {
            scene.scroll(0, SCROLL_AMOUNT);
        } else if (source == controlPanel.getScrollDownBtn()) {
            scene.scroll(0, -SCROLL_AMOUNT);
        } else if (source == controlPanel.getResetViewBtn()) {
            scene.resetView();
        }

        // After any action, repaint the graph panel
        graphPanel.repaint();
    }

    /**
     * Handles opening the JFileChooser and loading data for the specified graph slot.
     * @param graphIndex The slot (1 or 2) to load data into.
     */
    private void handleLoadGraph(int graphIndex) {
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            String name = selectedFile.getName();

            Color color;
            if (graphIndex == 1) {
                color = Color.RED; // Hard-coded colour
                controlPanel.setGraph1FileName(name);
            } else {
                color = Color.BLUE; // Hard-coded colour
                controlPanel.setGraph2FileName(name);
            }

            // Load the data
            loadGraphData(path, color);
        }
    }

    /**
     * Helper method to create a graph, load its data, and add it to the scene.
     * @param filename The data file path to load.
     * @param color The colour for the graph line.
     */
    private void loadGraphData(String filename, Color color) {
        Graph graph = new Graph(color);
        graph.loadDataFromFile(filename);
        scene.addGraph(graph);
    }

    public static void main(String[] args) {
        new Main("Plotter");
    }
}

