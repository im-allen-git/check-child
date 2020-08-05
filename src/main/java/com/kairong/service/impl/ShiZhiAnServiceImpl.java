package com.kairong.service.impl;

import com.kairong.mapper.ShiZhiAnMapper;
import com.kairong.pojo.ShiZhiAnUser;
import com.kairong.service.ShiZhiAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.service.impl
 * @date:2020-08-04
 */
@Service
public class ShiZhiAnServiceImpl implements ShiZhiAnService {


    @Autowired
    private ShiZhiAnMapper shiZhiAnMapper;

    @Override
    public int insertUser(ShiZhiAnUser user) {
        return shiZhiAnMapper.insertUser(user);
    }

    @Override
    public int updateUser(ShiZhiAnUser user) {
        return shiZhiAnMapper.updateUser(user);
    }

    @Override
    public boolean checkUser(ShiZhiAnUser user) {
        return shiZhiAnMapper.checkUser(user) > 0;
    }

    @Override
    public ShiZhiAnUser selectUser(ShiZhiAnUser user) {
        return shiZhiAnMapper.selectUser(user);
    }
}
