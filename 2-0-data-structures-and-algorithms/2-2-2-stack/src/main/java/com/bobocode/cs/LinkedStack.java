package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;

/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedStack<T> implements Stack<T> {

    private Node<T> stackHead;
    private int size;

    public LinkedStack(Node<T> initNode) {
        this.stackHead = initNode;
        size = 1;

        Node<T> currNode = stackHead;

        while(currNode.hasNext()) {
            size++;
            currNode = currNode.getNext();
        }
    }

    public LinkedStack() { }
    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        int n = elements.length;

        if(n == 0) return null;

        Node<T> first = new Node<T>(elements[0]);
        Node<T> prev = first;

        for (int i = 1; i < n; i++) {
            Node<T> newNode = new Node<T>(elements[i]);
            prev.addNext(newNode);
            prev = newNode;
        }

        return new LinkedStack<T>(first);
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        if (element == null) throw new NullPointerException();
        Node<T> newNode = new Node(element, stackHead);
        this.stackHead = newNode;
        size++;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        if (stackHead==null) throw new EmptyStackException();
        Node<T> nextNode = stackHead.getNext();
        T value = stackHead.getValue();
        stackHead = nextNode;
        size--;
        return value;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return stackHead == null;
    }


    static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public void addNext(Node<T> node) {
            this.next = node;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return next;
        }

        public boolean hasNext() {
            return next != null;
        }
    }
}
