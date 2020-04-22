package com.smile.controller;


import com.alibaba.fastjson.JSONObject;
import com.smile.config.Role;
import com.smile.domain.MaYunUser;
import com.smile.domain.User;
import com.smile.service.UserService;
import com.smile.utils.JWTUtil;
import com.smile.utils.RedisUtils;
import com.smile.utils.UUid;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author: Smile
 * @description: 认证
 * @create: 2020-04-02 15:51
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

//    private static Logger log = LogManager.getLogger(AuthController.class);

    @Value(value = "${justAuth.clientId.gitee}")
    private String giteeClienId;
    @Value(value = "${justAuth.clientSecret.gitee}")
    private String giteeClientSecret;
    @Value(value = "${data.url.website}")
    private String webSiteUrl;
    @Value(value = "${data.url.web}")
    private String webUrl;

    private final RedisUtils redisUtils;
    private final JWTUtil jwtUtil;
    private final UserService userService;

    public AuthController(JWTUtil jwtUtil, UserService userService, RedisUtils redisUtils) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.redisUtils = redisUtils;
    }

    @GetMapping("/render")
    public String renderAuth() {
        AuthRequest authRequest = getAuthRequest();
        return authRequest.authorize(AuthStateUtils.createState());
    }

    @RequestMapping("/callback")
    public void login(AuthCallback callback, HttpServletResponse response, HttpServletRequest request) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        AuthResponse authResponse = authRequest.login(callback);
        String result = JSONObject.toJSONString(authResponse.getData());
        MaYunUser maYunUser = JSONObject.parseObject(result, MaYunUser.class);
        User user = userService.findUserByAvatar(maYunUser.getAvatar());
        if (user == null) {
            user = new User();
            user.setUid(UUid.getId());
            user.setAvatar(maYunUser.getAvatar());
            user.setNickname(maYunUser.getNickname());
            user.setIfadmin(false);
            user.setUsername(maYunUser.getUsername());
            userService.saveUser(user);
        }
        redisUtils.saveUser(user.getUid(), user);
        String token = jwtUtil.createToken(Role.USER, user.getUid());
        response.sendRedirect(webSiteUrl + "/" + token);
    }

    private AuthRequest getAuthRequest() {
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId(giteeClienId)
                .clientSecret(giteeClientSecret)
                .redirectUri(webUrl)
                .build());
    }
}

