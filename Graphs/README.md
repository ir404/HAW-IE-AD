# Graph Data Structure Implementation

This project implements a **Directed Weighted Graph** in Java using the **Adjacency List** representation. It is designed to be generic, allowing vertices to be represented by any data type (e.g., `String` for city names, `Integer` for IDs), while internally managing connections efficiently using integer indices.

## 📂 Project Structure

The project consists of three core classes within the `myGraph` package:

### 1. `Graph<T>`
The main class representing the graph.
* **Generic Design:** Uses a `HashMap` to map generic vertex labels (of type `T`) to internal integer IDs, separating the user-facing data from the internal logic.
* **Adjacency List:** Stores connections as a list of `AdjacencyList` objects, where each list corresponds to a vertex.
* **Pathfinding:** Implements a **Depth-First Search (DFS)** algorithm to calculate the distance between two nodes. It includes cycle detection using a `visited` list to prevent infinite loops.
* **Error Handling:** Safely handles operations on non-existent vertices to prevent runtime crashes.

### 2. `AdjacencyList`
Represents the collection of edges for a specific vertex.
* **Linked List:** Internally uses a `LinkedList<Node>` to store neighbors, which is efficient for sparse graphs.
* **Iterator Pattern:** Implements `Iterable<Node>`, allowing external classes to iterate over neighbors without exposing the underlying list structure.
* **Encapsulation:** Hides the internal list implementation while providing methods to add edges and check for emptiness.

### 3. `Node`
Represents a directed edge to a neighboring vertex.
* **Components:** Stores the `destination` (vertex ID) and the `weight` (distance or cost) of the connection.
* **Purpose:** Acts as the building block for the adjacency list, defining both *where* a connection goes and *how much* it costs.

## 🚀 Features

* **Add Vertices:** Explicitly add vertices of any type (e.g., `"Hamburg"`, `"Berlin"`).
* **Add Edges:** Create directed, weighted connections between vertices.
* **Distance Calculation:** Calculate the total weight of a path between two vertices using DFS.
* **Cycle Handling:** Logic prevents infinite loops in cyclic graphs (e.g., A -> B -> A).
* **Graph Visualization:** A `printAdjacencyLists` method displays the graph structure in a readable format.

## 🛠️ Usage Example

```java
public class Main {
    public static void main(String[] args) {
        // Initialize a graph with String labels
        Graph<String> cityMap = new Graph<>();

        // 1. Add Vertices
        cityMap.addVertex("Hamburg");
        cityMap.addVertex("Berlin");
        cityMap.addVertex("Munich");

        // 2. Add Weighted Edges
        cityMap.addEdge("Hamburg", "Berlin", 70);
        cityMap.addEdge("Berlin", "Munich", 150);

        // 3. Calculate Distance
        // Finds path: Hamburg -> Berlin -> Munich (70 + 150 = 220)
        int distance = cityMap.getDistance("Hamburg", "Munich");
        System.out.println("Distance: " + distance); 
        
        // 4. Print Graph Structure
        cityMap.printAdjacencyLists();
    }
}