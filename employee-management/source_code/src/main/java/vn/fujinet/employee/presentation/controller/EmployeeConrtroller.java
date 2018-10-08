package vn.fujinet.employee.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.fujinet.employee.application.service.EmployeeService;

@RestController
//@RequestMapping("/employee")
public class EmployeeConrtroller {

	@RequestMapping("/")
	public String index()
	{
		return "Hello World";
	}
//	@Autowired
//	EmployeeService employeeService;
//	
//	@RequestMapping(value = "employee", method = RequestMethod.GET)
//	public ResponseEntity<List<EmployeeDto>> get()
//	{
//		return new ResponseEntity<T>(status)
//	}
//	
	
	
}
