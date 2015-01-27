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
	
	public boolean crateDatabase()
	{
		Statement stmt = null;
		String sql = "CREATE TABLE RECIPE"
				+ "(recipeId INT PRIMARY KEY,"
				+ "name TEXT NOT NULL,"
				+ "description TEXT,"
				+ "timePrep TEXT,"
				+ "timeCook TEXT,"
				+ "rating FLOAT,"
				+ "category TEXT)"
				+ "CREATE TABLE NUTRITION"
				+ "(nutritionId INT PRIMARY KEY,"
				+ "name TEXT NOT NULL,"
				+ "amount FLOAT,"
				+ "percentage TEXT)"
				+ "CREATE TABLE DIRECTION"
				+ "(directionId INT PRIMARY KEY,"
				+ "description TEXT)"
				+ "CREATE TABLE INGREDIENT"
				+ "(ingredientId PRIMARY KEY,"
				+ "recipeId INT PRIMARY KEY,"
				+ "name TEXT)"
				+ "CREATE TABLE REVIEW"
				+ "(reviewId INT PRIMARY KEY,"
				+ "recipeId INT PRIMARY KEY"
				+ "description TEXT,"
				+ "author text)"
				+ "CREATE TABLE REL_RECIPE_NUTRITION"
				+ "(recipeId INT PRIMARY KEY,"
				+ "nutritionId INT PRIMARY KEY,"
				+ "amount TEXT NOT NULL,"
				+ "percentage)"
				+ "";
		try 
		{
			Class.forName("org.sqlite.JDBC");
			try 
			{
				connector = DriverManager.getConnection("jdbc:sqlite:"+dbName);
				stmt = connector.createStatement();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			
		return true;
	}

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
