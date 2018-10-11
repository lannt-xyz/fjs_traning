package vn.fujinet.employee.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fujinet.employee.domain.model.Employee;
import vn.fujinet.employee.domain.model.converter.EmployeeConverter;
import vn.fujinet.employee.infrastructure.repository.EmployeeRepository;
import vn.fujinet.employee.presentation.dto.EmployeeDto;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<EmployeeDto> getEmployee() {
		List<Employee> employees = employeeRepository.selectAllEmployee().getEmployee();

		return employees.stream()
				.map(EmployeeConverter::convert)
				.collect(Collectors.toList());
	}

	public EmployeeDto getByIDEmployee() {
		Employee employees = employeeRepository.selecByIDEmployee().getByIDEmployee();
		return null;
	}
	
//	public List<EmployeeDto> getHighSalary() {
//		List<Employee> employees = employeeRepository.selectAllEmployee().getHighSalary();
//
//		return employees.stream()
//				.map(EmployeeConverter::convert)
//				.collect(Collectors.toList());
//	}
//
//	public List<EmployeeDto> getLowSalary() {
//		List<Employee> employees = employeeRepository.selectAllEmployee().getLowSalary();
//
//		return employees.stream()
//				.map(EmployeeConverter::convert)
//				.collect(Collectors.toList());
//	}
	
	

}
