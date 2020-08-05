package com.kairong.controller;

import com.kairong.pojo.ShiZhiAnUser;
import com.kairong.service.ShiZhiAnService;
import com.kairong.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.controller
 * @date:2020-08-04
 */
@Slf4j
@RestController
@RequestMapping("/shiZhiAn")
public class ShiZhiAnController {


    @Autowired
    private ShiZhiAnService shiZhiAnService;

    @PostMapping("/register")
    public CommonResult register(ShiZhiAnUser user) {

        Assert.notNull(user, "user info is null");
        try {
            if (shiZhiAnService.checkUser(user)) {
                System.out.println("user" + user + " exists");
                ShiZhiAnUser anUser = shiZhiAnService.selectUser(user);
                return CommonResult.success(anUser.getId());
            } else {
                shiZhiAnService.insertUser(user);
                System.err.println("user" + user + " is not exists");
                return CommonResult.success(user.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("register user[{}],error:", user, e);
            return CommonResult.failed(e.getMessage());
        }
    }
}
