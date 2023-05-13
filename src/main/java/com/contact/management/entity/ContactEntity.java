package com.contact.management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACTS")
public class ContactEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "COMPANY")
	private String company;

	@Column(name = "NUMBER")
	private Integer number;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public ContactEntity(Integer id, String name, String company, Integer number) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.number = number;
	}

	public ContactEntity() {
		// TODO Auto-generated constructor stub
	}

}
