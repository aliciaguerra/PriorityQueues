/*
 *Alicia Guerra
 *CS 310
 *Professor Steve Price
 *masc 1529
 */

package data_structures;

import java.util.Iterator;

public class OrderedListPriorityQueue<T> implements PriorityQueue<T> {
public OrderedList<T> list;
    
    public OrderedListPriorityQueue()
    {
        list=new OrderedList<T>();
    }
    
    @Override
/*Inserts a new object into the priority queue. Returns truw if the insertion is
successful, otherwise false.*/
    public boolean insert(T object) {
        list.insert(object);
        return true;
    }

    @Override
/*Returns the object of highest priority that has been in the PQ the longest
but does not remove it.*/
    public T remove() {
        if (list.isEmpty()) {
            return null;
        }

        return list.removeMin();
        
    }

    @Override
    public T peek() {
/*All this is supposed to do is return the first item in queue*/  
        return list.peekMin(); 
    }

/*The @Suppresswarnings annotation type allows Java programmers to disable compilation
warnings for a certain part of the program.*/
    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(T object) {
        for(T obj : this.list)
        {
            if (((Comparable<T>) object).compareTo(((T) obj)) == 0)
                return true;
        }
        return false;
    }

    @Override
/*Returns the number of objects currently in the PQ*/
    public int size() {
        return list.getCurrentSize();
    }
/*Returns an iterator of the objects in PQ, in no order*/
    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) list.iterator();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
/*Returns true if the PQ is empty, otherwise false.*/
    public boolean isEmpty() {
        return this.size() < 1;
    }

    @Override
/*Retunr struw if the PQ is full, otherwise false.*/
    public boolean isFull() {
        return false;
    }

}
