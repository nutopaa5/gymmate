package hh.swd20.gymmate.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Style {
	
	// Attributes and validation
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long styleid;
	
	@NotEmpty
	private String name;
	
	// OneToMany
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "style")
	@JsonBackReference
	private List<Workout> workouts;
	
	public Style() {
		//
	}
	
	
	// Getters and setters
	public Style(String name) {
		super();
		this.name = name;
	}
	
	public Long getStyleid() {
		return styleid;
	}
	public void setStyleid(Long styleid) {
		this.styleid = styleid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	// Workouts in List format
	public List<Workout> getWorkout() {
		return workouts;
	}
	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}
	
	//toString
	@Override
	public String toString() {
		return "Category [styleid=" + styleid + ", name=" + name + "]";
	}
}
