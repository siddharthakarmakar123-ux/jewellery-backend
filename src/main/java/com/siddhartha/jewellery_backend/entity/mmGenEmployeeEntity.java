package com.siddhartha.jewellery_backend.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mm_gen_employee")
@Data
public class MmGenEmployeeEntity {

	@Id
	@Column(name = "int_employee_id")
	private Long intEmployeeId;

	@Column(name = "employee_number", nullable = false, unique = true)
	private String employeeNumber;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "active_flag", nullable = false)
	private String activeFlag;
	
	@Column(name = "role_abbr", nullable = false)
	private String roleAbbr;

	@Column(name = "created_timestamp")
	private Timestamp createdTimestamp;

}
