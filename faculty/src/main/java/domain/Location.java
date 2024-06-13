package domain;

import jakarta.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer capacity;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private LocationType locationType;

    @Embedded
    private Audit audit;
}
