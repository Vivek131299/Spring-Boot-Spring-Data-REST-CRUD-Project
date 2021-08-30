package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// This is our Spring Data JPA Repository instead of Hibernate or JPA API.
// This provides all basic CRUD operations for free.
// So we don't need to write any methods for CRUD operations.

/*@RepositoryRestResource(path = "members")*/ // So now, we need to go to /magic-api/members instead of /magic-api/employees.
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
// Above, 'Employee' is a Entity type and 'Integer' is a primary key type.

    // that's it... no need to write any code.
    // Also, there is no need to write any Implementation class of this Interface.
}
