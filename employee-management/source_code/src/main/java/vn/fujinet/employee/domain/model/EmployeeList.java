package vn.fujinet.employee.domain.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import vn.fujinet.employee.presentation.dto.EmployeeDto;

public class EmployeeList {

	private List<Employee> employees;

	public EmployeeList(List<Employee> employees) {
		this.employees = employees;
	}	

	public EmployeeList getHighSalary() {
		return employees.stream()
				.filter(e -> e.calculateSalary() >= 500)
				.collect(collectingAndThen(toList(), EmployeeList::new));
	}

	public List<EmployeeDto> toDtoes() {
		return employees.stream()
				.map(Employee::toDto)
				.collect(Collectors.toList());
	}
	
	@Query("select e from EmployeeEntity where e.lastname = :lastname")
	public EmployeeList getByName(@Param("lastname") String lastname)
	{
		return (EmployeeList) employees;
	}

	public List<EmployeeDto> getByName() {
		return employees.stream()
				.map(Employee::toDto)
				.collect(Collectors.toList());
	}
}
