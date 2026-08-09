package com.siddhartha.jewellery_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.jewellery_backend.entity.mmGenCustomerEntity;
import com.siddhartha.jewellery_backend.service.ReqBean;
import com.siddhartha.jewellery_backend.service.RequestBean;
import com.siddhartha.jewellery_backend.service.mmGenCustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mmGenCustomer")
public class mmGenCustomerController {

	private final mmGenCustomerService mmGenCustomerService;

	public mmGenCustomerController(mmGenCustomerService mmGenCustomerService) {
		this.mmGenCustomerService = mmGenCustomerService;
	}

	@PostMapping("/post/save/customerData")
	public ResponseEntity<mmGenCustomerEntity> createCustomer(@RequestBody @Valid RequestBean requestBean) throws Exception {
		
		if(null != requestBean && null != requestBean.getReq())
		{
			ReqBean req = requestBean.getReq();
		mmGenCustomerEntity savedCustomer = mmGenCustomerService.saveCustomer(req);

		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
		}else {
			throw new Exception("No Data Found");
		}
	}
	
	@PostMapping("/post/search/customerData")
	public ResponseEntity<List<mmGenCustomerEntity>> searchCustomer(@RequestBody RequestBean requestBean) throws Exception {
		
		if(null != requestBean && null != requestBean.getReq())
		{
			ReqBean req = requestBean.getReq();
		List<mmGenCustomerEntity> data = mmGenCustomerService.searchCustomer(req);

		return new ResponseEntity<>(data, HttpStatus.OK);
		}else {
			throw new Exception("No Data Found");
		}
	}

}
