package no.minde.ecommerce.hexagon.common.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@Embeddable
public class Contact extends ValueObject {

    private String email;
    private String phone;
    private String mobilePhone;

    public Contact(String email, String phone, String mobilePhone) {
        this.email = email;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(email, contact.email) &&
                Objects.equals(phone, contact.phone) &&
                Objects.equals(mobilePhone, contact.mobilePhone);    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone, mobilePhone);
    }
}
