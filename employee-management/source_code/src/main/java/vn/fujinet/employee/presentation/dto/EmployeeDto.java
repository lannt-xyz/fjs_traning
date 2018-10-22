package vn.fujinet.employee.presentation.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class EmployeeDto {
	public int id;
	public String firstName;
	public String lastName;
	public Date dateOfBirth;
	public double salary;
}
