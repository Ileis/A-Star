package Algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import Structures.Graph.Edge;
import Structures.Graph.Graph;
import Structures.UnionFind.UnionFind;

public class AGM {
    public static <T> Graph<T> kruskal(Graph<T> G){

        Comparator<Edge<T>> comparator = Comparator.comparingDouble(edge -> edge.getCost());
        PriorityQueue<Edge<T>> priorityQueueEdges = new PriorityQueue<>(comparator);

        ArrayList<T> vertices = G.getAllVertices();
        Graph<T> S = new Graph<>(vertices);

        ArrayList<Edge<T>> allEdges = G.getAllEdges();
        priorityQueueEdges.addAll(allEdges);

        UnionFind<T> setOfVertices = new UnionFind<T>(vertices);

        int edgesCounter = 0;

        while(edgesCounter < (vertices.size() - 1) && vertices.size() > 1){

            Edge<T> e = priorityQueueEdges.poll();

            if(e != null){
                T firstVertex = e.getFirstVertex();
                T secondVertex = e.getSecondVertex();
                Float cost = e.getCost();

                T nameSet1 = setOfVertices.find(firstVertex);
                T nameSet2 = setOfVertices.find(secondVertex);

                if(!nameSet1.equals(nameSet2)){

                    setOfVertices.union(firstVertex, secondVertex);
                    S.setCost(firstVertex, secondVertex, cost);
                    edgesCounter++;
                }
            }
        }

        return S;
    }

    public static <T> Float costKruskal(ArrayList<Edge<T>> edges){

        

        return null;
    }

    public static <T> Graph<T> primm(Graph<T> G){

        return null;
    }
}
