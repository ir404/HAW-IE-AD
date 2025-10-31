package panels;

import drawingTool.Drawing;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int width, height;
    private Scene scene;

    public GraphPanel(int width, int height) {
        this.width = width;
        this.height = height;
        super.setPreferredSize(new Dimension(this.width, this.height));
        scene = new Scene(this.width, this.height);
    }

    public Scene getScene() {
        return scene;
    }


    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        Drawing.set(pen);
        scene.draw();
    }
}
