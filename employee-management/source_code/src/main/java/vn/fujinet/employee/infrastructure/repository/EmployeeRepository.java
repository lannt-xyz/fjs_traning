package vn.fujinet.employee.infrastructure.repository;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fujinet.employee.domain.model.Employee;
import vn.fujinet.employee.domain.model.EmployeeList;
import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;

@Repository
public class EmployeeRepository {

    @Autowired
    private JpaRepository<EmployeeEntity, Integer> jpaRepository;

    //Return list employees
    public EmployeeList selectAll() {
        return StreamSupport.stream(jpaRepository.findAll().spliterator(), false)
                .map(Employee::fromEntity)
                .collect(collectingAndThen(toList(), EmployeeList::new));
    }
    //Select employee by "id"
	public Optional<EmployeeEntity> selectByID(int id) {
		return jpaRepository.findById(Integer.valueOf(id));
	}
	//Delete employee by "id"
	public void delete(int id) {
		if(jpaRepository.existsById(id)) {
			jpaRepository.deleteById(Integer.valueOf(id));
			System.out.println("Delete completed");
		}
		else {
			throw new RuntimeException("ID Not Found");
		}
	}
	//Update employee
	public void update(Employee employee, int id) {
		EmployeeEntity employeeEntity = jpaRepository.getOne(id);
		if(jpaRepository.existsById(id)) {
			employeeEntity.setFirstName(employee.getFirstName());
			employeeEntity.setLastName(employee.getLastName());
			employeeEntity.setDateOfBirth(employee.getDateOfBirth());
			employeeEntity.setSalary(employee.getSalary());
			jpaRepository.save(employeeEntity);
		}
		else {
			throw new RuntimeException("ID Not Found");
		}
	}
}
