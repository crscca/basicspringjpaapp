package com.sample.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.samples.beans.PageReturnData;
import com.samples.entities.Address;
import com.samples.entities.IAddressHolder;
import com.samples.entities.User;
import com.samples.entities.UserSimplified;
import com.samples.service.IGenericService;
import com.samples.service.UserService;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.rss.Channel;
import com.samples.entities.UserSimplified;
import com.samples.service.IGenericService;
import com.samples.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	private IGenericService<UserSimplified, Long> userSimplifiedService;

	

	
		@RequestMapping(value="{prefix}/{noOfUsers}", method=RequestMethod.GET)
		public @ResponseBody List<UserSimplified> createDummyUsers (
				 @PathVariable String prefix,
				 @PathVariable Integer noOfUsers) 
		{
			System.out.println("got prefix="+prefix+",noOfUsers="+noOfUsers);
			List<UserSimplified> users= new ArrayList<UserSimplified>();
				for (int i = 0; i < noOfUsers; i++) 
				{
					UserSimplified user= new UserSimplified("loginId"+prefix+i,"password", "Mr",  "FirstName1", "LastName1", "emailId@amail.com", "(011)2512-5189", new Date(2001-1900, 1-1,31));
					try
					{
					UserSimplified ret = userSimplifiedService.create(user);
					users.add(ret);
					}
					catch(Throwable e)
					{
						System.err.println(e.getMessage());
					}
				}
				return users;
			
		}
		 
		 
		
			@RequestMapping(value="getPage/{pageSize}/{pageNo}", method=RequestMethod.GET)
			public @ResponseBody PageReturnData<UserSimplified> getPage (
					 @PathVariable Integer pageSize,
					 @PathVariable Integer pageNo) 
			{
			 ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			    HttpServletRequest req = sra.getRequest();
			   String url= req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/"+req.getContextPath()+"/getPage/"+pageSize+"/";
			 PageReturnData<UserSimplified> page = userSimplifiedService.getPage(pageSize, pageNo);
			 page.setPrefixUrl(url);
				return page;
			}
			 
		 
		

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<User> create(@Valid @RequestBody User bean) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		// u= new User("loginId1", "Mr", 'M', "FirstName1", "LastName1", "emailId@amail.com", "(011)2512-5189", new Date(2001-1900, 1-1,31));
		//(u, "line1", "line2", "line3", "city", "state", "country", "zipOrPin");
		User u=userService.createuser(bean);
		
		
		return new ResponseEntity<User>(u, headers , HttpStatus.OK);
	}
	
	

	
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	// StringHttpMessageConverter
	
		 public void setUserSimplifiedService(
				IGenericService<UserSimplified, Long> userSimplifiedService) {
			this.userSimplifiedService = userSimplifiedService;
		}

	
}