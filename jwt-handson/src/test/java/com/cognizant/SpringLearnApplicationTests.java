package com.cognizant;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;

import com.cognizant.springlearn.SpringLearnApplication;
import com.cognizant.springlearn.controller.CountryController;
import com.cognizant.springlearn.model.Country;

@SpringBootTest(classes=SpringLearnApplication.class)
@AutoConfigureMockMvc
class SpringLearnApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CountryController countryController;

	@Test
	void contextLoads() {
		Assert.notNull(countryController,"Country object not null");
	}
	
	@Test
	public void testGetCountry() throws Exception{
		ResultActions actions=mockMvc.perform(get("/country"));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.code").exists());
		actions.andExpect(jsonPath("$.code").value("IN"));
	}

	@Test
	public void testGetCountryException() throws Exception {
		ResultActions actions=mockMvc.perform(get("/countries/ina"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Country not found"));
	}
	@Test
	public void testSaveOneLetterCodeCountry() throws Exception {
		String countryJson="{\"code\": \"B\",\"name\": \"Bhutan\"}";
		mockMvc.perform(post("/countries")
				.content(countryJson)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.timestamp", is(notNullValue())))
		.andExpect(jsonPath("$.status", is(400)))
		.andExpect(jsonPath("$.errors", hasItem("Country code should be 2 characters")));		
	}
	@Test
	public void testSaveNameNullCountry() throws Exception {
		String countryJson="{\"code\": \"BH\",\"nae\": \"Bhutan\"}";
		mockMvc.perform(post("/countries")
				.content(countryJson)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.timestamp", is(notNullValue())))
		.andExpect(jsonPath("$.status", is(400)))
		.andExpect(jsonPath("$.errors", hasItem("must not be null")));		
	}
	@Test
	public void testSaveCodeOneLetterNameNullCountry() throws Exception {
		String countryJson="{\"code\": \"B\",\"nae\": \"Bhutan\"}";
		mockMvc.perform(post("/countries")
				.content(countryJson)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.timestamp", is(notNullValue())))
		.andExpect(jsonPath("$.status", is(400)))
		.andExpect(jsonPath("$.errors", hasItem("must not be null")))
		.andExpect(jsonPath("$.errors", hasItem("Country code should be 2 characters")));		
	}
	
	@Test
	public void testDeleteCountry() throws Exception{
		ResultActions actions=mockMvc.perform(delete("/countries/in"));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.code").exists());
		actions.andExpect(jsonPath("$.code").value("IN"));
		actions.andExpect(jsonPath("$.name").exists());
		actions.andExpect(jsonPath("$.name").value("India"));
	}
	
	@Test
	public void testDeleteCountryException() throws Exception {
		ResultActions actions=mockMvc.perform(delete("/countries/ina"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Country not found"));
	}
	@Test
	public void testDeleteEmployee() throws Exception{
		ResultActions actions=mockMvc.perform(delete("/employees/101"));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.id").exists());
		actions.andExpect(jsonPath("$.id").value("101"));
	}
	
	@Test
	public void testDeleteEmployeeException() throws Exception {
		ResultActions actions=mockMvc.perform(delete("/employees/10"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Employee not found"));
	}
}
