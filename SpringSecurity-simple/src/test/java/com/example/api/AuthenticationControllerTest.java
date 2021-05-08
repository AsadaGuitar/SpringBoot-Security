package com.example.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @Componentなどはスキャンされない。
 * MockMvc を自動構成する。
 *
 */
@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testContoller() throws Exception {
		//whenb
		System.out.println("test start");
		mockMvc.perform(get("/loginForm"))
			.andExpect(status().isOk())
			.andExpect(view().name("Login"));
	}
}
