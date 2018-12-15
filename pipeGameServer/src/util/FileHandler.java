package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
	
	public static void printFile(File file)
	{
		////System.out.println("Here in file printer");
		try {
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 String line = null;
			 while ((line = br.readLine()) != null) 
			 {
			   //System.out.println(line);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getFirstRow(File file)
	{
		////System.out.println("Here in file getFirstRow");
		try {
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 String line = "";
			 while ((line = br.readLine()) != null) 
			 {
			   return line;
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return null;
	}
	
	public static String getRow(File file,int rowNum)
	{
		int total = getNumRows(file);
		if(rowNum < 0 || rowNum > total-1)
		{
			return null;
		}
		try {
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 String line = "";
			 int count = 0;
			 while ((line = br.readLine()) != null) 
			 {
			   if(count != rowNum)
				   count++;
			   else 
				   return line;
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return null;
	}
	
	public static int getNumRows(File file)
	{
		////System.out.println("Here in file getNumRows");
		try {
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 String line = "";
			 int count = 0;
			 while ((line = br.readLine()) != null) 
			 {
			   count++;
			 }
			 return count;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return -1; // problem with the file
	}
	
	
	//test main FileHandler
	public static void main(String[]ags)
	{
	    File file = new File("C:\\test\\level1.txt");
	    printFile(file);
	    //System.out.println(getFirstRow(file).length());
	    //System.out.println(getNumRows(file));
	}
}
