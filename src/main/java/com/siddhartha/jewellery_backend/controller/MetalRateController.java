package com.siddhartha.jewellery_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siddhartha.jewellery_backend.entity.MmGenMetalRateEntity;
import com.siddhartha.jewellery_backend.service.MetalRateService;
import com.siddhartha.jewellery_backend.service.ReqBean;
import com.siddhartha.jewellery_backend.service.RequestBean;

@RestController
@RequestMapping("/api/rates")
public class MetalRateController {
	private final MetalRateService service;
	
	public MetalRateController(MetalRateService service) {
		this.service = service;
	}

    @PostMapping("/save")
    public ResponseEntity<List<MmGenMetalRateEntity>> saveRates(
            @RequestBody RequestBean request) throws Exception {

    	ReqBean req=request.getReq();
        List<MmGenMetalRateEntity> savedRate = service.metalRateSave(req);

        return ResponseEntity.ok(savedRate);
    }

    @PostMapping("/search")
    public MmGenMetalRateEntity getCurrentRates() throws Exception {

    	MmGenMetalRateEntity respData = new MmGenMetalRateEntity();
    	respData= service.getCurrentRates();
         
         return respData;
    }
}
