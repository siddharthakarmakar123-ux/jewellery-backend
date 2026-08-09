package com.siddhartha.jewellery_backend.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.siddhartha.jewellery_backend.config.SecurityConfig;
import com.siddhartha.jewellery_backend.dto.CommonFunctions;
import com.siddhartha.jewellery_backend.entity.mmGenCustomerEntity;
import com.siddhartha.jewellery_backend.entity.mmGenEmployeeEntity;
import com.siddhartha.jewellery_backend.repo.mmGenEmployeeRepo;

@Service
public class loginService {

	private final mmGenEmployeeRepo mmGenEmployeeRepo;
	private final PasswordEncoder passwordEncoder;

	public loginService(mmGenEmployeeRepo mmGenEmployeeRepo, PasswordEncoder passwordEncoder) {
		this.mmGenEmployeeRepo = mmGenEmployeeRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public mmGenEmployeeEntity login(ReqBean req) throws Exception {

		if (null == req.getUserName() || "".equals(req.getUserName())) {
			throw new Exception("User Name Can not be blank");
		}
		if (null == req.getPassword() || "".equals(req.getPassword())) {
			throw new Exception("Password Can not be blank");
		}

		mmGenEmployeeEntity userData = mmGenEmployeeRepo.getByUsernameAndActiveFlag(req.getUserName(), "Y")
				.orElseThrow(() -> new Exception("Invalid User Name or Password"));
		if (!passwordEncoder.matches(req.getPassword(), userData.getPassword())) {
			throw new Exception("Invalid User Name or Password");
		}
		return userData;

	}

	public mmGenEmployeeEntity signup(ReqBean req)  throws Exception {

		if (null == req.getUserName() || "".equals(req.getUserName()))
			throw new Exception("User Name not present in request");
		if (null == req.getPassword() || "".equals(req.getPassword()))
			throw new Exception("Password not present in request");
		if (null == req.getFullName() || "".equals(req.getFullName()))
			throw new Exception("FullName not present in request");

		List<mmGenEmployeeEntity> existingData = mmGenEmployeeRepo.getByUsername(req.getUserName());
		mmGenEmployeeEntity dataToSave = new mmGenEmployeeEntity();
		if (existingData.size() > 0) {
			throw new Exception("User Name already used.");
		} else {
			System.out.println("Data not present going for insert");
			dataToSave.setIntEmployeeId(Long.parseLong(CommonFunctions.generateId()));
			String employeeNo = CommonFunctions.generateEmployeeNumber();
			if (mmGenEmployeeRepo.getByEmployeeNumber(employeeNo).size() > 0) {
				employeeNo = CommonFunctions.generateEmployeeNumber();
			}
			dataToSave.setEmployeeNumber(employeeNo);
			dataToSave.setActiveFlag("Y");
			dataToSave.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
			dataToSave.setUsername(req.getUserName());
			dataToSave.setFullName(req.getFullName());
			dataToSave.setPassword(passwordEncoder.encode(req.getPassword()));
		}

		return mmGenEmployeeRepo.save(dataToSave);
	}
}
