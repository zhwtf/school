import java.util.Arrays;/**   A class that implements a bag of objects by using an array.	The bag is never full.   @author Frank M. Carrano   @version 3.0*/public class ResizableArrayBag<T> implements BagInterface<T>{	private T[] bag; // cannot be final due to doubling	private static final int DEFAULT_INITIAL_CAPACITY = 25; // initial capcity of bag 	private int numberOfEntries;	/** Creates an empty bag whose initial capacity is 25. */	public ResizableArrayBag() 	{		this(DEFAULT_INITIAL_CAPACITY);	} // end default constructor	/** Creates an empty bag having a given initial capacity.	    @param capacity  the integer capacity desired */	public ResizableArrayBag(int capacity) 	{		numberOfEntries = 0;      // the cast is safe because the new array contains null entries      @SuppressWarnings("unchecked")      T[] tempBag = (T[])new Object[capacity]; // unchecked cast      bag = tempBag;	} // end constructor	/** Creates a bag containing given entries.	    @param contents  an array of objects */   public ResizableArrayBag(T[] contents)    {      bag = Arrays.copyOf(contents, contents.length);      numberOfEntries = contents.length;   } // end constructor       	/** Adds a new entry to this bag.	    @param newEntry  the object to be added as a new entry	    @return true if the addition is successful, or false if not */	public boolean add(T newEntry) 	{		ensureCapacity();		bag[numberOfEntries] = newEntry;		numberOfEntries++;				return true;	} // end add   // Doubles the size of the array bag if it is full	private void ensureCapacity() 	{		if (numberOfEntries == bag.length)         bag = Arrays.copyOf(bag, 2 * bag.length);	} // end ensureCapacity   /** @return true; the bag is never full because the array bag is                resized, as necessary */	public boolean isFull() 	{		return false;	} // end isFull		/** Retrieves all entries that are in this bag.	    @return a newly allocated array of all the entries in the bag */	public T[] toArray() 	{      // the cast is safe because the new array contains null entries      @SuppressWarnings("unchecked")      T[] result = (T[])new Object[numberOfEntries]; // unchecked cast		for (int index = 0; index < numberOfEntries; index++) 		{			result[index] = bag[index];		} // end for					return result;	} // end toArray	/** Sees whether this bag is empty.	    @return true if this bag is empty, or false if not */	public boolean isEmpty() 	{		return numberOfEntries == 0;	} // end isEmpty	/** Gets the (current) capacity of this bag.	    @return the integer number of entries that the bag can hold */	public int getCapacity() 	{		return bag.length;	} // end getCapacity	/** Gets the number of entries currently in this bag.	    @return the integer number of entries currently in the bag */	public int getCurrentSize() 	{		return numberOfEntries;	} // end getCurrentSize	/** Counts the number of times a given entry appears in this bag.		 @param anEntry  the entry to be counted		 @return the number of times anEntry appears in the bag */	public int getFrequencyOf(T anEntry) 	{		int counter = 0;		for (int index = 0; index < numberOfEntries; index++) 		{			if (anEntry.equals(bag[index]))			{				counter++;			} // end if		} // end for		return counter;	} // end getFrequencyOf	/** Tests whether this bag contains a given entry.		 @param anEntry  the entry to locate		 @return true if the bag contains anEntry, or false otherwise */	public boolean contains(T anEntry)	{		return getIndexOf(anEntry) > -1;	} // end contains    	// Locates a given object within the array bag.	// Returns the index of the object, if located,	// or -1 otherwise.	private int getIndexOf(T anEntry) 	{		int where = -1;		boolean found = false;      		for (int index = 0; !found && (index < numberOfEntries); index++) 		{			if (anEntry.equals(bag[index]))			{				found = true;				where = index;			} // end if		} // end for      		return where;	} // end getIndexOf   	/** Removes all entries from this bag. */	public void clear() 	{		while (!isEmpty())          remove();      //    while (remove() != null) {} // Q7	} // end clear		/** Removes one unspecified entry from this bag.       @return either the removed entry, if the removal               was successful, or null otherwise */	public T remove()	{		T result = removeEntry(numberOfEntries - 1);				return result;	} // end remove		/** Removes one occurrence of a given entry from this bag.       @param anEntry  the entry to be removed       @return true if the removal was successful, or null otherwise */   public boolean remove(T anEntry) 	{		int index = getIndexOf(anEntry);      T result = removeEntry(index);				return anEntry.equals(result);	} // end remove      // Removes and returns the array entry at a given index.   // If no such entry exists, returns null.	private T removeEntry(int givenIndex)	{		T result = null;      		if (!isEmpty() && (givenIndex >= 0))		{         result = bag[givenIndex]; // entry to remove         numberOfEntries--;         bag[givenIndex] = bag[numberOfEntries]; // replace entry to remove with last entry         bag[numberOfEntries] = null; // remove reference to last entry		} // end if            return result;	} // end removeEntry   } // end ResizableArrayBag