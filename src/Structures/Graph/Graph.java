package Structures.Graph;

import java.util.ArrayList;
import java.util.Collection;

public class Graph <T>{

    private ArrayList<T> vertices;
    private Float adjMatrix[][];

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

    public float getTotalCost(){
        float acc = 0;

        for(int i = 0; i < this.vertices.size(); i++){
            for(int j = 0; j < i; j++){
                acc += this.adjMatrix[i][j];
            }
        }

        return acc;
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
