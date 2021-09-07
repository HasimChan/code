//给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。 
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。 
//
// 
//
// 
//
// 示例 1: 
//
// 
//输入: rowIndex = 3
//输出: [1,3,3,1]
// 
//
// 示例 2: 
//
// 
//输入: rowIndex = 0
//输出: [1]
// 
//
// 示例 3: 
//
// 
//输入: rowIndex = 1
//输出: [1,1]
// 
//
// 
//
// 提示: 
//
// 
// 0 <= rowIndex <= 33 
// 
//
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？ 
// Related Topics 数组 动态规划 👍 318 👎 0

//2021/09/07
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // my solution: 递推，用两个变量保存上一层和当前层
    public List<Integer> getRow(int rowIndex) {
        LinkedList<Integer> pre = new LinkedList<>();
        pre.add(1);
        if (rowIndex == 0)
            return pre;

        pre.add(1);
        if (rowIndex == 1)
            return pre;

        for (int i = 2; i <= rowIndex; i++) {
            int len = pre.size();
            LinkedList<Integer> temp = new LinkedList<>();
            for (int j = 0; j < len - 1; j++) {
                temp.add(pre.get(j) + pre.get(j + 1));
            }
            temp.addFirst(1);
            temp.addLast(1);
            pre = temp;
        }
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
