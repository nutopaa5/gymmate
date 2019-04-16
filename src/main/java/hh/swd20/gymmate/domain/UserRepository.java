package hh.swd20.gymmate.domain;

import org.springframework.data.repository.CrudRepository;

// User repository config
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	User findByEmail(String email);
	
}