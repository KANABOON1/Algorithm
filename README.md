# This is my Algorithm repository

## Part I

Finish some little algorithm projects on the Princeton University's Coursera, and this is what I have done:

- [x] [Percolation](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php)  100/100

  To solve this problem, my solution is to use two WeightedQuickUnion with path compression, one with two virtual sites and the other uses just the top virtual(to solve the wachback problem).

- [x] [Double-ended queue and Randomized Queue](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php)  91/100

  Deque(doubly circular linked-list based): To simplify the boundary handling, a  sentinel node is used.

  Randomized Queue(singular linked-list based): Save much memory, but cannot maintain the uniform randomness! Use resizing array instead!

- [x] [Collinear](https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php)  100/100

  BruteCollinearPoints: Use loop to find four points in a line segment, but this solution has high time complexity and it's hard to detect more points.

  FastCollinearPoints: Use Sorting algorithm to sort the points according to their slope with a point,  the same group must be in the same line segment.

  

## Part II

Implement some algorithms:

- [x] Merge sort: Implement the in-place merge sort, including the top-down version(化整为零的思想) and the bottom-up version(循序渐进的思想). One disadvantage of the merge sort is that the auxiliary space is proportional to the array size N.
- [x] Basic sorts: Implement the selection sort(in-place, O(N^2), unstable), bubble sort(in-place, O(N^2), stable), and insertion sort(in-place, O(N^2), unstable). 

​       
