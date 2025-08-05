package dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEntity {
    protected UUID id;
    protected LocalDateTime date;

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BaseEntity() {
    }
}
