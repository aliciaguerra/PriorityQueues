/*
 *Alicia Guerra
 *Computer Science 310
 *Professor Steve Price
 *masc 1529
 */

package data_structures;

/*The java.util.Arrays class contains a static factory that allows arrays to
be viewed as lists.*/
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayPriorityQueue<T> implements PriorityQueue<T> {
    public int getMaxSize() {
        return maxSize;
    }

public OrderedArrayPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }
   
    private T[] storage;
    private int currentSize;
    private int maxSize;


/*The @Suppresswarnings annotation type allows Java programmers to disable compilation
warnings for a certain part of the program.*/
    @SuppressWarnings("unchecked")
    public OrderedArrayPriorityQueue(int size) {
        currentSize = 0;
        maxSize = size;
        storage = (T[]) new Object[maxSize];
    }

/*The @Suppresswarnings annotation type allows Java programmers to disable compilation
warnings for a certain part of the program.*/
    @SuppressWarnings("unchecked")
    
/*We use the binary search method to identify the correct insertion point
for new additions.*/
    public int binarySearch(T obj, int lowest, int highest) {
/*In each step, the binary search algorithm comapres the search key value 
with the key value of the middle element of the array.*/
        int middle = (lowest + highest) / 2;
        if (highest < lowest)
            return -1; // not found
        middle = lowest + (highest - lowest) / 2;
        if (((Comparable<T>) obj).compareTo(storage[middle]) == 0)
            return middle;
        else if (((Comparable<T>) obj).compareTo(storage[middle]) < 0)
            return binarySearch(obj, lowest, middle - 1);

        return binarySearch(obj, middle + 1, highest);
    }

    @Override
/*Inserts a new object into the priority queue. Returns true if the insertion
is successful. If the PQ is full, the insertion is aborted, and the method
returns false. */
    public boolean insert(T object) {
        if (isFull()) {
            return false;
        }
        int index = find_last_match_index(object);
        int insertIndex = index;
        int shift=insertIndex+1;
        if (index < 0) {
            insertIndex = -index - 1;
            shift=insertIndex+1;
        }

        T[] newSortedArray = (T[]) new Object[storage.length + 1];
        System.arraycopy(storage, 0, newSortedArray, 0, insertIndex);
        
/*After we shift the new array, we put the old array there*/       
        System.arraycopy(storage, insertIndex, newSortedArray, shift, storage.length - insertIndex);
        newSortedArray[insertIndex] = object;
        storage = newSortedArray;

        currentSize++;

        return true;
    }

    private int find_last_match_index(T object) {
        int last_good=0;
        int index = last_good= Arrays.binarySearch(storage, 0, currentSize, object);
        
        while(index>-1 && index<currentSize)
        {
            index=Arrays.binarySearch(storage, index+1, currentSize, object);
            
            if(index>0)
                last_good=index;
            if(index<0)
                last_good=-index-1;
        }
        
        return last_good;
    }

    @Override
/*Removes the object of highest priority that has been in the PQ the longest, 
and then returns it.Returns null if the PQ is empty.*/
    public T remove() {
        if (isEmpty())
        {
            return null;
        }

        T first_element = storage[0];

/*We're supposed to shift all of the elemements in our array to the left by one.*/
        System.arraycopy(storage, 1, storage, 0, storage.length - 1);

        //make collection smaller by 1
        currentSize--;

        return first_element;
    }

    @Override
    public T peek() {
        int hightest_priority_index = getHighestPriorityIndex();

        return getAtIndex(hightest_priority_index);

    }

    private T getAtIndex(int index) {
        if (isEmpty()) //this is not good solution
        {
            return null;
        }

        T element = storage[index];

        return element;

    }

/*The @Suppresswarnings annotation type allows Java programmers to disable compilation
warnings for a certain part of the program.*/
    @SuppressWarnings("unchecked")
    private int getHighestPriorityIndex() {
        int highest_priority_index = 0;
        for (int i = 0; i < currentSize; i++) {
            T obj = storage[i];
            if (((Comparable<T>) storage[highest_priority_index]).compareTo(((T) obj)) > 0) {
                highest_priority_index = i;
            }
        }
        return highest_priority_index;
    }

/*The @Suppresswarnings annotation type allows Java programmers to disable compilation
warnings for a certain part of the program.*/
    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(T object) {
        for (int i = 0; i < currentSize; i++)
            if (((Comparable<T>) object).compareTo(((T) storage[i])) == 0)
                return true;
        return false;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
 /*Returns an iterator of the object of the PQ, in no particular order.*/
    public Iterator<T> iterator() {
        return new ArrayIterator<T>();
    }

    @Override
/*Returns the PQ to an empty state.*/
    public void clear() {
        currentSize = 0;

    }

    @Override
/*Returns true if PQ is empty, otherwise false.*/
    public boolean isEmpty() {
        return this.size() < 1;
    }

    @Override
/*Returns true if PQ id full, otherwise false. List-based implementations
should always return false.*/
    public boolean isFull() {
        return currentSize == maxSize;
    }

    public class ArrayIterator<T> implements Iterator<T> {
        public ArrayIterator() {
            this.endIndex = size();
            this.index = 0;
        }

        protected int endIndex = 0;
        /** The current iterator index */
        protected int index = 0;

        @Override
        public boolean hasNext() {
            return (index < endIndex);
        }

        @Override
        public T next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }
            //return Array.get(array, index++);
            return (T) storage[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

    }

}
