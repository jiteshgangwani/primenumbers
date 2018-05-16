package com.jit.springboot.demo.resource;

import com.jit.springboot.demo.model.PrimeNumberObject;
import com.jit.springboot.demo.service.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@RequestMapping(path = "/rest")
public class PrimeNumberController {

    @Autowired
    @Qualifier("defaultBean")
    PrimeNumberService primeNumberService;

    @Autowired
    @Qualifier("soeBean")
    PrimeNumberService primeNumberSoeService;


    @GetMapping("/prime/{initials}")
    public PrimeNumberObject getPrimeNumbers(@PathVariable("initials") @Max(Integer.MAX_VALUE) int initials)
    {
        PrimeNumberObject primeNumberObject = new PrimeNumberObject();
        primeNumberObject.setInitial(initials);
        primeNumberObject.setPrimes( primeNumberService.getPrimeNumberList(initials));
        return  primeNumberObject;
    }

    @GetMapping("/prime/{initials}/soe")
    public PrimeNumberObject getPrimeNumbersSoe(@PathVariable("initials") @Max(Integer.MAX_VALUE) int initials)
    {
        PrimeNumberObject primeNumberObject = new PrimeNumberObject();
        primeNumberObject.setInitial(initials);
        primeNumberObject.setPrimes( primeNumberSoeService.getPrimeNumberList(initials));
        return  primeNumberObject;
    }


}
