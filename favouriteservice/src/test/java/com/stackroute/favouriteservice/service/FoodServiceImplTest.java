package com.stackroute.favouriteservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.domain.Food;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;
import com.stackroute.favouriteservice.repository.FoodRepo;



public class FoodServiceImplTest {
	
	@Mock
	private FoodRepo foodRepo;
	
	
	private Food food;
	
	@InjectMocks
	private FoodServiceImpl foodServiceImpl;
	
	Optional<Food> options;
	
	@Before
	public void setupMock(){
		MockitoAnnotations.initMocks(this);
		
		food=new Food(1, "1234", "Abc", "user1");
		
		options=Optional.of(food);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(food);
		assertNotNull(foodRepo);
	}
	@Test
	public void testSaveFoodSuccess() throws FoodAlreadyExistsException{
		when(foodRepo.save(food)).thenReturn(food);
		boolean flag = foodServiceImpl.saveFood(food);
		assertTrue("Saving movie failed",flag);
		verify(foodRepo,times(1)).save(food);
		verify(foodRepo,times(1)).findById(food.getId());
		
	}
	
	@Test(expected=FoodAlreadyExistsException.class)
	public void testSaveMovieFailure() throws FoodAlreadyExistsException{
		when(foodRepo.findById(1)).thenReturn(options);
		when(foodRepo.save(food)).thenReturn(food);
		boolean flag = foodServiceImpl.saveFood(food);
		assertFalse("Saving movie failed",flag);
		verify(foodRepo,times(1)).findById(food.getId());
	}
	
	@Test
	public void testUpdateMovie() throws FoodNotFoundException{
		when(foodRepo.findById(1)).thenReturn(options);
		when(foodRepo.save(food)).thenReturn(food);
		food.setName("Mango");
		final Food food1 = foodServiceImpl.updateFoodName(food);
		assertEquals("updating movie failed", "Mango", food1.getName());
		verify(foodRepo, times(1)).save(food);
		verify(foodRepo, times(1)).findById(food.getId());
		
	}
	
	@Test
	public void testDeleteFoodById() throws FoodNotFoundException{
		when(foodRepo.findById(1)).thenReturn(options);
		doNothing().when(foodRepo).delete(food);
		boolean flag = foodServiceImpl.deleteFoodById(1);
		assertEquals("deleting movie failed",true,flag);
		verify(foodRepo,times(1)).delete(food);
		verify(foodRepo,times(1)).findById(food.getId());
	}
	
	@Test
	public void testGetMovieById() throws FoodNotFoundException{
		when(foodRepo.findById(1)).thenReturn(options);
		final Food food1 = foodServiceImpl.getFoodById(1);
		assertEquals("Getting Movie by This id failed",food1,food);
		verify(foodRepo, times(1)).findById(food.getId());
		
	}
	
	
	
	@Test
	public void testGetMyFoods() throws Exception{
		List<Food> foodList = new ArrayList<>();
		foodList.add(food);
		when(foodRepo.findByUserId("user1")).thenReturn(foodList);
		List<Food> foodList1 = foodServiceImpl.getMyFood("user1");
		assertEquals(foodList,foodList1);
		verify(foodRepo,times(1)).findByUserId("user1");
	}


}
