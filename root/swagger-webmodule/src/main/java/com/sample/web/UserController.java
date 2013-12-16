package com.sample.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sample.status.OperationResult;
import com.sample.status.OperationResult.Status;
import com.samples.beans.PageReturnData;
import com.samples.entities.User;
import com.samples.entities.UserSimplified;
import com.samples.service.IGenericService;
import com.samples.service.UserService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;


@Controller
@RequestMapping(value="/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	private IGenericService<UserSimplified, Long> userSimplifiedService;
	public void delete(Long id) {
		userSimplifiedService.delete(id);
	}
	
	 @ApiOperation(value = "Creates a User")
	    @RequestMapping(value="/create4",method = RequestMethod.POST)
	    public  @ResponseBody UserSimplified create4(@ApiParam(required = true, name = "user", value = "The user object that needs to be created")@RequestBody UserSimplified user){
		 
		
		UserSimplified ret = userSimplifiedService.create(user);
		
		return ret;


	    }
	 
	 @ExceptionHandler
		public @ResponseBody OperationResult handle(Throwable e) {
		 OperationResult ret= new OperationResult(Status.ERROR, e.getMessage());
			return ret;
		}
	 @ApiOperation(value = "Creates dummy Users")
	@RequestMapping(value="{prefix}/{noOfUsers}", method=RequestMethod.GET)
	public @ResponseBody List<UserSimplified> createDummyUsers (
			@ApiParam(required = true, name = "prefix", value = "prefix") @PathVariable String prefix,
			@ApiParam(required = true, name = "noOfUsers", value = "noOfUsers") @PathVariable Integer noOfUsers) 
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
	 
	 
	 @ApiOperation(value = "Returns data in a page")
		@RequestMapping(value="getPage/{pageSize}/{pageNo}", method=RequestMethod.GET)
		public @ResponseBody PageReturnData<UserSimplified> getPage (
				@ApiParam(required = true, name = "pageSize", value = "pageSize") @PathVariable Integer pageSize,
				@ApiParam(required = true, name = "pageNo", value = "pageNo") @PathVariable Integer pageNo) 
		{
		 ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		    HttpServletRequest req = sra.getRequest();
		   String url= req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/"+req.getContextPath()+"/getPage/"+pageSize+"/";
		 PageReturnData<UserSimplified> page = userSimplifiedService.getPage(pageSize, pageNo);
		 page.setPrefixUrl(url);
			return page;
		}
		 
	 
	
	
	

	/*public void updateField(Long id, boolean lock, String fieldName,
			Object value) {
		userSimplifiedService.updateField(id, lock, fieldName, value);
	}*/

	public UserSimplified findById(Long id) {
		return userSimplifiedService.findById(id, false);
	}

	public List<UserSimplified> findAll() {
		return userSimplifiedService.findAll();
	}
	private UserService userService;
/*
	@ApiOperation(value = "Creates a User")
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody OperationResult<User> create(@ApiParam(required = true, name = "user", value = "The user object that needs to be created") @RequestBody User user) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		User u=userService.createuser(user);
		
		
		//return new ResponseEntity<User>(u, headers , HttpStatus.OK);
		OperationResult<User> ret= new OperationResult<User>(Status.SUCCESS, "msg");
		ret.setData(u);
		return ret;
	}
	
	

	

	
	
	 
	 
	 @ApiOperation(value = "Creates a User")
	    @RequestMapping(value="/create2",method = RequestMethod.POST)
	    public  @ResponseBody OperationResult<UserSimplified> create2(@ApiParam(required = true, name = "user", value = "The user object that needs to be created")@RequestBody UserSimplified user){
		 OperationResult<UserSimplified> ret= new OperationResult<UserSimplified>();
		 try
		 {
		user = userSimplifiedService.create(user);
		
		ret.setData(user);
		ret.setStatus(Status.SUCCESS);
		ret.setMessage("created user");
		 }
		 catch(Throwable e)
		 {
			 ret.setStatus(Status.ERROR);
			 ret.setMessage(e.getMessage());
		 }
		return ret;


	    }*/
	 
	
	 
	 
	/* @ApiOperation(value = "Creates a User")
	    @RequestMapping(value="/create3",method = RequestMethod.POST)
	    public  ResponseEntity<OperationResult<UserSimplified>>  create3(@ApiParam(required = true, name = "user", value = "The user object that needs to be created")@RequestBody UserSimplified user){
		 OperationResult<UserSimplified> ret= new OperationResult<UserSimplified>();
		 try
		 {
		user = userSimplifiedService.create(user);
		
		ret.setData(user);
		ret.setStatus(Status.SUCCESS);
		ret.setMessage("created user");
		 }
		 catch(Throwable e)
		 {
			 ret.setStatus(Status.ERROR);
			 ret.setMessage(e.getMessage());
		 }
		 HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_PLAIN);
			return new ResponseEntity<OperationResult<UserSimplified>>(ret, headers , HttpStatus.OK);


	    }*/
	 
		@Resource(name="userService")
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		@Resource(name="userSimplifiedService" )
		public void setUserSimplifiedService(
				IGenericService<UserSimplified, Long> userSimplifiedService) {
			this.userSimplifiedService = userSimplifiedService;
		}
	 

	
}