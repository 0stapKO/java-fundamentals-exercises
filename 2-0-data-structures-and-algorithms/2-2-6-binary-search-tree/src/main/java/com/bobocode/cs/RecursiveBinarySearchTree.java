package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    Node<T> root;
    int size = 0;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {

        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<T>();

        if(elements.length > 0) {
            tree.root =  new Node(elements[0]);
            tree.size = 1;
            for (int i = 1; i < elements.length; i++) {
                tree.insert(elements[i]);
            }
        }

        return tree;
    }

    @Override
    public boolean insert(T element) {
        if(root == null) {
            root = new Node<>(element);
            size = 1;
            return true;
        }
        return inertHelper(element, root);
    }

    private boolean inertHelper(T element, Node<T> current) {
        switch (Integer.signum(element.compareTo(current.value))) {
            case -1:
                if (current.hasLeft()) return inertHelper(element, current.left);
                else {
                    current.left = new Node<T>(element);
                    size++;
                    return true;
                }
            case 1:
                if (current.hasRight()) return inertHelper(element, current.right);
                else {
                    current.right = new Node<T>(element);
                    size++;
                    return true;
                }
            case 0: return false;
            default: return false;
        }
    }

    @Override
    public boolean contains(T element) {
        return containsHelper(element, root);
    }

    private boolean containsHelper(T element, Node<T> current) {
        switch (Integer.signum(element.compareTo(current.value))) {
            case -1:
                if (current.hasLeft()) return containsHelper(element, current.left);
                return false;
            case 1:
                if (current.hasRight()) return containsHelper(element, current.right);
                return false;
            case 0: return true;
            default: return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return depthHelper(root);
    }

    private int depthHelper(Node<T> currentNode) {
        if(currentNode == null) return 0;
        int depthLeft = currentNode.hasLeft() ? depthHelper(currentNode.left) + 1 : 0;
        int depthRight = currentNode.hasRight() ?  depthHelper(currentNode.right) + 1 : 0;

        return depthLeft > depthRight ? depthLeft : depthRight;
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversalHelper(consumer, root);
    }

    private void inOrderTraversalHelper(Consumer<T> consumer, Node<T> current) {
        if(current.hasLeft()) inOrderTraversalHelper(consumer, current.left);
        consumer.accept(current.value);
        if(current.hasRight()) inOrderTraversalHelper(consumer, current.right);
    }

    static class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public void addLeft(Node<T> node) {
            this.left = node;
        }

        public void addRight(Node<T> node) {
            this.right = node;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public Node<T> getRight() {
            return right;
        }

        public boolean hasLeft() {
            return left != null;
        }

        public boolean hasRight() {
            return right != null;
        }
    }

    public static void main(String[] args) {
        RecursiveBinarySearchTree<Integer> tree = RecursiveBinarySearchTree.of(10, 5, 15, 1, 20);
        System.out.println(tree.depth());
    }
}
