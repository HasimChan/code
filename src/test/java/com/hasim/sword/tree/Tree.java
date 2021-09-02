package com.hasim.sword.tree;

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

    public void preOrderPrintNotRec(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.println(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
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

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

/**
 * 将数组的值与索引通过哈希表关联比采用遍历查找更高效（如果数组不改变）
 * 二叉树的前序、中序、后序遍历
 */
class BuildTree {
    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node0.left = node1;
        node0.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        new Tree().preOrderPrint(node0);
        System.out.println("---***---");
        new Tree().preOrderPrintNotRec(node0);

//        int[] preorder = new int[]{3,9,20,15,7};
//        int[] inorder = new int[]{9,3,15,20,7};
//        int[] preorder = new int[]{1, 2};
//        int[] inorder = new int[]{2, 1};
//
//        TreeNode treeNode = new BuildTree().buildTree(preorder, inorder);
//        new Tree().preOrderPrint(treeNode);
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

/**
 * 层序遍历
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
        if (root == null)
            return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);
        return res;
    }
}

/**
 * 分层打印
 */
class LevelOrder2 {
    // my solution: 借鉴队列的思想，用两个队列存储树的节点，一个存储当前层，一个存储下一层
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<List<Integer>>();
        ArrayList<List<Integer>> treeList = new ArrayList<>();
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<TreeNode> queue2 = new LinkedList<>();
        queue2.add(root);

        while (!queue2.isEmpty()) {
            queue1 = queue2;
            queue2 = new LinkedList<>();
            ArrayList<Integer> layer = new ArrayList<>();
            while (!queue1.isEmpty()) {
                TreeNode poll = queue1.poll();
                layer.add(poll.val);
                if (poll.left != null)
                    queue2.add(poll.left);
                if (poll.right != null)
                    queue2.add(poll.right);
            }
            treeList.add(layer);
        }
        return treeList;
    }

    // 方式一: 只用一个队列，妙在运用了当前队列的长度
    public List<List<Integer>> levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null)
            queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) { // 此处666
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }
}

/**
 * Z字型遍历
 * 双端队列 LinkedList nb，可队列可栈可双端队列
 */
class LevelOrder3 {
    // my solution: 层序遍历 + 奇偶层
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        LinkedList<List<Integer>> resultList = new LinkedList<>();
        if (root == null)
            return resultList;
        list.add(root);
        boolean tip = true;

        while (!list.isEmpty()) {
            int len = list.size();
            LinkedList<Integer> layer = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                TreeNode node = list.poll();
                layer.add(node.val);
                if (node.left != null)
                    list.add(node.left);
                if (node.right != null)
                    list.add(node.right);
            }
            if (tip) {
                resultList.add(layer);
            } else { // 此处较为臃肿
                LinkedList<Integer> temp = new LinkedList<>();
                while (!layer.isEmpty()) {
                    temp.add(layer.removeLast());
                }
                resultList.addLast(temp);
            }
            tip = !tip;
        }
        return resultList;
    }

    // 方式一：通过返回集合的size大小作为奇偶层依据，通过判断奇偶层选择从头部添加还是从尾部添加
    public List<List<Integer>> levelOrder1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null)
            queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if (res.size() % 2 == 0)
                    tmp.addLast(node.val); // 偶数层 -> 队列头部
                else
                    tmp.addFirst(node.val); // 奇数层 -> 队列尾部
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }

    // 方式二：以一奇一偶两层为单位遍历树
    public List<List<Integer>> levelOrder2(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null)
            deque.add(root);
        while (!deque.isEmpty()) {
            // 打印奇数层
            List<Integer> tmp = new ArrayList<>();
            for (int i = deque.size(); i > 0; i--) {
                // 从左向右打印
                TreeNode node = deque.removeFirst();
                tmp.add(node.val);
                // 先左后右加入下层节点
                if (node.left != null)
                    deque.addLast(node.left);
                if (node.right != null)
                    deque.addLast(node.right);
            }
            res.add(tmp);
            if (deque.isEmpty())
                break; // 若为空则提前跳出
            // 打印偶数层
            tmp = new ArrayList<>();
            for (int i = deque.size(); i > 0; i--) {
                // 从右向左打印
                TreeNode node = deque.removeLast();
                tmp.add(node.val);
                // 先右后左加入下层节点
                if (node.right != null)
                    deque.addFirst(node.right);
                if (node.left != null)
                    deque.addFirst(node.left);
            }
            res.add(tmp);
        }
        return res;
    }

    // 方式三：层序遍历 + 反转列表
    public List<List<Integer>> levelOrder3(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null)
            queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            if (res.size() % 2 == 1)
                Collections.reverse(tmp); // 反转列表
            res.add(tmp);
        }
        return res;
    }
}

