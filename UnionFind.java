/*
 * Class: UnionFind.java
 * 
 * Description: This UnionFind class is used to help
 *   	speed up the process during Krustal's algorithm
 * 		to check between each cluster.
 * 
 * Author: Yvette (I-Ting) Tsai
 * Email: ytsai@bu.edu
 * Date: Oct 13, 2011
 * 
 */

public class UnionFind 
{
	int[] id;
	int[] size;
	
	/*
	 * The constructor for class UnionFind
	 */
	public UnionFind(int n)
	{
		id = new int[n];
		size = new int[n];
		for(int i = 0; i < n; i++)
		{	
			id[i] = i;
			size[i] = 1;
		}
	}
	
	
	public int root(int i)
	{
		while(i != id[i])
		{ 
			id[i] = id[id[i]];
			i = id[i];	
		}
		
		return i;
	}

	
	public boolean find(int v, int u)
	{	return root(v) == root(u);	}
	
	
	public void union(int v, int u)
	{
		int i = root(v);
		int j = root(u);
		if(size[i] < size[j])
		{
			id[i] = j;
			size[j] += size[i];
		}
		else
		{
			id[j] = i;
			size[i] += size[j];
		}
	}
}
