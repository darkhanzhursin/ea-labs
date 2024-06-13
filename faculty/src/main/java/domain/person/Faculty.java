package domain.person;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("faculty")
public class Faculty extends Person {
    private String salutation;

    @ElementCollection
    @CollectionTable(name = "faculty_hobbies", joinColumns = @JoinColumn(name = "faculty_id"))
    private List<String> hobbies;

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
