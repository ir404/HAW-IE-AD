package panels.pages.gradesPage;

import drawingTool.AppFrame;
import panels.ControlPanel;

import javax.swing.*;
import java.awt.*;

public class GradesPageControlPanel extends ControlPanel {
    private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
    private JButton showBtn;

    public GradesPageControlPanel(AppFrame appFrame) {
        super(appFrame);
    }

    public JButton getShowBtn() {
        return showBtn;
    }

    @Override
    protected void addControls(GridBagConstraints gbc) {
        titleLabel = new JLabel("Grades for Math");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, TITLE_FONT_SIZE));

        showBtn = new JButton("Show");

        Insets titleInsets = new Insets(15, 8, 15, 8);
        Insets defaultInsets = new Insets(5, 8, 5, 8);
        
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = titleInsets;
        add(titleLabel, gbc);

        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridy = 3;
        gbc.insets = defaultInsets;
        add(showBtn, gbc);
    }
}
