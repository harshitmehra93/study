package study.model;

import java.util.HashMap;

public class UndirectedEdge<T extends Comparable> extends Edge<T> {

    public HashMap<String, String> attributes = new HashMap<String, String>();

    public UndirectedEdge(GraphNode<T> a, GraphNode<T> b) {
        vertice1 = a;
        vertice2 = b;
    }

    @Override
    public boolean equals(Object e1) {
        if (e1 instanceof UndirectedEdge<?> edge) {
            return (edge.vertice1.equals(this.vertice1) && edge.vertice2.equals(this.vertice2))
                    || (edge.vertice1.equals(this.vertice2) && edge.vertice2.equals(this.vertice1));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return vertice1.hashCode() + vertice2.hashCode();
    }

    @Override
    public String toString() {
        return "edge : (" + vertice1 + ", " + vertice2 + ") " + attributes;
    }
}
