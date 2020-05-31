package com.stackroute.favouriteservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.Food;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;
import com.stackroute.favouriteservice.service.FoodService;


import io.jsonwebtoken.Jwts;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/favouriteservice")
public class FoodRepoController {
	
	@Autowired
	private FoodService foodService;

	public FoodRepoController(FoodService foodService) {
		super();
		this.foodService = foodService;
	}
	
	
	@PostMapping(path="/food")
	public ResponseEntity<?> SaveNewfood(@RequestBody final Food food, HttpServletRequest request, HttpServletResponse response)
	{
		
		final String authHeader = request.getHeader("authorization") ;
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId:"+ userId);
		
		try {
			food.setUserId(userId);
			foodService.saveFood(food);
		
		}catch(FoodAlreadyExistsException e)
		{
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
			
					
		}
		return new ResponseEntity<Food>(food, HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping(path = "/food/{id}")
	public ResponseEntity<?> deleteFood(@PathVariable("id") final Integer id)
	{
	
		try {
		 foodService.deleteFoodById(id);
		}catch(FoodNotFoundException e)
		{
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
			
					
		}
		return new ResponseEntity<String>("food Delete successfully", HttpStatus.OK);
	
		
	}
	
	@GetMapping(path = "/food/{id}")
	public ResponseEntity<?> fetchMovieById(@PathVariable("id") final Integer id)
	{
		
		Food thisfood = null;
		try {
			thisfood  = foodService.getFoodById(id);
		 
		}catch(FoodNotFoundException e)
		{
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
			
					
		}
		return new ResponseEntity<Food>(thisfood, HttpStatus.OK);
	
		
	}
	
	@GetMapping("/food")
	public @ResponseBody ResponseEntity<List<Food>> fetchMyMovies(final HttpServletRequest req, final HttpServletResponse res)
	{
		
		final String authHeader = req.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId:"+ userId);
		
		return new ResponseEntity<List<Food>>(foodService.getMyFood(userId),HttpStatus.OK);
	}
	
	@PutMapping("/food/{id}")
	public ResponseEntity<?> updateFood(@PathVariable("id") final Integer id, @RequestBody final Food food)
	{

			
		try {
			final Food fetchedFood = foodService.updateFoodName(food);
			return new ResponseEntity<Food>(fetchedFood, HttpStatus.OK);
		}catch(FoodNotFoundException e)
		{
			return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
			
					
		}
	
		
	}

	

}
