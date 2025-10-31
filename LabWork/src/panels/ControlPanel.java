package panels;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 1L;

	private JLabel titleLabel, graph1Label, graph2Label;
    private JButton loadGraph1Btn, loadGraph2Btn, resetViewBtn;

    private final Color CONTROL_PANEL_COLOUR = Color.LIGHT_GRAY;
    private final int TITLE_FONT_SIZE = 14;
    private final int BODY_FONT_SIZE = 12;

    public void initialise() {
        super.setBackground(CONTROL_PANEL_COLOUR);
        super.setLayout(new GridBagLayout());
        initialiseUIComponents();
        addUIComponentsToGrid();
    }

    private void initialiseUIComponents() {
        titleLabel = new JLabel("Plotter Controls");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, TITLE_FONT_SIZE));

        graph1Label = new JLabel("LineGraph 1 (Red)");
        graph1Label.setFont(new Font(graph1Label.getFont().getName(), Font.BOLD, BODY_FONT_SIZE));
        loadGraph1Btn = new JButton("Generate");

        graph2Label = new JLabel("LineGraph 2 (Blue)");
        graph2Label.setFont(new Font(graph2Label.getFont().getName(), Font.BOLD, BODY_FONT_SIZE));
        loadGraph2Btn = new JButton("Generate");

        resetViewBtn = new JButton("Reset View [R]");
    }

    private void addUIComponentsToGrid() {
        GridBagConstraints gbc = new GridBagConstraints();

        Insets titleInsets = new Insets(15, 8, 15, 8);
        Insets defaultInsets = new Insets(5, 8, 5, 8);
        Insets sectionTopInsets = new Insets(15, 8, 5, 8);
        Insets buttonTopInsets = new Insets(20, 8, 5, 8);

        // --- Row 0: Title Label ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = titleInsets;
        add(titleLabel, gbc);

        // --- Reset common constraints ---
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;

        // --- Row 1: LineGraph 1 Title ---
        gbc.gridy = 1;
        gbc.insets = defaultInsets;
        add(graph1Label, gbc);

        // --- Row 2: LineGraph 1 Button ---
        gbc.gridy = 2;
        gbc.insets = defaultInsets;
        add(loadGraph1Btn, gbc);

        // --- Row 3: LineGraph 2 Title ---
        gbc.gridy = 3;
        gbc.insets = sectionTopInsets;
        add(graph2Label, gbc);

        // --- Row 4: LineGraph 2 Button ---
        gbc.gridy = 4;
        gbc.insets = defaultInsets;
        add(loadGraph2Btn, gbc);

        gbc.gridy = 5;
        gbc.insets = buttonTopInsets;
        add(resetViewBtn, gbc);

        // --- Spacer ---
        gbc.gridy = 6;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel spacer = new JPanel();
        spacer.setBackground(CONTROL_PANEL_COLOUR);
        add(spacer, gbc);
    }

    public JButton getResetViewBtn() {
        return resetViewBtn;
    }

    public JButton getLoadGraph1Btn() {
        return loadGraph1Btn;
    }
}