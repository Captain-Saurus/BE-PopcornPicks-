package com.example.popcornpicks.orders.domain.repository;

import com.example.popcornpicks.orders.domain.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderMaster, Long> {
}
