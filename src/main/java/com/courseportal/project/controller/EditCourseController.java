package com.courseportal.project.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.courseportal.project.forms.EditCourseForm;
import com.courseportal.project.model.Course;
import com.courseportal.project.model.CourseBook;
import com.courseportal.project.model.University;

@Controller
@Transactional
public class EditCourseController {
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/courses/{courseId}/{name}/edit", method = RequestMethod.POST)
    public String editCourse(@PathVariable Long courseId, Principal currentUser, @Valid EditCourseForm form, BindingResult formBinding, Model model) {
        
        if (formBinding.hasErrors()) {
            return "editcourse";
        }

        Course course = entityManager.find(Course.class, courseId);
        
        course.setCourseCode(form.getCourseCode());
        course.setCourseLink(form.getCourseLink());
        course.setCourseName(form.getCourseName());
        course.setTeacherName(form.getTeacherName());
        course.setCoursePoints(form.getCoursePoints());
        course.setNumberOfAssignments(form.getNumberOfAssignments());
        course.setNumberOfProjects(form.getNumberOfProjects());
        course.setNumberOfExams(form.getNumberOfExams());
        
        University university = entityManager.find(University.class, form.getUniversity().getName());
        if (university == null) {
            University newUniversity = new University(form.getUniversity().getName());
            entityManager.persist(newUniversity);
            course.setUniversity(newUniversity);
        } else {
            course.setUniversity(university);
        }

        List<CourseBook> newBooks = new ArrayList<CourseBook>();

        for (CourseBook formBook : form.getCourseBooks()) {
            if (formBook.getIsbn() != null && !formBook.getIsbn().trim().equals("")) {
                ArrayList<String> usedISBNs = new ArrayList<String>();
                if (!usedISBNs.contains(formBook.getIsbn())) {
                    CourseBook book = entityManager.find(CourseBook.class, formBook.getIsbn());
                    if (book != null) {
                        book.setCreator(formBook.getCreator());
                        book.setReleased(formBook.getReleased());
                        book.setLanguage(formBook.getLanguage());
                        book.setTitle(formBook.getTitle());
                        entityManager.persist(book);
                        newBooks.add(book);
                    } else {
                        entityManager.persist(formBook);
                        newBooks.add(formBook);
                    }
                    usedISBNs.add(formBook.getIsbn());
                }
            }
        }

        course.setCourseBooks(newBooks);
        entityManager.persist(course);

        return "redirect:/courses/"+courseId+"/"+ form.getCourseName();
    }
    
    @RequestMapping(value = "/courses/{courseId}/{name}/edit", method = RequestMethod.GET)
    public ModelAndView editCourse(@PathVariable Long courseId, Principal currentUser, WebRequest request, Model model) {

        Course course = entityManager.find(Course.class, courseId);
        
        EditCourseForm form = new EditCourseForm();

        Hibernate.initialize(course.getCourseBooks());
        form.setCourseCode(course.getCourseCode());
        form.setCourseLink(course.getCourseLink());
        form.setCourseName(course.getCourseName());
        form.setTeacherName(course.getTeacherName());
        form.setUniversity(course.getUniversity());
        form.setCourseBooks(course.getCourseBooks());
        form.setCoursePoints(course.getCoursePoints());
        form.setNumberOfAssignments(course.getNumberOfAssignments());
        form.setNumberOfExams(course.getNumberOfExams());
        form.setNumberOfProjects(course.getNumberOfProjects());
        
        
        return new ModelAndView("editcourse", "editCourseForm", form);
    }
}