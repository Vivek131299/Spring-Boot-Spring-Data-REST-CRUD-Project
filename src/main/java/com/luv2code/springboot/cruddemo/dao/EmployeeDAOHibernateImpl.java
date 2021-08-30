package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
    // EntityManager is automatically created by Spring Boot, we are just injecting it by constructor injection.
        entityManager = theEntityManager;
    }

    @Override
    @Transactional // Handles transaction management, so we don't have to manually start and commit transaction.
    public List<Employee> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // .unwrap() for getting current hibernate session.

        // create a query, we are using native Hibernate API for this
        Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }
}
