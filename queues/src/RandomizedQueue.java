import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item queue[];
    private int end;
    private int count;
    
    public RandomizedQueue() {
        end = -1;
        queue = null;
        count = 0;
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public int size() {
        return count;
    }
    
    public void enqueue(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        
        if(queue == null) {
            queue = (Item[])new Object[1];
        }
        
        end = end + 1;
        count = count + 1;
        
        if(end == queue.length) {
            resize(2*queue.length);   
            end = count - 1;
        }
        
        queue[end] = item;
    }
    
    private int length() {
        return queue.length;
    }
    public Item dequeue() {
        if(count == 0) {
            throw new NoSuchElementException();
        }
        
        count = count - 1;
        
        if(end == 0) {
            return queue[0];
        }
        
        int i = randomIndex();
        Item item = queue[i];
        queue[i] = null;
        
        if(queue.length > 2 && count < queue.length/4) {
            resize(queue.length/2);
        }
        
        return item;
    }
    
    private int randomIndex() {
        Item item = null;
        
        int rand;
        do{
            rand = StdRandom.uniform(end+1);
            item = queue[rand];
        }while(item == null);
        
        return rand;
    }
    
    public Item sample() {
        if(count == 0) {
            throw new NoSuchElementException();
        }
        
        if(end == 0) {
            return queue[0];
        }
        
        int i = randomIndex();
        return queue[i];
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private void resize(int capacity) {
        Item[] oldq = queue;
        queue = (Item[]) new Object[capacity];
        for(int i = 0, j = 0; i < oldq.length; ++i) {
            if(oldq[i] != null)
                queue[j] = oldq[i];
        }
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>{ 
        private int cur;
        private int[] seq;
        public RandomizedQueueIterator() {
            cur = 0;
            seq = new int[count];
            for(int i = 0, j = 0; i < end; ++i) {
                if(queue[i] != null) {
                    seq[j] = i;
                }
            }
            
        }
        
        @Override
        public boolean hasNext() {
            if(queue == null) {
                return false;
            }
                      
            return cur < count;
        }

        @Override
        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            
            Item item = queue[cur];
            cur = cur + 1;
            
            return item;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        System.out.println(q.isEmpty());
        System.out.println(q.size());
        q.enqueue(10);
        System.out.println(q.isEmpty());
        System.out.println(q.size());
        System.out.println(q.dequeue());
        System.out.println(q.size());
        System.out.println(q.isEmpty());
        System.out.println(q.size());
        q.enqueue(20);
        q.enqueue(30);
        System.out.println(q.size());
        System.out.println("Sample " + q.sample());
        System.out.println(q.isEmpty());
        System.out.println(q.dequeue());
        System.out.println(q.size());
        System.out.println(q.end);
        System.out.println("Sample " + q.sample());
        System.out.println("Size " + q.size());
        System.out.println("Sample " + q.sample());
        System.out.println(q.dequeue());
        System.out.println("Size " + q.size());
        System.out.println("End " + q.end);
        System.out.println("Length " + q.length());
        //System.out.println("Sample " + q.sample());
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(50);
        q.enqueue(50);
        q.enqueue(40);
        q.enqueue(40);
        q.enqueue(60);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}
