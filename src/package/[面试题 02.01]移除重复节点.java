//编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。 
//
// 示例1: 
//
// 
// 输入：[1, 2, 3, 3, 2, 1]
// 输出：[1, 2, 3]
// 
//
// 示例2: 
//
// 
// 输入：[1, 1, 1, 1, 2]
// 输出：[1, 2]
// 
//
// 提示： 
//
// 
// 链表长度在[0, 20000]范围内。 
// 链表元素在[0, 20000]范围内。 
// 
//
// 进阶： 
//
// 如果不得使用临时缓冲区，该怎么解决？ 
// Related Topics 哈希表 链表 双指针 
// 👍 110 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//2021/6/30

import java.util.HashSet;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
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
//leetcode submit region end(Prohibit modification and deletion)
