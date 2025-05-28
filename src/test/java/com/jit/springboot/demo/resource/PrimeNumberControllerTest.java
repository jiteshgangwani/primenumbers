package com.jit.springboot.demo.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jit.springboot.demo.model.PrimeNumberObject;
import com.jit.springboot.demo.service.PrimeNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@WebMvcTest(PrimeNumberController.class)
class PrimeNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("defaultBean")
    private PrimeNumberService primeNumberService;

    @MockBean
    @Qualifier("soeBean")
    private PrimeNumberService primeNumberSoeService;

    @Autowired
    private ObjectMapper objectMapper;

    // Tests for getPrimeNumbers endpoint
    @Test
    void getPrimeNumbers_whenInitialIs1_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/rest/prime/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Input number must be greater than or equal to 2."));
    }

    @Test
    void getPrimeNumbers_whenInitialIs0_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/rest/prime/0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Input number must be greater than or equal to 2."));
    }
    
    @Test
    void getPrimeNumbers_whenInitialIsNegative_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/rest/prime/-5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Input number must be greater than or equal to 2."));
    }

    @Test
    void getPrimeNumbers_whenInitialIsValid_shouldReturnOkAndPrimeObject() throws Exception {
        int initials = 10;
        List<Integer> primes = Arrays.asList(2, 3, 5, 7);
        PrimeNumberObject expectedResponse = new PrimeNumberObject();
        expectedResponse.setInitial(initials);
        expectedResponse.setPrimes(primes);

        when(primeNumberService.getPrimeNumberList(initials)).thenReturn(primes);

        mockMvc.perform(get("/rest/prime/{initials}", initials))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.initial", is(initials)))
                .andExpect(jsonPath("$.primes[0]", is(2)))
                .andExpect(jsonPath("$.primes[1]", is(3)))
                .andExpect(jsonPath("$.primes[2]", is(5)))
                .andExpect(jsonPath("$.primes[3]", is(7)));

        verify(primeNumberService, times(1)).getPrimeNumberList(initials);
    }

    // Tests for getPrimeNumbersSoe endpoint
    @Test
    void getPrimeNumbersSoe_whenInitialIs1_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/rest/prime/1/soe"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Input number must be greater than or equal to 2."));
    }

    @Test
    void getPrimeNumbersSoe_whenInitialIsNegative5_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/rest/prime/-5/soe"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Input number must be greater than or equal to 2."));
    }

    @Test
    void getPrimeNumbersSoe_whenInitialIsValid_shouldReturnOkAndPrimeObject() throws Exception {
        int initials = 10;
        List<Integer> primes = Arrays.asList(2, 3, 5, 7);
        PrimeNumberObject expectedResponse = new PrimeNumberObject();
        expectedResponse.setInitial(initials);
        expectedResponse.setPrimes(primes);

        when(primeNumberSoeService.getPrimeNumberList(initials)).thenReturn(primes);

        mockMvc.perform(get("/rest/prime/{initials}/soe", initials))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.initial", is(initials)))
                .andExpect(jsonPath("$.primes[0]", is(2)))
                .andExpect(jsonPath("$.primes[1]", is(3)))
                .andExpect(jsonPath("$.primes[2]", is(5)))
                .andExpect(jsonPath("$.primes[3]", is(7)));

        verify(primeNumberSoeService, times(1)).getPrimeNumberList(initials);
    }
    
    @Test
    void getPrimeNumbers_whenInitialIs2_shouldReturnOkAndPrimeObject() throws Exception {
        int initials = 2;
        List<Integer> primes = Collections.singletonList(2);
        PrimeNumberObject expectedResponse = new PrimeNumberObject();
        expectedResponse.setInitial(initials);
        expectedResponse.setPrimes(primes);

        when(primeNumberService.getPrimeNumberList(initials)).thenReturn(primes);

        mockMvc.perform(get("/rest/prime/{initials}", initials))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.initial", is(initials)))
                .andExpect(jsonPath("$.primes[0]", is(2)));

        verify(primeNumberService, times(1)).getPrimeNumberList(initials);
    }

    @Test
    void getPrimeNumbersSoe_whenInitialIs2_shouldReturnOkAndPrimeObject() throws Exception {
        int initials = 2;
        List<Integer> primes = Collections.singletonList(2);
        PrimeNumberObject expectedResponse = new PrimeNumberObject();
        expectedResponse.setInitial(initials);
        expectedResponse.setPrimes(primes);

        when(primeNumberSoeService.getPrimeNumberList(initials)).thenReturn(primes);

        mockMvc.perform(get("/rest/prime/{initials}/soe", initials))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.initial", is(initials)))
                .andExpect(jsonPath("$.primes[0]", is(2)));

        verify(primeNumberSoeService, times(1)).getPrimeNumberList(initials);
    }
}
