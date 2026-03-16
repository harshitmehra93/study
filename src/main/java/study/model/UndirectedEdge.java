package study.model;

import java.util.HashMap;

public class UndirectedEdge<T extends Comparable> extends Edge<T> {

    public UndirectedEdge(GraphNode<T> a, GraphNode<T> b) {
        super(a,b);
    }

    @Override
    public boolean equals(Object e1) {
        if (e1 instanceof UndirectedEdge<?> edge) {
            return (edge.vertice1.equals(this.vertice1) && edge.vertice2.equals(this.vertice2))
                    || (edge.vertice1.equals(this.vertice2) && edge.vertice2.equals(this.vertice1));
        }
        return false;
    }
}
