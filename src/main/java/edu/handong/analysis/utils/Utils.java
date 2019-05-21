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
		 //return null; // 임시방편 
	}
	
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		
		try{
            //파일 객체 생성
            File file = new File(targetFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            
            if(file.isFile() && file.canWrite()){
                //쓰기
                bufferedWriter.write(lines.toString());
                bufferedWriter.write(",");

                //개행문자쓰기
                bufferedWriter.newLine();
                bufferedWriter.write("문자열 추가2");
                
                bufferedWriter.close();
            }
        }catch (IOException e) {
            System.out.println(e);
        }


	
	}
}
