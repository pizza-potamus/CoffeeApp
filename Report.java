import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;


/* Report Class - author Andrea Zemp - date last modified 12-1-2014
   The report class runs reports on the Coffee Tasting text files. It provides the data aggregation functions of the application.
*/
class Report
{
   static final Scanner input = new Scanner(System.in);
   
   private Date fromDate = new Date();
   private Date toDate = new Date();
   


//Report Method - Default constructor for Report class   
   Report()
   {
   }



/* report 1 Method 
   Purpose - Pricing Report
   1. Totals the amount spent between two user provided dates
   2. Finds the most expensive and least expensive cups purchased between the two user provided dates
*/ 
   protected void report1()
   {
     //call LoadFiles.loadforReport() method to create an array of the text file names
     File [] textFileNames = LoadFiles.loadForReport();
          
     //Create/initialize double variable for the total amount spent on coffee     
     double total = 0;
     NumberFormat fmt1 = NumberFormat.getCurrencyInstance();      
   
     //Create/initialize most expensive cup variables
     int indexMostExpensive = 0;
     double highestCost = 0, highestTotalCost= 0, temp = 0;
     String highestCafe = "", highestCity = "", highestState = "";
     
     //Create/initialize least expensive cup variables
     int indexLeastExpensive = 0;
     double lowestCost = 0, lowestTotalCost= 0;
     String lowestCafe = "", lowestCity = "", lowestState = "";
     
     //Create/initialize integer to count the number of times through the following if statements 
         //to determine if no text files fall within query dates
     int count = 0;
    
     //For loop cycles through textFileNames arrayList to create a String array of the text file at element (i)    
     for (int i=0; i< textFileNames.length; i++)
     {
          ArrayList textFile = new ArrayList <>();
          textFile = LoadFiles.createTextArray(i, textFileNames); 
          //Create/initialize boolean to check if each text file is dated between the two query dates   
          boolean fitsDateQuery = dateQuery(textFile); 
        
          //temp variable stores the textFile's price per oz info 
          temp = Double.parseDouble(textFile.get(11).toString()); 
          
          //If the textFile's tasting date falls within the two query dates run
          if (fitsDateQuery)
          {
            count ++;
            
            //Total is the total amount spent between the query dates
            total += Double.parseDouble(textFile.get(10).toString());
            
            //If the temp variable (text file's price per oz) is greater than the highest cost cup, update highest cup variables
            if ( temp > highestCost)
            {
               highestCost = temp;          
               highestTotalCost = Double.parseDouble(textFile.get(10).toString()); 
               highestCafe = textFile.get(1).toString();
               highestCity = textFile.get(2).toString();
               highestState = textFile.get(3).toString();
            }//if stmt
            
            else if (temp < highestCost)
            {
               lowestCost = temp;          
               lowestTotalCost = Double.parseDouble(textFile.get(10).toString()); 
               lowestCafe = textFile.get(1).toString();
               lowestCity = textFile.get(2).toString();
               lowestState = textFile.get(3).toString();
            }//if stmt
            
            //Clear the textFile arrayList to ensure no problems will be encountered when refilling it with the next text file's data
            textFile.clear();
         
         }//if fitsDateQuery
      }//for
   
      //If no text files fall within query range run the following code
      if (count == 0)
         System.out.print("You did not file any tastings between " + this.fromDate + " and " + this.toDate + ". Good Bye!");           
      
      //Else print the total amount spent and info concerning the most expensive and least expensive cups
      else
      {
         System.out.println("\nFrom " + this.fromDate + " to " + this.toDate + ": ");
         System.out.println("The total amount spent on coffee was " + fmt1.format(total) + ".\n");
         System.out.println("The most expensive cup was enjoyed at " + highestCafe + " in " + highestCity + ", " + highestState + ".\n" +
                            "            It cost " +  fmt1.format(highestCost) + " per fluid oz and totaled " + fmt1.format(highestTotalCost) + ".");   
         System.out.println("\nThe least expensive cup was enjoyed at " + lowestCafe + " in " + lowestCity + ", " + lowestState + ".\n" +
                            "            It cost " +  fmt1.format(lowestCost) + " per fluid oz and totaled " + fmt1.format(lowestTotalCost) + ".");   
      }                      
   }//report1



/* report 2 Method: Total Cups of Coffee
   Purpose - Total Cups of Coffee Report (Totals number of cups tasted between user provided query dates)

   1. Calls loadForReport() method to create ArrayList of ALL text file names
   2. For loop creates an ArrayList for each text file
   3. If a text file's tasting date falls within the query date range, increments the count of cups
*/
   protected void report2()
   {
      //LoadFiles.loadForReport() creates an array of text file names
      File[] textFileNames = LoadFiles.loadForReport();
 
      //Create/initialize integer to count the number of cups
      int countCups = 0;
      
      //for loop cycles through textFileNames arrayList to create a String array of the text file at element (i)    
      for (int i=0; i< textFileNames.length; i++)
      {
         ArrayList textFile = new ArrayList <>();
         textFile = LoadFiles.createTextArray(i, textFileNames); 
         
         //Create/initialize boolean to check if textFile's tasting date falls within the user's query dates   
            //passes textFile to method dateQuery
         boolean fitsDateQuery = dateQuery(textFile); 
  
         //Increments variable countCups if text file's tasting date falls within the query dates
         if(fitsDateQuery)
         {
            countCups++;
         }
      }//for
      
      //If only 1 tasting was conducted run this code 
      if (countCups == 1)
      {
         System.out.println("\nBetween " + fromDate.toString().substring(4, 10) + " " + fromDate.toString().substring(24) + " and " + 
            toDate.toString().substring(4, 10) + " " + toDate.toString().substring(24)+ " you enjoyed only " + countCups + " cup of coffee!");         
      }
      //If 2 or more tastings were conducted within the date query range run this code. (plural grammar)
      else
      {
         System.out.println("\nBetween " + fromDate.toString().substring(4, 10) + " " + fromDate.toString().substring(24) + " and " + 
            toDate.toString().substring(4, 10) + " " + toDate.toString().substring(24) + " you enjoyed a total of " + countCups + " cups!");
      }
   }//report2    

   

/* report3() Method
   Purpose - Highest and Lowest Rated Coffees Report (finds the highest and lowest rated coffees)
   
   1. Calls loadForReport() method to create ArrayList of ALL text file names
   2. For loop creates an ArrayList for each text file
   3. If a text file's tasting date falls within the query date range, compares that tasting's rating to the highest and lowest ratings
   4. If highest or lowest rated, retrives & prints data from the textFile arraylist
*/
  protected void report3()
  {
      //Calls LoadFiles.loadForReport() to create an arrayList of text file names
      File[] textFileNames = LoadFiles.loadForReport();
   
      NumberFormat fmt1 = NumberFormat.getCurrencyInstance();            
      double temp;
      
      //Create/initialize variables to store highest rated coffee's values    
      double highRating = 0, highCost = 0;
      String highDate = "", highCafe = "", highCity = "", highState = "", highBrew = "", highBeans = ""; 
      
      //Create/initialize variables to store lowest rated coffee's values
      double lowRating = 5, lowCost = 0;
      String lowDate = "", lowCafe = "", lowCity = "", lowState = "", lowBrew = "", lowBeans = "";
       
      //Create/initialize variable that counts whether any text files are dated within the query date range
      int count = 0; 
         
      //For loop cycles through textFileNames ArrayList to create a String array of the text file at element (i)    
      for (int i=0; i< textFileNames.length; i++)
      {
         ArrayList textFile = new ArrayList <>();
         textFile = LoadFiles.createTextArray(i, textFileNames); 
         
         //Create/initialize boolean to check if the textFile is dated between the two query dates   
         boolean fitsDateQuery = dateQuery(textFile); 
  
         if(fitsDateQuery)
         {
            //Retrieve & parse the rating from the textFile arrayList and store it as a double in temp
            temp = Double.parseDouble(textFile.get(22).toString());
            count++;
            
            //If temp variable is higher or lower than the highest or lowest rated coffees retrieve that 
               //coffees data
            if (temp > highRating)
            {
               highRating = temp;
               highDate = textFile.get(6).toString();
               highCafe = textFile.get(1).toString();
               highCity = textFile.get(2).toString();
               highState = textFile.get(3).toString();
               highBrew = textFile.get(8).toString();
               highBeans = textFile.get(7).toString();
               highCost = Double.parseDouble(textFile.get(10).toString());
            }//if
            if (temp < lowRating)
            {   
               lowRating = temp;
               lowDate = textFile.get(6).toString();
               lowCafe = textFile.get(1).toString();
               lowCity = textFile.get(2).toString();
               lowState = textFile.get(3).toString();
               lowBrew = textFile.get(8).toString();
               lowBeans = textFile.get(7).toString();
               lowCost = Double.parseDouble(textFile.get(10).toString());
            } //if    
            //Clear the textFile arrayList to ensure no trouble when it is filled with the next text file's data
            textFile.clear();
         }//if fitsDateQuery
     }//for
  
      //If no text files fall within query range
      if (count == 0)
         System.out.print("You did not file any tastings between " + fromDate + " and " + toDate + ". Good Bye!");           
      
      //If text files were dated within the query range, print the highest and lowest rated coffees info
      else
      { 
         System.out.println("\nThe highest rated coffee rated " + highRating +
         "\n\tYou bought it at " + highCafe + " in " + highCity + ", " + highState + " on " + highDate + ". " +
         "\n\tThe beans were from " + highBeans +
         " and it was brewed as: " + highBrew + "." +
         "\n\tThat was the best " + fmt1.format(highCost) + " you've ever spent!");
         
         System.out.println("\nThe lowest rated coffee rated " + lowRating +
         "\n\tYou bought it at " + lowCafe + " in " + lowCity + ", " + lowState + " on " + lowDate + ". " +
         "\n\tThe beans were from " + lowBeans +
         " and it was brewed as: " + lowBrew + "." +
         "\n\tThat was a waste of " + fmt1.format(lowCost) + "! Don't order that again!");
      }//else
   }//report3 
   

 
/* report 4 Method
   Purpose - Locations Report
   1. Creates an ArrayList of arrays that stores the info for each coffee shop visited
   2. Sums the number of times each cafe was visited
   3. Provides the average rating of all coffees ordered at each location
   4. Prints chart showing all locations ordered from visits to least visits
*/
   protected void report4()
   {
      //Create ArrayList of String arrays.Each String array pertains to 1 text file of a tasting report
       //Each String array will contain 5 elements (0. cafe, 1. city, 2. state, 3. country, 4. rating, 5. number of visits 
      ArrayList <String[]> locations = new ArrayList <String[]>();
      
      //Use the LoadFiles.loadForReport method to create an array of text file names
      File[] textFileNames = LoadFiles.loadForReport();
      
      //Create a String variable in which the cafe name will be stored
      String temp = "";

      //Create a boolean that will be true if the cafe name is already stored in the ArrayList(of arrays), 
         //and false if it is the first occurence of that cafe name
      boolean check;
      
      //For loop cycles through textFileNames array to create a String array of the text file at element [i]    
      for (int i=0; i< textFileNames.length; i++)
      {
         //create ArraList to store each text file's data and initialize using the LoadFiles.createTextArray() method
         ArrayList textFile = new ArrayList <>();
         textFile = LoadFiles.createTextArray(i, textFileNames); 
         
         //Create a String array for each text file. contain 5 elements (0. cafe, 1. city, 2. state, 3. country, 4. rating, 5. number of visits 
         //This is the array that will be added to the ArrayList(of arrays) called locations
         String [] data = new String [7];
        
         //Retrieve rating and cost from textFile, parse and store in double variable        
         double rating = Double.parseDouble(textFile.get(22).toString());
         double cost = Double.parseDouble(textFile.get(10).toString());
         
         //Set check to false each time the loop renews
         check = false;
         
         //Initialis temp variable to store the cafe name in
         temp = textFile.get(1).toString(); 
                
         if (i !=0)
         {
            for (int k =0; k < locations.size(); k++)    
            {  
               //If the cafe name stored in temp matches the cafe name stored in any other array, average the ratings stored in element 4 and the number of visits stored in element 5
                     //Switch boolean check to true to indicate that the array was an additional visit to an already listed cafe
               if (temp.equalsIgnoreCase((locations.get(k)[0]).toString()))
               {
                  locations.get(k)[4] = "" + (Math.round((Double.parseDouble(locations.get(k)[4].toString()) + rating)/2 * 100.00) /100.00);
                  locations.get(k)[5] = "" + (Math.round((Double.parseDouble(locations.get(k)[5].toString()) + cost)/2 * 100.00) /100.00);
                  locations.get(k)[6] = "" + (Integer.parseInt(locations.get(k)[6].toString()) + 1);         
                  check = true;
                  break;
               }//if   
            }//for
         }//if (i!=0)
         
         //If value for Cafe is not already stored in the ArrayList(of arrays), add a whole new array to the end
            // of the ArrayList
         if (!check)
         {
            data[0] = textFile.get(1).toString();
            data[1] = textFile.get(2).toString();
            data[2] = textFile.get(3).toString();
            data[3] = textFile.get(4).toString();
            data[4] = "" + rating;
            data[5] = "" + cost;
            data[6] = "1";  
                                           
            locations.add(data);
         }//if !check
         
         //Clear the textFile arrayList to ensure no trouble when it is filled with the next text file's data
         textFile.clear();

      }//for
      
      NumberFormat fmt1 = NumberFormat.getCurrencyInstance();      
      
      //call chooseSortBy method to enable user to choose the variable by which to sort the ArrayList of arrays
      int sortIndex = locationsSortBy();
      
      //sort locations ArrayList of arrays using the sortMegaArray method
      locations = sortMegaArray(sortIndex, 7, locations);
            
      //Print out the whole ArrayList of arrays formatted as a chart using a for loop!
      System.out.println("\nCAFE                           CITY                      STATE      COUNTRY    AVG RATING  AVG PRICE  # VISITS");  
      System.out.println("----------------------------------------------------------------------------------------------------------------");    
      for(String[] data : locations)
      {
         System.out.printf("%-30s %-27s %-11s %-10s %-10s %-11s %-5s\n", data[0], data[1], data[2], data[3], data[4], fmt1.format(Double.parseDouble(data[5])), data[6]);
      }//for       
   }//report4   



/* locationsSortBy method 
   Enables user to choose which index will be used to sort the locations ArrayList of arrays
   Returns int sortIndex
*/  
   protected int locationsSortBy()
   {
      System.out.println("\nEnter the number of the variable by which you would like to sort: "+
                         "\n\t1. Alphabetical by cafe name " +
                         "\n\t2. Average Rating " +
                         "\n\t3. Average Price " +
                         "\n\t4. Number of visits ");       
      //variable that will hold the users choice 
      int sortIndex;
      
      //Make sure the user enters and Int between 1 and 4
      while (true)
      {
         if (!input.hasNextInt())
         {
            System.out.print("Oops, please enter a number between 1 and 4: ");
            input.next();
            continue;
         }
         sortIndex = input.nextInt();
      
         if ((sortIndex < 1) || (sortIndex > 4))
         {
            System.out.print("Please enter a number between 1 and 4 : ");
            continue;
         }            
         break;
      }//while
      
      //Use a switch to change the user's sort choice into the appropriate array index number
      switch (sortIndex)
      {
         case 1 : sortIndex = 0;
            break;
         case 2: sortIndex = 4;
            break;
         case 3 : sortIndex =5;
            break;
         case 4 : sortIndex = 6;
            break;
      }//switch
                         
      return sortIndex;
   }//locationsSortBy



/* report 5 Method
   Purpose - Brew Methods Report
   1. Creates an ArrayList of arrays that stores the info for each Coffee brew method
   2. Sums the number of times each brew method was orderd
   3. Provides the average rating of all cups ordered for each coffee method
   4. Prints chart showing all methods ordered from highest average rating to lowest average rating
*/
   protected void report5()
 {
      //Create ArrayList of String arrays.Each String array pertains to 1 text file of a tasting report
            //Each String array will contain 3 elements (0. brew method, 1. avg rating, 2. number 
      ArrayList <String[]> brewing = new ArrayList <String[]>();
      
      //Use the LoadFiles.loadForReport method to create an array of text file names
      File[] textFileNames = LoadFiles.loadForReport();
      
      //Create a String variable in which the brewing method will be stored
      String temp = "";
      //Create a boolean that will be true if the method is already stored in the ArrayList(of arrays),
         // and false if it is the first occurence of that cafe name
      boolean check;
      
      //For loop cycles through textFileNames array to create a String array of the text file at element [i]    
      for (int i=0; i< textFileNames.length; i++)
      {
         //create ArraList to store each text file's data and initialize using the LoadFiles.createTextArray() method
         ArrayList textFile = new ArrayList <>();
         textFile = LoadFiles.createTextArray(i, textFileNames); 
         
         //Create a String array for each text file. contain 5 elements (0. brew method, 1. rating, 2. number)
         //This is the array that will be added to the ArrayList(of arrays) called brewing
         String [] data = new String [3];
        
         //Retrieve rating from textFile, parse and store in double variable        
         double rating = Double.parseDouble(textFile.get(22).toString());
         
         //Set check to false each time the loop renews
         check = false;
         
         //Initialize temp variable to store the method name in
         temp = textFile.get(8).toString(); 
                
         if (i !=0)
         {
            for (int k =0; k < brewing.size(); k++)    
            {  
               //If the method stored in temp matches the method stored in any other array, average the ratings
                 // stored in element 1 and the number of visits stored in element 2
                  //Switch boolean check to true to indicate that the array already has that brew method listed
               if (temp.equalsIgnoreCase((brewing.get(k)[0]).toString()))
               {
                  brewing.get(k)[1] = "" + (Math.round((Double.parseDouble(brewing.get(k)[1].toString()) + rating)/2 * 100.00) /100.00);
                  brewing.get(k)[2] = "" + (Integer.parseInt(brewing.get(k)[2].toString()) + 1);         
                  check = true;
                  break;
               }//if   
            }//for
         }//if (i!=0)
         
         //If value for brew method is not already stored in the ArrayList(of arrays), add a whole new array to the end of the ArrayList
         if (!check)
         {
            data[0] = textFile.get(8).toString();
            data[1] = "" + rating;
            data[2] = "1";  
                               
            brewing.add(data);
         }//if !check
         
      //Clear the textFile arrayList to ensure no trouble when it is filled with the next text file's data
      textFile.clear();

      }//for

      //Use the sortMegaArray method to sort brewing ArrayList of arrays by the avg rating
      brewing = sortMegaArray(1, 3, brewing);
            
      //Print out the whole ArrayList of arrays formatted as a chart using a for loop!
      System.out.println("\nBrew Method          Avg Rating           Number of Cups");  
      System.out.println  ("--------------------------------------------------------");    
      for(String[] data : brewing)
      {
         System.out.printf("%-20s %-20s %-20s\n", data[0], data[1], data[2]);
      }//for       
   }//report5   



/* report6 Method
   Purpose - to compare cups of coffee at a user specified cafe
   1. Create an array list of arrays
   2. Load only tastings from the user specified cafe into arrays 
   3. Sort arraylist of arrays by rating
   4. Print arrayList of arrays 
*/
   protected void report6()
   {
      //Create ArrayList of String arrays.Each String array pertains to 1 text file of a tasting report
       //Each String array will contain 5 elements (0. cafe, 1. city, 2. state, 3. country, 4. rating, 5. number of visits 
      ArrayList <String[]> cafe = new ArrayList <String[]>();
      
      //Use the LoadFiles.loadForReport method to create an array of text file names
      File[] textFileNames = LoadFiles.loadForReport();
     
     //User specifies cafe   
      System.out.print("Which cafe are you interested in?");
      String selectCafe = input.nextLine();
    
    //comparing selectCafe to "" is the only way I could find to solve this instance of the nextLine() 
      //delimiter problem if this report method is choosen after other reports were run
      if (selectCafe.equals(""))
      {
         selectCafe = input.nextLine();
      }
      
      //Used to count whether any tastings have been filed for the specified cafe
      int cafeCount = 0;
    
      NumberFormat fmt1 = NumberFormat.getCurrencyInstance();      
       
      //For loop cycles through textFileNames array to create a String array of the text file at element [i]    
      for (int i=0; i< textFileNames.length; i++)
      {
         //create ArrayList to store each text file's data and initialize using the LoadFiles.createTextArray() method
         ArrayList textFile = new ArrayList <>();
         textFile = LoadFiles.createTextArray(i, textFileNames); 
         
         //Create a String array for each text file. 
         //This is the array that will be added to the ArrayList(of arrays) called cafe
         String [] data = new String [12];
       
         //If the cafe name found in textFile at element 1 is equal to the user specified cafe add data to array data
         if (textFile.get(1).toString().equalsIgnoreCase(selectCafe))
         {
            data[0] = textFile.get(22).toString();
            data[1] = textFile.get(7).toString();
            data[2] = textFile.get(8).toString();
            data[3] = textFile.get(13).toString();
            data[4] = textFile.get(14).toString();
            data[5] = textFile.get(15).toString();
            data[6] = textFile.get(16).toString();
            data[7] = textFile.get(17).toString();
            data[8] = textFile.get(18).toString();
            data[9] = textFile.get(19).toString();
            data[10] = textFile.get(20).toString();
            data[11] = textFile.get(10).toString();
            
            cafeCount++;
            //add array data to the ArrayList cafe                 
            cafe.add(data);                  
         }//if   
         
         //Clear the textFile arrayList to ensure no trouble when it is filled with the next text file's data
         textFile.clear();
      }//for
      
      //if the Cafe was found in any Tasting Reports proceed
      if (cafeCount > 0)
      {
         //If Cafe was found in more than one Tasting Report sort
         if(cafeCount > 1)
         {
            //calls report6SortBy method to enable user to specify the variable by which they want the cafe
               // arraylist of arrays sorted
            int sortIndex = report6SortBy();
            
            //calls sortMegaArray method to sort the arraylist of arrays
            cafe = sortMegaArray(sortIndex, 12, cafe);
         }//inner if
                             
         //Print out the whole ArrayList of arrays formatted as a chart using a for loop!
         System.out.println("\nCafe Report for " + selectCafe);
         System.out.println("Rating  Beans           Brew Method   Bitterness  Acid  Fragrance  Flavor  Aftertaste  Body  Sweetness  CleanCup  Cost");  
         System.out.println  ("---------------------------------------------------------------------------------------------------------------------------");    
         
         for(String[] data : cafe)
         {
            System.out.printf("%-7s %-15s %-14s %-11s %-9s %-8s %-8s %-8s %-8s %-8s %-8s %-8s\n", data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9],data[10], fmt1.format(Double.parseDouble(data[11])));
         }//for     
      }//outer if
      else
         System.out.println("You have not filed any tastings for " + selectCafe + "!");  
   }//report6  


