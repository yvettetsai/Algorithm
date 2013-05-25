/*
 * Class: CMP_V2.java
 * 
 * Description: A comparator class which specific
 *   	the order of priority MST_V2.java would
 * 		like to have. It overwritten the one
 * 		in Java's API.
 * 
 * Author: Yvette (I-Ting) Tsai
 * Email: ytsai@bu.edu
 * Date: Oct 13, 2011
 * 
 */

import java.util.Comparator;

/*
 * CMP_V2- a comparator class for the priority queue
 */
class CMP_V2 implements Comparator<Node>
{
	public int compare(Node n1, Node n2)
	{
		if (n1.weight < n2.weight) 
			return -1;
		if (n1.weight > n2.weight) 
			return +1;
		if(n1.startV < n2.startV)
			return -1;
		if(n1.startV > n2.startV)
			return +1;
		if(n1.endV < n2.endV)
			return -1;
		if(n1.endV > n2.endV)
			return 1;
		
		return 0;
	}
}
