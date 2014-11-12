/*
 *Alicia Guerra
 *CS 310
 *Professor Steve Price
 *masc 1529
*/

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*This is an ordered list used by PQ#3 via composition.*/
/*We're implenting our ordered linked list.*/
/*The order of the linked list is ascending. */
public class OrderedList<T>  implements Iterable<T>{
    private Node<T> head = null;
    private int currentSize;
    private Node<T> tail;

/*The @Suppresswarnings annotation type allows Java programmers to disable compilation
warnings for a certain part of the program.*/
    @SuppressWarnings("unchecked")
/*Inserts a new object into the PQ. Returns true if the insertion is successful.
If the PQ is full, the insertion is aborted, and the method returns false.*/
    public void insert(T obj) {
/* This is the only insertion method. */
        Node<T> nextNode = new Node<T>(obj);
        Node<T> previous = null, current = head;

        while (current != null && ((Comparable<T>) obj).compareTo(current.data) >= 0) {
            previous = current;
            current = current.next;
        }

        if (isEmpty()) {
            head = nextNode;
        }
        else if (previous == null) {
            nextNode.next = head;
            head = nextNode;
        }
        else if (current == null) {
            previous.next = nextNode;
        }
        else {
            previous.next = nextNode;
            nextNode.next = current;
        }

        currentSize++;
    }

    public T removeFirst() 
    {
        return removeMin();
    }
/*The @Suppresswarnings annotation type allows Java programmers to disable compilation
warnings for a certain part of the program.*/
    @SuppressWarnings("unchecked")
/*We're supposed to remove arbitrary objects in the list.*/
    public T remove(T obj) 
    {
/* We're supposed to be traversing the list until we find our object.*/
        Node<T> previous = null, current = head;

        while (current != null && ((Comparable<T>) obj).compareTo(current.data) != 0) {
            previous = current;
            current = current.next;
        }
        if (current == null)
            return null;
/*This only applies if there's one item in the list.*/
        if (head == tail)
        {
            head = tail = null;
        }
        else if (previous == null) {
            head = head.next;
        }
        else if (current == tail) {
            previous.next = null;
            tail = previous;
        }
 /*If none of these apply, then that means there are two nodes and it's in the
 middle. */
        else 
        {
            previous.next = current.next;

        }

        currentSize--;
        return current.data;
    }
    public T peekMin() {
        return this.head.data;
    }
    
    public T removeMin() {

        if (isEmpty()) {
            return null;
        }

        T temp = head.data;
        head = head.next;
        currentSize--;
        return temp;

    }

    public Iterator<T> iterator() {
        return new ListIterator();

    }
/*Returns true if the PQ is empty, otherwise false.*/
    public boolean isEmpty() {
        return this.getCurrentSize() < 1;
    }

    public int getCurrentSize() {
        return currentSize;
    }
/*We also do this for the unordered list.*/
    public class ListIterator implements Iterator<T> {

        Node<T> iterPtr;

        public ListIterator() {
            iterPtr = head;
        }

        @Override
        public boolean hasNext() {
            return iterPtr != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T temp = iterPtr.data;
            iterPtr = iterPtr.next;
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }
    }
/*Retuns the PQ to an empty state.*/
    public void clear() {
        head=null;
        currentSize=0;
        
    }
    
    public class Node<T> {

        public Node(T obj) {
            this.data=obj;
        }
        public Node<T> next;
        public Node<T> previous;
        public T data;

    }
}
