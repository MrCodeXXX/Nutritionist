package com.stackroute.favouriteservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.domain.Food;




@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class FoodRepoTest {
	
	@Autowired
	private  FoodRepo repo;
	
	
	
	Food food;
	
	@Before
	public void setUp()
	{
		food=new Food(1, "1234", "Abc", "user1");
	}
	
	@After
	public void tearDown()
	{
		repo.deleteAllInBatch();
	}
	

	@Test
	public void testSaveFood() throws Exception
	{
		repo.save(food);
		boolean fetch=repo.existsById(food.getId());
		assertThat(fetch).isEqualTo(true);

		
		
	}
	
	@Test
	public void testDeleteFood() throws Exception {
		
		 Food food2 = repo.save(food);
		assertEquals("Abc",food2.getName());
		repo.delete(food2);
		assertEquals(Optional.empty(),repo.findById(1));
	}
	@Test
	public void testGetFood() throws Exception  {
		
		
		final Food food1 = repo.save(food);
		assertEquals(food1.getName(),"Abc");
		
	}
	
	
	@Test
	public void testGetAllFoods() throws Exception {
		
		Food food2 = new Food();
		food2=new Food(2, "1000", "tmk", "user2");
		
		repo.save(food);
		repo.save(food2);
		List<Food> foodList =repo.findByUserId("user2");
		
		assertEquals(foodList.size(),1);
		
	}
	


}
