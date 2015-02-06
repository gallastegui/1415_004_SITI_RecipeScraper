package connection;
import java.sql.*;

import entity.*;

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
				+ "(directionId INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "recipeId INT NOT NULL,"
				+ "description TEXT,"
				+ "FOREIGN KEY(recipeId) REFERENCES RECIPE(recipeId));"
				+ "CREATE TABLE INGREDIENT"
				+ "(ingredientId INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "recipeId INT NOT NULL,"
				+ "name TEXT,"
				+ "amount TEXT,"
				+ "FOREIGN KEY(recipeId) REFERENCES RECIPE(recipeId));"
				+ "CREATE TABLE REVIEW"
				+ "(reviewId INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "recipeId INT NOT NULL,"
				+ "stars TEXT,"
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
	
	public boolean disconnectDatabase()
	{
		if(dbName == null)
			 return false;
		try 
		{
			 connector.close();
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
				+ "VALUES("+id+",'"+name+"','"+description+"','"+timePrep+"','"+timeCook+"','"+timeTotal+"','"+rating+"','"+category+"');";
		
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
	
	public boolean insertNutrition(Integer nutritionId, String name)
	{
		Statement stmt = null;
		String sql = "INSERT INTO NUTRITION(nutritionId,name)"
				+ "VALUES("+nutritionId+",'"+name+"');";

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

	public boolean insertDirection(String description, int recipeId)
	{
		Statement stmt = null;
		String sql = "INSERT INTO DIRECTION(recipeId,description)"
				+ "VALUES("+recipeId+",'"+description+"');";
		
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
				+ "VALUES('"+name+"',"+recipeId+",'"+amount+"');";
		
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
	
	public boolean insertReview(String description, String author, String stars, int recipeId)
	{
		Statement stmt = null;
		String sql = "INSERT INTO REVIEW(description, recipeId, author, stars)"
				+ "VALUES(\""+description.replace('"', '\'')+"\","+recipeId+",\""+author+"\",\""+stars+"\");";
	
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
		String sql = "INSERT INTO REL_RECIPE_NUTRITION "
				+ "VALUES("+recipeId+","+nutritionId+",'"+amount+"','"+percentage+"');";
		
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
			ResultSet rs = stmt.executeQuery( "SELECT recipeId FROM RECIPE ORDER BY recipeId DESC LIMIT 1;" );
			while (rs.next())
			{
				id = rs.getInt("recipeId");
			}
			rs.close();
			stmt.close();

		} catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return id;
		}
		
		return id;
	}
	
	public Integer selectLastIdNutrition()
	{
		Statement stmt = null;
		int id = -1;
		try
		{
			stmt = connector.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT nutritionId FROM NUTRITION ORDER BY nutritionId DESC LIMIT 1;" );
			while (rs.next())
			{
				id = rs.getInt("nutritionId");
			}
			rs.close();
			stmt.close();
			
		} catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return id;
		}
		
		return id;
	}
	
	public Integer selectNutrition(String name)
	{
		Statement stmt = null;
		int id = -1;
		try
		{
			stmt = connector.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT nutritionId FROM NUTRITION where name = '"+name+"';");
			while (rs.next())
			{
				id = rs.getInt("nutritionId");
			}
			rs.close();
			stmt.close();

		} catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return -1;
		}
		
		return id;
	}
	
	public boolean insertRecipe(Recipe r)
	{
		
		if(connectDatabase() == false)
			return false;
		
		int id = selectLastIdRecipe() + 1;
		int nutritionId = -1;
		
		/*se inserta la receta*/
		if(insertRecipe(id, r.getName(), r.getDescription(), r.getTimes()[0], r.getTimes()[1], r.getTimes()[2], r.getRating(), r.getCategory()) == false)
		{
			disconnectDatabase();
			return false;
		}
		
		/*se insertan los ingredientes*/
		for(Ingredient in : r.getIngredients())
		{
			insertIngredient(in.getName(), in.getAmount(),id);
		}
		
		/*se insertan los nutrientes*/
		for(Nutrient nt : r.getNutrients())
		{
			if((nutritionId=selectNutrition(nt.getName())) == -1)
			{
				nutritionId = selectLastIdNutrition() + 1;
				if(insertNutrition(nutritionId,nt.getName()) == false)
				{
					disconnectDatabase();
					return false;
				}
			}

			if(insertrelRecipeNutrition(id, nutritionId, nt.getUnit(), nt.getPercentage()) == false)
			{
				disconnectDatabase();
				return false;
			}
		}
		
		/*Se insertan los pasos*/
		for(String dr : r.getDirection())
		{
			if(insertDirection(dr, id) == false)
			{
				disconnectDatabase();
				return false;
			}
		}
		
		/*Se insertan las criticas*/
		for(Review rv : r.getReviews())
		{
			if(insertReview(rv.getText(), rv.getAuthor(), rv.getStars(), id) == false)
			{
				disconnectDatabase();
				return false;
			}
		}
		
		try
		{
			connector.commit();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		disconnectDatabase();
		
		return true;
	}
	/**MAIN**/
	public static void main(String [] args)
	{
		
		SqlConnection sc = new SqlConnection("C:\\Users\\jars\\Sqliteman-1.2.2\\allrecipesv1.db");
		
		sc.crateDatabase();
	}
}
