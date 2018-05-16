package com.jit.springboot.demo.integration;

import com.jit.springboot.demo.PrimeNumberApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PrimeNumberApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class PrimeNumberIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testIntegrationPrimeNumber() throws Exception {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:"+port+"/rest/prime/20",
                HttpMethod.GET, entity, String.class);

        String expected = "{\"initial\":20,\"primes\":[2,3,5,7,11,13,17,19]}";
        Assert.assertEquals(expected,response.getBody());

    }

    @Test
    public void testIntegrationPrimeNumberSoeMethod() throws Exception {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:"+port+"/rest/prime/20/soe",
                HttpMethod.GET, entity, String.class);

        String expected = "{\"initial\":20,\"primes\":[2,3,5,7,11,13,17,19]}";
        Assert.assertEquals(expected,response.getBody());

    }


}
