package drawingTool;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for all user interface controls (buttons, sliders, etc.)
 * This version is updated to allow users to load data files dynamically
 * with hard-coded colours.
 */
public class ControlPanel extends JPanel {
    private final Color CONTROL_PANEL_COLOUR = Color.LIGHT_GRAY;

    // UI Components for the plotter
    private JLabel titleLabel;

    // --- Graph 1 Components ---
    private JLabel graph1Label;
    private JButton loadGraph1Btn;
    private JLabel graph1FileLabel;

    // --- Graph 2 Components ---
    private JLabel graph2Label;
    private JButton loadGraph2Btn;
    private JLabel graph2FileLabel;

    // --- View Control Components ---
    private JButton scrollLeftBtn, scrollRightBtn, scrollUpBtn, scrollDownBtn;
    private JButton resetViewBtn;

    /**
     * Sets up the panel and initialises all UI components.
     */
    public void initialise() {
        super.setBackground(CONTROL_PANEL_COLOUR);
        super.setLayout(new GridBagLayout());
        initialiseUIComponents();
        addUIComponentsToGrid();
    }

    /**
     * Creates instances of all UI components.
     */
    private void initialiseUIComponents() {
        titleLabel = new JLabel("Plotter Controls");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 14));

        // Graph 1
        graph1Label = new JLabel("Graph 1 (Red)");
        graph1Label.setFont(new Font(graph1Label.getFont().getName(), Font.BOLD, 12));
        loadGraph1Btn = new JButton("Load File...");
        graph1FileLabel = new JLabel("(no file selected)");
        graph1FileLabel.setFont(new Font(graph1FileLabel.getFont().getName(), Font.ITALIC, 10));

        // Graph 2
        graph2Label = new JLabel("Graph 2 (Blue)");
        graph2Label.setFont(new Font(graph2Label.getFont().getName(), Font.BOLD, 12));
        loadGraph2Btn = new JButton("Load File...");
        graph2FileLabel = new JLabel("(no file selected)");
        graph2FileLabel.setFont(new Font(graph2FileLabel.getFont().getName(), Font.ITALIC, 10));

        // View Controls
        scrollLeftBtn = new JButton("Scroll Left");
        scrollRightBtn = new JButton("Scroll Right");
        scrollUpBtn = new JButton("Scroll Up");
        scrollDownBtn = new JButton("Scroll Down");

        resetViewBtn = new JButton("Reset View");
    }

    /**
     * Adds all components to the panel using GridBagLayout.
     */
    private void addUIComponentsToGrid() {
        GridBagConstraints gbc = new GridBagConstraints();

        // --- Row 0: Title Label ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 8, 15, 8);
        add(titleLabel, gbc);

        // --- Reset common constraints ---
        gbc.gridwidth = 2; // Make all components span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.anchor = GridBagConstraints.LINE_START;

        // --- Row 1: Graph 1 Title ---
        gbc.gridy = 1;
        add(graph1Label, gbc);

        // --- Row 2: Graph 1 Button ---
        gbc.gridy = 2;
        add(loadGraph1Btn, gbc);

        // --- Row 3: Graph 1 File Label ---
        gbc.gridy = 3;
        add(graph1FileLabel, gbc);

        // --- Row 4: Graph 2 Title ---
        gbc.gridy = 4;
        gbc.insets = new Insets(15, 8, 5, 8); // Add space
        add(graph2Label, gbc);

        // --- Row 5: Graph 2 Button ---
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 8, 5, 8); // Reset space
        add(loadGraph2Btn, gbc);

        // --- Row 6: Graph 2 File Label ---
        gbc.gridy = 6;
        add(graph2FileLabel, gbc);

        // --- View Controls ---
        gbc.insets = new Insets(20, 8, 5, 8);

        gbc.gridy = 7;
        add(scrollUpBtn, gbc);
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 8, 5, 8);
        add(scrollDownBtn, gbc);
        gbc.gridy = 9;
        add(scrollLeftBtn, gbc);
        gbc.gridy = 10;
        add(scrollRightBtn, gbc);
        gbc.gridy = 11;
        gbc.insets = new Insets(20, 8, 5, 8);
        add(resetViewBtn, gbc);

        // --- Spacer ---
        gbc.gridy = 12;
        gbc.weighty = 1.0;
        add(new JPanel() {{ setBackground(CONTROL_PANEL_COLOUR); }}, gbc);
    }

    // --- Getters for Main class to add listeners ---
    public JButton getLoadGraph1Btn() { return loadGraph1Btn; }
    public JButton getLoadGraph2Btn() { return loadGraph2Btn; }
    public JButton getScrollLeftBtn() { return scrollLeftBtn; }
    public JButton getScrollRightBtn() { return scrollRightBtn; }
    public JButton getScrollUpBtn() { return scrollUpBtn; }
    public JButton getScrollDownBtn() { return scrollDownBtn; }
    public JButton getResetViewBtn() { return resetViewBtn; }

    // --- Setters for state ---
    public void setGraph1FileName(String name) {
        graph1FileLabel.setText(name);
    }

    public void setGraph2FileName(String name) {
        graph2FileLabel.setText(name);
    }
}

