package com.hasim.sword.tree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Tree {

    public TreeNode createTree(int[] array) {



        return null;
    }

    private void createNode(TreeNode node, int[] array, int index) {
    }

    public void preOrderPrint(TreeNode node) {
        if (node == null) {
            return;
        } else {
            System.out.println(node.val);
            preOrderPrint(node.left);
            preOrderPrint(node.right);
        }
    }

    public void midOrderPrint(TreeNode node) {
        if (node == null) {
            return;
        } else {
            midOrderPrint(node.left);
            System.out.println(node.val);
            midOrderPrint(node.right);
        }
    }

    public void postOrderPrint(TreeNode node) {
        if (node == null) {
            return;
        } else {
            postOrderPrint(node.left);
            postOrderPrint(node.right);
            System.out.println(node.val);
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * 将数组的值与索引通过哈希表关联比采用遍历查找更高效（如果数组不改变）
 */
class BuildTree {
    public static void main(String[] args) {
//        TreeNode node0 = new TreeNode(0);
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(5);
//        TreeNode node6 = new TreeNode(6);
//
//        node0.left = node1;
//        node0.right = node2;
//        node1.left = node3;
//        node1.right = node4;
//        node2.left = node5;
//        node2.right = node6;

//        new Tree().postOrderPrint(node0);

//        int[] preorder = new int[]{3,9,20,15,7};
//        int[] inorder = new int[]{9,3,15,20,7};
        int[] preorder = new int[]{1, 2};
        int[] inorder = new int[]{2, 1};

        TreeNode treeNode = new BuildTree().buildTree(preorder, inorder);
        new Tree().preOrderPrint(treeNode);
    }

    // 方式一：同my solution，采用哈希表使得查找更加高效，指针比my solution多了一个，可以，但没必要
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>(); // 将中序遍历数组的值与索引关联
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    // 方式二：迭代，我看不懂，但我大受震撼
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    // my solution:前序遍历与中序遍历的规律+递归+双指针
    // 对于查找中序遍历数组中根节点的索引，实验结果表明使用哈希表比循环快得多
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        indexMap = new HashMap<Integer, Integer>(); // 将中序遍历数组的值与索引关联
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int root, int l, int r) {

        if (l > r)
            return null;
        TreeNode treeNode = new TreeNode(preorder[root]);
        if (l == r)
            return treeNode;
//        int index = findIndex(preorder[root], inorder);
        int index = indexMap.get(preorder[root]);
        treeNode.left = buildTree(preorder, inorder, root + 1, l, index - 1);
        treeNode.right = buildTree(preorder, inorder, root + index - l + 1, index + 1, r);

        return treeNode;
    }

    private int findIndex(int value, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            if (value == inorder[i])
                return i;
        }
        return -1;
    }
}