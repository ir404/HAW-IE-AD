package panels.pages;

import drawingTool.AppFrame;
import util.PageName;

import javax.swing.*;

public class HomePage extends JPanel {
    private JButton option1;
    private JButton option2;

    public HomePage(AppFrame appFrame) {
        option1 = new JButton("Trig Functions");
        option1.addActionListener(__ -> appFrame.showPage(PageName.TRIG_PAGE));
        option2 = new JButton("Company Revenue");
        option2.addActionListener(__ -> appFrame.showPage(PageName.COMPANY_STATS_PAGE));
        super.add(option1);
        super.add(option2);
    }
}
