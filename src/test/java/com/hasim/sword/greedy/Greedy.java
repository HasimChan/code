package com.hasim.sword.greedy;

/**
 * @Description
 * @Author Hasim
 * @Date 2021/9/6 19:52
 * @Version 1.0
 */
public class Greedy {

}

/**
 * 算术几何均值不等式
 * (A + B)/2 >= sqrt(A * B)
 */
class CuttingRope {
    // 方式一：贪心思想（求导）
    public int cuttingRope1(int n) {
        if (n <= 3)
            return n - 1;
        int a = n / 3, b = n % 3;
        if (b == 0)
            return (int) Math.pow(3, a);
        if (b == 1)
            return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }

    /*
        我们想要求长度为n的绳子剪掉后的最大乘积，可以从前面比n小的绳子转移而来
        用一个dp数组记录从0到n长度的绳子剪掉后的最大乘积，也就是dp[i]表示长度为i的绳子剪成m段后的最大乘积，初始化dp[2] = 1
        我们先把绳子剪掉第一段（长度为j），如果只剪掉长度为1，对最后的乘积无任何增益，所以从长度为2开始剪
        剪了第一段后，剩下(i - j)长度可以剪也可以不剪。如果不剪的话长度乘积即为j * (i - j)；如果剪的话长度乘积即为j * dp[i - j]。取两者最大值max(j * (i - j), j * dp[i - j])
        第一段长度j可以取的区间为[2,i)，对所有j不同的情况取最大值，因此最终dp[i]的转移方程为
        dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))
        最后返回dp[n]即可
     */

    // 方式二：动态规划
    public int cuttingRope2(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            for (int j = 2; j < i; j++) { // 这里存在对数组同一位置元素重复迭代的过程
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}

/**
 * 何时适用？
 * 怎么用？
 * 不做重复工作
 *
 * 状态定义：dp数组的每个元素代表什么？dp数组有时也可用一个数值代替(最大/最小)
 * 转移方程：怎么算出dp数组元素的值
 * 初始状态
 * 返回值
 */
class MaxProfit {
    // my solution: 暴力破解，O(n^2)，因为第二次遍历中出现了一些重复操作，导致效率降低
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            int buy = prices[i];
            for (int j = i + 1; j < len; j++) {
                int r = prices[j] - buy;
                if (res < r)
                    res = r;
            }
        }
        return res;
    }

    // 方式一：动态规划（减小了暴力破解需要遍历的case）
    public int maxProfit1(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) { // 第i日最大利润 = max(前i - 1日最大利润, 第i日售价 - 前i-1日最低价格)
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }
}

class CuttingRope2 {
    public int cuttingRope(int n) {
        if (n <= 2)
            return 1;
        if (n == 3)
            return 2;
        int a = n / 3, b = n % 3;
        if (b == 0)
            return (int) Math.pow(3, a);
        if (b == 1)
            return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }
}
