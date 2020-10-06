package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//TODO: See where to add @Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
