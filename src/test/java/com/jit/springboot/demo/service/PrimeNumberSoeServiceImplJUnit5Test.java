package com.jit.springboot.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PrimeNumberSoeServiceImplJUnit5Test {

    @InjectMocks
    private PrimeNumberSoeServiceImpl primeNumberSoeService;

    @Test
    void getPrimeNumberList_input1_shouldReturnEmptyList() {
        List<Integer> result = primeNumberSoeService.getPrimeNumberList(1);
        assertTrue(result.isEmpty(), "List should be empty for input 1");
    }

    @Test
    void getPrimeNumberList_input0_shouldReturnEmptyList() {
        List<Integer> result = primeNumberSoeService.getPrimeNumberList(0);
        assertTrue(result.isEmpty(), "List should be empty for input 0");
    }
    
    @Test
    void getPrimeNumberList_inputNegative_shouldReturnEmptyList() {
        List<Integer> result = primeNumberSoeService.getPrimeNumberList(-10);
        assertTrue(result.isEmpty(), "List should be empty for negative input");
    }

    @Test
    void getPrimeNumberList_input2_shouldReturnListOf2() {
        List<Integer> expected = Collections.singletonList(2);
        List<Integer> result = primeNumberSoeService.getPrimeNumberList(2);
        assertEquals(expected, result, "List should contain [2] for input 2");
    }

    @Test
    void getPrimeNumberList_input10_shouldReturnListOf2_3_5_7() {
        List<Integer> expected = Arrays.asList(2, 3, 5, 7);
        List<Integer> result = primeNumberSoeService.getPrimeNumberList(10);
        assertEquals(expected, result, "List should contain [2, 3, 5, 7] for input 10");
    }

    @Test
    void getPrimeNumberList_input20_shouldReturnListOfPrimesUpTo20() {
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);
        List<Integer> result = primeNumberSoeService.getPrimeNumberList(20);
        assertEquals(expected, result, "List should contain primes up to 20 for input 20");
    }
}
