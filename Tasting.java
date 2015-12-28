import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
/*
Tasting class
Programmers: Pierce MacMillan, Andrea Zemp, Michelle Petruska

Class to build Tasting objects that contain all the datafields necessary to rate a cup of coffee.
Ancillary data is stored as well, such as price, pricePerOz, etc... for report generation purposes.

Objects of this class can be written to text files and editted using the methods below.
*/
class Tasting {

  //Good ole Scanner for reading in user inputs in the below switch logic controlled menus.
  private Scanner input = new Scanner(System.in);
  
  //A very important choice integer for the user to choose from various switch logic controlled menus throughout the class.
  private int choice = 0;
   
  //String datafields to be saved in a Tasting object.
  private String userName = new String("");
  private String coffeeShopName = new String("");
  private String coffeeShopCity = new String("");
  private String coffeeShopState = new String("");
  private String coffeeShopCountry = new String("");
  private String coffeeBean = new String("");
  private String brewMethod = new String("");
  
  //A Date object as a the coffee's brewed time datafield.
  private Date coffeeBrewTime = new Date();
   
  //An intdatafield for the coffee's temperature.
  private int temperature = 0;
   
  //Double datafields for the coffee's price and price per ounce.
  private double price = 0;
  private double pricePerOz = 0;
  
  //A very important rating integer for taking the user's input in for each rating.
  private int rating = 0;
  
  //Integer datafields that represent the user's ratings of each aspect of a coffee Tasting object.
  private int bitterness = 0;
  private int acidity = 0;
  private int fragrance = 0;
  private int flavor = 0;
  private int aftertaste = 0;
  private int body = 0;
  private int sweetness = 0;
  private int cleanCup = 0;
  
  //A double datafield to represent the user's rating average for this Tasting object.
  private double average = 0;
  
  //An integer array datafield to store all the coffee rating integers.
  private int[] coffeeRatingList = new int[8];
  
  //A NumberFormat for displaying the price and pricePerOz variables as USD.
  private NumberFormat fmt1 = NumberFormat.getCurrencyInstance();
  
  //A File Object that will hold the directory where the tasting report should be printed.
  private File tastingDirectory;
   
  //Construct a new Tasting() object.
  Tasting()
  {
  }
  
  //Construct a new Tasting() object with an ArrayList as an argument.
  //The below commented arraylist.get() data fields represent what can be pulled from each tasting report text file.
  Tasting(ArrayList<String> arrayList)
  {
    userName = arrayList.get(0);
    coffeeShopName = arrayList.get(1);
    coffeeShopCity = arrayList.get(2);
    coffeeShopState = arrayList.get(3);
    coffeeShopCountry = arrayList.get(4);
    //arrayList.get(5) is a blank line.
    //arrayList.get(6) is the coffeeBrewTime to be parsed.
    coffeeBean = arrayList.get(7);
    brewMethod = arrayList.get(8);
    //arrayList.get(9) is the temperature to be parsed.
    //arrayList.get(10) is the price to be parsed.
    //arrayList.get(11) is the pricePerOz to be parsed.
    //arrayList.get(12) is a blank line.
    //arrayList.get(13) is the bitterness to be parsed.
    //arrayList.get(14) is the acidity to be parsed.
    //arrayList.get(15) is the fragrance to be parsed.
    //arrayList.get(16) is the flavor to be parsed.
    //arrayList.get(17) is the aftertaste to be parsed.
    //arrayList.get(18) is the body to be parsed.
    //arrayList.get(19) is the sweetness to be parsed.
    //arrayList.get(20) is the cleanCup to be parsed.
    //arrayList.get(21) is a blank line.
    //arrayList.get(22) is the average to be parsed.

    try // a try block to handle all the parsing, so that exceptions can be caught
    {
      //Parse the date value
      String reportBrewDate = arrayList.get(6);
      SimpleDateFormat dateFormat = new SimpleDateFormat ("EEE MMM dd kk:mm:ss z yyyy");
      //Making sure that the date format is strictly maintained.
      dateFormat.setLenient(false);
      coffeeBrewTime = dateFormat.parse(reportBrewDate);
        
      //Parse the double values
      price = Double.parseDouble(arrayList.get(10));
      pricePerOz = Double.parseDouble(arrayList.get(11));
      average = Double.parseDouble(arrayList.get(22));
        
      //Parse the integer values
      temperature = Integer.parseInt(arrayList.get(9));
      bitterness = Integer.parseInt(arrayList.get(13));
      acidity = Integer.parseInt(arrayList.get(14));
      fragrance = Integer.parseInt(arrayList.get(15));
      flavor = Integer.parseInt(arrayList.get(16));
      aftertaste = Integer.parseInt(arrayList.get(17));
      body = Integer.parseInt(arrayList.get(18));
      sweetness = Integer.parseInt(arrayList.get(19));
      cleanCup = Integer.parseInt(arrayList.get(20));   
    }
    
    //catch any parsing exceptions and prin the stack trace to help with debugging
    catch (Exception ex) 
    {
      ex.printStackTrace();
    }
  }
   