/* report6SortBy method
   Purpose - To enable user to choose which index will be used to sort the cafe ArrayList of arrays for Report6
   Returns int sortIndex
*/  
   protected int report6SortBy()
   {
      System.out.println("\nEnter the number of the variable by which you would like to sort: "+
                         "\n\t1. Rating " +
                         "\n\t2. Beans " +
                         "\n\t3. Brew Method " +
                         "\n\t4. Cost ");
      //Create a variable by which to store the sorting choice
      int sortIndex;
      
      //Make sure the user inputs a Int between 1 and 4
      while (true)
      {
         if (!input.hasNextInt())
         {
            System.out.print("Oops, please enter a number between 1 and 4: ");
            input.next();
            continue;
         }
         sortIndex = input.nextInt();
      
         if ((sortIndex < 1) || (sortIndex > 4))
         {
            System.out.print("Please enter a number between 1 and 4 : ");
            continue;
         }            
         break;
      }//while

      //Use a switch to assign the appropriate array index number for the user's sort choice
      switch (sortIndex)
      {
         case 1 : sortIndex = 0;
            break;
         case 2: sortIndex = 1;
            break;
         case 3 : sortIndex = 2;
            break;
         case 4 : sortIndex = 11;
            break;
      }//switch
                         
      return sortIndex;
   }//report6SortBy



