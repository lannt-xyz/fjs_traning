package vn.fujinet.employee.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;

public interface EmployeeDao extends JpaRepository<EmployeeEntity, Integer>{

	@Query("select e from EmployeeEntity where e .firstname = :firstname or e.lastname = :lastname")
	public EmployeeEntity findByName(@Param("lastname") String lastname);
}
