package app;

import domain.Course;
import domain.Department;
import domain.Grade;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.StudentRepository;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student student = new Student("Student1");
        Department department = new Department("department1");
        student.setDepartment(department);
        Course course = new Course("Programming");
        student.setGrade(new Grade(course, 90.0));
        studentRepository.save(student);

        Student student2 = new Student("Student2");
        student2.setDepartment(department);
        student2.setGrade(new Grade(course, 95.0));
        studentRepository.save(student2);

        studentRepository.getStudentsByCourseName(course);
        studentRepository.getStudentsByDepartment(department);
    }
}
