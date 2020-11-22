package ex1.src;

import java.util.Collection;
import java.util.HashMap;

/**
 * This class represents an undirectional weighted graph.
 * It supports a large number of nodes (over 10^6, with average degree of 10).
 */

public class WGraph_DS implements weighted_graph,java.io.Serializable {
	
	private HashMap<Integer, node_info> graph;
	private HashMap<node_info,HashMap<node_info,Double>> connections;
	private int countEdge;
	private int graphChanges;


    /**
    constructor for a new graph.
     */
	public WGraph_DS() {
		this.graph=new HashMap<>();
		this.connections=new HashMap<>();
		this.countEdge=0;
		this.graphChanges=0;
	}
	/**
	this method returns the node for a specific given key.
	@param
	@return
	 */
	public node_info getNode(int key) {
		if(graph.containsKey(key)) {
			return graph.get(key);
		}
		return null;
	}

	/**
	 *return true iff (if and only if) there is an edge between node1 and node2
	 *this method runs in O(1) time.
	 * @param node1
	 * @param node2
	 * @return
	 */
	public boolean hasEdge(int node1, int node2) {
		if(graph.containsKey(node1)&&graph.containsKey(node2)) {
			if(node1==node2){
				return false;
			}
			if(connections.get(graph.get(node1)).containsKey(graph.get(node2))){
				return true;
			}
		}
		return false;
	}
	/**
	 * return the weight of the edge (node1, node2).
	 * In case there is no such edge - return -1
	 * this method runs in O(1) time.
	 * @param node1
	 * @param node2
	 * @return
	 */
	public double getEdge(int node1, int node2) {
		if(node1==node2){
			return 0;
		}
		if(hasEdge (node1,node2)) {
			return connections.get(graph.get(node1)).get(graph.get(node2));
		}
		return -1;
	}
	/**
	 * this method adds a new node to the graph with the given key.
	 * if there is already a node with such a key this method does nothing.
	 * this method construct a node.
	 * @param key
	 */
	public void addNode(int key) {
		if(!graph.containsKey(key)) {
			node_info temp=new NodeInfo(key);
			graph.put(key, temp);
			connections.put(temp,new HashMap<>());
			graphChanges++;
		}
	}
	/**
	 * Connect an edge between node1 and node2, with an edge with given weight W.
	 *  if the edge node1-node2 already exists - the method simply updates the weight of the edge.
	 * @param node1
	 * @param node2
	 * @param w
	 */
	public void connect(int node1, int node2, double w) {
		if(hasEdge(node1,node2)){
			if(connections.get(graph.get(node1)).get(graph.get(node2))==w){
				return;
			}
			connections.get(graph.get(node1)).put(graph.get(node2),w);
			connections.get(graph.get(node2)).put(graph.get(node1),w);
			graphChanges++;
			return;
		}
		if(graph.containsKey(node1)&&graph.containsKey(node2)){
			if(node1==node2){
				return;
			}
			connections.get(graph.get(node1)).put(graph.get(node2),w);
			connections.get(graph.get(node2)).put(graph.get(node1),w);
			countEdge++;
			graphChanges++;
		}
	}

	/**
	 * This method return a pointer (shallow copy) for a
	 * Collection representing all the nodes in the graph.
	 * @return Collection<node_data>
	 */
	public Collection<node_info> getV() {
		return graph.values();
	}

	/**
	 * This method returns a Collection containing all the
	 * nodes connected to given node_id
	 * this method runs in O(1) time.
	 * if the given node_id is not in the graph, return null.
	 * @return Collection<node_data>
	 */
	public Collection<node_info> getV(int node_id) {
		if (!graph.containsKey(node_id)) {
			return null;
		}
		return connections.get(graph.get(node_id)).keySet();
	}
	/**
	 * Delete the node (with the given ID) from the graph.
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none).
	 * @param key
	 */
	public node_info removeNode(int key) {
		if(!graph.containsKey(key)){
		return null; }
		node_info copy=graph.get(key);
		if(!connections.get(graph.get(key)).isEmpty()){
			for(node_info temp:connections.get(graph.get(key)).keySet()){
				connections.get(temp).remove(graph.get(key));
				countEdge--;
				graphChanges++;
			}
		}
		connections.remove(graph.get(key));
		graph.remove(key);
		graphChanges++;
		return copy;
	}
	/** this method removes the edge between node1 and node 2.
	 * if they are not connected this method does nothing.
	 * @param node1
	 * @param node2
	 */
	public void removeEdge(int node1, int node2) {
		if(hasEdge(node1, node2)){
			connections.get(graph.get(node1)).remove(graph.get(node2));
			connections.get(graph.get(node2)).remove(graph.get(node1));
			countEdge--;
			graphChanges++;
		}
	}

	/**
	 * this method returns the size of the graph (number of nodes).
	 * @return
	 */
	public int nodeSize() {
		return graph.size();
	}

	/**
	 * this method returns the number of edges in the graph.
	 * @return
	 */
	public int edgeSize() {
		return countEdge;
	}

	/**
	 * this method returns the number of changes that has been made on the graph.
	 * A change is:remove node , add node , add edge , remove edge.
	 * @return
	 */
	public int getMC() {
		return graphChanges;
	}

	public class NodeInfo implements node_info,java.io.Serializable{

		private String Info;
		private double Tag;
		private int Key;

		/**
		 * constructor for a new node with the given key.
		 * each node has his unique personal key.
		 * @param Key
		 */
		public NodeInfo(int Key) {
			this.Key=Key;
			this.Info="";
			this.Tag=0;
		}

		/**
		 * this method returns the key of a given node.
		 * @return
		 */
		public int getKey() {
			return Key;
		}

		/**
		 * this nethod returns the info of the node.
		 * @return
		 */
		public String getInfo() {
			return Info;
		}

		/**
		 * this method sets the Info of a node with given String s.
		 * @param s
		 */
		public void setInfo(String s) {
			Info=s;
		}

		/**
		 * this method returns the Tag of the node.
		 * @return
		 */
		public double getTag() {
			return Tag;
		}

		/**
		 * this method sets the Tag of the node with given t.
		 * @param t
		 */
		public void setTag(double t) {
			Tag=t;
		}
	}
}
