package edu.handong.analysis.utils;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Utils {

	public static ArrayList<String> getLines(String file,boolean removeHeader){
		 ArrayList<String> bizList = null;
		  BufferedReader br = null;

		  if ( !(file==null)) {
		   bizList = new ArrayList<String>();
		   try {
		    br = new BufferedReader(new FileReader(file));
		    String s;

			if (removeHeader) br.readLine();		

		    while ((s = br.readLine()) != null) {
		     bizList.add(s);
		    }
		    br.close();
		   } catch (IOException e) {
		    System.err.println(e);
		   }finally{
		    try { if( br != null ) { br.close(); } } catch(Exception ex) { }
		   }
		  }

		  return bizList;
	}
	
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		
		try{
            File file = new File(targetFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            
            if(file.isFile() && file.canWrite()){
            	
                bufferedWriter.write("studentID" + "," + "TotalNumberOfSemestersRegistered" + "," + "Semester" + "," + "NumCoursesTakenInTheSemester");
                bufferedWriter.newLine();

                //쓰기
        		for(String line:lines) {

                bufferedWriter.write(line.toString());

                bufferedWriter.newLine();
        		}
                
                bufferedWriter.close();
            }
        }catch (IOException e) {
            System.out.println(e);
        }


	
	}
}
