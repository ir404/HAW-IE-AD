package drawingTool;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;

/**
 * Manages all drawable elements, view offsets, and drawing logic.
 * Draws axes, markers, and all Graph objects.
 */
public class Scene {
    private int width, height;
    private ArrayList<Graph> graphs;

    // View state
    private int viewOffsetX = 50;
    private int viewOffsetY = 200;

    // Scale: pixels per data unit (e.g., 10 pixels per day)
    private final double scaleX = 10.0;
    private final double scaleY = 10.0;

    // --- Constants for axis drawing ---
    private static final Color AXIS_COLOR = Color.BLACK;
    private static final Color MARKER_COLOR = Color.DARK_GRAY;
    private static final Font MARKER_FONT = new Font("Arial", Font.PLAIN, 10);
    private static final int X_AXIS_MARKER_INTERVAL_DAYS = 5; // Label every 5 days
    private static final int Y_AXIS_MARKER_INTERVAL_DEGREES = 2; // Label every 5 degrees
    private static final int TICK_MARK_LENGTH = 4; // 4 pixels on each side of the axis

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.graphs = new ArrayList<>();
    }

    public void addGraph(Graph graph) {
        this.graphs.add(graph);
    }

    public void scroll(int dx, int dy) {
        viewOffsetX += dx;
        viewOffsetY += dy;
    }

    public void resetView() {
        viewOffsetX = 50;
        viewOffsetY = 200;
    }

    /**
     * Main drawing method.
     * Calls helper methods to draw axes/markers and then the graphs.
     */
    public void draw() {
        // The "origin" (0,0) of the graph in pixel coordinates.
        // We use height/2 as the base Y-origin, as in your original code.
        int yAxisOriginBase = height / 2;

        // Calculate the *current* scrolled pixel position of the axes
        int horizontalAxisY_scrolled = yAxisOriginBase + viewOffsetY; // Pixel Y-coord of the X-axis (0°C line)
        int verticalAxisX_scrolled = viewOffsetX;                 // Pixel X-coord of the Y-axis (Day 0 line)

        // Draw axes and markers first (so they are behind the graphs)
        drawAxesAndMarkers(horizontalAxisY_scrolled, verticalAxisX_scrolled);

        // Draw the graphs on top
        // Graphs need the *base* origin, as they also receive the offsets separately.
        drawGraphs(yAxisOriginBase);
    }

    private void drawAxesAndMarkers(int horizontalAxisY, int verticalAxisX) {
        Drawing.pen().setColor(AXIS_COLOR);

        // --- 1. Draw Main Axis Lines ---
        Drawing.pen().drawLine(verticalAxisX, 0, verticalAxisX, height); // Y-Axis
        Drawing.pen().drawLine(0, horizontalAxisY, width, horizontalAxisY); // X-Axis

        // --- 2. Draw Origin Labels (from your code) ---
        Drawing.pen().drawString("Day 0", verticalAxisX + 5, horizontalAxisY + 15);
        Drawing.pen().drawString("0°C", verticalAxisX - 30, horizontalAxisY + 5);

        // --- 3. Set up for markers ---
        Drawing.pen().setColor(MARKER_COLOR);
        Drawing.pen().setFont(MARKER_FONT);
        FontMetrics fm = Drawing.pen().getFontMetrics();

        // --- 4. Draw X-Axis Markers (Days) ---
        // Find the data value (day) at the left edge of the screen
        double minDay = -verticalAxisX / scaleX;
        // Find the first multiple of our interval that is on-screen
        int startDay = (int)Math.ceil(minDay / X_AXIS_MARKER_INTERVAL_DAYS) * X_AXIS_MARKER_INTERVAL_DAYS;

        for (int day = startDay; ; day += X_AXIS_MARKER_INTERVAL_DAYS) {
            int markerX = verticalAxisX + (int)(day * scaleX);

            if (markerX > width) {
                break; // Stop when we go off the right side
            }

            if (day == 0) {
                continue; // Skip 0, it's already labelled
            }

            // Draw tick mark
            Drawing.pen().drawLine(markerX, horizontalAxisY - TICK_MARK_LENGTH, markerX, horizontalAxisY + TICK_MARK_LENGTH);

            // Draw label, centered under the tick
            String label = Integer.toString(day);
            int labelWidth = fm.stringWidth(label);
            Drawing.pen().drawString(label, markerX - (labelWidth / 2), horizontalAxisY + TICK_MARK_LENGTH + fm.getAscent());
        }

        // --- 5. Draw Y-Axis Markers (Temperature °C) ---
        // Find the data value (temp) at the bottom edge of the screen
        double minTemp = (horizontalAxisY - height) / scaleY;
        // Find the first multiple of our interval that is on-screen
        int startTemp = (int)Math.ceil(minTemp / Y_AXIS_MARKER_INTERVAL_DEGREES) * Y_AXIS_MARKER_INTERVAL_DEGREES;

        for (int temp = startTemp; ; temp += Y_AXIS_MARKER_INTERVAL_DEGREES) {
            int markerY = horizontalAxisY - (int)(temp * scaleY);

            if (markerY < 0) {
                break; // Stop when we go off the top
            }

            if (temp == 0) {
                continue; // Skip 0, it's already labelled
            }

            // Draw tick mark
            Drawing.pen().drawLine(verticalAxisX - TICK_MARK_LENGTH, markerY, verticalAxisX + TICK_MARK_LENGTH, markerY);

            // Draw label, to the left of the tick
            String label = Integer.toString(temp);
            int labelWidth = fm.stringWidth(label);
            // Position text: (x = axis - tick - spacing - label width), (y = centered on tick)
            Drawing.pen().drawString(label, verticalAxisX - TICK_MARK_LENGTH - labelWidth - 5, markerY + (fm.getAscent() / 2) - 2);
        }
    }

    /**
     * Calls the draw method for each graph in the list.
     * @param yAxisOriginBase The non-scrolled Y-pixel coordinate for 0°C (e.g., height / 2).
     */
    private void drawGraphs(int yAxisOriginBase) {
        for (Graph graph : graphs) {
            graph.draw(viewOffsetX, viewOffsetY, scaleX, scaleY, yAxisOriginBase);
        }
    }
}

