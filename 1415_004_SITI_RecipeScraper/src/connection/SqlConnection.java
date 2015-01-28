package connection;
import java.sql.*;

public class SqlConnection
{
	private Connection connector = null;
	private String dbName = null;
	
	public SqlConnection(String dbName)
	{
		this.dbName=dbName;
	}
	/*
	 * Database operations
	 */
	public boolean crateDatabase()
	{
		if(dbName == null)
			return false;
		
		Statement stmt = null;
		String sql = "CREATE TABLE RECIPE"
				+ "(recipeId INT PRIMARY KEY AUTOINCREMENT,"
				+ "name TEXT NOT NULL,"
				+ "description TEXT,"
				+ "timePrep TEXT,"
				+ "timeCook TEXT,"
				+ "rating FLOAT,"
				+ "category TEXT);"
				+ "CREATE TABLE NUTRITION"
				+ "(nutritionId INT PRIMARY KEY AUTOINCREMENT,"
				+ "name TEXT NOT NULL);"
				+ "CREATE TABLE DIRECTION"
				+ "(directionId INT PRIMARY KEY AUTOINCREMENT,"
				+ "recipeId INT PRIMARY KEY,"
				+ "description TEXT,"
				+ "FOREIGN KEY(recipeId) REFERENCES RECIPE(recipeId));"
				+ "CREATE TABLE INGREDIENT"
				+ "(ingredientId PRIMARY KEY AUTOINCREMENT,"
				+ "recipeId INT PRIMARY KEY,"
				+ "name TEXT,"
				+ "amount TEXT,"
				+ "FOREIGN KEY(recipeId) REFERENCES RECIPE(recipeId));"
				+ "CREATE TABLE REVIEW"
				+ "(reviewId INT PRIMARY KEY AUTOINCREMENT,"
				+ "recipeId INT PRIMARY KEY"
				+ "description TEXT,"
				+ "author TEXT,"
				+ "FOREIGN KEY(recipeId) REFERENCES RECIPE(recipeId));"
				+ "CREATE TABLE REL_RECIPE_NUTRITION"
				+ "(recipeId INT PRIMARY KEY,"
				+ "nutritionId INT PRIMARY KEY,"
				+ "amount TEXT NOT NULL,"
				+ "percentage TEXT,"
				+ "FOREIGN KEY(recipeId) REFERENCES RECIPE(recipeId),"
				+ "FOREIGN KEY(nutritionId) REFERENCES NUTRITION(nutritionId));";

		try 
		{
			Class.forName("org.sqlite.JDBC");
			try 
			{
				connector = DriverManager.getConnection("jdbc:sqlite:"+dbName);
				stmt = connector.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
			    connector.close();
			}
			catch (SQLException e)
			{
			    System.err.println(e.getClass().getName() + ": " + e.getMessage());
				return false;
			}
		}
		catch (ClassNotFoundException e)
		{
		    System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
			
		return true;
	}
	
	public boolean connectDatabase()
	{
		if(dbName == null)
			return false;
		try 
		{
		      Class.forName("org.sqlite.JDBC");
		      connector = DriverManager.getConnection("jdbc:sqlite:"+dbName);
		}
		catch (Exception e)
		{
		      System.err.println(e.getClass().getName() + ": " + e.getMessage());
		      return false;
		}
		return true;
	}
	/*
	 * Insert operations 
	 */
	public boolean insertRecipe(String name, String description, String timePrep, String timeCook, float rating, String category)
	{
		Statement stmt = null;
		String sql = "INSERT INTO RECIPE(name, description, timePrep, timeCook, rating, category)"
				+ "VALUES("+name+","+description+","+timePrep+","+timeCook+","+rating+","+category+");";
		if(name == null)
		{
			return false;
		}
		
		if(!connectDatabase())
			return false;
		
		try
		{
			connector.setAutoCommit(false);
		} 
		catch (SQLException e1)
		{
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			return false;
		}
		
		try
		{
			stmt = connector.createStatement();
			stmt.executeUpdate(sql);
		    stmt.close();
		    connector.commit();
		    return true;
		}
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}
	
	public boolean insertNutrition(String name)
	{
		Statement stmt = null;
		String sql = "INSERT INTO NUTRITION(name)"
				+ "VALUES("+name+");";
		
		if(name == null)
			return false;
		
		if(!connectDatabase())
			return false;

		try
		{
			connector.setAutoCommit(false);
		} 
		catch (SQLException e1)
		{
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			return false;
		}
		
		try
		{
			stmt = connector.createStatement();
			stmt.executeUpdate(sql);
		    stmt.close();
		    connector.commit();
		    return true;
		}
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}

	public boolean insertDirection(String name, String description, int recipeId)
	{
		Statement stmt = null;
		String sql = "INSERT INTO DIRECTION(name,recipeId,description)"
				+ "VALUES("+name+","+recipeId+","+description+");";
		
		if(name == null)
			return false;
		
		if(!connectDatabase())
			return false;

		try
		{
			connector.setAutoCommit(false);
		} 
		catch (SQLException e1)
		{
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			return false;
		}
		
		try
		{
			stmt = connector.createStatement();
			stmt.executeUpdate(sql);
		    stmt.close();
		    connector.commit();
		    return true;
		}
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}
	
	public boolean insertIngredient(String name, String amount,int recipeId)
	{
		Statement stmt = null;
		String sql = "INSERT INTO INGREDIENT(name, recipeId, amount)"
				+ "VALUES("+name+","+recipeId+","+amount+");";
		
		if(name == null)
			return false;
		
		if(!connectDatabase())
			return false;

		try
		{
			connector.setAutoCommit(false);
		} 
		catch (SQLException e1)
		{
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			return false;
		}
		
		try
		{
			stmt = connector.createStatement();
			stmt.executeUpdate(sql);
		    stmt.close();
		    connector.commit();
		    return true;
		}
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}
	
	public boolean insertReview(String description, String author, int recipeId)
	{
		Statement stmt = null;
		String sql = "INSERT INTO REVIEW(description, recipeId, author)"
				+ "VALUES("+description+","+recipeId+","+author+");";
		
		if(description == null)
			return false;
		
		if(!connectDatabase())
			return false;

		try
		{
			connector.setAutoCommit(false);
		} 
		catch (SQLException e1)
		{
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			return false;
		}
		
		try
		{
			stmt = connector.createStatement();
			stmt.executeUpdate(sql);
		    stmt.close();
		    connector.commit();
		    return true;
		}
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}
	
	public boolean insertrelRecipeNutrition(int recipeId, int nutritionId,String amount, String percentage)
	{
		Statement stmt = null;
		String sql = "INSERT INTO NUTRITION"
				+ "VALUES("+recipeId+","+nutritionId+","+amount+","+percentage+");";
		
		if(amount == null)
			return false;
		
		if(!connectDatabase())
			return false;

		try
		{
			connector.setAutoCommit(false);
		} 
		catch (SQLException e1)
		{
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			return false;
		}
		
		try
		{
			stmt = connector.createStatement();
			stmt.executeUpdate(sql);
		    stmt.close();
		    connector.commit();
		    return true;
		}
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}
	/*
	 * Getters and setters
	 */
	public Connection getConnector()
	{
		return connector;
	}

	public void setConnector(Connection connector)
	{
		this.connector = connector;
	}

	public String getDbName()
	{
		return dbName;
	}

	public void setDbName(String dbName)
	{
		this.dbName = dbName;
	}
}
