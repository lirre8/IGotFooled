package com.courseportal.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CourseBook extends Book {
	
	@Id
	private String isbn;
    
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
