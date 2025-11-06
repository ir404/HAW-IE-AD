package panels.pages.trigPage;

import drawingTool.AppFrame;
import panels.ControlPanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class TrigPageControlPanel extends ControlPanel {
    @Serial
    private static final long serialVersionUID = 1L;

    private JLabel titleLabel, dataPointsLabel;
    private JTextField dataPointsField;
    private JRadioButton redRadioBtn;
    private JRadioButton blueRadioBtn;
    private ButtonGroup colorGroup;
    private JButton generateGraphBtn;
    private JButton clearBtn;

    public TrigPageControlPanel(AppFrame appFrame) {
        super(appFrame);
    }

    public JButton getGenerateBtn() {
        return generateGraphBtn;
    }

    public Color getSelectedColor() {
        return blueRadioBtn.isSelected() ? Color.BLUE : Color.RED;
    }
    
    public JButton getClearBtn() {
        return clearBtn;
    }

    @Override
    protected void addControls(GridBagConstraints gbc) {
        titleLabel = new JLabel("Trig Function Controls");
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

        dataPointsLabel = new JLabel("Data Points (2-1000):");
        dataPointsLabel.setFont(new Font(dataPointsLabel.getFont().getName(), Font.PLAIN, BODY_FONT_SIZE));

        dataPointsField = new JTextField("50");
        dataPointsField.setFont(new Font(dataPointsField.getFont().getName(), Font.PLAIN, BODY_FONT_SIZE));

        generateGraphBtn = new JButton("Generate");
        clearBtn= new JButton("Clear graphs");
        resetViewBtn = new JButton("Reset View [R]");

        Insets titleInsets = new Insets(15, 8, 15, 8);
        Insets defaultInsets = new Insets(5, 8, 5, 8);
        Insets sectionTopInsets = new Insets(15, 8, 5, 8);
        Insets buttonTopInsets = new Insets(20, 8, 5, 8);

        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = titleInsets;
        add(titleLabel, gbc);

        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridy = 1;
        gbc.insets = defaultInsets;
        add(redRadioBtn, gbc);

        gbc.gridy = 2;
        gbc.insets = defaultInsets;
        add(blueRadioBtn, gbc);

        gbc.gridy = 3;
        gbc.insets = sectionTopInsets;
        add(dataPointsLabel, gbc);

        gbc.gridy = 4;
        gbc.insets = defaultInsets;
        add(dataPointsField, gbc);

        gbc.gridy = 5;
        gbc.insets = defaultInsets;
        add(generateGraphBtn, gbc);
        
        gbc.gridy = 6;
        gbc.insets = defaultInsets;
        add(clearBtn, gbc);

        // --- Row 6: Reset Button ---
        gbc.gridy = 7;
        gbc.insets = buttonTopInsets;
        add(resetViewBtn, gbc);
        
    }

    public int getNumDataPoints() {
        final int DEFAULT_POINTS = 50;
        try {
            int points = Integer.parseInt(dataPointsField.getText().trim());
            if (points < 2) {
                JOptionPane.showMessageDialog(
                    this,
                    "Number of points must be at least 2. Using default of " + DEFAULT_POINTS + ".",
                    "Input Warning",
                    JOptionPane.WARNING_MESSAGE
                );
                dataPointsField.setText(String.valueOf(DEFAULT_POINTS));
                //points = DEFAULT_POINTS;
            } else if (points > 1000) {
                JOptionPane.showMessageDialog(
                    this,
                    "Number of points cannot exceed 1000. Using default of " + DEFAULT_POINTS + ".",
                    "Input Warning",
                    JOptionPane.WARNING_MESSAGE
                );
                dataPointsField.setText(String.valueOf(DEFAULT_POINTS));
                points = 0;
            }
            return points;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Invalid input. Please enter a whole number (e.g., '50'). Using default.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            dataPointsField.setText(String.valueOf(DEFAULT_POINTS));
            return 0;
        }
    }
}