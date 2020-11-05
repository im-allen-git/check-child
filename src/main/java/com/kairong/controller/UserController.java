package com.kairong.controller;

import com.alibaba.fastjson.JSON;
import com.kairong.pojo.BindingUserPojo;
import com.kairong.pojo.EquipmentPojo;
import com.kairong.pojo.UserPojo;
import com.kairong.pojo.WeighingdataPojo;
import com.kairong.service.UserService;
import com.kairong.util.CommonResult;
import com.kairong.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: zlw
 * @version: v1.0
 * @description: com.kairong.controller
 * @date:2020/8/05
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

//    @CrossOrigin("*")
//    @RequestMapping("/login")
//    public CommonResult Login(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) {
//        Assert.notNull(userInfo, "userInfo null");
//        Assert.isTrue(StringUtils.isNotBlank(userInfo.getEmail()), "email null");
//        Assert.isTrue(StringUtils.isNotBlank(userInfo.getPass_word()), "pass_word null");
//        try {
//            int count = userService.checkEmail(userInfo);
//            if (count > 0) {
//                UserInfo loginInfo = userService.queryByEmail(userInfo);
//                return CommonResult.success(1, loginInfo);
//            } else {
//                return CommonResult.success(0, null);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("Login userInfo:[{}], error:", userInfo, e);
//            return CommonResult.failed(e.getMessage());
//        }
//    }
//
//    @CrossOrigin("*")
//    @GetMapping("/loginGet")
//    public CommonResult LoginGet(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) {
//        Assert.notNull(userInfo, "userInfo null");
//        Assert.isTrue(StringUtils.isNotBlank(userInfo.getEmail()), "email null");
//        Assert.isTrue(StringUtils.isNotBlank(userInfo.getPass_word()), "pass_word null");
//        try {
//            int count = userService.checkEmail(userInfo);
//            if (count > 0) {
//                UserInfo loginInfo = userService.queryByEmail(userInfo);
//                return CommonResult.success(1, loginInfo);
//            } else {
//                return CommonResult.success(0, null);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("Login userInfo:[{}], error:", userInfo, e);
//            return CommonResult.failed(e.getMessage());
//        }
//    }

    @PostMapping("/equipmentInfoUp")
    //设备数据更新 硬件调用接口
    public CommonResult equipmentInfoUp(HttpServletRequest request, HttpServletResponse response,EquipmentPojo equipmentPojo) {

        try {
            int count = userService.equipmentInfoUp(equipmentPojo);
            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("equipmentInfoUp, error:", equipmentPojo, e);
            return CommonResult.failed(e.getMessage());
        }
    }

