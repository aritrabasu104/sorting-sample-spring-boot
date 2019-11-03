package hello.resource;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.dto.EmployeeDto;
import hello.model.Employee;
import hello.service.EmployeeService;
import util.SortOrder;

@RestController
public class EmployeeResource {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ModelMapper modelMapper;

	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/sortEmployeesByColumn")
	public ResponseEntity<?> findEmployeesSorted(@Valid @RequestParam(required = false) Employee.columnNames column,
			@Valid @RequestParam(required = false) SortOrder sortOrder) {
		try {
			return ResponseEntity.ok().body(employeeService.findEmployeesSortedByColumn(column, sortOrder).stream()
					.map(item -> modelMapper.map(item, EmployeeDto.class)).collect(Collectors.toList()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createEmployee")
	public ResponseEntity<?> create(@RequestBody EmployeeDto employeeDto) {
		return ResponseEntity.ok().body(employeeService.createEmployee(modelMapper.map(employeeDto, Employee.class)));
	}
}
