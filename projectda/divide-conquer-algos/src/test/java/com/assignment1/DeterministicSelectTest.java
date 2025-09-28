package com.assignment1;

import com.assignment1.algorithms.DeterministicSelect;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeterministicSelectTest {
    @Test
    void testSelectSmallArray() {
        int[] arr = {7, 2, 9, 1, 5};
        int k = 2;
        int result = DeterministicSelect.select(arr.clone(), k);
        Arrays.sort(arr);
        assertEquals(arr[k], result);
    }

    @Test
    void testRandomArrays() {
        Random rand = new Random();
        for (int t = 0; t < 100; t++) {
            int[] arr = rand.ints(20, 0, 100).toArray();
            int k = rand.nextInt(arr.length);
            int result = DeterministicSelect.select(arr.clone(), k);
            Arrays.sort(arr);
            assertEquals(arr[k], result);
        }
    }
}
