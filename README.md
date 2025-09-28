# alghoritms
Assignment 1 Report: Divide and Conquer Algorithms

Introduction

The goal of this assignment was to implement classic divide and conquer algorithms, analyze their running time using the Master Theorem and Akra-Bazzi intuition, and validate the theory with experiments. During this process, metrics such as execution time, recursion depth, and the number of comparisons were collected. The algorithms implemented were MergeSort, QuickSort, Deterministic Select (Median of Medians), and Closest Pair of Points.

Implementation Details

MergeSort

The implementation follows the classic "divide and conquer" recursive strategy. To avoid repeated memory allocation, a reusable buffer is employed; it is created once and passed to all recursive calls. To improve performance on small sub-arrays (size 10 or less), a cutoff to Insertion Sort is used, which reduces the overhead of recursion.

QuickSort

To enhance the algorithm's reliability and avoid worst-case performance on sorted data, a randomized pivot selection strategy is used. To guarantee a logarithmic recursion depth of O(log n), the recursive call is made only for the smaller partition, while the larger partition is handled iteratively within a loop. This ensures stability against stack overflow.

Deterministic Select

The algorithm is implemented using the "Median of Medians" method to find the k-th smallest element in linear time O(n). Elements are grouped by 5, the median of each group is found, and then the median of these medians is recursively found to be used as the pivot. The array partitioning is done in-place, and recursion only proceeds into the partition where the sought element lies.

Closest Pair of Points

The points are first sorted by their x-coordinate. The array is then recursively divided in half, and the problem is solved for each half. In the "combine" step, points within a central "strip" are checked. For efficiency, these points are pre-sorted by their y-coordinate. During the check, each point is compared with no more than 7-8 of its neighbors, which ensures an overall complexity of O(n log n).

Recurrence Analysis

    MergeSort: T(n) = 2T(n/2) + Θ(n). By the Master Theorem (Case 2), this resolves to Θ(n log n).

    QuickSort: The average case is T(n) ≈ 2T(n/2) + Θ(n), which also results in Θ(n log n). The worst case is Θ(n²), but randomization makes this highly unlikely.

    Deterministic Select: T(n) = T(n/5) + T(7n/10) + Θ(n). By the Akra-Bazzi theorem intuition, since n/5 + 7n/10 < n, the complexity is linear, Θ(n).

    Closest Pair: T(n) = 2T(n/2) + Θ(n). By the Master Theorem (Case 2), this is Θ(n log n).

Experimental Results

Time vs. N
<img width="1200" height="700" alt="time_vs_n" src="https://github.com/user-attachments/assets/8e34c722-976b-4868-8848-b1fe8cad14a5" />

The execution time plots show that MergeSort and QuickSort exhibit the expected n log n growth. QuickSort is often faster in practice due to more efficient cache utilization. Deterministic Select shows linear growth, but its larger constant factor makes it slower on smaller array sizes. Closest Pair also aligns with its theoretical n log n complexity.

Recursion Depth vs. N
<img width="1200" height="700" alt="depth_vs_n" src="https://github.com/user-attachments/assets/3a895fa4-0f7e-4c02-8951-fc81b14dee3d" />


The recursion depth graphs confirm that MergeSort has a logarithmic depth. QuickSort also stays within O(log n) thanks to the optimization of recursing only on the smaller partition. Select has a very shallow recursion depth because it only descends into one of the partitions.


Conclusion

The experiments fully validate the theoretical analysis. QuickSort and MergeSort both perform in Θ(n log n) time, but QuickSort tends to be more efficient in practice. Deterministic Select confirmed its linear complexity, although it was slower for small inputs due to its high constant factor. Overall, the implementation results align well with theory, and the collected metrics clearly demonstrate how recursion control and memory management techniques affect real-world performance.
