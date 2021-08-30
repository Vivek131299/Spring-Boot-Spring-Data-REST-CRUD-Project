package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // Constructor Injection of our DAO
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {

        employeeRepository = theEmployeeRepository;
    }

    // No need to write @Transactional Annotation since we are using Spring Data JPA Repository,
    // it provides this functionality.

    @Override
    public List<Employee> findAll() {
        // delegate the call to DAO
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {

        Optional<Employee> result = employeeRepository.findById(theId);
        // Here, Optional is a different pattern, so instead of manually checking for null, we use Optional
        // to see if given value is present. JPA Repository uses Optional.
        // So, we can check if the value is present or not by isPresent() method.
        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        return theEmployee;
    }


    @Override
    public void save(Employee theEmployee) {
        // delegate the call to DAO
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        // delegate the call to DAO
        employeeRepository.deleteById(theId);
    }
}
