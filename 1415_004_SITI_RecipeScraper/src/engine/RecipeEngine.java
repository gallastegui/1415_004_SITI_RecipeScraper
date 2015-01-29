package engine;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;;
public class RecipeEngine
{
	public org.jsoup.nodes.Document obtainRecipeHtml()
	{
		Document d = null;
		
		try
		{
			d = Jsoup.connect("http://allrecipes.com/recipe/grandma-ritas-soft-butter-rolls/detail.aspx?soid=home_pins_3").get();
		} catch (IOException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
		
		return d;
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
	
	public static void main(String [] args)
	{
		RecipeEngine re = new RecipeEngine();
		int cont=0;
		Element e = null;
		org.jsoup.nodes.Element e2;
		Document d = re.obtainRecipeHtml();
		String prep, cook, total;
		e = d.getElementById("divRecipeTimesContainer");
		e2 = e.getElementById("ulReadyTime");
		String[] times = {"","",""};
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

		
		for(String caracter : times)	
			System.out.println(caracter);
	}
}
