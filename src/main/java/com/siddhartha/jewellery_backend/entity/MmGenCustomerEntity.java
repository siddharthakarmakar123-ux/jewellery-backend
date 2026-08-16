package com.siddhartha.jewellery_backend.entity;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mm_gen_customer")
@Data
public class MmGenCustomerEntity {

    @Id
    @Column(name = "int_customer_id")
    private Long customerId;

    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "mobile", unique = true)
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "created_timestamp")
    private Timestamp createdTimestamp;
    
    @Column(name = "active_flag", nullable = false)
    private String activeFlag;
    
}
