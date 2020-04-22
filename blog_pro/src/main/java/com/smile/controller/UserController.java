package com.smile.controller;

import com.smile.domain.User;
import com.smile.service.UserService;
import com.smile.vo.BaseResult;
import com.smile.vo.CodeMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-11 18:19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public BaseResult login(@RequestBody User user) {
        String login = userService.login(user);
        return Optional.ofNullable(login)
                .map(BaseResult::success)
                .orElse(BaseResult.error(CodeMessage.LOGIN_ERROR));
    }

    @GetMapping
    public BaseResult getNowUser(HttpServletRequest request) {
        return BaseResult.success(userService.getNowUser(request));
    }

    @PutMapping
    public BaseResult editUser(@RequestBody User user) {
        userService.editUser(user);
        return BaseResult.success();
    }
}
