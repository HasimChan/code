package com.hasim.sword.doublepointer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 双指针：（对撞指针、快慢指针） --> 链表、数组
 * 1. 对撞指针
 * 对撞指针是指在有序数组中，将指向最左侧的索引定义为左指针(left)，最右侧的定义为右指针(right)，然后从两头向中间进行数组遍历
 * 对撞数组适用于有序数组，也就是说当你遇到题目给定有序数组时，应该第一时间想到用对撞指针解题
 * 2. 快慢指针
 * 快慢指针的两个指针从同一侧开始遍历数组，两个指针以不同的策略移动，直到两个指针的值相等（或其他特殊条件）为止
 * 3. 滑动窗口
 */
public class DoublePointer {

}

class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 17;

        int[] ints = new TwoSum().twoSum(nums, target);
        // Arrays.toString(array) 打印数组内容
        System.out.println(Arrays.toString(ints));
    }

    /*
    有序数组 -> 对撞指针
     */
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            if (target - nums[left] == nums[right]) {
                return new int[]{nums[left], nums[right]};
            } else if (target - nums[left] > nums[right]) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }
}

/**
 * 数组也能作为泛型类型传入 -> List<int[]> vec
 */
class FindContinuousSequence {

    public static void main(String[] args) {
        for (int[] ints : new FindContinuousSequence().findContinuousSequence(94527)) {
            System.out.println(Arrays.toString(ints));
        }
    }

    // 方式一：重头来过，无双指针
    public int[][] findContinuousSequence1(int target) {
        List<int[]> vec = new ArrayList<>();
        int sum = 0, limit = (target - 1) / 2; // (target - 1) / 2 等效于 target / 2 下取整
        for (int i = 1; i <= limit; ++i) {
            for (int j = i; ; ++j) {
                sum += j;
                if (sum > target) { // 在循环中累加，效率较每次移动指针都累加高
                    sum = 0;
                    break;
                } else if (sum == target) {
                    int[] res = new int[j - i + 1];
                    for (int k = i; k <= j; ++k) {
                        res[k - i] = k;
                    }
                    vec.add(res);
                    sum = 0;
                    break;
                }
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    // 方式二：数学方法（首尾两数得序列之和 = target，知首求尾 => 二次方程）
    public int[][] findContinuousSequence2(int target) {
        List<int[]> vec = new ArrayList<>();
        int sum = 0, limit = (target - 1) / 2; // (target - 1) / 2 等效于 target / 2 下取整
        for (int x = 1; x <= limit; ++x) {
            long delta = 1 - 4 * (x - (long) x * x - 2 * target);
            if (delta < 0) {
                continue;
            }
            int delta_sqrt = (int) Math.sqrt(delta + 0.5);
            if ((long) delta_sqrt * delta_sqrt == delta && (delta_sqrt - 1) % 2 == 0) {
                int y = (-1 + delta_sqrt) / 2; // 另一个解(-1-delta_sqrt)/2必然小于0，不用考虑
                if (x < y) {
                    int[] res = new int[y - x + 1];
                    for (int i = x; i <= y; ++i) {
                        res[i - x] = i;
                    }
                    vec.add(res);
                }
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    // 方式三：滑动窗口
    public int[][] findContinuousSequence3(int target) {
        List<int[]> vec = new ArrayList<>();
        for (int l = 1, r = 2; l < r; ) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    res[i - l] = i;
                }
                vec.add(res);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    // my solution: 双指针（滑动窗口），每移动一次指针都要进行 for 循环计算，太费时 -> 优化累加算法即可
    public int[][] findContinuousSequence(int target) {
        int left = 1;
        int right = 1;
        ArrayList list = new ArrayList();

        while (left < target && right < target) {
            if (addList(left, right) == target) {
                list.add(createArray(left, right));
                left++;
            } else if (addList(left, right) < target) {
                right++;
            } else if (addList(left, right) > target) {
                left++;
            }
        }
        int[][] ret = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = (int[]) list.get(i);
        }
        return ret;
    }

    private int addList(int l, int r) {
        return (l + r) * (r - l + 1) / 2;
    }

    private int[] createArray(int left, int right) {
        int len = right - left + 1;
        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            ret[i] = left + i;
        }
        return ret;
    }
}

/**
 * 从后往前遍历有时可以节省内存开销（栈）
 */
class ReverseWords {
    /**
     * 1. String 类工具函数
     * trim()：删除首尾空格
     * split(String str)：以 str 分割字符串，返回字符串数组
     * substring(l, r)：返回子串（左闭右开）
     */

    /**
     * 2. 正则表达式
     */
    public static void main(String[] args) {
        String str = "     I  am  a  student. ";
        System.out.println(new ReverseWords().reverseWords2(str));
    }

    // 方式一：双指针
    public String reverseWords1(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while (i >= 0) { // 从后往前遍历，不用增加其他数据结构进行倒转（如栈）
            while (i >= 0 && s.charAt(i) != ' ')
                i--; // 搜索首个空格
            res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
            while (i >= 0 && s.charAt(i) == ' ')
                i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }

    // 方式二：库函数法（分割字符串）
    public String reverseWords2(String s) {
        String[] strs = s.trim().split(" "); // 删除首尾空格，分割字符串 （可用正则表达式匹配多个空格 "\\s+"，"\s" 表示空格， "+" 表示多个）
        System.out.println(strs.length);
        StringBuilder res = new StringBuilder();
        for(int i = strs.length - 1; i >= 0; i--) { // 倒序遍历单词列表
            if(strs[i].equals(""))
                continue; // 遇到空单词则跳过
            res.append(strs[i] + " "); // 将单词拼接至 StringBuilder
        }
        return res.toString().trim(); // 转化为字符串，删除尾部空格，并返回
    }

    // my solution: 从前往后遍历，增加了开销，思路没问题
    public String reverseWords(String s) {
        int l = 0;
        int r = 0;
        int len = s.length();
        Stack<String> stack = new Stack<>();

        while (l < len && r < len) {
            while (l < len && s.charAt(l) == ' ') {
                l++;
            }
            r = l;

            while (r < len && s.charAt(r) != ' ') {
                r++;
            }
            if (l != r) {
                stack.push(s.substring(l, r));
            } else {
                break;
            }
            l = r;
        }
        String ret = "";

        while (!stack.empty()) {
            if (ret == "") {
                ret = stack.pop();
            } else {
                ret += " " + stack.pop();
            }
        }

        return ret;
    }
}

class ReverseLeftWords {
    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;

        System.out.println(new ReverseLeftWords().reverseLeftWords2(s, k));
    }

    // 方式一：my solution，字符串切片
    public String reverseLeftWords1(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

    // 方式二：遍历（若不使用字符串切片函数），也可用字符串作缓存对象
    public String reverseLeftWords2(String s, int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            builder.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            builder.append(s.charAt(i));
        }
        return builder.toString();

/*        StringBuilder res = new StringBuilder(); // 改进：用求余减少for语句
        for(int i = n; i < n + s.length(); i++)
            res.append(s.charAt(i % s.length()));
        return res.toString();*/
    }
}
