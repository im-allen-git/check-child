package com.kairong.controller;

import com.kairong.pojo.UserInfo;
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

    @CrossOrigin("*")
    @GetMapping("/equipmentInfoUp")
    //设备数据更新
    public CommonResult equipmentInfoUp(HttpServletRequest request, HttpServletResponse response,String ip,String deviceId) {

        try {
            int count = userService.equipmentInfoUp(ip,deviceId);
            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("equipmentInfoUp, error:", deviceId, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @CrossOrigin("*")
    @GetMapping("/weighingDataInsert")
    //称重设备插入
    public CommonResult weighingDataInsert(HttpServletRequest request, HttpServletResponse response,String deviceId,String createTime,String items,String weights) {

        try {
            int count = 0;
            String[] aryItem =  items.split("-");
            String[] aryWeight =  weights.split("-");
            //查询设备对应的userId
            int userId = userService.getUserId(deviceId);

            for(int i=0; i< aryItem.length; i++){
                count = userService.weighingDataInsert(String.valueOf(userId) , deviceId, aryItem[i],  aryWeight[i] ,createTime);
            }
            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("weighingDataInsert, error:", deviceId, e);
            return CommonResult.failed(e.getMessage());
        }
    }


}
