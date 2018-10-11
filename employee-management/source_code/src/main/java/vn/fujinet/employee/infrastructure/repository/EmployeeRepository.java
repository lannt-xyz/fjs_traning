package vn.fujinet.employee.infrastructure.repository;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.fujinet.employee.domain.model.EmployeeList;
import vn.fujinet.employee.domain.model.converter.EmployeeConverter;
import vn.fujinet.employee.infrastructure.dao.EmployeeDao;

@Repository
public class EmployeeRepository {
	@Autowired
	private EmployeeDao employeeDto;

	public EmployeeList selectAllEmployee() {
		return StreamSupport.stream(employeeDto.findAll().spliterator(), false).map(EmployeeConverter::convert)
				.collect(collectingAndThen(toList(), EmployeeList::new));

	}
	public EmployeeList selecByIDEmployee() {
		
		return null;

	}
	 
}
