package com.yuschool.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResUtil {
    public static void writeObjectToResp(HttpServletResponse response, Object object) {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        try {
            mapper.writeValue(response.getOutputStream(), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
