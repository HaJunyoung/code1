package com.spart.code.code1.DTO;

import com.spart.code.code1.Entity.Order;
import com.spart.code.code1.Entity.Product;
import lombok.Getter;

@Getter
public class OrderResponse {
    private final Long orderId;
    private final Product product;


    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.product = order.getProduct();
    }
}