//    @PostMapping("/weighingDataInsert")
//    //称重设备插入 硬件调用接口
//    public CommonResult weighingDataInsert(HttpServletRequest request, HttpServletResponse response,String deviceId,String createTime,String items,String weights) {
//
//        try {
//            int count = 0;
//            String[] aryItem =  items.split("-");
//            String[] aryWeight =  weights.split("-");
//            //查询设备对应的userId
//            int userId = userService.getUserId(deviceId);
//
//            for(int i=0; i< aryItem.length; i++){
//                count = userService.weighingDataInsert(String.valueOf(userId) , deviceId, aryItem[i],  aryWeight[i] ,createTime);
//            }
//            if (count > 0) {
//                return CommonResult.success(1, count);
//            } else {
//                return CommonResult.success(0, null);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("weighingDataInsert, error:", deviceId, e);
//            return CommonResult.failed(e.getMessage());
//        }
//    }


    @PostMapping("/weighingDataInsert")
    //称重设备插入 硬件调用接口
    public CommonResult weighingDataInsert(HttpServletRequest request, HttpServletResponse response,WeighingdataPojo weighingdataPojo) {

        try {
            //查询设备对应的userId
            int userId = userService.getUserId(weighingdataPojo.getMac());

            weighingdataPojo.setUser_id(String.valueOf(userId));
            weighingdataPojo.setCreate_time(TimeUtil.stampToDate(weighingdataPojo.getCreate_time()));
            // 插入数据
            int id = userService.weighingdataAdd(weighingdataPojo);

            if (id > 0) {
                return CommonResult.success(1, id);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("weighingDataInsert, error:", weighingdataPojo, e);
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


    @PostMapping("/registerOrLogin")
    // 保存注册用户数据
    public CommonResult registerOrLogin(HttpServletRequest request, HttpServletResponse response,UserPojo userPojo) {

        try {
            int userId =0;
            UserPojo user = userService.checkUserIdExist(userPojo);
            if(user != null){
                userId = user.getUser_id();
            }
            if(userId ==0){
//                UserPojo userPojo = new UserPojo();
//                userPojo.setNick_name(nickName);
//                userPojo.setMobile(mobile);
                // 保存注册用户数据
                int count = userService.saveUserDataBase(userPojo);
                // session保存userId
//                request.getSession().setAttribute(String.valueOf(userPojo.getId()),userPojo);
                userId = userPojo.getUser_id();
            }
            if (userId > 0) {
                return CommonResult.success(1, userId);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("registerOrLogin, error:", userPojo.getMobile(), e);
            return CommonResult.failed(e.getMessage());
        }
    }

//    @PostMapping("/getUserId")
//    // 取得session里的userid
//    public String getUserId(HttpServletRequest request, HttpServletResponse response) {
//
//        String userId = (String) request.getSession().getAttribute("userId");
//        return JSON.toJSONString(userId);
//
//    }

    @PostMapping("/getUserInfoDataList")
    // 查询用户信息数据
    public String getUserInfoDataList(HttpServletRequest request, HttpServletResponse response,UserPojo userPojo) {

        // 查询用户信息数据
        UserPojo userInfo = userService.getUserInfoData(userPojo);

        return JSON.toJSONString(userInfo);

    }


    @PostMapping("/updateUserInfo")
    // 用户数据修改
    public CommonResult updateUserInfo(HttpServletRequest request, HttpServletResponse response,UserPojo userPojo) {

        Assert.notNull(userPojo, "UserPojo info is null");
        try {

            int count = userService.updateUserInfoDataBase(userPojo);

            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateUserInfo, error:", userPojo.getUser_id(), e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @PostMapping("/bindingUserAdd")
    // 保存绑定用户数据
    public CommonResult bindingUserAdd(HttpServletRequest request, HttpServletResponse response,BindingUserPojo bindingUserPojo) {

        Assert.notNull(bindingUserPojo, "bindingUserPojo info is null");
        try {

            int count =0;
            int checkFlag =0;
            //检查绑定的手机是否是我们app用户
            UserPojo userPojo =new UserPojo();
            userPojo.setMobile(bindingUserPojo.getBinding_userid());
            UserPojo user = userService.checkUserIdExist(userPojo);
            if(user != null){
                // 检查绑定用户是否存在
                checkFlag = userService.checkbingIdExist(bindingUserPojo);
                if(checkFlag ==0){
                    // 保存群组共享用户数据
                    count = userService.saveBindingUserDataBase(bindingUserPojo);
                }
            }else{
                checkFlag = 3;
            }
            if (checkFlag > 0) {
                if(checkFlag==3){
                    //绑定的手机不是我们app用户
                    return CommonResult.success(3, checkFlag);
                }else{
                    //绑定用户已存在
                    return CommonResult.success(2, checkFlag);
                }
            } else if (count>0) {
                return CommonResult.success(1, count);
            } else{
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("bindingUserAdd, error:", bindingUserPojo.getUser_id(), e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @PostMapping("/bindingUserDel")
    // 删除群组共享用户数据
    public CommonResult bindingUserDel(HttpServletRequest request, HttpServletResponse response,BindingUserPojo bindingUserPojo) {

        Assert.notNull(bindingUserPojo, "bindingUserPojo info is null");
        try {

            // 删除群组共享用户数据
            int count = userService.bindingUserDel(bindingUserPojo);

            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("bindingUserDel, error:", bindingUserPojo.getUser_id(), e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @PostMapping("/getBindingUserList")
    // 查询绑定用户信息数据
    public String getBindingUserList(HttpServletRequest request, HttpServletResponse response,BindingUserPojo bindingUserPojo) {

        // 查询绑定用户信息数据
        List<BindingUserPojo>  bindingUserPojoList= userService.getBindingUserList(bindingUserPojo);

        return JSON.toJSONString(bindingUserPojoList);

    }


    @PostMapping("/equipmentAdd")
    // 保存设备信息数据
    public CommonResult equipmentAdd(HttpServletRequest request, HttpServletResponse response,EquipmentPojo equipmentPojo) {

        Assert.notNull(equipmentPojo, "equipmentPojo info is null");
        try {

            // 检查绑定保存设备信息数据是否存在
            int count = userService.checkEquipmentDataBase(equipmentPojo);
            if(count ==0){
                // 保存设备信息数据
                count = userService.saveEquipmentDataBase(equipmentPojo);
            }

            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("equipmentAdd, error:", equipmentPojo, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @PostMapping("/equipmentDel")
    // 设备信息删除
    public CommonResult equipmentDel(HttpServletRequest request, HttpServletResponse response,EquipmentPojo equipmentPojo) {

        try {
            int count =0;
            // 设备信息删除
            count = userService.equipmentDel(equipmentPojo);

            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("equipmentDel, error:", equipmentPojo, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @PostMapping("/updateEquipment")
    // 更新设备信息数据
    public CommonResult updateEquipment(HttpServletRequest request, HttpServletResponse response,EquipmentPojo equipmentPojo) {

        Assert.notNull(equipmentPojo, "equipmentPojo info is null");
        try {

            // 保存设备信息数据
            int count = userService.updateEquipment(equipmentPojo);

            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateEquipment, error:", equipmentPojo, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @PostMapping("/updateEquipments")
    // 更新设备信息数据左设备变换右设备
    public CommonResult updateEquipments(HttpServletRequest request, HttpServletResponse response,EquipmentPojo equipmentPojo) {

        Assert.notNull(equipmentPojo, "equipmentPojo info is null");
        try {

            // 保存设备信息数据
            int count = userService.updateEquipments(equipmentPojo);

            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateEquipments, error:", equipmentPojo, e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @PostMapping("/getEquipmentDataList")
    // 查询设备信息数据
    public String getEquipmentDataList(HttpServletRequest request, HttpServletResponse response,EquipmentPojo equipmentPojo) {

        // 查询设备信息数据
        List<EquipmentPojo>  equipmentPojoList= userService.getEquipmentDataList(equipmentPojo);

        return JSON.toJSONString(equipmentPojoList);

    }

    @PostMapping("/weighingdataAdd")
    // 保存称重信息数据 手动输入
    public CommonResult weighingdataAdd(HttpServletRequest request, HttpServletResponse response,WeighingdataPojo weighingdataPojo) {

        Assert.notNull(weighingdataPojo, "weighingdataPojo info is null");
        try {

//            weighingdataPojo.setCreate_time(TimeUtil.stampToDate(weighingdataPojo.getCreate_time()));
            // 保存称重信息数据 手动输入
            int id = userService.weighingdataAdd(weighingdataPojo);
            id = weighingdataPojo.getId();
            if (id > 0) {
                return CommonResult.success(1, id);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("weighingdataAdd, error:", weighingdataPojo, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @PostMapping("/updateWeighingdata")
    // 更新称重信息数据
    public CommonResult updateWeighingdata(HttpServletRequest request, HttpServletResponse response,WeighingdataPojo weighingdataPojo) {

        Assert.notNull(weighingdataPojo, "weighingdataPojo info is null");
        try {

            // 更新称重信息数据
            int count = userService.updateWeighingdata(weighingdataPojo);

            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateEquipment, error:", weighingdataPojo, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @PostMapping("/updateDelWeighingData")
    // 逻辑删除称重信息数据单条数据
    public CommonResult updateDelWeighingData(HttpServletRequest request, HttpServletResponse response,WeighingdataPojo weighingdataPojo) {

        Assert.notNull(weighingdataPojo, "weighingdataPojo info is null");
        try {

//            WeighingdataPojo weighingdataPojo = new WeighingdataPojo();
//            weighingdataPojo.setId(Integer.valueOf(id));
            // 逻辑删除称重信息数据单条数据
            int count = userService.updateDelWeighingData(weighingdataPojo);

            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateDelWeighingData, error:", weighingdataPojo, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @PostMapping("/updateDelWeighingAllData")
    // 逻辑删除称重信息数据多条数据
    public CommonResult updateDelWeighingAllData(HttpServletRequest request, HttpServletResponse response,WeighingdataPojo weighingdataPojo) {

        Assert.notNull(weighingdataPojo, "weighingdataPojo info is null");
        try {
            // 逻辑删除称重信息数据多条数据
            int count = userService.updateDelWeighingAllData(weighingdataPojo);

            if (count > 0) {
                return CommonResult.success(1, count);
            } else {
                return CommonResult.success(0, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateDelWeighingAllData, error:", weighingdataPojo, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @PostMapping("/getWeightingDataList")
    // 查询称重信息数据所有
    public String getWeightingDataList(HttpServletRequest request, HttpServletResponse response,WeighingdataPojo weighingdataPojo) {

        // 查询称重信息数据
        List<WeighingdataPojo>  weighingdataPojoList= userService.getWeightingDataList(weighingdataPojo);

        return JSON.toJSONString(weighingdataPojoList);

    }

    @PostMapping("/getWeightingDataCalculateList")
    // 查询称重信息数据
    public String getWeightingDataCalculateList(HttpServletRequest request, HttpServletResponse response,WeighingdataPojo weighingdataPojo) {

        // 查询称重信息数据
        List<WeighingdataPojo>  weighingdataPojoList= userService.getWeightingDataCalculateList(weighingdataPojo);

        return JSON.toJSONString(weighingdataPojoList);

    }

    @PostMapping("/getCalculateList")
    // 查询称重信息数据第一餐第二餐时间
    public String getCalculateList(HttpServletRequest request, HttpServletResponse response,WeighingdataPojo weighingdataPojo) {

        // 查询称重信息数据
        List<WeighingdataPojo>  weighingdataPojoList= userService.getCalculateList(weighingdataPojo);

        return JSON.toJSONString(weighingdataPojoList);

    }


    @PostMapping("/getWeightingDataListByHour")
    // 查询称重信息数据编辑修改查询
    public String getWeightingDataListByHour(HttpServletRequest request, HttpServletResponse response,WeighingdataPojo weighingdataPojo) {

        // 查询称重信息数据编辑修改查询
        List<WeighingdataPojo>  weighingdataPojoList= userService.getWeightingDataListByHour(weighingdataPojo);

        return JSON.toJSONString(weighingdataPojoList);

    }


    @PostMapping("/getWeightingDataListByYMWD")
    // 查询称重信息数据按日周月年(flag_type:1天2周3月4年)
    public String getWeightingDataListByYMWD(HttpServletRequest request, HttpServletResponse response,WeighingdataPojo weighingdataPojo) {

        // 查询称重信息数据
        List<WeighingdataPojo>  weighingdataPojoList= userService.getWeightingDataListByYMWD(weighingdataPojo);

        return JSON.toJSONString(weighingdataPojoList);

    }



}
