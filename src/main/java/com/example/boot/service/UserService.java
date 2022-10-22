package com.example.boot.service;

import com.example.boot.config.jwt.JwtTokenUtil;
import com.example.boot.dao.UserDao;
import com.example.boot.model.UserDetailModel;
import com.example.boot.model.UserModel;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService {

	@Value("${aes.key}")
    private String AES_KEY;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Map<String, Object> getUserTokenWithUser(String phone_num) throws Exception {
        Map<String, Object> param = Maps.newHashMap();
        param.put("phone_num", phone_num);
        param.put("aesKey", AES_KEY);

        UserModel user = userDao.getUserInfo(param);
		Map<String, Object> result = Maps.newHashMap();

		result.put("token", jwtTokenUtil.generateToken(user));
		result.put("user_id", user.getUser_id());
		result.put("user_nm", user.getUser_nm());
		result.put("phone_num", user.getPhone_num());

     	return result;
    }

    public UserDetailModel getUserDetail(String userId) throws Exception {
        Map<String, Object> param = Maps.newHashMap();
        param.put("aesKey", AES_KEY);
        param.put("user_id", userId);

        return userDao.getUserDetail(param);

    }
}
