# Ex2 - Data Structures and Algorithms On Graphs
### 

<p align="center">
<img align="center" src="https://www.researchgate.net/profile/Edwin-Sha/publication/3923952/figure/fig3/AS:667702590963725@1536204111731/A-graph-with-nodes-and-edges.png" />
</p>

### @ Or Yitshak & Haim Goldfisher
---------
## 1. Introduction:

In this exercise, we are required to design and implement a variety of different algorithms on graphs (Directed and Weighted), which are represented using data structures.

Each graph (MyDirectedWeightedGraph) has Mode Count int param and two hash sets:

* Mode Count (MC) - Count the number of actions performed so far in the graph (initialized to 0).

* Nodes - Each node contains the following important parameters (not all are specified):
  * key (int): (Called also ID) The name of the vertex (using an int number). Helps us access the desired vertex using its name. Inevitably the name of each vertex is unique and exclusive.
  * location (x,y,z double): The location of the vertex in the three-dimensional space. Note that this task we are required for two-dimensional representation. Therefore the value of z will always be 0.
  * parents_ids (Hashset): An Hashmap that represents all the parent keys of this vertex. Required for access to the parents.
  * children_ids (Hashset): An Hashmap that represents all the child keys of this vertex. Required for access to the children.
  * edges_to_children (Hashset): An Hashmap that represents the keys (src+dest) of all the edges that go from this vertex to child vertices.

* Edges - Each edge contains the following important parameters (not all are specified):
  * src_key (int): The key of the vertex from which the edge emerges
  * dest_key (int): The key of the vertex to which the edge reaches
  * weight (double): Edge's weight. Also called Edge's length.

The algorithms we would like to implement at maximum efficiency:

* <ins>**Is Connected**</ins> - The purpose of this algorithm is very simple to understand. We would like to implement an efficient algorithm in oreder to understand
whether a given graph is a connected or not.
* <ins>**Shortest Path Disttance**</ins> - 
* <ins>**Shortest Path**</ins> - Algorithm for finding the shortest path between any 2 vertices in a graph.
* <ins>**Center**</ins> - Algorithm for finding the most central vertex in a graph (can be more than one)
* <ins>**Travelling Salesman Problem (TSP)**</ins> - An algorithm for finding the shortest path that passes through all the given vertices.

---------
## 2. The Thoughts Behind The Classes:
---------
## 3. UML Diagram:
---------
## 4. Testing Class:
---------
## 5. Analysis - The Performance of The Algorithms:
---------
## 6. How to Download, Run and Use The Graphical Interface:
---------
## 7. Languages & Tools:

<p align="left">
<a href="https://www.java.com" target="Java"> <img src="https://github.com/tomchen/stack-icons/blob/master/logos/java.svg" alt="java" width="40" height="40"/></a>
<a href="https://www.jetbrains.com/idea/" title="Intellij"> <img src="https://github.com/tomchen/stack-icons/blob/master/logos/intellij-idea.svg" alt="Intellij IDEA" width="40" height="40"/></a>  
<a href="https://www.https://www.json.org/json-en.html" title="JSON"> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/JSON_vector_logo.svg/2048px-JSON_vector_logo.svg.png" alt="JSON" width="40" height="40"/></a>  
