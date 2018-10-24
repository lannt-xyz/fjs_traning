package vn.fujinet.employee.domain.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;
import vn.fujinet.employee.presentation.dto.EmployeeDto;

@Data
@Builder
@AllArgsConstructor
public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private double salary;

	/**
	 * Convert EmployeeEntity to Employee using Builder.
	 * 
	 * @param employeeEntity
	 * @return Employee convert from EmployeeEntity
	 */
	public static Employee fromEntity(EmployeeEntity employeeEntity) {
		return Employee.builder()
				.firstName(employeeEntity.firstName)
				.lastName(employeeEntity.lastName)
				.dateOfBirth(employeeEntity.dateOfBirth)
				.salary(employeeEntity.salary)
				.build();
	}

	/**
	 * Convert Employee to EmployeeDto using Builder.
	 * 
	 * @return EmployeeDto convert from Employee
	 */
	public EmployeeDto toDto() {
		return EmployeeDto.builder()
				.firstName(this.firstName)
				.lastName(this.lastName)
				.dateOfBirth(this.dateOfBirth)
				.salary(this.salary)
				.build();
	}
	public double calculateSalary() {
		// TODO: Bien them xu ly sau
		return 0;
	}
}
