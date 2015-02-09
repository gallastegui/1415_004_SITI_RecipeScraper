package navigation;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import engine.RecipeEngine;

public class ScraperNavigator
{
	public void navigateCategory(String UrlCategory)
	{
		
	}
	
	public ArrayList<String> getCategoryUrls(Document d)
	{
		ArrayList<String> urls = new ArrayList<String>();
		Elements e = null, links = null, spans = null;
		
		e=d.select("div[class=showcase-cntnr]");
		for(Element e2 : e)
		{
			spans = e2.select("span");
			for(Element span : spans)
			{
				links = span.select("a");
				for(Element link : links)
				{
					urls.add(link.attr("href"));
				}
			}
		}
		
		return urls;
	}
	
	public String getNextPage(Document d)
	{
		String url = null;
		Elements e = null, links = null;

		e=d.select("div[id=ctl00_CenterColumnPlaceHolder_RecipeContainer_ucPager_corePager_panel]");
		for(Element e2 : e)
		{
			links = e2.select("a");
			for(Element link : links)
			{
				if(link.text().equals("NEXT »"))
				{
					url = link.attr("href");
				}
			}
		}
		
		return url;
	}
	
	public ArrayList<String> getRecipeUrlsFromPage(Document d)
	{
		Elements e = null, links = null;
		ArrayList<String> urls = new ArrayList<String>();
		
		e=d.select("div[class=grid-view]");
		for(Element e2 : e)
		{
			links = e2.select("a[class=img-link]");
			for(Element link : links)
			{
				urls.add(link.attr("href"));
			}
		}		
		
		return urls;
	}
	
	/*public static void main(String [] args)
	{
		RecipeEngine re = new RecipeEngine();
		Document d = re.obtainRecipeHtml("http://allrecipes.com/Recipes/Holidays-and-Events/Valentines-Day/main.aspx?soid=showcase_10&Page=39#recipes");
		Elements e = null, links = null, spans=null;
		
		e=d.select("div[class=grid-view]");
		for(Element e2 : e)
		{
			links = e2.select("a[class=img-link]");
			for(Element link : links)
			{
				System.out.println(link.attr("href"));
			}
		}

		System.out.println("finnish3");
	}*/
}
