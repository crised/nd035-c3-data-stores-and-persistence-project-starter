package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
//TODO: See where to add @Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Set<Schedule> findByEmployeesIn(List<Employee> employees);
    Set<Schedule> findByPetsIn(List<Pet> pets);

}
