package vn.fujinet.employee.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int id;

	@Column(name = "firstname")
	public String firstName;

	@Column(name = "lastname")
	public String lastName;

	@Column(name = "dateofbirth")
	public String dayOfBirth;

	@Column(name = "salary")
	public double salary;

}
