package domain;

import jakarta.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    private double grade;

    public Grade(Course course, double grade) {
        this.course = course;
        this.grade = grade;
    }

    public Grade() { }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
