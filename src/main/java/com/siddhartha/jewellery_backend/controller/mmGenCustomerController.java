package com.siddhartha.jewellery_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.jewellery_backend.entity.MmGenCustomerEntity;
import com.siddhartha.jewellery_backend.service.ReqBean;
import com.siddhartha.jewellery_backend.service.RequestBean;
import com.siddhartha.jewellery_backend.service.MmGenCustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mmGenCustomer")
public class MmGenCustomerController {

	private final MmGenCustomerService mmGenCustomerService;

	public MmGenCustomerController(MmGenCustomerService mmGenCustomerService) {
		this.mmGenCustomerService = mmGenCustomerService;
	}

	@PostMapping("/post/save/customerData")
	public ResponseEntity<MmGenCustomerEntity> createCustomer(@RequestBody @Valid RequestBean requestBean) throws Exception {
		
		if(null != requestBean && null != requestBean.getReq())
		{
			ReqBean req = requestBean.getReq();
		MmGenCustomerEntity savedCustomer = mmGenCustomerService.saveCustomer(req);

		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
		}else {
			throw new Exception("No Data Found");
		}
	}
	
	@PostMapping("/post/search/customerData")
	public ResponseEntity<List<MmGenCustomerEntity>> searchCustomer(@RequestBody RequestBean requestBean) throws Exception {
		
		if(null != requestBean && null != requestBean.getReq())
		{
			ReqBean req = requestBean.getReq();
		List<MmGenCustomerEntity> data = mmGenCustomerService.searchCustomer(req);

		return new ResponseEntity<>(data, HttpStatus.OK);
		}else {
			throw new Exception("No Data Found");
		}
	}

}
