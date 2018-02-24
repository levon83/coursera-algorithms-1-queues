import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Stack and a queue at the same time.
 *
 * @param <Item> the type to be stored in this data structure
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * The front node.
     */
    private Node front;

    /**
     * The back node.
     */
    private Node back;

    /**
     * The amount of stored nodes.
     */
    private int size;

    /**
     * Construct an empty deque.
     */
    public Deque() {
        this.size = 0;
    }

    /**
     * Is the randomized queue empty?
     *
     * @return true if it is, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Return the number of items on the randomized queue.
     *
     * @return the number of items
     */
    public int size() {
        return this.size;
    }

    /**
     * Add the item to the front.
     *
     * @param item the item to add
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        Node node = new Node(item);

        if (this.front != null) {
            node.next = this.front;
            this.front.prev = node;
        }

        this.front = node;

        if (this.back == null) {
            this.back = node;
        }

        this.size++;
    }

    /**
     * Add the item to the end.
     *
     * @param item the item to add
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        Node node = new Node(item);

        if (this.back != null) {
            node.prev = this.back;
            this.back.next = node;
        }

        this.back = node;

        if (this.front == null) {
            this.front = node;
        }

        this.size++;
    }

    /**
     * Remove and return the item from the front.
     *
     * @return the item
     */
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The structure is empty.");
        }

        Node node = this.front;

        this.front = node.next;
        if (this.front != null) {
            this.front.prev = null;
        }
        node.next = null;

        this.size--;

        if (this.isEmpty()) {
            this.back = null;
        }

        return node.item;
    }

    /**
     * Remove and return the item from the end.
     *
     * @return the item
     */
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The structure is empty.");
        }

        Node node = this.back;

        this.back = node.prev;
        if (this.back != null) {
            this.back.next = null;
        }
        node.prev = null;

        this.size--;

        if (this.isEmpty()) {
            this.front = null;
        }

        return node.item;
    }

    /**
     * Return an independent iterator over items in random order.
     *
     * @return the iterator
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * Inner class to store items.
     */
    private final class Node {

        /**
         * The item stored.
         */
        private final Item item;

        /**
         * The next node.
         */
        private Node next;

        /**
         * The previous node.
         */
        private Node prev;

        /**
         * Create and initialize.
         *
         * @param value the item to store
         */
        private Node(Item value) {
            this.item = value;
        }

    }

    /**
     * Iterator implementation for this data structure.
     */
    private final class DequeIterator implements Iterator<Item> {

        private Node current = front;

        public boolean hasNext() {
            return this.current != null;
        }

        public Item next() {
            if (this.current == null) {
                throw new NoSuchElementException("No more items.");
            }

            Node node = this.current;

            this.current = node.next;

            return node.item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove.");
        }

    }

}
