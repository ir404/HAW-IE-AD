package drawingTool;

import panels.pages.HomePage;
import panels.pages.companyStatsPage.CompanyStatsPage;
import panels.pages.trigPage.TrigPage;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private HomePage homePage;
    private TrigPage trigPage;
    private CompanyStatsPage companyStatsPage;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public static final String MENU_PAGE = "HomePage";
    public static final String TRIG_PAGE = "TrigPage";
    public static final String STATS_PAGE = "StatsPage";

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
        mainPanel.add(homePage, MENU_PAGE);
        mainPanel.add(trigPage, TRIG_PAGE);
        mainPanel.add(companyStatsPage, STATS_PAGE);

        super.add(mainPanel);
        super.setTitle(title);
        super.setBounds(framePlacementX, framePlacementY, frameWidth, frameHeight);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }
}