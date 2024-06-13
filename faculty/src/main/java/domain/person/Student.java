package domain.person;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("student")
public class Student extends Person {

    private String entry;

    @ManyToOne(cascade={CascadeType.PERSIST}, fetch= FetchType.LAZY)
    private Faculty faculty;

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
