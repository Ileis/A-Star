package Structures.Graph;

import java.util.ArrayList;
import java.util.Collection;

public class Graph <T>{

    private ArrayList<T> vertices;
    private Float adjMatrix[][];

    public void printMatrix(){
        for(int i = 0; i < this.vertices.size(); i++){
            for(int j = 0; j < this.vertices.size(); j++){
                System.out.print(String.format(this.vertices.get(i) + "<->" + this.vertices.get(j) + ":" + "%f", this.adjMatrix[i][j]));
                if(j < (this.vertices.size() - 1)) System.out.print(", ");
            }
            System.out.println();
        }
    }

    public Graph(Collection<T> vertices){
        this.vertices = new ArrayList<>(vertices);
        this.adjMatrix = new Float[this.vertices.size()][this.vertices.size()];
    }

    public Float getCost(T element1, T element2){

        if(this.vertices.contains(element1) && this.vertices.contains(element2)){
            return adjMatrix[this.vertices.indexOf(element1)][this.vertices.indexOf(element2)];
        }

        return null;
    }

    public boolean setCost(T element1, T element2, Float cost){

        if(this.vertices.contains(element1) && this.vertices.contains(element2)){
            int indexOfElement1 = this.vertices.indexOf(element1);
            int indexOfElement2 = this.vertices.indexOf(element2);

            this.adjMatrix[indexOfElement1][indexOfElement2] = this.adjMatrix[indexOfElement2][indexOfElement1] = cost;

            return true;
        }

        return false;
    }

    public ArrayList<Edge<T>> getAllNeighborEdges(T element){
        ArrayList<Edge<T>> allNeighborEdges = new ArrayList<>();

        for(int i = 0; i < this.vertices.size(); i++){
            T neighbor = this.vertices.get(i);

            allNeighborEdges.add(new Edge<T>(element, neighbor, this.getCost(element, neighbor)));
        }

        return allNeighborEdges;
    }

    public ArrayList<T> getAllVertices(){
        return new ArrayList<T>(vertices);
    }

    public ArrayList<Edge<T>> getAllEdges(){
        ArrayList<Edge<T>> allEdges = new ArrayList<>();

        for(int i = 0; i < this.vertices.size(); i++){
            for(int j = 0; j < i; j++){
                T element1 = this.vertices.get(i);
                T element2 = this.vertices.get(j);

                allEdges.add(new Edge<T>(element1, element2, this.getCost(element1, element2)));
            }
        }

        return allEdges;
    }

    public float getTotalCost(){
        float acc = 0;

        for(int i = 0; i < this.vertices.size(); i++){
            for(int j = 0; j < i; j++){
                if(this.adjMatrix[i][j] != null)
                    acc += this.adjMatrix[i][j];
            }
        }

        return acc;
    }

    public static <T> ArrayList<Edge<T>> inducedSubgraphByVertices(ArrayList<Edge<T>> edges, ArrayList<T> vertices){

        ArrayList<Edge<T>> S = new ArrayList<Edge<T>>();
        ArrayList<Edge<T>> allEdges = new ArrayList<Edge<T>>(edges);

        while(!allEdges.isEmpty()){
            Edge<T> e = allEdges.remove(allEdges.size() - 1);

            T firstVertex = e.getFirstVertex();
            T secondVertex = e.getSecondVertex();

            if(vertices.contains(firstVertex) && vertices.contains(secondVertex)){
                S.add(e);
            }
        }

        return S;
    }

    public Graph<T> inducedSubgraphByVertices(Collection<T> vertices){

        Graph<T> S = new Graph<>(vertices);

        for(int i = 0; i < vertices.size(); i++){
            for(int j = 0; j < vertices.size(); j++){
                S.setCost(S.vertices.get(i), S.vertices.get(j), this.getCost(S.vertices.get(i), S.vertices.get(j)));
            }
        }

        return S;
    }
}
