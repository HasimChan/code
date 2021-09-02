//给定一棵二叉搜索树，请找出其中第k大的节点。 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//输出: 4 
//
// 示例 2: 
//
// 输入: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//输出: 4 
//
// 
//
// 限制： 
//
// 1 ≤ k ≤ 二叉搜索树元素个数 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 197 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//2021/09/01
class Solution {
    // 方式一：中序遍历 + 提前返回（更快）
    int res, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs1(root);
        return res;
    }

    void dfs(TreeNode root) { // 中序遍历倒序
        if (root == null)
            return;
        dfs(root.right);
        if (k == 0)
            return;
        if (--k == 0)
            res = root.val;
        dfs(root.left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
