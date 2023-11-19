package Algorithms;

public class aStar {
    private class Node <T>{
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
    }

    public static void aStarAlgorithm(){
    }
}
