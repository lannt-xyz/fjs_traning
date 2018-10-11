package vn.fujinet.employee.domain.model.converter;

import org.springframework.beans.BeanUtils;

import vn.fujinet.employee.domain.model.Employee;
import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;
import vn.fujinet.employee.presentation.dto.EmployeeDto;

public class EmployeeConverter {

	public static Employee convert(EmployeeEntity employeeEntity){
		
		return new Employee();
	}
	public static EmployeeDto convert(Employee employee) {
		
		EmployeeDto employeeDto = new EmployeeDto();
		BeanUtils.copyProperties(employee, employeeDto);
		return employeeDto;
	}
}
