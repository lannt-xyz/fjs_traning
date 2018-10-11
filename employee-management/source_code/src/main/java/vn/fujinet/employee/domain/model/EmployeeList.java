package vn.fujinet.employee.domain.model;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeList {

	private List<Employee> employees;

	public EmployeeList(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Employee> getEmployee() {
		return employees.stream().collect(Collectors.toList());
	}

public Employee getByIDEmployee() {

	return null ;
}
//				
//		
//	}
//	
//	
	
	
	
	// public List<Employee> getHighSalary() {
	// return employees.stream().filter(e -> e.countSalary() >=
	// 500).collect(Collectors.toList());
	// }
	//
	// public List<Employee> getLowSalary() {
	// return employees.stream().filter(e -> e.countSalary() <
	// 500).collect(Collectors.toList());
	// }
	//

}
