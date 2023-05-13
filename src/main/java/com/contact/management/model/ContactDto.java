package com.contact.management.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContactDto {

	private Integer id;

	@NotBlank
	private String name;

	@NotBlank
	private String company;

	@NotNull
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

}
