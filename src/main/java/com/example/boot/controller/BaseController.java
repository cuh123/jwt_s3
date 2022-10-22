package com.example.boot.controller;

import com.example.boot.model.BaseModel;
import com.example.boot.model.PageModel;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BaseController {

	protected final Log log = LogFactory.getLog(getClass());
	private final Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).create();

	@Autowired
	private Environment environment;

	protected String getMessage(String key){
		return environment.getProperty(key);
	}

	protected BaseModel ok(BaseModel model) {
		model.setResultCode(0);
		model.setDesc("호출성공");
		return model;
	}

	protected BaseModel pageOk(BaseModel model, PageModel pageModel) {
		HashMap<String, Object> map = Maps.newHashMap();
		map.put("page", pageModel.getPage());
		map.put("total", pageModel.getTotal());
		map.put("count", pageModel.getCount());

		model.setPage(map);
		return ok(model);
	}

	protected BaseModel pageOk(BaseModel model, PageModel pageModel, Map<String, Object> map) {
		map.put("page", pageModel.getPage());
		map.put("total", pageModel.getTotal());
		map.put("count", pageModel.getCount());
		
		model.setPage(map);
		return ok(model);
	}

	public void renderJSON(HttpServletResponse response, int httpStatus, BaseModel model) throws IOException {
		response.setStatus(httpStatus);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		String output = gson.toJson(model);

		PrintWriter writer = response.getWriter();
		writer.write(output);
		writer.flush();
	}

}
