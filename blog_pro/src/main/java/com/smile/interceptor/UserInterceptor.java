package com.smile.interceptor;


import com.smile.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-25 10:01
 * @deprecated 拦截器
 */
@Component
public class UserInterceptor  implements HandlerInterceptor{
    private static final String OPTIONS = "OPTIONS";

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {

        // 放行所有options请求
        if (OPTIONS.equals(request.getMethod())) {
            System.out.println("option请求");
            return true;
        }

        StringBuffer requestURL = request.getRequestURL();
//
        if (requestURL.indexOf("login") != -1) {
            return true;
        }
        String token = request.getHeader("authorization");
        System.out.println(token);
        if (!StringUtils.isEmpty(token)) {
            if ( redisUtils.ifLogin(token)) {
                return true;
            }
        }
        System.out.println("url" + requestURL + "未通过");
        return false;
    }


}
