package edu.handong.analysis.utils;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.handong.analysis.HGUCoursePatternAnalyzer;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    private static final String SAMPLE_CSV_FILE_PATH = "./users-with-header.csv";

    //public static void main(String[] args) throws IOException {
    public static ArrayList<String> getLines (String file,boolean removeHeader) {
		 ArrayList<String> bizList = new ArrayList<String>();
		 //BufferedReader br = null;
		 CSVParser csvParser = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(file));
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
        }
        catch (IOException e) {
        	System.out.println(e.getMessage());
        	System.exit(0);
        }
        
        
        for (CSVRecord csvRecord : csvParser) {
            // Accessing values by Header names
            String studentID = csvRecord.get("StudentID");
            String yearMonthGraduated = csvRecord.get("YearMonthGraduated");
            String fistMajor = csvRecord.get("FistMajor");
            String secondMajor = csvRecord.get("SecondMajor");
            String courseCode = csvRecord.get("CourseCode");
            String courseName = csvRecord.get("CourseName");
            String courseCredit = csvRecord.get("CourseCredit");
            String yearTaken = csvRecord.get("YearTaken");
            String semesterTaken = csvRecord.get("SemesterTaken");

            String line = studentID + "," 
            		+ yearMonthGraduated + ","
            		+fistMajor+ ","
            		+secondMajor+ ","
            		+courseCode+ ","
            		+courseName+ ","
            		+courseCredit+ ","
            		+yearTaken+ ","
            		+semesterTaken;
            
            bizList.add(line);
        }
        
        return bizList;
    }



 
//import au.com.bytecode.opencsv.CSVReader;
/*
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
	*/
	
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {

    	//System.out.print("writeAFile");
		Path path = Paths.get(targetFileName);
		File parentDir = path.toFile().getParentFile();
		if (!parentDir.exists()) {
			try {
				parentDir.mkdirs();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		File file = new File(targetFileName);
		try{
      
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            
            if(file.isFile() && file.canWrite()){
            	if(HGUCoursePatternAnalyzer.analysis.equals("1")) {
	                bufferedWriter.write("studentID" + "," + "TotalNumberOfSemestersRegistered" + "," + "Semester" + "," + "NumCoursesTakenInTheSemester");
	                bufferedWriter.newLine();
            	} else if(HGUCoursePatternAnalyzer.analysis.equals("2")) {
            		bufferedWriter.write("Year" + "," + "Semester" + "," + "CouseCode" + "," + "CourseName" + "," +"TotalStudents" + ","+ "StudentsTaken" +"," + "Rate");
                    bufferedWriter.newLine();
            	}

                //쓰기
        		for(String line:lines) {

                bufferedWriter.write(line.toString());
                bufferedWriter.newLine();
                
        		}
                
                bufferedWriter.close();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

	
	}
}
