package panels;

import drawingTool.AppFrame;
import panels.pages.PageName;

import javax.swing.*;
import java.awt.*;

public abstract class ControlPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    protected final Color CONTROL_PANEL_COLOUR = Color.LIGHT_GRAY;
    protected final int TITLE_FONT_SIZE = 14;
    protected final int BODY_FONT_SIZE = 12;

    private JButton backBtn;
    private JButton resetViewBtn;

    public ControlPanel(AppFrame appFrame) {
        super.setBackground(CONTROL_PANEL_COLOUR);
        super.setLayout(new GridBagLayout());

        resetViewBtn = new JButton("Reset View [R]");
        backBtn = new JButton("Back to menu");
        backBtn.addActionListener(e -> appFrame.showPage(PageName.HOME_PAGE));

        initialiseDefaultComponents();
    }

    public JButton getResetViewBtn() {
        return resetViewBtn;
    }

    private void initialiseDefaultComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 8, 5, 8);

        addControls(gbc);

        gbc.gridy = 90;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel spacer = new JPanel();
        spacer.setBackground(CONTROL_PANEL_COLOUR);
        add(spacer, gbc);

        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 8, 5, 8);

        gbc.gridy = 91;
        add(resetViewBtn, gbc);

        gbc.gridy = 92;
        gbc.insets = new Insets(5, 8, 50, 8);
        add(backBtn, gbc);
    }

    protected abstract void addControls(GridBagConstraints gbc);
}