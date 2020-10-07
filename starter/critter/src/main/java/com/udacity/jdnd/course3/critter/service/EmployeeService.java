package com.udacity.jdnd.course3.critter.service;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee persistedEmployee = repository.save(convertEmployeeDTOToEmployee(employeeDTO));
        return persistedEmployee;
    }

    public List<Employee> getAllEmployees() {
        return (List<Employee>) repository.findAll();
    }

    public List<Employee> findAllBySkillsAndDaysAvailable(EmployeeRequestDTO employeeRequestDTO) {
        Set<EmployeeSkill> skillsNeeded = employeeRequestDTO.getSkills();
        Set<DayOfWeek> dayNeeded = Sets.newHashSet(employeeRequestDTO.getDate().getDayOfWeek());
        Set<Employee> unFiltered = repository.findBySkillsInAndDaysAvailableIn(skillsNeeded, dayNeeded);
        List<Employee> filtered = new ArrayList<>();
        for (Employee e : unFiltered) {
            if(e.getSkills().containsAll(skillsNeeded) && e.getDaysAvailable().containsAll(dayNeeded))
                filtered.add(e);
        }
        return filtered;
    }

    public Employee findEmployeeById(Long id) {
        Optional<Employee> employeeOptional = repository.findById(id);
        if (employeeOptional.isPresent()) return employeeOptional.get();
        throw new AppException();
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId){
        Employee employee = findEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

}
