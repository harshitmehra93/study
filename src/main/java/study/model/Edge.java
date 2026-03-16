package study.model;

public class Edge<T extends Comparable> {
    public GraphNode<T> vertice1;
    public GraphNode<T> vertice2;

    public Edge(GraphNode<T> a, GraphNode<T> b) {
        vertice1 = a;
        vertice2 = b;
    }

    @Override
    public int hashCode() {
        return vertice1.hashCode() + vertice2.hashCode();
    }

    @Override
    public String toString() {
        return "edge : (" + vertice1 + ", " + vertice2 + ") ";
    }
}
