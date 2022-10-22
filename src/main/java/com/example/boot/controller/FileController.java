package com.example.boot.controller;

import com.example.boot.model.BaseModel;
import com.example.boot.model.BodyModel;
import com.example.boot.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/file")
public class FileController extends BaseController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value="/fileUpload", method=RequestMethod.POST)
	public BaseModel fileUpload(@RequestHeader("Authorization") String auth,
										@RequestParam("files") List<MultipartFile> files,
										@RequestParam("contact_type") String contactType,
										@RequestParam("contact_title") String contactTitle,
										@RequestParam("contact_txt") String contactTxt) throws Exception {
		BodyModel body = new BodyModel();
		body.setBody(fileService.fileUpload(auth, files, contactType, contactTitle, contactTxt));

		return ok(body);
	}
	
	@RequestMapping(value="/fileRemove", method=RequestMethod.DELETE)
	public BaseModel fileRemove(@RequestHeader("Authorization") String auth,
										   @RequestParam("contact_id") int contactId) throws Exception {
		BodyModel body = new BodyModel();
		body.setBody(fileService.fileRemove(auth, contactId));

		return ok(body);
	}

}
