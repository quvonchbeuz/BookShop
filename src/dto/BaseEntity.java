package dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEntity {
    protected UUID id;
    protected LocalDateTime createdDate;

    public BaseEntity(UUID id, LocalDateTime createdDate) {
        this.id = id;
        this.createdDate = createdDate;
    }

    public BaseEntity() {
    }
}
