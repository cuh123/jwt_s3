package com.example.boot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class AdminModel {

	private String token;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int no;
	
	private String id;
	private String password;
	private String name;
	private String phone;
	private String role;
	private String auth;
	private String create_dt;
	private String update_dt;
	private String creator;
	private String updator;
	private int id_cnt;
	private int phone_cnt;
}