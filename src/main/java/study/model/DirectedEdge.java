package study.model;

public class DirectedEdge<T extends Comparable> extends Edge<T> {

    public DirectedEdge(GraphNode<T> a, GraphNode<T> b) {
        super(a, b);
    }

    public DirectedEdge(GraphNode<T> a, GraphNode<T> b, int weight) {
        super(a, b, weight);
    }

    @Override
    public boolean equals(Object e1) {
        if (e1 instanceof DirectedEdge<?> edge) {
            return edge.vertice1.equals(this.vertice1) && edge.vertice2.equals(this.vertice2);
        }
        return false;
    }
}
