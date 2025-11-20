package drawingTool;

import panels.pages.HomePage;
import panels.pages.gradesPage.GradesPages;
import panels.pages.studentGradesPage.StudentGradesPage;
import panels.pages.trigPage.TrigPage;
import panels.pages.PageName;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private HomePage homePage;
    private TrigPage trigPage;
    private GradesPages gradesPages;
    private StudentGradesPage studentGradesPage;
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
        gradesPages = new GradesPages(this, frameWidth, frameHeight);
        studentGradesPage = new StudentGradesPage(this, frameWidth, frameHeight);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(homePage, PageName.HOME_PAGE.getName());
        mainPanel.add(trigPage, PageName.TRIG_PAGE.getName());
        mainPanel.add(gradesPages, PageName.GRADES_PAGE.getName());
        mainPanel.add(studentGradesPage, PageName.STUDENT_GRADES_PAGE.getName());

        super.add(mainPanel);
        super.setTitle(title);
        super.setBounds(framePlacementX, framePlacementY, frameWidth, frameHeight);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showPage(PageName pageName) {
        cardLayout.show(mainPanel, pageName.getName());
    }
}