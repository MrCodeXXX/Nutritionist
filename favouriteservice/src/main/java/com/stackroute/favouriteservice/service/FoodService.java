package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.domain.Food;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;


public interface FoodService {

		boolean saveFood (Food food) throws FoodAlreadyExistsException;
		
		boolean deleteFoodById (int id) throws FoodNotFoundException;
		
		Food updateFoodName(Food food) throws FoodNotFoundException;
		
		Food getFoodById (int id) throws FoodNotFoundException;
		
		List <Food> getMyFood (String userId);
}
