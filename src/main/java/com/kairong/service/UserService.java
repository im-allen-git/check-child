package com.kairong.service;

import com.kairong.pojo.UserInfo;
import com.kairong.pojo.UserPojo;

import java.util.List;
import java.util.Map;

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

    // 设备数据更新
    int equipmentInfoUp(String ip,String mac);

    // 根据mac取得userid
    int getUserId(String mac);

    //称重数据插入
    int weighingDataInsert(String userId,String mac,String item,String weight,String createTime);

    // 检查用户是否存在
    int checkUserIdExist(String mobile);

    // 保存注册用户数据
    int saveUserDataBase(UserPojo userPojo);

    // 获取用户数据
    UserPojo getUserInfoData(String userId);

    // 用户数据修改
    int updateUserInfoDataBase(UserPojo userPojo);


}
