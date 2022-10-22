package com.example.boot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailModel {

	private String token;
	private String user_id;
	private String user_nm;
	private String phone_num;
	private String sender_addr;
	private String sender_nm;
	private String sender_phone_num;
	private String sender_gate_num;
	private int ad_agree;
}
