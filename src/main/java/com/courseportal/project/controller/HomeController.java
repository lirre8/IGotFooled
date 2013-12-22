package com.courseportal.project.controller;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.courseportal.project.forms.AddScamForm;
import com.courseportal.project.model.Scam;

@Controller
@Transactional
public class HomeController {

    @PersistenceContext
    private EntityManager entityManager;

	@RequestMapping("/")
	public String home(Model model) {
	    
	    
	    
	    ArrayList<Scam> scams = new ArrayList<Scam>(entityManager.createQuery("FROM Scam").getResultList());
	    model.addAttribute("addScamForm", new AddScamForm());
	    model.addAttribute("scams", scams);
	    return "home";
	}

}
