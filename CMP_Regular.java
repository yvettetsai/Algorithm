/*
 * Class: CMP_Regular.java
 * 
 * Description: A comparator class with regular 
 *   	priority as usual priority queue. 
 * 		The smallest value in the head, the 
 * 		larger in the tail.
 * 
 * Author: Yvette (I-Ting) Tsai
 * Email: ytsai@bu.edu
 * Date: Dec 08, 2011
 * 
 */

import java.util.Comparator;

/*
 * CMP- a comparator class for the priority queue
 */
class CMP_Regular implements Comparator<Long>
{
	public int compare(Long n1, Long n2)
	{
		if (n1 > n2) 
			return 1;
		if (n1 < n2) 
			return -1;

		return 0;
	}
}
