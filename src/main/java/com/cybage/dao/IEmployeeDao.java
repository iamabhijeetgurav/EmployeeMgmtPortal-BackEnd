package com.cybage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.entity.Employee;

@Repository
public interface IEmployeeDao extends JpaRepository<Employee, Long>{
	Employee findByEmail(String email);
	Employee findByMobileNo(String mobileNo);
	Employee findByEmployeeId(Long employeeId);
}
