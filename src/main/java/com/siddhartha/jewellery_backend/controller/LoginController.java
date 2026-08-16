package com.siddhartha.jewellery_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.jewellery_backend.dto.LoginResponse;
import com.siddhartha.jewellery_backend.entity.MmGenCustomerEntity;
import com.siddhartha.jewellery_backend.entity.MmGenEmployeeEntity;
import com.siddhartha.jewellery_backend.service.ReqBean;
import com.siddhartha.jewellery_backend.service.RequestBean;
import com.siddhartha.jewellery_backend.service.LoginService;
import com.siddhartha.jewellery_backend.service.MmGenCustomerService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/post/authinticate")
	public ResponseEntity<?> loginAuthinticate(@RequestBody RequestBean requestBean) throws Exception {
			try {
				if (null != requestBean && null != requestBean.getReq()) {
				ReqBean req = requestBean.getReq();
				LoginResponse data = loginService.login(req);

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
				MmGenEmployeeEntity data = loginService.signup(req);

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
