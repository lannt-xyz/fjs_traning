package vn.fujinet.employee.domain.model;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.collectingAndThen;

import vn.fujinet.employee.presentation.dto.EmployeeDto;

public class EmployeeList {

	private int min = 1000;
	private int max = 50000;

    private List<Employee> employees;

    /**
     * Define constructor.
     *
     * @param employees
     */
    public EmployeeList(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Return a list EmployeeDto converted from Employee.
     *
     * @return a list EmployeeDto
     */
    public List<EmployeeDto> toDtoes() {
        return employees.stream()
                .map(Employee::toDto)
                .collect(toList());
    }

    /**
     * Return a list employees with salary was filtered between min and max.
     *
     * @return a list employees
     */
    public EmployeeList findBySalary() {
    	return employees.stream()
    			.filter(e -> beetween(e.getSalary()))
    			.collect(collectingAndThen(toList(), EmployeeList::new));
    }

    public boolean beetween(double Salary) {
    	return Salary > min && Salary < max;
    }
    /**
     * Return a list employees which was filtered by firstName.
     *
     * @param firstName
     * @return a list employees
     */
    public EmployeeList findByFirstName(String firstName) {
    	return employees.stream()
    			.filter(e -> e.getFirstName().equalsIgnoreCase(firstName))
    			.collect(collectingAndThen(toList(), EmployeeList::new));
    }

    /**
     * Return a list employees which was filtered by lastName.
     *
     * @param lastName
     * @return a list employees which was filtered by lastName
     */
    public EmployeeList findByLastName(String lastName) {
    	return employees.stream()
    			.filter(e -> e.getLastName().equalsIgnoreCase(lastName))
    			.collect(collectingAndThen(toList(), EmployeeList::new));
    }
}
