package com.stackroute.favouriteservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.domain.Food;
import com.stackroute.favouriteservice.service.FoodServiceImpl;




@RunWith(SpringRunner.class)
@WebMvcTest(FoodRepoController.class)
public class FoodControllerTest {
	@Autowired
	private transient MockMvc mvc;
	
	@MockBean
	private transient FoodServiceImpl service;
	

	
	private transient Food food;
	
	static List<Food> foods;
	String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTU1MzQxMjM3Mn0.N-MYEuUtwgwit7nec-K3jPnB67vfRhy_K0jDePlwEdM";

@InjectMocks
FoodRepoController foodController;
	@Before
	public void setUp() 
	{
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(foodController).build();
		foods = new ArrayList<>();
		food=new Food(1, "1234", "Abc", "user1");
		foods.add(food);
		food=new Food(2, "1111", "KBC", "user1");
		foods.add(food);
		
	}
	
	
	@Test
	public void testSaveNewMovie() throws Exception
	{
		
	
		
		when(service.saveFood(food)).thenReturn(true);
		mvc.perform(post("/api/v1/favouriteservice/food/")
			.header("authorization", "Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(food)))
				.andExpect(status().isCreated());
		verify(service, times(1)).saveFood(Mockito.any(Food.class));
		
	}
		

	
	@Test
	public void testDeleteMyfoodById() throws Exception
	{

		
		
		when(service.deleteFoodById(1)).thenReturn(true);
		mvc.perform(delete("/api/v1/favouriteservice/food/{id}",1))
				.andExpect(status().isOk());
		verify(service, times(1)).deleteFoodById(1);
		verifyNoMoreInteractions(service);
		
	}

	
	@Test
	public void testGetMyFood() throws Exception
	{
		
		
		
		System.out.println(token);
		when(service.getMyFood("user1")).thenReturn(foods);
		mvc.perform(get("/api/v1/favouriteservice/food/")
	.header("authorization", "Bearer " + token)
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonToString(food)))
		.andExpect(status().isOk()).andDo(print());
		verify(service, times(1)).getMyFood("user1");
		verifyNoMoreInteractions(service);
		
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
	

}
