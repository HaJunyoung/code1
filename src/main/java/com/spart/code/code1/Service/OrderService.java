package com.spart.code.code1.Service;

import com.spart.code.code1.DTO.OrderRequest;
import com.spart.code.code1.DTO.OrderResponse;
import com.spart.code.code1.Entity.Order;
import com.spart.code.code1.Entity.Product;
import com.spart.code.code1.Repository.OrderRepository;
import com.spart.code.code1.Repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ResponseEntity<OrderResponse> createOrder(@Valid OrderRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 상품이 존재하지 않습니다. id=" + request.getProductId()));

        Order order = new Order(product);
        Order saved = orderRepository.save(order);
        OrderResponse response = new OrderResponse(saved);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getOrderId())).body(response);

    }


    public OrderResponse getOrder(Long id){
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                    "해당 주문이 존재하지 않습니다. id=" + id));

        OrderResponse orderResponse = new OrderResponse(order);

        return orderResponse;
    }


}
