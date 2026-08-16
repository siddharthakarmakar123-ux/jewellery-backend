package com.siddhartha.jewellery_backend.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "mm_gen_metal_rate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MmGenMetalRateEntity {

    @Id
    @Column(name = "int_rate_id")
    private Long rateId;

    @Column(name = "gold_rate_per_gram", nullable = false)
    private String goldRatePerGram;

    @Column(name = "silver_rate_per_gram", nullable = false)
    private String silverRatePerGram;

    @Column(name = "created_timestamp")
    private Timestamp createdTimestamp;

    @Column(name = "updated_timestamp")
    private Timestamp updatedTimestamp;

    @Column(name = "active_flag")
    private String activeFlag;
}