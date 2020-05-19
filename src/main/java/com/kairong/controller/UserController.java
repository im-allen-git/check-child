package com.kairong.controller;

import com.kairong.controller.pojo.UserInfo;
import com.kairong.service.UserService;
import com.kairong.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.controller
 * @date:2020/5/18
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @CrossOrigin("*")
    @RequestMapping("/login")
    public CommonResult Login(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo null");
        Assert.isTrue(StringUtils.isNotBlank(userInfo.getEmail()), "email null");
        Assert.isTrue(StringUtils.isNotBlank(userInfo.getPass_word()), "pass_word null");
        try {
            int count = userService.checkEmail(userInfo);
            if (count > 0) {
                UserInfo loginInfo = userService.queryByEmail(userInfo);
                return CommonResult.success(1, loginInfo);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Login userInfo:[{}], error:", userInfo, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @CrossOrigin("*")
    @GetMapping("/loginGet")
    public CommonResult LoginGet(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo null");
        Assert.isTrue(StringUtils.isNotBlank(userInfo.getEmail()), "email null");
        Assert.isTrue(StringUtils.isNotBlank(userInfo.getPass_word()), "pass_word null");
        try {
            int count = userService.checkEmail(userInfo);
            if (count > 0) {
                UserInfo loginInfo = userService.queryByEmail(userInfo);
                return CommonResult.success(1, loginInfo);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Login userInfo:[{}], error:", userInfo, e);
            return CommonResult.failed(e.getMessage());
        }
    }


}
