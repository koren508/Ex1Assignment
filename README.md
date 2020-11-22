Class node_info

this class holds the information of a node.

methods:
  
getKey() - return the key associated with this node.

getInfo()- return String s (info associated with this node).

setInfo() - set node's info with a new given string s.

getTag() - return the Tag associated with this node.

setTag() - set node's Tag with a new given double value.






Class weighted_graph

this class represents an undirectional weighted graph.
It supports a large number of nodes (over 10^6, with average degree of 10).

methods:


getNode()- return the node for the specific given key.


hasEdge() - return true if and only if there is an edge between node1 and node2.


getEdge() - return the weight of an edge between node1 and node2.


addNode() - construct a new node and add him to the graph with the given key.


connect()- connect node1 and node2 with an edge with given weight W.


nodeSize() - return the number of nodes in the graph.


edgeSize() - return the number of edges in the graph.


getV() - return a collection of nodes which contains all the nodes in the graph.


getV(node_id) - return a collection of all the nodes that are connected to this node_id.


getMC() - return the number of changes in the graph.
A change is:remove node , add node , remove edge , connect.

all the methods above runs in O(1) because we are using a HashMap which contains all those methodes.


removeNode() - removes a node from the graph and removes all the edges which starts or ends at this node.
this method runs in O(n) |n|=num of edges , as this node might be connected to all the others in the graph.


removeEdge() - remove the edge between node1 and node2.






Class weighted_graph_algorithms

This class represents an undirectional weighted graph with several algorithms.

methods:


init()- Init the graph on which this set of algorithms operates on.

getGraph() - return the graph of which this class works on.

copy() - deep copy of this weighted graph by the following steps.

1.create a new graph.
2.deep copy of every node in the graph and his personal information.
3.connect the nodes as they are in the original graph.
this method runs in O(V+E) |V|=num of nodes  |E|=num of edges.

isConnected()- return true if and only if there is a valid path from
 
EVERY  node in the graph to each other node.
1.we are going over all the nodes by a BFS algorithm  and set their tag to 5.
2.every single time we set a node's tag to 5 we count +1 on our counter.
(note:in this algorithm we are visiting every node once.)
3.after we reached all the connected nodes , if the counter is equal to the number of nodes in the graph , return true as the graph is connected.
BFS algorithm works by layers.
run time O(V+E)  |V|=num of nodes   |E|=num of edges


shortestPathDist() - return the shortest path distance from src to dest.
the distance from a node to another is the weight of all the edges in the path.
this method works with BFS algorithm as isConnected().
1.we are going over all the nodes in the graph and set their tag to the distance from src to them.
2.we are checking first the nearest to src nodes by a priorityQueue which prioritize the lower Tag.
3.when we reach dest , we return his Tag.
run time O(V+E)  |V|=num of nodes   |E|=num of edges


shortestPath() - return a list of nodes which contains the shortest 

path between src to dest.
this method does the same algorithm as shortestPathDist , but there is several diffrences.

everytime we set a node's tag we write down who his father is.
when we finished to run over the nodes , we are going backwards from dest to src and adding the nodes to a Stack , then returning the Stack upside down by moving all the nodes from the stack to a List.
dest > node > node > node > src  (Stack).
src > node > node > node > dest  (List).
run time O(V+E)  |V|=num of nodes   |E|=num of edges
 

