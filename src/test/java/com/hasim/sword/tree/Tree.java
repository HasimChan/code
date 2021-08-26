package com.hasim.sword.tree;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

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
 * 二叉树的前序、中序、后序遍历
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

class IsSubStructure {

    // my solution: 需考虑重复节点，故需遍历整树
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null)
            return false;

        if (A.val == B.val) {
            if (isSubTree(A, B)) {
                return true;
            }
        }
        if (A == null) {
            return false;
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isSubTree(TreeNode A, TreeNode B) {
        if (B == null)
            return true;
        if (A == null || A.val != B.val)
            return false;
        return isSubTree(A.left, B.left) && isSubTree(A.right, B.right);
    }

    // 方式一：写法nb，思想一致
    public boolean isSubStructure1(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure1(A.left, B) || isSubStructure1(A.right, B));
    }

    boolean recur(TreeNode A, TreeNode B) {
        if (B == null)
            return true;
        if (A == null || A.val != B.val)
            return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

}

class MirrorTree {
    // my solution: 递归遍历，每次遍历反转左右子树
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);
        return root;
    }

    // 方式一：同 my solution
    public TreeNode mirrorTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTree1(root.left);
        TreeNode right = mirrorTree1(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 方式二：辅助栈，思想同递归
    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null)
            return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null)
                stack.add(node.left);
            if (node.right != null)
                stack.add(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }
}

class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : isSymmetric(root.left, root.right); // 空树也算对称
    }

    private boolean isSymmetric(TreeNode A, TreeNode B) {
        if (A == null && B != null || A != null && B == null) // 此处可优化为方式一
            return false;
        if (A == null && B == null)
            return true;

        if (A.val == B.val) {
            return isSymmetric(A.left, B.right) && isSymmetric(A.right, B.left);
        } else {
            return false;
        }
    }

    // 方式一：同 my solution，但条件判断更为简洁
    public boolean isSymmetric1(TreeNode root) {
        return root == null ? true : recur(root.left, root.right);
    }

    boolean recur(TreeNode L, TreeNode R) {
        if (L == null && R == null)
            return true;
        if (L == null || R == null || L.val != R.val)
            return false;
        return recur(L.left, R.right) && recur(L.right, R.left);
    }
}

/**
 * BFS算法 广度优先搜索
 * 1. 非递归 有去无回
 * 2. 按层遍历
 */
class LevelOrder {
    // my solution: 队列
    public int[] levelOrder(TreeNode root) {
        if (root == null)
            return new int[0];
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> array = new ArrayList<>();
        queue.add(root);
        while (queue.size() != 0) {
            TreeNode poll = queue.poll(); // poll为出队，pop为出栈
            array.add(poll.val);
            if (poll.left != null)
                queue.add(poll.left);
            if (poll.right != null)
                queue.add(poll.right);
        }
        int len = array.size();
        int[] retArr = new int[len];
        for (int i = 0; i < len; i++) {
            retArr[i] = array.get(i);
        }
        return retArr;
    }

    // 方式一：同my solution，几乎一模一样，怀疑答案是抄我的
    public int[] levelOrder1(TreeNode root) {
        if(root == null)
            return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
        int[] res = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);
        return res;
    }
}