  //Have the user input the userName String.
  protected void setUserName()
  {
    System.out.println();
    System.out.print("Enter your name: ");
    userName = input.nextLine().substring(0);
  }
     
  //Return the userName String.
  protected String getUserName()
  {
    return userName;
  }
   
  //Have the user input the coffeeShopName String.
  protected void setCoffeeShopName()
  {
    System.out.print("Enter the name of the cafe or shop where you purchased your coffee: ");
    coffeeShopName = input.nextLine().substring(0);
  }
   
  //Return the coffeeShopName String.
  protected String getCoffeeShopName()
  {
    return coffeeShopName;
  }
   
  //Have the user input the coffeeShopCity String.
  protected void setCoffeeShopCity()
  {
    System.out.print("Enter the city for said cafe or coffee shop: ");
    coffeeShopCity = input.nextLine().substring(0);
  }
   
  //Return the coffeeShopCity String.
  protected String getCoffeeShopCity()
  {
    return coffeeShopCity;
  }
   
  //Have the user input the coffeeShopState String.
  protected void setCoffeeShopState()
  {
    System.out.print("Enter the state or region of this cafe or coffee shop: ");
    coffeeShopState = input.nextLine().substring(0);
  }
   
  //Return the coffeeShopState String.
  protected String getCoffeeShopState()
  {
    return coffeeShopState;
  }
   
  //Have the user input the coffeeShopCountrye String.
  protected void setCoffeeShopCountry()
  {
    System.out.print("Enter the country of your cafe or coffee shop: ");
    coffeeShopCountry = input.nextLine().substring(0);
  } 
   
  //Return the coffeeShopCountry String.
  protected String getCoffeeShopCountry()
  {
    return coffeeShopCountry;
  }
   
  //Have the user input the coffeeBean String.
  protected void setCoffeeBean()
  {
    System.out.print("Enter the country of origin of the coffee beans: ");
    coffeeBean = input.nextLine().substring(0);
  } 
   
  //Return the coffeeBean String.
  protected String getCoffeeBean()
  {
    return coffeeBean;
  }
   
  //Method to prompt the user to input a date and check that it is in the "MM/dd/yyyy HH:mm a" format, then parse this time as the coffeeBrewTime.
  protected void setCoffeeBrewTime()
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy hh:mm a");
    dateFormat.setLenient(false);
    System.out.print("\nWhen was your coffee brewed?\n Please input a date, time and AM or PM:\n e.g. 04/16/2014 10:00 AM: ");
     
