package vn.fujinet.employee.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fujinet.employee.infrastructure.repository.EmployeeRepository;
import vn.fujinet.employee.presentation.dto.EmployeeDto;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<EmployeeDto> getAll() {
		return employeeRepository.selectAllEmployee().toDtoes();
	}

	public List<EmployeeDto> getHighSalary() {
		return employeeRepository.selectAllEmployee()
				.getHighSalary()
				.toDtoes();
	}

	public List<EmployeeDto> getLowSalary() {
		return employeeRepository.selectAllEmployee()
				.getLowSalary()
				.toDtoes();
	}
}
