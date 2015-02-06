package entity;

public class Review
{
	String author;
	String text;
	String stars;
	
	public Review(String author, String text, String stars)
	{
		this.author = author;
		this.text = text;
		this.stars = stars;
	}
	
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public String getStars()
	{
		return stars;
	}
	public void setStars(String stars)
	{
		this.stars = stars;
	}
	
	
}
