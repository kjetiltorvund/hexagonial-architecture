package no.minde.ecommerce.hexagon.order_management.domain.aggregate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.minde.ecommerce.hexagon.common.valueobjects.Description;
import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.common.valueobjects.Money;

import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
public class ProductId {
    private ID id;
    private Money price;
    private Description name;

    public ProductId(ID id, Money price, Description name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public ProductId(ID id, Money price) {
        this.id = id;
        this.price = price;
    }

    protected Money getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return Objects.equals(id.getUuid(), productId.id.getUuid()) &&
                Objects.equals(price, productId.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price);
    }
}
