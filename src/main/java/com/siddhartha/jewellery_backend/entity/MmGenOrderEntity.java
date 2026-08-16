package com.siddhartha.jewellery_backend.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "mm_gen_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MmGenOrderEntity {

    @Id
    @Column(name = "int_order_id")
    private Long orderId;

    @Column(name = "int_customer_id")
    private Long customerId;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "metal_type", nullable = false)
    private String metalType;

    @Column(name = "ornament_type")
    private String ornamentType;

    @Column(name = "ornament_weight", nullable = false)
    private String ornamentWeight;
    
    @Column(name = "exchange_weight")
    private String exchangeWeight;

    @Column(name = "net_weight")
    private String netWeight;

    @Column(name = "rate_per_gram")
    private String ratePerGram;

    @Column(name = "making_charge_per_gram")
    private String makingChargePerGram;

    @Column(name = "total_amount")
    private String totalAmount;

    @Column(name = "active_flag", nullable = false)
    private String activeFlag;

    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;
}