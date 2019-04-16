package hh.swd20.gymmate.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

// Style repository config
public interface StyleRepository extends CrudRepository<Style, Long> {

    List<Style> findByName(String name);
    
}