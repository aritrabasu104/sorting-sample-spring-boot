package hello.service;

import java.util.List;

import hello.model.Employee;
import util.SortOrder;

public interface EmployeeService {
	Employee createEmployee(Employee employee);
	Employee findEmployee(String name) throws Exception;
	List<Employee> findEmployeesSortedByColumn(Employee.columnNames column, SortOrder sortOrder);
}
