import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * use a singly linked list to simulate a randomized queue
 * TODO: instead, we should use an Array to simulate, or the randomness is not uniform
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    /**
     * create a kind of node that contains the prev, item, next, which builds a randomized queue
     * using singly linked list
     */
    private class Node {
        private Item item;
        private Node next;

        private Node(Item t, Node next) {
            this.item = t;
            this.next = next;
        }
    }

    /**
     * create a kind of randomized queue iterator
     */
    private class RandomizedQueIter implements Iterator<Item> {
        private Item[] array;
        private int pointer;    // point to the last index

        public RandomizedQueIter(Item[] array) {
            this.array = array;
            this.pointer = array.length - 1;
        }

        @Override
        public boolean hasNext() {
            return !(pointer == -1);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int randomIndex = StdRandom.uniformInt(pointer + 1);
            Item randomItem = array[randomIndex];
            array[randomIndex] = array[pointer];
            array[pointer] = null;

            pointer--;
            return randomItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private final Node sentinel;
    private Node last;
    private Node randomNode;
    private int size;

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        this.sentinel = new Node(null, null);
        this.last = sentinel;
        this.randomNode = sentinel;
        this.size = 0;
    }

    /**
     * is the randomized queue empty?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the randomized queue
     */
    public int size() {
        return size;
    }

    /**
     * add the item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        boolean random = StdRandom.bernoulli();
        if (random) {
            addFirst(item);
        }
        else {
            addLast(item);
        }
    }

    /**
     * remove and return a random item
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return removeFirst();
    }

    /**
     * return a random item (but do not remove it)
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        randomNode = randomNode.next;
        Item item = randomNode.item;
        // 回绕
        if (randomNode == last) {
            randomNode = sentinel;
        }

        return item;
    }

    /**
     * return an independent iterator over items in random order
     * time complexity: O(N)
     * space complexity: O(N)
     */
    public Iterator<Item> iterator() {
        Item[] array = (Item[]) new Object[size];
        Node pointer = sentinel;
        for (int i = 0; i < size; i++) {
            pointer = pointer.next;
            array[i] = pointer.item;
        }
        return new RandomizedQueIter(array);
    }

    /**
     * add the item to the first pos of the queue
     */
    private void addFirst(Item item) {
        Node oldFirstNode = sentinel.next;
        Node newFirstNode = new Node(item, oldFirstNode);
        sentinel.next = newFirstNode;
        newFirstNode.next = oldFirstNode;

        // 边界条件
        if (isEmpty()) {
            last = newFirstNode;
        }

        size++;
    }

    /**
     * add the item to the last pos of the queue
     */
    private void addLast(Item item) {
        Node newLastNode = new Node(item, null);
        last.next = newLastNode;
        last = newLastNode;

        size++;
    }
    /**
     * delete the first item
     */
    private Item removeFirst() {
        if (size == 1) {
            last = sentinel;
        }
        Node oldFirstNode = sentinel.next;
        sentinel.next = oldFirstNode.next;

        size--;
        return oldFirstNode.item;
    }

    /**
     * unit testing (required)
     */
    public static void main(String[] args) {

        int n = 5;
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        // test enqueue and dequeue
        for (int i = 0; i < n; i++) {
            rq.enqueue(i);
        }
        for (int j = 0; j < n; j++) {
            System.out.println(rq.dequeue());
        }
        // test iterator
        for (int i = 0; i < n; i++) {
            rq.enqueue(i);
        }
        Iterator<Integer> iter1 = rq.iterator();
        Iterator<Integer> iter2 = rq.iterator();

        System.out.print("iter1: ");
        while (iter1.hasNext()) {
            System.out.print(iter1.next() + " ");
        }
        System.out.println();

        System.out.print("iter2: ");
        while (iter2.hasNext()) {
            System.out.print(iter2.next() + " ");
        }
        System.out.println();

        // test sample
        for (int i = 0; i < 10 * n; i++) {
            System.out.print(rq.sample() + " ");
        }
    }
}
