package no.minde.ecommerce.hexagon.common.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class Payment extends ValueObject {

    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;

    public Payment(String cardNumber, String cardHolderName, String expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return """
                Payment{
                cardNumber='%s',
                cardHolderName='%s',
                expirationDate='%s'
                }
                """;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(cardNumber, payment.cardNumber) &&
                Objects.equals(cardHolderName, payment.cardHolderName) &&
                Objects.equals(expirationDate, payment.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardHolderName, expirationDate);
    }
}
