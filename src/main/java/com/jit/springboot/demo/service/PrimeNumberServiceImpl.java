package com.jit.springboot.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Qualifier("defaultBean")
public class PrimeNumberServiceImpl implements  PrimeNumberService{

    private static final Logger logger = LoggerFactory.getLogger(PrimeNumberServiceImpl.class);


    @Cacheable("initials")
    public List<Integer> getPrimeNumberList(int initials) {
        List<Integer> primeNumbers = new LinkedList<>();
        if (initials >= 2) {
            primeNumbers.add(2);
        }
        for (int i = 3; i <= initials; i ++) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    private boolean isPrime(int number) {
        logger.info("checking for prime number for " + number);
        if (number%2==0) return false;
        for(int i=3;i*i<=number;i+=2) {
            if(number%i==0)
                return false;
        }
        return true;


    }

}
