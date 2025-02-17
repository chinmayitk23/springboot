package com.wipro.springboot.usecase1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class EmployeeUsecase1Application {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeUsecase1Application.class, args);
	}
	@Bean
    CommandLineRunner initData(EmployeeRepository repository) {
        return args -> {
            repository.save(new Employee("Alice", "Developer"));
            repository.save(new Employee("Bob", "Tester"));
            repository.save(new Employee("Charlie", "Architect"));
        };
    }

}
@Entity
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;

    public Employee() {}
    
    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

interface EmployeeRepository extends JpaRepository<Employee, Long> {}

@Service
class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() { return repository.findAll(); }
    public Optional<Employee> getEmployeeById(Long id) { return repository.findById(id); }
    public Employee saveEmployee(Employee employee) { return repository.save(employee); }
}

@RestController
@RequestMapping("/employees")
class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getEmployees() { return service.getAllEmployees(); }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id) { return service.getEmployeeById(id); }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) { return service.saveEmployee(employee); }
}

