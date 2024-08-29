import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int maxSize = k;
        if (maxSize == 0) {
            return;
        }
        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            if (rq.size() == maxSize) {
                boolean addString = StdRandom.bernoulli();
                if (!addString) {    // 不添加该字符串
                    continue;
                }
                rq.dequeue();
            }
            rq.enqueue(token);
        }

        while (!rq.isEmpty()) {
            System.out.println(rq.dequeue());
        }
    }
}
