package com.gustavo.orderapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "uuid")
    private String id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "uuid")
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime finalDate;
}
