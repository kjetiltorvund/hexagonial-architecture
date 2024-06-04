package no.minde.ecommerce.hexagon.common.domain;

import no.minde.ecommerce.hexagon.common.valueobjects.ID;

public abstract class DomainEntity {
    ID id;

    public DomainEntity(ID id) {
        this.id = id;
    }
}
