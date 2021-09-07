//给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。 
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
//输入: numRows = 5
//输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 
//
// 示例 2: 
//
// 
//输入: numRows = 1
//输出: [[1]]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= numRows <= 30 
// 
// Related Topics 数组 动态规划 👍 567 👎 0

//2021/09/07
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // my solution: 动态规划好像也不算，逐层生成
    // 第1、2层固定，为[1],[1, 1]
    // 第三层开始，从集合中取出最后一层(当前层的上一层)，遍历并把相邻两数相加，添加到列表中
    // 在列表头尾添加1
    // 把列表加入集合
    public List<List<Integer>> generate(int numRows) {
        LinkedList<List<Integer>> rows = new LinkedList<>();
        rows.add(Arrays.asList(new Integer[]{1}));
        if (numRows == 1)
            return rows;
        rows.add(Arrays.asList(new Integer[]{1, 1}));
        if (numRows == 2)
            return rows;

        for (int i = 3; i <= numRows; i++) {
            LinkedList<Integer> row = new LinkedList<>();
            List<Integer> pre = rows.getLast();
            int len = pre.size();
            for (int j = 0; j < len - 1; j++) {
                row.add(pre.get(j) + pre.get(j + 1));
            }
            row.addFirst(1);
            row.addLast(1);
            rows.addLast(row);
        }
        return rows;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
