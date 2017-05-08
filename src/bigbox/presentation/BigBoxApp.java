package bigbox.presentation;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import bigbox.business.Store;
import bigbox.db.DAOFactory;
import bigbox.divisions.DivisionDAO;
import bigbox.store.StoreDAO;
import bigbox.store_sales.Store_SalesDAO;
import bigbox.util.Validator;

public class BigBoxApp {

	private static StoreDAO storeDAO = null;
	private static DivisionDAO divisionDAO = null;
	private static Store_SalesDAO store_salesDAO = null;
	private static Scanner sc;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		System.out.println("Welcome to the Big Box application!\n");		
		
		storeDAO = DAOFactory.createStoreDAO();
		divisionDAO = DAOFactory.createDivisionsDAO();		
		store_salesDAO = DAOFactory.createStore_SalesDAO();
		
		String choice = "y";
		while (choice.equalsIgnoreCase("y")) {
			// split menu into stand alone function
			
			// give user option of continue after running function to avoid clutter by menu
			
			String operation = Validator.getString(sc,
					"\nCOMMAND MENU\n" + "list\t\t- List all stores\n" 
							+ "divList\t\t- List all divisions\n"
							+ "div\t\t- List all the stores in a division\n"
							+ "add\t\t- Add a store\n" 
							+ "del\t\t- Delete a store\n" 
							+ "totalSales\t- Get total sales for the lifetime of a store\n"
							+ "annualSales\t- Get annual sales for a store\n"
							+ "divSales\t- Get total sales for the lifetime of a divison\n" 
							+ "help\t\t- Show this menu\n"
							+ "exit\t\t- Exit the application\n\n" + "Enter a command:  ");

			switch (operation.toLowerCase()) 
			{
				
			case "list":
				storeList();
				break;
				
			case "divlist":
				divisionDAO.getAllDivisions();
				break;				
			
			case "div":
				div();
				break;				
			
			case "add":
				add();
				break;				
			
			case "del":
				del();				
				break;			
			
			case "totalsales":
				totalSales();				
				break;
				
			case "annualsales":
				annualSales();
				break;
				
			case "divsales":
				divSales();				
				break;
				
			case "help":
				continue;			
			
			case "exit":
				choice = exit();				
				break;
			
			default:
				System.out.println("User Input Error!");
				break;
			}
		}
		sc.close();
	}
	private static void divSales()
	{
		String divNum = ""; 
		boolean divTest = false;
		int divId = -1;
		while(!divTest)
		{
			divNum = Validator.getStringNumeric(sc, "Please enter division number: ", 3);
			divId = divisionDAO.getDivisionID(divNum);
			if(divId > 0)
			{
				divTest = true;
			}
			else
			{
				System.out.println("Error!  Division number " + divNum + " was not found in database.");
			}
		}	
		double totalDivSales = store_salesDAO.divSalesTotal(divId);
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		String totalDivSalesFormatted = currency.format(totalDivSales);
		System.out.println("Division " +divNum+ " has a total sales amount of " +totalDivSalesFormatted);
	}

	private static void totalSales()
	{
		String storeNum = Validator.getStringNumeric(sc, "Please enter the Store number: ", 5);
		String divNum = ""; 
		boolean divTest = false;
		int divId = -1;
		while(!divTest)
		{
			divNum = Validator.getStringNumeric(sc, "Please enter division number: ", 3);
			divId = divisionDAO.getDivisionID(divNum);
			if(divId > 0)
			{
				divTest = true;
			}
			else
			{
				System.out.println("Error!  Division number " + divNum + " was not found in database.");
			}
		}				
		double totalSales = store_salesDAO.storeSalesTotal(storeNum, divId);
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		String totalSalesFormatted = currency.format(totalSales);
		System.out.println("Store " +storeNum+ " in Division " +divNum+ " has a total sales amount of " +totalSalesFormatted);
	}
	
	private static void annualSales()
	{
		
		// get Division from user and validate division number by returning Division ID from divisions table
		String divNum = ""; 
		boolean divTest = false;
		int divId = -1;
		while (!divTest)
		{
			divNum = Validator.getStringNumeric(sc, "Please enter division number: ", 3);
			divId = divisionDAO.getDivisionID(divNum);
			if (divId > 0) 
			{
				divTest = true;
			} 
			else 
			{
				System.out.println("Error!  Division number " + divNum + " was not found in database.");
			}
		}
		
		// get Store number from user and validate store number by returning Store ID from stores table
		String storeNum = "";
		boolean storeTest = false;
		int storeId = -1;
		while(!storeTest)
		{
			storeNum = Validator.getStringNumeric(sc, "Please enter the Store number: ", 5);
			storeId = storeDAO.getStoreId(storeNum, divId);
			if(storeId > 0)
			{
				storeTest = true;
			}
			else
			{
				System.out.println("Error!  Store number " + storeNum + " was not found in database.");
			}
		}		
		int year = Validator.getInt(sc, "Please enter the year: ", 2000, 2099);
		double storeAnnualSales = store_salesDAO.storeSalesAnnual(storeId, year);
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		String annualSalesFormatted = currency.format(storeAnnualSales);
		System.out.println("Store " +storeNum+ " had a total sales amount of " +annualSalesFormatted+ " in "+ year);
	}
	
	private static void add() 
	{
		String storeName = Validator.getLine(sc, "Please enter store name: ");
		String storeAddress = Validator.getLine(sc, "Please enter store address: ");
		String storeCity = Validator.getLine(sc, "Please enter city: ");
		String storeState = Validator.getString(sc, "Please enter state: ", 2);
		String storeZip = Validator.getStringNumeric(sc, "Please enter zip code: ", 5);
		String storeNum = Validator.getStringNumeric(sc, "Please enter store number: ", 5);
		boolean divTest = false;
		int divID = 0;
		while(!divTest)
		{
			String divNum = Validator.getStringNumeric(sc, "Please enter div number: ", 3);
			divID = divisionDAO.getDivisionID(divNum);
			if(divID > 0)
			{
				divTest = true;
			}
			else
			{
				System.out.println("Error!  Division number " + divNum + " was not found in database.");
			}
		}				
		storeDAO.addStore(divID, storeNum, storeName, storeAddress, storeCity, storeState, storeZip);	
		
	}
	
	private static void div() 
	{
		// get Division from user and validate division number by returning
		// Division ID from divisions table
		String divNum = "";
		boolean divTest = false;
		int divId = -1;
		while (!divTest) 
		{
			divNum = Validator.getStringNumeric(sc, "Please enter division number: ", 3);
			divId = divisionDAO.getDivisionID(divNum);
			
			if (divId > 0) 
			{
				divTest = true;
			} 
			else 
			{
				System.out.println("Error!  Division number " + divNum + " was not found in database.");
			}
		}
		System.out.println();
		ArrayList<Store> divStoresArray = storeDAO.getAllStoresByDivision(divId);
		for (Store i : divStoresArray) 
		{
			System.out.print(i);
		}
	}
	
	private static void storeList()
	{
		System.out.println();
		ArrayList<Store> allStoresArray = storeDAO.getAllStores();
		for (Store i : allStoresArray)
		{
			System.out.print(i);
		}
	}

	private static void del()
	{
		String delDivNum = Validator.getStringNumeric(sc, "Please enter the 3-digit Division number in which this store operates: ", 3);
		int divID = divisionDAO.getDivisionID(delDivNum);
		String delStoreNum = Validator.getStringNumeric(sc, "Please enter the 5-digit Store number: ", 5);
		boolean delStore = storeDAO.deleteStore(divID, delStoreNum);
		if(!delStore)
		{
			System.out.println("Store not deleted");
		}
		else
		{
			System.out.println("Store " + delStoreNum + " in Divison " + delDivNum + " has been deleted."); 
		}
	}

	private static String exit()
	{
		System.out.println("\nThank you for using this application.");
		String c = "n";
		return c;
	}
}

/*
 * PURPOSE OF A CODE REVIEW
 * 
 * For the code author to get feedback and consider/adjust code.
 * Do not 'explain' the code unless specifically asked by reviewer.
 * 
 * Positive feedback
 * 
 * Consider adjustments
 * 
 * Required changes
 * 
 */


