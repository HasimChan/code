package com.hasim.sword.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

/**
 * 记得画图
 */
public class LinkedList {
    public static void printNode(ListNode head) {
        ListNode cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.val + "->");
            cur = cur.next;
        }
        System.out.println(sb);
    }

    public static ListNode create(int start, int end) {

        if (start > end) {
            throw new RuntimeException("start > end");
        }

        ListNode head = new ListNode(start);
        ListNode cur = head;
        for (int i = start + 1; i < end + 1; i++) {
            ListNode node = new ListNode(i);
            cur.next = node;
            cur = node;
        }
        return head;
    }

    public static ListNode arrayToLinked(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode cur = head;
        for (int i = 1; i < array.length; i++) {
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class ReversePrint {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<Integer>();

        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        int[] retVal = new int[stack.size()];
        for (int i = 0; i < retVal.length; i++) {
            retVal[i] = stack.pop();
        }
        return retVal;
    }
}

class DeleteNode {
    public ListNode deleteNode(ListNode head, int val) {
        // 1. 递归
        if (head == null)
            return head;

        if (head.val == val) {
            return head.next;
        } else {
            head.next = deleteNode(head.next, val);
            return head;
        }
    }
}

class RemoveDuplicateNodes {
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

class GetKthFromEnd {

    public static void main(String[] args) {

        ListNode head = LinkedList.create(0, 10);

        LinkedList.printNode(head);

        ListNode kthFromEnd = new GetKthFromEnd().getKthFromEnd(head, 2);

        LinkedList.printNode(kthFromEnd);
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode after = head;
        ListNode before = head;
        for (int i = 0; i < k; i++) {
            after = after.next;
        }

        while (after != null) {
            after = after.next;
            before = before.next;
        }
        return before;
    }
}

class DetectCycle {

    public static void main(String[] args) {

    }

    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }
}

class ReverseList {
    public static void main(String[] args) {
        ListNode head = LinkedList.create(0, 10);

        LinkedList.printNode(head);

        ListNode listNode = new ReverseList().reverseList3(head);
        LinkedList.printNode(listNode);
    }

    // 方式一：双指针
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next; // 中间变量
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // 方式二：递归
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList2(head.next);
        head.next.next = head; // 尿分叉，很妙，想不出来
        head.next = null;
        return newHead;
    }

    // 方式三：双指针二
    public ListNode reverseList3(ListNode head) {
        ListNode cur = head;
        while (head.next != null) {
            ListNode next = head.next.next;
            head.next.next = cur;
            cur = head.next;
            head.next = next;
        }
        return cur;
    }
}

class MergeTwoLists {

    public static void main(String[] args) {
        int[] array1 = {1, 2, 4};
        int[] array2 = {1, 3, 4};

        ListNode l1 = LinkedList.arrayToLinked(array1);
        ListNode l2 = LinkedList.arrayToLinked(array2);

        LinkedList.printNode(l1);
        LinkedList.printNode(l2);

        ListNode node = new MergeTwoLists().mergeTwoLists(l1, l2);
        LinkedList.printNode(node);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum; // 辅助头节点
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }
}

class CopyRandomList {

    public static void main(String[] args) {

    }

    public Node copyRandomList(Node head) {
        /*
        通过Hash表建立映射关系
         */
        if (head == null)
            return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        // 4. 构建新链表的 next 和 random 指向
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 5. 返回新链表的头节点
        return map.get(head);
    }

    public Node copyRandomList2(Node head) {
        if (head == null)
            return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表
        while (cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while (cur != null) {
            if (cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while (cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }
}

class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();

        ListNode cur = headA;
        while (cur != null) {
            set.add(cur);
            cur = cur.next;
        }

        cur = headB;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            cur = cur.next;
        }

        return null;
    }

    // 双指针
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // A：a + c
        // B：b + c
        // pA：a + c + b + c
        // pB：b + c + a + c
        // if 相交：a + c + b = b + c + a -> 交点
        // if 不相交：a + c + b + c = b + c + a + c -> null
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}

