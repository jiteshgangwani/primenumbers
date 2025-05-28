package com.jit.springboot.demo.service;


import org.junit.jupiter.api.Assertions; // JUnit 5 Assertions
import org.junit.jupiter.api.Test; // JUnit 5 Test
import org.junit.jupiter.api.extension.ExtendWith; // JUnit 5 ExtendWith
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension; // JUnit 5 SpringExtension

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class) // Replaced RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.jit.springboot.demo.PrimeNumberApplication.class) // Explicitly set context
public class PrimeNumberServiceSoeTest {


    @Autowired
    @Qualifier("soeBean")
    PrimeNumberService primeNumberService;

    @Test // JUnit 5 Test
    public void testValidCountPrimeNumber(){
    List<Integer> primeNumbers =  primeNumberService.getPrimeNumberList(6);
        Assertions.assertEquals(primeNumbers.size(),3); // Replaced Assert.assertEquals
    }


    @Test // JUnit 5 Test
    public void testInvalidCountValidPrimeNumber(){
        List<Integer> primeNumbers =  primeNumberService.getPrimeNumberList(7);
        Assertions.assertNotEquals(primeNumbers.size(),2); // Replaced Assert.assertNotEquals
    }



    @Test // JUnit 5 Test
    public void testValidPrimeNumbers(){

        List<Integer> validPrimeNumbers = Arrays.asList(2,3,5,7);
        List<Integer> primeNumbers =  primeNumberService.getPrimeNumberList(8);
        Assertions.assertEquals(primeNumbers.size(),4); // Replaced Assert.assertEquals
        Assertions.assertEquals(primeNumbers,validPrimeNumbers); // Replaced Assert.assertEquals
    }



    @Test // JUnit 5 Test
    public void testInvalidPrimeNumbers(){

        List<Integer> validPrimeNumbers = Arrays.asList(2,4,5,7);
        List<Integer> primeNumbers =  primeNumberService.getPrimeNumberList(9);
        Assertions.assertEquals(primeNumbers.size(),4); // Replaced Assert.assertEquals
        Assertions.assertNotEquals(primeNumbers,validPrimeNumbers); // Replaced Assert.assertNotEquals
    }

}
