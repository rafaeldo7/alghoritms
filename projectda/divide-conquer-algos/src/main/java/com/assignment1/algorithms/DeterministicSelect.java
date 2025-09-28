package com.assignment1.algorithms;

import com.assignment1.utils.Metrics;
import java.util.Arrays;

public class DeterministicSelect {
    public static int select(int[] arr, int k) {
        Metrics.reset();
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        Metrics.calls++;
        Metrics.currentDepth++;
        Metrics.maxDepth = Math.max(Metrics.maxDepth, Metrics.currentDepth);

        if (left == right) {
            Metrics.currentDepth--;
            return arr[left];
        }

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        if (k == pivotIndex) {
            Metrics.currentDepth--;
            return arr[k];
        } else if (k < pivotIndex) {
            int res = select(arr, left, pivotIndex - 1, k);
            Metrics.currentDepth--;
            return res;
        } else {
            int res = select(arr, pivotIndex + 1, right, k);
            Metrics.currentDepth--;
            return res;
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        return medianOfMedians(medians, 0, numMedians - 1);
    }

    private static int partition(int[] arr, int left, int right, int pivotValue) {
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivotValue) {
                Metrics.comparisons++;
                i++;
            }
            while (arr[j] > pivotValue) {
                Metrics.comparisons++;
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        return i - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        Metrics.swaps++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
