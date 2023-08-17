package com.example.ppaexec;

import com.example.ppaexec.ddo.NumberSumObj;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	@DirtiesContext
	void numberOutOfRangeALess() throws Exception {
		Integer a = -1;
		Integer b = 1;

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + a + "/" + b)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

	@Test
	@DirtiesContext
	void numberOutOfRangeBLess() throws Exception {
		Integer a = 1;
		Integer b = -1;

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + a + "/" + b)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

	@Test
	@DirtiesContext
	void numberOutOfRangeABigger() throws Exception {
		Integer a = 101;
		Integer b = 1;

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + a + "/" + b)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

	@Test
	@DirtiesContext
	void numberOutOfRangeBBigger() throws Exception {
		Integer a = 1;
		Integer b = 101;

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + a + "/" + b)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

	@Test
	@DirtiesContext
	void simpleGetSumOfNumberSumTest() throws Exception {
		Integer a = 1;
		Integer b = 2;

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + a + "/" + b)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.sum").value(a + b));

	}

	@Test
	@DirtiesContext
	void simpleGetSumOfNumbersTest() throws Exception {
		NumberSumObj numberSumObj = new NumberSumObj(1, 2);
		ObjectMapper mapper = new ObjectMapper();

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + numberSumObj.getA() + "/" + numberSumObj.getB())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(numberSumObj)));

	}

	@Test
	@DirtiesContext
	void simpleGetAllNumbersTest() throws Exception {
		Integer a = 1;
		Integer b = 2;
		Integer sort = 1;

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + a + "/" + b)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());


		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/all/" + sort)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.hasSize(1)));
	}

	@Test
	@DirtiesContext
	void getAllNumbersAscendingAndDescendingTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + 1 + "/" + 2)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + 5 + "/" + 6)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + 3 + "/" + 4)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());


		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/all/" + 1)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.hasSize(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].sum").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].sum").value(7))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[2].sum").value(11));


		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/all/" + -1)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.hasSize(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].sum").value(11))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].sum").value(7))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[2].sum").value(3));
	}

	@Test
	@DirtiesContext
	void getAllNumbersBySelectedNumber() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + 1 + "/" + 2)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + 5 + "/" + 6)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/getSumOfNumbers/" + 3 + "/" + 4)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());


		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/all/" + 1 + "/" + 3)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].sum").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].sum").value(7));


		this.mockMvc.perform(MockMvcRequestBuilders.get("/number/all/" + -1 + "/" + 3)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].sum").value(7))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].sum").value(3));
	}

}
