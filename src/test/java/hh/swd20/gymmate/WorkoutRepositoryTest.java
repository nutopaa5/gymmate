package hh.swd20.gymmate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.gymmate.domain.StyleRepository;
import hh.swd20.gymmate.domain.Style;
import hh.swd20.gymmate.domain.Workout;
import hh.swd20.gymmate.domain.WorkoutRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WorkoutRepositoryTest {

    @Autowired
    private WorkoutRepository repository1;
    
    @Autowired
    private StyleRepository repository2;

    //Workout class test
    @Test
    public void findByTitleShouldReturnWorkout() {
        List<Workout> workouts = repository1.findByTitle("Arnold's Golden Six");
        
        assertThat(workouts).hasSize(1);
        assertThat(workouts.get(0).getDescription()).isEqualTo("Testing");
    }
    
    //Style class test
    @Test
    public void findByNameShouldReturnStyle() {
        List<Style> styles = repository2.findByName("Strength");
        
        assertThat(styles).hasSize(1);
        assertThat(styles.get(0).getName()).isEqualTo("Strength");
    }
    
    //Adding workout test
    @Test
    public void addWorkout() {
    	Workout workout = new Workout("Arnold's Golden Six 3", "Testing 3","Then do this and this ex.", "2h10m", 10L, 2L, repository2.findByName("Muscle Mass").get(0));
    	repository1.save(workout);
    	assertThat(workout.getWorkoutid()).isNotNull();
    }
    
    //Adding category test
    @Test
    public void addStyle() {
    	Style style = new Style("HIIT");
    	repository2.save(style);
    	assertThat(style.getStyleid()).isNotNull();
    }
    
    //Workout delete test
    @Test
    public void deleteWorkout() {
    	long workoutId = 4;
    	repository1.deleteById(workoutId);
    }
    
    //Style delete test
    @Test
    public void deleteStyle() {
    	long styleId = 1;
    	repository2.deleteById(styleId);
    }
    
}