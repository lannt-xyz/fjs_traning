package vn.fujinet.employee.domain.model;

import java.util.List;
import java.util.stream.Collectors;

import vn.fujinet.employee.presentation.dto.EmployeeDto;

public class EmployeeList {

	int min = 1000;
	int max = 50000;

    private List<Employee> employees;

    //Define constructor
    public EmployeeList(List<Employee> employees) {
        this.employees = employees;
    }
    //Return a list EmployeeDto
    public List<EmployeeDto> toDtoes() {
        return employees.stream()
                .map(Employee::toDto)
                .collect(Collectors.toList());
    }
    //Return a list employees with salary was filtered between min and max
    public EmployeeList findBySalary() {
    	this.employees = employees.stream()
    			.filter(e -> e.getSalary() > min && e.getSalary() < max)
    			.collect(Collectors.toList());
    	return this;
    }
    //Return a list employees which was filtered by firstName
    public EmployeeList findByFirstName(String firstName) {
    	this.employees = (List<Employee>) employees.stream()
    			.filter(e -> e.getFirstName().equalsIgnoreCase(firstName))
    			.collect(Collectors.toList());
    	return this;
    }
    //Return a list employees which was filtered by lastName
    public EmployeeList findByLastName(String lastName) {
    	this.employees = (List<Employee>) employees.stream()
    			.filter(e -> e.getLastName().equalsIgnoreCase(lastName))
    			.collect(Collectors.toList());
    	return this;
    }
}
