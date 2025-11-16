package panels.pages;

import drawingTool.AppFrame;
import util.PageName;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 30;

    private JButton option1;
    private JButton option2;
    private JButton option3;

    public HomePage(AppFrame appFrame) {
        super.setLayout(new GridBagLayout());
        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);

        option1 = new JButton("Trig Functions");
        option1.addActionListener(__ -> appFrame.showPage(PageName.TRIG_PAGE));
        option1.setPreferredSize(buttonSize);

        option2 = new JButton("Grades");
        option2.addActionListener(__ -> appFrame.showPage(PageName.GRADES_PAGE));
        option2.setPreferredSize(buttonSize);

        option3 = new JButton("Individual Student Grades");
        option3.addActionListener(__ -> appFrame.showPage(PageName.STUDENT_GRADES_PAGE));
        option3.setPreferredSize(buttonSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        super.add(option1, gbc);

        gbc.gridy = 1;
        super.add(option2, gbc);

        gbc.gridy = 2;
        super.add(option3, gbc);
    }
}