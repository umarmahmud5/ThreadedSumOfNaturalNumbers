package naturalnumbers;
/**
 * @(#)SumofNaturalNumbers.java
 * This code calculates the sum of natural numbers up to (Integer.Max_Value-5 = 2147483642)
 * The sum is 2305842997402533903
 * This code uses two threads, one to add odd numbers and the otehr to add even numbers
 * @Max Radl
 * @version 2.00 2020/12/8
 */
public class ThreadedSumofNaturalNumbers extends Thread {

    final int maxValue = Integer.MAX_VALUE-5;      //Integer.MAX_VALUE = 2147483647  //Long.MAX_VALUE= 9223372036854775807 //353000000
    long oddSum = 0;
    long evenSum = 0;

    public ThreadedSumofNaturalNumbers(String threadName) {
        super(threadName);
        //System.out.println("The maximum value is: " + maxValue);
        // System.out.println();
        ///System.out.println("The sum upto " + maxValue + " is: " + calculateSum());

    }

    public void run() {
        if (Thread.currentThread().getName().startsWith("oddAddition")) {
            oddSum = calculateSum(1);
            //System.out.println("Odd Sum: "+oddSum);
        } else if (Thread.currentThread().getName().startsWith("evenAddition")) {
            evenSum = calculateSum(2);
            //System.out.println("Even Sum: "+evenSum);
        }
    }

    private long calculateSum(int startNumber) {
        long sum = 0;
        try {
            long startTime = System.nanoTime();
            for (int i = startNumber; i <= maxValue; i = i + 2) {
                sum = sum + i;
                //System.out.println("Value of i is: "+i+" and sum is: "+sum);
            }
            //System.out.println("Sum: "+sum);
            long endTime = System.nanoTime();
            System.out.println("Total Time taken by "+Thread.currentThread().getName()+ " is: " + (endTime - startTime));
        } catch (Exception e) {
            System.err.println(e);
        }
        return sum;
    }

    public static void main(String args[]) {
        ThreadedSumofNaturalNumbers odd = new ThreadedSumofNaturalNumbers("oddAddition");
        ThreadedSumofNaturalNumbers even = new ThreadedSumofNaturalNumbers("evenAddition");
        odd.start();
        even.start();
        try {
            odd.join();
            even.join();   //We need to wait for both threads to complete before final sum is generated.
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("Sum of odd natural numbers upto is: " + odd.oddSum);
        System.out.println("Sum of even natural numbers upto is: " + even.evenSum);
        System.out.println("Sum is: " + ((odd.oddSum) + (even.evenSum)));

    }
}
