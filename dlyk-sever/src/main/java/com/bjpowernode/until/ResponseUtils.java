package com.bjpowernode.until;

import cn.hutool.json.JSONUtil;
import com.bjpowernode.result.R;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Response工具类
 *
 */
public class ResponseUtils {

    public static void writeJson(HttpServletResponse response, R result) {
        //设置响应的内容类型
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String json = JSONUtil.toJsonStr(result);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
