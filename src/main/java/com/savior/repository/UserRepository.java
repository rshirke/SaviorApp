package com.savior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.savior.model.security.User;

/**
 * Created by stephan on 20.03.16.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
