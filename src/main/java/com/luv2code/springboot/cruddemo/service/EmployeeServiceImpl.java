package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    // Constructor Injection of our DAO
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        // delegate the call to DAO
        return employeeDAO.findAll();
    }

    @Transactional
    @Override
    public Employee findById(int theId) {
        // delegate the call to DAO
        return employeeDAO.findById(theId);
    }

    @Transactional
    @Override
    public void save(Employee theEmployee) {
        // delegate the call to DAO
        employeeDAO.save(theEmployee);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        // delegate the call to DAO
        employeeDAO.deleteById(theId);
    }
}
