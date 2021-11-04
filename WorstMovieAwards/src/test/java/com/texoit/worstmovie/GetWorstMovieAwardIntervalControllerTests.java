package com.texoit.worstmovie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.texoit.worstmovie.model.AwardInterval;

@SpringBootTest
@AutoConfigureMockMvc
class GetWorstMovieAwardIntervalControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getWorstMovieIntervalTest() throws Exception {
		ResultActions mockCallActions = this.mockMvc.perform(get("/worstMovie/getIntervals")).andDo(print()).andExpect(status().isOk());
		String mockResponse = mockCallActions.andReturn().getResponse().getContentAsString();
		
		String expectedResponse = "{\n"
				+ "    \"min\": [\n"
				+ "        {\n"
				+ "            \"producer\": \"Joel Silver\",\n"
				+ "            \"interval\": 1,\n"
				+ "            \"previousWin\": 1990,\n"
				+ "            \"followingWin\": 1991\n"
				+ "        }\n"
				+ "    ],\n"
				+ "    \"max\": [\n"
				+ "        {\n"
				+ "            \"producer\": \"Matthew Vaughn\",\n"
				+ "            \"interval\": 13,\n"
				+ "            \"previousWin\": 2002,\n"
				+ "            \"followingWin\": 2015\n"
				+ "        }\n"
				+ "    ]\n"
				+ "}";
		
		ObjectMapper objMapper = new ObjectMapper();
		Map<String, Set<AwardInterval>> responseMap 
		  = objMapper.readValue(mockResponse, new TypeReference<Map<String, Set<AwardInterval>>>(){});
		Map<String, Set<AwardInterval>> expectedMap
		  = objMapper.readValue(expectedResponse, new TypeReference<Map<String, Set<AwardInterval>>>(){});
		assertThat(responseMap.equals(expectedMap));
	}

}