/* sortMegaArray method
   Purpose -  to sort Arraylist of arrays
   Parameters - int sortBy is the index number by which to sort
                int arrayLength is the length of the array
                ArrayList <String[]> megaArray is the ArrayList of arrays to be sorted
*/   
   protected ArrayList <String[]> sortMegaArray(int sortBy, int arrayLength, ArrayList <String[]> megaArray)
   {
      //for loop to cycle through the ArrayList of arrays
      for (int z = 0; z<megaArray.size()-1; z++)
      {
         //Declare String array and initialize with data from the array at index z in the ArrayList(of arrays)
         String [] currentMax = new String [arrayLength];
         System.arraycopy(megaArray.get(z), 0, currentMax, 0, arrayLength);
         int currentMaxIndex = z;
         
         //for each subsequent array in the ArrayList if the value at the sortBy index excedes the value in the SortBy index of maxArray, replace maxArray
         for (int y = z+1; y < megaArray.size(); y++)
         {
            if (((megaArray.get(y)[sortBy].toString()).compareTo(currentMax[sortBy])) < 0)
            {
               System.arraycopy(megaArray.get(y), 0, currentMax, 0, arrayLength);  
               currentMaxIndex = y;
            }
         }//inner for
         
         //Swap array in the megaArray ArrayList with megaArray.get(currentMaxIndex) if necessary
         if (currentMaxIndex != z)
         {
            System.arraycopy(megaArray.get(z), 0, megaArray.get(currentMaxIndex), 0, arrayLength);
            System.arraycopy(currentMax, 0, megaArray.get(z), 0, arrayLength);
         }
      }//outer for
      return megaArray;
   }//sortMegaArray



