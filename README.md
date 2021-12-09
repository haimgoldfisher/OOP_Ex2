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
whether a given graph is a connected or not.
* `Shortest Path Disttance` - 
* `Shortest Path` - Algorithm for finding the shortest path between any 2 vertices in a graph.
* `Center` - Algorithm for finding the most central vertex in a graph (can be more than one)
* `Travelling Salesman Problem (TSP)` - An algorithm for finding the shortest path that passes through all the given vertices.

---------
## 2. The Thoughts Behind The Classes:

It is important to note that since most of the designings of the task are given to us, most of the planning is in the auxiliary functions for the implementation of the algorithms and less about the classes (except for the implementation of the GUI). Since we were initially given five interfaces that we implemented, we will build a class that matches each interface. Each class will implement all the necessary methods and functions. Next, additional methods and additional functions will be built within each class in order to help us solve the task. The work on the GUI interface is divided into several different classes. A class that represents the main menu, one that represents the drawing of the graph,
We will also complete the MAIN function EX2 so that we can run the program as one complete unit.

---------
## 3. UML Diagram:

<p align="center">
<img align="center" src="https://github.com/or-yitshak/OOP_Ex2/blob/main/diagram.png?raw=true" />
</p>

---------
## 4. Testing Class:
---------
## 5. Analysis - The Performance of The Algorithms:

| **Algorithm**                         |    **Complexity**   |
|---------------------------------------|---------------------|
| `isConnected()`                       | O())          |
| `shortestPathDist(int src, int dest)` | O()      |
| `shortestPath(int src, int dest)`     | O()      |
| `center()`                            | O()          |
| `tsp(List<NodeData> cities)`          | O()          |
| `save(String file)`                   | O()          |
| `load(String file)`                   | O()          |

> **1,000**



> **10,000**



> **100,000**



> **1,000,000**



---------
## 6. How to Download, Run and Use The Graphical Interface:

Download the whole project and export it by the above actions:
```
Code (Green Button) -> Download ZIP -> Extract to Folder -> 'cmd' in the project folder's path line
```
Run the above command in cmd:
```
java -jar Ex2.jar 
```
If you want to boot the program already with a loaded graph, use the following command:
```
java -jar Ex2.jar (G1.json/G2.json/G3.json)
```
---------
## 7. Info & Resources:

- Directed Graphs : https://en.wikipedia.org/wiki/Directed_graph
- More information about HashSets : https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html
- DFS Algorithm : https://en.wikipedia.org/wiki/Depth-first_search
- Dijkstra's Algorithm : https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
- Gson : https://github.com/google/gson
- Java Swing Tutorial : https://www.javatpoint.com/java-swing

---------
## 8. Languages & Tools:

<p align="left">
<a href="https://www.java.com" target="Java"> <img src="https://github.com/tomchen/stack-icons/blob/master/logos/java.svg" alt="java" width="40" height="40"/></a>
<a href="https://www.jetbrains.com/idea/" title="Intellij"> <img src="https://github.com/tomchen/stack-icons/blob/master/logos/intellij-idea.svg" alt="Intellij IDEA" width="40" height="40"/></a>  
<a href="https://www.https://www.json.org/json-en.html" title="JSON"> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/JSON_vector_logo.svg/2048px-JSON_vector_logo.svg.png" alt="JSON" width="40" height="40"/></a>  
