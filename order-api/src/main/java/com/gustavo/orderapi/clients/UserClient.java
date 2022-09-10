package com.gustavo.orderapi.clients;

import com.gustavo.orderapi.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "user-microservice", path = "/users")
public interface UserClient {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/{userId}")
    UserDTO findById(@PathVariable String userId);

}
