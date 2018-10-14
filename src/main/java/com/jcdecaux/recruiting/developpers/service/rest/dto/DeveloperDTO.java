package com.jcdecaux.recruiting.developpers.service.rest.dto;

import java.util.List;

public class DeveloperDTO {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private List<LanguageDTO> languages;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<LanguageDTO> getLanguages() {
		return languages;
	}
	public void setLanguages(List<LanguageDTO> languages) {
		this.languages = languages;
	}
	

}
