package vn.fujinet.employee.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping()
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return new ResponseEntity<List<EmployeeDto>>(employeeService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}")
    public ResponseEntity<EmployeeEntity> getByID(@PathVariable("id") int id) {
    	return new ResponseEntity<EmployeeEntity>(employeeService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/salary")
	public ResponseEntity<List<EmployeeDto>> searchBySalary() {
		return new ResponseEntity<List<EmployeeDto>>(employeeService.getBySalary(), HttpStatus.OK);
	}

    @RequestMapping(params = {"firstname"})
    public ResponseEntity<List<EmployeeDto>> searchByFirstName(@RequestParam("firstname") String firstname) {
    	return new ResponseEntity<List<EmployeeDto>>(employeeService.getByFirstName(firstname), HttpStatus.OK);
    }

    @RequestMapping(params = {"lastname"})
    public ResponseEntity<List<EmployeeDto>> searchByLastName(@RequestParam("lastname") String lastname) {
    	return new ResponseEntity<List<EmployeeDto>>(employeeService.getByLastName(lastname), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") int id) {
    	employeeService.deleteById(id);
    }

    @PutMapping("{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable("id") int id) {
    	employeeService.updateEmployee(employee, id);
    }

    @PostMapping("{id}")
    public void addEmployee(@RequestBody Employee employee, @PathVariable("id") int id) {
    	employeeService.addEmployee(employee, id);
    }
}
