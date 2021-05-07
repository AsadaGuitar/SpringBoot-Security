package com.example.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

	@GetMapping(path="/loginForm")
	public String viewLoginForm() {
		return "Login";
	}
	
	@GetMapping(path="/Success")
	public String vewSuccess() {
		System.out.println("SUCCESS");
		return "Success";
	}
}
