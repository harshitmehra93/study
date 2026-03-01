package study.neetcode.coreskills.graph;

import static java.util.Objects.isNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import study.model.Graph;
import study.model.GraphException;
import study.model.GraphNode;

public class UndirectedGraphWithAdjMatrix<T extends Comparable> implements Graph<T> {

  public static final double LOAD_THRESHOLD = .75;
  HashMap<T, GraphNode<T>> nodesMap = new HashMap<>();
  HashMap<GraphNode<T>, Integer> nodeToIndexMap = new HashMap<>();
  HashMap<Integer, GraphNode<T>> indexToNodeMap = new HashMap<>();
  private int initialCapacity;
  int[][] adjMatrix;

  UndirectedGraphWithAdjMatrix(int initialCapacity) {
    this.initialCapacity = initialCapacity;
    adjMatrix = new int[initialCapacity][initialCapacity];
  }
  UndirectedGraphWithAdjMatrix() {
    this(10);
  }

  @Override
  public Set<GraphNode<T>> getGraphNodes() {
    return new HashSet<>(nodesMap.values());
  }

  @Override
  public int getSize() {
    return nodesMap.size();
  }

  @Override
  public void addEdge(T a, T b) {
    addEdge(getNode(a), getNode(b));
  }

  @Override
  public void addEdge(GraphNode<T> a, GraphNode<T> b) {
    int indexOfA = nodeToIndexMap.get(a);
    int indexOfB = nodeToIndexMap.get(b);
    if(a.compareTo(b)==0) {
      throw new GraphException("Cant have self loop");
    }
    if(adjMatrix[indexOfA][indexOfB] == 1 && adjMatrix[indexOfB][indexOfA] == 1) {
      throw new GraphException("edge already exists");
    }
    if(adjMatrix[indexOfA][indexOfB] == 1 || adjMatrix[indexOfB][indexOfA] == 1) {
      throw new GraphException("graph state corrupted");
    }
    adjMatrix[indexOfA][indexOfB]=1;
    adjMatrix[indexOfB][indexOfA]=1;
  }

  @Override
  public void addNode(T o) {
    if(isNull(o)){
      throw new GraphException("node is null");
    }
    if(nodesMap.containsKey(o)) {
      throw new GraphException("node already exists");
    }
    if(isOverloaded()){
      resize();
    }
    GraphNode<T> newNode = new GraphNode<>(o);
    nodesMap.put(o, newNode);
    int newIndex = nodesMap.size() - 1;
    nodeToIndexMap.put(newNode, newIndex);
    indexToNodeMap.put(newIndex, newNode);
  }

  private void resize() {
    System.out.println("Resizing... from "+initialCapacity+" to "+initialCapacity*2);
    initialCapacity *= 2;
    copyAdjMatrixToNew();
  }

  private void copyAdjMatrixToNew() {
    int[][] newAdj = new int[initialCapacity][initialCapacity];
    for (int i = 0; i < adjMatrix.length; i++) {
      for (int j = 0; j < adjMatrix.length; j++) {
        newAdj[i][j] = adjMatrix[i][j];
      }
    }
    adjMatrix = newAdj;
  }

  @Override
  public GraphNode<T> getNode(T node) {
    if(!nodesMap.containsKey(node)) {
      throw new GraphException("node not found");
    }
    return nodesMap.get(node);
  }

  @Override
  public void removeEdge(T nodeA, T nodeB) {
    GraphNode<T> a = getNode(nodeA);
    GraphNode<T> b = getNode(nodeB);

    int indexOfA = nodeToIndexMap.get(a);
    int indexOfB = nodeToIndexMap.get(b);

    if(adjMatrix[indexOfA][indexOfB] == 1 && adjMatrix[indexOfB][indexOfA] == 1) {
      adjMatrix[indexOfA][indexOfB]=0;
      adjMatrix[indexOfB][indexOfA]=0;
    }else{
      throw new GraphException("edge does not exist or graph is corrupted");
    }
  }

  @Override
  public Set<GraphNode<T>> getNeighbours(T node) {
    GraphNode<T> NodeA = getNode(node);
    Set<GraphNode<T>> neighbours = new TreeSet<>();
    int indexOfA = nodeToIndexMap.get(NodeA);
    for(int i = 0; i < adjMatrix.length; i++){
      int value = adjMatrix[indexOfA][i];
      if(value == 1){
        neighbours.add(indexToNodeMap.get(i));
      }
    }
    return neighbours;
  }

  private boolean isOverloaded(){
    return (double) nodesMap.size() / initialCapacity > LOAD_THRESHOLD;
  }
}
