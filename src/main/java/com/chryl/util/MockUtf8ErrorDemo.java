package com.chryl.util;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
/**
 * Created by Chryl on 2026/9/17.
 *
 * @author Chryl
 */
public class MockUtf8ErrorDemo {
    public static void main(String[] args) throws IOException {
        // JSON字符串
        String jsonStr = "{\"name\":\"张三\"}";
        // 【关键】使用GBK编码得到字节数组（不是UTF-8）
        byte[] gbkJsonBytes = jsonStr.getBytes(Charset.forName("GBK"));

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        HttpInputMessage inputMsg = new HttpInputMessage() {
            @Override
            public InputStream getBody() {
                return new ByteArrayInputStream(gbkJsonBytes);
            }
            @Override
            public org.springframework.http.HttpHeaders getHeaders() {
                org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
                headers.add("Content-Type", "application/json"); // 声明是json，但实际body是GBK字节！
                return headers;
            }
        };
        // 模拟Spring读取@RequestBody，直接抛出 JsonParseException: Invalid UTF-8 middle byte 0xc7
        converter.read(Object.class, inputMsg);
    }
}