package com.jit.springboot.demo.resource;

import com.jit.springboot.demo.model.PrimeNumberObject;
import com.jit.springboot.demo.service.PrimeNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.time.Duration;
import java.time.Instant;


@RestController
@RequestMapping(path = "/rest")
public class PrimeNumberController {

    @Autowired
    @Qualifier("defaultBean")
    PrimeNumberService primeNumberService;

    @Autowired
    @Qualifier("soeBean")
    PrimeNumberService primeNumberSoeService;

    private static final Logger logger = LoggerFactory.getLogger(PrimeNumberController.class);


    @GetMapping("/prime/{initials}")
    public PrimeNumberObject getPrimeNumbers(@PathVariable("initials") @Max(Integer.MAX_VALUE) int initials) {
        PrimeNumberObject primeNumberObject = new PrimeNumberObject();
        primeNumberObject.setInitial(initials);
        Instant start = Instant.now();
        primeNumberObject.setPrimes(primeNumberService.getPrimeNumberList(initials));
        Instant end = Instant.now();
        logTiming(start, end, "Default", initials);
        return primeNumberObject;
    }

    @GetMapping("/prime/{initials}/soe")
    public PrimeNumberObject getPrimeNumbersSoe(@PathVariable("initials") @Max(Integer.MAX_VALUE) int initials) {
        PrimeNumberObject primeNumberObject = new PrimeNumberObject();
        primeNumberObject.setInitial(initials);
        Instant start = Instant.now();
        primeNumberObject.setPrimes(primeNumberSoeService.getPrimeNumberList(initials));
        Instant end = Instant.now();
        logTiming(start, end, "SOE", initials);
        return primeNumberObject;
    }

    private void logTiming(Instant start, Instant end, String method, int initial) {
        logger.info("Time take for calculating prime number {} method for number {} in nano seconds is  {}", method, initial, Duration.between(start, end).toNanos());
    }

}
