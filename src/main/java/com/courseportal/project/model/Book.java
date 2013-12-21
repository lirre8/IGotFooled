package com.courseportal.project.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Book {
	

	private String title;
    private String creator;
    private String language;
    private String released;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getReleased() {
		return released;
	}
	public void setReleased(String released) {
		this.released = released;
	}
    

}
