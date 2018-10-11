package vn.fujinet.employee.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.fujinet.employee.application.service.EmployeeService;
import vn.fujinet.employee.presentation.dto.EmployeeDto;

@RestController
@RequestMapping("/api")
public class EmployeeConrtroller {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeDto>> getAll() {
		return new ResponseEntity<List<EmployeeDto>>(employeeService.getEmployee(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public 	EmployeeDto findByID(@PathVariable("id") long id) {
		EmployeeDto employee= (EmployeeDto) employeeService.getByIDEmployee();
		if(employee == null) {
			ResponseEntity.notFound().build();
		}
		return employee;
	}
	
	



}
