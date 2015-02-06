package test;

import java.util.ArrayList;

import navigation.ScraperNavigator;

import org.jsoup.nodes.Document;

import connection.SqlConnection;
import engine.RecipeEngine;
import entity.Recipe;



public class TestScraper
{
	public static void main(String [] args)
	{
		RecipeEngine re = new RecipeEngine();
		ScraperNavigator sn = new ScraperNavigator();
		SqlConnection sc = new SqlConnection("C:\\Users\\jars\\Sqliteman-1.2.2\\allrecipesv1.db");
		Recipe actualRecipe = null;
		Document d = re.obtainRecipeHtml("http://allrecipes.com/"), aux = null;
		ArrayList<String> categoryUrls = null, recipeUrls = null;
		String page = null;
		int contador = 0, flag = 0;
		
		categoryUrls = sn.getCategoryUrls(d);
		
		if(categoryUrls == null)
			return;
		
		for(String categoryUrl : categoryUrls)
		{
			d = re.obtainRecipeHtml(categoryUrl);
			page = categoryUrl;
			flag = 0;
			while(flag == 0)
			{
				aux = re.obtainRecipeHtml(page);
				recipeUrls = sn.getRecipeUrlsFromPage(aux);
				for(String recipeUrl : recipeUrls)
				{
					System.out.println(contador+":"+recipeUrl);
					actualRecipe = re.obtainRecipe("http://allrecipes.com"+recipeUrl);
					if(actualRecipe != null)
					{
						actualRecipe.setCategory(categoryUrl);
						if(sc.insertRecipe(actualRecipe) == true)
						{
							contador++;
						}
					}
				}
				page = sn.getNextPage(aux);
				recipeUrls.clear();
				if(page == null)
					flag = 1;
			}
		}
		return;
	}
}
