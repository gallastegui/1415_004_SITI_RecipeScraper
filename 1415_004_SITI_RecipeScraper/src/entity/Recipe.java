package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe
{
	Integer id;
	String name;
	String description;
	String[] times;
	String rating;
	String category;
	ArrayList<Ingredient> ingredients;
	ArrayList<Nutrient> nutrients;
	ArrayList<String> direction;
	ArrayList<Review> reviews;
	
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String[] getTimes()
	{
		return times;
	}
	public void setTimes(String[] times)
	{
		this.times = times;
	}
	public String getRating()
	{
		return rating;
	}
	public void setRating(String rating)
	{
		this.rating = rating;
	}
	public ArrayList<Ingredient> getIngredients()
	{
		return ingredients;
	}
	public void setIngredients(ArrayList<Ingredient> ingredients)
	{
		this.ingredients = ingredients;
	}
	public ArrayList<Nutrient> getNutrients()
	{
		return nutrients;
	}
	public void setNutrients(ArrayList<Nutrient> nutrients)
	{
		this.nutrients = nutrients;
	}
	public ArrayList<String> getDirection()
	{
		return direction;
	}
	public void setDirection(ArrayList<String> direction)
	{
		this.direction = direction;
	}
	public ArrayList<Review> getReviews()
	{
		return reviews;
	}
	public void setReviews(ArrayList<Review> reviews)
	{
		this.reviews = reviews;
	}
}
