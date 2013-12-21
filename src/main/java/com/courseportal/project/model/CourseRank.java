package com.courseportal.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_ranks")
public class CourseRank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int workload;
    private int structure;
    private int teachers;
    private int avarageScore;

    @Column(columnDefinition="TEXT")
    private String comment;
    private String date;
    private int userId;

    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public int getStructure() {
        return structure;
    }

    public void setStructure(int structure) {
        this.structure = structure;
    }

    public int getTeachers() {
        return teachers;
    }

    public void setTeachers(int teachers) {
        this.teachers = teachers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAvarageScore() {
        return avarageScore;
    }

    public void setAvarageScore(int avarageScore) {
        this.avarageScore = avarageScore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
