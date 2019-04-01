package hh.swd20.gymmate.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Style {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long styleid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "style")
	private List<Workout> workouts;
	
	public Style() {
	}
	
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

	public List<Workout> getWorkout() {
		return workouts;
	}
	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}
	
	@Override
	public String toString() {
		return "Category [styleid=" + styleid + ", name=" + name + "]";
	}
}
