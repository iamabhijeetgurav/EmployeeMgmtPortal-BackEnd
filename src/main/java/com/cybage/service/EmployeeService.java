package com.cybage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.IEmployeeDao;
import com.cybage.entity.Employee;
import com.cybage.exception.ResourceAlreadyExistException;
import com.cybage.exception.ResourceNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;

	public Employee addEmployee(Employee employee) throws ResourceAlreadyExistException {

		if (employeeDao.findByEmployeeId(employee.getEmployeeId()) != null) {
			throw new ResourceAlreadyExistException("Employee with same ID already exist");
		} else if (employeeDao.findByEmail(employee.getEmail()) != null) {
			throw new ResourceAlreadyExistException("A employee with this email already exist");
		} else if (employeeDao.findByMobileNo(employee.getMobileNo()) != null) {
			throw new ResourceAlreadyExistException("A employee with this mobile already exist");
		} else {
			return employeeDao.save(employee);
		}
	}
	
	public Employee findEmployeeByEmployeeId(Long employeeId) {
		Employee employeeToBeFound=employeeDao.findByEmployeeId(employeeId);
		if(employeeToBeFound!=null) {
			return employeeToBeFound;
		}else {
			throw new ResourceNotFoundException("Employee Id: "+employeeId+" does not exist");
		}
		
	}
}
