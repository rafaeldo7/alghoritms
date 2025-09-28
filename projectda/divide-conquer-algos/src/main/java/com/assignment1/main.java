package com.assignment1;

import com.assignment1.algorithms.*;
import com.assignment1.utils.Metrics;
import com.assignment1.utils.Point;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String CSV_FILE = "metrics.csv";

    public static void main(String[] args) {
        createCsvWithHeaderIfNotExists();

        while (true) {
            System.out.println("Choose an algorithm:");
            System.out.println("1 - MergeSort");
            System.out.println("2 - QuickSort");
            System.out.println("3 - Deterministic Select (k-th element)");
            System.out.println("4 - Closest Pair");
            System.out.println("0 - Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1 -> runMergeSort();
                case 2 -> runQuickSort();
                case 3 -> runDeterministicSelect();
                case 4 -> runClosestPair();
                default -> System.out.println("Invalid option: " + choice);
            }
        }
    }

    private static int[] inputArray() {
        System.out.print("Enter array size: ");
        int n = scanner.nextInt();

        System.out.print("1 - Manual input, 2 - Random array: ");
        int mode = scanner.nextInt();

        int[] arr = new int[n];
        if (mode == 1) {
            System.out.println("Enter elements:");
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
        } else {
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                arr[i] = rand.nextInt(1000);
            }
            System.out.println("Generated array: " + Arrays.toString(arr));
        }
        return arr;
    }

    private static Point[] inputPoints() {
        System.out.print("Enter number of points: ");
        int n = scanner.nextInt();
        Point[] points = new Point[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            points[i] = new Point(rand.nextInt(1000), rand.nextInt(1000));
        }
        System.out.println("Generated points: " + Arrays.toString(points));
        return points;
    }

    private static void runMergeSort() {
        int[] arr = inputArray();
        System.out.println("Before: " + Arrays.toString(arr));

        Metrics.reset();
        long start = System.nanoTime();
        MergeSort.sort(arr);
        long end = System.nanoTime();

        System.out.println("After: " + Arrays.toString(arr));
        printMetrics(start, end);
        writeMetricsToCsv("mergesort", arr.length, end - start);
    }

    private static void runQuickSort() {
        int[] arr = inputArray();
        System.out.println("Before: " + Arrays.toString(arr));

        Metrics.reset();
        long start = System.nanoTime();
        QuickSort.sort(arr);
        long end = System.nanoTime();

        System.out.println("After: " + Arrays.toString(arr));
        printMetrics(start, end);
        writeMetricsToCsv("quicksort", arr.length, end - start);
    }

    private static void runDeterministicSelect() {
        int[] arr = inputArray();
        System.out.print("Enter k (0-indexed): ");
        int k = scanner.nextInt();

        Metrics.reset();
        long start = System.nanoTime();
        int kth = DeterministicSelect.select(arr.clone(), k);
        long end = System.nanoTime();

        System.out.println(k + "-th element = " + kth);
        printMetrics(start, end);
        writeMetricsToCsv("select", arr.length, end - start);
    }

    private static void runClosestPair() {
        Point[] points = inputPoints();

        Metrics.reset();
        long start = System.nanoTime();
        double dist = ClosestPair.closestPair(points);
        long end = System.nanoTime();

        System.out.printf("Minimum distance = %.4f%n", dist);
        printMetrics(start, end);
        writeMetricsToCsv("closestpair", points.length, end - start);
    }

    private static void printMetrics(long start, long end) {
        System.out.println("--- Metrics ---");
        System.out.println("Comparisons = " + Metrics.comparisons);
        System.out.println("Copies      = " + Metrics.copies);
        System.out.println("Swaps       = " + Metrics.swaps);
        System.out.println("Calls       = " + Metrics.calls);
        System.out.println("Max depth   = " + Metrics.maxDepth);
        System.out.printf("Time        = %.4f ms%n%n", (end - start) / 1_000_000.0);
    }

    private static void writeMetricsToCsv(String algo, int size, long timeNs) {
        try (FileWriter writer = new FileWriter(CSV_FILE, true)) {
            writer.append(String.format("%s,%d,%d,%d,%d,%d,%d,%d\n",
                    algo,
                    size,
                    timeNs,
                    Metrics.comparisons,
                    Metrics.swaps,
                    Metrics.copies,
                    Metrics.calls,
                    Metrics.maxDepth
            ));
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    private static void createCsvWithHeaderIfNotExists() {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(CSV_FILE)) {
                writer.append("Algorithm,Size,Time(ns),Comparisons,Swaps,Copies,Calls,MaxDepth\n");
            } catch (IOException e) {
                System.err.println("Error creating CSV file: " + e.getMessage());
            }
        }
    }
}