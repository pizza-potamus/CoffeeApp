import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;

/* LoadFiles Class - author Andrea Zemp - date modified 11-25-2014
   The LoadFiles class is used to create File objects of text files and 
   load String arrays from those objects to provide data for Report class objects.
*/
class LoadFiles
{
  final static Scanner input = new Scanner(System.in);
  
   static File[] loadForReport()
   {
       //User provides his/her folder directory info
      Scanner input = new Scanner (System.in);
              
      //Create/initialize String directory to store folder directory info
      System.out.print("Copy and paste the directory address of the folder containing your Tasting Reports: ");      
      String directory = input.nextLine();
       
      //Create a file object. This is the folder in which the text files are stored
      //All of these backslashes are necessary b/c of a backslash reg expression problem. Don't delete them! 
      File file = new File(directory);
       
      //Check if the folder exists
      while (!file.exists())
      {   
         System.out.print("Try recopying and pasting the directory address of the folder containing your Tasting Reports: ");      
         
         directory = input.nextLine();
         file = new File (directory);
      } 
      
      //Creates a File object array of the names of all the Tasting Reports found in the folder
      File[] textFileNames = file.listFiles(new FilenameFilter()
      {
         public boolean accept(File dir, String name)
         {
            return name.startsWith("Tasting Report");
         }
      });
       
       return textFileNames;
   }//loadForReport method closing brace
     
/* createTextArray Method 
   Purpose - create a String arrayList of the contents of one text file. 
   1. Choose to use an ArrayList b/c we were not sure if we would add more info to the tasting reports over time
   2. Method is set up to create a String arrayLists of text files as a for loop cycles through the textFileNames array. Parameters are 1. an array of text files names and 2. and integer (feed in from the for loop) 
   3. Edits the elements to delete unnecessary characters   
*/
   static ArrayList<String> createTextArray(int i, File[] textFileNames)
   {     
      ArrayList<String> textFile = new ArrayList<String>();
      
      try 
      {
         //Use FileInputStream, inputStream and Buffered reader to read text files
         //InputStreamReader converts the computer read bytes iinto characters  
         InputStream in = new FileInputStream(textFileNames[i]);
         //I choose BufferedReader b/c I wanted to read the file line by line
         //BufferedReader reads a line of text from the inputStreamReader
         BufferedReader reader = new BufferedReader(new InputStreamReader(in));
         String line;
         
         //While line read is not null add line to the textFile arrayList  
         //Try and catch to handle IO Exception
         try 
         {
            while((line = reader.readLine()) != null)
            {
               textFile.add(line);
            }      
         }
         catch (IOException IOex) 
         {
            IOex.printStackTrace();
         }
         
         //Close the BufferedReader
         reader.close();
      }//try
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
       
      //Trim the strings in the ArrayList so that they only have the data we care about.
      textFile.remove("ABOUT THE TASTER:");
      textFile.remove("ABOUT THE COFFEE:");
      textFile.remove("RATINGS ON A SCALE OF 1 TO 5:");
      String toTrim = new String();
      String trimmed = new String();
      
      //for loop to Trim all of the colons and dollar signs
      for (int j = 0; j < textFile.size(); j++)
      {
         toTrim = textFile.get(j); 
         for (int x = 0; x < toTrim.length(); x++)
         {
            if (toTrim.charAt(x) == ':' && toTrim.charAt(x+1) == ' ' && toTrim.charAt(x+2) == '$')
            {
               trimmed = toTrim.substring(x+3);
               textFile.set(j, trimmed);
            }//if
            else if (toTrim.charAt(x) == ':' && toTrim.charAt(x+1) == ' ')
            {
               trimmed = toTrim.substring(x+2);
               textFile.set(j, trimmed);         
            } //else if
         }//inner for
      }//outer for
      
      //Remove the degrees celsius from the temperature String at index 8.
      String oneMoreTrim = new String();
      oneMoreTrim = textFile.get(9);
      for (int x = 0; x < oneMoreTrim.length(); x++)
      {
         if (oneMoreTrim.charAt(x) == ' ')
         {
            oneMoreTrim = oneMoreTrim.substring(0,x);
         } 
      }//for
      textFile.set(9, oneMoreTrim);
       
      return (textFile);
   }//createTextArray closing brace

}//Class closing brace