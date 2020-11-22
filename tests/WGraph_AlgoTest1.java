package ex1.tests;

import ex1.src.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest1 {

    /**
     * test deep copy of graphs.
     */
    @Test
    void copy() {
        boolean flag=true;
        weighted_graph g1=creategraph();
        weighted_graph_algorithms g2 = new WGraph_Algo();
        weighted_graph g3 = new WGraph_DS();
        g2.init(g1);
        g3=g2.copy();
        assertEquals(g1, g3);
    }

    /**
     * test if graph is connected or not.
     */
    @Test
    void isConnected() {
        weighted_graph g1 = creategraph();
        weighted_graph_algorithms g2=new WGraph_Algo();
        g2.init(g1);
        assertTrue(g2.isConnected());
        g1.removeNode(0);
        g2.init(g1);
        assertFalse(g2.isConnected());
    }

    /**
     * test the shortest distance path between src to dest.
     */
    @Test
    void shortestPathDist() {
        weighted_graph g0=creategraph();
        weighted_graph_algorithms g1=new WGraph_Algo();
        g1.init(g0);
        double distance=g1.shortestPathDist(0,2);
        assertTrue(distance==17);
        distance=g1.shortestPathDist(3,8);
        assertTrue(distance==17);
        g1.getGraph().removeEdge(3,6);
        distance=g1.shortestPathDist(3,8);
        assertTrue(distance==22);
    }

    /**
     * test the shortestPath between src to dest.
     */
    @Test
    void shortestPath() {
        weighted_graph g0=creategraph();
        weighted_graph_algorithms g1=new WGraph_Algo();
        g1.init(g0);
        List<node_info> Path=g1.shortestPath(0,2);
        assertTrue(Path.size()==6);
        assertTrue(Path.remove(5)==g1.getGraph().getNode(2));
        assertTrue(Path.remove(4)==g1.getGraph().getNode(7));
        assertTrue(Path.remove(3)==g1.getGraph().getNode(6));
        assertTrue(Path.remove(2)==g1.getGraph().getNode(3));
        assertTrue(Path.remove(1)==g1.getGraph().getNode(4));
        assertTrue(Path.remove(0)==g1.getGraph().getNode(0));
    }

    @Test
    void SaveAndLoad() throws FileNotFoundException {
        weighted_graph g1= creategraph();
        weighted_graph_algorithms g2 = new WGraph_Algo();
        g2.init(g1);
        String str = "g1.obj";
        g2.save(str);
        g2.load(str);
        assertEquals(g1,g2.getGraph());
        g2.getGraph().removeEdge(3,5);
        assertFalse(g1==g2);
        assertFalse(g2.load("JustKidding.obj"));
    }

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