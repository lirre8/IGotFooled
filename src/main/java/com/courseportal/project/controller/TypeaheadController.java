package com.courseportal.project.controller;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.courseportal.project.model.Course;


@Controller
@Transactional
@RequestMapping(value = "/typeahead")
public class TypeaheadController {

    @PersistenceContext
    private EntityManager entityManager;
   
    @RequestMapping(value = "/searchCourseCodeName", method = RequestMethod.GET)
    @ResponseBody
    public List<JSONcourse> searchCourseCodeName() throws JSONException {
        
        ArrayList<Course> courseList = new ArrayList<Course>(entityManager.createQuery("FROM Course").getResultList());
    	List<JSONcourse> jsonList = new ArrayList<JSONcourse>(); 
    	
    	for (Course course : courseList) {
    	    JSONcourse jsonCourse = new JSONcourse();
    	    jsonCourse.setCourseName(course.getCourseName());
    	    jsonCourse.setCourseCode(course.getCourseCode());
    	    jsonCourse.setUniversity(course.getUniversity().getName());
    	    jsonCourse.setId(course.getId());
    	    jsonList.add(jsonCourse);
    	}
    	return jsonList;
    }
}

class JSONcourse {

    Long id;
    String courseCode;
    String courseName;
    String university;
 
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
}