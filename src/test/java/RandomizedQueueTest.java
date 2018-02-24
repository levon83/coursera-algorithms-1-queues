import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RandomizedQueueTest {

    private RandomizedQueue<String> randomizedQueue;

    @Before
    public void before() {
        this.randomizedQueue = new RandomizedQueue<>();
    }

    @Test
    public void isEmpty() {
        assertThat(this.randomizedQueue.isEmpty(), equalTo(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void enqueueException() {
        this.randomizedQueue.enqueue(null);
    }

    @Test
    public void enqueue() {
        this.randomizedQueue.enqueue("aaa");
        assertThat(this.randomizedQueue.size(), equalTo(1));
        assertThat(this.randomizedQueue.sample(), equalTo("aaa"));
        this.randomizedQueue.enqueue("bbb");
        assertThat(this.randomizedQueue.size(), equalTo(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeueException() {
        this.randomizedQueue.dequeue();
    }

    @Test
    public void dequeue() {
        this.randomizedQueue.enqueue("aaa");
        assertThat(this.randomizedQueue.dequeue(), equalTo("aaa"));
        this.randomizedQueue.enqueue("bbb");
        this.randomizedQueue.enqueue("ccc");
        this.randomizedQueue.enqueue("ddd");
        this.randomizedQueue.enqueue("eee");
        this.randomizedQueue.dequeue();
        this.randomizedQueue.dequeue();
        this.randomizedQueue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void sampleException() {
        this.randomizedQueue.sample();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorExceptionNext() {
        this.randomizedQueue.enqueue("aaa");
        Iterator<String> iterator = this.randomizedQueue.iterator();
        iterator.next();
        iterator.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void iteratorExceptionRemove() {
        this.randomizedQueue.iterator().remove();
    }

    @Test
    public void iterator() {
        this.randomizedQueue.enqueue("aaa");
        this.randomizedQueue.enqueue("bbb");
        Iterator<String> iterator = this.randomizedQueue.iterator();
        assertThat(iterator.hasNext(), equalTo(true));
        assertThat(iterator.next(), notNullValue());
        assertThat(iterator.hasNext(), equalTo(true));
        assertThat(iterator.next(), either(equalTo("aaa")).or(equalTo("bbb")));
        assertThat(iterator.hasNext(), equalTo(false));
    }

}
