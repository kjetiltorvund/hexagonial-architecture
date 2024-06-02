package no.minde.ecommerce.hexagon.common.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Description extends ValueObject {

    private String value;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(value, that.value);    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
