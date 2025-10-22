package drawingTool;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    private ArrayList<Double> dataPoints;
    private Color colour;

    public Graph(Color colour) {
        this.colour = colour;
        this.dataPoints = new ArrayList<>();
    }

    public void loadDataFromFile(String filename) {
        dataPoints.clear();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                try {
                    String line = scanner.nextLine();
                    if (!line.trim().isEmpty()) {
                        dataPoints.add(Double.parseDouble(line));
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Skipping non-numeric line: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Could not find data file: " + filename);
            e.printStackTrace();
        }
    }

    public void draw(int viewOffsetX, int viewOffsetY, double scaleX, double scaleY, int originY) {
        if (dataPoints.size() > 2) {
            Drawing.pen().setColor(this.colour);

            for (int i = 0; i < dataPoints.size() - 1; i++) {
                int x1 = (int) (i * scaleX) + viewOffsetX;
                int y1 = originY - (int) (dataPoints.get(i) * scaleY) + viewOffsetY;

                int x2 = (int) ((i + 1) * scaleX) + viewOffsetX;
                int y2 = originY - (int) (dataPoints.get(i + 1) * scaleY) + viewOffsetY;

                Drawing.pen().drawLine(x1, y1, x2, y2);
            }
        }
    }
}
