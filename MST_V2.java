/*
 * Class: MST_V2. java
 * 
 * Description: This program take in numbers of n nodes, and
 *   	build a complete graph upon this n nodes with n*(n-1)/2 edges.
 * 		Two types of edges are calculated, the first one is with 
 * 		uniformly random generate real number in the range of [0, 1] for
 * 		each possible edge in the graph. The second one is each node has 
 * 		coordinates (x, y) which are uniformly random generate real number 
 * 		in the range of [0, 1], yet the weidgt of edge between two nodes
 * 		are their Euclidean distance calculated from their coordinates.
 * 		After the graph is completely built, the program runs Prime's
 * 		algorithm to produce the minimum spanning tree and then output
 * 		the average weight of all edges.
 * 
 * Author: Yvette (I-Ting) Tsai
 * Email: ytsai@bu.edu
 * Date: Oct 13, 2011
 * 
 */

import java.util.*;
import java.io.*;

public class MST_V2 
{
	public static final LinkedList<Node> edgesList1 = new LinkedList<Node>();
	public static final LinkedList<Node> edgesList2 = new LinkedList<Node>();
	
	/*
	 * The main method for the graph
	 */
	public static void main(String[] args) throws IOException
	{
		int vertices = 16;
		int edges = vertices*(vertices-1)/2;
		
		LinkedList[] adjList = new LinkedList[vertices];
		initial(adjList, vertices);
		
		// For Graph 1
		adjList = buildGraph(adjList, vertices, edges);
		
		// For Graph 2
//		adjList = buildEuclideanGraph(adjList, vertices, edges);
		
		System.out.println(kruskal(adjList, vertices, edges));
//		prim(adjList, edges);
	}

	
	/*
	 * initial(LinkedList[], int l)
	 * 	This method initialize the array of LinkedList
	 */
	public static void initial(LinkedList[] adj, int numV)
	{
		for(int i = 0; i < numV; i++)
		{	adj[i] = new LinkedList<Node>();	}
	}
	
	
	/*
	 * randomNum()
	 * 	This method return a random number from [0,1]
	 */
	public static double randomNum()
	{	
		Random generator = new Random(100);
		return Math.random();	
		}
	
	
	/*
	 * buildGraph(LinkedList[] adj, int v, int e)
	 * 	This method build up the graph using the adjency list
	 * 
	 * Running Time: O(n^2)
	 * 
	 */
	public static LinkedList[] buildGraph(LinkedList[] adj, int v, int e) 
	{
		for(int i = 0; i < v; i++)
		{
			for(int j = i+1; j < v; j++)
			{
				double w = randomNum();		     
				adj[i].add(new Node(i, j, w));
			}
		}
		return adj;
	}
	
	
	/*
	 * buildEuclideanGraph(LinkedList[] adj, int v, int e)
	 * 	This method calculate each possible weight of edge between
	 * 	two nodes using Euclidean Distance.
	 */
	public static LinkedList[] buildEuclideanGraph(LinkedList[] adj, int v, int e) throws IOException
	{
		for(int i = 0; i < v; i++)
		{
			Node tempN = new Node(i, 0, 2);
			tempN.x = randomNum();				
			tempN.y = randomNum();
			adj[i].add(tempN);
		}
				
		for(int i = 0; i < v; i++)
		{
			Node tempN1 = (Node) adj[i].pollFirst();
			
			for(int j = i+1; j < v; j++)
			{
				Node tempN2 = (Node) adj[j].getFirst();
				double w = getDis(tempN1, tempN2);
					
				adj[i].addLast(new Node(i, j, w));
				adj[j].addLast(new Node(j, i, w));
//				edgesList2.add(new Node(i, j, w));
			}
		}
		
		return adj;
	}
	
	
	/*
	 *	getDis(Node n1, Node n2)
	 *		This method use Euclidean Distance formula to calculate
	 *		this distance between two coordinates 
	 */
	public static double getDis(Node n1, Node n2)
	{	
		return Math.sqrt(Math.pow((n1.x - n2.x), 2) + Math.pow((n1.y - n2.y), 2));
	}
	

	/*
	 *	averageWeight(LinkedList<Node> list)
	 *		This method average out the weight of the
	 *		minimum spinning tree 
	 *
	 *	Running Time: O(n)
	 */
	public static double averageWeight(LinkedList<Node> list)
	{
		double size = list.size();
		double sum = 0.0;
		for(int i = 0; i < size; i ++)
		{	
			double temp = list.get(i).weight;
			System.out.println(temp);
			sum += temp;
		}
		System.out.println("***********************");
		return sum/size;
	}
	
	
	/*
	 * 	kruskal(LinkedList<Node> edges, int v, int e)
	 * 		This method is as its name, run through
	 * 		the Kruskal's Algorithm to creat a MST
	 */
	public static double kruskal(LinkedList[] adj, int v, int e)
	{	
		CMP_V2 cmp = new CMP_V2();
		PriorityQueue<Node> edgesPQ = new PriorityQueue<Node>(e, cmp);
		
		LinkedList<Node> mst = new LinkedList<Node>();
		
		UnionFind cluster = new UnionFind(v);
				
		for(int i = 0; i < edgesList1.size(); i++)
		{	edgesPQ.add(edgesList1.get(i));	}
		
		while(mst.size() < (v-1))
		{
			Node tempS = edgesPQ.poll();
			int startV = tempS.startV;
			int endV = tempS.endV;
			
			if(!(cluster.find(startV, endV)))
			{
				mst.add(tempS);
				cluster.union(startV, endV);
			}	
		}
		return averageWeight(mst);
	}


	/*
	 *	prime(LinkedList[] adj, int numE)
	 *		This method implement the Prime's algorith to find the
	 *		minimum spanning tree 
	 */
	public static void prim(LinkedList[] adj, int numE)
	{
		double[] cost = new double[adj.length];
		double min = 2;
		
		CMP_V2 cmp = new CMP_V2();
		LinkedList<Node> explored = new LinkedList<Node>();
		PriorityQueue<Node> verticesPQ = new PriorityQueue<Node>(numE, cmp);
		
		for(int i = 0; i < adj.length; i ++)
		{	
			cost[i] = 2;	
			verticesPQ.add(new Node(i, i, 2));
		}
		
		while(!verticesPQ.isEmpty())
		{
			Node u = verticesPQ.poll();
			explored.add(u);
			int size = adj[u.startV].size();
			for(int i = 0; i < size; i++)
			{
				Node v = (Node) adj[u.startV].get(i);
				double costV = v.weight;
				
				if((!(explored.contains(v))) & costV < cost[v.endV])
				{	
					cost[v.endV] = costV;
					if(costV < min)	{ min = costV; }
				}
			}
		}
	
		double sum = 0;
		for(int i = 0; i < cost.length; i++)
		{
			double temp = cost[i];
			sum += temp;
			System.out.println(temp);
		}
		System.out.println("********************");
		System.out.println((sum - min)/(cost.length - 1.0));
	}
}
