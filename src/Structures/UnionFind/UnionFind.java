package Structures.UnionFind;

import java.util.ArrayList;
import java.util.Collection;

public class UnionFind <T>{

    private class NodeUnionFind{

        public T key;
        public int parent;
        public int size;

        public NodeUnionFind(T key, int parent){
            this.key = key;
            this.parent = parent;
            this.size = 1;
        }
    }

    private ArrayList<NodeUnionFind> disjointSet;

    private boolean contains(T element){
        for(NodeUnionFind node : this.disjointSet){
            if(node.key.equals(element)) return true;
        }
        return false;
    }

    private int indexOf(NodeUnionFind element){
        for(int i = 0; i < this.disjointSet.size(); i++){
            if(element.equals(this.disjointSet.get(i))) return i;
        }

        return -1;
    }

    private NodeUnionFind get(T element){
        for(NodeUnionFind node : this.disjointSet){
            if(node.key.equals(element)) return node;
        }
        return null;
    }

    private NodeUnionFind get(int index){
        return this.disjointSet.get(index);
    }

    public UnionFind(Collection<T> elements){

        this.disjointSet = new ArrayList<>();

        for(T element : elements){
            this.disjointSet.add(new NodeUnionFind(element, this.disjointSet.size()));
        }
    }

    // -----------------------------------------------------------------
    // FIND
    private NodeUnionFind find(NodeUnionFind element){
        if(element == null || !this.disjointSet.contains(element)) return null;
        if(element.equals(this.disjointSet.get(element.parent))) return element;
        return find(this.disjointSet.get(element.parent));
    }

    public T find(T element){
        NodeUnionFind node = this.get(element);

        if(node != null) pathCompression(node);

        return this.find(node).key;
    }

    // -----------------------------------------------------------------
    // COMPACTAÇÃO DE CAMINHO
    private void pathCompression(NodeUnionFind node){
        NodeUnionFind root = find(node);
        int rootIndex = indexOf(root);

        while(node != root){
            NodeUnionFind parent = this.get(node.parent);
            node.parent = rootIndex;
            node = parent;
        }
    }

    // -----------------------------------------------------------------
    // UNION
    public boolean union(T element1, T element2){
        if(this.contains(element1) && this.contains(element2)){

            NodeUnionFind root1 = this.get(element1);
            NodeUnionFind root2 = this.get(element2);

            if(!root1.equals(root2)){

            NodeUnionFind smallerTree = (root1.size) <  (root2.size) ? (root1) : (root2);
            NodeUnionFind biggerTree  = (root1.size) >= (root2.size) ? (root1) : (root2);

            smallerTree.parent = this.indexOf(biggerTree);
            biggerTree.size += smallerTree.size;

            return true;
            }
        }

        return false;
    }

    // -----------------------------------------------------------------

    @Override
    public String toString(){
        String out = "[";

        for(int i = 0; i < this.disjointSet.size(); i++){
            NodeUnionFind element = this.get(i);
            out += element.key.toString() + "->" + this.get(element.parent).key.toString();
            if(i < (this.disjointSet.size() - 1)) out += ", ";
        }

        // out += "]\n[";

        // for(int i = 0; i < this.disjointSet.size(); i++){
        //     NodeUnionFind element = this.get(i);
            
        //     if(element.parent == i){
        //         out += "|" + element.key.toString() + "|=" + element.size + " ";
                
        //         if(i < (this.disjointSet.size() - 1)) out += ", ";
        //     }
        // }

        out += "]";

        return out;
    }
}