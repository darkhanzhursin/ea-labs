package domain;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeNumber;

    private String name;

    protected Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    public void setDepartment(Department department) {
        this.department = department;
    }
}
