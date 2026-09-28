# Matrix Chain Multiplication Optimization Using Dynamic Programming 


### Matrix Chain Multiplication is a fundamental optimization problem in Data 
Structures and Algorithms that focuses on finding the most efficient order for 
multiplying a sequence of matrices. Although the final result remains the same 
regardless of the order of multiplication, different parenthesizations can require 
significantly different numbers of scalar multiplications. This project presents a 
Java-based optimization tool that uses Dynamic Programming, specifically 
Interval Dynamic Programming, to determine the optimal multiplication order 
for a given chain of matrices. 
The system accepts matrix dimensions as input and constructs dynamic 
programming tables to calculate the minimum number of scalar multiplications 
required. It also identifies and displays the optimal parenthesization of the 
matrix chain. To demonstrate the effectiveness of the optimization, the system 
compares the Dynamic Programming approach with a naïve multiplication 
strategy and presents the reduction in computational cost. The proposed solution 
has a time complexity of O(n³) and a space complexity of O(n²) for a chain of n 
matrices. The project demonstrates the practical application of Dynamic 
Programming in solving optimization problems efficiently and provides a clear 
visualization of the difference between optimized and non-optimized 
multiplication strategies.
