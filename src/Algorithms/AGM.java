package Algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import Structures.Graph.Edge;
import Structures.Graph.Graph;
import Structures.UnionFind.UnionFind;

public class AGM {
    public static <T> Graph<T> kruskal(Graph<T> G){

        ArrayList<T> vertices = G.getAllVertices();
        Graph<T> S = new Graph<>(vertices);

        Comparator<Edge<T>> comparator = Comparator.comparingDouble(edge -> edge.getCost());
        PriorityQueue<Edge<T>> priorityQueueEdges = new PriorityQueue<>(comparator);

        ArrayList<Edge<T>> allEdges = G.getAllEdges();

        System.out.println(allEdges);

        priorityQueueEdges.addAll(allEdges);

        UnionFind<T> setOfVertices = new UnionFind<>(vertices);

        int edgesCounter = 0;

        // Verificar se o heap está vazio e verificar se a aresta é diferente de nulo
        while(edgesCounter < vertices.size()){
            Edge<T> e = priorityQueueEdges.poll();

            T firstVertex = e.getFirstVertex();
            T secondVertex = e.getSecondVertex();
            Float cost = e.getCost();

            if(!setOfVertices.find(firstVertex).equals(setOfVertices.find(secondVertex))){
                setOfVertices.union(firstVertex, secondVertex);
                S.setCost(firstVertex, secondVertex, cost);
                edgesCounter++;
            }
        }

        return S;
    }

    public static <T> Graph<T> primm(Graph<T> G){

        return null;
    }
}
