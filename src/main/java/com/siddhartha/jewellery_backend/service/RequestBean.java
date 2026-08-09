package com.siddhartha.jewellery_backend.service;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class RequestBean {

	@Valid
	private ReqBean req;

}
