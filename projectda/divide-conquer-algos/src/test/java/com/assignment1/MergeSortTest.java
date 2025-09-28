package com.assignment1;

import com.assignment1.algorithms.MergeSort;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {
    @Test
    void testRandomArray() {
        int[] arr = {5, 2, 9, 1, 6, 3};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int[] expected = arr.clone();
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = {7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7};
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testDuplicates() {
        int[] arr = {5, 1, 5, 3, 1, 5};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testLargeReverseSortedArray() {
        int n = 1000;
        int[] arr = IntStream.range(0, n).map(i -> n - 1 - i).toArray();
        int[] expected = IntStream.range(0, n).toArray();
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }
}