package com.siddhartha.jewellery_backend.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.siddhartha.jewellery_backend.dto.CommonFunctions;
import com.siddhartha.jewellery_backend.entity.mmGenCustomerEntity;
import com.siddhartha.jewellery_backend.repo.mmGenCustomerRepo;

@Service
public class mmGenCustomerService {

	private final mmGenCustomerRepo mmGenCustomerRepo;

	public mmGenCustomerService(mmGenCustomerRepo mmGenCustomerRepo) {
		this.mmGenCustomerRepo = mmGenCustomerRepo;
	}

	public mmGenCustomerEntity saveCustomer(ReqBean req) throws Exception {

		if (null == req.getMobile() || "".equals(req.getMobile()))
			throw new Exception("Mobile not present in request");
		if (null == req.getFullName() || "".equals(req.getFullName()))
			throw new Exception("FullName not present in request");

		List<mmGenCustomerEntity> existingData = mmGenCustomerRepo.getByMobileAndActiveFlag(req.getMobile(), "Y");
		mmGenCustomerEntity dataToSave = new mmGenCustomerEntity();
		if (existingData.size() > 0) {
			System.out.println("Data already present going for update");
			dataToSave.setCustomerId(existingData.get(0).getCustomerId());
			dataToSave.setCustomerNumber(existingData.get(0).getCustomerNumber());
		} else {
			System.out.println("Data not present going for insert");
			dataToSave.setCustomerId(Long.parseLong(CommonFunctions.generateId()));
			String customerNo = CommonFunctions.generateCustomerNumber();
			if (mmGenCustomerRepo.getByCustomerNumber(customerNo).size() > 0) {
				customerNo = CommonFunctions.generateCustomerNumber();
			}
			dataToSave.setCustomerNumber(customerNo);
		}
		dataToSave.setActiveFlag("Y");
		dataToSave.setAddress(req.getAddress());
		dataToSave.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		dataToSave.setEmail(req.getEmail());
		dataToSave.setFullName(req.getFullName());
		dataToSave.setMobile(req.getMobile());

		return mmGenCustomerRepo.save(dataToSave);
	}

	public List<mmGenCustomerEntity> searchCustomer(ReqBean req) throws Exception {

		if (null == req.getReqType())
			throw new Exception("ReqType not present in request");
		List<mmGenCustomerEntity> respData = new ArrayList<mmGenCustomerEntity>();

		if ("M".equals(req.getReqType())) {
			if (null == req.getMobile() || "".equals(req.getMobile()))
				throw new Exception("Mobile not present in request");

			respData = mmGenCustomerRepo.getByMobileAndActiveFlag(req.getMobile(), "Y");
		} else if ("A".equals(req.getReqType())) {
			respData = mmGenCustomerRepo.getByActiveFlag("Y");
		} else if ("C".equals(req.getReqType())) {
			if (null == req.getCustomerNumber() || "".equals(req.getCustomerNumber()))
				throw new Exception("CustomerNumber not present in request");

			respData = mmGenCustomerRepo.getByCustomerNumberAndActiveFlag(req.getCustomerNumber(), "Y");
		} else {
			respData = mmGenCustomerRepo.findAll();
		}
		return respData;
	}

}
