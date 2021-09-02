package com.hasim.extra.testother.collection.set;

import java.util.*;

public class HashSetStructure {
    Node[] table = new Node[16];

    public static void main(String[] args) {
        LinkedHashSet<String> strings = new LinkedHashSet<>();
        strings.add("abc");
    }
}

class Node {
    private Object item;
    private Node next;

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }
}

class TestLinkedList {
    private int a;
    private static int z;
    private String k;

    public TestLinkedList() {
        z = 20;
        a = 66;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(6);
        list.add(5);
        list.add(1);
        list.add(4);
        list.add(2);

        System.out.println(list);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    private class IterTest {
        private TestLinkedList testLinkedList;
        public void test() {
            testLinkedList = new TestLinkedList();
            int b = testLinkedList.a;
            int k = testLinkedList.z;
            System.out.println(b);
            System.out.println(k);
        }
    }
}
