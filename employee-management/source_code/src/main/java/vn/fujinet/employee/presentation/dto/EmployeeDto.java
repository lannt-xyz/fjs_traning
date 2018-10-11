package vn.fujinet.employee.presentation.dto;

public class EmployeeDto {
	public int id;
	public String firstName;
	public String lastName;
	public String dayOfBirth;
	public double salary;
	
	public EmployeeDto() {

	}

	public EmployeeDto(int id, String firstName, String lastName, String dayOfBirth, double salary) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dayOfBirth = dayOfBirth;
		this.salary = salary;
	}


}