// getDate Method
// Purpose - enables user to enter a query date
// 1. Creates Date object called userDate and SimpleDateFormat "MM/dd/yyyy"
// 2. Creates a string variable inwhich to store the user input 
// 3. While user input exists the string is parsed using the SimpleDateFormat and stored in Date userDate
// 4. Uses a try and catch to catch parsing exceptions
// 5. returns Date
   protected Date getDate()  
   {   
      Date userDate = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy");
      dateFormat.setLenient(false);
      String inputDate = input.next();

      while (true)
      {
         try
         {
            userDate = dateFormat.parse(inputDate);
            break;
         }
         catch(ParseException e) 
         {
            System.out.print("\nOops that format is not acceptable.\n Please try again by inputing a date.\n e.g. 04/16/2014: ");
            inputDate = input.next();    
         }
      }//while
      return userDate;
   }//getDate


// setDates Method
// Purpose - User Sets Date Query Dates
// 1. Calls setter for fromDate
// 2. Calls setter for toDate
// 3. Do while loops if the fromDate and toDate are not in chronological order by calling the datesInOrder method
   protected void setDates()
   {
      do{
         this.setFromDate();
         this.setToDate();
      }while(!this.datesInOrder());
   }


//Setter Method for fromDate
   protected void setFromDate()
   {
      System.out.print("Enter the date range in which you want to search. First, enter the from date, eg 04/01/2014: ");
      fromDate = getDate();      
   }
   

