package vn.fujinet.employee.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
	@Entity
	@Table(name="employees")
	public class EmployeeEntity {
	@Id 
//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="firstname")
	private String firstName;
	@Column(name="lastname")
	private String lastName;
	@Column(name="salary")
	private double salary;
	@Column(name="dateofbirth")
	 private String dateOfBirth;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateOfBirth() {
	return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
	}
	public EmployeeEntity() {
	}
	}