package no.minde.ecommerce.hexagon.common.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Embeddable
public class NameSurname extends ValueObject {
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;

    public NameSurname(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public NameSurname(String title, String firstName, String middleName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameSurname that = (NameSurname) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(lastName, that.lastName);    }

    @Override
    public int hashCode() {
        return Objects.hash(title, firstName, middleName, lastName);
    }
}
