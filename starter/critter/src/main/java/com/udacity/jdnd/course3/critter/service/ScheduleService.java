package com.udacity.jdnd.course3.critter.service;

import com.google.common.collect.Lists;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private ScheduleRepository repository;
    private PetService petService;
    private EmployeeService employeeService;

    public ScheduleService(ScheduleRepository repository, PetService petService, EmployeeService employeeService) {
        this.repository = repository;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    public Schedule saveSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = convertScheduleDTOToSchedule(scheduleDTO);
        return repository.save(schedule);
    }

    public List<Schedule> findAllSchedules(){
        return repository.findAll();
    }

    public List<Schedule> findScheduleByEmployee(long employeeId){
        Employee employee = employeeService.findEmployeeById(employeeId);
        return new ArrayList<>(repository.findByEmployeesIn(Arrays.asList(employee)));
    }

    public List<Schedule> findScheduleByPet(long petId){
        Pet pet = petService.findPetById(petId);
        return new ArrayList<>(repository.findByPetsIn(Arrays.asList(pet)));
    }

    public List<Schedule> findScheduleByPets(List<Pet> pets){
        return new ArrayList<>(repository.findByPetsIn(pets));
    }

    public Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        schedule.setEmployees(scheduleDTO.getEmployeeIds().stream()
                .map(employeeService::findEmployeeById).collect(Collectors.toList()));
        schedule.setPets(scheduleDTO.getPetIds().stream()
                .map(petService::findPetById).collect(Collectors.toList()));
        return schedule;
    }

}
