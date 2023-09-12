package com.example.popcornpicks.orders.service;

import com.example.popcornpicks.common.enums.HttpStatusCode;
import com.example.popcornpicks.common.exception.CommonException;
import com.example.popcornpicks.common.exception.InvalidParameterException;
import com.example.popcornpicks.orders.domain.OrderMasterDto;
import com.example.popcornpicks.orders.domain.entity.OrderMaster;
import com.example.popcornpicks.orders.domain.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.Optional;

import static com.example.popcornpicks.common.enums.HttpStatusCode.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;

    public OrderMasterDto findDetailOrderId(Long orderId) {
        Optional<OrderMaster> result = orderJpaRepository.findById(orderId);
        if (ObjectUtils.isEmpty(result.get())) {
           throw new InvalidParameterException("잘못된 주문번호 입니다.");
        }
        return OrderMasterDto.dtoToEntity(result.get());
    }

    public Long add(OrderMasterDto dto) {
        OrderMaster saved = orderJpaRepository.save(OrderMaster.entityToDto(dto));
        return saved.getOrderId();
    }
}
