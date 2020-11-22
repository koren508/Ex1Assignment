package ex1.tests;

import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest1 {

    @Test
    /**
     * tests the following methods:
     * removeEdge , removeEdge , getEdge , getMC , nodeSize , hasEdge and edgeSize.
     */
    void EdgesAndNodes() {
        weighted_graph g0=creategraph();
        g0.removeNode(2);
        g0.removeEdge(0,1);
        boolean flag=true;
        if(g0.getEdge(6,6)!=0) flag=false;
        if(g0.getEdge(2,0)!=-1) flag=false;
        if(g0.getEdge(0,1)!=-1) flag=false;
        if(g0.getMC()!=25) flag=false;
        if(g0.nodeSize()!=9) flag=false;
        if(g0.hasEdge(0,1)) flag=false;
        if(g0.hasEdge(2,5)) flag=false;
        if(g0.edgeSize()!=8) flag=false;
        Assertions.assertEquals(true,flag);
    }

    @Test
    /**
     * tests the following methods:
     * getV , addNode , removeNode , getNode.
     */
    void getV() {
        boolean flag = true;
        weighted_graph g0 = creategraph();
        if (g0.getV().isEmpty()) {
            flag = false;
            Assertions.assertEquals(true, flag);
        }
        Assertions.assertEquals(10, g0.getV().size());
        g0.addNode(12);
        Assertions.assertEquals(11, g0.getV().size());
        g0.removeNode(2);
        g0.addNode(12);
        Assertions.assertEquals(10, g0.getV().size());
        Assertions.assertEquals(null, g0.getNode(2));
    }

    /**
     * create a graph with 10 nodes (0-9).
     * NOTE: the graph is connected.
     * @return
     */
    public static weighted_graph creategraph(){
        weighted_graph g0=new WGraph_DS();
        for(int i=0;i<10;i++){
            g0.addNode(i);
        }
        g0.connect(0,1,2);
        g0.connect(0,4,4);
        g0.connect(2,7,1);
        g0.connect(2,5,1);
        g0.connect(3,9,10);
        g0.connect(3,6,8);
        g0.connect(2,7,1);
        g0.connect(3,5,11);
        g0.connect(3,4,3);
        g0.connect(6,7,1);
        g0.connect(6,8,9);
        g0.connect(6,9,3);
        return g0;
    }
}