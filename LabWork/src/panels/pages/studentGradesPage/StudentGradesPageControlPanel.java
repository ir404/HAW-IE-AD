package panels.pages.studentGradesPage;

import drawingTool.AppFrame;
import panels.ControlPanel;
import sortings.SortAlgorithm;

import javax.swing.*;
import java.awt.*;

public class StudentGradesPageControlPanel extends ControlPanel {
    private static final long serialVersionUID = 1L;
	private final int MIN_STUDENTS = 10;
    private final int MAX_STUDENTS = 1000;
    private final int INITIAL_STUDENTS = 100;

    private JLabel titleLabel, sliderLabel, algorithmLabel, comparisonsLabel, swapsLabel;
    private JSlider studentCountSlider;
    private JButton generateBtn, sortBtn, revertToOriginalBtn;
    private JComboBox<String> algorithmDropdown;
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

    public JButton getRevertToOriginalBtn() {
        return revertToOriginalBtn;
    }

    public int getStudentCount() {
        return studentCountSlider.getValue();
    }

    // New public method to get the selected algorithm
    public SortAlgorithm getSortingAlgorithm() {
        String selectedDisplayName = algorithmDropdown.getSelectedItem().toString();
        SortAlgorithm selected = null;
        if (selectedDisplayName != null) {
            selected = SortAlgorithm.fromName(selectedDisplayName);
        }
        return selected;
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
        studentCountSlider.addChangeListener(event -> {
            int value = studentCountSlider.getValue();
            sliderLabel.setText("Number of Students: " + value);
        });
        studentCountSlider.getInputMap(JComponent.WHEN_FOCUSED).clear();        // disable keyboard movement

        generateBtn = new JButton("Randomly Generate Data");
        sortBtn = new JButton("Sort Data");
        revertToOriginalBtn = new JButton("Revert To Original");

        algorithmLabel = new JLabel("Sorting Algorithm:");
        algorithmLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, super.BODY_FONT_SIZE));

        algorithmDropdown = new JComboBox<>(
            new String[]{
                SortAlgorithm.QUICK_SORT.getName(),
                SortAlgorithm.SELECTION_SORT.getName()
            }
        );
        algorithmDropdown.setSelectedIndex(0);

        comparisonsLabel = new JLabel("");
        comparisonsLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, super.BODY_FONT_SIZE));
        swapsLabel = new JLabel("");
        swapsLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, super.BODY_FONT_SIZE));

        // --- 2. Layout and Constraints ---
        Insets titleInsets = new Insets(15, 8, 15, 8);
        Insets defaultInsets = new Insets(5, 8, 5, 8);
        Insets verticalGapInsets = new Insets(30, 8, 5, 8); // Larger top padding for the gap

        // Title Label
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Spans two columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = titleInsets;
        add(titleLabel, gbc);

        // Subsequent controls use 2 columns and fill horizontally
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

        // NEW: Algorithm Label
        gbc.gridy = 4;
        gbc.insets = verticalGapInsets;
        add(algorithmLabel, gbc);

        // NEW: Algorithm Dropdown
        gbc.gridy = 5;
        gbc.insets = defaultInsets;
        add(algorithmDropdown, gbc);

        // ⭐ Place the Sort and Reset buttons side-by-side on the next row
        gbc.gridy = 6;
        gbc.gridwidth = 1; // Back to a single column

        // Sort Button (Left half)
        gbc.insets = defaultInsets;
        gbc.weightx = 0.5; // Give it half the width
        add(sortBtn, gbc);

        // Reset Button (Right half)
        gbc.gridx = 1; // Move to the second column
        gbc.weightx = 0.5; // Give it the other half
        add(revertToOriginalBtn, gbc);

        // Reset constraints for the next elements
        gbc.gridx = 0; // Back to the first column
        gbc.gridwidth = 2; // Spanning two columns
        gbc.weightx = 0.0; // Reset weight

        // Comparisons label
        gbc.gridy = 7;
        gbc.insets = defaultInsets;
        add(comparisonsLabel, gbc);

        // Swaps label
        gbc.gridy = 8;
        gbc.insets = defaultInsets;
        add(swapsLabel, gbc);
    }
}