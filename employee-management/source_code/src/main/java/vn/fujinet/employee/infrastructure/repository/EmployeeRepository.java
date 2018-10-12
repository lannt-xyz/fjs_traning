package vn.fujinet.employee.infrastructure.repository;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.fujinet.employee.domain.model.Employee;
import vn.fujinet.employee.domain.model.EmployeeList;
import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;

@Repository
public class EmployeeRepository {

	@Autowired
	private JpaRepository<EmployeeEntity, Integer> jpaRepository;

	public EmployeeList selectAll() {
		return StreamSupport.stream(jpaRepository.findAll().spliterator(), false)
				.map(Employee::fromEntity)
				.collect(collectingAndThen(toList(), EmployeeList::new));
	}
}
