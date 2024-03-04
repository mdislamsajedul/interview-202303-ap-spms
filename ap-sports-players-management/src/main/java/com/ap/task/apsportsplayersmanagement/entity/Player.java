package com.ap.task.apsportsplayersmanagement.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="PLAYERS_TBL")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", 
		  scope = Player.class)
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String email;
	Integer level;
	Integer age;
	String gender;
	
	@ManyToMany(mappedBy = "players", fetch = FetchType.EAGER)
	@JsonBackReference
	List<Sport> sports;
	
	
	
	public Player() {
	}
	
	public Player(String email, Integer level, Integer age, String gender) {
		super();
		this.email = email;
		this.level = level;
		this.age = age;
		this.gender = gender;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Sport> getSports() {
		return sports;
	}
	public void setSports(List<Sport> sports) {
		this.sports = sports;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", email=" + email + ", level=" + level + ", age=" + age + ", gender=" + gender
				+ "]";
	}
	
}
