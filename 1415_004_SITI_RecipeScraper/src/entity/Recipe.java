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
	ArrayList<String> ingredients;
	HashMap<String, ArrayList<String>> nutrients;
	ArrayList<String> direction;
	HashMap<Integer, ArrayList<String>> reviews;
	
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
	public ArrayList<String> getIngredients()
	{
		return ingredients;
	}
	public void setIngredients(ArrayList<String> ingredients)
	{
		this.ingredients = ingredients;
	}
	public HashMap<String, ArrayList<String>> getNutrients()
	{
		return nutrients;
	}
	public void setNutrients(HashMap<String, ArrayList<String>> nutrients)
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
	public HashMap<Integer, ArrayList<String>> getReviews()
	{
		return reviews;
	}
	public void setReviews(HashMap<Integer, ArrayList<String>> reviews)
	{
		this.reviews = reviews;
	}
}
