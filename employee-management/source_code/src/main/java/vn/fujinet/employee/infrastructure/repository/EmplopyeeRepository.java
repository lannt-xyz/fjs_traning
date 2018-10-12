package vn.fujinet.employee.infrastructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;

	@Repository
	public interface EmplopyeeRepository extends JpaRepository<EmployeeEntity, Long>{
		
	}

	

