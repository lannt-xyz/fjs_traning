package vn.fujinet.employee.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;

//Jpa refers Create, Read, Update, Delete
public interface EmployeeDao extends JpaRepository<EmployeeEntity, Integer>{

}
