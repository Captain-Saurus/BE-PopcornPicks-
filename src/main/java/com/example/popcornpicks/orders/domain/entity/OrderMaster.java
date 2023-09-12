package com.example.popcornpicks.orders.domain.entity;

import com.example.popcornpicks.orders.domain.OrderMasterDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private String content;

    public static OrderMaster entityToDto(OrderMasterDto dto) {
        return OrderMaster.builder()
                .content(dto.getContent()).build();
    }
}
