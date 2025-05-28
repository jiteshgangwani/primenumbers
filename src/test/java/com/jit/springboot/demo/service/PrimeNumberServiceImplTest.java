package com.jit.springboot.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PrimeNumberServiceImplTest {

    @InjectMocks
    private PrimeNumberServiceImpl primeNumberService;

    // Helper to access private isPrime method
    private boolean invokeIsPrime(int number) throws Exception {
        Method method = PrimeNumberServiceImpl.class.getDeclaredMethod("isPrime", int.class);
        method.setAccessible(true);
        return (boolean) method.invoke(primeNumberService, number);
    }

    @Test
    void testIsPrime_input2_shouldReturnTrue() throws Exception {
        assertTrue(invokeIsPrime(2), "2 should be prime");
    }

    @Test
    void testIsPrime_input3_shouldReturnTrue() throws Exception {
        assertTrue(invokeIsPrime(3), "3 should be prime");
    }

    @Test
    void testIsPrime_input4_shouldReturnFalse() throws Exception {
        assertFalse(invokeIsPrime(4), "4 should not be prime");
    }

    @Test
    void testIsPrime_input17_shouldReturnTrue() throws Exception {
        assertTrue(invokeIsPrime(17), "17 should be prime");
    }

    @Test
    void testIsPrime_input18_shouldReturnFalse() throws Exception {
        assertFalse(invokeIsPrime(18), "18 should not be prime");
    }
    
    @Test
    void testIsPrime_input25_shouldReturnFalse() throws Exception {
        assertFalse(invokeIsPrime(25), "25 should not be prime");
    }

    @Test
    void testGetPrimeNumberList_input1_shouldReturnEmptyList() {
        List<Integer> result = primeNumberService.getPrimeNumberList(1);
        assertTrue(result.isEmpty(), "List should be empty for input 1");
    }

    @Test
    void testGetPrimeNumberList_input0_shouldReturnEmptyList() {
        List<Integer> result = primeNumberService.getPrimeNumberList(0);
        assertTrue(result.isEmpty(), "List should be empty for input 0");
    }
    
    @Test
    void testGetPrimeNumberList_inputNegative_shouldReturnEmptyList() {
        List<Integer> result = primeNumberService.getPrimeNumberList(-5);
        assertTrue(result.isEmpty(), "List should be empty for negative input");
    }

    @Test
    void testGetPrimeNumberList_input2_shouldReturnListOf2() {
        List<Integer> expected = Collections.singletonList(2);
        List<Integer> result = primeNumberService.getPrimeNumberList(2);
        assertEquals(expected, result, "List should contain [2] for input 2");
    }

    @Test
    void testGetPrimeNumberList_input10_shouldReturnListOf2_3_5_7() {
        List<Integer> expected = Arrays.asList(2, 3, 5, 7);
        List<Integer> result = primeNumberService.getPrimeNumberList(10);
        assertEquals(expected, result, "List should contain [2, 3, 5, 7] for input 10");
    }
}
