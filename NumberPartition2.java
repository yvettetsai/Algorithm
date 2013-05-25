/*
 * Name: NumberPartition2.java
 * 
 * Description: This java file input with an array of integers,
 *   	and then try to minimize the difference between two sub-sets
 * 		by running Karmarkar Karp algorithm and several different 
 * 		approach of randomized algorithm.
 * 
 * Author: Yvette (I-Ting) Tsai
 * Email: ytsai@bu.edu
 * Date: Dec 08, 2011
 * 
 */

import java.util.*;

public class NumberPartition2
{
	public static final long MAX = 1000000000;
	public static final long MIN = 1;
	public static final int SIZE = 100;
	public static final long[] ARRAY = new long[SIZE];
	public static final boolean[] SIGN = new boolean[SIZE];
	public static final int K = 2500000;
	
	
	/*
	 * randomInt()- return a randomly generate number
	 * 		within range of MIN and MAX 
	 */
	public static long randomLong()
	{
		Random generator = new Random();
		long n = MAX*1000 - MIN + 1;
		long t = (generator.nextLong()) % (n);
		
		if(t == (long) 0)
			return (n);
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
			
			// add random number into KK's priority queue
			pq.add(temp);	
						
			// add random number to psudo-poly array 
			ARRAY[i] = temp;
		}
		return pq;
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
		}
		
		return pq;
	}
	
	
	/*
	 * randomizeAlgorithm()- 
	 * 		it runs over K trials of repeated random residue,
	 * 		and local moves of gradient descent, and simulated
	 * 		annealing algorithm. Upon termination of the method
	 * 		it prints out the best residue of each algorithm 
	 */
	public static void randomizeAlgorithm()
	{
		CMP_Regular cmpR = new CMP_Regular();
		PriorityQueue<Long> rr_pq = new PriorityQueue<Long>(K, cmpR);
		
		long sum_GD = initialRandom();
		long sum_SA = initialRandom();
		
		// generate K number of random solution
		for(int j = 0; j < K; j ++)
		{
			rr_pq.add(repeatedRandom());
			
			long tempSumGD = gradientDescent(sum_GD);
						
			if(sum_GD > tempSumGD)
				sum_GD = tempSumGD;
			
			long tempSumSA = gradientDescent(sum_SA);

			if(sum_SA > tempSumSA)
				sum_SA = tempSumSA;
			else
				sum_SA = simulatedAnnealing(sum_SA, tempSumSA, j);
		}
		
		// return the smallest residue after K generation of trials
		System.out.println("");
		System.out.println("RR's residue = " + rr_pq.poll());
		System.out.println("");
		System.out.println("GD's residue = " + sum_GD);
		System.out.println("");
		System.out.println("SA's residue = " + sum_SA);
	}
	
	
	/*
	 * repeatedRandom()-
	 * 		this method go through each integer and assign 
	 * 		the +1 or -1 sign to each integer with equal
	 * 		probability
	 */
	public static long repeatedRandom()
	{
		Random generator = new Random();
		long sum = 0;
		
		for(int i = 0; i < SIZE; i++)
		{	
			if(generator.nextDouble() < 0.5) 
				sum += ARRAY[i];
			else 
				sum -= ARRAY[i];
		}
		return Math.abs(sum);
	}
	
	
	/*
	 * initialRandom()-
	 * 		this method return an initial random residue
	 * 		for Gradient Descent and Simulated Annealing
	 * 		approach
	 */
	public static long initialRandom()
	{
		Random generator = new Random();
		long sum = 0;
		
		for(int i = 0; i < SIZE; i++)
		{	
			if(generator.nextDouble() < 0.5) 
			{
				sum += ARRAY[i];
				SIGN[i] = true;
			}
			else 
			{
				sum -= ARRAY[i];
				SIGN[i] = false;
			}
		}
		
		return Math.abs(sum);
	}
	
	
	/*
	 *	gradientDescent(sum)-
	 *		Where sum is the residue sum obtain from completion. 
	 *		This method does a local research operates on neighborhoods.
	 *		It chooses two random indices i and j from [1, n] where 
	 *		i is not equals to j. Set Si to -Si and with probability
	 *		0.5 set Sj to -Sj. Return the new sum if it is smaller than 
	 *		the input sum.
	 */
	public static long gradientDescent(long sum)
	{
		Random generator = new Random();
		int i = generator.nextInt(SIZE);
		int j = generator.nextInt(SIZE);
	
		while(i == j)	j = generator.nextInt(SIZE);
		
		// set Si to -Si
		if(SIGN[i])
		{
			sum = sum - 2*ARRAY[i];
			SIGN[i] = false;
		}
		else
		{
			sum = sum + 2*ARRAY[i];
			SIGN[i] = true;
		}
		
		
		// with probability 0.5, set Sj to -Sj
		if(generator.nextDouble() < 0.5) 
		{
			if(SIGN[j])
			{
				sum = sum - 2*ARRAY[j];
				SIGN[j] = false;
			}
			else
			{
				sum = sum + 2*ARRAY[j];
				SIGN[j] = true;
			}
		}
		
		return Math.abs(sum);
	}
	
	
	/*
	 * simulatedAnnealing(betterSum, worseSum, k)-
	 * 		Where better residue sum is the residue obtain from 
	 * 		completion of a local move of gradient descent  
	 * 		of each iteration; k is the number of iteration. 
	 * 		This method return the worse residue sum with 
	 * 		probability p.
	 */
	public static long simulatedAnnealing(long betterSum, long worseSum, int k)
	{
		Random generator = new Random();
		
		// cooling schedule based on iteration k
		double t = Math.pow(10, 10)*Math.pow(0.8, k/300);
		
		// accepting worse moves with probability p
		double p = Math.exp(-(worseSum - betterSum)/t) ;
		
		if(generator.nextDouble() < p)
			return worseSum;
		else
			return betterSum;
	}
	
	
	public static void main(String[] args)
	{
		// a comparator which reverse the usual priority
		CMP_Irregular cmpI = new CMP_Irregular();
		
		for(int i = 0; i < 5; i ++)
		{
			System.out.println("***** Random Instance " + i + " *****");
		
			PriorityQueue<Long> kk_pq = new PriorityQueue<Long>(SIZE, cmpI);
			kk_pq = insert(kk_pq);

			// run through the Karmarkar Karp algorithm
			kk_pq = KKAlgorithm(kk_pq);
			System.out.println("KK's residue = " + kk_pq.peek());
			
			// run through all other three randomized algorithm
			randomizeAlgorithm();
			
			System.out.println("");
		}
	}
}
