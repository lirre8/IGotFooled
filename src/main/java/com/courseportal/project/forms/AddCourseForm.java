package com.courseportal.project.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class AddCourseForm {
    
    @NotEmpty
    private String courseName;

    @NotEmpty
    private String courseCode;

    @NotEmpty
    private String university;

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

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

}
