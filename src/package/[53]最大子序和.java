//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：nums = [-1]
//输出：-1
// 
//
// 示例 5： 
//
// 
//输入：nums = [-100000]
//输出：-100000
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治 动态规划 👍 3663 👎 0

//2021/09/07
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // my solution: 动态规划
    // 状态定义: dp数组用两个变量代替，一个代表当前遍历的序列中和的最大值，一个代表以前一个元素为右边界的序列的最大值
    // 转移方程: pre = max(nums[i], pre + nums[i])，并更新max
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int pre = nums[0], max = pre;
        for (int i = 1; i < len; i++) {
            pre = Math.max(nums[i], pre + nums[i]);
            if (pre > max)
                max = pre;
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
