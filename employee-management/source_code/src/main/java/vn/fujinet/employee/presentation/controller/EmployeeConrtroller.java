package vn.fujinet.employee.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.fujinet.employee.application.service.EmployeeService;
import vn.fujinet.employee.domain.model.Employee;
import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;
import vn.fujinet.employee.presentation.dto.EmployeeDto;

@RestController
@RequestMapping("/employees")
public class EmployeeConrtroller {

    @Autowired

    private EmployeeService employeeService;

    //Get all employees
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return new ResponseEntity<List<EmployeeDto>>(employeeService.getAll(), HttpStatus.OK);
    }
    //Get detail employee by "id"
    @RequestMapping(value = "{id}")
    public ResponseEntity<EmployeeEntity> getByID(@PathVariable("id") int id) {
    	return new ResponseEntity<EmployeeEntity>(employeeService.getById(id), HttpStatus.OK);
    }
    //Get a list of employees filterd by salary between max and min
    @RequestMapping(value = "/salary")
	public ResponseEntity<List<EmployeeDto>> getBySalary() {
		return new ResponseEntity<List<EmployeeDto>>(employeeService.getBySalary(), HttpStatus.OK);
	}
    //Get a list of employees filtered by firstName
    @RequestMapping(params = {"firstname"})
    public ResponseEntity<List<EmployeeDto>> getByFirstName(@RequestParam("firstname") String firstname) {
    	return new ResponseEntity<List<EmployeeDto>>(employeeService.getByFirstName(firstname), HttpStatus.OK);
    }
    //Get a list of employees filtered by lastName
    @RequestMapping(params = {"lastname"})
    public ResponseEntity<List<EmployeeDto>> getByLastName(@RequestParam("lastname") String lastname) {
    	return new ResponseEntity<List<EmployeeDto>>(employeeService.getByLastName(lastname), HttpStatus.OK);
    }
    //Delete employee by "id"
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") int id) {
    	employeeService.deleteById(id);
    }
    //Update employee
    @PutMapping("{id}")
    public void updateEmployee(@RequestBody Employee employee,@PathVariable("id") int id) {
    	employeeService.updateEmployee(employee, id);
    }

}
