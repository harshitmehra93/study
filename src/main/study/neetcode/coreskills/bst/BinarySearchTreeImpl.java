package study.neetcode.coreskills.bst;

import java.util.*;

import static java.util.Objects.isNull;

public class BinarySearchTreeImpl implements BinarySearchTree{
    private int size;
    BstNode root;
    BinarySearchTreeImpl(List<Integer> list){
        size=list.size();
        createBst(list);
    }

    private BstNode createBst(List<Integer> list) {
        Collections.sort(list);
        root=getMiddleNode(null,list,0,list.size()-1);
        return root;
    }

    private BstNode getMiddleNode(BstNode parent, List<Integer> list, int start, int end){
        BstNode node=null;
        if(start<=end){
            int midIndex = (start + end) / 2;
            int val = list.get(midIndex);
            node = new BstNode(val);
            node.parent=parent;
            node.left=getMiddleNode(node,list,start,midIndex-1);
            node.right=getMiddleNode(node,list,midIndex+1,end);
        }
        return node;
    }

    BinarySearchTreeImpl(){
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public List<Integer> inorderWalk() {
        List<Integer> result = inorderWalkIterative();
//        List<Integer> result = new ArrayList<>();
//        inorderWalk(root,result);
        return result;
    }

    @Override
    public List<Integer> preOrder() {
        List<Integer> result = new ArrayList<>();
        preOrder(root,result);
        return result;
    }
    private void preOrder(BstNode node, List<Integer> result){
        if(node==null)
            return;

        result.add(node.val);
        preOrder(node.left,result);
        preOrder(node.right,result);
    }

    @Override
    public List<Integer> postOrder() {
        List<Integer> result = new ArrayList<>();
        postOrder(root,result);
        return result;
    }

    @Override
    public BstNode search(int target) {
//        return search(root,target);
        return iterativeSearch(root,target);
    }

    @Override
    public BstNode treeMin() {
        BstNode tmp = root;
        return min(tmp);
    }

    private static BstNode min(BstNode tmp) {
        while(tmp.left!=null)
            tmp = tmp.left;
        return tmp;
    }

    @Override
    public BstNode treeMax() {
        BstNode tmp = root;
        return max(tmp);
    }

    private BstNode max(BstNode tmp) {
        while(tmp.right!=null)
            tmp = tmp.right;
        return tmp;
    }

    @Override
    public BstNode successor(int i) {
        BstNode node = search(i);
        if(isNull(node))
            return null;
        if(node.right!=null){
            return min(node.right);
        }

        BstNode parent = node.parent;
        BstNode child = node;
        while(parent!=null && child==parent.right){
            child = parent;
            parent=child.parent;
        }
        return parent;
    }

    @Override
    public BstNode predecessor(int i) {
        BstNode node = search(i);
        if(isNull(node))
            return null;
        if(node.left!=null){
            return max(node.left);
        }

        BstNode parent = node.parent;
        BstNode child = node;
        while(parent!=null && child==parent.left){
            child = parent;
            parent=child.parent;
        }
        return parent;
    }

    private BstNode iterativeSearch(BstNode node, int target) {
        while(node!=null){
            if(node.val ==target){
                break;
            }
            if(node.val <target){
                node=node.right;
            }else {
                node=node.left;
            }
        }
        return node;
    }

    private BstNode search(BstNode node,int target){
        if(isNull(node)){
            return null;
        }
        if(node.val ==target){
            return node;
        } else if (node.val <target) {
            return search(node.right,target);
        } else {
            return search(node.left,target);
        }
    }

    private void postOrder(BstNode node, List<Integer> result){
        if(node==null)
            return;

        preOrder(node.left,result);
        preOrder(node.right,result);
        result.add(node.val);
    }

    private void inorderWalk(BstNode node, List<Integer> result){
        if(node==null){
            return;
        }

        inorderWalk(node.left,result);
        result.add(node.val);
        inorderWalk(node.right,result);
    }

    public List<Integer> inorderWalkIterative(){
        Deque<BstNode> stack = new LinkedList();
        ArrayList<Integer> result = new ArrayList<>();

        BstNode current = root;

        while(current!=null || !stack.isEmpty()){
            while(current!=null){
                stack.push(current);
                current=current.left;
            }

            current=stack.pop();
            result.add(current.val);

            current=current.right;
        }
        return result;
    }
}
