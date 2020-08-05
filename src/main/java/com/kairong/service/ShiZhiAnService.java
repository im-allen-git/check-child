package com.kairong.service;

import com.kairong.pojo.ShiZhiAnUser;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.service
 * @date:2020-08-04
 */
public interface ShiZhiAnService {


    int insertUser(ShiZhiAnUser user);

    int updateUser(ShiZhiAnUser user);

    boolean checkUser(ShiZhiAnUser user);

    ShiZhiAnUser selectUser(ShiZhiAnUser user);

}
