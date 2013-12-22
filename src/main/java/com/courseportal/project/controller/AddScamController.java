
package com.courseportal.project.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.courseportal.project.forms.AddScamForm;
import com.courseportal.project.model.Adress;
import com.courseportal.project.model.City;
import com.courseportal.project.model.Scam;

@Controller
@Transactional
public class AddScamController {

    //private final Provider<ConnectionRepository> connectionRepositoryProvider;

    @PersistenceContext
    private EntityManager entityManager;
    
    @RequestMapping(value = "/addscam", method = RequestMethod.POST)
    public String addCourse(@Valid AddScamForm form, BindingResult formBinding, WebRequest request) {
        if (formBinding.hasErrors()) {
            System.out.println("skit");
            return "home";
        }
        
        Scam newScam = new Scam();
        newScam.setTitle(form.getTitle());
        newScam.setDescription(form.getDescription());
        
        /*entityManager.createQuery("SELECT FROM Account a WHERE a.username = :username")
        .setParameter("username", username).getSingleResult();*/
        
        // City
        City newCity = new City();
        newCity.setName(form.getCity());
        newCity.setCountry(form.getCountry());
        newCity.setCrimeRate(1);
        entityManager.persist(newCity);
        newScam.setCity(newCity);
        
        newScam.setCategory(form.getCategory());
        newScam.setDateString(getDateTime());

        //Address
        Adress adress = new Adress();
        adress.setLat(form.getLat());
        adress.setLng(form.getLng());

        entityManager.persist(adress);

        newScam.setAdress(adress);
        
        //Damage
        String damages = "";
        

        damages += form.getMoney() + " ";
        damages += form.getViolence() + " ";
        damages += form.getViolence() + " ";

        newScam.setDamage(damages);

        entityManager.persist(newScam);

        return "redirect:/";
    }
    
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
}
