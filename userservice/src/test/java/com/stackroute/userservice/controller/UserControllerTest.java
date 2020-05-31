package com.stackroute.userservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.SecurityTokenGenerator;
import com.stackroute.userservice.service.UserService;




@RunWith(SpringRunner.class)
@WebMvcTest(UserRepoController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private SecurityTokenGenerator securityTokenGenerator;
	
	private User user;
	
	@InjectMocks
	UserRepoController userController;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		user = new User("user1","Thomas","Medhi","123456",new Date());
	}
	
	
	@Test
	public void testRegisterUser() throws Exception
	{
		user = new User("user1","Thomas","Medhi","123456",new Date());
		when(userService.saveUser(user)).thenReturn(true);
		
		mockMVC.perform(post("/api/v1/userservice/register")
				
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
				.andExpect(status().isCreated());
		verify(userService, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
		
	}
	
	@Test
	public void TestLogInUser() throws Exception
	{
		String userId = "user1";
		String password = "123456";
		user = new User("user1","Thomas","Medhi","123456",new Date());
		when(userService.saveUser(user)).thenReturn(true);
		when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
	mockMVC.perform(post("/api/v1/userservice/login").accept(MediaType.APPLICATION_JSON)
				
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
				.andExpect(status().isOk());
	verify(userService, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	verifyNoMoreInteractions(userService);
	
				
	}
	
	
	private static String jsonToString(final Object obj) throws JsonProcessingException
	{
		String result;
		try 
		{
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
			
		}catch(JsonProcessingException e) 
		{
			result = "Json Processing Error";
		}
		return result;
	}
	
	public UserControllerTest() {
		// TODO Auto-generated constructor stub
	}

}
