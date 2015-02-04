package connection;
import java.sql.*;

import entity.Recipe;

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
				+ "(recipeId INTEGER PRIMARY KEY,"
				+ "name TEXT NOT NULL,"
				+ "description TEXT,"
				+ "timePrep TEXT,"
				+ "timeCook TEXT,"
				+ "timeTotal TEXT,"
				+ "rating TEXT,"
				+ "category TEXT);"
				+ "CREATE TABLE NUTRITION"
				+ "(nutritionId INTEGER PRIMARY KEY,"
				+ "name TEXT NOT NULL);"
				+ "CREATE TABLE DIRECTION"
				+ "(directionId INTEGER PRIMARY KEY,"
				+ "recipeId INT NOT NULL,"
				+ "description TEXT,"
				+ "FOREIGN KEY(recipeId) REFERENCES RECIPE(recipeId));"
				+ "CREATE TABLE INGREDIENT"
				+ "(ingredientId INTEGER PRIMARY KEY,"
				+ "recipeId INT NOT NULL,"
				+ "name TEXT,"
				+ "amount TEXT,"
				+ "FOREIGN KEY(recipeId) REFERENCES RECIPE(recipeId));"
				+ "CREATE TABLE REVIEW"
				+ "(reviewId INTEGER PRIMARY KEY,"
				+ "recipeId INT NOT NULL,"
				+ "description TEXT,"
				+ "author TEXT,"
				+ "FOREIGN KEY(recipeId) REFERENCES RECIPE(recipeId));"
				+ "CREATE TABLE REL_RECIPE_NUTRITION"
				+ "(recipeId INT,"
				+ "nutritionId INT,"
				+ "amount TEXT NOT NULL,"
				+ "percentage TEXT,"
				+ "FOREIGN KEY(recipeId) REFERENCES RECIPE(recipeId),"
				+ "FOREIGN KEY(nutritionId) REFERENCES NUTRITION(nutritionId),"
				+ "PRIMARY KEY(recipeId,nutritionId));";

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
		      connector.setAutoCommit(false);
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
	public boolean insertRecipe(int id, String name, String description, String timePrep, String timeCook, String timeTotal, String rating, String category)
	{
		Statement stmt = null;
		String sql = "INSERT INTO RECIPE(recipeId, name, description, timePrep, timeCook, timeTotal, rating, category)"
				+ "VALUES("+id+","+name+","+description+","+timePrep+","+timeCook+","+timeTotal+","+rating+","+category+");";
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
	
	public Integer selectLastIdRecipe()
	{
		Statement stmt = null;
		int id = -1;
		try
		{
			stmt = connector.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT recipeId FROM RECIPE ORDER BY recipeId LIMIT 1 DESC;" );
			while (rs.next())
			{
				id = rs.getInt("recipeId");
			}
			rs.close();
			stmt.close();
			connector.close();
		} catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return id;
		}
		
		return id;
	}
	
	public boolean insertRecipe(Recipe r)
	{
		int id = selectLastIdRecipe() + 1;
		
		if(insertRecipe(id, r.getName(), r.getDescription(), r.getTimes()[0], r.getTimes()[1], r.getTimes()[2], r.getRating(), r.getCategory()) == false)
			return false;
		
		return true;
	}
	/**MAIN**/
	public static void main(String [] args)
	{
		
		SqlConnection sc = new SqlConnection("C:\\Users\\eps\\Downloads\\Sqliteman-1.2.2\\allrecipesv3.db");
		
		sc.crateDatabase();
	}
}
