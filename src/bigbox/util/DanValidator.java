package bigbox.util;

import java.util.Scanner;

public class DanValidator {
	
	
	public static String getDivInt(Scanner sc)
	{
		
		int userInt = 0;
		String divNumString = "";
		boolean isValidInt = false;
		while (!isValidInt)
		{
			System.out.print("Please enter a valid Division number: ");
			if (sc.hasNextInt())
			{
				userInt = sc.nextInt();
				if (userInt > 0 && userInt < 1000)
				{
					divNumString = Integer.toString(userInt);
					int divStringLength = divNumString.length();
					switch(divStringLength)
					{
						case 1:
							divNumString = ("00" + divNumString);
							break;
						case 2:
							divNumString = ("0" + divNumString);
							break;
						default:
							break;
					}
					if( (!divNumString.equalsIgnoreCase("001")) & (!divNumString.equalsIgnoreCase("111")) )
					{
						sc.nextLine();
						System.out.println("\tError!  This division number was not found in our system.\n");
					}
					else
					{
						isValidInt = true;
					}
				}
				else
				{
					sc.nextLine();
					System.out.println("\tError!  This input only accepts integer values between 001 and 999. \n");
					continue;
				}				
			}
			else
			{
				sc.nextLine();
				System.out.println("\tError! This input only accepts numerical characters 0-9.\n");
				continue;
			}
		}
		sc.nextLine();
		return divNumString;
	}
	
	
	
	public static String getStoreInt(Scanner sc, String divNum)
	{
		
		int userInt = 0;
		String storeNumString = "";
		boolean isValidInt = false;
		while (!isValidInt)
		{
			System.out.print("Please enter a valid Store number: ");
			if (sc.hasNextInt())
			{
				userInt = sc.nextInt();
				if (userInt > 0 && userInt < 100000)
				{
					storeNumString = Integer.toString(userInt);
					int storeStringLength = storeNumString.length();
					switch(storeStringLength)
					{
						case 1:
							storeNumString = ("0000" + storeNumString);
							break;
						case 2:
							storeNumString = ("000" + storeNumString);
							break;
						case 3:
							storeNumString = ("00" + storeNumString);
							break;
						case 4:
							storeNumString = ("0" + storeNumString);
							break;
						default:
							break;
					}
					if ( (divNum.equalsIgnoreCase("001")) && ( (storeNumString.equalsIgnoreCase("00011")) ||
							(storeNumString.equalsIgnoreCase("00075")) || 
							(storeNumString.equalsIgnoreCase("00172")) ||
							(storeNumString.equalsIgnoreCase("00255")) ) )
					{
						isValidInt = true;
					}
					else if ( (divNum.equalsIgnoreCase("111")) &&  ( (storeNumString.equalsIgnoreCase("00001")) ||
							(storeNumString.equalsIgnoreCase("00044")) ) )
					{
						isValidInt = true;
					}
					else 
					{
						System.out.println("\tError!  Division number " +divNum+ " is valid however, no matching store number found in that divison. \n"
								+ "\tPlease check records that Store #" + storeNumString + " is in Division #" + divNum + "\n");
						continue;
					}
				}
				else
				{
					sc.nextLine();
					System.out.println("\tError!  This input only accepts integer values between 00001 and 99999. \n");
					continue;
				}				
			}
			else
			{
				sc.nextLine();
				System.out.println("\tError! This input only accepts numerical characters 0-9.\n");
				continue;
			}
		}
		sc.nextLine();
		return storeNumString;
	}
}

