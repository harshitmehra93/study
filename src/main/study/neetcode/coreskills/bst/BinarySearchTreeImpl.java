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

    @Override
    public void insert(int i) {
        BstNode node = new BstNode(i);
        if(root==null){
            root=node;
        }else{
            BstNode ptr = root;
            BstNode parent = null;
            while(ptr!=null){
                parent=ptr;
                if(ptr.val<=i)
                    ptr=ptr.right;
                else
                    ptr=ptr.left;
            }
            node.parent=parent;
            if(parent.val<=i)
                parent.right=node;
            else
                parent.left=node;
        }
        size++;
    }

    @Override
    public void prettyPrint() {
        if(root==null){
            return;
        }
        System.out.println();
        Queue<BstNode> q1 = new LinkedList<>();
        Queue<BstNode> q2 = new LinkedList<>();
        Queue<BstNode> tmp = null;
        BstNode nan = new BstNode(-1);
        q1.add(root);
        int height = getMaxHeight();
        int level = 0;
        while(!q1.isEmpty()){
            int spaces = height*4-(int)Math.pow(2,level);
            for(int i=0;i<spaces;i++){
                System.out.print("  ");
            }
            while (!q1.isEmpty()){
                BstNode node = q1.poll();
                if(node.left!=null){
                    q2.offer(node.left);
                }else if(node!=nan){
                    q2.offer(nan);
                }
                if(node.right!=null){
                    q2.offer(node.right);
                }
                else if(node!=nan){
                    q2.offer(nan);
                }
                System.out.print(node.val+"   ");
            }
            level++;
            System.out.println();
            tmp=q1;
            q1=q2;
            q2=tmp;
        }
    }

    @Override
    public int getMaxHeight() {
        if(isNull(root))
            return 0;
        return getMaxHeight(root);
    }

    @Override
    public boolean delete(int i) {
        if(root==null)
            return false;

        BstNode node = search(i);

        if(isNull(node)){
            return false;
        }

        if(root==node&&size==1){
            root=null;
            size--;
            return true;
        }

        // if leaf node, then sever connection with parent
        if(isNull(node.left)&&isNull(node.right)){
            if(node.parent.left==node)
                node.parent.left=null;
            if(node.parent.right==node)
                node.parent.right=null;
            size--;
            return true;
        }

        if(isNull(node.right)||isNull(node.left)){
            if(node==root){
                if(isNull(node.left)){
                    root=node.right;
                    node.right.parent=null;
                }
                if(isNull(node.right)){
                    root=node.left;
                    node.left.parent=null;
                }
            }else{
                if(node.parent.left==node){
                    if(isNull(node.left)){
                        node.parent.left=node.right;
                        node.right.parent=node.parent;
                    }else {
                        node.parent.right=node.left;
                        node.left.parent=node.parent;
                    }
                }else if(node.parent.right==node){
                    if(isNull(node.right)){
                        node.parent.right=node.left;
                        node.left.parent=node.parent;
                    }else {
                        node.parent.right=node.right;
                        node.right.parent=node.parent;
                    }
                }
            }
            size--;
            return true;
        }

        if(node.left!=null&&node.right!=null){
            BstNode successor = successor(node.val);
            if(node.right==successor){
                if(node.parent.left==node){
                    node.parent.left=successor;
                }else {
                    node.parent.right=successor;
                }
                successor.parent=node.parent;
                successor.left=node.left;
                size--;
                return true;
            }

            BstNode newNode =new BstNode(successor.val);
            newNode.parent=node.parent;
            newNode.left=node.left;
            newNode.right=node.right;

            if(successor.right!=null){
                successor.parent.left=successor.right;
                successor.right.parent=successor.parent;
            }else{
                successor.parent.left=null;
            }

            if(node.parent.left==node){
                node.parent.left=newNode;
            }else {
                node.parent.right=newNode;
            }
            size--;
            return true;
        }
        return false;
    }

    public void transplant(BstNode u, BstNode v){
        if(u==root){
            root=v;
            v.parent=null;
        }
        if(u.parent.left==u){
            u.parent.left=v;
        }else {
            u.parent.right=v;
        }
        if(v!=null)
            v.parent=u.parent;
    }

    public int getMaxHeight(BstNode node){
        if(isNull(node)){
            return 0;
        }
        return 1 + Math.max(getMaxHeight(node.left),getMaxHeight(node.right));
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
