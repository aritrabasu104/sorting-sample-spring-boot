package hello.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.model.Employee;
import hello.model.Employee.columnNames;
import hello.repository.EmployeeRepository;
import hello.service.EmployeeService;
import util.SortOrder;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee findEmployee(String name) throws Exception {

		return employeeRepository.findByName(name).get(0);

	}

	@Override
	public List<Employee> findEmployeesSortedByColumn(columnNames column, SortOrder sortOrder) {
		if (SortOrder.ASC.equals(sortOrder)) {
			switch (column) {
			case name:
				return employeeRepository.findAllByOrderByNameAsc();
			case hireDate:
				return employeeRepository.findAllByOrderByHireDateAsc();
			case salary:
				return employeeRepository.findAllByOrderBySalaryAsc();
			}
		} else if (SortOrder.DSC.equals(sortOrder)) {
			switch (column) {
			case name:
				return employeeRepository.findAllByOrderByNameDesc();
			case hireDate:
				return employeeRepository.findAllByOrderByHireDateDesc();
			case salary:
				return employeeRepository.findAllByOrderBySalaryDesc();
			}

		}
		return employeeRepository.findAll();
	}

}
