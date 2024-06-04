package no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.mappers;

import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderItem;
import no.minde.ecommerce.hexagon.order_management.framework.infrastructure.persistence.entities.OrderItemData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta",  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderItemDataMapper {
    OrderItemDataMapper INSTANCE = Mappers.getMapper(OrderItemDataMapper.class);


    @Mapping(source = "orderItemId", target = "orderItemId.id")
    @Mapping(source = "orderItemCost", target = "orderItemCost")
    @Mapping(source = "orderItemQuantity", target = "orderItemQuantity")
    @Mapping(source = "productUuid", target = "product.id.uuid")
    @Mapping(source = "productPrice", target = "product.price")
    OrderItem unmarshalToDomain(OrderItemData orderItemData);


    @InheritInverseConfiguration(name = "unmarshalToDomain")
    OrderItemData marshalToPersistence(OrderItem orderItemEntity);
}
