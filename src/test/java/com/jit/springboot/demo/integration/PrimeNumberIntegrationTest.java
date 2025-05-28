package com.jit.springboot.demo.integration;

import com.jit.springboot.demo.PrimeNumberApplication;
import org.junit.jupiter.api.Assertions; // JUnit 5 Assertions
import org.junit.jupiter.api.Test; // JUnit 5 Test
import org.junit.jupiter.api.extension.ExtendWith; // JUnit 5 ExtendWith
import org.springframework.beans.factory.annotation.Autowired; // Added Autowired import
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension; // JUnit 5 SpringExtension

// Removed unused MockMvc imports as this is a TestRestTemplate based test
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(SpringExtension.class) // Replaced RunWith(SpringRunner.class)
@SpringBootTest(classes = PrimeNumberApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeNumberIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired // Inject TestRestTemplate
    private TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Test // JUnit 5 Test
    public void testIntegrationPrimeNumber() throws Exception {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:"+port+"/rest/prime/20",
                HttpMethod.GET, entity, String.class);

        String expected = "{\"initial\":20,\"primes\":[2,3,5,7,11,13,17,19]}";
        Assertions.assertEquals(expected,response.getBody()); // Replaced Assert.assertEquals

    }

    @Test // JUnit 5 Test
    public void testIntegrationPrimeNumberSoeMethod() throws Exception {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:"+port+"/rest/prime/20/soe",
                HttpMethod.GET, entity, String.class);

        String expected = "{\"initial\":20,\"primes\":[2,3,5,7,11,13,17,19]}";
        Assertions.assertEquals(expected,response.getBody()); // Replaced Assert.assertEquals

    }


}
