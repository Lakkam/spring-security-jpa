package com.lakkam.springsecurityjpa;

 import org.springframework.data.jpa.repository.JpaRepository;

import com.lakkam.springsecurityjpa.models.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	
	public Users findByUserName(String username);
	
	

}
