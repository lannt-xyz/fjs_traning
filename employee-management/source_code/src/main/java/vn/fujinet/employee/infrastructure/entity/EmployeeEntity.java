package vn.fujinet.employee.infrastructure.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    public int id;

    @Column(name = "FIRST_NAME")
    public String firstName;

    @Column(name = "LAST_NAME")
    public String lastName;

    @Column(name = "DATE_OF_BIRTH")
    public Date dateOfBirth;

    @Column(name = "SALARY")
    public double salary;
}
