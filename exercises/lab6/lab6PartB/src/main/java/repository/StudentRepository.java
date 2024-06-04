package repository;

import domain.Course;
import domain.Department;
import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> getStudentsByDepartment(Department department);

    @Query("select s from Student s where s.grade.course=:course")
    List<Student> getStudentsByCourseName(@Param("course") Course course);
}
