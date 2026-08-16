package com.siddhartha.jewellery_backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhartha.jewellery_backend.entity.MmGenEmployeeEntity;

public interface MmGenEmployeeRepo extends JpaRepository<MmGenEmployeeEntity, Long> {
	
	Optional<MmGenEmployeeEntity> getByUsernameAndActiveFlag(String userName,String activeFlag);

	List<MmGenEmployeeEntity> getByUsername(String userName);

	List<MmGenEmployeeEntity> getByEmployeeNumber(String employeeNo);

}
