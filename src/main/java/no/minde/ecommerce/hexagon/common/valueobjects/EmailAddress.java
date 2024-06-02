package no.minde.ecommerce.hexagon.common.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import no.minde.ecommerce.hexagon.common.exceptions.InvalidEmailException;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

@Getter
@Setter
@ToString
@Embeddable
public class EmailAddress extends ValueObject{

    private static final EmailValidator validator = EmailValidator.getInstance();
    private final String value;

    public EmailAddress(String value) throws InvalidEmailException {
        if(!validator.isValid(value)) {
            throw new InvalidEmailException();
        }
        this.value = value;
    }

    public EmailAddress update(String value) throws InvalidEmailException {
        return new EmailAddress(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
