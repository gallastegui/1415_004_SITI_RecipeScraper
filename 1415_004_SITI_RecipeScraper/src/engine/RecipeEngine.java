package engine;
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import entity.Recipe;
public class RecipeEngine
{
	/************************************************RECIPE METHODS**************************************************/
	public org.jsoup.nodes.Document obtainRecipeHtml(String url)
	{
		Document d = null;
		
		if(url == null)
			return null;
		
		try
		{
			d = Jsoup.connect(url).timeout(1000*1000).get();
		} catch (IOException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
		
		return d;
	}
	
	public Recipe obtainRecipe(String url)
	{
		Recipe r = new Recipe();
		Document d = null;
		
		if(url == null)
			return null;
		
		d = obtainRecipeHtml(url);
		if(d == null)
			return null;
		
		r.setName(obtainRecipeName(d));
		r.setDescription(obtainRecipeDescription(d));
		r.setTimes(obtainRecipeTimes(d));
		r.setRating(obtainRecipeRating(d));
		r.setIngredients(obtainIngredients(d));
		r.setNutrients(obtainNutrients(d));
		r.setDirection(obtainDirections(d));
		r.setReviews(obtainReviews(d));
			
		return r;
	}
	
	public String obtainRecipeName(Document d)
	{
		String name = "undefined";
		Element e = null;
		
		if(d == null)
			return name;
		
		e = d.getElementById("metaOpenGraphTitle");
		name = e.attr("content");
		
		return name;
	}
	
	public String obtainRecipeDescription(Document d)
	{
		String description = "undefined";
		Element e = null;
		
		if(d == null)
			return description;
		
		e = d.getElementById("metaDescription");
		description = e.attr("content");
		
		return description;
	}
	
	public String[] obtainRecipeTimes(Document d)
	{
		String[] times = {"","",""};
		int cont=0;
		Element e=null,e2=null;
		
		e = d.getElementById("divRecipeTimesContainer");
		e2 = e.getElementById("ulReadyTime");
		
		for(Element li : e2.select("li"))
		{
			for(String s : li.text().split(" "))
			{
				if( !s.equals("PREP")  && !s.equals("COOK") && !s.equals("READY") && !s.equals("IN"))
				{
					times[cont] = times[cont] +" "+ s;
				}
			}
			cont++;
		}	
		
		return times;
	}
	
	public String obtainRecipeRating(Document d)
	{
		Elements e = null,e2=null;
		String rating = "undefined";
		e = d.select("div[itemprop=aggregateRating]");
		for(Element el : e)
		{
			e2 = e.select("meta[itemprop=ratingValue]");
			for(Element el2 : e2)
			{
				rating = el2.attr("content");
			}
		}
		
		return rating;
	}
	
	public HashMap<String, String> obtainRecipeValues(String url)
	{
		HashMap<String, String> recipeValues = new HashMap<String,String>();
		Document d = null;
		String name=null,description=null,rating=null;
		String [] times = {"","",""};
		
		if(url == null)
			return null;
		
		d = obtainRecipeHtml(url);
		if(d == null)
			return null;
		
		name = obtainRecipeName(d);
		description = obtainRecipeDescription(d);
		times =obtainRecipeTimes(d);
		rating = obtainRecipeRating(d);
		
		recipeValues.put("NAME", name);
		recipeValues.put("DESCRIPTION", description);
		recipeValues.put("TIMEPREP", times[0]);
		recipeValues.put("TIMECOOK", times[1]);
		recipeValues.put("TIMETOTAL", times[2]);
		recipeValues.put("RATING", rating);
		
		return recipeValues;
	}
	/************************************************END RECIPE METHODS**************************************************/
	/************************************************INGREDIENT METHODS**************************************************/
	public ArrayList<String> obtainIngredients(Document d)
	{
		ArrayList<String> ingredients = new ArrayList<String>();
		Elements e= null;
		e=d.select("li[id=liIngredient]");
		for(Element el : e)
		{
			ingredients.add(el.text());
		}
	
		return ingredients;
		
	}
	/************************************************END INGREDIENT METHODS**************************************************/
	/************************************************NUTRITION METHODS**************************************************/
	public HashMap<String, ArrayList<String>> obtainNutrients(Document d)
	{
		Elements e2= null,categories = null, units = null, percentages = null;
		String category = null, unit = null, percentage = null;
		HashMap<String, ArrayList<String>> nutrients = new HashMap<String, ArrayList<String>>();
		ArrayList<String> aux = null;
		
		e2=d.select("ul[id=ulNutrient]");
		for(Element nutr : e2)
		{
			categories = nutr.select("li[class=categories");
			for(Element cat : categories)
			{
				category = cat.text();
			}
			
			units = nutr.select("li[class=units]");
			for(Element un : units)
			{
				unit = un.text();
			}
			
			percentages = nutr.select("li[class=percentages]");
			for(Element per : percentages)
			{
				percentage = per.text();
			}
			aux = new ArrayList<String>();
			aux.add(unit);
			aux.add(percentage);
			nutrients.put(category, aux);
		}
		return nutrients;
	}
	/************************************************END NUTRITION METHODS**************************************************/
	/************************************************DIRECTION METHODS**************************************************/
	public ArrayList<String> obtainDirections(Document d)
	{
		Elements e = null;
		ArrayList<String> directions = new ArrayList<String>();
		e=d.select("span[class=plaincharacterwrap break]");
		for(Element direct : e)
		{
			directions.add(direct.text());
		}
		
		return directions;	
	}
	/************************************************END DIRECTION METHODS**************************************************/
	/************************************************REVIEW METHODS**************************************************/
	public HashMap<Integer, ArrayList<String>> obtainReviews(Document d)
	{
		HashMap<Integer, ArrayList<String>> reviews = new HashMap<Integer, ArrayList<String>>();
		ArrayList<String> aux = new ArrayList<String>();
		Integer incr = 1;
		Elements e = null, rating = null, text = null, author = null;
		String stars = null, txt = null, auth = null;
		
		e=d.select("div[itemprop=review]");
		for(Element review : e)
		{
			rating = review.select("meta[itemprop=ratingValue]");
			for(Element rat : rating)
			{
				stars = rat.attr("content");
			}
			
			text = review.select("p[id=pReviewText]");
			for(Element tx : text)
			{
				txt = tx.text();
			}
			
			author = review.select("span[itemprop=author]");
			for(Element au : author)
			{
				auth = au.text();
			}
			aux.add(auth);
			aux.add(txt);
			aux.add(stars);
			reviews.put(incr, aux);
			incr++;
			aux.clear();
		}
		
		return reviews;
	}
	/************************************************END REVIEW METHODS**************************************************/
	/*public static void main(String [] args)
	{
		RecipeEngine re = new RecipeEngine();
		Document d = re.obtainRecipeHtml("http://allrecipes.com/");
		Elements e2 = null, rating = null, text = null, author = null;
		
		e2=d.select("div[itemprop=review]");
		for(Element review : e2)
		{
			rating = review.select("meta[itemprop=ratingValue]");
			for(Element rat : rating)
			{
				String stars = rat.attr("content");
				System.out.println(stars);
			}
			
			text = review.select("p[id=pReviewText]");
			for(Element tx : text)
			{
				System.out.println(tx.text());
			}
			
			author = review.select("span[itemprop=author]");
			for(Element au : author)
			{
				System.out.println(au.text());
			}
		}

		System.out.println("finnish");
	}*/
}
