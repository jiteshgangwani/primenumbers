package com.jit.springboot.demo.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jit.springboot.demo.model.PrimeNumberObject;
import com.jit.springboot.demo.service.PrimeNumberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class) // Necessary for Spring features in JUnit 5
@WebMvcTest(PrimeNumberController.class)
class PrimeNumberControllerJUnit5Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("defaultBean") // Matches the qualifier in PrimeNumberController
    private PrimeNumberService primeNumberServiceDefault;

    @MockBean
    @Qualifier("soeBean") // Matches the qualifier in PrimeNumberController
    private PrimeNumberService primeNumberServiceSoe;

    @Autowired
    private ObjectMapper objectMapper;

    // Tests for getPrimeNumbers endpoint (/rest/prime/{initials})
    @Test
    void getPrimeNumbers_whenInitialIs1_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/rest/prime/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Input number must be greater than or equal to 2."));
    }

    @Test
    void getPrimeNumbers_whenInitialIsValid_shouldReturnOkAndCallService() throws Exception {
        int initials = 10;
        List<Integer> primes = Arrays.asList(2, 3, 5, 7); // Example prime list
        
        // Mock the service call for "defaultBean"
        when(primeNumberServiceDefault.getPrimeNumberList(initials)).thenReturn(primes);

        mockMvc.perform(get("/rest/prime/{initials}", initials))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.initial", is(initials)))
                .andExpect(jsonPath("$.primes", is(primes)));

        verify(primeNumberServiceDefault, times(1)).getPrimeNumberList(initials);
        verifyNoInteractions(primeNumberServiceSoe); // Ensure SOE service is not called
    }

    // Tests for getPrimeNumbersSoe endpoint (/rest/prime/{initials}/soe)
    @Test
    void getPrimeNumbersSoe_whenInitialIs1_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/rest/prime/1/soe"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Input number must be greater than or equal to 2."));
    }

    @Test
    void getPrimeNumbersSoe_whenInitialIsValid_shouldReturnOkAndCallSoeService() throws Exception {
        int initials = 10;
        List<Integer> primes = Arrays.asList(2, 3, 5, 7); // Example prime list

        // Mock the service call for "soeBean"
        when(primeNumberServiceSoe.getPrimeNumberList(initials)).thenReturn(primes);

        mockMvc.perform(get("/rest/prime/{initials}/soe", initials))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.initial", is(initials)))
                .andExpect(jsonPath("$.primes", is(primes)));

        verify(primeNumberServiceSoe, times(1)).getPrimeNumberList(initials);
        verifyNoInteractions(primeNumberServiceDefault); // Ensure default service is not called
    }
}
