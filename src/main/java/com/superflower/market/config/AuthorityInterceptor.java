package com.superflower.market.config;

import com.alibaba.fastjson.JSON;
import com.superflower.market.entity.Result;
import com.superflower.market.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Token");
        String oldToken = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            token = oldToken;
        }
        System.out.println(token + "*************");
        if (StringUtils.isNotEmpty(token)) {
            if (!JwtUtils.checkToken(token)) {
                // 返回一个登录错误对象
                Result result = Result.fail(2002l, "登录信息失效");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(result));
                writer.close();
                return false;
            }
            Map map = JwtUtils.decode(token);
            request.setAttribute("id", (String) map.get("id"));
        }
        return true;
    }
}
