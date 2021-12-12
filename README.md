# Ex2 - Data Structures and Algorithms On Graphs
### 

<p align="center">
<img align="center" src="https://www.researchgate.net/profile/Edwin-Sha/publication/3923952/figure/fig3/AS:667702590963725@1536204111731/A-graph-with-nodes-and-edges.png" />
</p>

### @ Or Yitshak & Haim Goldfisher
---------
## 1. Introduction:

In this exercise, we are required to design and implement a variety of different algorithms on graphs (Directed and Weighted), which are represented using data structures.

Each **graph (MyDirectedWeightedGraph)** has Mode Count int param and two hash sets:

* <ins>**Mode Count (MC)**</ins> - Count the number of actions performed so far in the graph (initialized to 0).

* <ins>**Nodes**</ins> - Each node contains the following important parameters (not all are specified):
  * `key (int)`: (Called also ID) The name of the vertex (using an int number). Helps us access the desired vertex using its name. Inevitably the name of each vertex is unique and exclusive.
  * `location (x,y,z double)`: The location of the vertex in the three-dimensional space. Note that this task we are required for two-dimensional representation. Therefore the value of z will always be 0.
  * `parents_ids (Hashset)`: An Hashmap that represents all the parent keys of this vertex. Required for access to the parents.
  * `children_ids (Hashset)`: An Hashmap that represents all the child keys of this vertex. Required for access to the children.
  * `edges_to_children (Hashset)`: An Hashmap that represents the keys (src+dest) of all the edges that go from this vertex to child vertices.

* <ins>**Edges**</ins> - Each edge contains the following important parameters (not all are specified):
  * `src_key (int)`: The key of the vertex from which the edge emerges
  * `dest_key (int)`: The key of the vertex to which the edge reaches
  * `weight (double)`: Edge's weight. Also called Edge's length.

<ins>**The algorithms**</ins> we would like to implement at maximum efficiency:

* `Is Connected` - The purpose of this algorithm is very simple to understand. We would like to implement an efficient algorithm in oreder to understand
whether a given graph is strongly connected or not (strongly connected - there is a valid path from each node to each other node).
* `Shortest Path Distance` - Algorithm for finding the distance of shortest path between any 2 vertices in a graph.
* `Shortest Path` - Algorithm for finding the shortest path between any 2 vertices in a graph.
* `Center` - Algorithm for finding the most central vertex in a graph (can be more than one), but we can give one. Note that an unconnected graph does not contain a center.
* `Travelling Salesman Problem (TSP)` - An algorithm for finding the shortest path that passes through all the given vertices.

---------
## 2. The Thoughts Behind The Classes:

It is important to note that since most of the designings of the task are given to us, most of the planning is in the auxiliary functions for the implementation of the algorithms and less about the classes (except for the implementation of the GUI). Since we were initially given five interfaces that we implemented, we will build a class that matches each interface. Each class will implement all the necessary methods and functions. Next, additional methods and additional functions will be built within each class in order to help us solve the task. Note that the Diyxtra algorithm can be implemented to calculate the shortest path from vertex to vertex. We will implement it using a priority queue, if we have time left, we will improve it using a Fibonacci stack. We would also like to implement search algorithms (BFS or DFS) to implement a search on our graph. It can help us implement the Is Connceted algorithm. The work on the GUI interface is divided into several different classes. A class that represents the main menu and contains a second inner class that represents a text box that the user needs to fill. It is consumed because in some functions, there is a requirement to fill some input to enable it (edit, algorithms like TSP). The third represents the drawing of the graph. It includes vertices with their key number. Edges are marked along with an arrow to indicate where they are aimed. Also their weight is indicated. We will also complete the MAIN function EX2 so that we can run the program as one complete unit. Finally, after performing a meaningful and comprehensive test, we will know that all our functions work. At this point we will turn the project into a jar file.

---------
## 3. UML Diagram:

Since each class contains a lot of parameters, methods and functions, we will only present the names of the classes and the hierarchy between them:

<p align="center">
<img align="center" src="https://github.com/or-yitshak/OOP_Ex2/blob/main/diagram.png?raw=true" height=500 weight=1000/>
</p>

---------
## 4. Testing Class:

We have created a tests directory. The tests should be done in three ways. First of all we want to see that our functions work, both on valid inputs and on illegal inputs (exception throwing). This is critical because some of the algorithms and methods are performed through user input. Therefore we should expect the user to enter in the text box an incorrect input. Once we are sure that all the functions work and accept any input, we want to be sure that the output of all our algorithms is correct. This is a bit complicated because the test is manual. Given a particular graph with certain properties, we would like to make sure that the output of the algorithm matches the correct answer it is supposed to give. We will manually solve the algorithms and verify the correctness of the algorithms. We will also need to make sure that every situation exists, and also that when an incorrect input is obtained, the algorithm manages to give an answer accordingly. Having gone through all of this, we would like to test the efficiency of our algorithms. It is important to note at this point that this cannot be calculated by checking the running time on the computer, because it also depends on the computer's hardware abilities. We will need to calculate this with the tools we have from data structure and algorithms courses. This test is also done manually and not by a computer.

