package com.hasim.testcode;


import org.junit.Test;

import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestCode {

    @Test
    public void testReplaceSpace() {
//        Test test = new Test();
//        System.out.println(test.replaceSpace("We are happy."));
//        test.test1(new StringBuffer("We are happy."));

//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
//        int[][] matrix = {{7},{9},{6}};
//        System.out.println(test.spiralOrder(matrix));

//        String str = "cc";
//        System.out.println(this.firstUniqChar2(str));
//        int[] pushed = {1, 2, 3, 4, 5};
//        int[] poped = {4, 5, 3, 2, 1};
//        System.out.println(this.validateStackSequences(pushed, poped));

        // 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
//
//
// 示例 2：
//
// 输入：arr = [0,1,2,1], k = 1

    }

    public static void main(String[] args) {

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }

    }

    /**
     * my Explanation
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                builder.append("%20");
            } else {
                builder.append(ch);
            }
        }

        return builder.toString();
    }

    /**
     * [1,2,3,4]
     * [5,6,7,8]
     * [9,10,11,12]
     */

    /**
     * Explanation in github
     *
     * @param str
     * @return
     */
    public String test1(StringBuffer str) {
        int P1 = str.length() - 1;
        for (int i = 0; i <= P1; i++)
            if (str.charAt(i) == ' ')
                str.append("  ");
        System.out.println(str.append("-"));

        int P2 = str.length() - 1;
        while (P1 >= 0 && P2 > P1) {
            char c = str.charAt(P1--);
            if (c == ' ') {
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
            } else {
                str.setCharAt(P2--, c);
            }
        }
        return str.toString();
    }

    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        int up = 0;
        int down = matrix.length;
        int left = 0;
        int right = matrix[0].length;

        ArrayList<Integer> ret = new ArrayList<Integer>();
        Integer[] ints = new Integer[matrix.length * matrix[0].length];

        while (up < down && left < right) {
            for (int i = left; i < right; i++) {
                ret.add(matrix[up][i]);
            }

            if (up + 1 >= down) {
                break;
            }

            for (int i = up + 1; i < down; i++) {
                ret.add(matrix[i][right - 1]);
            }

            if (right - 2 < left) {
                break;
            }

            for (int i = right - 2; i >= left; i--) {
                ret.add(matrix[down - 1][i]);
            }

            for (int i = down - 2; i > up; i--) {
                ret.add(matrix[i][left]);
            }
            up++;
            down--;
            left++;
            right--;
        }
        return ret;
    }

    public char firstUniqChar(String s) {
        LinkedHashMap<Character, Boolean> map = new LinkedHashMap<Character, Boolean>();

        for (char ch : s.toCharArray()) {
            if (map.get(ch) == null) {
                map.put(ch, false);
            } else {
                map.put(ch, true);
            }
        }

        Iterator<Map.Entry<Character, Boolean>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Character, Boolean> next = iterator.next();
            if (next.getValue() == false) {
                return next.getKey();
            }
        }

        return ' ';
    }

    public char firstUniqChar2(String s) {
        ArrayList<Character> list = new ArrayList<Character>();
        boolean[] vals = new boolean[s.length()];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int index = list.indexOf(ch);
            if (index == -1) {
                list.add(ch);
                vals[i] = false;
            } else {
                vals[index] = true;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (vals[i] == false) {
                return list.get(i);
            }
        }

        return ' ';
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        if (pushed.length == popped.length) {
            Stack<Integer> stack = new Stack<Integer>();

            int push = 0;
            int pop = 0;
            while (pop < popped.length) {
                if (stack.isEmpty() || stack.peek() != popped[pop]) {
                    if (push < pushed.length) {
                        stack.push(pushed[push++]);
                    } else {
                        break;
                    }
                } else {
                    stack.pop();
                    pop++;
                }
            }
            return stack.isEmpty() ? true : false;
        }
        return false;
    }

    public int[] getLeastNumbers(int[] arr, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                queue.add(arr[i]);
                continue;
            }
            if (queue.peek() > arr[i]) {
                queue.remove();
                queue.add(arr[i]);
            }
        }

        int[] ret = new int[k];

        for (int i = 0; i < k; i++) {
            ret[i] = queue.remove();
        }

        return ret;
    }
}

// 1 2 3 4 5

class CQueue {

    private Stack<Integer> buffer;
    private Stack<Integer> master;

    public CQueue() {
        buffer = new Stack<Integer>();
        master = new Stack<Integer>();
    }

    public void appendTail(int value) {
        master.push(value);
    }

    public int deleteHead() {
        if (buffer.isEmpty()) {
            if (master.isEmpty()) {
                return -1;
            } else {
                while (!master.isEmpty()) {
                    buffer.push(master.pop());
                }
            }
        }

        return buffer.pop();
    }
}

