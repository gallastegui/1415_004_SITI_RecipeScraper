package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import navigation.ScraperNavigator;

import org.jsoup.nodes.Document;

import engine.RecipeEngine;



public class TestScraper
{
	public static void main(String [] args)
	{
		RecipeEngine re = new RecipeEngine();
		ScraperNavigator sn = new ScraperNavigator();
		
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
					contador++;
					System.out.println(contador+":"+recipeUrl);
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
