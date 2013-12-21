package com.courseportal.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class University implements Serializable {
	
    @GeneratedValue
    private long id;
	
    @Id
    @Column(unique=true)
	private String name;

	public University() {}
	   
	public University(String university) {
        name = university;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
