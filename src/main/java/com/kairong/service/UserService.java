package com.kairong.service;

import com.kairong.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.service
 * @date:2020/5/18o
 */
public interface UserService {

//    UserInfo queryByEmail(UserInfo userInfo);
//
//    int checkEmail(UserInfo userInfo);
//
//    int insertUserInfo(UserInfo userInfo);
//
//    int updateUserInfo(UserInfo userInfo);

    // 设备数据更新
    int equipmentInfoUp(EquipmentPojo equipmentPojo);

    // 根据mac取得userid
    int getUserId(String mac);

    //称重数据插入
    int weighingDataInsert(String userId,String mac,String item,String weight,String createTime);

    // 检查用户是否存在
    UserPojo checkUserIdExist(UserPojo userPojo);

    // 保存注册用户数据
    int saveUserDataBase(UserPojo userPojo);

    // 获取用户数据
    UserPojo getUserInfoData(UserPojo userPojo);

    // 用户数据修改
    int updateUserInfoDataBase(UserPojo userPojo);

    // 检查用户是否存在
    int checkbingIdExist(BindingUserPojo bindingUserPojo);

    // 保存群组共享用户数据
    int saveBindingUserDataBase(BindingUserPojo bindingUserPojo);

    // 删除群组共享用户数据
    int bindingUserDel(BindingUserPojo bindingUserPojo);

    // 检查保存设备数据是否存在
    int checkEquipmentDataBase(EquipmentPojo equipmentPojo);

    // 保存设备数据
    int saveEquipmentDataBase(EquipmentPojo equipmentPojo);

    // 保存QA数据
    int saveQaDataBase(QAPojo qaPojo);

    // 保存日均摄入重量数据
    int saveWeightAvgAddBase(WeighingDataAvgPojo wighingDataAvgPojo);


    // 设备信息删除
    int equipmentDel(EquipmentPojo equipmentPojo);

    // 查询绑定用户信息数据
    List<BindingUserPojo> getBindingUserList(BindingUserPojo bindingUserPojo);

    // 设备信息修改
    int updateEquipment(EquipmentPojo equipmentPojo);

    // 设备信息修改
    int updateEquipments(EquipmentPojo equipmentPojo);

    // 查询设备信息数据
    List<EquipmentPojo> getEquipmentDataList(EquipmentPojo equipmentPojo);

    // 保存称重信息数据 手动输入
    int weighingdataAdd(WeighingdataPojo weighingdataPojo);

    // 重量信息修改
    int updateWeighingdata(WeighingdataPojo weighingdataPojo);

    // 逻辑删除称重信息数据单条数据
    int updateDelWeighingData(WeighingdataPojo weighingdataPojo);

    // 逻辑删除称重信息数据多条数据
    int updateDelWeighingAllData(WeighingdataPojo weighingdataPojo);

    // 查询称重信息数据
    List<WeighingdataPojo> getWeightingDataList(WeighingdataPojo weighingdataPojo);

    // 查询称重信息数据计算后
    List<WeighingdataPojo> getWeightingDataCalculateList(WeighingdataPojo weighingdataPojo);

    // 查询称重信息第几餐时间
    List<WeighingdataPojo> getCalculateList(WeighingdataPojo weighingdataPojo);

    // 查询称重信息数据编辑修改查询
    List<WeighingdataPojo> getWeightingDataListByHour(WeighingdataPojo weighingdataPojo);

    // 查询称重信息数据按周月年
    List<WeighingdataPojo> getWeightingDataListByYMWD(WeighingdataPojo weighingdataPojo);

    // 查询日均摄入数据
    List<WeighingDataAvgPojo> getWeightAvgList(WeighingDataAvgPojo weighingDataAvgPojo);

}
