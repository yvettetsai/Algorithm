/*
 * Class: CMP.java
 * 
 * Description: A comparator class which specific
 *   the order of priority for KK.java would
 *   like to have. It overwritten the one
 *   in Java's API.
 * 
 * Author: Yvette (I-Ting) Tsai
 * Email: ytsai@bu.edu
 * Date: Oct 13, 2011
 * 
 */

import java.util.Comparator;

/*
 * CMP- a comparator class for the priority queue
 */
class CMP implements Comparator<Integer>
{
 public int compare(Integer n1, Integer n2)
 {
  if (n1 < n2) 
   return 1;
  if (n1 > n2) 
   return -1;
  
  return 0;
 }
}
