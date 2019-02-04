import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private Node first;
    private Node last;
    private int qsize;
    
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    
    private class DequeIterator implements Iterator<Item>
    {
        private Node cur = first;
        
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Item next() {
            Item item = cur.item;
            cur = cur.next;
            
            return item;
        }
        
        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
    
    public Deque() {
        first = null;
        last = null;
        qsize = 0;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return qsize;
    }
    
    public void addFirst(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldfirst;
        
        if ( oldfirst != null ) {
            oldfirst.prev = first;
        }else {
            last = first;
        }
        
        qsize += 1;
    }
    
    public void addLast(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.prev = oldlast;
        last.next = null;
        
        if (oldlast != null) {
            oldlast.next = last;
        }else {
            first = last;
        }
        
        qsize += 1;
    }
    
    public Item removeFirst() {
        if(first == null) {
            throw new NoSuchElementException();
        }
        
        Node oldfirst = first;
        first = first.next;
        if(first == null) {
            last = first;
        }else {
            first.prev = null;
        }
        
        qsize -= 1;
        return oldfirst.item;
    }
    
    public Item removeLast() {
        if(last == null) {
            throw new NoSuchElementException();
        }
        
        Node oldlast = last;
        last = last.prev;
        if ( last == null ) {
            first = last;
        }else {
            last.next = null;
        }
        
        qsize -= 1;
        return oldlast.item;
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        System.out.println("size: " + d.size());
        try {
            d.removeFirst();
        }catch (NoSuchElementException e){
            System.out.println("No such element caught");
        }
        
        d.addFirst("brown fox");
        d.addFirst("A quick");
        System.out.println(d.size());
        System.out.println(d.removeFirst());
        System.out.println(d.removeLast());
        System.out.println(d.size());
        d.addLast("Hello");
        d.addFirst("World");
        System.out.println(d.removeFirst());
        System.out.println(d.size());
        System.out.println(d.removeFirst());
    }
}
