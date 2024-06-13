package domain;

import enums.LocationTypeEnum;
import jakarta.persistence.*;

@Entity
public class LocationType {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Audit audit;

    @Enumerated(EnumType.STRING)
    private LocationTypeEnum type;
}
