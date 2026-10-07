package com.bobocode.cs;


import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        size = 0;
    }

    public LinkedList(Node<T> first) {
        head = first;
        size = 1;

        Node<T> currNode = head;

        while(currNode.hasNext()) {
            size++;
            currNode = currNode.getNext();
        }

        tail = currNode;
    }


    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        int n = elements.length;

        if(n == 0) return null;

        Node<T> first = new Node<T>(elements[0]);
        Node<T> last = first;

        for (int i = 1; i < n; i++) {
            Node<T> newNode = new Node<T>(elements[i]);
            last.addNext(newNode);
            last = newNode;
        }

        LinkedList<T> list = new LinkedList<>();
        list.head = first;
        list.tail = last;
        list.size = n;
        return list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if(tail == null) {
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
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if(index < 0) throw new IndexOutOfBoundsException();

        if(index == 0) {
            head = new Node<>(element, head);
            size++;
            return;
        }
        if(index == size) {
            Node<T> newNode = new Node(element);
            tail.next = newNode;
            tail = newNode;
            size++;
            return;
        }

        Node<T> currNode = head;
        Node<T> prevNode = null;

        for(int i = 0; i < index; i++) {
           if(!currNode.hasNext()) throw new IndexOutOfBoundsException();
           prevNode = currNode;
           currNode = currNode.next;
        }

        Node<T> newNode = new Node(element, currNode);

        prevNode.next = newNode;

        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if(head == null) throw new IndexOutOfBoundsException();

        Node<T> currNode = head;
        for(int i = 0; i < index; i++) {
            if(!currNode.hasNext()) throw new IndexOutOfBoundsException();
            currNode = currNode.next;
        }

        currNode.value = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        if(head == null || index < 0) throw new IndexOutOfBoundsException();
        Node<T> currNode = head;
        for(int i = 0; i < index; i++) {
            if(!currNode.hasNext()) throw new IndexOutOfBoundsException();
            currNode = currNode.next;
        }
        return currNode.value;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if(head == null) throw new NoSuchElementException();
        return head.value;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if(tail == null) throw new NoSuchElementException();
        return tail.value;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        if(head == null) throw new IndexOutOfBoundsException();

        Node<T> currNode = head;
        Node<T> prevNode = null;
        for(int i = 0; i < index; i++) {
            if(!currNode.hasNext()) throw new IndexOutOfBoundsException();
            prevNode = currNode;
            currNode = currNode.next;
        }

        T res;

        if(prevNode != null) {
            res = currNode.value;
            prevNode.next = currNode.next;
            if(currNode == tail) tail = prevNode;
        } else {
            res = head.value;
            head = head.next;
        }
        size--;
        return res;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (head ==null) return false;
        Node<T> node = head;

        while(node.hasNext()) {
            if(node.value == element) return true;
            node = node.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
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
