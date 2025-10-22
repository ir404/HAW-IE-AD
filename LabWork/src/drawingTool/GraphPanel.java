package drawingTool;

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

    /**
     * Overridden paintComponent. This is the main drawing entry point.
     * It sets the static 'pen' (as per your structure) and then
     * delegates all drawing logic to the Scene object.
     */
    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        Drawing.set(pen); // Set the static pen

        // Delegate all drawing to the scene
        if (scene != null) {
            scene.draw();
        }
    }
}
