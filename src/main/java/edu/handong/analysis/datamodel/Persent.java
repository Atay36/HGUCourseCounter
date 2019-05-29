package edu.handong.analysis.datamodel;

public class Persent {
	
	private String studentNumber;
	private String totalNumber;
	private String courseCode;
	private String courseName;
	private int yearTaken;
	private int semesterCourseTaken;

	public void Course(String line){ // constructor에서 line을 받아 split해서 field초기화
		
	}
	
	public String getstudentNumber () {
		return studentNumber;
	}
	public String gettotalNumber () {
		return totalNumber;
	}
	public String getcourseCode () {
		return courseCode;
	}
	public String getcourseName () {
		return courseName;
	}
	public int getyearTaken () {
		return yearTaken;
	}
	public int getSemesterCourseTaken () {
		return semesterCourseTaken;
	}
	public String setstudentNumber (String newstudentNumber) {
		return studentNumber=newstudentNumber;
	}
	public String settotalNumber (String newtotalNumber) {
		return totalNumber=newtotalNumber;
	}
	public String setcourseCode (String newcourseCode) {
		return courseCode=newcourseCode;
	}
	public String setcourseName (String newcourseName) {
		return courseName=newcourseName;
	}
	public int setyearTaken (int newyearTaken) {
		return yearTaken=newyearTaken;
	}
	public int setSemesterCourseTaken (int newsemesterCourseTaken) {
		return semesterCourseTaken=newsemesterCourseTaken;
	}
	
}
