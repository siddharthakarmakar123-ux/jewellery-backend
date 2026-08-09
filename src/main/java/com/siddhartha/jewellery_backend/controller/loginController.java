package com.siddhartha.jewellery_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.jewellery_backend.entity.mmGenCustomerEntity;
import com.siddhartha.jewellery_backend.entity.mmGenEmployeeEntity;
import com.siddhartha.jewellery_backend.service.ReqBean;
import com.siddhartha.jewellery_backend.service.RequestBean;
import com.siddhartha.jewellery_backend.service.loginService;
import com.siddhartha.jewellery_backend.service.mmGenCustomerService;

@RestController
@RequestMapping("/api/login")
public class loginController {

	private final loginService loginService;

	public loginController(loginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/post/authinticate")
	public ResponseEntity<?> loginAuthinticate(@RequestBody RequestBean requestBean) throws Exception {
			try {
				if (null != requestBean && null != requestBean.getReq()) {
				ReqBean req = requestBean.getReq();
				mmGenEmployeeEntity data = loginService.login(req);

				return new ResponseEntity<>(data, HttpStatus.OK);
				} else {
					throw new Exception("No Data Found");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
			}
		
	}
	
	@PostMapping("/post/signup")
	public ResponseEntity<?> loginSignup(@RequestBody RequestBean requestBean) throws Exception {
			try {
				if (null != requestBean && null != requestBean.getReq()) {
				ReqBean req = requestBean.getReq();
				mmGenEmployeeEntity data = loginService.signup(req);

				return new ResponseEntity<>(data, HttpStatus.CREATED);
				} else {
					throw new Exception("No Data Found");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
			}
		
	}

}
