package com.jit.springboot.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@Service
@Qualifier("soeBean")
public class PrimeNumberSoeServiceImpl  implements PrimeNumberService{


    private static final Logger logger = LoggerFactory.getLogger(PrimeNumberSoeServiceImpl.class);

    @Override
    public List<Integer> getPrimeNumberList(int initials) {

        logger.info(("checking prime number soe "+ initials));

        boolean prime[] = new boolean[initials + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= initials; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= initials; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= initials; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}
