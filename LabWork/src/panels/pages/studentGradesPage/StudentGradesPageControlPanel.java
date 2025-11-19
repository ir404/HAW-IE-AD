package panels.pages.studentGradesPage;

import drawingTool.AppFrame;
import panels.ControlPanel;
import javax.swing.*;
import java.awt.*;

public class StudentGradesPageControlPanel extends ControlPanel {
    private static final int MIN_STUDENTS = 10;
    private static final int MAX_STUDENTS = 1000;
    private static final int INITIAL_STUDENTS = 100; // A good default starting point

    private JLabel titleLabel, sliderLabel, comparisonsLabel, swapsLabel;
    private JSlider studentCountSlider;
    private JButton generateBtn, sortBtn;
    private int comparisonCount, swapCount;

    public StudentGradesPageControlPanel(AppFrame appFrame) {
        super(appFrame);
        comparisonCount = 0;
        swapCount = 0;
    }

    public JButton getGenerateBtn() {
        return generateBtn;
    }

    public JButton getSortBtn() {
        return sortBtn;
    }

    public int getStudentCount() {
        return studentCountSlider.getValue();
    }

    public void setComparisonCount(int n) {
        comparisonCount = n;
        comparisonsLabel.setText("Number of comparisons : " + comparisonCount);
    }

    public void setSwapCount(int n) {
        swapCount = n;
        swapsLabel.setText("Number of swaps            : " + swapCount);
    }

    @Override
    protected void addControls(GridBagConstraints gbc) {
        // --- 1. Initialise Components ---
        titleLabel = new JLabel("Individual Student Grades for Math");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, super.TITLE_FONT_SIZE));

        sliderLabel = new JLabel("Number of Students: " + INITIAL_STUDENTS);
        sliderLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, super.BODY_FONT_SIZE));

        studentCountSlider = new JSlider(MIN_STUDENTS, MAX_STUDENTS, INITIAL_STUDENTS);
        studentCountSlider.setBackground(super.getBackground());
        studentCountSlider.setMajorTickSpacing(200);
        studentCountSlider.setMinorTickSpacing(50);
        studentCountSlider.setPaintTicks(true);
        studentCountSlider.setPaintLabels(true);
        studentCountSlider.addChangeListener(_ -> {
            int value = studentCountSlider.getValue();
            sliderLabel.setText("Number of Students: " + value);
        });
        studentCountSlider.getInputMap(JComponent.WHEN_FOCUSED).clear();        // disable keyboard movement

        generateBtn = new JButton("Randomly Generate Data");
        sortBtn = new JButton("Sort Data");

        comparisonsLabel = new JLabel("");
        comparisonsLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, super.BODY_FONT_SIZE));
        swapsLabel = new JLabel("");
        swapsLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, super.BODY_FONT_SIZE));

        // --- 2. Layout and Constraints ---
        Insets titleInsets = new Insets(15, 8, 15, 8);
        Insets defaultInsets = new Insets(5, 8, 5, 8);

        // Title Label
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = titleInsets;
        add(titleLabel, gbc);

        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;

        // Slider Label
        gbc.gridy = 1;
        gbc.insets = defaultInsets;
        add(sliderLabel, gbc);

        // JSlider
        gbc.gridy = 2;
        gbc.insets = defaultInsets;
        add(studentCountSlider, gbc);

        // Generate Button
        gbc.gridy = 3;
        gbc.insets = defaultInsets;
        add(generateBtn, gbc);

        // Sort Button
        gbc.gridy = 4;
        gbc.insets = defaultInsets;
        add(sortBtn, gbc);

        // Comparisons label
        gbc.gridy = 5;
        gbc.insets = defaultInsets;
        add(comparisonsLabel, gbc);

        // Swaps label
        gbc.gridy = 6;
        gbc.insets = defaultInsets;
        add(swapsLabel, gbc);
    }
}