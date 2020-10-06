package com.udacity.jdnd.course3.critter;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import static com.udacity.jdnd.course3.critter.CritterFunctionalTest.*;

public class SimpleTests {


    @Test
    public void customerDTO(){
        System.out.println("howdy");
        CustomerDTO customerDTO = createCustomerDTO();
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        System.out.println("Done");

    }

    @Test
    public void employeeDTO(){
        EmployeeDTO employeeDTO = createEmployeeDTO();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee); // NO copia todo, mejorar transformacion.
        System.out.println("Done");
    }
}
