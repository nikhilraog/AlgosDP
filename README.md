AlgosDP
=======

Algorithm Dynamic Programming

Problem: 

This is a generalization of the LMS problem.  In this project, we will
use just "monotonic" for monotonically increasing.  

Your program reads its input from the standard input (stdin).  The
first line of input has 2 integers, n and k.  The next n lines of the
input specify the elements.  Each element has a value (A[i]) and a
weight (W[i]).  

A sample input:
6 1    # n=6, k=1
2 1    # A[1]=2, W[1]=1
8 10   # A[2]=8, W[2]=10
3 4    # A[3]=3, W[3]=4
5 1    # A[4]=5, W[4]=1
7 4    # A[5]=7, W[5]=4
11 5   # A[6]=11, W[6]=5


Part a.
Find a monotonic subsequence of A, whose total weight is maximal. 
There will be 3 lines of output.  The first line has the total weight.
The second line has indexes of the optimal subsequence, and the third
line has the actual subsequence itself.

For the sample input, if weights are not considered, then an LMS has
length 5: {2,3,5,7,11}.  But if weights are considered, then its total
weight is 15.  But the subsequence {2,8,11} has fewer elements, but
its weight is 16.  The goal is to find a monotonic subsequence of A,
whose total weight is as big as possible.

Output for part a:
16
1 2 6
2 8 11

Part b.  
Find a subsequence of A, which can violate monotonicity at most k
times.  For a subsequence that is not monotonic, its weight is the sum
of the weights of its elements, minus the penalties for violations.
The penalty for a violation (a,b) with a>b, is equal to the 
weight of b, times (a-b).

For the sample input, there is a subsequence {2,8,5,7,11}.  This is
not monotonic, but it violates monotonicity only once (8,5).  The
weight of this subsequence is 1+10+1+4+5-(8-5)*1 = 18.  Here, there is
a single violating pair (8,5), and its penalty is (8-5)*Weight of
element 5 = (8-5)*W[4] = 3*1 = 3.

The goal is to find a subsequence, that violates monotonicity at most
k times, such that the sum of the weights, minus the penalties, is a maximum.

Output for part b:
18
1 2 4 5 6
2 8 5 7 11


To Run:

* Download the lmsdp.java file and just run it like any other java file ( javac lmsdp.java && java lmsdp )
