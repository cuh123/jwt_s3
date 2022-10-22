package com.example.boot.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseDao {

	@Autowired
	private SqlSession sessionTemplate;
	
	protected final Log log = LogFactory.getLog(getClass());

	public Object queryForObject(String qureyId){
		Object object = sessionTemplate.selectOne(qureyId);
		return object;
	}
	
	public Object queryForObject(String qureyId, Object obj){
		Object object = sessionTemplate.selectOne(qureyId, obj);
		return object;
	}
	
	public List queryForList(String qureyId, Object obj){
		List<?> objectList = sessionTemplate.selectList(qureyId, obj);
		return objectList;
	}
	
	public List queryForList(String qureyId){
		List<?> objectList = sessionTemplate.selectList(qureyId);
		return objectList;
	}
	
	public int update(String qureyId, Object obj){
		return sessionTemplate.update(qureyId, obj);
	}
	
	public int insert(String qureyId, Object obj){
		return sessionTemplate.insert(qureyId, obj);
	}
	
	public int delete(String qureyId, Object obj){
		return sessionTemplate.delete(qureyId, obj);
	}

}
