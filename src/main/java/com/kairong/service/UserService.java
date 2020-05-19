package com.kairong.service;

import com.kairong.controller.pojo.UserInfo;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.service
 * @date:2020/5/18o
 */
public interface UserService {

    UserInfo queryByEmail(UserInfo userInfo);

    int checkEmail(UserInfo userInfo);

    int insertUserInfo(UserInfo userInfo);

    int updateUserInfo(UserInfo userInfo);

}
