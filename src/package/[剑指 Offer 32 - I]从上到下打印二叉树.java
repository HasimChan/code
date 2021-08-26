//从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。 
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回： 
//
// [3,9,20,15,7]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 116 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] levelOrder(TreeNode root) {
        if (root == null)
            return new int[0];
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> array = new ArrayList<>();
        queue.add(root);
        while (queue.size() != 0) {
            TreeNode pop = queue.pop();
            array.add(pop.val);
            if (pop.left != null)
                queue.add(pop.left);
            if (pop.right != null)
                queue.add(pop.right);
        }
        int len = array.size();
        int[] retArr = new int[len];
        for (int i = 0; i < len; i++) {
            retArr[i] = array.get(i);
        }
        return retArr;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
