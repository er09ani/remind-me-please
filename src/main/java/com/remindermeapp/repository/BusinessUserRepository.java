package com.remindermeapp.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remindermeapp.entity.BusinessUser;
import com.remindermeapp.user.CustomUser;

@Repository
public interface BusinessUserRepository extends JpaRepository<BusinessUser, Long>{
	
	Optional<BusinessUser> findByCustomUser(CustomUser customUser);
	Optional<BusinessUser> findOneByBusinessUserId(UUID uuid);
}
