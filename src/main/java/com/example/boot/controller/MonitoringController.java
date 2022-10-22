package com.example.boot.controller;

import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/monitor")
public class MonitoringController extends BaseController {

    @RequestMapping(value = "/check", method = {RequestMethod.GET})
    public Map checkServerStatus() throws IOException {
        Map<String, Object> resultMap = Maps.newHashMap();
        Map<String, Object> status = Maps.newHashMap();

        resultMap.put("result", "OK");
        status.put("http", HttpStatus.OK.value());
        status.put("version", getMessage("server.version"));
        resultMap.put("status", status);

        return resultMap;
    }

}