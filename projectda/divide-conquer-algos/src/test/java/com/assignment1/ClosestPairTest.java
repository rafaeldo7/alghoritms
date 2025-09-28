package com.assignment1;

import com.assignment1.algorithms.ClosestPair;
import com.assignment1.utils.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {
    @Test
    void testSmallPoints() {
        Point[] points = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(1, 1),
                new Point(2, 2)
        };
        double result = ClosestPair.closestPair(points);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }

    @Test
    void testTwoPoints() {
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0)
        };
        double result = ClosestPair.closestPair(points);
        assertEquals(1.0, result, 1e-6);
    }
}
