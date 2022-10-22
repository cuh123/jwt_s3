package com.example.boot.controller;

import com.example.boot.config.jwt.JwtTokenUtil;
import com.example.boot.model.BaseModel;
import com.example.boot.model.BodyModel;
import com.example.boot.model.UserDetailModel;
import com.example.boot.model.UserModel;
import com.example.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
	private JwtTokenUtil tokenUtil;
    
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public BaseModel userJoin(@RequestBody UserModel userModel) throws Exception {
		BodyModel body = new BodyModel();

		Map<String, Object> user = userService.getUserTokenWithUser(userModel.getPhone_num());

		body.setBody(user);
		return ok(body);
	}

	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public BaseModel userDetail(@RequestHeader(required = false, value = "Authorization") String token) throws Exception {
		BodyModel body = new BodyModel();
		String user_id = tokenUtil.getUserIdFromToken(token);
		UserDetailModel result = userService.getUserDetail(user_id);
		result.setToken(token);
		body.setBody(result);

		return ok(body);
	}

}
