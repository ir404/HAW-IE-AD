package graphs;

import drawingTool.Drawing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class LineGraph extends Graph {
    private ArrayList<Double> dataPoints;
    private double amplitude;
    private double frequency;
    private double phase;

    public LineGraph(Color colour) {
        super(colour);
        this.dataPoints = new ArrayList<>();

        Random rand = new Random();
        this.amplitude = 10.0 + (rand.nextDouble() * 30.0);
        this.frequency = 0.1 + (rand.nextDouble() * 0.75);
        this.phase = rand.nextDouble() * Math.PI * 2;
    }

    public void generateData(int numPoints) {
        dataPoints.clear();
        for (int x = 0; x < numPoints; x++) {
            double y = amplitude * Math.sin(frequency * x + phase);
            dataPoints.add(y);
        }
    }

    /**
     * Draws the line graph by connecting all its data points.
     * This method correctly translates data coordinates (e.g., 5, 20.5)
     * into pixel coordinates, accounting for scale and scrolling.
     */
    @Override
    public void draw(int viewOffsetX, int viewOffsetY, double scaleX, double scaleY, int originX, int originY) {
        if (dataPoints.size() > 2) {
            Drawing.pen().setColor(getColour());
            int xOffset = dataPoints.size() / 2;
            for (int i = 0; i < dataPoints.size() - 1; i++) {
                // --- Point 1 (Current point) ---
                // Data coordinates
                double x_data1 = i - xOffset;
                double y_data1 = dataPoints.get(i);

                // Pixel coordinates
                int x1 = originX + (int) (x_data1 * scaleX);
                int y1 = originY - (int) (y_data1 * scaleY); // Y is inverted in graphics

                // --- Point 2 (Next point) ---
                // Data coordinates
                double x_data2 = (i + 1) - xOffset;
                double y_data2 = dataPoints.get(i + 1);

                // Pixel coordinates
                int x2 = originX + (int) (x_data2 * scaleX);
                int y2 = originY - (int) (y_data2 * scaleY);

                // Draw the line segment
                Drawing.pen().drawLine(x1, y1, x2, y2);
            }
        }
    }
}
