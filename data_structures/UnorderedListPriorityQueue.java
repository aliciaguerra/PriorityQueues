/*
 *Alicia Guerra
 *CS 310
 *Professor Steve Price
 *masc 1529
*/

package data_structures;

import java.util.Iterator;

public class UnorderedListPriorityQueue<T> implements PriorityQueue<T> {
    public UnorderedList<T> list;
  
    public UnorderedListPriorityQueue()
    {
        list=new UnorderedList<T>();
    }

    @Override
/*Inserts a new object into the PQ. Returns true if insertion is successful. If
unsuccessful, method is aborted and returns false.*/  
  public boolean insert(T obj) {
        list.insertLast(obj);
        return true;
    }

    @Override
    public T remove() {
        if (list.isEmpty()) {
            return null;
        }

        return list.removeMin();        

    }

    @Override
    public T peek() {
       return list.peekMin();
    }
/*The @Suppresswarnings annotation type allows Java programmers to disable compilation
warnings for a certain part of the program.*/
    @SuppressWarnings("unchecked")
    @Override
/*Returns true if the PQ contains a specified element.*/
    public boolean contains(T object) {
       if(list.find(object)!=null)
           return true;
       else
           return false;
    }

    @Override
 /*Returns number of objects currently in the PQ*/
    public int size() {
        return list.getCurrentSize();
    }

    @Override
/*Returns an iterator of the objects in the PQ, in no particular order.*/
    public Iterator<T> iterator() {
        return (Iterator<T>) list.iterator();
    }

    @Override
 /*Returns PQ to an empty state.*/
    public void clear() {
        list.clear();

    }

    @Override
/*Returns true if PQ is empty, otherwise false.*/
    public boolean isEmpty() {
        return this.size() < 1;
    }

    @Override
/*Returns true if the PQ is full, otherwise false.*/
    public boolean isFull() {
        return false;
    }

}