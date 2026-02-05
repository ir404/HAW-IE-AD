package test;

import myGraph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph<String> cityMap = new Graph<>();
        cityMap.addVertex("Hamburg");
        cityMap.addVertex("Berlin");
        cityMap.addVertex("Dortmund");
        cityMap.addVertex("Munich");
        cityMap.addVertex("Stuttgart");

        // Hamburg (0) connects to Berlin (1) and Dortmund (2)
        cityMap.addEdge("Hamburg", "Berlin", 70);      // Hamburg -> Berlin (70km)
        cityMap.addEdge("Hamburg", "Dortmund", 100);     // Hamburg -> Dortmund (100km)

        // Berlin (1) connects only to Munich (3)
        cityMap.addEdge("Berlin", "Munich", 150);     // Berlin -> Munich (150km)

        // Dortmund (2) has NO outgoing arrows

        // Munich (3) connects to Hamburg (0) and Stuttgart (4)
        cityMap.addEdge("Munich", "Hamburg", 200);     // Munich -> Hamburg (200km)
        cityMap.addEdge("Munich", "Stuttgart", 225);     // Munich -> Stuttgart (225km)

        // Stuttgart (4) connects to Dortmund (2)
        cityMap.addEdge("Stuttgart", "Dortmund", 125);     // Stuttgart -> Dortmund (125km)

        System.out.println("Neighbours of each vertex:");
        System.out.println("--------------------------");
        cityMap.printAdjacencyLists();

        System.out.println("\n");

        String start = "Hamburg", end = "Stuttgart";
        int distance = cityMap.getDistance(start, end);
        System.out.println("Path from " + start + " to " + end + ": " + distance);
    }
}