    while (true)//A while to loop to allow the user to make a choice with type/error catching.
    {
      //Try to parse the date submitted by the user.
      try
      {
        coffeeBrewTime = dateFormat.parse(input.nextLine());
        break;
      }
      catch(ParseException e) 
      {
        System.out.print("\nOops that format is not acceptable.\n Please try again by inputing a date, time and AM or PM.\n e.g. 04/16/2014 10:00 AM: ");
        continue;
      }
    } 
  }
   
  //Return the coffeeBrewTime Date object.
  protected Date getCoffeeBrewTime()
  {
    return coffeeBrewTime;
  }
   
  //Method to prompt the user to choose their coffee's brew method and set the brewMethod String to their choice.
  protected void setBrewMethod()
  {
    System.out.println("\nHow was your coffee brewed?: ");
    System.out.println("Please choose your method by entering the appropriate number:");
    System.out.println(" Enter 1: Drip");
    System.out.println(" Enter 2: French Press");
    System.out.println(" Enter 3: Pour Over");
    System.out.println(" Enter 4: Cold Pressed");
    System.out.println(" Enter 5: Iced");
    
    //Use the CoffeeApplication.setChoice method to assign the user choice value to the choice int.
    choice = CoffeeApplication.setChoice(1,5);
    
    //Switch block where the user's choice is converted to a string and set to the brewMethod.
    switch (choice)
    {
      case 1:
        brewMethod = "Drip"; 
        break;
      case 2:
        brewMethod = "French Press";
        break;
      case 3: 
        brewMethod = "Pour Over";
        break;
      case 4:
        brewMethod = "Cold Brewed";
        break;
      case 5:
        brewMethod = "Iced";
        break;
     }
  }
   
  //Return the coffeeBean String.
  protected String getBrewMethod()
  {
    return brewMethod;
  } 
  
  //Method to set the temperature of the coffee in degrees Celcius, first asking for the temperature scale.
  protected void setTemperature() 
  {
    //Check the brewMethod and see if it falls in the range of 'hot' coffees.
    if (brewMethod.equalsIgnoreCase("drip") || brewMethod.equalsIgnoreCase("french press") || brewMethod.equalsIgnoreCase("pour over") || brewMethod.equalsIgnoreCase("cold press")) 
    {
      System.out.println("\nPlease input your coffee's estimated temperature, but first we need the scale.");
      System.out.println("Which temperature scale would you like to use Fahrenheit or Celcius");
      System.out.println("Please choose your scale by entering the appropriate number:");
      System.out.println(" Enter 1: Fahrenheit");
      System.out.println(" Enter 2: Celcius");
      
      //Use the CoffeeApplication.setChoice method to assign the user choice value to the choice int.
      choice = CoffeeApplication.setChoice(1,2);
      
      //For Fahrenheit temperatures
      if (choice == 1) 
      {
        while (true)//A while to loop to allow the user to make a choice with type/error catching.
        {
          //Have the user enter an integer and check if it is an integer and in the correct range.
          System.out.print("Please input your coffee's estimated temperature in degrees Fahrenheit, which will be converted to Celcius: ");
         //Try and catch block to help with line delimiter issues returning newline characters.
         try 
         {
           double doubleTemperature = Double.parseDouble(input.nextLine()); //Have the user input the temperature as a double.
           temperature = (int)(doubleTemperature - 32) * 5/9; //Convert the Fahrenheit to Celcius and cast as an integer.
         } 
         catch (NumberFormatException e) 
         {
           System.out.print(" Woah, that doesn't look like a temperature for a cup of coffee. ");
           input.nextLine(); //Ask for an input of any type and then loop again to see if it is an double.
           continue;
         }
         //Check that the temperature is reasonable for a cup of coffee.
         if (temperature < 1 || temperature > 120)
         {
           System.out.print(" Woah, that doesn't look like the right temperature for a cup of coffee. ");
           continue;
         }
         //Use the Rebrew method to see if the coffee is the correct temperature.  
         Rebrew(temperature);
         break;
        }
      }
      //For Celsius temperatures
      else if (choice == 2)
      {
        while (true)//A while to loop to allow the user to make a choice with type/error catching.
        {
          //Have the user enter an integer and check if it is an integer and in the correct range.
          System.out.print("Please input your coffee's estimated temperature in degrees Fahrenheit, which will be converted to Celcius: ");
         //Try and catch block to help with line delimiter issues returning newline characters.
         try 
         {
           temperature = (int)(Double.parseDouble(input.nextLine())); //Have the user input the temperature as a double and cast int.
         } 
         catch (NumberFormatException e) 
         {
           System.out.print(" Woah, that doesn't look like a temperature for a cup of coffee. ");
           input.nextLine(); //Ask for an input of any type and then loop again to see if it is an integer.
           continue;
         }
         //Check that the temperature is reasonable for a cup of coffee.
         if (temperature < 1 || temperature > 120)
         {
           System.out.print(" Woah, that doesn't look like the right temperature for a cup of coffee. ");
           continue;
         }
         //Use the Rebrew method to see if the coffee is the correct temperature.  
         Rebrew(temperature);
         break;
        }
      }
    }
    //If the coffee brewMethod() is either 'cold brewed' or 'iced,' ignore the temperature, thus keeping it at 0 degrees Celcius.
    else if (brewMethod.equalsIgnoreCase("cold brewed"))
    {
      System.out.println("Your coffee is cold brewed, so no need to worry about the temperature.");
      temperature = 0;
    }
    else
    {
      System.out.println("Your coffee is iced, so no need to worry about the temperature.");
      temperature = 0;
    }
  } 
  
  //Return the temperature double.
  protected double getTemperature()
  {
    return temperature;
  }
  
  //Method to check if the user would like to rebrew their hot coffee if it is not in the proper hot coffee temperature range.
  protected void Rebrew(int temperature)
  {
    //Check the temperature range.
    if (temperature < 90 || temperature > 96)
    {
      System.out.println("\nThe ideal coffee temperature is between 92 and 96 degrees Celsius.");
      System.out.println(" But your coffee's temperature is " + temperature + " degrees Celsius.");
      System.out.println(" Would you like to rebrew?");
      System.out.println(" Enter 1: Yes");
      System.out.println(" Enter 2: No");
      
      //Use the CoffeeApplication.setChoice method to assign the user choice value to the choice int.
      choice = CoffeeApplication.setChoice(1,2);
      
      //If yes to rebrewing the coffee, restart the setTemperature method.
      if (choice == 1)
      {
        setTemperature();
      }
      //If no to rebrew, continue with funky coffee.
      else if (choice == 2)
      {
        System.out.println("Ok, but your coffee might taste a bit funky...");
      }
    }
  }
   
  //Method to retrieve and check price of coffee
  protected void setPrice()
  { 
    while (true)//A while to loop to allow the user to make a choice with type/error catching.
    {
      //Have the user enter an integer and check if it is an integer and in the correct range.
      System.out.print("\nPlease input the cost of your coffee in dollars without the $ symbol (e.g. 1.33): ");
      //Try and catch block to help with line delimiter issues returning newline characters.
      try 
      {
        price = Double.parseDouble(input.nextLine());
      } 
      catch (NumberFormatException e) 
      {
        System.out.print(" Woah, that doesn't look like a price for a cup of coffee. ");
        input.nextLine(); //Ask for an input of any type and then loop again to see if it is an double.
        continue;
      }
      //price is then checked to see if it fits within a reasonable price range for a cup of coffee
      if (price <= 0 || price >= 50)
      {
       System.out.print(" Woah, that doesn't look like a price for a cup of coffee. ");;
       continue;
      }
    break;
    } 
  }
  
  //Return the price double.
  protected double getPrice()
  {
    return price;
  }
  
  //Method to determine how much each milliliter of coffee costs.
  protected void setPricePerOz(double price)
  {
    // Set a double variable to store the volume in fluid ounces
    double oz = 0;
    
    System.out.println("\nPlease input your coffee's estimated volume.");
    System.out.println("But first will your volume be in milliliter or fluid ounces?");
    System.out.println(" Enter 1: Milliliters");
    System.out.println(" Enter 2: Fluid Ounces");
    
    //Use the CoffeeApplication.setChoice method to assign the user choice value to the choice int.
    choice = CoffeeApplication.setChoice(1,2);
    
    if (choice == 1) //if milliliters
    {
      while (true)//A while to loop to allow the user to make a choice with type/error catching.
      {
        System.out.print("Please input your coffee's estimated volume in milliliters, which will be converted to fluid ounces: ");
        //Try and catch block to help with line delimiter issues returning newline characters.
        try 
        {
          oz = Double.parseDouble(input.nextLine())* 0.033814; //Oz is set to the double input by the user with a conversion from milliliters to fluid ounces.
        } 
        catch (NumberFormatException e) 
        {
          System.out.print(" Woah, that doesn't look like a volume for a cup of coffee. ");
          input.nextLine(); //Ask for an input of any type and then loop again to see if it is an double.
          continue;
        }
        //Oz is then checked to see if it fits within a reasonable volume range for a cup of coffee
        if (oz <= 0 || oz >= 30)
        {
         System.out.print(" Woah, that doesn't look like the right amount in milliliters for a cup of coffee. ");;
         continue;
        }
      break;
      } 
    }
    else if (choice == 2) //if fluid ounces
    {
    
      while (true)//A while to loop to allow the user to make a choice with type/error catching.
      {
        System.out.print("Please input your coffee's estimated volume in milliliters, which will be converted to fluid ounces: ");
        //Try and catch block to help with line delimiter issues returning newline characters.
        try 
        {
          oz = Double.parseDouble(input.nextLine()); //Oz is set to the double input by the user.
        } 
        catch (NumberFormatException e) 
        {
          System.out.print(" Woah, that doesn't look like a volume for a cup of coffee. ");
          input.nextLine(); //Ask for an input of any type and then loop again to see if it is an double.
          continue;
        }
        //Oz is then checked to see if it fits within a reasonable volume range for a cup of coffee
        if (oz <= 0 || oz >= 30)
        {
         System.out.print(" Woah, that doesn't look like the right amount in milliliters for a cup of coffee. ");;
         continue;
        }
      break;
      }
    }
    //Using the oz volume variable, calculate the pricePerOz of the cup of coffee.
    pricePerOz = (double)Math.round((price / oz) * 100) / 100;
  }
  
  //Return the pricePerOz double.
  protected double getPricePerOz()
  {
    return pricePerOz;
  }

  protected int setRating(String ratingType)
  {
   while (true)//A while to loop to allow the user to make a choice with type/error catching.
   {
     //Have the user enter an integer and check if it is an integer and in the correct range.
     System.out.print("Enter the coffee's " + ratingType + ": ");
     //Try and catch block to help with line delimiter issues returning newline characters.
     try 
     {
       rating = Integer.parseInt(input.nextLine());//Since hasNextInt() returned true from above, choice is set to the int input by the user.
     } 
     catch (NumberFormatException e) 
     {
       System.out.print(" Oops, rating was not skipped with a 0 or an integer between 1 - 5: ");
       input.nextLine(); //Ask for an input of any type and then loop again to see if it is an integer.
       continue;
     }
     //Check that the rating is between 0 and 5.
     if (rating < 0 || rating > 5)
     {
       System.out.print(" Oops, rating was not skipped with a 0 or an integer between 1 - 5. ");
       continue;
     }
   break;
   }
  return rating;
  }
  

  //Method to set the bitterness integer rating.
  protected  void setBitterness()
  {
    bitterness = setRating("bitterness");
  }
  
  //Return the bitterness integer.
  protected  int getBitterness()
  {
    return bitterness;
  }
  
  //Method to set the acidity integer rating.
  protected  void setAcidity()
  {
    acidity = setRating("acidity");
  }
  
  //Return the acidity integer.
  protected  int getAcidity()
  {
    return acidity;
  }
  
  //Method to set the fragrance integer rating.
  protected  void setFragrance()
  {
    fragrance = setRating("fragrance");
  }
  
  //Return the fragrance integer.
  protected  int getFragrance()
  {
    return fragrance;
  }
  
  //Method to set the flavor integer rating.
  protected  void setFlavor()
  {
    flavor = setRating("flavor");
  }
  
  //Return the flavor integer.
  protected  int getFlavor()
  {
    return flavor;
  }