/**
 * 有关树的遍历的问题需要了解各种遍历的规律
 */
class VerifyPostorder {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 6, 12, 8, 16, 14, 10};
        System.out.println(new VerifyPostorder().verifyPostorder(arr));
    }

    // my solution: 没解出来，考虑了局部没考虑整体
    // 思路为找到当前根节点的左孩子节点（仅仅从左往右遍历一次，找到便不再遍历），此处出现bug
    // 如果当前节点的左右孩子节点符合要求则递归下去，否则递归终止返回false，或者遍历到叶节点返回true
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    public boolean verifyPostorder(int[] postorder, int l, int r) {
        if (l >= r)
            return true;
        int root = postorder[r];
        int index = searchIndex(postorder, root, l, r);
        if (r - 1 <= index) {
            if (postorder[index] > root)
                return false;
        } else if (index < 0) {
            if (postorder[r - 1] < root)
                return false;
        } else {
            if (postorder[index] > root || postorder[r - 1] < root)
                return false;
        }
        return verifyPostorder(postorder, l, index) && verifyPostorder(postorder, index + 1, r - 1);
    }

    private int searchIndex(int[] arr, int root, int l, int r) { // 左子树根节点
        for (int i = l; i < r; i++) {
            if (arr[i] > root) {
                return i - 1;
            }
        }
        return r - 1;
    }

    // 方式一：递归+分治
    public boolean verifyPostorder1(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    boolean recur(int[] postorder, int i, int j) {
        if (i >= j)
            return true;
        int p = i;
        while (postorder[p] < postorder[j]) // 两处while不用考虑边界条件，如果p增加到j会自动退出
            p++;
        int m = p; // m - 1 为当前节点的左孩子节点
        while (postorder[p] > postorder[j]) // 此处解决了my solution的局部性问题
            p++;
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    // 方式二：栈，不太理解
    public boolean verifyPostorder2(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root)
                return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }
}

/**
 * 递归保留集合数组等变量可将变量作为类变量
 */
class PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> path = new LinkedList<>();
        LinkedList<List<Integer>> res = new LinkedList<>();
        int count = 0;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            count += pop.val; // 此做法无法解决左子树突变到右子树，总值骤变为仅剩根节点
            path.addLast(pop.val);
            if (pop.left == null && pop.right == null) {
                if (count == target) {
                    res.add(path);
                }
                count -= pop.val; // 找到也需还原
                path.removeLast();
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return res;
    }

    // 方式一：回溯法，先序遍历+路径记录（深度优先）
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        recur(root, sum);
        return res;
    }

    void recur(TreeNode root, int tar) {
        if (root == null)
            return;
        path.add(root.val);
        tar -= root.val;
        if (tar == 0 && root.left == null && root.right == null)
            res.add(new LinkedList(path)); // 若直接add path，添加为引用变量，会随path的改变而改变
        recur(root.left, tar); // 左右递归，避免了my solution中值不匹配的问题
        recur(root.right, tar);
        path.removeLast(); // 此处保证了path的正确性，有进有出
    }


    // 方式二：广度优先
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();

    public List<List<Integer>> pathSum2(TreeNode root, int target) {
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queueNode = new LinkedList<TreeNode>();
        Queue<Integer> queueSum = new LinkedList<Integer>();
        queueNode.offer(root);
        queueSum.offer(0);

        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int rec = queueSum.poll() + node.val;

            if (node.left == null && node.right == null) {
                if (rec == target) {
                    getPath(node);
                }
            } else {
                if (node.left != null) {
                    map.put(node.left, node);
                    queueNode.offer(node.left);
                    queueSum.offer(rec);
                }
                if (node.right != null) {
                    map.put(node.right, node);
                    queueNode.offer(node.right);
                    queueSum.offer(rec);
                }
            }
        }
        return ret;
    }

    public void getPath(TreeNode node) {
        List<Integer> temp = new LinkedList<Integer>();
        while (node != null) {
            temp.add(node.val);
            node = map.get(node);
        }
        Collections.reverse(temp);
        ret.add(new LinkedList<Integer>(temp));
    }
}

