import java.util.*;
import java.text.*;
import java.io.*;
/*
Coffee Application
Programmers: Pierce MacMillan, Andrea Zemp, Michelle Petruska

The main method lives here and here is where we instantiate all our objects.
We start by prompting the user to make a choice as to what they would like to do.
Once the choice is made and the methods run, the prompt will loop should the user wish to continue,
creating tasting reports, editing them, or generating aggregrate reports.

*/
class CoffeeApplication
{ 
   //Good ole Scanner for reading in user inputs in the below switch logic controlled menus.
   static Scanner input = new Scanner(System.in);
     
   //A very important choice integer for the user to choose from various switch logic controlled menus throughout the class.
   static int choice = 0;
   
   public static void main (String[] args)
   {
   
      //Super moderately fun ASCII art to start the program.
      System.out.println("         {              ");
      System.out.println("      {   }             ");
      System.out.println("       }_{ __{          ");
      System.out.println("    .-{   }   }-.       ");
      System.out.println("   (   }     {   )      ");
      System.out.println("   |`-.._____..-'|      ");
      System.out.println("   |             ;--.   ");
      System.out.println("   |            (__  \\  ");
      System.out.println("   |             | )  ) ");
      System.out.println("   |             |/  /  ");
      System.out.println("   |             /  /   ");
      System.out.println("   |            (  /    ");
      System.out.println("   \\             y'     ");
      System.out.println("    `-.._____..-'       ");
      System.out.println();
      System.out.println("Hello Coffee Friend!");
      
      while(true)//The while loop that allows for continuation of the application after the user is done with the their initial choice.
      {
        //For the following switch menu.
        System.out.println("Please make your choice by entering the appropriate number. \n");
        System.out.println(" Enter 1: To create a new tasting report.");
        System.out.println(" Enter 2: To edit an old tasting report.");
        System.out.println(" Enter 3: To generate a specialized report.");
        System.out.println(" Enter 0: To exit.");
        System.out.println();
        
        //Use the setChoice method to assign the user choice value to the choice int.
        choice = setChoice(0,3);
                      
        switch (choice)
        {
          case 0: //Exit the program after the user inputs 0.
            System.out.println("Thanks for looking! Exiting.");
            System.exit(1);
          
          case 1: //Create a tasting after the user inputs 1.
            Tasting CoffeeTasting = new Tasting(); //Instantiate a new Tasting object.
            CoffeeTasting.setTastingDirectory(); //Get the directory where the user would like to save the tasting report.
            CoffeeTasting.setUserName(); //Set the taster's name.
            CoffeeTasting.setCoffeeShopName(); //Set the coffee shop's name.
            CoffeeTasting.setCoffeeShopCity(); //Set the coffee shop's city.
            CoffeeTasting.setCoffeeShopState(); //Set the coffee shop's state. 
            CoffeeTasting.setCoffeeShopCountry(); //Set the coffee shop's country.
            CoffeeTasting.setCoffeeBean(); //Set the type/origin of the coffee bean.
            CoffeeTasting.setCoffeeBrewTime(); //Set the date and time when the coffee was brewed.
            CoffeeTasting.setBrewMethod(); //Set how the coffee was brewed.
            CoffeeTasting.setTemperature(); //Set the coffee's estimated temperature.
            CoffeeTasting.setPrice(); //Set the price for the cup of coffee in question.
            CoffeeTasting.setPricePerOz(CoffeeTasting.getPrice()); //Using the price of the cup as an input, calculate how much the coffee cost per fluid ounce.
          
            System.out.print("\nNow let's start rating your coffee's attributes on an integer scale of 1-5.\n Use the following scale to rate each attribute: \n " +
                             "\n |-----------|------------|\n 1     2     3      4     5\nPoor       Neutral       Ideal");
            System.out.print("\n\nIf you want to omit an attribute from the rating system enter 0.\n\n");            
            
            //Set the ratings on a scale of 1 to 5 for each aspect of a fine cup of coffee.
            CoffeeTasting.setBitterness(); 
            CoffeeTasting.setAcidity();
            CoffeeTasting.setFragrance();
            CoffeeTasting.setFlavor();
            CoffeeTasting.setAftertaste();
            CoffeeTasting.setBody();
            CoffeeTasting.setSweetness();
            CoffeeTasting.setCleanCup();
            CoffeeTasting.setCoffeeRatingList();
            
            System.out.println();//A little extra console space please.
            
            CoffeeTasting.setAverage(); //Calculate and set the average of the cup of coffee.
            
            System.out.println("Your tasting report is complete and has been printed to your chosen directory");
            CoffeeTasting.writeNewTasting(); //Write the Tasting object to a text file in the directory chosen at the beginning of the switch case 1.
            
            break;
          
          case 2: //Edit an existing tasting report after the user inputs 2.
           
            //Instantiate a new object of the class ArrayListOfTextFileNames with the name list. 
            ArrayListOfTextFileNames list = new ArrayListOfTextFileNames();
           
            while (true) //while loop to give the user a chance to try a different directory if the first one is empty.
              {
              //Use the userSetFileDirectory method to have the user input the directory as a File object.
              list.userSetFileDirectory();
            
              //Filter out all files within the directory except text files and and set this new ArrayList
              //to the ArrayListOfTextFileNames.
              list.filterByTXTFiles();
            
              if (list.getArrayListOfTextFileNames().isEmpty())
              {
                System.out.println("Oops, no tasting reports in here. Please try a different directory.");
                continue;
              }
              break;
            }
            
            //Use the choice int to hold the int value returned from chooseArrayListOfTextFileNames();
            choice = list.chooseArrayListOfTextFileNames();
            
            //Check if the user would like to exit with the option choose a different directory.
            if (choice == 0)
            {
              System.out.println("Thanks for looking. Exiting.");
              break;
            }
            //Subtract 1 from choice so that it matches the entries in the ArrayListOfTextFileNames.
            else
            {
              choice = choice - 1;
            }

            //Set the File object fileToEdit using the user's choice.
            File fileToEdit = list.getArrayListOfTextFileNames().get(choice);

            //Instantiate a TastingFileToArrayList converter object.
            TastingFileToArrayList converter = new TastingFileToArrayList();

            //Use the TastingFileToArrayList's convertTastingToArrayList method 
            //to read the text file contents and convert it to an ArrayList.
            ArrayList<String> fileToEditAsArrayList = converter.convertTastingToArrayList(fileToEdit);

            //A boolean to check if the user would like to save their changes by overwriting the tasting report being edited.
            boolean overWrite;
           
            //Instantiate a Tasting object, using the fileToEditAsArrayList ArrayList to populate all the Tasting's datafields.
            Tasting TastingToEdit = new Tasting(fileToEditAsArrayList);
            
            //Use the printTasting() method of Tasting to print the TastingToEdit's data fields to the console as a numbered list.
            TastingToEdit.printTasting();
            
            //The overWrite boolean will hold the boolean chosen by the user as they are asked if they'd like to save their changes and 
            //over write the current tasting report This is a returned value from the editTasting() method of Tasting.
            overWrite = TastingToEdit.editTasting();
            
            if (overWrite)
            {
              TastingToEdit.overWriteTasting(fileToEdit); //Write over the fileToEdit replacing the values that were edited. 
            }
            break;
          
          
          case 3: //Generate a new report after the user inputs 3.
            //Instantiate a new Report object.
            Report report = new Report();
             
            System.out.println("\nThere are 4 specialized report options. Select a report number:");
            System.out.println("Please make your choice by entering the appropriate number. \n"); 
            System.out.println(" Enter 1: Price Report");
            System.out.println(" Enter 2: Total Cups Report");
            System.out.println(" Enter 3: Highest and Lowest Rated Report");
            System.out.println(" Enter 4: Locations Report");
            System.out.println(" Enter 5: Brew Methods Report");
            System.out.println(" Enter 6: Cafe Report\n");

            //Use the setChoice method to assign the user choice value to the choice int.
            choice = setChoice(1,6);
            
            //switch statment directs int choice to the correct report method
            switch (choice)
            {
              case 1: 
                 //Set the dates between which report1 will be run.
                 report.setDates();
                 report.report1();
                 break;
              case 2: 
                 //Set the dates between which report2 will be run.
                 report.setDates();
                 report.report2();
                 break;     
              case 3: 
                 //Set the dates between which report2 will be run.
                 report.setDates();
                 report.report3();
                 break;     
              case 4:
                 report.report4();
                 break; 
              case 5:
                 report.report5();
                 break; 
              case 6:
                 report.report6();
                 break;   
            }
            break;
        }
      
      //Ascertain if the user would like to keep working with the application. 
      System.out.println("\nWould you like to continue creating or editing reports?");
      System.out.println("Please choose to continue or to exit the application by entering the appropriate number.");
      System.out.println(" Enter 1: Continue");
      System.out.println(" Enter 0: Exit");
      
      //Use the setChoice method to assign the user choice value to the choice int.
      choice = setChoice(0,1);
      
      if (choice == 1)
      {
        //if yes, restart the loop.
        System.out.println("Continuing...");
        continue;
      }
      else if (choice == 0)
      {
        //else exit
        System.out.println("Thanks for using the program! And stop drinking so much coffee or you'll get the shakes! Exiting.");
        System.exit(1);
      }
    }
  }
  
  //Method to set the important choice integer for the user to choose from various switch logic controlled menus throughout the program.
   protected static int setChoice(int lowerBound, int upperBound)
   {       
     while (true) //While loop and if statements to ensure that user provides a proper integer input within the range of the lower and upper bounds.
     {
       System.out.print("Enter your choice: ");
       
       //Try and catch block to help with line delimiter issues returning newline characters.
       try 
       {
         choice = Integer.parseInt(input.nextLine());//Since hasNextInt() returned true from above, choice is set to the int input by the user.
       } 
       catch (NumberFormatException e) 
       {
         System.out.print("Oops, please enter a number between " + lowerBound + " and " + upperBound +": ");
         input.nextLine(); //Ask for an input of any type and then loop again to see if it is an integer.
         continue;
       }
       
       //choice is then checked to see if it fits within the range specified by the arguments of setChoice().
       if ((choice < lowerBound) || (choice > upperBound))
       {
         System.out.print("Oops, please enter a number between " + lowerBound + " and " + upperBound +": ");
         continue;
       }            
       break; //break out of the loop with choice set
     }
     //Once the choice input is confirmed to be both an integer and within the appropriate range, it is returned.
     return choice;
   }
}