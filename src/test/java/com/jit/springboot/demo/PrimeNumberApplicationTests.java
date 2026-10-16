package com.jit.springboot.demo;

import org.junit.jupiter.api.Test; // JUnit 5 Test annotation
import org.junit.jupiter.api.extension.ExtendWith; // JUnit 5 ExtendWith annotation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext; // Import DirtiesContext
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension; // JUnit 5 SpringExtension
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // Replaced RunWith(SpringRunner.class)
@SpringBootTest(classes = PrimeNumberApplication.class) // Explicitly set context
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS) // Added DirtiesContext
public class PrimeNumberApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test // JUnit 5 Test annotation
	public void  testPrimeNumberRequestResponse() throws Exception {
		this.mockMvc.perform(get("/rest/prime/20"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"initial\":20,\"primes\":[2,3,5,7,11,13,17,19]}")));
	}


	@Test // JUnit 5 Test annotation
	public void  testPrimeNumberSoeRequestResponse() throws Exception {
		this.mockMvc.perform(get("/rest/prime/20/soe"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"initial\":20,\"primes\":[2,3,5,7,11,13,17,19]}")));

	}


}