/**
 * 二叉树中序遍历即排序
 */
class treeToDoublyList {
    // my solution: 中序遍历+队列
    private LinkedList<Node> queue = new LinkedList<Node>();

    public Node treeToDoublyList(Node root) {
        if (root == null)
            return null;
        recur(root);
        queue.peek().left = queue.getLast();
        int len = queue.size();
        for (int i = 0; i < len - 1; i++) {
            queue.get(i).right = queue.get(i + 1);
            queue.get(i + 1).left = queue.get(i);
        }
        queue.getLast().right = queue.peek();
        return queue.peek();
    }

    private void recur(Node node) {
        if (node == null)
            return;
        recur(node.left);
        queue.add(node);
        recur(node.right);
    }

    // 方式一：中序遍历，妙在直接构造链表
    Node pre, head;

    public Node treeToDoublyList1(Node root) {
        if (root == null)
            return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    void dfs(Node cur) {
        if (cur == null)
            return;
        dfs(cur.left);
        if (pre != null)  // 这里直接构造链表
            pre.right = cur;
        else
            head = cur;
        cur.left = pre; // 双向链表
        pre = cur;
        dfs(cur.right);
    }
}

/**
 * 思路为构造两种遍历的结果，由两种遍历确定二叉树
 */
class Codec {

    String serializeData;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        return null;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return null;
    }
}

class KthLargest {
    // my solution: 中序遍历取k
    private LinkedList<Integer> list = new LinkedList<>();

    public int kthLargest(TreeNode root, int k) {
        dfs(root);
        return list.get(list.size() - k);
    }

    private void dfs(TreeNode node) {
        if (node == null)
            return;
        dfs(node.left);
        list.add(node.val);
        dfs(node.right);
    }

    // 方式一：中序遍历 + 提前返回（更快）
    int res, k;

    public int kthLargest1(TreeNode root, int k) {
        this.k = k;
        dfs1(root);
        return res;
    }

    void dfs1(TreeNode root) { // 中序遍历倒序
        if (root == null)
            return;
        dfs1(root.right);
        if (k == 0)
            return;
        if (--k == 0)
            res = root.val;
        dfs1(root.left);
    }
}

/**
 * 递归返回void时，要到函数结束才返回
 * 提前返回
 */
class MaxDepth {
    // my solution: 递归遍历
    private int depth = 0, res = 0;

    public int maxDepth(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            if (depth > res)
                res = depth;
            return;
        }
        depth++;
        dfs(node.left);
        dfs(node.right);
        depth--;
    }

    // 方式一：DFS，太简洁了这个
    public int maxDepth1(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth1(root.left), maxDepth1(root.right)) + 1;
    }

    //这个是方式一的扩充版
    public int maxDepth1plus(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth1plus(root.left);
        int right = maxDepth1plus(root.right);

        return Math.max(left + 1, right + 1);
    }

    // 方式二：BFS，层数即深度
    public int maxDepth2(TreeNode root) {
        if (root == null)
            return 0;
        List<TreeNode> queue = new LinkedList<>();
        List<TreeNode> tmp;
        queue.add(root);

        int res = 0;
        while (!queue.isEmpty()) {
            tmp = new LinkedList<>();
            for (TreeNode node : queue) {
                if (node.left != null)
                    tmp.add(node.left);
                if (node.right != null)
                    tmp.add(node.right);
            }
            queue = tmp;
            res++;
        }
        return res;
    }
}

