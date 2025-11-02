package drawingTool;

import panels.pages.HomePage;
import panels.pages.companyStatsPage.CompanyStatsPage;
import panels.pages.trigPage.TrigPage;
import util.PageName;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private HomePage homePage;
    private TrigPage trigPage;
    private CompanyStatsPage companyStatsPage;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public AppFrame(String title) {
        int screenWidth = getToolkit().getScreenSize().width;
        int screenHeight = getToolkit().getScreenSize().height;
        int frameWidth = (int) (screenWidth * 0.95);
        int frameHeight = (int) (screenHeight * 0.95);
        int framePlacementX = (int) (screenWidth * 0.5 - frameWidth * 0.5);
        int framePlacementY = (int) (screenHeight * 0.5 - frameHeight * 0.5);

        homePage = new HomePage(this);
        trigPage = new TrigPage(this, frameWidth, frameHeight);
        companyStatsPage = new CompanyStatsPage(this, frameWidth, frameHeight);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(homePage, PageName.HOME_PAGE.getValue());
        mainPanel.add(trigPage, PageName.TRIG_PAGE.getValue());
        mainPanel.add(companyStatsPage, PageName.COMPANY_STATS_PAGE.getValue());

        super.add(mainPanel);
        super.setTitle(title);
        super.setBounds(framePlacementX, framePlacementY, frameWidth, frameHeight);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showPage(PageName pageName) {
        cardLayout.show(mainPanel, pageName.getValue());
    }
}