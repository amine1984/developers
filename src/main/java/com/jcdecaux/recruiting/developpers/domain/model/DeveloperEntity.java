package com.jcdecaux.recruiting.developpers.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="DEVELOPER")
public class DeveloperEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	
	@ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
    @JoinTable(name = "developer_language", joinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"))
    Set<LanguageEntity> languages;
	
	public DeveloperEntity() {}

	public DeveloperEntity(String firstName, String lastName, Set<LanguageEntity> languages) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.languages = languages;
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public Set<LanguageEntity> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<LanguageEntity> languages) {
		this.languages = languages;
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
	
	public void addLanguage(LanguageEntity language){
		if (languages == null) {
			languages = new HashSet<>();
		}
		languages.add(language);
	}
}
