package com.jit.springboot.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // For Mockito support if needed, though this test is direct instantiation
class PrimeNumberServiceImplJUnit5Test {

    @InjectMocks // InjectMocks will instantiate PrimeNumberServiceImpl
    private PrimeNumberServiceImpl primeNumberService;

    // Test cases for getPrimeNumberList, which indirectly tests isPrime
    @Test
    void getPrimeNumberList_input1_shouldReturnEmptyList() {
        List<Integer> result = primeNumberService.getPrimeNumberList(1);
        assertTrue(result.isEmpty(), "List should be empty for input 1");
    }

    @Test
    void getPrimeNumberList_input2_shouldReturnListOf2() {
        // This test implicitly verifies that isPrime(2) works correctly.
        List<Integer> expected = Collections.singletonList(2);
        List<Integer> result = primeNumberService.getPrimeNumberList(2);
        assertEquals(expected, result, "List should contain [2] for input 2");
    }
    
    @Test
    void getPrimeNumberList_input3_shouldReturnListOf2And3() {
        // This test implicitly verifies isPrime(3)
        List<Integer> expected = Arrays.asList(2, 3);
        List<Integer> result = primeNumberService.getPrimeNumberList(3);
        assertEquals(expected, result, "List should contain [2, 3] for input 3");
    }

    @Test
    void getPrimeNumberList_input4_shouldReturnListOf2And3() {
        // This test implicitly verifies that isPrime(4) returns false.
        List<Integer> expected = Arrays.asList(2, 3);
        List<Integer> result = primeNumberService.getPrimeNumberList(4);
        assertEquals(expected, result, "List should contain [2, 3] for input 4, as 4 is not prime");
    }

    @Test
    void getPrimeNumberList_input10_shouldReturnListOf2_3_5_7() {
        List<Integer> expected = Arrays.asList(2, 3, 5, 7);
        List<Integer> result = primeNumberService.getPrimeNumberList(10);
        assertEquals(expected, result, "List should contain [2, 3, 5, 7] for input 10");
    }

    @Test
    void getPrimeNumberList_inputNegative_shouldReturnEmptyList() {
        List<Integer> result = primeNumberService.getPrimeNumberList(-5);
        assertTrue(result.isEmpty(), "List should be empty for negative input");
    }
}
