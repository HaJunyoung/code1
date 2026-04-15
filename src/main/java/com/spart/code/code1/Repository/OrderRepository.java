package com.spart.code.code1.Repository;

import com.spart.code.code1.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
