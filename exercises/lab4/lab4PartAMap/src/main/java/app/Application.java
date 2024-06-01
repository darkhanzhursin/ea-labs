package app;

import domain.School;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.SchoolRepository;
import repository.StudentRepository;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student student1 = new Student("studentLn1", "studentFn1");
        Student student2 = new Student("studentLn2", "studentFn2");
        School school = new School("school1");
        studentRepository.saveAll(List.of(student1, student2));
        school.setStudents(Map.of(student1.getStudentId(), student1, student2.getStudentId(), student2));
        schoolRepository.save(school);
    }
}
