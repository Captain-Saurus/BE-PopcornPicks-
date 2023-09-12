package com.example.popcornpicks.orders.controlller;

import com.example.popcornpicks.common.ApiResponse;
import com.example.popcornpicks.orders.domain.OrderMasterDto;
import com.example.popcornpicks.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService; // DIP

    @GetMapping()
    public ApiResponse<OrderMasterDto> datail(@RequestParam("orderId") Long orderId) {
        OrderMasterDto result = orderService.findDetailOrderId(orderId); // command + option + v
        return ApiResponse.createOk(result);
    }

//    @GetMapping()
//    public void list() {
//        orderService
//    }
//
    @PostMapping
    public ApiResponse<Long> add(@RequestBody OrderMasterDto dto) {
        return ApiResponse.createOk(orderService.add(dto));
    }

//    @DeleteMapping

//    @PutMapping
}
