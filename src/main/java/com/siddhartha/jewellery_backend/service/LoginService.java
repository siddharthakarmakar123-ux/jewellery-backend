package com.siddhartha.jewellery_backend.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.siddhartha.jewellery_backend.dto.CommonFunctions;
import com.siddhartha.jewellery_backend.dto.LoginResponse;
import com.siddhartha.jewellery_backend.entity.MmGenCustomerEntity;
import com.siddhartha.jewellery_backend.entity.MmGenEmployeeEntity;
import com.siddhartha.jewellery_backend.repo.MmGenEmployeeRepo;
import com.siddhartha.jewellery_backend.security.JwtService;

@Service
public class LoginService {

	private final MmGenEmployeeRepo mmGenEmployeeRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public LoginService(MmGenEmployeeRepo mmGenEmployeeRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.mmGenEmployeeRepo = mmGenEmployeeRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public LoginResponse login(ReqBean req) throws Exception {

		if (null == req.getUserName() || "".equals(req.getUserName())) {
			throw new Exception("User Name Can not be blank");
		}
		if (null == req.getPassword() || "".equals(req.getPassword())) {
			throw new Exception("Password Can not be blank");
		}

		MmGenEmployeeEntity userData = mmGenEmployeeRepo.getByUsernameAndActiveFlag(req.getUserName(), "Y")
				.orElseThrow(() -> new Exception("Invalid User Name or Password"));
		if (!passwordEncoder.matches(req.getPassword(), userData.getPassword())) {
			throw new Exception("Invalid User Name or Password");
		}
		String token = jwtService.generateToken(
		        userData.getIntEmployeeId(),
		        userData.getUsername(),
		        userData.getRoleAbbr()
		);

		return new LoginResponse(
		        token,
		        userData.getIntEmployeeId(),
		        userData.getUsername(),
		        userData.getFullName(),
		        userData.getRoleAbbr()
		);

	}

	public MmGenEmployeeEntity signup(ReqBean req)  throws Exception {

		if (null == req.getUserName() || "".equals(req.getUserName()))
			throw new Exception("User Name not present in request");
		if (null == req.getPassword() || "".equals(req.getPassword()))
			throw new Exception("Password not present in request");
		if (null == req.getFullName() || "".equals(req.getFullName()))
			throw new Exception("FullName not present in request");

		List<MmGenEmployeeEntity> existingData = mmGenEmployeeRepo.getByUsername(req.getUserName());
		MmGenEmployeeEntity dataToSave = new MmGenEmployeeEntity();
		if (existingData.size() > 0) {
			throw new Exception("User Name already used.");
		} else {
			System.out.println("Data not present going for insert");
			dataToSave.setIntEmployeeId(Long.parseLong(CommonFunctions.generateId()));
			String employeeNo = CommonFunctions.generateUniqueNumber("E");
			if (mmGenEmployeeRepo.getByEmployeeNumber(employeeNo).size() > 0) {
				employeeNo = CommonFunctions.generateUniqueNumber("E");
			}
			dataToSave.setEmployeeNumber(employeeNo);
			dataToSave.setActiveFlag("Y");
			dataToSave.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
			dataToSave.setUsername(req.getUserName());
			dataToSave.setFullName(req.getFullName());
			dataToSave.setPassword(passwordEncoder.encode(req.getPassword()));
			dataToSave.setRoleAbbr("EMPLOYEE");
		}

		return mmGenEmployeeRepo.save(dataToSave);
	}
}
