package A2;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */

        //intialize the disjoint set and graph
        DisjointSets set = new DisjointSets(g.getNbNodes());
        WGraph graph = new WGraph();

        for(int i = 0; i < g.listOfEdgesSorted().size(); i++){
            //checks to see if edge is safe to add into
            //the set
            if(IsSafe(set, g.listOfEdgesSorted().get(i))){

                set.union(g.listOfEdgesSorted().get(i).nodes[0], g.listOfEdgesSorted().get(i).nodes[1]);
                graph.addEdge(g.listOfEdgesSorted().get(i));

            }
        }

        return graph;

    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return 0 is here only to compile) */

        //if the representative of both node[0] and node[1] are equal
        //it is considered unsafe to add the edge
        if(p.find(e.nodes[0]) == p.find(e.nodes[1])){
            return false;
        }

        return true;

    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
