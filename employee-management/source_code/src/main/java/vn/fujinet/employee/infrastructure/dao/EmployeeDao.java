package vn.fujinet.employee.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;

public interface EmployeeDao extends JpaRepository<EmployeeEntity, Long> {

}
