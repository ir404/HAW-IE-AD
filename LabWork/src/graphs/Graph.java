package graphs;

import java.awt.*;

public abstract class Graph {
    private Color colour;

    public Graph(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }

    public abstract void draw(int viewOffsetX, int viewOffsetY, double scaleX, double scaleY, int originX, int originY);
}
