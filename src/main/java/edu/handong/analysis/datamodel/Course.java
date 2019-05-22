package edu.handong.analysis.datamodel;

public class Course {
	
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;

	public Course(String line){ // constructor에서 line을 받아 split해서 field초기화
		studentId = line.split(",")[0].trim();
		yearMonthGraduated = line.split(",")[1].trim();
		firstMajor = line.split(",")[2].trim();
		secondMajor = line.split(",")[3].trim();
		courseCode = line.split(",")[4].trim();
		courseName = line.split(",")[5].trim();
		courseCredit = line.split(",")[6].trim();
		yearTaken = Integer.parseInt(line.split(",")[7].trim());
		semesterCourseTaken = Integer.parseInt(line.split(",")[8].trim());

	}
	
	public String getstudentId () {
		return studentId;
	}
	public String getfirstMajor () {
		return firstMajor;
	}
	public String getsecondMajor () {
		return secondMajor;
	}
	public String getcourseCode () {
		return courseCode;
	}
	public String getcourseName () {
		return courseName;
	}
	public String getcourseCredit () {
		return courseCredit;
	}
	public int getyearTaken () {
		return yearTaken;
	}
	public int getSemesterCourseTaken () {
		return semesterCourseTaken;
	}
	public String setstudentId (String newstudentId) {
		return studentId=newstudentId;
	}
	public String setfirstMajor (String newfirstMajor) {
		return firstMajor=newfirstMajor;
	}
	public String setsecondMajor (String newsecondMajor) {
		return secondMajor=newsecondMajor;
	}
	public String setcourseCode (String newcourseCode) {
		return courseCode=newcourseCode;
	}
	public String setcourseName (String newcourseName) {
		return courseName=newcourseName;
	}
	public String setcourseCredit (String newcourseCredit) {
		return courseCredit=newcourseCredit;
	}
	public int setyearTaken (int newyearTaken) {
		return yearTaken=newyearTaken;
	}
	public int setSemesterCourseTaken (int newsemesterCourseTaken) {
		return semesterCourseTaken=newsemesterCourseTaken;
	}
	
}
