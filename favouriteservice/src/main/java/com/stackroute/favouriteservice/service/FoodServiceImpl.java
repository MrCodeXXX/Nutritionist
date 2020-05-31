package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.stackroute.favouriteservice.domain.Food;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;
import com.stackroute.favouriteservice.repository.FoodRepo;





@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	public final transient FoodRepo foodRepo;
	

	public FoodServiceImpl(FoodRepo foodRepo) {
		super();
		this.foodRepo = foodRepo;
	}


	public boolean saveFood(Food food) throws FoodAlreadyExistsException {
		
		final Optional<Food> object=foodRepo.findById(food.getId());
		if(object.isPresent()) 
		{
			throw new FoodAlreadyExistsException("Could not save food, food already Exists");
		}
		foodRepo.save(food);
		return true;
	}

	
	public boolean deleteFoodById(int id) throws FoodNotFoundException {
		
		final Food food = foodRepo.findById(id).orElse(null);
		if(food == null) 
		{
			throw new FoodNotFoundException("Could not delete food, food does not exist");
		}
		foodRepo.delete(food);
		return true;
	}


	public Food getFoodById(int id) throws FoodNotFoundException {
		final Food food = foodRepo.findById(id).orElse(null);
		if(food == null) 
		{
			throw new FoodNotFoundException("Could not find food");
		}
		return food;
	}


	public List<Food> getMyFood(String userId) {
		
		return foodRepo.findByUserId(userId);
	}


	@Override
	public Food updateFoodName(Food updatedFood) throws FoodNotFoundException {
		final Food food = foodRepo.findById(updatedFood.getId()).orElse(null);
		if(food == null) 
		{
			throw new FoodNotFoundException("Could not find food");
		}
		food.setName(updatedFood.getName());
		foodRepo.save(food);
		return food;
	} 
	
}
