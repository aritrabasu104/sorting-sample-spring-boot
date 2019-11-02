package hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	List<Employee> findByName(String name);
	List<Employee> findAllByOrderByNameAsc();
	List<Employee> findAllByOrderByNameDesc();
	List<Employee> findAllByOrderByHireDateAsc();
	List<Employee> findAllByOrderByHireDateDesc();
	List<Employee> findAllByOrderBySalaryAsc();
	List<Employee> findAllByOrderBySalaryDesc();
}
