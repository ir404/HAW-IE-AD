package graphs;

import drawingTool.Drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class ScatterPlot extends Graph {
    private final int MARKER_DIAMETER = 8;
    private final int X_GAP = 30;
    private final int Y_GAP = 40;
    private final int TICK_LENGTH = 3;
    private final int LABEL_PADDING = 5;
    private final int AXIS_LABEL_OFFSET = 2;
    private ArrayList<Point> points;

    private final String xAxisLabel;
    private final String yAxisLabel;

    public ScatterPlot(Color dotColour, ArrayList<Point> points, String xAxisLabel, String yAxisLabel) {
        super(dotColour);
        this.points = points;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
    }

    @Override
    public void draw(int viewOffsetX, int viewOffsetY, double scaleX, double scaleY, int originX, int originY) {
        Drawing.pen().setColor(Color.BLACK);
        FontMetrics fm = Drawing.pen().getFontMetrics();

        // --- Draw Y-Axis Ticks and Labels ---
        for (int yValue = 0; yValue <= 15; yValue++) {
            int yPos = originY - (Y_GAP * yValue);
            Drawing.pen().drawLine(originX - TICK_LENGTH, yPos, originX, yPos);

            String tickValue = Integer.toString(yValue);
            int tickValueWidth = fm.stringWidth(tickValue);
            int tickValueX = originX - tickValueWidth - LABEL_PADDING;
            int tickValueY = yPos + (fm.getAscent() / 2) - fm.getDescent();
            Drawing.pen().drawString(tickValue, tickValueX, tickValueY);
        }

        // --- Draw Points, X-Axis Ticks and Labels ---
        for (Point point: points) {
            int centerX = originX + (X_GAP * point.getX());
            int centerY = originY - (Y_GAP * point.getY());

            // Calculate the top-left corner for drawing the oval marker
            int circleX = centerX - (MARKER_DIAMETER / 2);
            int circleY = centerY - (MARKER_DIAMETER / 2);
            Drawing.pen().setColor(super.getColour());
            Drawing.pen().fillOval(circleX, circleY, MARKER_DIAMETER, MARKER_DIAMETER);

            // Draw the tick using the CENTRE coordinates
            Drawing.pen().setColor(Color.BLACK);
            Drawing.pen().drawLine(centerX, originY, centerX, originY + TICK_LENGTH);  // draw tick

            // --- Calculate X-Axis Tick Label Position ---
            String tickValue = Integer.toString(point.getX());
            int tickValueWidth = fm.stringWidth(tickValue);
            int tickValueX = centerX - (tickValueWidth / 2); // Horizontally centred
            int tickValueY = originY + TICK_LENGTH + LABEL_PADDING + fm.getAscent();
            Drawing.pen().drawString(tickValue, tickValueX, tickValueY);
        }

        drawXAxisLabel(originX, originY, fm);
        drawYAxisLabel(originX, originY, fm);
    }

    private void drawXAxisLabel(int originX, int originY, FontMetrics fm) {
        int xAxisTargetX = originX + (X_GAP * AXIS_LABEL_OFFSET);
        int xAxisLabelY = originY + TICK_LENGTH + LABEL_PADDING + fm.getAscent() + fm.getHeight();
        int labelWidth = fm.stringWidth(xAxisLabel);
        Drawing.pen().drawString(xAxisLabel, xAxisTargetX - (labelWidth / 2), xAxisLabelY);
    }

    private void drawYAxisLabel(int originX, int originY, FontMetrics fm) {
        Graphics2D g2d = (Graphics2D) Drawing.pen();
        int yAxisTargetY = originY - (Y_GAP * AXIS_LABEL_OFFSET);
        int xPivot = originX - fm.stringWidth("15") - (LABEL_PADDING * 5);
        int yPivot = yAxisTargetY;
        int labelWidth = fm.stringWidth(yAxisLabel);
        int labelHeight = fm.getAscent();
        AffineTransform originalTransform = g2d.getTransform();

        g2d.translate(xPivot, yPivot);
        g2d.rotate(-Math.PI / 2);           // -90 degrees in radians
        g2d.drawString(yAxisLabel, -(labelWidth / 2), labelHeight / 2);

        g2d.setTransform(originalTransform);
    }
}