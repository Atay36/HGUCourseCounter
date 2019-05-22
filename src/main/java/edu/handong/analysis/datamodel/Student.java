package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {

	private String studentId;
	private ArrayList<Course> coursesTaken; // 학생이 들은 수업 목록
	private HashMap<String,Integer> semestersByYearAndSemester; 
	                                                         // key: Year-Semester
	                                                         // e.g., 2003-1, 
	public Student(String studentId) { // constructor
	
		this.setStudentId(studentId);
		coursesTaken = new ArrayList<Course>();
		semestersByYearAndSemester = new HashMap<String,Integer>();
		
	}
	
	public void addCourse(Course newRecord) {
		coursesTaken.add(newRecord);
	}
	
	public HashMap<String,Integer> getSemestersByYearAndSemester(){
		
		int count = 1;
		
		for(Course course : coursesTaken) {
			String add = new String();
			
			add = Integer.toString(course.getyearTaken()) + "-" + Integer.toString(course.getSemesterCourseTaken());
			
			
			if(semestersByYearAndSemester.containsKey(add)==false) {
				semestersByYearAndSemester.put(add, count++);
			} else continue;

		}
		
		
		
		return semestersByYearAndSemester;
	}
	
	public int getNumCourseInNthSementer(int semester) {
		
		int count =0;
		
		String number = new String();
		String add = new String();
		
		for(String N : semestersByYearAndSemester.keySet()) {
			if(semestersByYearAndSemester.get(N).intValue() == semester) {
				number = N;
				break;
			}
		}
		
		for(Course course : coursesTaken) {
			add = Integer.toString(course.getyearTaken()) + "-" + Integer.toString(course.getSemesterCourseTaken());
			
			if(add.equals(number))
				count++;
		}
		
		return count;	
	}
	
	public String setStudentId(String studentName){
		return studentId = studentName ;
	}
	
	public String getStudentId(){
		return studentId;
	}
	
	
}
