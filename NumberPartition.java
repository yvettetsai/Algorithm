/*
 * Name: NumberPartition.java
 * 
 * Description: This java file input with an array of integers,
 * 		and then try to minimize the difference between two sub-sets
 * 		by running Karmarkar Karp algorithm and dynamic programming.
 * 
 * Author: Yvette (I-Ting) Tsai
 * Email: ytsai@bu.edu
 * Date: Nov 11, 2011
 * 
 */

import java.util.*;

public class NumberPartition 
{
	public static final long MAX = 100;
	public static final long MIN = 1;
	public static final int SIZE = 10;
	public static final long[] ARRAY = new long[SIZE];
	public static final long[] SUM = new long[1];
	
	
	/*
	 * randomInt()- return a randomly generate number
	 * 		within range of MIN and MAX 
	 */
	public static long randomLong()
	{
		Random generator = new Random();
		long n = MAX - MIN + 1;
		long t = (generator.nextLong()) % (n);
		
		if(t == (long) 0)
			return (MAX);
		else if(t < (long) 0)
			return (Math.abs(t));
		else
			return t;
	}
 
	
	/*
	 * difference(int n1, int n2)- it will return
	 * 		the abs(n1-n2)
	 */
	public static long difference(long n1, long n2)
	{
		return Math.abs(n1 - n2);
	}
	
	
	/*
	 * insert(PriorityQueue<Integer> pq)- this algorithm adds all the
	 * 		random integers into the priority queue 
	 */
	public static PriorityQueue<Long> insert(PriorityQueue<Long> pq)
	{	
		for(int i = 0; i < SIZE; i++)
		{	
			long temp = randomLong();
			System.out.print(temp + ", ");
			
			// add random number into KK's priority queue
			pq.add(temp);	
			
			// add up all the random integers
			SUM[0] += temp;
			
			// add random number to psudo-poly array 
			ARRAY[i] = temp;
		}
		
		System.out.println("");
		System.out.println("*********************");
		System.out.println("The total sum is " + SUM[0]);
		System.out.println("Half of the sum is " + SUM[0]/2);
		System.out.println("*********************");
		return pq;
	}
	
	
	
	/*
	 * insert(PriorityQueue<Integer> pq)- this algorithm adds all the
	 * 		random integers into the priority queue 
	 */
	public static PriorityQueue<Long> insert(PriorityQueue<Long> pq1, PriorityQueue<Long> pq2)
	{	
		for(int i = 0; i < SIZE; i++)
		{	
			long temp = randomLong();
			System.out.print(temp + ", ");
			
			// add random number into KK's priority queue
			pq1.add(temp);	
			
			// add up all the random integers
			SUM[0] += temp;
			
			// add random number to psudo-poly array 
			ARRAY[i] = temp;
		}
		
		System.out.println("");
		System.out.println("*********************");
		System.out.println("The total sum is " + SUM[0]);
		System.out.println("Half of the sum is " + SUM[0]/2);
		System.out.println("*********************");
		return pq1;
	}
	
	/*
	 * KKAlgorithm(PriorityQueue<Integer> pq)- it runs through Karmarkar Karp
	 * 		algorithm and then produce an achievable residue 
	 */
	public static PriorityQueue<Long> KKAlgorithm(PriorityQueue<Long> pq)
	{
		while(pq.size() > 1)
		{
			long num1 = pq.poll();
			long num2 = pq.poll();
			long temp = difference(num1, num2);
			pq.add(temp);
			System.out.println("(" + num1 + " - " + num2 + ") = " + temp);
		}
		
		return pq;
	}
	
	
	/*
	 * dynamic()- this method implements number partition problem with
	 * 		dynamic programming 
	 */
	public static void dynamic()
	{
		long sum = SUM[0]/2 + 1;		
		boolean[] sol = new boolean[(int) sum];
		
		sol[0] = true;
		
		for(long k = 0; k < ARRAY.length; k ++)
		{
			long i = ARRAY[(int) k];
			for(long j = (sum - 1); j >= i; j--)
			{
				if(sol[(int)(j-i)])
				{
					sol[(int)j] = true;
				}
			}
		}
		
		long halfsumcloser = sum - 1;

		while(!sol[(int)halfsumcloser])
			halfsumcloser --;

		long temp1 = SUM[0] - halfsumcloser;
		System.out.println("1st half is " + halfsumcloser);
		System.out.println("2nd half is " + temp1);
		System.out.println("The difference is " + Math.abs(halfsumcloser - temp1));
	}
	
	/*
	 * dynamicLong()- the long version for dynamic(), which it has
	 * 		two array. Both have lengths 10^7
	 */
	public static void dynamicLong()
	{
		long sum = SUM[0]/2 + 1;		
		
		boolean[] sol1 = new boolean[(int) MAX];
		boolean[] sol2 = new boolean[(int) MAX];
		
		sol1[0] = true;
		
		for(int k = 0; k < ARRAY.length; k ++)
		{
			long i = ARRAY[k];
			
			for(long j = (sum - 1); j >= i; j--)
			{
				if( (j - i) <= MAX)
				{
					if(sol1[(int) (j-i)])
					{
						if(j <= MAX)
							sol1[(int) j] = true;
						else
							sol2[(int) (j % MAX)] = true;
					}
				}
				else
				{
					if(sol2[(int) ((j-i) % MAX)])
					{
						if(j <= MAX)
							sol1[(int) j] = true;
						else
							sol2[(int) (j % MAX)] = true;
					}
				}
			}
		}
		
		long halfsumcloser = sum - 1;

		while(!sol2[(int)(halfsumcloser % MAX)])
			halfsumcloser --;

		long temp1 = SUM[0] - halfsumcloser;
		System.out.println("1st half is " + halfsumcloser);
		System.out.println("2nd half is " + temp1);
		System.out.println("The difference is " + Math.abs(halfsumcloser - temp1));
	}
	
	
	
	public static void main(String[] args)
	{
		// a comparator which reverse the usual priority
		CMP_Long cmp = new CMP_Long();
		PriorityQueue<Long> pq = new PriorityQueue<Long>(SIZE, cmp);
		
		// insert all random integers into the queue
		pq = insert(pq);
		
		// run through the Karmarkar Karp Algorithm
		pq = KKAlgorithm(pq);
		System.out.println(pq.peek());
		
		System.out.println("*********************");

		dynamic();
		
	}
}
