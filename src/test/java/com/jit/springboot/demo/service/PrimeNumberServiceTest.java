package com.jit.springboot.demo.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PrimeNumberServiceTest {

//Ensure giving different initials as same numbers with use cache
//Todo: find a way to disable caching while testing
//Todo: write a test to test caching

    @Autowired
    @Qualifier("defaultBean")
    PrimeNumberService primeNumberService;

    @Test
    public void testValidCountPrimeNumber(){
    List<Integer> primeNumbers =  primeNumberService.getPrimeNumberList(6);
        Assert.assertEquals(primeNumbers.size(),3);
    }


    @Test
    public void testInvalidCountValidPrimeNumber(){
        List<Integer> primeNumbers =  primeNumberService.getPrimeNumberList(7);
        Assert.assertNotEquals(primeNumbers.size(),2);
    }



    @Test
    public void testValidPrimeNumbers(){

        List<Integer> validPrimeNumbers = Arrays.asList(2,3,5,7);
        List<Integer> primeNumbers =  primeNumberService.getPrimeNumberList(8);
        Assert.assertEquals(primeNumbers.size(),4);
        Assert.assertEquals(primeNumbers,validPrimeNumbers);
    }



    @Test
    public void testInvalidPrimeNumbers(){

        List<Integer> validPrimeNumbers = Arrays.asList(2,4,5,7);
        List<Integer> primeNumbers =  primeNumberService.getPrimeNumberList(9);
        Assert.assertEquals(primeNumbers.size(),4);
        Assert.assertNotEquals(primeNumbers,validPrimeNumbers);
    }

}
