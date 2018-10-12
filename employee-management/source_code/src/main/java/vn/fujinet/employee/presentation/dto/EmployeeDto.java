package vn.fujinet.employee.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class EmployeeDto {
	//public Integer id;
	public String firstName;
	public String lastName;
	public String dateOfBirth;
	public double salary;
	
}