---------
## 5. Analysis - The Performance of The Algorithms:

**Before we start checking running times, it is important to note a few important things:**

- Because we use a random graph-building function, the results may not be accurate enough. This happens because in the edge creation phase, if the function matches a edge to the same vertex, it tries again until it finds another vertex (src & dest cannot be the same here).
- Each time we create a random graph and then run the function on it. That is, the initialization time of the graph in addition to the function must be taken into account.
- Our computers are not that powerful. This causes our functions to run quite poorly. Therefore, we have added the complications in advance. It is known that the efficiency of a function is measured by its complexity and not by its runtime, because it is directly affected by the CPU's strength.
- Since some of the algorithms depend on which or how many vertices we chose, for the shortest path algorithms, we chose the first and last vertex in terms of order. In the Traveling Saleman Problem algorithm, we selected 4 cities to divide the number of vertices by 4. Of course it is not possible to know how complex this possibility is, on the other hand, there is no end to this matter.
- We were required that the average rank of each vertex will be 20. Therefore, the more vertices there are, the less likely the graph is to be connected. As a result, some running times are when the graph is not connected. This greatly reduces the runtime of the functions that require connectivity so that the algorithm can find a proper (non-empty) solution.

| **Algorithm**                         |  **Complexity**  |  **1,000 Graph RT**  | **10,000 Graph RT**   |  **100,000 Graph RT**    |  **1,000,000 Graph RT**    |
|---------------------------------------|------------------|----------------------|-----------------------|--------------------------|----------------------------|
| `isConnected()`                       | O(n+m)           |  116 ms              |  605 ms               |  1 sec 424 ms            |  16 sec 384 ms             |
| `shortestPathDist(int src, int dest)` | O(mlogn)         |  38 ms               |  253 ms               |  1 sec 929 ms            |  19 sec 542 ms             |
| `shortestPath(int src, int dest)`     | O(mlogn)         |  31 ms               |  231 ms               |  1 sec 454 ms            |  16 sec 451 ms             |
| `center()`                            | O(n(mlogn))      |  1 sec 970 ms        |  4 min 32 sec         |  1 src 837 ms            |  TimeoutException          |
| `tsp(List<NodeData> cities)`          | O(n!)            |  2 sec 36 ms         |  OutOfMemoryError     |  OutOfMemoryError        |  OutOfMemoryError          |
| `save(String file)`                   | O(m+n)           |  216 ms              |  618 ms               |  3 sec 425 ms            |  OutOfMemoryError          |
| `load(String file)`                   | O(m+n)           |  16 ms               |  138 ms               |  1 sec 223 ms            |  13 sec 450 ms             |

* RT - Running Time
* n  - # of V nodes of the graph
* m  - # of the E edges of the graph

---------
## 6. How to Download, Run and Use The Graphical Interface:

Download the whole project and export it by the above actions:
```
Click Code (Green Button) -> Click Download ZIP -> Choose Extract to Folder in Zip -> Write 'cmd' in the project folder's path line
```
Run the above command in cmd:
```
java -jar Ex2.jar 
```
If you want to initialize the program already with a loaded graph, use one of the following commands:
```
java -jar Ex2.jar G1.json
java -jar Ex2.jar G2.json
java -jar Ex2.jar G3.json
```
This project was done by using JDK 15.0.2.

---------
## 7. Info & Resources:

- Directed Graphs : https://en.wikipedia.org/wiki/Directed_graph
- Connectivity : https://en.wikipedia.org/wiki/Connectivity_(graph_theory)
- More information about HashSets : https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html
- DFS Algorithm : https://en.wikipedia.org/wiki/Depth-first_search
- Priority Queue : https://en.wikipedia.org/wiki/Priority_queue
- Dijkstra's Algorithm : https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
- TSP : https://en.wikipedia.org/wiki/Travelling_salesman_problem
- Gson : https://github.com/google/gson
- Java Swing Tutorial : https://www.javatpoint.com/java-swing

---------
## 8. Languages & Tools:

<p align="left">
<a href="https://www.java.com" target="Java"> <img src="https://github.com/tomchen/stack-icons/blob/master/logos/java.svg" alt="java" width="40" height="40"/></a>
<a href="https://www.jetbrains.com/idea/" title="Intellij"> <img src="https://github.com/tomchen/stack-icons/blob/master/logos/intellij-idea.svg" alt="Intellij IDEA" width="40" height="40"/></a>  
<a href="https://www.https://www.json.org/json-en.html" title="JSON"> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/JSON_vector_logo.svg/2048px-JSON_vector_logo.svg.png" alt="JSON" width="40" height="40"/></a>  
