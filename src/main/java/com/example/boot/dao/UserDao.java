package com.example.boot.dao;

import com.example.boot.model.UserDetailModel;
import com.example.boot.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("UserDao")
public class UserDao extends BaseDao {

    public UserModel getUserInfo(Map<String, Object> param) throws Exception {
        return (UserModel) queryForObject("UserMapper.getUserInfo", param);
    }

    public UserDetailModel getUserDetail(Map<String, Object> param) throws Exception {
        return (UserDetailModel) queryForObject("UserMapper.getUserDetail", param);
    }

}
