package com.remindermeapp.repository;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.remindermeapp.entity.BusinessUser;
import com.remindermeapp.entity.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long>{
	
	Optional<Reminder> findOneByReminderId(UUID uuid);
	//List<Reminder> findAllBySendTimeLessThanEqualAndSendTimeGreaterThanEqual(OffsetDateTime endDate, OffsetDateTime startDate);
	@Query("select r from Reminder r where r.sendTime >= :currentTime and r.sendTime <= :upperBoundTime")
    List<Reminder> findAllWithSendTimeAfter(
      @Param("currentTime") Date currentTime, @Param("upperBoundTime") Date upperBoundTime);
	
	List<Reminder> findAllByBusinessUser(BusinessUser businessUser);
}