/**
 * 平衡二叉树的定义
 */
class IsBalanced {
    // my solution: 递归遍历深度（超时），原因在于没有及时回退
    boolean isBalance = true;

    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return isBalance;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;
        int i = dfs(node.left) - dfs(node.right);
        if (i >= 0 && i > 1) {
            isBalance = false;
        } else {
            if (i < -1)
                isBalance = false;
        }
        return Math.max(dfs(node.left), dfs(node.right)) + 1;
    }

    // 方式一：递归+提前返回
    public boolean isBalanced1(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null)
            return 0;
        int left = recur(root.left);
        if (left == -1)
            return -1;
        int right = recur(root.right);
        if (right == -1)
            return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    // 方式二
    public boolean isBalanced2(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int depth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}

/**
 * 二分搜索树，且每个值唯一适用
 * 若 root.val < p.val，则 p 在 root 右子树 中；
 * 若 root.val > p.val，则 p 在 root 左子树 中；
 * 若 root.val = p.val，则 p 和 root 指向 同一节点
 */
class LowestCommonAncestor {
    // 方式一：遍历，非递归
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val < p.val && root.val < q.val) // p,q 都在 root 的右子树中
                root = root.right; // 遍历至右子节点
            else if (root.val > p.val && root.val > q.val) // p,q 都在 root 的左子树中
                root = root.left; // 遍历至左子节点
            else break;
        }
        return root;
    }

    // 方式二：递归
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) // p,q 都在 root 的右子树中
            return lowestCommonAncestor(root.right, p, q); // 遍历至右子节点
        if (root.val > p.val && root.val > q.val) // p,q 都在 root 的左子树中
            return lowestCommonAncestor(root.left, p, q); // 遍历至左子节点
        return root;
    }
}

/**
 * 普通二叉树
 */
/**
 * 终止条件：
 *      当越过叶节点，则直接返回 null ；
 *      当 root等于 p, q ，则直接返回 root ；
 * 递推工作：
 *      开启递归左子节点，返回值记为 left ；
 *      开启递归右子节点，返回值记为 right ；
 * 返回值： 根据 left 和 right ，可展开为四种情况；
 *      当 left 和 right 同时为空 ：说明 root 的左 / 右子树中都不包含 p,q ，返回 null ；
 *      当 left 和 right 同时不为空 ：说明 p, q 分列在 root 的 异侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root ；
 *      当 left 为空 ，right 不为空 ：p,q 都不在 root 的左子树中，直接返回 right 。具体可分为两种情况：
 *          p,q 其中一个在 root 的 右子树 中，此时 right 指向 p/q ；
 *          p,q 两节点都在 root 的 右子树 中，此时的 right 指向最近公共祖先节点 ；
 * 当 left 不为空 ， right 为空 ：与情况 3. 同理；
 */
class LowestCommonAncestor2 {

    // 方式一：递归
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null; // 如果树为空，直接返回null
        if (root == p || root == q)
            return root; // 如果 p和q中有等于 root的，那么它们的最近公共祖先即为root（一个节点也可以是它自己的祖先）
        TreeNode left = lowestCommonAncestor(root.left, p, q); // 递归遍历左子树，只要在左子树中找到了p或q，则先找到谁就返回谁
        TreeNode right = lowestCommonAncestor(root.right, p, q); // 递归遍历右子树，只要在右子树中找到了p或q，则先找到谁就返回谁
        if (left == null)
            return right; // 如果在左子树中 p和 q都找不到，则 p和 q一定都在右子树中，右子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
        else if (right == null)
            return left; // 否则，如果 left不为空，在左子树中有找到节点（p或q），这时候要再判断一下右子树中的情况，如果在右子树中，p和q都找不到，则 p和q一定都在左子树中，左子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
        else
            return root; //否则，当 left和 right均不为空时，说明 p、q节点分别在 root异侧, 最近公共祖先即为 root
    }
}
