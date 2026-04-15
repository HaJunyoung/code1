package com.spart.code.code1.Controller;


import com.spart.code.code1.DTO.OrderRequest;
import com.spart.code.code1.DTO.OrderResponse;
import com.spart.code.code1.Entity.Order;
import com.spart.code.code1.Entity.Product;
import com.spart.code.code1.Repository.OrderRepository;
import com.spart.code.code1.Repository.ProductRepository;
import com.spart.code.code1.Service.OrderService;
import com.spart.code.code1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {



    private final OrderService orderService;

    @PostMapping
    @Transactional
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {

        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        OrderResponse orderResponse = orderService.getOrder(id);

        return ResponseEntity.ok(orderResponse);
    }
}