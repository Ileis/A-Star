package Algorithms;

import java.util.Comparator;
import Structures.City.City;
import Structures.Graph.Graph;
import Structures.Graph.Edge;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

public class aStar {

    private static class Node <T>{
        public T key;
        public Node<T> parent;
        public Float gn;
        public Float hn;

        public Node(T key, Node<T> parent, Float gn, Float hn){
            this.key = key;
            this.parent = parent;
            this.gn = gn;
            this.hn = hn;
        }

        public float getFn(){
            return this.gn + this.hn;
        }

        @Override
        public String toString() {
            return key.toString() + "-> f(n):" + this.getFn();
        }
    }

    public static void aStarAlgorithm(City initialCity, Graph<City> map){

        Node<City> initialNode = new Node<City>(initialCity, null, 0f, 0f);

        Comparator<Node<City>> comparator = Comparator.comparingDouble(node -> node.getFn());
        PriorityQueue<Node<City>> border = new PriorityQueue<Node<City>>(comparator);
        ArrayList<City> vertices = map.getAllVertices();
        ArrayList<Edge<City>> edges = map.getAllEdges();

        border.add(initialNode);

        Node<City> node = null;

        while(true){

            if(border.isEmpty()) break;

            node = border.poll(); 
            // System.out.println(node.key + ", fn:" + node.getFn() + ", gn:" + node.gn + ", hn:" + node.hn);

            ArrayList<City> path = getPath(node); // O(n)

            if(path.size() == (vertices.size() + 1) && node.key.equals(initialCity)) break; // O(1)

            ArrayList<Edge<City>> neighbors = map.getAllNeighborEdges(node.key); // O(n)
            ArrayList<City> verticesInducedSubgraph = new ArrayList<City>(vertices); // O(n)
            verticesInducedSubgraph.removeAll(path); // O(n * m)
            verticesInducedSubgraph.add(initialCity); // O(1)

            float hnNode = AGM.costKruskal(Graph.inducedSubgraphByVertices(edges, verticesInducedSubgraph)); // O(n^2) + O(n log n)

            // se edge.getSecondVertex está na lista mas apresenta um peso maior, substitua
            for(Edge<City> edge : neighbors){
                
                if(!path.contains(edge.getSecondVertex()) || (path.size() == vertices.size() && edge.getSecondVertex().equals(initialCity))){
                    
                    float gnNode = map.getCost(node.key, edge.getSecondVertex()) + node.gn;

                    border.add(new Node<City>(edge.getSecondVertex(), node, gnNode, hnNode));

                    // addAndRemoveAllBigger(border, new Node<City>(edge.getSecondVertex(), node, gnNode, hnNode));
                }
            }
        }

        ArrayList<City> path = getPath(node);
        Float pathCost = node.gn;

        System.out.print(path);
        System.out.println(" " + String.format("%f", pathCost));
    }

    public static boolean cityIsInBorder(PriorityQueue<Node<City>> priorityQueue, City c){
        for(Node<City> node : priorityQueue){
            if(node.key.equals(c)) return true;
        }
        return false;
    }

    public static void addAndRemoveAllBigger(PriorityQueue<Node<City>> priorityQueue, Node<City> newNode){

        ArrayList<Node<City>> removedNodes = new ArrayList<Node<City>>();

        for(Node<City> node : priorityQueue){
            if(node.key.equals(newNode.key) && newNode.getFn() < node.getFn()) removedNodes.add(node);
        }

        // System.out.println("NewNode:" + newNode + removedNodes);

        priorityQueue.removeAll(removedNodes);
        priorityQueue.add(newNode);
    }

    public static <T> ArrayList<T> getPath(Node<T> node){

        ArrayList<T> path = new ArrayList<T>();

        while(node != null){
            path.add(node.key);
            node = node.parent;
        }

        Collections.reverse(path);

        return path;
    }
}
