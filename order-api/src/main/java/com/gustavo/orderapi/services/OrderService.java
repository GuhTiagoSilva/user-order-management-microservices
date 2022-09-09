package com.gustavo.orderapi.services;

import com.gustavo.orderapi.clients.UserClient;
import com.gustavo.orderapi.dto.OrderDTO;
import com.gustavo.orderapi.dto.UserDTO;
import com.gustavo.orderapi.entities.Order;
import com.gustavo.orderapi.repositories.OrderRepository;
import com.gustavo.orderapi.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    @Transactional(readOnly = true)
    public OrderDTO findById(String orderId) {

        Order orderEntity = orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("Id Not Found: " + orderId));

        UserDTO userDTO = userClient.findById(orderEntity.getUserId());

        OrderDTO orderDTO = OrderDTO.builder()
                .id(orderEntity.getId())
                .user(userDTO)
                .description(orderEntity.getDescription())
                .title(orderEntity.getTitle())
                .build();

        return orderDTO;
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> findAllByUserId(String userId) {
        try {
            UserDTO userDTO = userClient.findById(userId);
            List<Order> orders = orderRepository.findAllByUserId(userId);
            return orders.stream().map(order -> new OrderDTO(order, userDTO)).collect(Collectors.toList());
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }
    }

    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setTitle(orderDTO.getTitle());
        order.setDescription(orderDTO.getDescription());
        order.setUserId(orderDTO.getUser().getId());
        orderRepository.save(order);
    }
}
