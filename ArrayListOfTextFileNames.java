import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
/*
ArrayListOfTextFileNames class
Programmers: Pierce MacMillan

A Class that contains the methods needed to get all the Tasting Reports file names and files from a directory set by the user.
These can then be past to TastingFileToArrayList class and have their contents read into ArrayLists.
*/
class ArrayListOfTextFileNames
{
  // Good ole Scanner object
  private Scanner input = new Scanner(System.in);
  
  //A very important choice integer for the user to choose from various switch logic controlled menus throughout the program.
  private int choice = 0;
  
  //An ArrayList of files that will contain all the file names.
  private ArrayList<File> arrayListOfTextFileNames;
  
  //The directory where the tasting files are stored.
  private File fileDirectory;
  
  //Default ArrayListOfTextFileNames constructor.
  ArrayListOfTextFileNames()
  {
  }
  
  //ArrayListOfTextFileNames constructor which takes in a File object directory and filters out the text files.
  ArrayListOfTextFileNames(File directoryWithTextFiles)
  {
    //Create a File array object and set it to the directory containing the tasting reports with
    //the files listed and filtered by the FilenameFilter class so that only those files starting with "Tasting Report"
    //make it into the File array. 
    File[] filesToBeFiltered = directoryWithTextFiles.listFiles(
      new FilenameFilter() 
      {
        public boolean accept(File dir, String fileName) 
        {
        return fileName.startsWith("Tasting Report");
        }
      }
    );
    //Set the arrayListOfTextFileNames to a new ArrayList of files by converting the File array above using the Arrays
    //class asList() method.
    arrayListOfTextFileNames = new ArrayList<File>(Arrays.asList(filesToBeFiltered));
  }
  
  //Method to set the arrayListOfFileNames to a differnt arrayList<File>.
  protected void setArrayListOfTextFileNames(ArrayList<File> newArrayListOfTextFileNames)
  {
    arrayListOfTextFileNames = new ArrayList<File>(newArrayListOfTextFileNames);
  }
  
  //Method to return the arrayListOfTextFileNames ArrayList<File> object.
  protected ArrayList<File> getArrayListOfTextFileNames()
  {
    return arrayListOfTextFileNames;
  }
  
  //Method to set the tastingFilesDirectory with a new File object.
  protected void setFileDirectory(File newDirectory)
  {
    fileDirectory = newDirectory;
  }
  
  //Method to return the tastingFilesDirectory File object.
  protected File getFileDirectory()
  {
    return fileDirectory;
  }
  
  //Method to have the user input the tastingFilesDirectory.
  protected void userSetFileDirectory()
  {
    System.out.println("Enter the directory where your text files are stored.");
    System.out.print(" (e.g. C:\\Users\\Steve\\Documents\\Text Files\\: ");
    
    while(true) //A while to loop to allow the user to continue after editing one entry.
    {
      File tempDirectory = new File(input.nextLine()); //Instantiate a File object to store the user's input.
      if (tempDirectory.exists()) //Check that the directory exists 
      {
        fileDirectory = tempDirectory;
        break;
      }
      else //If the directory doesn't exist ask the user to try again and loop.
      {
        System.out.println("Oops, that doesn't look like a file directory.  Please try again.");
        System.out.println(" Enter the directory where your text files are stored.");
        System.out.print(" (e.g. C:\\Users\\Steve\\Documents\\Text Files\\: ");
        continue;
      }
    }
  }
  
  //Method to create a File[] from the directory containing Tasting Reports and filter out all but the .txt files.
  //It then copies the contents of the File[] to the arrayListOfTextFileNames.
  protected void filterByTXTFiles()
  {
    //Create a File array object and set it to the directory containing the tasting reports with
    //the files listed and filtered by the FilenameFilter class so that only those files starting with "Tasting Report"
    //make it into the File array.
    File[] filesToBeFiltered = fileDirectory.listFiles(
      new FilenameFilter() 
      {
        public boolean accept(File dir, String name) 
        {
        return name.startsWith("Tasting Report");
        }
      }
    );
    //Set the arrayListOfTextFileNames to a new ArrayList of files by converting the File array above using the Arrays
    //class asList() method.
    arrayListOfTextFileNames = new ArrayList<File>(Arrays.asList(filesToBeFiltered));
  }
  
  //Method to print (with numbers) the contents of the ArrayListOfTextFileNames.
  protected void printArrayListOfTextFileNames()
  {
    System.out.println("\nHere are all the text files contained in your chosen directory: ");
    for (int i = 0; i < arrayListOfTextFileNames.size(); i++) //Cycle through the arrayListOfTextFileNames
    {
      int j = i+1; //Increment the numbered list by one so that 0 doesn't show up.
      //Print the list out with numbers using the j integer and the getName() method of the File object.
      System.out.println(j+". "+arrayListOfTextFileNames.get(i).getName());
    }
  }
  
  //Method to print (with numbers) the contents of the ArrayListOfTextFileNames.
  //And ask the user to choose the text file they would like to edit by returning an int.
  protected int chooseArrayListOfTextFileNames()
  {
    System.out.println("\nHere are all the text files contained in your chosen directory: ");
    for (int i = 0; i < arrayListOfTextFileNames.size(); i++) //Cycle through the arrayListOfTextFileNames
    {
      int j = i+1; //Increment the numbered list by one so that 0 doesn't show up.
      //Print the list out with numbers using the j integer and the getName() method of the File object.
      System.out.println(j+". "+arrayListOfTextFileNames.get(i).getName());
    }
    
    System.out.println("\nPlease choose the corresponding number of the file you would like to edit. ");
    System.out.print("Enter 0 to exit with an option to change directories:  ");
    
    //Use the CoffeeApplication.setChoice method to assign the user choice value to the choice int.
    choice = CoffeeApplication.setChoice(0,arrayListOfTextFileNames.size());
    
  return choice;
  } 
  
}