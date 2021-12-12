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
* `Shortest Path Distance` - 
* `Shortest Path` - Algorithm for finding the shortest path between any 2 vertices in a graph.
* `Center` - Algorithm for finding the most central vertex in a graph (can be more than one)
* `Travelling Salesman Problem (TSP)` - An algorithm for finding the shortest path that passes through all the given vertices.

---------
## 2. The Thoughts Behind The Classes:

It is important to note that since most of the designings of the task are given to us, most of the planning is in the auxiliary functions for the implementation of the algorithms and less about the classes (except for the implementation of the GUI). Since we were initially given five interfaces that we implemented, we will build a class that matches each interface. Each class will implement all the necessary methods and functions. Next, additional methods and additional functions will be built within each class in order to help us solve the task. The work on the GUI interface is divided into several different classes. A class that represents the main menu, one that represents the drawing of the graph,
We will also complete the MAIN function EX2 so that we can run the program as one complete unit.

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
**n** = # of nodes of the graph. **m** = # of the edges of the graph.

| **Algorithm**                         |    **Complexity**   |
|---------------------------------------|---------------------|
| `isConnected()`                       | O())                |
| `shortestPathDist(int src, int dest)` | O()                 |
| `shortestPath(int src, int dest)`     | O()                 |
| `center()`                            | O()                 |
| `tsp(List<NodeData> cities)`          | O()                 |
| `save(String file)`                   | O(m+n)              |
| `load(String file)`                   | O(m+n)              |

> **1,000:**

| **Algorithm**                         |    **Time (+ init())**   |
|---------------------------------------|--------------------------|
| `isConnected()`                       |  221 ms          |
| `shortestPathDist(int src, int dest)` |  ms      |
| `shortestPath(int src, int dest)`     |  ms      |
| `center()`                            |  3 sec 950 ms          |
| `tsp(List<NodeData> cities)`          |  ms          |
| `save(String file)`                   |  254 ms         |

> **10,000:**

| **Algorithm**                         |    **Time (+ init())**   |
|---------------------------------------|--------------------------|
| `isConnected()`                       |  988 ms          |
| `shortestPathDist(int src, int dest)` |  ms      |
| `shortestPath(int src, int dest)`     |  ms      |
| `center()`                            |  TimeOut          |
| `tsp(List<NodeData> cities)`          |  ms          |
| `save(String file)`                   |  1 sec 51 ms         |

> **100,000:**

| **Algorithm**                         |    **Time (+ init())**   |
|---------------------------------------|--------------------------|
| `isConnected()`                       |  9 sec 319 ms          |
| `shortestPathDist(int src, int dest)` |  ms      |
| `shortestPath(int src, int dest)`     |  ms      |
| `center()`                            |  TimeOut          |
| `tsp(List<NodeData> cities)`          |  ms          |
| `save(String file)`                   |  6 sec 600 ms         |

> **1,000,000:**

| **Algorithm**                         |    **Time (+ init())**   |
|---------------------------------------|--------------------------|
| `isConnected()`                       |  ms          |
| `shortestPathDist(int src, int dest)` |  ms      |
| `shortestPath(int src, int dest)`     |  ms      |
| `center()`                            |  TimeOut          |
| `tsp(List<NodeData> cities)`          |  ms          |
| `save(String file)`                   |  ms         |

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
If you want to boot the program already with a loaded graph, use one of the following commands:
```
java -jar Ex2.jar G1.json
java -jar Ex2.jar G2.json
java -jar Ex2.jar G3.json
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
