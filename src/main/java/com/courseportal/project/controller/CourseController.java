package com.courseportal.project.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.social.facebook.api.EducationEntry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.courseportal.project.EducationComparator;
import com.courseportal.project.config.MainConfig;
import com.courseportal.project.model.Course;
import com.courseportal.project.model.CourseBook;
import com.courseportal.project.model.University;
import com.courseportal.project.model.UserBook;

@Controller
@Transactional
@RequestMapping(value = "/course")
@ContextConfiguration(classes = MainConfig.class)
public class CourseController {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Facebook facebook;

    @RequestMapping(value = "/editCourseName", method = RequestMethod.POST)
    @ResponseBody
    public boolean editCourseCode(@RequestBody String newCourseName, @RequestParam Long courseId) throws Exception {
    	Course course = entityManager.find(Course.class, courseId);
    	course.setCourseName(newCourseName);
    	entityManager.persist(course);
        return true;
    }
    
    @RequestMapping(value = "/editUniversity", method = RequestMethod.POST)
    @ResponseBody
    public boolean editUniversity(@RequestBody String newUniversityString, @RequestParam Long courseId) throws Exception {
    	Course course = entityManager.find(Course.class, courseId);
    	University university = entityManager.find(University.class, newUniversityString);
    	
    	if (university == null) {
    		University newUniversity = new University(newUniversityString);
    		entityManager.persist(newUniversity);
    		course.setUniversity(newUniversity);
    	} else {
    		course.setUniversity(university);
    	}
    	entityManager.persist(course);
        return true;
    }
    
    @RequestMapping(value = "/editTeacherName", method = RequestMethod.POST)
    @ResponseBody
    public boolean editTeacherName(@RequestBody String newTeacherName, @RequestParam Long courseId) throws Exception {
    	Course course = entityManager.find(Course.class, courseId);
    	course.setTeacherName(newTeacherName);
    	entityManager.persist(course);
        return true;
    }
    
    @RequestMapping(value = "/editCourseLink", method = RequestMethod.POST)
    @ResponseBody
    public boolean editCourseLink(@RequestBody String newCourseLink, @RequestParam Long courseId) throws Exception {
    	Course course = entityManager.find(Course.class, courseId);
    	course.setCourseLink(newCourseLink);
    	entityManager.persist(course);
        return true;
    }

    @RequestMapping(value = "/addCourseBook", method = RequestMethod.POST)
    @ResponseBody
    public CourseBook addCourseBook(@RequestBody CourseBook newBook, @RequestParam Long courseId) throws Exception {
    	CourseBook book = entityManager.find(CourseBook.class, newBook.getIsbn());
    	Course course = entityManager.find(Course.class, courseId);
    	
    	//Silver tape fix
    	if(!course.getCourseBooks().contains(book)) {
	    	if(book == null) {
	    		entityManager.persist(newBook);
	    		course.addCourseBook(newBook);
	    	}
	    	else {
	    		course.addCourseBook(book);
	    	}
	    	entityManager.persist(course);
    	}
        return newBook;
    }
    
    /*@RequestMapping(value = "/addUserBook", method = RequestMethod.POST)
    @ResponseBody
    public UserBook addUserBook(@RequestBody UserBook newBook, @RequestParam Long courseId) throws Exception {
    	CourseBook courseBook = entityManager.find(CourseBook.class, newBook.getIsbn());
    	Course course = entityManager.find(Course.class, courseId);

    	newBook.setCreator(courseBook.getCreator());
    	newBook.setIsbn(courseBook.getIsbn());
    	newBook.setDate(getDateTime());
    	newBook.setLanguage(courseBook.getLanguage());
    	newBook.setTitle(courseBook.getTitle());
    	newBook.setUserId(facebook.userOperations().getUserProfile().getId());
    	newBook.setUserName(facebook.userOperations().getUserProfile().getFirstName() + " " + facebook.userOperations().getUserProfile().getLastName());
    	newBook.setUserProfile(facebook.userOperations().getUserProfile().getLink());

    	entityManager.persist(newBook);
    	course.addUserBook(newBook);
    	entityManager.persist(course);
        return newBook;
    }*/
    
    @RequestMapping(value = "/removeCourseBook", method = RequestMethod.POST)
    @ResponseBody
    public boolean removeCourseBook(@RequestBody long bookIsbn, @RequestParam Long courseId) throws Exception {
    	CourseBook book = entityManager.find(CourseBook.class, bookIsbn);
    	Course course = entityManager.find(Course.class, courseId);
		course.getCourseBooks().remove(book);
		entityManager.persist(course);
		return true;
    }
    
    @RequestMapping(value = "/removeUserBook", method = RequestMethod.POST)
    @ResponseBody
    public boolean removeUserBook(@RequestBody long id, @RequestParam Long courseId) throws Exception {
    	UserBook book = entityManager.find(UserBook.class, id);
    	Course course = entityManager.find(Course.class, courseId);
		course.getUserBooks().remove(book);
		entityManager.persist(course);
		return true;
    }
    
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    /**
     * Sorting the current users educations and returning the latest ones name
     * @return String the latest educations name
     */
    private String getLatestEducation() {
        if(facebook.userOperations().getUserProfile().getEducation() == null) {
        	return "";
        }
  
    	List<EducationEntry> educationList = facebook.userOperations().getUserProfile().getEducation();
        if(educationList.contains(null)) {
        	return "";
        }

        Collections.sort(educationList, new EducationComparator());

        return educationList.get(0).getSchool().getName();
    }
    
}