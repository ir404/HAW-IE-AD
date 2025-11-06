package panels;

import drawingTool.Drawing;
import graphs.Graph;

import java.awt.*;
import java.util.ArrayList;

public class Scene {
    private ArrayList<Graph> graphs;

    private final double SCALE_X = 10.0;
    private final double SCALE_Y = 10.0;
    private final int SCROLL_X = 20;
    private final int SCROLL_Y = 20;
    private final Color AXIS_COLOUR = Color.BLACK;

    private int defaultViewOffsetX, defaultViewOffsetY;
    private int width, height, viewOffsetX, viewOffsetY;

    public Scene(int width, int height, int defaultViewOffsetX, int defaultViewOffsetY) {
        this.width = width;
        this.height = height;
        this.graphs = new ArrayList<>();
        this.defaultViewOffsetX = defaultViewOffsetX;
        this.defaultViewOffsetY = defaultViewOffsetY;
        viewOffsetX = defaultViewOffsetX;
        viewOffsetY = defaultViewOffsetY;
    }

    public void addGraph(Graph graph) {
        this.graphs.add(graph);
    }

    public void scrollRight() {
        viewOffsetX -= SCROLL_X;
    }

    public void scrollLeft() {
        viewOffsetX += SCROLL_X;
    }

    public void scrollUp() {
        viewOffsetY += SCROLL_Y;
    }

    public void scrollDown() {
        viewOffsetY -= SCROLL_Y;
    }

    public void resetView() {
        viewOffsetX = defaultViewOffsetX;
        viewOffsetY = defaultViewOffsetY;
    }

    public void draw() {
        drawAxesAndMarkers();
    }

    private void drawAxesAndMarkers() {
        int originX = (width / 2) + viewOffsetX;
        int originY = (height / 2) + viewOffsetY;
        Drawing.pen().setColor(AXIS_COLOUR);
        Drawing.pen().drawLine(originX, 0, originX, height);
        Drawing.pen().drawLine(0, originY, width, originY);

        // TODO: draw markers on both axis

        drawGraphs(originX, originY);
    }

    private void drawGraphs(int originX, int originY) {
        for (Graph graph : graphs) {
            graph.draw(viewOffsetX, viewOffsetY, SCALE_X, SCALE_Y, originX, originY);
        }
    }
}

