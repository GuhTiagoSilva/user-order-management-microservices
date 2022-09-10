package com.gustavo.orderapi.services;

import com.gustavo.orderapi.clients.UserClient;
import com.gustavo.orderapi.amqp.RabbitMQMessageProducer;
import com.gustavo.orderapi.dto.OrderDTO;
import com.gustavo.orderapi.dto.UserDTO;
import com.gustavo.orderapi.entities.Order;
import com.gustavo.orderapi.repositories.OrderRepository;
import com.gustavo.orderapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Value("${spring.rabbitmq.queues.user-order}")
    private String queue;
    @Value("${spring.rabbitmq.exchanges.internal}")
    private String exchange;
    @Value("${spring.rabbitmq.routing-keys.user-order-routing}")
    private String routingKey;

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public OrderService(OrderRepository orderRepository, UserClient userClient, RabbitMQMessageProducer rabbitMQMessageProducer) {
        this.orderRepository = orderRepository;
        this.userClient = userClient;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
    }

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
        try {
            UserDTO userDTO = userClient.findById(orderDTO.getUser().getId());
            Order order = new Order();
            order.setTitle(orderDTO.getTitle());
            order.setDescription(orderDTO.getDescription());
            order.setUserId(userDTO.getId());
            order.setId(UUID.randomUUID().toString());
            order = orderRepository.save(order);
            orderDTO.setId(order.getId());
            orderDTO.setUser(userDTO);
            rabbitMQMessageProducer.publish(orderDTO, exchange, routingKey);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            throw new ResourceNotFoundException("User not found: " + orderDTO.getUser().getId());
        }

    }
}
