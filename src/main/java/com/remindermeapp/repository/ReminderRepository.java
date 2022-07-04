package com.remindermeapp.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remindermeapp.entity.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long>{
	
	Optional<Reminder> findOneByReminderId(UUID uuid);
}