//Setter Method for toDate
   protected void setToDate()
   {
        System.out.print("Now enter the to date, eg 05/01/2014: ");
        toDate = getDate();
   }
   

//Getter Method for fromDate
   protected Date getFromDate()
   {
      return fromDate;
   }
   

//Getter Method for toDate
   protected Date getToDate()
   {
      return toDate;
   }


// datesInOrder Method
// Purpose - to check if the dates provided by the user are in chronological order
// 1. Initializes a boolean using the Date class before() method on the variable fromDate & toDate
// 2. If the dates are not in chronological order print error message
// 3. Returns boolean
   protected boolean datesInOrder()
   {
      boolean datesInOrder = (this.fromDate.before(this.toDate));
      if(!datesInOrder)
      {
         System.out.println("The dates you entered are not in chronological order!\nTry again!\n");
      }
      return datesInOrder;
   }


// dateQuery Method
// Purpose - to determine if the text file is dated within the user defined query dates
// 1. Create date object for the date of the tasting is the arrayList
// 2. Set SimpleDateFormat
// 3. try and catch to handle the parsing exception
// 4. Return a boolean that is true if the text file is dated within the query dates 
   protected boolean dateQuery (ArrayList textFile)
   {         
      Date tastingDate = new Date();      
      
      //Declare and initialize tempDate to acquire the tasting date from the textFile arraylist - dates look like "Apr 16 2014"
      String tempDate = textFile.get(6).toString().substring(4, 10) + " " + textFile.get(6).toString().substring(24, 28);    
      
      //set SimpleDateFormat to "MMM dd yyyy" so dates in format "Apr 16 2014" are recognized
      SimpleDateFormat dateFormat1 = new SimpleDateFormat ("MMM dd yyyy");
      dateFormat1.setLenient(false);
 
      try
      {
         //parse the tempDate from the arraylist and assign to Date object tastingDate
         tastingDate = dateFormat1.parse(tempDate);
      }
      catch (ParseException e)
      {
         System.out.println("wrong date format!");
      }
      
      //boolean true if tastingDate from arraylist falls within the query dates
      boolean fitsDateQuery = (tastingDate.compareTo(this.fromDate) >= 0 && tastingDate.compareTo(this.toDate) <= 0);
      
      return fitsDateQuery;
   }//dateQuery
   
      
}//class closing brace