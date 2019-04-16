package hh.swd20.gymmate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import hh.swd20.gymmate.domain.Style;

@Entity
public class Workout {

	// Attributes and validations
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long workoutid;
	

	@Size(min=1, max=40, message="Title must be between 1 and 40 characters")
	private String title;
	
	@Size(min=1, max=100, message="Description must be between 1 and 100 characters")
	private String description;
	
	@Size(min=1, max=255, message="Instructions must be between 1 and 255 characters")
	private String instructions;
	
	@Size(min=1, max=10, message="Duration must be between 1 and 10 characters")
	private String duration;
	
	private long thumbup;
	
	private long thumbdown;
		
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="styleid")
	private Style style;
		
	// Constructors
	public Workout() { 
		//
	}
		
	public Workout(String title, String description, String instructions, String duration, long thumbup, long thumbdown, Style style) {
		super();
		this.title = title;
		this.description = description;
		this.instructions = instructions;
		this.duration = duration;
		this.thumbup = thumbup;
		this.thumbdown = thumbdown;
		this.style = style;
	}
	
	// Getters
	public long getWorkoutid() {
		return workoutid;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public void setWorkoutid(long workoutid) {
		this.workoutid = workoutid;
	}
	public String getInstructions() {
		return instructions;
	}
	public String getDuration() {
		return duration;
	}
	public long getThumbup() {
		return thumbup;
	}
	
	// Setters
	public void setId(long workoutid) {
		this.workoutid = workoutid;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void setThumbup(long thumbup) {
		this.thumbup = thumbup;
	}
	public long getThumbdown() {
		return thumbdown;
	}
	public void setThumbdown(long thumbdown) {
		this.thumbdown = thumbdown;
	}
	
	// Style's set & get
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}

	// Other methods
	@Override
	public String toString() {
		if (this.style != null)
			return "Workout [workoutid=" + workoutid + ", title=" + title + ", description=" + description
			+ ", instructions=" + instructions + ", duration=" + duration + ", thumbup=" + thumbup + ", thumbdown="
			+ thumbdown + ", style=" + this.getStyle() + "]";
		else
			return "Workout [workoutid=" + workoutid + ", title=" + title + ", description=" + description
			+ ", instructions=" + instructions + ", duration=" + duration + ", thumbup=" + thumbup + ", thumbdown="
			+ thumbdown + "]";
	
	// Thumb Up value + 1
	}
	public void increaseThumbupByOne() {
		this.thumbup += 1L;
	}
	
	// Thumb Down value + 1
	public void increaseThumbdownByOne() {
		this.thumbdown += 1L;
	}
}
