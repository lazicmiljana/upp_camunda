package root.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import root.demo.services.UserDetailsService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserDetailsService userDetailsService;
	
	@GetMapping(path = "/get/reviewer", produces = "application/json")
	public String getReviewers() {
		
		System.out.println(userDetailsService.getAllReviewers());
		
		return "b;a";
	}

}
