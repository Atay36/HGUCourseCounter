package edu.handong.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.datamodel.Persent;

import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

import java.net.*;
import java.text.DecimalFormat;
import java.io.*;
import java.util.*;

public class HGUCoursePatternAnalyzer extends Exception {
	
	String input;
	String output;
	static public String analysis;
	String coursecode;
	String startyear;
	String endyear;

	boolean help;

	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		Options option = createOptions();
		if (parseOptions(option, args))
		{
			if (help){
				printHelp(option);
				return;
			}
			
		}
		else
		{
			System.out.println("asdfasdf");
			System.exit(0);
		}
		try {
			
			
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		String dataPath = input; // csv file to be analyzed
		String resultPath = output; // the file path where the results are saved.
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		

		
		if(analysis.equals("1")) {
			// Generate result lines to be saved.
			ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
	    	//System.out.print("asdf");
			// Write a file (named like the value of resultPath) with linesTobeSaved.
			Utils.writeAFile(linesToBeSaved, resultPath);
		} else if(analysis.equals("2")) {
			//newthing~
			// Generate result lines to be saved.
			ArrayList<Persent> HW6 = loadHW6(lines);
			ArrayList<String> HW6write = writeHW6(HW6);
			Utils.writeAFile(HW6write, resultPath);

			}
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		
		HashMap<String,Student> data1 = new HashMap<String,Student>();
		ArrayList<String> courses = new ArrayList<String>();
		Student student;
		
		
		for(String line:lines) {
			String ID = line.split(",")[0].trim();
			
			
			if(data1.containsKey(ID)==false) {
				student = new Student(ID);
			
				data1.put(ID,student);
			
			} else {
				student = data1.get(ID);
				
			}
			
			Course course = new Course(line);
			
			student.addCourse(course);
			
			
		}
		
		// TODO: Implement this method
		
		return data1; // do not forget to return a proper variable.
	}
	
private ArrayList<Persent> loadHW6(ArrayList<String> lines) {
		
		HashMap<String,Integer> numberOfStudent = new HashMap<String,Integer>();
		HashMap<String,Integer> totalNumberOfStudent = new HashMap<String,Integer>();
		HashMap<Integer,Integer> totalNumberAndStudentNumber = new HashMap<Integer,Integer>();
		ArrayList<Persent> persents = new ArrayList<Persent>();

		String yearAndSemester = "";
		String courseAndSemester = "";
		String courseName = new String();

		int countTotal =0;
		int count = 0;

		String ID="";
		ArrayList<Course> courses = new ArrayList<Course>();
		Student student;
		
		
		for(String line:lines) {

			Course course = new Course(line);
			courses.add(course);
			
		}
		
		
		for(int i=Integer.parseInt(startyear);i<=Integer.parseInt(endyear);i++) {
			for(int j =1 ; j<=4; j++) {
				for(Course test : courses) {

					if(test.getyearTaken()==i && test.getSemesterCourseTaken() == j) {
						if(test.getcourseCode().equals(coursecode)) {
							count++;
							courseName = test.getcourseName();
						}
						if(ID.equals(test.getstudentId())==false) {
							countTotal++;
						}
						ID=test.getstudentId();
					}
				}
				Persent persent = new Persent();

				persent.setyearTaken(i);
				persent.setSemesterCourseTaken(j);
				persent.setstudentNumber(Integer.toString(count));
				persent.settotalNumber(Integer.toString(countTotal));
				persent.setcourseCode(coursecode);
				persent.setcourseName(courseName);
				
				persents.add(persent);
				/*
				numberOfStudent.put(i + "," + j, count);
				totalNumberOfStudent.put(i + "," + j, countTotal);
				totalNumberAndStudentNumber.put(count,countTotal);
				System.out.println(i + "," + j +","+ count);
				System.out.println(i + "," + j +","+ countTotal);
				*/
				count = 0;
				countTotal = 0;
				
			}
		}

		// TODO: Implement this method
		
		return persents; // do not forget to return a proper variable.
	}

	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		
		// TODO: Implement this method
		ArrayList<String> number = new ArrayList<String>();
	
		for(Student student: sortedStudents.values()) {
			
			Map<String, Integer> Semesters = new TreeMap<String, Integer>(student.getSemestersByYearAndSemester());

			Integer lastSemester = Semesters.values().size();
			
			for( Integer NthSemester : Semesters.values() ) {
				
				String add = new String();
				add = student.getStudentId() + "," + Integer.toString(lastSemester) + "," + Integer.toString(NthSemester) + "," + student.getNumCourseInNthSementer(NthSemester);
				
				number.add(add);
				
			}
		
		}

		
		return number; // do not forget to return a proper variable.
	}
	
private ArrayList<String> writeHW6(ArrayList<Persent> data) {
		
		// TODO: Implement this method
		//Set<Integer> studentNumber = data.keySet();
//		Collection studentNumber = data.keySet();
//		Iterator iter1 = studentNumber.iterator();
//		Collection totalNumber = data.values();
//		Iterator iter2 = totalNumber.iterator();
		int num =0;
		int totalNum=0;
		float persent=0;

		ArrayList<String> write = new ArrayList<String>();		
		/*
		while(iter1.hasNext()) {
			for(int i=Integer.parseInt(startyear);i<=Integer.parseInt(endyear);i++) {
				for(int j =1 ; j<=4; j++) {
					num = (int) iter1.next();
					totalNum = (int) iter2.next();
					//persent = (totalNum/num)*100;
					//System.out.println(i + "," + j +","+ num+ ","+totalNum);
					String line = new String();
					line = i + "," + j + "," + coursecode + ","+num +","+ totalNum +",";//+ persent +"%";
					System.out.println(line);
					write.add(line);
				}
			}
		}
		*/
		
		for(Persent test : data) {
			String line = new String();
			float p =0;
			
			if(test.getstudentNumber().equals("0")==false && test.gettotalNumber().equals("0")==false ) {
				System.out.println("ok" + test.getstudentNumber() +"," +test.gettotalNumber());
				p = (float)(Integer.parseInt(test.getstudentNumber())) 
					/(float)(Integer.parseInt(test.gettotalNumber()))
					*100;
			}

			line = test.getyearTaken() + "," 
					+test.getSemesterCourseTaken() + ","
					+ test.getcourseCode()+","
					+test.getcourseName()+","
					+test.gettotalNumber()+","
					+test.getstudentNumber()+","
					+String.format("%.1f",p) + "%";			
					System.out.println(line);
			write.add(line);
					
		}


		
		return write; // do not forget to return a proper variable.
	}
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			analysis = cmd.getOptionValue("a");
			if(analysis.equals("2")) {
				coursecode = cmd.getOptionValue("c");
			}
			startyear = cmd.getOptionValue("s");
			endyear = cmd.getOptionValue("e");

			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}
	
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an input file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, 2: Count per course name and year")
				.hasArg()
				.argName("Analysis option")
				.required()
				.build());
		
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg()
				.argName("course code")
				//.required()
				.build());
		
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("CSet the start year for analysis e.g., -s 2002")
				.hasArg()
				.argName("Start year for analysis")
				.required()
				.build());
		
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg()
				.argName("End year for analysis")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer ="\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}

}
	


