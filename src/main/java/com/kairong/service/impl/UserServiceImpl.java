package com.kairong.service.impl;

import com.kairong.pojo.UserInfo;
import com.kairong.mapper.UserMapper;
import com.kairong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.service.impl
 * @date:2020/5/18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserInfo queryByEmail(UserInfo userInfo) {
        return userMapper.queryByEmail(userInfo);
    }

    @Override
    public int checkEmail(UserInfo userInfo) {
        return userMapper.checkEmail(userInfo);
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        return userMapper.insertUserInfo(userInfo);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userMapper.updateUserInfo(userInfo);
    }


    @Override
    public int equipmentInfoUp(String ip,String mac) {
        return userMapper.equipmentInfoUp(ip,mac);
    }

    @Override
    public int getUserId(String mac) {
        return userMapper.getUserId(mac);
    }



    @Override
    public int weighingDataInsert(String userId,String mac,String item,String weight,String createTime) {
        return userMapper.weighingDataInsert(userId , mac, item, weight ,createTime);
    }




}
