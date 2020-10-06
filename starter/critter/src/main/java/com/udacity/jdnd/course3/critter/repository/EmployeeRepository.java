package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Set<Employee> findAllBySkillsIn(Set<EmployeeSkill> skills);
    Set<Employee> findByDaysAvailableIn(Set<DayOfWeek> daysAvailable);
    Set<Employee> findBySkillsInAndDaysAvailableIn(Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable);
}
