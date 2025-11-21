package panels;

import drawingTool.Drawing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GraphPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int width, height;
    private Scene scene;

    public GraphPanel(int width, int height, int defaultViewOffsetX, int defaultViewOffsetY) {
        this.width = width;
        this.height = height;
        super.setPreferredSize(new Dimension(this.width, this.height));
        scene = new Scene(this.width + 100, this.height + 100, defaultViewOffsetX, defaultViewOffsetY);
        setupKeyBindings();
    }

    public Scene getScene() {
        return scene;
    }

    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        Drawing.set(pen);
        scene.draw();
    }

    private void setupKeyBindings() {
        addKeyBinding(this, KeyEvent.VK_UP, "upReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getScene().scrollUp();
                repaint();
            }
        });
        addKeyBinding(this, KeyEvent.VK_DOWN, "downReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getScene().scrollDown();
                repaint();
            }
        });
        addKeyBinding(this, KeyEvent.VK_LEFT, "leftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getScene().scrollLeft();
                repaint();
            }
        });
        addKeyBinding(this, KeyEvent.VK_RIGHT, "rightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getScene().scrollRight();
                repaint();
            }
        });
        addKeyBinding(this, KeyEvent.VK_R, "rKeyReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getScene().resetView();
                repaint();
            }
        });
    }

    private void addKeyBinding(JComponent panel, int keyCode, String actionName, Action action) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, 0, true);
        inputMap.put(keyStroke, actionName);
        actionMap.put(actionName, action);
    }
}
