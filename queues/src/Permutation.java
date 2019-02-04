import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<>();
        try {
            while(true) {
                q.enqueue(StdIn.readString());
            }
        }catch(NoSuchElementException e) {
            for(int i = 0; i < k; ++i) {
                StdOut.println(q.dequeue());
            }
        }
               
    }

}
