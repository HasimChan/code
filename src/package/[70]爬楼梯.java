//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 1859 👎 0

//2021/09/07
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 分析：假设前面n-1层的所有可能情况都已经确定
    // 则第n层的所有可能情况数量为 nums[n - 1] + nums[n - 2]
    // 原因：只有两种情况，爬1格/2格
    //      假设爬上第n层的最后一步是1格，则所有可能情况为nums[n - 1]
    //      假设爬上第n层的最后一步是2格，则所有可能情况为nums[n - 2]
    // 所以其实就是斐波那契数列的变种(n = 2时为2)
    // 状态定义: dp数组用3个变量代替，一个代表n-2处的所有可能情况数量，一个代表n-1处的所有可能情况数量，一个代表n处的所有可能情况数量
    // 转移方程: cur = pp + p，并更新pp，p
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int pp = 0, p = 1, cur = 2;
        for (int i = 3; i <= n; i++) {
            pp = p;
            p = cur;
            cur = pp + p;
        }
        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
