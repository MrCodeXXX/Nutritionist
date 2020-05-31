package com.stackroute.favouriteservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "ndbno")
	private String ndbno;
	 
	@Column(name = "name")
	 private String name;
	
	@Column(name = "user_id")
	 private String userId;
	
	

	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Food(int id, String ndbno, String name, String userId) {
		super();
		this.id = id;
		this.ndbno = ndbno;
		this.name = name;
		this.userId = userId;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNdbno() {
		return ndbno;
	}



	public void setNdbno(String ndbno) {
		this.ndbno = ndbno;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	@Override
	public String toString() {
		return "Food [id=" + id + ", ndbno=" + ndbno + ", name=" + name + ", userId=" + userId + "]";
	}
	
	
	
}
