/*
 * Class: Node.java
 * 
 * Description: This Node class is a object class create 
 *   	to help store the information of the graph. Each 
 * 		node stores information of its id and another id 
 * 		for another node it connects to. 
 * 
 * Author: Yvette (I-Ting) Tsai
 * Email: ytsai@bu.edu
 * Date: OCt 13, 2011
 * 
 */

public class Node 
{

	int startV, endV;
	double weight;
	boolean visited;
	double x, y;
	
	public Node(int s, int e, double w)
	{
		startV = s;
		endV = e;
		weight = w;
		visited = false;
		x = 0.0;
		y = 0.0;
	}
	
	
	public int getStartNode()
	{	
		return startV;	
	}
	
	
	public int getEndNode()
	{	
		return endV;	
	}
	
	
	public double getWeight()
	{	
		return weight;	
	}
	
	
	public boolean getVisit()
	{	
		return visited;	
	}
	
}
