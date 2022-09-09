package com.gustavo.orderapi.repositories;

import com.gustavo.orderapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(value = "SELECT * FROM tb_order WHERE user_id = :userId", nativeQuery = true)
    List<Order> findAllByUserId(@Param("userId") String userId);
}
