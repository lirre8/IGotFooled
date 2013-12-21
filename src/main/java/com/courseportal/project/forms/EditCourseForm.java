package com.courseportal.project.forms;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.courseportal.project.model.CourseBook;
import com.courseportal.project.model.University;


public class EditCourseForm {

    @NotEmpty
    private String courseCode;

    @NotEmpty
    private String courseName;
    
    private University university;

    private String courseLink;

    private String teacherName;

    private List<CourseBook> courseBooks = new LinkedList<CourseBook>();
    
    private int numberOfAssignments;
    
    private int numberOfProjects;
    
    private int numberOfExams;
    
    private int coursePoints;
    
    public EditCourseForm() {
    }
 
    public EditCourseForm(List<CourseBook> courseBooks) {
        this.courseBooks = courseBooks;
    }

    public List<CourseBook> getCourseBooks() {
        return courseBooks;
    }

    public void setCourseBooks(List<CourseBook> courseBooks) {
        this.courseBooks = courseBooks;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
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
