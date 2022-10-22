package com.example.boot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class FileModel {
    
    private int contact_id;
	
    private String user_id;
	
    private String contact_title;
	
    private String contact_txt;
	
    private String contact_type;
	
    private int contact_answer_status;
	
    private String contact_answer_txt;
	
    private String contact_pic_url_1;
	
    private String contact_pic_url_2;
	
    private String create_dt;
	
    private String update_dt;
	
    private String delete_dt;

	private String creator;
	
	private String updator;
}
