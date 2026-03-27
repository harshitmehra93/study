package study.model;

public class Edge<T extends Comparable> {
    public GraphNode<T> vertice1;
    public GraphNode<T> vertice2;
    private int weight;

    public Edge(GraphNode<T> a, GraphNode<T> b) {
        vertice1 = a;
        vertice2 = b;
        weight = 0;
    }

    public Edge(GraphNode<T> a, GraphNode<T> b, int weight) {
        vertice1 = a;
        vertice2 = b;
        this.weight = weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        return vertice1.hashCode() + vertice2.hashCode();
    }

    @Override
    public String toString() {
        if (weight != 0) return "edge : (" + vertice1 + ", " + vertice2 + ") weight=" + weight + "";
        else return "edge : (" + vertice1 + ", " + vertice2 + ")";
    }
}
