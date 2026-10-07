package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.EmptyStackException;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> a generic parameter
 * @author Taras Boychuk
 * @author Ivan Virchenko
 */
public class LinkedQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedQueue() {
        size = 0;
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
        if(head == null) {
            head = new Node<>(element);
            tail = head;
            size = 1;
        }
        else {
            Node<T> newNode = new Node<>(element);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    public T poll() {
        if (head == null) return null;

        T result = head.value;
        head = head.next;
        if(head == null) tail = null;
        size--;
        return result;
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        return head == null;
    }

    static class Node<T> {
        private T value;
        private Node<T> next;

        public Node() {}

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