class CQueue2 {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public CQueue2() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        // 如果第二个栈为空
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            return -1;
        } else {
            int deleteItem = stack2.pop();
            return deleteItem;
        }

    }
}

class MinStack {

    private ArrayList<Integer> stack;
    private int min;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new ArrayList<Integer>();
    }

    public void push(int x) {
        if (stack.size() == 0) {
            min = x;
        } else {
            if (x < min) {
                min = x;
            }
        }

        stack.add(x);
    }

    public void pop() {
        if (stack.get(stack.size() - 1) == min) {
            stack.remove(stack.size() - 1);
            if (stack.size() == 0) {
                return;
            }
            min = stack.get(0);
            for (int val : stack) {
                if (val < min) {
                    min = val;
                }
            }
        } else {
            stack.remove(stack.size() - 1);
        }
    }

    public int top() {
        return stack.get(stack.size() - 1);
    }

    public int min() {
        return min;
    }
}

class TopK {

    public static void main(String[] args) {


    }


    public int[] getLeastNumbers(int[] arr, int k) {
        // 方式3：快排思想（效率最高）
        randomizedSelected(arr, 0, arr.length - 1, k);
        int[] vec = new int[k];
        for (int i = 0; i < k; ++i) {
            vec[i] = arr[i];
        }
        return vec;
    }

    // 方式3
    private void randomizedSelected(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return;
        }
        int pos = randomizedPartition(arr, l, r);
        int num = pos - l + 1;
        if (k == num) {
            return;
        } else if (k < num) {
            randomizedSelected(arr, l, pos - 1, k);
        } else {
            randomizedSelected(arr, pos + 1, r, k - num);
        }
    }

    // 基于随机的划分
    private int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l;
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*
["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
			[[],[-1],[],[-2],[],[-3],[],[-4],[],[-5],[]]
			测试结果:[null,null,-1.00000,null,-1.50000,null,-1.00000,null,-2.50000,null,-2.00000]
			期望结果:[null,null,-1.0,    null,-1.5,    null,-2.0,    null,-2.5,    null,-3.0]
 */

class MedianFinder {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        medianFinder.addNum(-2);
        medianFinder.addNum(-3);
        medianFinder.addNum(-4);
        medianFinder.addNum(-5);

        System.out.println(medianFinder.findMedian());
    }

    private ArrayList<Integer> list;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        list.add(num);
    }

    public double findMedian() {

        Integer[] array = (Integer[]) list.toArray();
        Arrays.sort(array);
        int index = array.length / 2;

        return list.size() % 2 == 0 ? (((double) array[index - 1] + (double) array[index]) / 2.0) : (double) array[index];

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;

        int[] ints = new Solution().reversePrint(node1);

        System.out.println(ints);

    }

    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<Integer>();

        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            System.out.println(cur.val);

            cur = cur.next;

        }

//        System.out.println(stack.size());

        int[] retVal = new int[stack.size()];
        for (int i = 0; i < retVal.length; i++) {
            retVal[i] = stack.pop();
            System.out.println(retVal[i]);
        }
        return retVal;
    }
}

class SolutionLinked {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;



        list(new SolutionLinked().deleteNode(node1, 1));
//        list(node1);
    }

    public static void list(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }

        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                return head;
            }
            cur = cur.next;
            pre = pre.next;
        }

        return head;
    }

/*    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        HashMap.Node<K,V>[] tab; HashMap.Node<K,V> p; int n, i; // 辅助变量
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            HashMap.Node<K,V> e; K k;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof HashMap.TreeNode)
                e = ((HashMap.TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }*/

}

class SolutionRemoveNode {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        new SolutionRemoveNode().removeDuplicateNodes(node1);

        print(node1);

    }

    public static void print(ListNode head) {
        ListNode cur = head;

        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        HashSet<Integer> set = new HashSet<>();
        set.add(pre.val);

        while (cur != null) {
            if (set.contains(cur.val)) {
                pre.next = pre.next.next;
            } else {
                set.add(cur.val);
                pre = pre.next;
            }
            cur = cur.next;
        }
        return head;
    }
}

class QuitSort {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        new QuitSort().quitSort(arr, 0, arr.length - 1);
        printArr(arr);
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public void quitSort(int[] arr, int l, int r) {
        int index = searchIndex(arr, l, r);

        if (l < r && index > l && index < r) {
            quitSort(arr, l, index - 1);
            quitSort(arr, index + 1, r);
        } else {
            return;
        }
    }

    public int searchIndex(int[] arr, int l, int r) {
        int temp = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r] > temp) {
                    r--;
                } else {
                    arr[l] = arr[r];
                    break;
                }
            }

            while (l < r) {
                if (arr[l] < temp) {
                    l++;
                } else {
                    arr[r] = arr[l];
                    break;
                }
            }
            System.out.println("*");
        }
        arr[l] = temp;
        return l;
    }
}
