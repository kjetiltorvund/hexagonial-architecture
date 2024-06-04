package no.minde.ecommerce.hexagon.order_management.application.ports_inbound;

import jakarta.transaction.Transactional;
import no.minde.ecommerce.hexagon.common.exceptions.BusinessException;
import no.minde.ecommerce.hexagon.common.exceptions.NotFoundException;
import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.common.valueobjects.Money;
import no.minde.ecommerce.hexagon.common.valueobjects.Quantity;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregateRecord;
import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.ProductRequest;

import java.util.List;

// Inbound Port serves as a internal application service port

public interface OrderManagementInboundPort {
    @Transactional
    ID createOrder(ID customerId, List<ProductRequest> productList) throws NotFoundException, BusinessException;

    @Transactional
    OrderAggregateRecord addProductToOrder(ID orderId, ID productId, Money price, Quantity quantity) throws NotFoundException, BusinessException;

    @Transactional
    OrderAggregateRecord removeProductFromOrder(ID orderId, ID productId) throws NotFoundException, BusinessException;

    @Transactional
    OrderAggregateRecord clearOrder(ID orderId) throws NotFoundException, BusinessException;

    @Transactional
    OrderAggregateRecord getOrder(ID orderId) throws NotFoundException, BusinessException;
}
