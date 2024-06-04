package no.minde.ecommerce.hexagon.common.domain;

import no.minde.ecommerce.hexagon.common.exceptions.BusinessException;

public interface IAggregateRoot {
    public  void validateDefaultInvariants() throws BusinessException;
    public AggregateRecord getImmutable();
}
