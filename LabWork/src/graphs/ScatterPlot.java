package graphs;

import drawingTool.Drawing;

import java.awt.*;
import java.util.ArrayList;

public class ScatterPlot extends Graph {
    private final int MARKER_DIAMETER = 8;
    private final int GAP = 40;
    private final int TICK_LENGTH = 3;
    private final int LABEL_PADDING = 5;
    private ArrayList<Point> points;

    public ScatterPlot(Color dotColour, ArrayList<Point> points) {
        super(dotColour);
        this.points = points;
    }

    @Override
    public void draw(int viewOffsetX, int viewOffsetY, double scaleX, double scaleY, int originX, int originY) {
        Drawing.pen().setColor(Color.BLACK);
        FontMetrics fm = Drawing.pen().getFontMetrics();

        // --- Draw Y-Axis Ticks and Labels ---
        for (int yValue = 0; yValue <= 15; yValue++) {
            int yPos = originY - (GAP * yValue);
            Drawing.pen().drawLine(originX - TICK_LENGTH, yPos, originX, yPos);

            String label = Integer.toString(yValue);
            int labelWidth = fm.stringWidth(label);
            int labelX = originX - labelWidth - LABEL_PADDING;
            int labelY = yPos + (fm.getAscent() / 2) - fm.getDescent();
            Drawing.pen().drawString(label, labelX, labelY);
        }

        // --- Draw Points, X-Axis Ticks and Labels ---
        for (Point point: points) {
            int centerX = originX + (GAP * point.getX());
            int centerY = originY - (GAP * point.getY());

            // Calculate the top-left corner for drawing the oval marker
            int circleX = centerX - (MARKER_DIAMETER / 2);
            int circleY = centerY - (MARKER_DIAMETER / 2);
            Drawing.pen().setColor(super.getColour());
            Drawing.pen().fillOval(circleX, circleY, MARKER_DIAMETER, MARKER_DIAMETER);

            // Draw the tick using the CENTRE coordinates
            Drawing.pen().setColor(Color.BLACK);
            Drawing.pen().drawLine(centerX, originY, centerX, originY + TICK_LENGTH);  // draw tick

            // --- Calculate X-Axis Label Position ---
            String label = Integer.toString(point.getX());
            int labelWidth = fm.stringWidth(label);
            int labelX = centerX - (labelWidth / 2); // Horizontally centred
            int labelY = originY + TICK_LENGTH + LABEL_PADDING + fm.getAscent();
            Drawing.pen().drawString(label, labelX, labelY);
        }
    }
}
