import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * double-ended queue
 */
public class Deque<Item> implements Iterable<Item> {
    /**
     * create a kind of node that contains the prev, item, next, which builds a Deque
     */
    private class Node {
        private Node prev;
        private Item item;
        private Node next;

        private Node(Node prev, Item t, Node next) {
            this.prev = prev;
            this.item = t;
            this.next = next;
        }
    }

    /**
     * create a kind of deque iterator
     */
    private class DequeIterator implements Iterator<Item> {
        Node currentNode;

        private DequeIterator(Node startNode) {
            this.currentNode = startNode.next;
        }
        @Override
        public boolean hasNext() {
            return !(currentNode == sentinel);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private final Node sentinel;
    private int size;

    /**
     * construct an empty deque
     */
    public Deque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;

        size = 0;
    }

    /**
     * is the deque empty?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * add the item to the front
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirstNode = sentinel.next;
        Node newFirstNode = new Node(sentinel, item, oldFirstNode);
        oldFirstNode.prev = newFirstNode;
        sentinel.next = newFirstNode;

        size++;
    }

    /**
     * add the item to the back
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLastNode = sentinel.prev;
        Node newLastNode = new Node(oldLastNode, item, sentinel);
        sentinel.prev = newLastNode;
        oldLastNode.next = newLastNode;

        size++;
    }

    /**
     * remove and return the item from the front
     */
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldFirstNode = sentinel.next;
        Node newFirstNode = oldFirstNode.next;
        sentinel.next = newFirstNode;
        newFirstNode.prev = sentinel;

        size--;

        return oldFirstNode.item;
    }

    /**
     * remove and return the item from the back
     */
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldLastNode = sentinel.prev;
        Node newLastNode = oldLastNode.prev;
        sentinel.prev = newLastNode;
        newLastNode.next = sentinel;

        size--;

        return oldLastNode.item;
    }

    /**
     * return an iterator over items in order from front to back
     */
    public Iterator<Item> iterator() {
        return new DequeIterator(sentinel);
    }

    /**
     * unit testing (required)
     */

    public static void main(String[] args) {
        // test add and remove
        Deque<Integer> dq = new Deque<>();
        int n = 10;
        for (int i = 0; i < n; i++) {
            dq.addFirst(i);
            dq.addLast(i);
        }
//        for (int j = 0; j < 2 * n; j++) {
//            System.out.println(dq.removeFirst());
//        }

        // test iterator
        for (int i = 0; i < n; i++) {
            dq.addFirst(i);
            dq.addLast(i);
        }
        Iterator<Integer> iter = dq.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
