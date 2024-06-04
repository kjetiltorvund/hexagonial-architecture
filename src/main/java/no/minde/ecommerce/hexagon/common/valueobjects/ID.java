package no.minde.ecommerce.hexagon.common.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Embeddable
public class ID extends ValueObject {

    private Long id;
    private UUID uuid;

    public ID(UUID uuid) {
        this.uuid = uuid;
    }

    public ID(Long id, UUID uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public ID() {
    }

    public static ID generateFromString(String id) {
        return new ID(UUID.fromString(id));
    }

    public static ID generateRandom() {
        return new ID(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return String.format("""
                ID{
                id=%d,
                uuid=%s
                }
                """, id, uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID id1 = (ID) o;
        return uuid.equals(id1.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
