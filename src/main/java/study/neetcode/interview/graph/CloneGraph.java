package study.neetcode.interview.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> cloneSet = new HashMap<>();
        cloneSet.put(node, new Node(node.val, new ArrayList<>()));

        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            Node clone = cloneSet.get(cur);

            for (Node neighbor : cur.getNeighbours()) {
                if (!cloneSet.containsKey(neighbor)) {
                    cloneSet.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    q.add(neighbor);
                }

                clone.getNeighbours().add(cloneSet.get(neighbor));
            }
        }

        return cloneSet.get(node);
    }

    public static class Node {
        public int val;
        private List<Node> neighbors;

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public List<Node> getNeighbours() {
            return neighbors;
        }

        public void setNeighbours(List<Node> _neighbors) {
            neighbors = _neighbors;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}
