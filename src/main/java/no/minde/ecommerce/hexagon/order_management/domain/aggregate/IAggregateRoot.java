package no.minde.ecommerce.hexagon.order_management.domain.aggregate;

import no.minde.ecommerce.hexagon.common.exceptions.BusinessException;

public interface IAggregateRoot {
    void validateDefaultInvariants() throws BusinessException;

    OrderAggregateRecord getImmutable();
}
