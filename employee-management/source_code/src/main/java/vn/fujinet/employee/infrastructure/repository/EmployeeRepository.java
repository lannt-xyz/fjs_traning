package vn.fujinet.employee.infrastructure.repository;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.fujinet.employee.domain.model.Employee;
import vn.fujinet.employee.domain.model.EmployeeList;
import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;

@Repository
public class EmployeeRepository {

	@Autowired
	private CrudRepository<EmployeeEntity, Integer> crudRepository;

	public EmployeeList selectAllEmployee() {
		return StreamSupport.stream(crudRepository.findAll().spliterator(), false)
				.map(Employee::fromEntity)
				.collect(collectingAndThen(toList(), EmployeeList::new));

	}
}
