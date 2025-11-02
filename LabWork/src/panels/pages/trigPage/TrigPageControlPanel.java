package panels.pages.trigPage;

import drawingTool.AppFrame;
import panels.ControlPanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class TrigPageControlPanel extends ControlPanel {
    @Serial
    private static final long serialVersionUID = 1L;

    private JLabel titleLabel, graph1Label, graph2Label;
    private JButton loadGraph1Btn, loadGraph2Btn;

    public TrigPageControlPanel(AppFrame appFrame) {
        super(appFrame);
    }

    @Override
    protected void addControls(GridBagConstraints gbc) {
        titleLabel = new JLabel("Trig Function Controls");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, TITLE_FONT_SIZE));

        graph1Label = new JLabel("LineGraph 1 (Red)");
        graph1Label.setFont(new Font(graph1Label.getFont().getName(), Font.BOLD, BODY_FONT_SIZE));
        loadGraph1Btn = new JButton("Generate");

        graph2Label = new JLabel("LineGraph 2 (Blue)");
        graph2Label.setFont(new Font(graph2Label.getFont().getName(), Font.BOLD, BODY_FONT_SIZE));
        loadGraph2Btn = new JButton("Generate");

        Insets titleInsets = new Insets(15, 8, 15, 8);
        Insets defaultInsets = new Insets(5, 8, 5, 8);
        Insets sectionTopInsets = new Insets(15, 8, 5, 8);

        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = titleInsets;
        add(titleLabel, gbc);

        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridy = 1;
        gbc.insets = defaultInsets;
        add(graph1Label, gbc);

        gbc.gridy = 2;
        gbc.insets = defaultInsets;
        add(loadGraph1Btn, gbc);

        gbc.gridy = 3;
        gbc.insets = sectionTopInsets;
        add(graph2Label, gbc);

        gbc.gridy = 4;
        gbc.insets = defaultInsets;
        add(loadGraph2Btn, gbc);
    }

    public JButton getLoadGraph1Btn() {
        return loadGraph1Btn;
    }

    public JButton getLoadGraph2Btn() {
        return loadGraph2Btn;
    }
}