package study.utils;

import study.model.BstNodeBase;
import study.model.TreeNodeBase;
import study.neetcode.coreskills.bst.BstNode;

import java.util.LinkedList;
import java.util.Queue;

import static java.util.Objects.isNull;

public class TreeUtils {
    static public <T> void prettyPrint(TreeNodeBase<T> root) {
        if (root == null) {
            return;
        }
        System.out.println();
        Queue<TreeNodeBase<T>> q1 = new LinkedList<>();
        Queue<TreeNodeBase<T>> q2 = new LinkedList<>();
        Queue<TreeNodeBase<T>> tmp = null;
        TreeNodeBase<T> nan = getNan();
        q1.add(root);
        int height = getMaxHeight(root);
        int level = 0;
        while (!q1.isEmpty()) {
            int spaces = height * 4 - (int) Math.pow(2, level);
            for (int i = 0; i < spaces; i++) {
                System.out.print("  ");
            }
            while (!q1.isEmpty()) {
                TreeNodeBase node = q1.poll();
                if (node.getLeft() != null) {
                    q2.offer(node.getLeft());
                } else if (node != nan) {
                    q2.offer(nan);
                }
                if (node.getRight() != null) {
                    q2.offer(node.getRight());
                } else if (node != nan) {
                    q2.offer(nan);
                }
                System.out.print(node.getValue() + "   ");
            }
            level++;
            System.out.println();
            tmp = q1;
            q1 = q2;
            q2 = tmp;
        }
    }

    private static <T> TreeNodeBase<T> getNan() {
        return new TreeNodeBase<T>() {

            @Override
            public T getValue() {
                return null;
            }

            @Override
            public TreeNodeBase getLeft() {
                return null;
            }

            @Override
            public TreeNodeBase getRight() {
                return null;
            }
        };
    }

    public static int getMaxHeight(TreeNodeBase node) {
        if (isNull(node)) {
            return 0;
        }
        return 1 + Math.max(getMaxHeight(node.getLeft()), getMaxHeight(node.getRight()));
    }
}
