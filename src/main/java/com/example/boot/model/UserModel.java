package com.example.boot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UserModel {

	private int no;
	
	@ApiModelProperty(value = "토큰")
	private String token;

	@ApiModelProperty(value = "사용자 ID")
	private String user_id;

	@ApiModelProperty(value = "사용자 이름")
	private String user_nm;

	@ApiModelProperty(value = "휴대폰 번호")
	private String phone_num;

	@ApiModelProperty(value = "수거자")
	private String sender_nm;

	@ApiModelProperty(value = "수거자 번")
	private String sender_phone_num;

	@ApiModelProperty(value = "수거지 주소")
	private String sender_addr;

	@ApiModelProperty(value = "수거지 공동현관 비밀번호")
	private String sender_gate_num;

	@ApiModelProperty(value = "수령인")
	private String receiver_nm;

	@ApiModelProperty(value = "수령인 번호")
	private String receiver_phone_num;

	@ApiModelProperty(value = "배송지 주소")
	private String receiver_addr;

	@ApiModelProperty(value = "배송지 공동현관 비밀번호")
	private String receiver_gate_num;

	@ApiModelProperty(value = "생성일")
	private String create_dt;

	@ApiModelProperty(value = "정보 수정일")
	private String update_dt;

	@ApiModelProperty(value = "로그인 날짜")
	private String login_dt;

	@ApiModelProperty(value = "탈퇴일")
	private String delete_dt;

	@ApiModelProperty(value = "광고성정보수신동의여부")
	private int ad_agree;

	private String recomm_num;

	private String sms_val;

	private String recomm_nm;
	private String post;
	private String base;
	private String detail;
	
	private int check;
}