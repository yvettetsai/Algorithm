/*
 * Class: CMP_Irregular.java
 * 
 * Description: A comparator class which specific
 *   the order of priority for KK.java would
 *   like to have. It overwritten the one
 *   in Java's API.
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
class CMP_Irregular implements Comparator<Long>
{
  public int compare(Long n1, Long n2)
	{
		if (n1 < n2) 
			return 1;
		if (n1 > n2) 
			return -1;

		return 0;
	}
}
