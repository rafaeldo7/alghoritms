package com.assignment1;

import com.assignment1.algorithms.QuickSort;
import com.assignment1.utils.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuickSortTest {
    @Test
    void testRandomArray() {
        int[] arr = {8, 4, 7, 3, 9, 2};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testDuplicates() {
        int[] arr = {5, 1, 5, 3, 1, 5};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = IntStream.range(0, 100).toArray();
        int[] expected = arr.clone();
        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = IntStream.range(0, 100).map(i -> 99 - i).toArray();
        int[] expected = IntStream.range(0, 100).toArray();
        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRecursionDepth() {
        int n = 1000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = (int)(Math.random() * 1000);

        Metrics.reset();
        QuickSort.sort(arr);

        // Теоретический максимум для случайного pivot ~2 * log2(n)
        double expectedMaxDepth = 2 * (Math.log(n) / Math.log(2)) + 2;
        System.out.println("QuickSort Max Depth: " + Metrics.maxDepth + ", Expected max: " + expectedMaxDepth);
        assertTrue(Metrics.maxDepth <= expectedMaxDepth, "Recursion depth is too high");
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        QuickSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }
}
