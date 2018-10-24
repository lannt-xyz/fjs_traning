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

    /**
     * Get all employees.
     *
     * @return a list all of Employees
     */
    public List<EmployeeDto> getAll() {
        return employeeRepository.selectAll().toDtoes();
    }

    /**
     * Get by id.
     *
     * @param id
     * @return a employee by "id"
     */
    public EmployeeEntity getById(int id) {
    	return employeeRepository.selectByID(id).orElseThrow(() -> new RuntimeException("ID Not Found"));
    }

    /**
     * Get by salary.
     *
     * @return a list employees by Salary
     */
    public List<EmployeeDto> getBySalary() {
    	return employeeRepository.selectAll().findBySalary().toDtoes();
    }

    /**
     * Get by first name.
     *
     * @param firstName
     * @return a list employees which was filtered by firstName
     */
    public List<EmployeeDto> getByFirstName(String firstName) {
    	return employeeRepository.selectAll().findByFirstName(firstName).toDtoes();
    }

    public List<EmployeeDto> getByLastName(String lastName) {
    	return employeeRepository.selectAll().findByLastName(lastName).toDtoes();
    }

    /**
     *  Delete employee by "id".
     *
     * @param id must not be {@literal null}
     */
    public void deleteById(int id) {
    	employeeRepository.delete(id);
    }

    /**
     * Update employee.
     *
     * @param employee
     * @param id must not be {@literal null}
     */
    public void updateEmployee(Employee employee, int id) {
    	employeeRepository.update(employee, id);
    }

    /*
     * Add employee
     */
    public void addEmployee(Employee employee, int id) {
    	employeeRepository.add(employee, id);
    }
}
