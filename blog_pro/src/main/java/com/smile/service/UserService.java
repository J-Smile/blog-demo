package com.smile.service;

import com.smile.config.Role;
import com.smile.dao.UserDao;
import com.smile.domain.User;
import com.smile.exception.UserException;
import com.smile.utils.JWTUtil;
import com.smile.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author: Smile
 * @description:
 * @create: 2020-03-11 18:18
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    private final RedisUtils redisUtils;
    private final JWTUtil jwtUtil;

    public UserService(JWTUtil jwtUtil, RedisUtils redisUtils) {
        this.jwtUtil = jwtUtil;
        this.redisUtils = redisUtils;
    }


    public String login(User user) {
        boolean flag = StringUtils.isEmpty(user.getUsername()) && StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getPassword());
        if (flag) {
            return null;
        }
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo(user);
        User thisUser = userDao.selectOneByExample(example);
        if (thisUser == null) {
            return null;
        }
        String token = jwtUtil.createToken(Role.ADMIN, thisUser.getUid());
        redisUtils.saveUser(thisUser.getUid(), thisUser);
        return token;
    }

    public User findUserById(String uid) {
        return userDao.selectByPrimaryKey(uid);
    }

    public void saveUser(User user) {
        userDao.insert(user);
    }

    public User getNowUser(HttpServletRequest request) {
        return redisUtils.getUser(request.getHeader("authorization"));
    }

    public User findUserByAvatar(String avatar) {
        return userDao.findUserByAvatar(avatar);
    }

    @Transactional
    public void editUser(User user) {
        try {
            userDao.updateByPrimaryKey(user);
            redisUtils.saveUser(user.getUid(), user);
        } catch (Exception e) {
            throw new UserException("修改用户错误");
        }

    }

    public Integer getUserNum() {
       return  userDao.getUserNum();
    }
}
