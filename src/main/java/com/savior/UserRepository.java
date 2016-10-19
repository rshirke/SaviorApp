package com.savior;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserFB, Long> {

	List<UserFB> findByFacebookid(String facebookid);
}
