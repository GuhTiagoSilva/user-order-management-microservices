package com.gustavo.userapi.amqp;

import com.gustavo.userapi.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    @RabbitListener(queues = "${spring.rabbitmq.queues.user-order}")
    public void listener(OrderDTO orderDTO) {
      log.info("received: {}", orderDTO);
    }

}
