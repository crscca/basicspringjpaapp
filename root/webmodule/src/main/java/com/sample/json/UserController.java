package com.sample.json;

import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samples.entities.Address;
import com.samples.entities.IAddressHolder;
import com.samples.entities.User;
import com.samples.service.UserService;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.rss.Channel;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;

	// StringHttpMessageConverter

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<User> create(@Valid @RequestBody User bean) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		// u= new User("loginId1", "Mr", 'M', "FirstName1", "LastName1", "emailId@amail.com", "(011)2512-5189", new Date(2001-1900, 1-1,31));
		//(u, "line1", "line2", "line3", "city", "state", "country", "zipOrPin");
		User u=userService.createuser(bean);
		
		return new ResponseEntity<User>(u, headers , HttpStatus.OK);
	}
	
	private static Address setAddress(IAddressHolder u, String line1, String line2, String line3, String city, String state, String country, String zipOrPin) {
		Address a= new Address(line1, line2, line3, city, state, country, zipOrPin);
		u.setAddress(a);
		return a;
	}

	
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}