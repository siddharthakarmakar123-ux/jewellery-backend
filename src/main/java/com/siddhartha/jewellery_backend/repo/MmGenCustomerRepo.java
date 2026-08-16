package com.siddhartha.jewellery_backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhartha.jewellery_backend.entity.MmGenCustomerEntity;

public interface MmGenCustomerRepo extends JpaRepository<MmGenCustomerEntity, Long>{

	List<MmGenCustomerEntity> getByMobileAndActiveFlag(String mobile,String activeFlag);

	List<MmGenCustomerEntity> getByActiveFlag(String activeFlag);

	List<MmGenCustomerEntity> getByCustomerNumberAndActiveFlag(String customerNumber, String activeFlag);
	
	List<MmGenCustomerEntity> getByCustomerNumber(String customerNumber);

}
