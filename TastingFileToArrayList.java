import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
/*
TastingFileToArrayList class
Programmers: Pierce MacMillan

A Class that contains the methods needed to take in a Tasting Report File object and convert its contents to an ArrayList with trimmed atomic values.
*/
class TastingFileToArrayList
{

  //The contents of a tasting report text file converted into entries in an ArrayList object.
  private ArrayList<String> tastingReportContents = new ArrayList<String>();

  //Default constructor for TastingFileToArrayList converter.
  TastingFileToArrayList()
  {
  }

  //Constuctor with a File argument that converts the Tasting Report file's contents to an ArrayList<String>.
  TastingFileToArrayList(File newTastingReportFile)
  {
  }

  //Method to copy the contents of the an ArrayList<String> to tastingReportContents ArrayList<String>.
  protected void setTastingReportContents(ArrayList<String> newTastingReportContents)
  {
    tastingReportContents = new ArrayList<String>(newTastingReportContents);
  }

  //Method to return the ArrayList<String> tastingReportContents.
  protected ArrayList<String> getTastingReportContents()
  {
    return tastingReportContents;
  }

  //Method to convert a Tasting Report file to an ArrayList<String>.
  protected ArrayList<String> convertTastingToArrayList(File fileToConvert)
  {
  
  try //try block to handle the exceptions thrown by the BufferedReader class
  {
    //Instantiate an InputReader object of type FileInputStream and use fileToConvert as its argument.
    InputStream inputReader = new FileInputStream(fileToConvert);
    
    //Instantiate a BufferReader class to read the InputStreamReader stream line by line.
    BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputReader));
    
    //A string to take in the bufferReader's output.
    String lineToRead;
    
    try //another try block to handle the exceptions thrown by the readLine() method of BufferedReader
    {
      while((lineToRead = bufferReader.readLine()) != null)//If the bufferReader line being read has something in it, set that line to lineToRead.
      {
        tastingReportContents.add(lineToRead); //Add the lineToRead to the tastingReportContents ArrayList.
      }
      bufferReader.close(); //When the file is finished being read, close the BufferedReader.
    }
    catch (Exception e) //Catch any reader exceptions and print the stack trace for troubleshooting.
    {
      e.printStackTrace();
    }
  }
  catch (Exception e) //Catch any reader exceptions and print the stack trace for troubleshooting.
  {
    e.printStackTrace();
  }
  
  //Trim the strings in the ArrayList so that they only have the data we care about.
  //First start by trimming out the unneeded tasting report headings.
  tastingReportContents.remove("ABOUT THE TASTER:");
  tastingReportContents.remove("ABOUT THE COFFEE:");
  tastingReportContents.remove("RATINGS ON A SCALE OF 1 TO 5:");
  
  //Instantiate two new String objects to hold the elements of the Array List for trimming
  String toTrim = new String();
  String trimmed = new String();
  
  //Cycle through the tastingReportContents Array List
  for (int j = 0; j < tastingReportContents.size(); j++)
  {
    toTrim = tastingReportContents.get(j); //Set each element to the toTrim String.
    
    //Now cycle through the toTrim String
    for (int x = 0; x < toTrim.length(); x++)
    {
      //Find the combination of characters ": $"
      if (toTrim.charAt(x) == ':' && toTrim.charAt(x+1) == ' ' && toTrim.charAt(x+2) == '$')
      {
        //Trim the toTrim String to remove the above characters.  This will give set the currency elements to just their numbers.
        trimmed = toTrim.substring(x+3);
        tastingReportContents.set(j, trimmed); //Replace the untrimmed currency elements with the trimmed ones. 
        
      }
      //Now find the combination of characters ": "
      else if (toTrim.charAt(x) == ':' && toTrim.charAt(x+1) == ' ')
      {
        //Trim the descriptive portions of these strings off, so that only the data is left.
        trimmed = toTrim.substring(x+2);
        tastingReportContents.set(j, trimmed); //Replace the untrimmed elements with the newly trimmed ones.
      }
    }
  }

  //Remove the degrees celsius from the temperature String at index 8.
  String oneMoreTrim = new String(); //Create a new String.
  oneMoreTrim = tastingReportContents.get(9); //Set this string to the contents of element 9 in the tastingReportContents ArrayList
  for (int x = 0; x < oneMoreTrim.length(); x++) //Cycle through the string
  {
    if (oneMoreTrim.charAt(x) == ' ') //find the space between the integer and "degrees Celcius"
    {
      oneMoreTrim = oneMoreTrim.substring(0,x); //remove the "degrees Celcius"
    } 
  }
  tastingReportContents.set(9, oneMoreTrim); //Set element 9 to the newly trimmed temperature integer.
  
    return tastingReportContents; //return the ArrayList with all atomic tasting report values in each element
  }
}