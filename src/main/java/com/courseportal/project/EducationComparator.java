package com.courseportal.project;

import java.util.Comparator;

import org.springframework.social.facebook.api.EducationEntry;

public class EducationComparator implements Comparator<EducationEntry> {
	    
	@Override
	public int compare(EducationEntry education, EducationEntry education1) {
		return education1.getYear().getName().compareTo(education.getYear().getName());
	}
}
