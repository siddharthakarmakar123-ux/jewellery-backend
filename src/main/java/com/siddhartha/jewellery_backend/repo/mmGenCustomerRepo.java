package com.siddhartha.jewellery_backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhartha.jewellery_backend.entity.mmGenCustomerEntity;

public interface mmGenCustomerRepo extends JpaRepository<mmGenCustomerEntity, Long>{

	List<mmGenCustomerEntity> getByMobileAndActiveFlag(String mobile,String activeFlag);

	List<mmGenCustomerEntity> getByActiveFlag(String activeFlag);

	List<mmGenCustomerEntity> getByCustomerNumberAndActiveFlag(String customerNumber, String activeFlag);
	
	List<mmGenCustomerEntity> getByCustomerNumber(String customerNumber);

}
