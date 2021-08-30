package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

// In this, we are using Standard JPA API instead of Hibernate
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    // constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a query
        Query theQuery = entityManager.createQuery("from Employee");

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // get the employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        // find() is similar to Hibernate's get() or load().

        // return the employee
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {

        Employee dbEmployee = entityManager.merge(theEmployee);
        // merge() is similar to Hibernate's saveOrUpdate().
        // So, id id=0, then it will insert/save OR else if id is given then it will update.
        // AND JPA's persist() is similar to Hibernate's save().

        // update with id from db... so we can get generated id for save/insert.
        theEmployee.setId(dbEmployee.getId());

    }

    @Override
    public void deleteById(int theId) {

        // delete object with primary key
        Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", theId);

        theQuery.executeUpdate();

        // We can use JPA's remove() which is similar to Hibernate's delete().
    }
}
