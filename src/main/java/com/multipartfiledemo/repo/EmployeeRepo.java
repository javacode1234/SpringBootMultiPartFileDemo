package com.multipartfiledemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multipartfiledemo.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
