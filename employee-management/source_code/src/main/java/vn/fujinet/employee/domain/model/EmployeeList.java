package vn.fujinet.employee.domain.model;

import java.util.List;
import java.util.stream.Collectors;

import vn.fujinet.employee.presentation.dto.EmployeeDto;

public class EmployeeList {

	private List<Employee> employees;

	public EmployeeList(List<Employee> employees) {
		this.employees = employees;
	}

	public EmployeeList getHighSalary() {
		this.employees = employees.stream()
				.filter(e -> e.calculateSalary() >= 500)
				.collect(Collectors.toList());
		return this;
	}

	public EmployeeList getLowSalary() {
		this.employees = employees.stream()
				.filter(e -> e.calculateSalary() < 500)
				.collect(Collectors.toList());
		return this;
	}

	public List<EmployeeDto> toDtoes() {
		return employees.stream()
				.map(Employee::toDto)
				.collect(Collectors.toList());
	}
}
