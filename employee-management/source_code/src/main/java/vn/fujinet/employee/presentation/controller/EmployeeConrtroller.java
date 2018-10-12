package vn.fujinet.employee.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.fujinet.employee.application.service.EmployeeService;
import vn.fujinet.employee.presentation.dto.EmployeeDto;

@RestController
@RequestMapping("/employees")
public class EmployeeConrtroller {

	@Autowired

	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET)

	public ResponseEntity<List<EmployeeDto>> getAll() {
		return new ResponseEntity<List<EmployeeDto>>(employeeService.getAll(), HttpStatus.OK);
	}
	@RequestMapping(value = "/lastname", method = RequestMethod.GET)
	
	public ResponseEntity<List<EmployeeDto>> getByName() {
		return new ResponseEntity<List<EmployeeDto>>(employeeService.getByName(), HttpStatus.OK);
	}
}
