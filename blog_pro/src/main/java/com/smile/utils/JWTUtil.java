package com.smile.utils;

import com.smile.config.Role;
import com.smile.exception.TokenException;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-02 15:26
 */
@Component
public class JWTUtil {


    @Value(value = "${data.jwt.key}")
    private String key;
    @Value(value = "${data.jwt.time}")
    private int time;
    @Value(value = "${data.url.web}")
    private String webUrl;

    /**
     * 获取token中的参数
     *
     * @param token
     * @return
     */
    public Claims parseToken(String token) {
        if ("".equals(token)) {
            return null;
        }
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                    .parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            throw new TokenException("token失效");
        }
    }

    /**
     * 生成token
     *
     * @return 角色
     */
    public String createToken(String role, String uid) {
        System.out.println(webUrl);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签名密钥
        System.out.println(key);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .claim("role", role) // 设置载荷信息
                .claim("uid", uid)
                .setExpiration(DateTime.now().plusMinutes(time).toDate())// 设置超时时间
                .signWith(signatureAlgorithm, signingKey);

        //生成JWT
        return builder.compact();
    }


    public static void main(String[] args) {
//        JWTUtil jwtUtil = new JWTUtil();
//        String token = jwtUtil.createToken(Role.ADMIN, "123");
//        System.out.println(token);
//
//        Claims claims = jwtUtil.parseToken(token);
//
//        System.out.println(claims);


    }


}