package com.chryl.controller;

import com.alibaba.fastjson2.JSONObject;
import com.chryl.util.JsonKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Chr.yl on 2026/7/8.
 *
 * @author Chr.yl
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("show")
    public void show(String fileId, String fileUrl, String fileName) {
//    public void show() {
        log.info("id:{} , url:{} , name:{}", fileId, fileUrl, fileName);
        log.info("id: , url: , name:");
    }


}
