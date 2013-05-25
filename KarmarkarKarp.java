import java.util.*;



public class KarmarkarKarp 
{
  public static final int MAX = 1000000;
	public static final int MIN = 1;
	public static final int SIZE = 100;
	public static final int[] ARRAY = new int[SIZE + 1];
	
	
	/*
	 * randomInt()- return a randomly generate number
	 * 		within range of MIN and MAX 
	 */
	public static int randomInt()
	{
		Random generator = new Random();
		int t = generator.nextInt(MAX);
		if(t == 0)
			t ++;
		return t;
	}
 
	
	/*
	 * difference(int n1, int n2)- it will return
	 * 		the abs(n1-n2)
	 */
	public static int difference(int n1, int n2)
	{
		if(n1 >= n2)
			return (n1 - n2);
		else
			return (n2 - n1);
	}
	
	
	/*
	 * insert(PriorityQueue<Integer> pq)- this algorithm adds all the
	 * 		random integers into the priority queue 
	 */
	public static PriorityQueue<Integer> insert(PriorityQueue<Integer> pq)
	{	
		for(int i = 0; i < SIZE; i++)
		{	
			int temp = randomInt();
			
			// add random number into KK's priority queue
			pq.add(temp);	
			
			// add up all the random integers
			ARRAY[0] += temp;
			
			// add random number to psudo-poly array 
			ARRAY[i] = temp;
		}
			
		return pq;
	}
	
	
	/*
	 * KKAlgorithm(PriorityQueue<Integer> pq)- it runs through Karmarkar Karp
	 * 		algorithm and then produce an achievable residue 
	 */
	public static PriorityQueue<Integer> KKAlgorithm(PriorityQueue<Integer> pq)
	{
		while(pq.size() > 1)
		{
			int num1 = pq.poll();
			int num2 = pq.poll();
			pq.add(difference(num1, num2));
		}
		
		return pq;
	}
	
	
	/*
	 * dynamic()- this method implements number partition problem with
	 * 		dynamic programming 
	 */
	public static void dynamic()
	{
		int sum = ARRAY[0]/2 + 1;
		
		
		boolean[] sol = new boolean[sum];
		sol[0] = true;
		for(int i : ARRAY)
		{
			for(int j = (sum - 1); j >= i; j--)
			{
				if(sol[j-i])
					sol[j] = true;
			}
		}
		
		int halfsumcloser = sum - 1;
		for(; !sol[halfsumcloser]; halfsumcloser --);
		System.out.println("The half sum closer is " + halfsumcloser);
		System.out.println("The another half is " + (ARRAY[0] - halfsumcloser));
		System.out.println("---------------------------------");		
		
	}
	
	
	/*
	 * optMAX(int[][] m, int i, int w)- this is the OPT 
	 * 		for dynamic programming, which returns the maximum
	 */
	public static int optMAX(int[][] m, int i, int w)
	{
		int n1 = m[i][w];
		int n2 = ARRAY[i] + m[i-1][w - ARRAY[i]];
		
		return Math.max(n1, n2);	                
	}
	
	public static void main(String[] args)
	{
		// a comparator which reverse the usual priority
		CMP cmp = new CMP();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(SIZE, cmp);
		
		// insert all random integers into the queue
		pq = insert(pq);
		
		System.out.println("");
		System.out.println("");
		
		// run through the Karmarkar Karp Algorithm
		pq = KKAlgorithm(pq);
		
		int residue = pq.peek();
		System.out.println("The reslut from KK Algorithm is " + residue);
		System.out.println("-------------------------------");
		
		dynamic();
		
	}
}
