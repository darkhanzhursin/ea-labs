package app;

import domain.Department;
import domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.DepartmentRepository;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Department department1 = new Department("Law");
        department1.addEmployee(new Employee("Sarah"));
        department1.addEmployee(new Employee("Sergey"));
        departmentRepository.save(department1);

        Department department2 = new Department("IT");
        department2.addEmployee(new Employee("Mark"));
        department2.addEmployee(new Employee("Dan"));
        departmentRepository.save(department2);
    }
}
