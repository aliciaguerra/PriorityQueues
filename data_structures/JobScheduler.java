/*
 *Alicia Guerra
 *Professor Steve Price
 *CS 310: Data Structures
 *November 6, 2014
 *masc1529
 */

/*The JobScheduler class will store pending pending ServiceRequest jobs and will
provide on demand the waiting job with the highest priority.*/
package data_structures;
/*We import all the types in the data_structures package; we use the import
statement with the asterisk (*) wildcard character. For example: 
import graphics.*;
Now you can refer to any class or interface in the graphics package by its name:
Circle myCircle=new Circle();
Rectangle myRectangle=new Rectangle();
The asterisk in the import statement can only be used to specify all the classes
within a package. It cannot be used to match a subset of classes in a package.
For example, the following does not match all the classes in the graphics package
that begin with A:
//does not work
import graphics.A*;
With the import statement, you generally only import a single package member or
an entire package.*/
import data_structures.PriorityQueue;
import data_structures.*;
import data_structures.PriorityQueue;
public class JobScheduler {
/*We use our OrderedArrayPriorityQueue<E> implementation here.*/
    private PriorityQueue<ServiceRequest> queue;
    private int maxJobs;
/*Any method that is not declared void must contain a return statement with a
corresponding return value.*/
    public int getMaxJobs() {
        return maxJobs;
    }
    public JobScheduler(int maxJobs) {
        this.maxJobs = maxJobs;
        queue = new OrderedArrayPriorityQueue<ServiceRequest>(this.maxJobs);
    }
/*We insert a new job into the scheduler;returns false if insertion fails.*/
    public boolean insertJob(ServiceRequest req) {
        return queue.insert(req);
    }
/*We returns the next job of highest precedence based on the specifications
in the assignment, and removes it from the scheduler.*/
    public ServiceRequest nextJob() {
        return queue.remove();
    }
/*peek() looks at the object at the top of the stack without removing it from
 the stack.*/
/*So, we will be returning the next job of highest precedence based on the 
specifications in the assignment, but does NOT remove it.*/
    public ServiceRequest peekJob() {
        return queue.peek();
    }
/*This will return true if more jobs remain in the scheduler and false if the 
scheduler is empty.*/
    public boolean hasMoreJobs() {
        return !queue.isEmpty();
    }
/*Returns true if the scheduler is full.*/
    public boolean schedulerFull() {
        return this.getMaxJobs() == queue.size();
    }
/*We print out all the jobs with the given priority.*/
    public void printJobs(int priority) {
        for (ServiceRequest job : queue) {       
 /*If it's the right priority, we print out the job.*/
            if (job.getPriority() == priority) {
                System.out.println(job);
            }
        }
    }
/*We print all the jobs.*/
    public void printJobs() {
        printJobs(-1);
    }
/* We return the total number of jobs waiting in the scheduler with the given
priority.*/
    public int numberWaiting(int priority) {
        if (priority == -1)
/*Perhaps we could have also used java.util.ArrayList.size()?*/
            return queue.size();
        else {
/*We initialize our count to zero.*/            
            int count = 0;
            for (ServiceRequest job : queue) {
                if (job.getPriority() == priority) {
/*If we have the right priority, we increment our count by one.*/
                    count++;
                }
            }
            return count;
        }
    }
  /*We returns the total number of jobs waiting in the scheduler.*/
    public int numberWaiting() {
        return numberWaiting(-1);
    }
}
