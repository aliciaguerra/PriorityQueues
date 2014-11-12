/*
 *Alicia Guerra
 *Professor Steve Price
 *CS 310: Data Structures
 *November 6, 2014
 *masc1529
 */

/*A package is a grouping of related types providing access protection and name 
space management. Types refers to classes, interfaces, enumerations, and 
annotation types.*/
package data_structures;
/*Hashtables are supported by Java, in the form of the java.util.Hashtable class.
Hashtables accept as keys */
import java.util.Hashtable;
/*The Locale is used to make the system relevant and usable for the users from
different cultures. In other words, it is used to customize the system for 
different region, culture, and language. The Locale is the mechanism for identifying
the kind of object that you would like to get. Java provides certain classes 
which provide local specific operations. For example, they provide methods to
format values that represent dates, currency, and numbers according to a specific
locale. These classes are known as Locale sensitive classes.*/
import java.util.Locale;
/*To declare a class that implements an interface, you include an implements 
clause in the class declaration. Your class can implement more than one
interface, so the implements keyword is followed by a comma-seperated list of
the interfaces implemented by th class. By convention, the implements clause
follows the extends clause, if there is one.*/
public class ServiceRequest implements Comparable<ServiceRequest> {
/*An enum type is a special data type that enables for a variable to be a set of
predefined constants. The variable must be equal to one of the values that has
been predefined for it. Examples might include compass directions or days of the 
week.*/
    public enum priorities {
        P1, P2, P3, P4, P5;
    }
/*Final indicates that the value of the string won't change - in other words, it
can't be modified after it is declared. We use "public static final String" to
create a string that belongs to the class and that won't change, for instance 
when you define a String constant that will be avaialble to all instances of the 
class, and other objects using the class, depending on the access modifier:
public final static String MY_CONSTANT="SomeValue";
//in some other code, possibly in another object, use the constant:
if(input.equals(MyClass.MY_CONSTANT)*/
    public static final String FACULTY = "faculty";
    public static final int P1 = 1;
    public static final int P2 = 2;
    public static final int P3 = 3;
    public static final int P4 = 4;
    public static final int P5 = 5;
/*The categories start out by being null.*/
    public static final String Categories = null;
/*Hashtables are an extremely useful mechanism to store data. Hashtables work
by mapping a key to a value, which is stored in an in-memory data structure.
Rather than searching through all elements of the hashtable for a matching key,
a hashing function analyzes a key, and returns an index number. The index matches 
a stored value, and then the data is accessed.*/
    public static Hashtable<String, Integer> CATEGORIES;
    private int priority;
    private String client;
    private String category;
    private String description;

    public ServiceRequest(int priority, String client, String category, String description) {
/*We must validate our priority and category fields.*/
        if (priority < 0 || priority > 5)
/*Because our method only accepts arguments within a particular range, we check
for invalid parameters and throw an IllegalArgumentException.*/
            throw new IllegalArgumentException("Priority must be between 1-5");
        else if (!category.matches("(?i)classroom|network|server|lab|faculty|staff"))
            throw new IllegalArgumentException("category must be of form: classroom|network|server|lab|faculty|staff");
/*The this keywords are required to distinguish among the parameters.*/ 
        this.priority = priority;
        this.client = client;
        this.category = category.toLowerCase(Locale.US);
        this.description = description;
/*If many entries are to be made in the hashtable, creating it with a sufficiently
large capacity may allow the entires to be inserted more efficiently than letting
it perform automatic rehashing as needed to grow the table.*/
        CATEGORIES = new Hashtable<String, Integer>();
/*The classroom cathegory holds the highest priority.*/
        CATEGORIES.put("classroom", 1);
/*The network category holds the second highest priority.*/
        CATEGORIES.put("network", 2);
/*The server category holds the third highest priority.*/
        CATEGORIES.put("server", 3);
/*The lab category holds the fourth highest priority*/
        CATEGORIES.put("lab", 4);
/*The faculty category holds the fifth highest priority.*/
        CATEGORIES.put("faculty", 5);
/*The staff category holds the lowest priority.*/
        CATEGORIES.put("staff", 6);
    }
/*We use @Override everytime we override a method for two reasons: (1) We can
take advanatage of the compiler checking to make sure we actually are overriding
a method we think we are. That way, if you make a common mistake of misspelling 
a method name or incorrectly matching our parameters, we will be warned that the 
method does not actually override as we think it does. and (2) it makes our code 
easier to understand because it becomes more obvious when methods are overwritten.*/
    @Override
/*The CompareTo method comapres the number object that invoked the method to
the argument. However, two different types cannot be compared.*/
    public int compareTo(ServiceRequest next) {
        int priority_diff = this.priority - next.priority;
        if (priority_diff != 0)
            return priority_diff;
        else {
            int category_diff = CATEGORIES.get(this.category) - CATEGORIES.get(next.category);
            return category_diff;
        }
    }
/*The get methods (also called an accessor method) return the value of the 
member they are associated with.*/
/*In the SechedulerDriver.java file, the string information is broken up into
parts: the category, client, description, and priority information.*/
    public String getCategory() {
        return category;
    }
    public String getClient() {
        return client;
    }
    public String getDescription() {
        return description;
    }
    public int getPriority() {
        return priority;
    }
/*The set method (also called a mutator method) sets the member to a new value.*/
    public void setCategory(String c) {
        this.category = c;
    }
/*Void is the return type. It means "this method returns nothing".*/
    public void setClient(String c) {
        this.client = c;
    }
    public void setDescription(String d) {
        this.description = d;
    }
    public void setPriority(int n) {
        this.priority = n;
    }
    @Override
/*ToString is a major formatting method. It converts an object to its string
representation so that it is suitable for display.*/    
    public String toString() {
        return "ServiceRequest [priority=" + priority + ", client=" + client + ", category=" + category
                + ", description=" + description + "]";
    }

}
