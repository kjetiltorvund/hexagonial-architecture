package no.minde.ecommerce.hexagon.order_management.domain.aggregate;

import no.minde.ecommerce.hexagon.common.domain.IExternalReferenceAggregateRoot;
import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.common.valueobjects.NameSurname;
import no.minde.ecommerce.hexagon.common.valueobjects.ValueObject;

import java.util.Objects;

public class CustomerId extends ValueObject implements IExternalReferenceAggregateRoot {

    private ID id;
    private NameSurname nameSurname;

    public CustomerId() {
    }

    public CustomerId(ID id) {
        this.id = id;
    }

    public CustomerId(ID id, NameSurname nameSurname) {
        this.id = id;
        this.nameSurname = nameSurname;
    }

    public ID getId() {
        return id;
    }

    @Override
    public String toString() {
        if (nameSurname != null) {
            return String.format("%s - %s", id, nameSurname);
        } else {
            return id.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerId that = (CustomerId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameSurname, that.nameSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameSurname);
    }
}
