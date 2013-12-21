package com.courseportal.project.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.courseportal.project.forms.AddCourseForm;
import com.courseportal.project.model.Course;
import com.courseportal.project.model.University;

@Controller
@Transactional
public class AddCourseController {
    
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/addcourse", method = RequestMethod.POST)
    public String addCourse(@Valid AddCourseForm form, BindingResult formBinding, WebRequest request) {
        if (formBinding.hasErrors()) {
            return null;
        }

        Course newCourse = new Course();
        newCourse.setCourseName(form.getCourseName());
        newCourse.setCourseCode(form.getCourseCode());

        University university = entityManager.find(University.class, form.getUniversity());
        if (university == null) {
            university = new University(form.getUniversity());
            entityManager.persist(university);
        }
        newCourse.setUniversity(university);

        entityManager.persist(newCourse);

        return "redirect:courses/"+newCourse.getId()+"/"+newCourse.getCourseName();
    }
    
    @RequestMapping(value="/addcourse", method=RequestMethod.GET)
    public AddCourseForm addCourseForm(WebRequest request) {
            return new AddCourseForm();
    }
}