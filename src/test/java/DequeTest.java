import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTest {

    private Deque<String> deque;

    @Before
    public void before() {
        this.deque = new Deque<>();
    }

    @Test
    public void isEmpty() {
        assertTrue(this.deque.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addFirstException() {
        this.deque.addFirst(null);
    }

    @Test
    public void addFirst() {
        this.deque.addFirst("aaa");
        assertEquals(1, this.deque.size());
        this.deque.addFirst("bbb");
        assertEquals(2, this.deque.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addLastException() {
        this.deque.addLast(null);
    }

    @Test
    public void addLast() {
        this.deque.addLast("yyy");
        assertEquals(1, this.deque.size());
        this.deque.addLast("zzz");
        assertEquals(2, this.deque.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstException() {
        this.deque.removeFirst();
    }

    @Test
    public void removeFirst() {
        this.deque.addFirst("aaa");
        this.deque.addFirst("bbb");
        assertEquals("bbb", this.deque.removeFirst());
        assertEquals("aaa", this.deque.removeFirst());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastException() {
        this.deque.removeLast();
    }

    @Test
    public void removeLast() {
        this.deque.addLast("yyy");
        this.deque.addLast("zzz");
        assertEquals("zzz", this.deque.removeLast());
        assertEquals("yyy", this.deque.removeLast());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorExceptionNext() {
        this.deque.addFirst("aaa");
        Iterator iterator = this.deque.iterator();
        iterator.next();
        iterator.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void iteratorExceptionRemove() {
        this.deque.iterator().remove();
    }

    @Test
    public void iterator() {
        this.deque.addFirst("aaa");
        this.deque.addFirst("bbb");
        Iterator iterator = this.deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("bbb", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("aaa", iterator.next());
        assertFalse(iterator.hasNext());
    }

}
