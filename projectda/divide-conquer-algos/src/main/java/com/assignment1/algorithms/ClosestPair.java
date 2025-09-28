package com.assignment1.algorithms;

import com.assignment1.utils.Metrics;
import com.assignment1.utils.Point;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double closestPair(Point[] points) {
        Metrics.reset();
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingInt(p -> p.x));
        return closest(sortedByX, 0, sortedByX.length - 1);
    }

    private static double closest(Point[] points, int left, int right) {
        Metrics.calls++;
        Metrics.currentDepth++;
        Metrics.maxDepth = Math.max(Metrics.maxDepth, Metrics.currentDepth);

        if (right - left <= 3) {
            double min = bruteForce(points, left, right);
            Metrics.currentDepth--;
            return min;
        }

        int mid = (left + right) / 2;
        double d1 = closest(points, left, mid);
        double d2 = closest(points, mid + 1, right);
        double d = Math.min(d1, d2);

        // Build strip
        Point midPoint = points[mid];
        Point[] strip = new Point[right - left + 1];
        int j = 0;
        for (int i = left; i <= right; i++) {
            Metrics.comparisons++;
            if (Math.abs(points[i].x - midPoint.x) < d) {
                strip[j++] = points[i];
            }
        }

        double minStrip = stripClosest(strip, j, d);

        Metrics.currentDepth--;
        return Math.min(d, minStrip);
    }

    private static double bruteForce(Point[] points, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                Metrics.comparisons++;
                double dist = points[i].distance(points[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    private static double stripClosest(Point[] strip, int size, double d) {
        Arrays.sort(strip, 0, size, Comparator.comparingInt(p -> p.y));
        double min = d;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; j++) {
                Metrics.comparisons++;
                double dist = strip[i].distance(strip[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }

        return min;
    }
}