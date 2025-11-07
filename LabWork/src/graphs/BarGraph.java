package graphs;

import drawingTool.Drawing;

import java.awt.*;

public class BarGraph extends Graph {
    private final int BAR_HEIGHT_PER_UNIT = 30;
    private final int BAR_DATA_WIDTH = 20;
    private final int BAR_DATA_GAP = 10;

    private int[] xAxisValues;
    private int[] yAxisValues;

    public BarGraph(Color colour, int[] xAxisValues, int[] yAxisValues) {
        super(colour);
        this.xAxisValues = xAxisValues;
        this.yAxisValues = yAxisValues;
    }

    @Override
    public void draw(int viewOffsetX, int viewOffsetY, double scaleX, double scaleY, int originX, int originY) {
        scaleX = 2.0;
        scaleY = 2.0;

        Drawing.pen().setColor(super.getColour());
        for (int i = 0; i < yAxisValues.length; i++) {
            int category = xAxisValues[i];
            int frequency = yAxisValues[i];
            int barHeight = (int) (frequency * BAR_HEIGHT_PER_UNIT * scaleY);
            int barWidth = (int) (BAR_DATA_WIDTH * scaleX);
            int totalBarSpace = (int) ((BAR_DATA_WIDTH + BAR_DATA_GAP) * scaleX);
            int barX = originX + (category * totalBarSpace);
            int barTopY = originY - barHeight;

            if (frequency > 0) {
                Drawing.pen().fillRect(barX, barTopY, barWidth, barHeight);
            }
        }
    }
}