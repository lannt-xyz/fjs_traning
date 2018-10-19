package vn.fujinet.employee.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.fujinet.employee.domain.model.Employee;
import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;
import vn.fujinet.employee.infrastructure.repository.EmployeeRepository;
import vn.fujinet.employee.presentation.dto.EmployeeDto;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    //Return a list all of Employees
    public List<EmployeeDto> getAll() {
        return employeeRepository.selectAll().toDtoes();
    }
    //Return a employee by "id"
    public EmployeeEntity getById(int id) {
    	return employeeRepository.selectByID(id).orElseThrow(() -> new RuntimeException("ID Not Found"));
    }
    //Return a list employees by Salary
    public List<EmployeeDto> getBySalary() {
    	return employeeRepository.selectAll().findBySalary().toDtoes();
    }
    //Return a list employees which was filtered by firstName
    public List<EmployeeDto> getByFirstName(String firstName) {
    	return employeeRepository.selectAll().findByFirstName(firstName).toDtoes();
    }
    //Return a list employees which was filtered by lastName
    public List<EmployeeDto> getByLastName(String lastName) {
    	return employeeRepository.selectAll().findByLastName(lastName).toDtoes();
    }
    //Delete employee by "id"
    public void deleteById(int id) {
    	employeeRepository.delete(id);
    }
    //Update employee
    public void updateEmployee(Employee employee, int id) {
    	employeeRepository.update(employee, id);
    }
}
