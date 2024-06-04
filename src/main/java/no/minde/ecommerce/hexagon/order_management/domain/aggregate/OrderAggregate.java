package no.minde.ecommerce.hexagon.order_management.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import no.minde.ecommerce.hexagon.common.domain.ISpecification;
import no.minde.ecommerce.hexagon.common.exceptions.BusinessException;
import no.minde.ecommerce.hexagon.common.valueobjects.*;
import no.minde.ecommerce.hexagon.order_management.domain.specification.NonNegativeQuantitiesSpecification;
import no.minde.ecommerce.hexagon.order_management.domain.specification.TotalEqualsSumOfOrderItemsSpecification;
import no.minde.ecommerce.hexagon.order_management.domain.status.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@Getter
public class OrderAggregate implements IAggregateRoot {
    private ID orderId;

    //Aggregate References as Value Objects
    private CustomerId customer;

    private Description description;

    private OrderStatus orderStatus;

    private Money orderTotalCost;

    // Entities
    private List<OrderItem> orderItems = new ArrayList<>();

    private Checkout checkout;

    private OrderAggregate(CustomerId customerId) {
        this.customer = customerId;
    }

    @JsonIgnore
    public ID getId() {
        return orderId;
    }

    @Override
    public void validateDefaultInvariants() throws BusinessException {
        ISpecification<OrderAggregate> totalEqualsSumSpec = new TotalEqualsSumOfOrderItemsSpecification();
        ISpecification<OrderAggregate> nonNegativeQuantitiesSpec = new NonNegativeQuantitiesSpecification();

        validateCustomInvariants(totalEqualsSumSpec, nonNegativeQuantitiesSpec);
    }

    private void validateCustomInvariants(ISpecification<OrderAggregate>... specifications) throws BusinessException {
        for (ISpecification<OrderAggregate> specification : specifications) {
            if (!specification.isSatisfiedBy(this)) {
                System.out.println("Custom invariant violated: " + specification.getDescription());
                throw new BusinessException("Custom invariant violated: " + specification.getDescription());
            }
        }
    }


    @Override
    public OrderAggregateRecord getImmutable() {
        return new OrderAggregateRecord(orderId,
                customer,
                Collections.unmodifiableList(orderItems).stream().toList(),
                orderStatus,
                orderTotalCost);
    }

    public void addProduct(ID productId, Money price, Quantity quantity) throws BusinessException {

        //Create a Domain related value object
        ProductId product = new ProductId(productId, price);

        // Check invariants before processing the command
        validateDefaultInvariants();

        //Add Item
        orderItems.add(new OrderItem(product, quantity));

        //Calculate Price- Addition
        orderTotalCost.value = orderTotalCost.value.add(product.getPrice().value.multiply(BigDecimal.valueOf(quantity.value)));

        // Check invariants after processing the command
        validateDefaultInvariants();
    }

    public void removeProduct(ID productId) throws BusinessException {
        //Create a Domain related value object.
        ProductId product = new ProductId(productId,null);

        // Check invariants before processing the command
        validateDefaultInvariants();

        // Check if the product is already in the order
        OrderItem existingItem = findOrderItemByProduct(product);

        // Remove order item from the list
        orderItems.remove(existingItem);

        //Calculate Price - Subtraction
        orderTotalCost.value = orderTotalCost.value.subtract(existingItem.getProduct().getPrice().value.multiply(BigDecimal.valueOf(existingItem.getOrderItemQuantity().value)));

        // Check invariants after processing the command
        validateDefaultInvariants();
    }

    public void updateOrderStatus(OrderStatus newStatus)  {
        orderStatus = newStatus;
    }

    public void updateProductQuantity(ID productId,  Quantity newQuantity) throws BusinessException {

        //Create a Domain related value object. This time we
        ProductId product = new ProductId(productId,null);

        // Check invariants before processing the command
        validateDefaultInvariants();

        // Check if the product is already in the order
        OrderItem existingItem = findOrderItemByProduct(product);

        // Update quantity if product already exists in the order
        existingItem.setQuantity(newQuantity);

        //Calculate Order Total Cost  - Subtraction existing Product Cost * quantity + Addition New Product Cost * quantity
        orderTotalCost.value = orderTotalCost.value.subtract(existingItem.getProduct().getPrice().value.multiply(BigDecimal.valueOf(existingItem.getOrderItemQuantity().value)));
        orderTotalCost.value = orderTotalCost.value.add(product.getPrice().value.multiply(BigDecimal.valueOf(newQuantity.value)));

        // Check invariants after processing the command
        validateDefaultInvariants();
    }


    public void setCheckout(ID customerId, Contact contact, NameSurname shippingNameSurname, Payment payment, Address shippingAddress) {
        this.customer = new CustomerId(customerId);
        this.checkout = new Checkout(contact,shippingNameSurname,payment,shippingAddress);
    }

    private OrderItem findOrderItemByProduct(ProductId productId) {
        return orderItems.stream().filter(orderItem -> orderItem.getProduct().equals(productId))
                .findFirst().orElse(null);
    }

    public static OrderAggregate builder(CustomerId customerId) {
        OrderAggregate orderAggregate = new OrderAggregate(customerId);
        return orderAggregate;
    }

    public void clearOrder() {

    }
}