//Method to set the aftertaste integer rating.
  protected  void setAftertaste()
  {
    aftertaste = setRating("aftertaste");
  }
  
  //Return the aftertaste integer.
  protected  int getAftertaste()
  {
    return aftertaste;
  }
  
  //Method to set the body integer rating.
  protected  void setBody()
  {
    body = setRating("body");
  }
  
  //Return the body integer.
  protected  int getBody()
  {
    return body;
  }

//Method to set the sweetness integer rating.
  protected  void setSweetness()
  {
    sweetness = setRating("sweetness");
  }
  
  //Return the sweetness integer.
  protected  int getSweetness()
  {
    return sweetness;
  }
  
  //Method to set the CleanCup integer rating.
  protected  void setCleanCup()
  {
    cleanCup = setRating("cleanCup");
  }
  
  //Return the cleanCup integer.
  protected  int getCleanCup()
  {
    return cleanCup;
  }
  
  //Method to create an array made of the coffe rating integers.
  protected void setCoffeeRatingList()
  {
    coffeeRatingList[0] = bitterness;
    coffeeRatingList[1] = acidity;
    coffeeRatingList[2] = fragrance;
    coffeeRatingList[3] = flavor;
    coffeeRatingList[4] = aftertaste;
    coffeeRatingList[5] = body;
    coffeeRatingList[6] = sweetness;
    coffeeRatingList[7] = cleanCup;
  }
  
  //Return the coffeeRatingList integer array.
  protected int[] getCoffeeRatingList()
  {
    return coffeeRatingList;
  }
  
  //start getAverageRating - calculates average of all numbers except 0
  protected void setAverage()
  {
    int i = 0, sum = 0, count = 0; //Declare a few temperature method variables for calculating the average.
    //Go through the list of rating data fields.
    for (i = 0; i < coffeeRatingList.length; i++)
    {
      if (coffeeRatingList[i] > 0) //Check that the rating was not skipped with a 0.
      {
         sum = coffeeRatingList[i] + sum; //Add the non-zero rating up
         count++;
      }
    }
    average = Math.round(((double)sum / count) * 100.00) / 100.00; //Sum the non-zero ratings.
    System.out.print("Your average rating for this cup is: " + average + "\n");
  }
  
  //Method to return the average rating for the tasting.
  protected double getAverage()
  {
    return average;
  }
  
 //Method to have the user input the directory where they would like to save their Tasting Reports.
  protected void setTastingDirectory()
  {
    System.out.println("Enter the directory where you would like your Tasting Report to be saved.");
    System.out.print(" (e.g. C:\\Users\\Steve\\Documents\\Tasting Reports\\: ");
    while (true) //A while to loop to allow the user to make a choice with type/error catching.
    {
      File tempDirectory = new File(input.nextLine()); //Have the user input a directory as a File object.
      if (tempDirectory.exists()) //Make sure this directory exists.
      {
        tastingDirectory = tempDirectory; //Set this existing directory to a tempDirectory File object.
        break;
      }
      else //If the directory doesn't exist retry the loop.
      {
        System.out.println("Oops, that doesn't look like a file directory.  Please try again.");
        System.out.println("Enter the directory where you would like your Tasting Report to be saved.");
        System.out.print(" e.g. C:\\Users\\Steve\\Documents\\Tasting Reports\\ : ");
        continue;
      }
    }
  }
  
  //Method to return the tastingDirectory File object.
  protected File getTastingDirectory()
  {
    return tastingDirectory;
  }
  
  //Method to write Tasting object to a text file.
  protected void writeNewTasting()
  {
    //Try to write the contents of the Tasting object to file, catching any exceptions.
    try 
    {
       //Create a timeStamp String using the SimpleDateFormat method of Date and the Calendar class' getInstance() method to get the current time.
       String timeStamp = new SimpleDateFormat("HH-mm-ss  MM-dd-yyyy").format(Calendar.getInstance().getTime());
       //Create a File object with the file name Tasting Report and the timestampt from above.
       File tastingReport = new File(tastingDirectory, "Tasting Report - " + timeStamp + ".txt");
       //Use the BufferedWriter class to write the values of the Tasting object to a FileWriter class, which will write to an actual File object.
       BufferedWriter output = new BufferedWriter(new FileWriter(tastingReport));
       
       //Write the following entries via the FileWrite class to the tastingReport File object.
       output.write("ABOUT THE TASTER:\r\n");
       output.write("Name: " + userName + "\r\n");
       output.write("Coffee Shop: " + coffeeShopName + "\r\n");
       output.write("City: " + coffeeShopCity + "\r\n");
       output.write("State: " + coffeeShopState + "\r\n");
       output.write("Country: " + coffeeShopCountry + "\r\n\r\n");
        
       output.write("ABOUT THE COFFEE:\r\n");
       output.write("Date Brewed: " + coffeeBrewTime + "\r\n");
       output.write("Origin/Type of bean: " + coffeeBean + "\r\n");
       output.write("Brew Method: " + brewMethod + "\r\n");
       output.write("Temperature: " + temperature + " degrees Celsius\r\n");
       output.write("Total cost of cup: " + fmt1.format(price) + "\r\n");
       output.write("Cost Per Fluid Ounce: " + fmt1.format(pricePerOz) + "\r\n\r\n");
        
       output.write("RATINGS ON A SCALE OF 1 TO 5:\r\n");
       output.write("Bitterness: " + bitterness + "\r\n");
       output.write("Acidity: " + acidity + "\r\n");
       output.write("Fragrance: " + fragrance + "\r\n");
       output.write("Flavor: " + flavor + "\r\n");
       output.write("Aftertaste: " + aftertaste + "\r\n");
       output.write("Body: " + body + "\r\n");
       output.write("Sweetness: " + sweetness + "\r\n");
       output.write("Clean Cup: " + cleanCup + "\r\n\r\n");
        
       output.write("Overall Rating: " + average + "\r\n");
       
       //Close the BufferedWriter object.
       output.close();
     }
     
     //If any exceptions are found, print the stack trace for debugging purposes.
     catch ( IOException e ) 
     {
     e.printStackTrace();
     }
   }
  
  //Method to print a Tasting objects to the console with numbered datafields for editing purposes.
  //This is done using the 'getter' methods contained above.
  protected void printTasting()
  {
    System.out.println();
    System.out.println("ABOUT THE TASTER:");
    System.out.println("1. Name: " + getUserName());
    System.out.println("2. Coffee Shop: " + getCoffeeShopName());
    System.out.println("3. City: " + getCoffeeShopCity());
    System.out.println("4. State: " + getCoffeeShopState());
    System.out.println("5. Country: " + getCoffeeShopCountry());
    
    System.out.println(); //Some extra spaceing
        
    System.out.println("ABOUT THE COFFEE:");
    System.out.println("6. Date Brewed: " + getCoffeeBrewTime());
    System.out.println("7. Origin/Type of bean: " + getCoffeeBean());
    System.out.println("8. Brew Method: " + getBrewMethod());
    System.out.println("9. Temperature: " + getTemperature() + " degrees Celsius");
    System.out.println("10. Total cost of cup: " + fmt1.format(getPrice()));
    System.out.println("11. Cost Per Fluid Ounce: " + fmt1.format(getPricePerOz()));
    
    System.out.println(); //Some extra spaceing
        
    System.out.println("RATINGS ON A SCALE OF 1 TO 5:");
    System.out.println("12. Bitterness: " + getBitterness());
    System.out.println("13. Acidity: " + getAcidity());
    System.out.println("14. Fragrance: " + getFragrance());
    System.out.println("15. Flavor: " + getFlavor());
    System.out.println("16. Aftertaste: " + getAftertaste());
    System.out.println("17. Body: " + getBody());
    System.out.println("18. Sweetness: " + getSweetness());
    System.out.println("19. Clean Cup: " + getCleanCup());
    
    System.out.println(); //Some extra spaceing
        
    System.out.println("Overall Rating: " + getAverage());
    
    System.out.println(); //Some extra spaceing
       
  }
  
  //Method to overwrite an existing text file with a new Tasting object.
   protected void overWriteTasting(File FileToBeOverwritten)
   {    
        
    //Overwrite the File object argument with the below Tasting object datafields.
    try 
    {
      //Use the BufferedWriter class to write the values of the Tasting object to a FileWriter class, which will write to an actual File object.
      BufferedWriter output = new BufferedWriter(new FileWriter(FileToBeOverwritten));
      
      output.write("ABOUT THE TASTER:\r\n");
      output.write("Name: " + userName + "\r\n");
      output.write("Coffee Shop: " + coffeeShopName + "\r\n");
      output.write("City: " + coffeeShopCity + "\r\n");
      output.write("State: " + coffeeShopState + "\r\n");
      output.write("Country: " + coffeeShopCountry + "\r\n\r\n");
        
      output.write("ABOUT THE COFFEE:\r\n");
      output.write("Date Brewed: " + coffeeBrewTime + "\r\n");
      output.write("Origin/Type of bean: " + coffeeBean + "\r\n");
      output.write("Brew Method: " + brewMethod + "\r\n");
      output.write("Temperature: " + temperature + " degrees Celsius\r\n");
      output.write("Total cost of cup: " + fmt1.format(price) + "\r\n");
      output.write("Cost Per Fluid Ounce: " + fmt1.format(pricePerOz) + "\r\n\r\n");
        
      output.write("RATINGS ON A SCALE OF 1 TO 5:\r\n");
      output.write("Bitterness: " + bitterness + "\r\n");
      output.write("Acidity: " + acidity + "\r\n");
      output.write("Fragrance: " + fragrance + "\r\n");
      output.write("Flavor: " + flavor + "\r\n");
      output.write("Aftertaste: " + aftertaste + "\r\n");
      output.write("Body: " + body + "\r\n");
      output.write("Sweetness: " + sweetness + "\r\n");
      output.write("Clean Cup: " + cleanCup + "\r\n\r\n");
        
      output.write("Overall Rating: " + average + "\r\n");
      
      //Close the BufferedWriter object.
      output.close();
    }
    
    //If any exceptions are found, print the stack trace for debugging purposes.
    catch ( IOException e ) 
    {
    e.printStackTrace();
    }
  }
  
  //Method to allow the user to edit a Tasting text file after one is printed using the method above.
  protected boolean editTasting()
  {
    //A boolean to check if the user would like to continue editing and thus reprint the tasting report.
    boolean reprint = false;
    
    //A boolean to check if the user would like to save their changes by overwriting the tasting report being edited.
    boolean overWrite = false;
    
    System.out.println("What would like to edit in this tasting?");
    
    while (true)//A while to loop to allow the user to continue after editing one entry.
    {
      //Reprint the tasting if the user want's to conintue editing.
      if (reprint)
      { 
        //Print out the contents of the Tasting, so that the user can choose what to edit.
        printTasting();
      }

      System.out.println(" Enter the number of the entry that you would like to change.  The entries will be refreshed after you make your selection. ");
      System.out.println(" Or enter 0 to exit with the option to save your changes: ");
      
      //Use the CoffeeApplication.setChoice method to assign the user choice value to the choice int.
      choice = CoffeeApplication.setChoice(0,19);

      switch (choice)
      {
        case 0: //The user has chosen to exit the editTasting method.
          if (reprint == false) //If reprint is false, then no changes have been made, so just exit.
          {
            System.out.println("No changes were made.  Thanks for looking.  Exiting.");
            break;
          }
          else //If reprint is true then changes have been made.
          {
            printTasting(); //Print the tasting report with the new changes for the user's review.
            System.out.println("Please review the above entries in the tasting file.");
            System.out.println("Would you like to save your changes?");
            System.out.println(" Enter 1: Yes, save my changes.");
            System.out.println(" Enter 0: No, exit the editing menu.");
            
            //Use the CoffeeApplication.setChoice method to assign the user choice value to the choice int.
            choice = CoffeeApplication.setChoice(0,1);

            if (choice == 0) //If no, do not save the changes by overwriting the file.
            {
              System.out.println("No changes will be saved. Thanks for looking. Exiting");
              break;
            }
            else if (choice == 1) //If yes, overwrite the text file Tasting Report by setting the overWrite boolean to true and returning it.
            {
              overWrite = true;
              System.out.println("Overwriting...");
              System.out.println("Your tasting file is now updated.  Thanks for the edit.  Exiting.");
              return overWrite;
            }
          }
          break;
        
        //Continues are used after each case to loop the editing menu, should the user wish to edit multiple entries.
        //The reprint boolean is set to true after each switch case so that the user can see their edits in updated print out of the Tasting.
        case 1: //Edit the username.
          setUserName();
          reprint = true;
          continue;
        case 2: //Edit the coffeeShopName.
          setCoffeeShopName();
          reprint = true;
          continue;
        case 3: //Edit the coffeeShopCity.
          setCoffeeShopCity();
          reprint = true;
          continue;
        case 4: //Edit the coffeeShopState.
          setCoffeeShopState();
          reprint = true;
          continue;
        case 5: //Edit the coffeeShopCountry.
          setCoffeeShopCountry();
          continue;
        case 6: //Edit the coffeeBrewTime
          setCoffeeBrewTime();
          reprint = true;
          continue;
        case 7: //Edit the type of coffee bean.
          setCoffeeBean(); 
          reprint = true;
          continue;
        case 8: //Edit the brewMethod, which will require a new temperature so setTemperature as well.
          setBrewMethod();
          setTemperature();
          reprint = true;
          continue;
        case 9: //Edit the coffee's temperature.
          setTemperature();
          reprint = true;
          continue;
        case 10: //Edit the coffee cup's price.
          double tempOz = getPrice()/getPricePerOz();
          setPrice();
          pricePerOz = (double)Math.round((price / tempOz) * 100) / 100;
          reprint = true;
          continue;
        case 11: //Edit the pricePerOz of the coffee, which will require getting the price of the coffee.
          setPricePerOz(getPrice());
          reprint = true;
          continue;
          
        //Edit the coffee ratings, all of which require pulling the current ratings into the coffeeRatingList array and recalculating the average rating.
        case 12:
          setBitterness();
          setCoffeeRatingList();
          setAverage();
          reprint = true;
          continue;
        case 13:
          setAcidity();
          setCoffeeRatingList();
          setAverage();
          reprint = true;
          continue;
        case 14: 
          setFragrance();
          setCoffeeRatingList();
          setAverage();
          reprint = true;
          continue;
        case 15:
          setFlavor();
          setCoffeeRatingList();
          setAverage();
          reprint = true;
          continue;
        case 16:
          setAftertaste();
          setCoffeeRatingList();
          setAverage();
          reprint = true;
          continue;
        case 17:
          setBody();
          setCoffeeRatingList();
          setAverage();
          reprint = true;
          continue;
        case 18: 
          setSweetness();
          setCoffeeRatingList();
          setAverage();
          reprint = true;
          continue;
        case 19:
          setCleanCup();
          setCoffeeRatingList();
          setAverage();
          reprint = true;
          continue;      
       }
       break; //If the user chose 0 to exit and reprint was false due to no edits being made, break out of the while loop.
     }
     return overWrite;  //If this boolean is returned true the overWrite method will be run in CoffeApplication to overwrite the file being editted.
  }
} 