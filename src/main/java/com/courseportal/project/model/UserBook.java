package com.courseportal.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class UserBook extends Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String isbn;

	@NumberFormat
	@NotNull(message="Must specify price")
	private Integer price;
	private String quality;
	private String username;
	private String fullName;
	private int userId;
	private String added;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }
}
