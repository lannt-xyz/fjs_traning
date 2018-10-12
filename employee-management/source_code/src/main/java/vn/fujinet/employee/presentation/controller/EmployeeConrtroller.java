package vn.fujinet.employee.presentation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import vn.fujinet.employee.application.service.EmployeeService;
import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;
import vn.fujinet.employee.infrastructure.repository.EmplopyeeRepository;
@RestController
@RequestMapping("/employee")
public class EmployeeConrtroller {
	public static Logger logger = LoggerFactory.getLogger(EmployeeConrtroller.class);
	
	@Autowired
	EmplopyeeRepository employeeservice;
	
	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	public ResponseEntity<List<	EmployeeEntity>> listAllContact(){
		List<EmployeeEntity> listContact= employeeservice.findAll();
		if(listContact.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<	EmployeeEntity>>(listContact, HttpStatus.OK);
	}
//	
	
	
}
