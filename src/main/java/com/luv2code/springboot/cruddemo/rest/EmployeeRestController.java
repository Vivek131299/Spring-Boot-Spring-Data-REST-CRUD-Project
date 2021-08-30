package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // inject employee service using constructor injection
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {

        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeID}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST /employees - adding a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
    // @RequestBody is used to handle and binding the incoming JSON data from request body to our Employee object.

        // Also, just in case, if user pass an id in JSON, then we set it to 0. because if id=0 then our saveOrUpdate() will insert new employee.
        theEmployee.setId(0);

        employeeService.save(theEmployee);

        return theEmployee;
    } // We tested this method in Postman software.

    // add mapping for PUT /employees - Updating existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        employeeService.save(theEmployee);

        return theEmployee;
    } // We tested this method in Postman software.

    // add mapping for DELETE /employees/{employeeId} - Deleting the employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        // throw exception if null
        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    } // We tested this method in Postman software.
}