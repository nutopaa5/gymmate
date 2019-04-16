package hh.swd20.gymmate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.gymmate.web.WorkoutController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GymmateApplicationTests {

    @Autowired
    private WorkoutController controller;

    // Controller test
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }	
    
}
