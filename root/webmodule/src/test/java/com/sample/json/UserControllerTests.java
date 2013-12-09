package com.sample.json;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.sample.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTests extends AbstractContextControllerTests {

	private static String URI = "/user/{action}";

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		WebApplicationContext wac2 = this.wac;
		
		this.mockMvc = webAppContextSetup(wac2).alwaysExpect(status().isOk()).build();
	}


	

	
	@Test
	public void createUser() throws Exception {
		String input="{\"address\":{\"id\":null,\"state\":\"state\",\"country\":\"country\",\"line1\":\"line1\",\"line2\":"
				+"\"line2\",\"line3\":\"line3\",\"city\":\"city\",\"zipOrPin\":\"zipOrPin\"},\"id\":"
				+"null,\"contacts\":[{\"address\":{\"id\":null,\"state\":\"state\",\"country\":\"country\",\"line"
				+"1\":\"line1\",\"line2\":\"line2\",\"line3\":\"line3\",\"city\":\"city\",\"zipOrPin\":\"zipOrPin\"},"
				+"\"id\":null,\"title\":\"Mr\",\"gender\":\"M\",\"firstName\":\"Contactfirstname\",\"lastName\":\"C"
				+"ontactlastname\",\"emailId\":\"contactemail@amail.com\",\"phone\":\"123456778\"}],\"loginI"
				+"d\":\"loginId1\",\"title\":\"Mr\",\"gender\":\"M\",\"firstName\":\"FirstName1\",\"lastName\":\"Las"
				+"tName1\",\"emailId\":\"emailId@amail.com\",\"phone\":\"(011)2512-5189\",\"birthday\":980879"
				+"400000}";
		
		String output="{\"address\":{\"id\":1,\"state\":\"state\",\"country\":\"country\",\"line1\":\"line1\",\"line2\":\"line2\",\"line3\":\"line3\",\"city\":\"city\",\"zipOrPin\":\"zipOrPin\"},\"id\":1,\"loginId\":\"loginId1\",\"title\":\"Mr\",\"gender\":\"M\",\"firstName\":\"FirstName1\",\"lastName\":\"LastName1\",\"emailId\":\"emailId@amail.com\",\"phone\":\"(011)2512-5189\",\"birthday\":980879400000,\"contacts\":[{\"address\":{\"id\":2,\"state\":\"state\",\"country\":\"country\",\"line1\":\"line1\",\"line2\":\"line2\",\"line3\":\"line3\",\"city\":\"city\",\"zipOrPin\":\"zipOrPin\"},\"id\":1,\"title\":\"Mr\",\"gender\":\"M\",\"firstName\":\"Contactfirstname\",\"lastName\":\"Contactlastname\",\"emailId\":\"contactemail@amail.com\",\"phone\":\"123456778\"}]}";
		this.mockMvc.perform(
				
				
				post(URI, "create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(input.getBytes()))
				.andExpect(content().string(output));
	}

	

	
	
}
