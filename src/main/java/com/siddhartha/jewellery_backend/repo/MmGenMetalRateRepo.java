package com.siddhartha.jewellery_backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhartha.jewellery_backend.entity.MmGenMetalRateEntity;

public interface MmGenMetalRateRepo extends JpaRepository<MmGenMetalRateEntity, Long> {
	
    List<MmGenMetalRateEntity> findByActiveFlag(String activeFlag);

}
