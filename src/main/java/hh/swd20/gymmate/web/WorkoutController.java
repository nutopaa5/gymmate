package hh.swd20.gymmate.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.gymmate.domain.Style;
import hh.swd20.gymmate.domain.StyleRepository;
import hh.swd20.gymmate.domain.WorkoutRepository;
import hh.swd20.gymmate.domain.Workout;

@Controller
@CrossOrigin
public class WorkoutController {

	@Autowired
	private WorkoutRepository repository;
	
	@Autowired
	private StyleRepository srepository;

	// Show all workouts
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
    
    // Homepage
    @RequestMapping(value="/workoutlist", method = RequestMethod.GET)
    public String workoutList(Model model) {	
        model.addAttribute("workouts", repository.findAll());
        return "workoutlist";
    }
	
    // RESTful service to get all workouts
    @RequestMapping(value="/workouts", method = RequestMethod.GET)
    public @ResponseBody List<Workout> workoutlistRest() {	
        return (List<Workout>) repository.findAll();
    }
    
    // RESTful service to save a new workout 
    @RequestMapping(value="/workouts", method = RequestMethod.POST)
    public @ResponseBody Workout addWorkoutRest(@RequestBody Workout workout) {	
    	return repository.save(workout);
    }
    
    // RESTful service to get book by id
    @RequestMapping(value="/workout/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Workout> findWorkoutRest(@PathVariable("id") Long workoutId) {	
    	return repository.findById(workoutId);
    } 
    
    // Authorized addworkout-page to save data
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addworkout")
    public String addWorkout(Model model){
    	model.addAttribute("workout", new Workout());
    	model.addAttribute("styles", srepository.findAll());
        return "addworkout";
    }   
	
    // Save method for workouts
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Workout workout, BindingResult bindingResult, Model model){
    	model.addAttribute("styles", srepository.findAll());
    	//Check for errors in form
    	if (bindingResult.hasErrors()) {
            return "addworkout";
        }
        repository.save(workout);
        return "redirect:workoutlist";
    }

    // Delete method for workouts
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteWorkout(@PathVariable("id") Long workoutId, Model model) {
    	repository.deleteById(workoutId);
        return "redirect:../workoutlist";
    }
    
    
    // Thumb Up method for workouts
    @RequestMapping(value = "/thumbup/{id}")
    public String upWorkout(@PathVariable("id") Long workoutId, Long thumbup, Model model) {
    	//haetaan db :stä päivitettävä workout
    	Workout workout = repository.findById(workoutId).orElse(null);
    	//päivitetään workoutin thumbuppia + 1
    	workout.increaseThumbupByOne();
    	//tallennetaan repositoryn avulla päivitetty workout db :hen
    	repository.save(workout);
    	return "redirect:../workoutlist";
    }
    
    // Thumb Down method for workouts
    @RequestMapping(value = "/thumbdown/{id}")
    public String downWorkout(@PathVariable("id") Long workoutId, Long thumbdown, Model model) {
    	//haetaan db :stä päivitettävä workout
    	Workout workout = repository.findById(workoutId).orElse(null);
    	//päivitetään workoutin thumbuppia + 1
    	workout.increaseThumbdownByOne();
    	//tallennetaan repositoryn avulla päivitetty workout db :hen
    	repository.save(workout);
    	return "redirect:../workoutlist";
    }
    
    // Edit method for workouts
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/edit/{id}")
    public String editWorkout(@PathVariable("id") Long workoutId, Model model) {
    	model.addAttribute("workout", repository.findById(workoutId));
    	model.addAttribute("styles", srepository.findAll());
        return "edit";
    }
    
}
