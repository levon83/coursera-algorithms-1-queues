import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from
 * items in the data structure.
 *
 * @param <Item> The type of stored items
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    /**
     * Stored items.
     */
    private Item[] items;

    /**
     * Number of items in this data structure.
     */
    private int count;

    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        this.items = (Item[]) new Object[1];
        this.count = 0;
    }

    /**
     * Is the randomized queue empty?
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Return the number of items on the randomized queue.
     *
     * @return the number of items
     */
    public int size() {
        return this.count;
    }

    /**
     * Add the item at the end, and later shuffle this item in the array so that the last item is set uniformly at
     * random.
     *
     * @param item the item to add
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Value is required.");
        }

        if (this.count == this.items.length) {
            this.items = this.resize(this.items.length * 2);
        }

        this.items[this.count++] = item;

        this.exchange(this.count - 1, StdRandom.uniform(this.count));
    }

    /**
     * Remove and return an item from the end which is actually randomly set during previous dequeue operation.
     *
     * @return a random item
     */
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Data structure is empty.");
        }

        Item item = this.items[--this.count];
        this.items[this.count] = null;

        if (this.count > 0 && this.count == this.items.length / 4) {
            this.items = this.resize(this.items.length / 2);
        }

        return item;
    }

    /**
     * Return a random item (but do not remove it).
     *
     * @return a random item
     */
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Data structure is empty.");
        }

        return this.items[StdRandom.uniform(this.count)];
    }

    /**
     * Return an independent iterator over items in random order.
     *
     * @return an iterator over this data structure
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    /**
     * Resize the array by copying it over to a new array.
     *
     * @param capacity the desired new capacity
     */
    private Item[] resize(int capacity) {
        Item[] copied = (Item[]) new Object[capacity];

        for (int i = 0; i < this.count; i++) {
            copied[i] = this.items[i];
        }

        return copied;
    }

    /**
     * Exchange two items in the array.
     *
     * @param i the index of one item
     * @param j the index of another item
     */
    private void exchange(int i, int j) {
        Item temp = this.items[i];
        this.items[i] = this.items[j];
        this.items[j] = temp;
    }

    /**
     * Inner class to accommodate iterator functionality.
     */
    private final class RandomizedQueueIterator implements Iterator<Item> {

        /**
         * Array to traverse.
         */
        private final Item[] items;

        /**
         * Current index.
         */
        private int index = 0;

        /**
         * Basic constructor. Copies and shuffles original underlying array.
         */
        private RandomizedQueueIterator() {
            this.items = resize(count);
            StdRandom.shuffle(this.items);
        }

        @Override
        public boolean hasNext() {
            return this.index < this.items.length;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No more items.");
            }

            return this.items[this.index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operation is forbidden.");
        }

    }

}
