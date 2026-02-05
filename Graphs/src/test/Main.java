package test;

import myGraph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph cityMap = new Graph(5);
        cityMap.setVertexName(0, "Hamburg");
        cityMap.setVertexName(1, "Berlin");
        cityMap.setVertexName(2, "Dortmund");
        cityMap.setVertexName(3, "Munich");
        cityMap.setVertexName(4, "Stuttgart");

        // Hamburg (0) connects to Berlin (1) and Dortmund (2)
        cityMap.addEdge(0, 1, 70);      // Hamburg -> Berlin (70km)
        cityMap.addEdge(0, 2, 100);     // Hamburg -> Dortmund (100km)

        // Berlin (1) connects only to Munich (3)
        cityMap.addEdge(1, 3, 150);     // Berlin -> Munich (150km)

        // Dortmund (2) has NO outgoing arrows

        // Munich (3) connects to Hamburg (0) and Stuttgart (4)
        cityMap.addEdge(3, 0, 200);     // Munich -> Hamburg (200km)
        cityMap.addEdge(3, 4, 225);     // Munich -> Stuttgart (225km)

        // Stuttgart (4) connects to Dortmund (2)
        cityMap.addEdge(4, 2, 125);     // Stuttgart -> Dortmund (125km)

        System.out.println("Neighbours of each vertex:");
        System.out.println("--------------------------");
        cityMap.printAdjacencyLists();

        System.out.println("\n");

        int start = 0, end = 4;
        int distance = cityMap.getDistance(start, end);
        System.out.println("Path from " + cityMap.getVertexName(start) + " to " + cityMap.getVertexName(end) + ": " + distance);
    }
}
