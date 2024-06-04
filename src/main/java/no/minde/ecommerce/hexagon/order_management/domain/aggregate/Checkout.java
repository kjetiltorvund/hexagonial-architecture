package no.minde.ecommerce.hexagon.order_management.domain.aggregate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import no.minde.ecommerce.hexagon.common.domain.ComplexValueObject;
import no.minde.ecommerce.hexagon.common.valueobjects.Address;
import no.minde.ecommerce.hexagon.common.valueobjects.Contact;
import no.minde.ecommerce.hexagon.common.valueobjects.NameSurname;
import no.minde.ecommerce.hexagon.common.valueobjects.Payment;

import java.util.Objects;

@Getter
@Setter
@ToString
public class Checkout extends ComplexValueObject {
    private NameSurname shippingNameSurname;
    private Payment payment;
    private Address shippingAddress;
    private Contact contact;

    protected Checkout(Contact contact, NameSurname shippingNameSurname, Payment payment, Address shippingAddress) {
        this.contact = contact;
        this.shippingNameSurname = shippingNameSurname;
        this.payment = payment;
        this.shippingAddress = shippingAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkout checkout = (Checkout) o;
        return Objects.equals(shippingNameSurname, checkout.shippingNameSurname) &&
                Objects.equals(payment, checkout.payment) &&
                Objects.equals(shippingAddress, checkout.shippingAddress) &&
                Objects.equals(contact, checkout.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shippingNameSurname, payment, shippingAddress, contact);
    }
}
