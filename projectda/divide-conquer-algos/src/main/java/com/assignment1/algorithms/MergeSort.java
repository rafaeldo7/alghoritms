package com.assignment1.algorithms;

import com.assignment1.utils.Metrics;

public class MergeSort {
    private static final int CUTOFF = 10;

    public static void sort(int[] arr) {
        int[] buffer = new int[arr.length];
        sort(arr, buffer, 0, arr.length - 1, 1);
    }

    private static void sort(int[] arr, int[] buffer, int left, int right, int depth) {
        Metrics.calls++;
        Metrics.maxDepth = Math.max(Metrics.maxDepth, depth);

        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right);
            return;
        }

        if (left >= right) return;

        int mid = left + (right - left) / 2;
        sort(arr, buffer, left, mid, depth + 1);
        sort(arr, buffer, mid + 1, right, depth + 1);
        merge(arr, buffer, left, mid, right);
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        // Копируем только необходимый участок в буфер
        for (int i = left; i <= right; i++) {
            buffer[i] = arr[i];
            Metrics.copies++;
        }

        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                arr[k] = buffer[j++];
                Metrics.copies++;
            } else if (j > right) {
                arr[k] = buffer[i++];
                Metrics.copies++;
            } else {
                Metrics.comparisons++;
                if (buffer[i] <= buffer[j]) {
                    arr[k] = buffer[i++];
                    Metrics.copies++;
                } else {
                    arr[k] = buffer[j++];
                    Metrics.copies++;
                }
            }
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                Metrics.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    Metrics.swaps++; // Технически это сдвиг, но считаем как обмен
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
}