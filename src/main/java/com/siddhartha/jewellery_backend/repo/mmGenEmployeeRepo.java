package com.siddhartha.jewellery_backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhartha.jewellery_backend.entity.mmGenEmployeeEntity;

public interface mmGenEmployeeRepo extends JpaRepository<mmGenEmployeeEntity, Long> {
	
	Optional<mmGenEmployeeEntity> getByUsernameAndActiveFlag(String userName,String activeFlag);

	List<mmGenEmployeeEntity> getByUsername(String userName);

	List<mmGenEmployeeEntity> getByEmployeeNumber(String employeeNo);

}
