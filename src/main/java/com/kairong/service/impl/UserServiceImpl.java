package com.kairong.service.impl;

import com.kairong.pojo.*;
import com.kairong.mapper.UserMapper;
import com.kairong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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


//    @Override
//    public UserInfo queryByEmail(UserInfo userInfo) {
//        return userMapper.queryByEmail(userInfo);
//    }
//
//    @Override
//    public int checkEmail(UserInfo userInfo) {
//        return userMapper.checkEmail(userInfo);
//    }
//
//    @Override
//    public int insertUserInfo(UserInfo userInfo) {
//        return userMapper.insertUserInfo(userInfo);
//    }
//
//    @Override
//    public int updateUserInfo(UserInfo userInfo) {
//        return userMapper.updateUserInfo(userInfo);
//    }


    @Override
    public int equipmentInfoUp(EquipmentPojo equipmentPojo) {
        return userMapper.equipmentInfoUp(equipmentPojo);
    }

    @Override
    public int getUserId(String mac) {
        return userMapper.getUserId(mac);
    }



    @Override
    public int weighingDataInsert(String userId,String mac,String item,String weight,String createTime) {
        return userMapper.weighingDataInsert(userId , mac, item, weight ,createTime);
    }


    @Override
    public UserPojo checkUserIdExist(UserPojo userPojo) {
        return userMapper.checkUserIdExist(userPojo);
    }

    @Override
    public int saveUserDataBase(UserPojo userPojo) {
        return userMapper.saveUserDataBase(userPojo);
    }

    @Override
    public UserPojo getUserInfoData(UserPojo userPojo) {
        return userMapper.getUserInfoData(userPojo);
    }
    @Override
    public int updateUserInfoDataBase(UserPojo userPojo) {
        return userMapper.updateUserInfoDataBase(userPojo);
    }

    @Override
    public int checkbingIdExist(BindingUserPojo bindingUserPojo) {
        return userMapper.checkbingIdExist(bindingUserPojo);
    }

    @Override
    public int saveBindingUserDataBase(BindingUserPojo bindingUserPojo) {
        return userMapper.saveBindingUserDataBase(bindingUserPojo);
    }

    @Override
    public int bindingUserDel(BindingUserPojo bindingUserPojo) {
        return userMapper.bindingUserDel(bindingUserPojo);
    }

    @Override
    public int saveEquipmentDataBase(EquipmentPojo equipmentPojo) {
        return userMapper.saveEquipmentDataBase(equipmentPojo);
    }

    @Override
    public int saveQaDataBase(QAPojo qaPojo) {
        return userMapper.saveQaDataBase(qaPojo);
    }

    @Override
    public int saveWeightAvgAddBase(WeighingDataAvgPojo wighingDataAvgPojo) {
        return userMapper.saveWeightAvgAddBase(wighingDataAvgPojo);
    }


    @Override
    public int checkEquipmentDataBase(EquipmentPojo equipmentPojo) {
        return userMapper.checkEquipmentDataBase(equipmentPojo);
    }


    @Override
    public int equipmentDel(EquipmentPojo equipmentPojo) {
        return userMapper.equipmentDel(equipmentPojo);
    }

    @Override
    public List<BindingUserPojo> getBindingUserList(BindingUserPojo bindingUserPojo) {
        return userMapper.getBindingUserList(bindingUserPojo);
    }

    @Override
    public int updateEquipment(EquipmentPojo equipmentPojo) {
        return userMapper.updateEquipment(equipmentPojo);
    }

    @Override
    public int updateEquipments(EquipmentPojo equipmentPojo) {
        return userMapper.updateEquipments(equipmentPojo);
    }

    @Override
    public List<EquipmentPojo> getEquipmentDataList(EquipmentPojo equipmentPojo) {
        return userMapper.getEquipmentDataList(equipmentPojo);
    }

    @Override
    public int weighingdataAdd(WeighingdataPojo weighingdataPojo) {
        return userMapper.weighingdataAdd(weighingdataPojo);
    }


    @Override
    public int updateWeighingdata(WeighingdataPojo weighingdataPojo) {

        int count =0;
        if( !"".equals(weighingdataPojo.getWeightStr()) && weighingdataPojo.getWeightStr() !=null ){
            // "1:10;2:20;3:30"  id:weight
            String[] weightArry =  weighingdataPojo.getWeightStr().split(";");
            //String数组转List
            List<String> weightList= Arrays.asList(weightArry);
            for(String ws:weightList){
                if( !"".equals(ws.split(":")[1]) && ws.split(":")[1] !=null ){
                    weighingdataPojo.setWeight(String.valueOf(ws.split(":")[1]));
                    weighingdataPojo.setId(Integer.valueOf(ws.split(":")[0]));
                }
                count = userMapper.updateWeighingdata(weighingdataPojo);
            }
        }

        return count;
    }

    @Override
    public int updateDelWeighingData(WeighingdataPojo weighingdataPojo) {

        int count = userMapper.updateDelWeighingData(weighingdataPojo);

        return count;
    }

    @Override
    public int updateDelWeighingAllData(WeighingdataPojo weighingdataPojo) {

        int count =0;
        // "1:2:3"
        String[] weightArry =  weighingdataPojo.getIdAllStr().split(":");
        //String数组转List
        List<String> idList= Arrays.asList(weightArry);
        for(String idS:idList){
            weighingdataPojo.setId(Integer.valueOf(idS));
            count = userMapper.updateDelWeighingData(weighingdataPojo);
        }
        return count;
    }


    @Override
    public List<WeighingdataPojo> getWeightingDataList(WeighingdataPojo weighingdataPojo) {
        return userMapper.getWeightingDataList(weighingdataPojo);
    }

    @Override
    public List<WeighingDataAvgPojo> getWeightAvgList(WeighingDataAvgPojo weighingDataAvgPojo) {
        return userMapper.getWeightAvgList(weighingDataAvgPojo);
    }



    @Override
    public List<WeighingdataPojo> getWeightingDataCalculateList(WeighingdataPojo weighingdataPojo) {
        return userMapper.getWeightingDataCalculateList(weighingdataPojo);
    }

    @Override
    public List<WeighingdataPojo> getCalculateList(WeighingdataPojo weighingdataPojo) {
        return userMapper.getCalculateList(weighingdataPojo);
    }



    @Override
    public List<WeighingdataPojo> getWeightingDataListByHour(WeighingdataPojo weighingdataPojo) {
        return userMapper.getWeightingDataListByHour(weighingdataPojo);
    }

    @Override
    public List<WeighingdataPojo> getWeightingDataListByYMWD(WeighingdataPojo weighingdataPojo) {
        return userMapper.getWeightingDataListByYMWD(weighingdataPojo);
    }


}
