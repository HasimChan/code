package com.hasim.hot.array;

import java.util.HashMap;
import java.util.Map;

public class ArrayCode {
}

/**
 * 两数之和(哈希表)
 */
class TwoSum {
    // my solution：哈希表 + 两次遍历
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < len; i++) {
            Integer k = map.get(target - nums[i]);
            if (k != null && k != i) {
                return new int[]{i, k};
            }
        }
        return null;
    }

    // 方式一：哈希表 + 一次遍历
    public int[] twoSum1(int[] nums, int target) { // 仅一次遍历
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
