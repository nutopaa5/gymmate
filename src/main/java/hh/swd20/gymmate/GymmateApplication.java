package hh.swd20.gymmate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.gymmate.domain.Style;
import hh.swd20.gymmate.domain.StyleRepository;
import hh.swd20.gymmate.domain.User;
import hh.swd20.gymmate.domain.UserRepository;
import hh.swd20.gymmate.domain.Workout;
import hh.swd20.gymmate.domain.WorkoutRepository;

@SpringBootApplication
public class GymmateApplication {
	private static final Logger log = LoggerFactory.getLogger(GymmateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GymmateApplication.class, args);
	}

	@Bean
	public CommandLineRunner workoutDemo(WorkoutRepository wrepo, StyleRepository srepo, UserRepository urepo) {
		return (args) -> {
			log.info("Save sample workouts");
			
			// Create workout styles
			srepo.save(new Style("Strength"));
			srepo.save(new Style("Muscle Mass"));
			srepo.save(new Style("Cardio"));
			
			// Create sample workouts
			wrepo.save(new Workout("Arnold's Golden Six", "Testing","Do this and this ex.", "1h20m", 18L, 6L, srepo.findByName("Strength").get(0)));
			wrepo.save(new Workout("Arnold's Golden Six 2", "Testing 2","Then do this and this ex.", "2h10m", 10L, 2L, srepo.findByName("Muscle Mass").get(0)));
			
			// Create users: admin/admin & user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@user.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@admin.com", "ADMIN");
			urepo.save(user1);
			urepo.save(user2);
			
			log.info("Fetch all workouts");
			for (Workout workout : wrepo.findAll()) {
				log.info(workout.toString());
			}
		
		};
}
	
}
