package no.minde.ecommerce.hexagon.order_management.framework.adapters_inbound.web;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.minde.ecommerce.hexagon.common.exceptions.BusinessException;
import no.minde.ecommerce.hexagon.common.exceptions.CheckoutException;
import no.minde.ecommerce.hexagon.common.exceptions.NotFoundException;
import no.minde.ecommerce.hexagon.common.valueobjects.ID;
import no.minde.ecommerce.hexagon.order_management.application.ports_inbound.OrderManagementInboundPort;
import no.minde.ecommerce.hexagon.order_management.domain.aggregate.OrderAggregateRecord;
import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.CheckoutRequest;
import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.OrderRequest;
import no.minde.ecommerce.hexagon.order_management.framework.ports_inbound.web.inboundobjects.ProductRequest;

import javax.inject.Inject;
import java.net.URI;

// This adapter serves the port interface and acts as an old school controller.
// Orders come from the users, presentation layer and is passed to the core business logic
// Inbound (Driver) adapter

@Path("order-service/hexagon/v1/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderUIInboundAdapter implements OrderWebUIInboundPort {

    private final OrderManagementInboundPort applicationAdapter;

    @Inject
    public OrderUIInboundAdapter(OrderManagementInboundPort applicationAdapter) {
        this.applicationAdapter = applicationAdapter;
    }

    @GET
    @Path("/{orderId}")
    @Override
    public Response getOrder(String orderId) throws NotFoundException, BusinessException {
        OrderAggregateRecord order = applicationAdapter.getOrder(ID.generateFromString(orderId)); //Call Application Layer to Get  the Order
        return Response.ok(order).build();
    }

    @POST
    @Override
    public Response createOrder(OrderRequest orderRequest) throws BusinessException, NotFoundException {
        ID newOrderId = applicationAdapter.createOrder(orderRequest.customerId(), orderRequest.products()); //Call Application Layer to Create  the Order and return the ID

        return Response.created(URI.create("/" + newOrderId.getUuid().toString())).entity(newOrderId).build();
    }

    @PUT
    @Path("/{orderId}/addProduct")
    @Override
    public Response addProductToOrder(String orderId, ProductRequest productRequest) throws NotFoundException, BusinessException {
        OrderAggregateRecord order = applicationAdapter.addProductToOrder(ID.generateFromString(orderId), productRequest.productId(), productRequest.price(), productRequest.quantity());
        return Response.ok(URI.create("/" + order.id().getUuid().toString())).entity(order).build();
    }

    @DELETE
    @Path("/{orderId}/removeProduct")
    @Override
    public Response removeProductFromOrder(String orderId, ProductRequest productRequest) throws NotFoundException, BusinessException {
        OrderAggregateRecord order = applicationAdapter.removeProductFromOrder(ID.generateFromString(orderId), productRequest.productId()); //Call Application Layer to Remove a Product to  the Order

        return Response.ok(URI.create("/checkout-success/" + order.id().getUuid().toString())).entity(order).build();
    }

    @GET
    @Path("/{orderId}/clear")
    @Override
    public Response clearOrder(String orderId) throws NotFoundException, BusinessException {
        applicationAdapter.clearOrder(ID.generateFromString(orderId));
        return Response.ok(URI.create("/cleared")).build();
    }

    @GET
    @Override
    public Response checkout(String orderId, CheckoutRequest checkoutRequest) throws NotFoundException, BusinessException, CheckoutException {
        return null;
    }
}
