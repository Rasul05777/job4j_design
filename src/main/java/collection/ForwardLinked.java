package collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        Node<T> element = new Node<>(value, null);
        if (head == null) {
            head = element;
        } else {
            Node<T> tile = head;
            while (tile.next != null) {
                tile = tile.next;
            }
            tile.next = element;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> element = head;
        for (int i = 0; i < index; i++) {
            element = element.next;
        }
        return element.item;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> element = head;
        T result = element.item;
        head = element.next;
        element.next = null;
        element.item = null;
        size--;
        modCount++;
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectModCount = modCount;
            private Node<T> nextElement = head;
            private Node<T> currentElement;

            @Override
            public boolean hasNext() {
                if (expectModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return nextElement != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                currentElement = nextElement;
                nextElement = nextElement.next;
                return currentElement.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}
