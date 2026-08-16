package com.siddhartha.jewellery_backend.service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.siddhartha.jewellery_backend.dto.CommonFunctions;
import com.siddhartha.jewellery_backend.entity.MmGenMetalRateEntity;
import com.siddhartha.jewellery_backend.repo.MmGenMetalRateRepo;

@Service
public class MetalRateService {
	private final MmGenMetalRateRepo repo;

	public MetalRateService(MmGenMetalRateRepo repo) {
		this.repo = repo;
	}

	public List<MmGenMetalRateEntity> metalRateSave(ReqBean req) throws Exception {

		if (null == req.getGoldRatePerGram() || "".equals(req.getGoldRatePerGram()))
			throw new Exception("Gold Rate not present in request");
		if (null == req.getSilverRatePerGram() || "".equals(req.getSilverRatePerGram()))
			throw new Exception("Silver Rate not present in request");

		List<MmGenMetalRateEntity> existingData = repo.findByActiveFlag("Y");
		MmGenMetalRateEntity dataToSave = new MmGenMetalRateEntity();
		if (existingData.size() > 0) {
			System.out.println("Data present going for Update");
			for (MmGenMetalRateEntity eachData : existingData) {
				eachData.setActiveFlag("N");
			}
		} else {
			System.out.println("Data not present going for New insert");
		}
		dataToSave.setRateId(Long.parseLong(CommonFunctions.generateId()));
		dataToSave.setGoldRatePerGram(req.getGoldRatePerGram());
		dataToSave.setSilverRatePerGram(req.getSilverRatePerGram());
		dataToSave.setActiveFlag("Y");
		dataToSave.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		dataToSave.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
		existingData.add(dataToSave);

		return repo.saveAll(existingData);
	}
	
	public MmGenMetalRateEntity getCurrentRates() throws Exception {

		List<MmGenMetalRateEntity> existingData = repo.findByActiveFlag("Y");
		MmGenMetalRateEntity latestData = new MmGenMetalRateEntity();
		
		if(existingData.size()>0)
		{
			latestData = existingData.stream()
			        .max(Comparator.comparing(MmGenMetalRateEntity::getCreatedTimestamp))
			        .orElse(null);
		}else {
			throw new Exception("Metal rates not configured");
		}
        return latestData;
    }

}
