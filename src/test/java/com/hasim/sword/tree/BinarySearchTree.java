package com.hasim.sword.tree;

public class BinarySearchTree<T extends Comparable<? super T>> {
    private static class BinaryNode<T> {
        public BinaryNode(T element) {
            this(element, null, null);
        }

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
    }

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null)
            return false;

        int compareTo = x.compareTo(t.element);

        if (compareTo < 0)
            return contains(x, t.left);
        else if (compareTo > 0)
            return contains(x, t.right);
        else
            return true;
    }

    public T findMix() {
        if (isEmpty())
            throw new RuntimeException();
        return findMix(root).element;
    }

    public T findMax() {
        if (isEmpty())
            throw new RuntimeException();
        return findMax(root).element;
    }

    /**
     * 递归：
     * 1. 返回递归结束的最终结果
     * 2. 返回递的最终结果
     * 不同类型递归的写法？
     */
    private BinaryNode<T> findMix(BinaryNode<T> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMix(t.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    /**
     * 插入总是在叶子节点
     * @param x
     */
    public void insert(T x) {
        root = insert(x, root);
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }

        int compareTo = x.compareTo(t.element);

        if (compareTo < 0)
            insert(x, t.left);
        else if (compareTo > 0)
            insert(x, t.right);

        return t; // 此处的 t 只有归的最后一次有意义，其他都为凑数
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null)
            return t;

        int compareTo = x.compareTo(t.element);

        if (compareTo < 0)
            t.left = remove(x, t.left);
        else if (compareTo > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) { // 俩个孩子节点
            t.element = findMix(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right; // 叶子节点也属于这种情况
        return t; // 此处的 t 只有归的最后一次有意义，其他都为凑数
    }
}
