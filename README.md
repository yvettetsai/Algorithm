CS330-Algorithm
===============

This repository contains some popular algorithm's implementation in Java.


Project 1 - MST
===============
Goal: 
  To determine how the average weight of the minimum spanning tree 
  grows as a function of n for each of these families of graphs. Where 
  weights are asign with two option. Option one, weight of each edge is 
  a real number chosen uniformaly at random from [0,1]. Option two, weight
  of each edge is the Euclidean distance between its endpoints where the
  endpoints are chosen uniformly at radnom inside the unit square.

Files:
  CMP_V2.java
  MST_V2.java
  Node.java
  UnionFind.java
  
  
Project 2 - Number Partition I
==============================
Goal: 
  Number Partition is a NP-Complete problem, but we can be solved in 
  pseudopolynomial time. Our goal is to try two different approaches
  to solve the problem. First approach is to use dynamic programming.
  Second approach is to use Karmarkar-Karp algorithm, a deterministic 
  heuristic. 
  
Files:
  NumberPartition.java
  CMP.java
  
  
Project 3 - Number Partition II
==============================
Goal: 
  Revisiting the Number Partition problem in project 2, this time 
  using local search heuristics to solve the problem. Try three
  different local search heruistics to solve the problem. First
  approach, we use repeated random. Generat k random solution to the
  problem and return the smallest residue. Second approach, we use
  gradient descent. Third approach, we use simulated annealing.
  Comparing these three approaches with the two approaches used in 
  project 2. 
  
Files:
  NumberPartition2.java
  CMP_Irregular.java
  CMP_Regular.java
  


