package com.smile.utils;

import com.smile.domain.Author;
import com.smile.domain.Blog;
import com.smile.domain.Image;
import com.smile.domain.User;
import io.jsonwebtoken.Claims;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-25 11:21
 */
@Component
public class RedisUtils {

    private final RedisTemplate redisTemplate;
    private final JWTUtil jwtUtil;

    public RedisUtils(RedisTemplate redisTemplate, JWTUtil jwtUtil) {
        this.redisTemplate = redisTemplate;
        this.jwtUtil = jwtUtil;
    }

    public User getUser(String token) {
        Claims claims = jwtUtil.parseToken(token);
        return (User) Optional.ofNullable(claims)
                .map(e -> claims.get("uid"))
                .map(e -> redisTemplate.opsForValue().get("user_" + e))
                .orElse(null);

    }

    public void saveUser(String uid, @NotNull User user) {
        save("user_" + uid, user, 1, TimeUnit.DAYS);
    }

    public boolean ifLogin(String token) {
        if (redisTemplate.opsForValue().get(token) != null) {
            return true;
        }
        return false;
    }

    public Blog getBlog(String bid) {
        return (Blog) redisTemplate.opsForValue().get("blog_" + bid);
    }

    public void saveBlog(String id, Blog blog) {
        save("blog_" + id, blog, 1, TimeUnit.DAYS);
    }


    public List<Image> getLunBo() {
        return (List<Image>) get("image_lunbo");
    }

    public void saveLunBo(List<Image> all) {
        save("image_lunbo", all);
    }

    public Image getBackGround() {
        return (Image) get("image_background");
    }

    public void saveBackGround(Image image) {
        save("image_background", image);
    }

    public Author getAuthor() {
        return (Author) get("author");
    }

    public void saveAuthor(Author author) {
        save("author", author);
    }

    public void addRecord() {
        String time = LocalDate.now().toString().replaceAll("-", "_");
        Long size = redisTemplate.opsForValue().size("record_" + time);
        if (size==null){
            save("record_" + time,1,1,TimeUnit.DAYS);
        }else {
            saveRecord("record_" + time);
        }
    }

    public void save(String name, Object data) {
        redisTemplate.opsForValue().set(name, data);
    }

    public void save(String name, Object data, int time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(name, data, time, timeUnit);
    }

    public void saveRecord(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Object getRecord() {
        String time = LocalDate.now().toString().replaceAll("-", "_");
        return  get("record_" + time);
    }

}
