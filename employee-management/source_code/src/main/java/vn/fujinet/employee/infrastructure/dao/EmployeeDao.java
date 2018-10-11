package vn.fujinet.employee.infrastructure.dao;

import org.springframework.data.repository.CrudRepository;

import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;

public interface EmployeeDao extends CrudRepository<EmployeeEntity, String>{

}
