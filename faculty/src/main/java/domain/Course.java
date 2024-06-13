package domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    private Integer credits;

    @Embedded
    private Audit audit;

    @Lob
    private String description;

    private String code;
    private String name;
    private String department;
}
