package no.minde.ecommerce.hexagon.common.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@ToString
@Embeddable
public class Money extends ValueObject {

    public BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money that = (Money) o;

        // Compare using a tolerance for BigDecimal values
        return value != null ? value.subtract(that.value).abs().compareTo(BigDecimal.ZERO) == 0 : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.setScale(2, RoundingMode.HALF_UP).hashCode() : 0;
    }
}
