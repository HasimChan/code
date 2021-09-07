package com.hasim.comment.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author Hasim
 * @Date 2021/9/7 20:15
 * @Version 1.0
 */
public class DP {
}

class Fib {
    // my solution: 动态规划
    // 状态定义: dp[n] = fib[n]
    // 转移方程:
    //    n <= 2: 1
    //    else: fib(n) = fib(n - 1) + fib(n - 2)
    int[] dp;

    public int fib(int n) {
        if (n == 0)
            return 0;
        dp = new int[n];
        return recur(n - 1);
    }

    private int recur(int n) {
        if (n <= 1)
            dp[n] = 1;
        if (dp[n] == 0)
            dp[n] = recur(n - 1) + recur(n - 2);
        return dp[n];
    }

    // 方式一：动态规划，滚动数组优化空间复杂度为O(1)
    public int fib1(int n) {
        if (n < 2) {
            return n;
        }
        // |p|q|r|
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
    // 方式二：矩阵快速幂，方式三：通项公式，我看不懂，但我大受震撼，原来递归只配我这样的辣鸡才会写
}

class MaxSubArray {
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

    // 方式一：动态规划，与my solution思想一样
    public int maxSubArray1(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    // 方式二：分治法，还没看
}

class ClimbStairs {
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

    // 方式一：动态规划，思想同my solution，不同在于将n <= 2的情况合并到for循环中
    public int climbStairs1(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    // 方式二：矩阵快速幂，方式三：通项公式
    // 看完官方题解，我发现，像这么经典的动态规划题你再用动态规划解的话就弱了，一定要秀一把自己的数学肌肉才行
    // 建议这题可以加上“数学”的标签了。没有学好线性代数的我留下了没有数学的眼泪
}

class Generate {
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

    // 方式一：数学法，与my solution差不多
    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }
}

class GetRow {
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

    // v1.0：存储整个杨辉三角，最后返回最后一行，中间求解每一行与my solution类似
    public List<Integer> getRow1(int rowIndex) {
        List<List<Integer>> C = new ArrayList<List<Integer>>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(C.get(i - 1).get(j - 1) + C.get(i - 1).get(j));
                }
            }
            C.add(row);
        }
        return C.get(rowIndex);
    }

    // v2.0：优化v1.0的空间复杂度，用两个变量存储前一层和当前层，同my solution
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> pre = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> cur = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return pre;
    }

    // v3.0：通过对每一行倒着计算，只需用一个列表即可存储
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add(0);
            for (int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

}
