package com.courseportal.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "code")
    private String courseCode;
    
    @Column(name = "name")
    private String courseName;
    
    @ElementCollection
    private List<CourseRank> courseranks;
    
    @ElementCollection
    private List<Comment> comments;
    
    @ElementCollection
    private List<UserBook> userBooks;
    
    @ElementCollection
    private List<CourseBook> courseBooks;

    @ManyToOne
    private University university;

	private String teacherName;
    
    private String courseLink;
    
    private int numberOfAssignments;
    
    private int numberOfProjects;
    
    private int numberOfExams;
    
    private int coursePoints;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
    
    public String getCourseLink() {
		return courseLink;
	}

	public void setCourseLink(String courseLink) {
		this.courseLink = courseLink;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void setCourseranks(List<CourseRank> courseranks) {
		this.courseranks = courseranks;
	}

	public List<CourseRank> getCourseranks() {
        return courseranks;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void addRank(CourseRank newRank) {
        courseranks.add(newRank);
    }
    
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    //User books, books that are for sale getters and setters
    
	public void removeUserBook(UserBook book) {
		this.userBooks.remove(book);
	}
	
	public void addUserBook(UserBook book) {
		this.userBooks.add(book);
	}
	
	public List<UserBook> getUserBooks() {
		return userBooks;
	}
	
	public void setUserBooks(List<UserBook> userBooks) {
		this.userBooks = userBooks;
	}
	
	/** Course books getter and setters **/
    
	public List<CourseBook> getCourseBooks() {
		return courseBooks;
	}

	public void setCourseBooks(List<CourseBook> courseBooks) {
		this.courseBooks = courseBooks;
	}
	
	public void removeCourseBook(CourseBook book) {
		this.courseBooks.remove(book);
	}
	
	public void addCourseBook(CourseBook book) {
		this.courseBooks.add(book);
	}

    public int getNumberOfAssignments() {
        return numberOfAssignments;
    }

    public void setNumberOfAssignments(int numberOfassignments) {
        this.numberOfAssignments = numberOfassignments;
    }

    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    public void setNumberOfProjects(int numberOfprojects) {
        this.numberOfProjects = numberOfprojects;
    }

    public int getNumberOfExams() {
        return numberOfExams;
    }

    public void setNumberOfExams(int numberOfexams) {
        this.numberOfExams = numberOfexams;
    }

    public int getCoursePoints() {
        return coursePoints;
    }

    public void setCoursePoints(int coursePoints) {
        this.coursePoints = coursePoints;
    }


}
