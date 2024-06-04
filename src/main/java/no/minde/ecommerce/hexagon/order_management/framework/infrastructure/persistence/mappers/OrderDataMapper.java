package no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.mappers;

import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregate;
import no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.entities.OrderData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta",  uses= OrderItemDataMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDataMapper {
    OrderDataMapper INSTANCE = Mappers.getMapper(OrderDataMapper.class);


    @Mapping(source = "orderId", target = "orderId.id")
    @Mapping(source = "orderUuid", target = "orderId.uuid")
    @Mapping(source = "customerUuid", target = "customer.id.uuid")
    @Mapping(source = "orderFriendlyName", target = "orderFriendlyName")
    @Mapping(source = "orderTotalCost", target = "orderTotalCost")
    @Mapping(source = "orderStatus", target = "orderStatus")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderAggregate unmarshalToDomain(OrderData orderData);


    @InheritInverseConfiguration(name = "unmarshalToDomain")
    OrderData marshalToPersistence(OrderAggregate aggregate);
}
