package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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

    public Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
//        for(Long employeeId: scheduleDTO.getEmployeeIds())
//            employees.add(employeeService.findEmployeeById(employeeId));
        schedule.setEmployees(scheduleDTO.getEmployeeIds().stream()
                .map(employeeService::findEmployeeById).collect(Collectors.toList()));
        schedule.setPets(scheduleDTO.getPetIds().stream()
                .map(petService::findPetById).collect(Collectors.toList()));
        return schedule;
    }

}
