package com.remindermeapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remindermeapp.user.CustomUser;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long>{
	
	List<CustomUser> findByEmailId(String emaildId);
	
	Optional<CustomUser> findOneByUserId(UUID uuid);
}
