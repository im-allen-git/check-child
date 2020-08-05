package com.kairong.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kairong.pojo.UserInfo;
import com.kairong.pojo.UserPojo;
import com.kairong.service.UserService;
import com.kairong.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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



//    // 客户端发过来的数据
//    @PostMapping("/userDataSync")
//    public String userDataSync( @RequestParam("userListSync") String userListSync,HttpServletRequest request, HttpServletResponse response) {
//
//        List<UserPojo> userList = JSONObject.parseArray(userListSync,  UserPojo.class);
//
//        for (int i=0; i<userList.size(); i++) {
//            System.out.println(userList.get(i).getUserId());
//        }
//
//        return "";
//    }


    @CrossOrigin("*")
    @GetMapping("/registerOrLogin")
    // 保存注册用户数据
    public CommonResult registerOrLogin(HttpServletRequest request, HttpServletResponse response,String nickName,String mobile) {

        try {

            int userId = userService.checkUserIdExist(mobile);
            if(userId ==0){
                UserPojo userPojo = new UserPojo();
                userPojo.setNick_name(nickName);
                userPojo.setMobile(mobile);

                // 保存注册用户数据
                userId = userService.saveUserDataBase(userPojo);
                // session保存userId
                request.getSession().setAttribute("userId", userId);
            }

            if (userId > 0) {
                return CommonResult.success(1, userId);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("registerOrLogin, error:", mobile, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @CrossOrigin("*")
    @GetMapping("/getUserId")
    // 取得session里的userid
    public String getUserId(HttpServletRequest request, HttpServletResponse response) {

        String userId = (String) request.getSession().getAttribute("userId");
        return userId;

    }

    @CrossOrigin("*")
    @GetMapping("/getUserInfoDataList")
    // 查询用户信息数据
    public String getUserInfoDataList(HttpServletRequest request, HttpServletResponse response,String userId) {

        // 查询用户信息数据
        UserPojo userInfo = userService.getUserInfoData(userId);

        return JSON.toJSONString(userInfo);

    }


    @CrossOrigin("*")
    @GetMapping("/updateUserInfo")
    // 用户数据修改
    public CommonResult updateUserInfo(HttpServletRequest request, HttpServletResponse response,String userId,String nickName,String sex,String birthday,
                                       String height,String weight,String wasteRate,String number) {

        try {

            UserPojo userPojo = new UserPojo();
            userPojo.setUser_id(userId);
            userPojo.setNick_name(nickName);
            userPojo.setSex(sex);
            userPojo.setBirthday(birthday);
            userPojo.setHeight(height);
            userPojo.setWeight(weight);
            userPojo.setWaste_rate(wasteRate);
            userPojo.setNumber(number);

            int count = userService.updateUserInfoDataBase(userPojo);

            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateUserInfo, error:", userId, e);
            return CommonResult.failed(e.getMessage());
        }
    }


}
