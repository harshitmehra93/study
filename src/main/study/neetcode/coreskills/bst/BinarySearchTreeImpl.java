package study.neetcode.coreskills.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<Integer> result = new ArrayList<>();
        inorderWalk(root,result);
        return result;
    }

    private void inorderWalk(BstNode node, List<Integer> result){
        if(node==null){
            return;
        }

        inorderWalk(node.left,result);
        result.add(node.value);
        inorderWalk(node.right,result);
    }
}
