package panels.pages.companyStatsPage;

import drawingTool.AppFrame;
import panels.GraphPanel;

import javax.swing.*;
import java.awt.*;

public class CompanyStatsPage extends JPanel {
    private GraphPanel graphPanel;
    private CompanyStatsPageControlPanel companyStatsPageControlPanel;

    public CompanyStatsPage(AppFrame appFrame, int width, int height) {
        graphPanel = new GraphPanel((int) (width * 0.80), height);
//        setupKeyBindings(graphPanel);

        companyStatsPageControlPanel = new CompanyStatsPageControlPanel(appFrame);
        companyStatsPageControlPanel.setPreferredSize(new Dimension((int) (width * 0.20), height));
//        companyStatsPageControlPanel.getResetViewBtn().addActionListener(this);
//        companyStatsPageControlPanel.getLoadGraph1Btn().addActionListener(this);

        super.setLayout(new BorderLayout());
        super.add(graphPanel, BorderLayout.CENTER);
        super.add(companyStatsPageControlPanel, BorderLayout.EAST);
    }
}
