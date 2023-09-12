package com.example.popcornpicks.orders.domain;

import com.example.popcornpicks.orders.domain.entity.OrderMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMasterDto {

    private Long orderId;

    private String content;

    public static OrderMasterDto dtoToEntity(OrderMaster entity) {
        return new OrderMasterDto(entity.getOrderId(), entity.getContent());
    }
}
