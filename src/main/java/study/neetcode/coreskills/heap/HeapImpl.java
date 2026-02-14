package study.neetcode.coreskills.heap;

public class HeapImpl implements Heap {
    int size = 0;
    private HeapNode root;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Integer get() {
        if (getSize() == 0) throw new HeapException("Heap is empty");
        var result = root.getValue();
        minHeapify();
        size--;
        return result;
    }

    private void minHeapify() {
        if (root == null) return;

        HeapNode parent = null;
        HeapNode node = root;
        while (node != null) {
            HeapNode tmp = null;
            if (node.left == null && node.right == null) {
                break;
            }
            if (node.left == null) {
                tmp = node.right;
            } else if (node.right == null) {
                tmp = node.left;
            } else {
                tmp = node.left.value <= node.right.value ? node.left : node.right;
            }
            swap(node, tmp);
            parent = node;
            node = tmp;
        }
        if (parent == null) { // single node tree
            root = null;
        } else if (parent.left == null && parent.right == null) {
            // do nothing
        } else if (parent.right == null) {
            parent.left = null;
        } else if (parent.left == null) {
            parent.right = null;
        } else if (parent.left.value <= parent.right.value) {
            parent.left = null;
        } else if (parent.left.value > parent.right.value) {
            parent.right = null;
        }
    }

    void swap(HeapNode parent, HeapNode child) {
        if (child == null) return;
        var val = parent.value;
        parent.value = child.value;
        child.value = val;
    }

    @Override
    public void push(int i) {
        if (getSize() == 0) {
            root = new HeapNode(i);
        } else {

        }
        size++;
    }
}
