package ex1.src;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms,java.io.Serializable {

    private weighted_graph myGraph;

    /**
     * constructor for a new graph.
     */
    public WGraph_Algo(){
        this.myGraph=new WGraph_DS();
    }

    /**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
    public void init(weighted_graph g) {
        myGraph=g;
    }

    /**
     * Return the underlying graph of which this class works.
     * @return
     */
    public weighted_graph getGraph() {
        return myGraph;
    }

    /**
     * this method returns a deep copy of the graph.
     * @return
     */
    public weighted_graph copy() {
        weighted_graph duplicated = new WGraph_DS();
        if (myGraph.getV().isEmpty()) {
            return duplicated;
        }else{
            for(node_info temp:myGraph.getV()){
                duplicated.addNode(temp.getKey());
                duplicated.getNode(temp.getKey()).setTag(temp.getTag());
                duplicated.getNode(temp.getKey()).setInfo(temp.getInfo());
            }
            for(node_info temp2:myGraph.getV()){
             for(node_info temp3:myGraph.getV(temp2.getKey())){
                 duplicated.connect(temp2.getKey(), temp3.getKey(),myGraph.getEdge(temp2.getKey(), temp3.getKey()));
             }
            }
                }
        return duplicated;
    }
    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node (ubdirectional graph).
     * if the graph contains only 1 node return true.
     * @return
     */
    public boolean isConnected() {
        if(myGraph.nodeSize()==1){
            return true;
        }
        if (myGraph.nodeSize() != 0) {
            int TagsCount=0;
            for(node_info initTag:myGraph.getV()){
                initTag.setTag(0);
            }
            Queue<node_info> neighbers = new LinkedList<>();
            for (node_info temp : myGraph.getV()) {
                if(temp.getTag()!=5) {
                    temp.setTag(5);
                    TagsCount++;
                }
                neighbers.add(temp);
                while(!neighbers.isEmpty()) {
                    if (!myGraph.getV(neighbers.peek().getKey()).isEmpty()) {
                        for (node_info neighbor : myGraph.getV(neighbers.poll().getKey())) {
                            if (neighbor.getTag() != 5) {
                                neighbor.setTag(5);
                                TagsCount++;
                                neighbers.add(neighbor);
                            }
                        }
                    }else{
                    return false;
                }}
//                for(node_info checkTag:myGraph.getV()){
//                    if(checkTag.getTag()!=5){
                        return TagsCount==myGraph.nodeSize();
                    }
                }
        return true;
    }
    /**
     *this method returns the distance from node with key "src" to a node with key "dest"
     * if one of the nodes are not in the graph return -1.
     * if both nodes that given is the same node return 0.
     * the distance from a node to another is the wheight of all the edges in the path.
     * this method runs in O(E+V). |E|=num of nodes , |V|=num of edges
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    public double shortestPathDist(int src, int dest) {
        if(myGraph.getV().contains(src)||myGraph.getV().contains(dest)) return -1;
        if(src==dest) return 0;
        for(node_info initTagAndInfo:myGraph.getV()){
            initTagAndInfo.setTag(0);
            initTagAndInfo.setInfo("");}

            Comparator <node_info> comp = Comparator.comparingDouble(node_info::getTag);
            Queue<node_info> shortestToSrc = new PriorityQueue<>(comp);
//            List<node_info> ThePathIs=new ArrayList<>();
//            Stack<node_info> PathStack=new Stack<>();
            HashMap<node_info,node_info> nodeParent=new HashMap<>();
            myGraph.getNode(src).setInfo("visited");
            shortestToSrc.add(myGraph.getNode(src));
            while(!shortestToSrc.isEmpty()) {
                if(myGraph.getNode(dest).getInfo()=="done") return myGraph.getNode(dest).getTag();
                node_info temp=shortestToSrc.poll();
                for (node_info neighbor : myGraph.getV(temp.getKey())) {
                    if(neighbor.getInfo()!="done"){
                        if(temp.getTag()+myGraph.getEdge(neighbor.getKey(), temp.getKey())<neighbor.getTag()||neighbor.getTag()==0){
                            neighbor.setTag(temp.getTag()+myGraph.getEdge(temp.getKey(), neighbor.getKey()));
                            nodeParent.put(neighbor,temp);}
                    }
                    if (!(neighbor.getInfo() =="visited"||(neighbor.getInfo()=="done")||shortestToSrc.contains(neighbor))){
                        neighbor.setInfo("visited");
                        shortestToSrc.add(neighbor);
                    }

                }
                temp.setInfo("done");
            }
            if(!nodeParent.containsKey(myGraph.getNode(dest))) return -1;
            return myGraph.getNode(dest).getTag();
    }
    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * Note if no such path --> returns null.
     * this method runs in O(E+V). |E|=num of nodes , |V|=num of edges
     *
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    public List<node_info> shortestPath(int src, int dest) {
        List<node_info> ThePathIs=new LinkedList<>();
        if(src==dest) {
            ThePathIs.add(myGraph.getNode(src));
            return ThePathIs;
        }
        for(node_info initTagAndInfo:myGraph.getV()){
            initTagAndInfo.setTag(0);
            initTagAndInfo.setInfo("");}
        Comparator <node_info> comp = Comparator.comparingDouble(node_info::getTag);
        Queue<node_info> shortestToSrc = new PriorityQueue<>(comp);
        Stack<node_info> PathStack=new Stack<>();
        HashMap<node_info,node_info> nodeParent=new HashMap<>();
        myGraph.getNode(src).setInfo("visited");
        shortestToSrc.add(myGraph.getNode(src));
        while(!shortestToSrc.isEmpty()) {
            node_info temp=shortestToSrc.poll();
            for (node_info neighbor : myGraph.getV(temp.getKey())) {
                if(neighbor.getInfo()!="done"){
                    if(temp.getTag()+myGraph.getEdge(neighbor.getKey(), temp.getKey())<neighbor.getTag()||neighbor.getTag()==0){
                        neighbor.setTag(temp.getTag()+myGraph.getEdge(temp.getKey(), neighbor.getKey()));
                        nodeParent.put(neighbor,temp);}
                }
                if (!(neighbor.getInfo() =="visited"||(neighbor.getInfo()=="done")||shortestToSrc.contains(neighbor))){
                    neighbor.setInfo("visited");
                    shortestToSrc.add(neighbor);
                }
            }
            temp.setInfo("done");
        }
        if(!nodeParent.containsKey(myGraph.getNode(dest))){
            return new LinkedList<>();
        }
        node_info temp=myGraph.getNode(dest);
        while (temp!=myGraph.getNode(src)){
            PathStack.add(temp);
            temp=nodeParent.get(temp);
        }
        PathStack.add(myGraph.getNode(src));
        while(!PathStack.isEmpty()){
            ThePathIs.add(PathStack.pop());
        }
        return ThePathIs;
    }
    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    public boolean save(String file) {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.myGraph);
            fileOutputStream.close();
            objectOutputStream.close();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    public boolean load(String file) {
        try {
            FileInputStream fileInputStream=new FileInputStream(file);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            this.myGraph= (weighted_graph)objectInputStream.readObject();

        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return false;


        }
        return true;
    }
}
