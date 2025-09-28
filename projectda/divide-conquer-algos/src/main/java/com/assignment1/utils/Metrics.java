package com.assignment1.utils;

public class Metrics {
    public static long comparisons = 0;   // comparisons between elements
    public static long copies = 0;        // array copies (MergeSort buffer, etc.)
    public static long swaps = 0;         // element swaps (QuickSort, Select)
    public static long calls = 0;         // recursive calls or top-level calls
    public static int maxDepth = 0;       // maximum recursion depth
    public static int currentDepth = 0;   // current recursion depth

    public static void reset() {
        comparisons = 0;
        copies = 0;
        swaps = 0;
        calls = 0;
        maxDepth = 0;
        currentDepth = 0;
    }
}