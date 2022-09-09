package com.gustavo.orderapi.controllers;

import com.gustavo.orderapi.dto.OrderDTO;
import com.gustavo.orderapi.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public OrderDTO findById(@PathVariable String orderId) {
        return orderService.findById(orderId);
    }

    @GetMapping
    public List<OrderDTO> findAllByUserId(@RequestParam String userId){
        return orderService.findAllByUserId(userId);
    }

}
