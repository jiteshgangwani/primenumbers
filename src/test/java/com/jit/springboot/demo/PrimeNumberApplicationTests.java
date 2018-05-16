package com.jit.springboot.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class PrimeNumberApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void  testPrimeNumberRequestResponse() throws Exception {
		this.mockMvc.perform(get("/rest/prime/20"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"initial\":20,\"primes\":[2,3,5,7,11,13,17,19]}")));
	}


	@Test
	public void  testPrimeNumberSoeRequestResponse() throws Exception {
		this.mockMvc.perform(get("/rest/prime/20/soe"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"initial\":20,\"primes\":[2,3,5,7,11,13,17,19]}")));

	}


}
