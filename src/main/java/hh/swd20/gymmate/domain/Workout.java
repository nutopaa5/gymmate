package hh.swd20.gymmate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hh.swd20.gymmate.domain.Style;

@Entity
public class Workout {

	// attribuutit
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long workoutid;
	private String title;
	private String description;
	private String instructions;
	private String duration;
	private String thumbup;
	private String thumbdown;
		
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="styleid")
	private Style style;
		
	// konstruktorit
	public Workout() { 
		// parametritön konstruktori
	}
		
	public Workout(String title, String description, String instructions, String duration, String thumbup, String thumbdown) {
		super();
		this.title = title;
		this.description = description;
		this.instructions = instructions;
		this.duration = duration;
		this.thumbup = thumbup;
		this.thumbdown = thumbdown;
	}

	// getterit
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
	public String getThumbup() {
		return thumbup;
	}
	
	
	//setterit
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
	public void setThumbup(String thumbup) {
		this.thumbup = thumbup;
	}
	public String getThumbdown() {
		return thumbdown;
	}
	public void setThumbdown(String thumbdown) {
		this.thumbdown = thumbdown;
	}
	
	// stylen set & get
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}

	// muut methodit
	@Override
	public String toString() {
		return "Workout [workoutid=" + workoutid + ", title=" + title + ", description=" + description
				+ ", instructions=" + instructions + ", duration=" + duration + ", thumbup=" + thumbup + ", thumbdown="
				+ thumbdown + ", style=" + style + "]";
	}
		
}
