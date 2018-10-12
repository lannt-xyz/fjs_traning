package vn.fujinet.employee.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;
import vn.fujinet.employee.presentation.dto.EmployeeDto;

@Data
@Builder
@AllArgsConstructor
public class Employee {
	public String firstName;
	private String lastName;
	private String dateOfBirth;
	private double salary;

	public static Employee fromEntity(EmployeeEntity employeeEntity) {
		return Employee.builder()
				.firstName(employeeEntity.firstName)
				.lastName(employeeEntity.lastName)
				.dateOfBirth(employeeEntity.dayOfBirth)
				.salary(employeeEntity.salary)
				.build();
	}

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
