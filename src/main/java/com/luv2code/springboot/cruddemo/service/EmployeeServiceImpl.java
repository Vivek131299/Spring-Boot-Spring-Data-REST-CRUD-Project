package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    // Constructor Injection of our DAO
    @Autowired
    public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO theEmployeeDAO) {
    // Here, @Qualifier ANNOTATION, since we are using JPA API instead of Hibernate in this project,
    // and we hav both DAO implementations (for JPA and Hibernate as well), So the parameter above (EmployeeDAO)
    // doesn't know which API DAO Impl to use.
    // So, we are giving @Qualifier("employeeDAOJpaImpl") to tell that we want to use this Bean id (employeeDAOJpaImpl).
    // So now, it will use JPA Impl (employeeDAOJpaImpl is a Bean id for EmployeeDAOJpaImpl) without any error.

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
