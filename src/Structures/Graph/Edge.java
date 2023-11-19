package Structures.Graph;

public class Edge <T>{
    private T firstVertex;
    private T secondVertex;
    private Float cost;

    public Edge(T v1, T v2, Float cost){
        this.firstVertex = v1;
        this.secondVertex = v2;
        this.cost = cost;
    }

    public T getFirstVertex(){
        return this.firstVertex;
    }

    public T getSecondVertex(){
        return this.secondVertex;
    }

    public Float getCost(){
        return this.cost;
    }

    @Override
    public String toString() {
        return this.firstVertex.toString() + " <-> " + this.secondVertex.toString() + ": " + this.cost;
    }
}
