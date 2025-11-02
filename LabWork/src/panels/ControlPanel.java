package panels;

import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JLabel titleLabel;
    private JRadioButton redRadioBtn;
    private JRadioButton blueRadioBtn;
    private ButtonGroup colorGroup;
    
    // --- ADDED ---
    private JLabel dataPointsLabel;
    private JTextField dataPointsField;
    // --- END ---
    
    private JButton generateGraphBtn;
    private JButton resetViewBtn;

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

        redRadioBtn = new JRadioButton("Red Graph");
        redRadioBtn.setFont(new Font(redRadioBtn.getFont().getName(), Font.PLAIN, BODY_FONT_SIZE));
        redRadioBtn.setBackground(CONTROL_PANEL_COLOUR);
        redRadioBtn.setSelected(true);

        blueRadioBtn = new JRadioButton("Blue Graph");
        blueRadioBtn.setFont(new Font(blueRadioBtn.getFont().getName(), Font.PLAIN, BODY_FONT_SIZE));
        blueRadioBtn.setBackground(CONTROL_PANEL_COLOUR);

        colorGroup = new ButtonGroup();
        colorGroup.add(redRadioBtn);
        colorGroup.add(blueRadioBtn);

        // --- ADDED ---
        dataPointsLabel = new JLabel("Data Points (2-1000):");
        dataPointsLabel.setFont(new Font(dataPointsLabel.getFont().getName(), Font.PLAIN, BODY_FONT_SIZE));

        dataPointsField = new JTextField("50"); // Default value
        dataPointsField.setFont(new Font(dataPointsField.getFont().getName(), Font.PLAIN, BODY_FONT_SIZE));
        // --- END ---

        generateGraphBtn = new JButton("Generate");
        resetViewBtn = new JButton("Reset View [R]");
    }

    private void addUIComponentsToGrid() {
        GridBagConstraints gbc = new GridBagConstraints();

        Insets titleInsets = new Insets(15, 8, 15, 8);
        Insets defaultInsets = new Insets(5, 8, 5, 8);
        Insets sectionTopInsets = new Insets(15, 8, 5, 8); // For spacing
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

        // --- Row 1: Red Radio Button ---
        gbc.gridy = 1;
        gbc.insets = defaultInsets;
        add(redRadioBtn, gbc);

        // --- Row 2: Blue Radio Button ---
        gbc.gridy = 2;
        gbc.insets = defaultInsets;
        add(blueRadioBtn, gbc);

        // --- Row 3: Data Points Label (ADDED) ---
        gbc.gridy = 3;
        gbc.insets = sectionTopInsets; // Add space above
        add(dataPointsLabel, gbc);

        // --- Row 4: Data Points Field (ADDED) ---
        gbc.gridy = 4;
        gbc.insets = defaultInsets;
        add(dataPointsField, gbc);

        // --- Row 5: Generate Button ---
        gbc.gridy = 5; // (was 3)
        gbc.insets = defaultInsets;
        add(generateGraphBtn, gbc);

        // --- Row 6: Reset Button ---
        gbc.gridy = 6; // (was 4)
        gbc.insets = buttonTopInsets;
        add(resetViewBtn, gbc);

        // --- Spacer ---
        gbc.gridy = 7; // (was 5)
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel spacer = new JPanel();
        spacer.setBackground(CONTROL_PANEL_COLOUR);
        add(spacer, gbc);
    }

    public JButton getResetViewBtn() {
        return resetViewBtn;
    }

    public JButton getGenerateGraphBtn() {
        return generateGraphBtn;
    }

    public Color getSelectedColor() {
        if (blueRadioBtn.isSelected()) {
            return Color.BLUE;
        } else {
            return Color.RED;
        }
    }

 // --- REPLACED: Method to get data points safely ---
    public int getNumDataPoints() {
        final int DEFAULT_POINTS = 50;
        try {
            // Read the text and convert it to an integer
            int points = Integer.parseInt(dataPointsField.getText().trim()); // .trim() removes spaces

            // Check 1: Out of bounds (too low)
            if (points < 2) {
                // Show a warning dialog
                JOptionPane.showMessageDialog(
                        this, // 'this' refers to the ControlPanel, centering the dialog
                        "Number of points must be at least 2. Using default of " + DEFAULT_POINTS + ".",
                        "Input Warning", // Title of the dialog
                        JOptionPane.WARNING_MESSAGE);
                
                dataPointsField.setText(String.valueOf(DEFAULT_POINTS)); // Reset text
                return DEFAULT_POINTS; // Return a safe default
            }
            
            // Check 2: Out of bounds (too high)
            if (points > 1000) { // Safety limit
                // Show a warning dialog
                JOptionPane.showMessageDialog(
                        this,
                        "Number of points cannot exceed 1000. Using default of " + DEFAULT_POINTS + ".",
                        "Input Warning",
                        JOptionPane.WARNING_MESSAGE);
                
                dataPointsField.setText(String.valueOf(DEFAULT_POINTS)); // Reset text
                return DEFAULT_POINTS; // Return a safe default
            }
            
            return points; // All good, return the user's number

        } catch (NumberFormatException e) {
            // Check 3: Not a number (e.g., "abc" or empty)
            
            // Show an error dialog
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid input. Please enter a whole number (e.g., '50'). Using default.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE); // Use a more serious icon

            dataPointsField.setText(String.valueOf(DEFAULT_POINTS)); // Reset text
            return DEFAULT_POINTS; // Return a safe default
        }
    }
    // --- END ---
}