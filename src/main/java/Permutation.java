import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * A client program that takes an integer k as a command-line argument; reads in a sequence of strings from standard
 * input and prints exactly k of them, uniformly at random.
 */
public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        int i = 0;

        while (!StdIn.isEmpty() && k > 0) {
            String input = StdIn.readString();

            if (randomizedQueue.size() < k) {
                randomizedQueue.enqueue(input);
            } else if (StdRandom.uniform(i + 1) < k) {
                randomizedQueue.dequeue();
                randomizedQueue.enqueue(input);
            }

            i++;
        }

        randomizedQueue.iterator().forEachRemaining(System.out::println);
    }

}
