package com.dating.datingsuggestions.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
    private Long id;
    private String name;
    private String gender;
    private int age;
    private List<String> interests;    

    public User()
    {
    	
    }
    
    // Constructors, getters, and setters
    public User(Long id, String name, String gender, int age, List<String> interests) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.interests = interests;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", interests=" + interests
				+ "]";
	}   

}
