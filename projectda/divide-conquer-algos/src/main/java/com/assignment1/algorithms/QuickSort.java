package com.assignment1.algorithms;

import com.assignment1.utils.Metrics;
import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] arr) {
        Metrics.reset();
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int left, int right) {
        int currentLeft = left;
        int currentRight = right;
        int depth = 0;

        while (currentLeft < currentRight) {
            Metrics.calls++;
            depth++;
            Metrics.maxDepth = Math.max(Metrics.maxDepth, depth);

            int pivotIndex = partition(arr, currentLeft, currentRight);

            if (pivotIndex - currentLeft < currentRight - pivotIndex) {
                sort(arr, currentLeft, pivotIndex - 1);
            } else {
                sort(arr, pivotIndex + 1, currentRight);
                currentRight = pivotIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivotIndex = left + rand.nextInt(right - left + 1);
        swap(arr, pivotIndex, right);
        int pivot = arr[right];
        int i = left;

        for (int j = left; j < right; j++) {
            Metrics.comparisons++;
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) return; // Не считаем обмены на месте
        Metrics.swaps++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}