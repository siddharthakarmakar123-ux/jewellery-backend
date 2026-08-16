package com.siddhartha.jewellery_backend.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReqBean {

	private String customerId;
	private String customerNumber;

	@NotBlank(message = "Full name is required")
	private String fullName;

	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Mobile number must be a valid 10 digit number")
	private String mobile;

	@Email(message = "Invalid email address")
	private String email;

	private String address;
	private String activeFlag;
	private String reqType;
	private String userName;
	private String password;
	private String goldRatePerGram;
    private String silverRatePerGram;
}
