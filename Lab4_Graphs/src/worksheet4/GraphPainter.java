/*
 * B-REE3-VSP HAW Hamburg
 *
 * Created on : 10-12-2020
 * Author     : Björn Gottfried
 *
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 *-----------------------------------------------------------------------------
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 * OLD/NEW     DATE                RFC NO
 *-----------------------------------------------------------------------------
 * --/1.0  | B. Gottfried  | Initial Create.
 *         | 10-12-20      |
 *---------|---------------|---------------------------------------------------
 *         | author        |
 *         | dd-mm-yy      |
 *---------|---------------|---------------------------------------------------
 */

package worksheet4;

import javax.swing.JFrame;

public class GraphPainter {
    
    private Visualisation visualisation;

    public GraphPainter(Graph aGraph, AdjacencyList aPath ) {
        visualisation = new Visualisation(aGraph, aPath);
        
        JFrame frame = new JFrame("A Graph");
        frame.add(visualisation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    /********************************************************************************
     * Test program
     ********************************************************************************/

    public static void main(String[] args) {
        final int H = 0;
        int[][] adjMatrix = {
            {H, 0, 0, 1, 1, 0, 0, 0},           // means vertex 0 has two neighbours, 3 and 4
            {0, H, 1, 0, 0, 0, 1, 0},
            {0, 0, H, 0, 0, 0, 0, 1},
            {0, 0, 0, H, 1, 0, 0, 1},
            {0, 0, 0, 0, H, 1, 0, 1},
            {0, 0, 0, 0, 0, H, 1, 1},
            {0, 0, 0, 0, 0, 0, H, 1},
            {0, 0, 1, 1, 0, 0, 0, H}
        };
        int[][] emptyMatrix = {
            {H, 0, 0, 0, 0, 0, 0, 0},
            {0, H, 0, 0, 0, 0, 0, 0},
            {0, 0, H, 0, 0, 0, 0, 0},
            {0, 0, 0, H, 0, 0, 0, 0},
            {0, 0, 0, 0, H, 0, 0, 0},
            {0, 0, 0, 0, 0, H, 0, 0},
            {0, 0, 0, 0, 0, 0, H, 0},
            {0, 0, 0, 0, 0, 0, 0, H}
        };
        int[][] weightedMatrix = {
            {H, 0, 0, 2, 4, 0, 0, 0},           // means vertex 0 has two neighbours, 3 and 4
            {0, H, 2, 0, 0, 0, 6, 0},
            {0, 0, H, 0, 0, 0, 0, 2},
            {0, 0, 0, H, 4, 0, 0, 2},
            {0, 0, 0, 0, H, 8, 0, 4},
            {0, 0, 0, 0, 0, H, 2, 2},
            {0, 0, 0, 0, 0, 0, H, 4},
            {0, 0, 2, 2, 0, 0, 0, H}
        };

        Graph g = new Graph(adjMatrix);
        AdjacencyList aPath = g.somePath(0, 5);
        
        // Print all the vertices and their neighbours
        for (int v = 0; v < g.numOfVertices(); v++) {
            System.out.print("Neighbours of vertex " + v + ":");
            AdjacencyList adjList = g.getNeighboursFor(v);
            for (Integer neighbour: adjList) {
                System.out.print(" " + neighbour.toString());
            }
            System.out.println();
        }
        System.out.println("Number of edges: " + g.numOfEdges());


        for (Integer i : aPath) {
            System.out.println("Path i = " + i);
        }
        
        new GraphPainter(g, aPath);
    }
}

