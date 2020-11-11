package com.kairong.controller;

import com.kairong.service.AliPayServer;
import com.kairong.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: 获取支付宝配置相关信息
 * @date:2020-08-11
 */
@RestController
@RequestMapping("/wxPay")
@Slf4j
public class WXPayController {


    private String RSA2_PRIVATE = "996";
    private String RSA_PRIVATE = "";


    @Autowired
    private AliPayServer aliPayServer;

    @PostMapping("/getOrderInfo")
    public CommonResult getOrderInfo(String stlId, Double stlAmount, String recipient, String phone, String address) {

        Assert.notNull(stlId, "stlId null");
        Assert.notNull(stlAmount, "stlAmount null");
        Assert.notNull(recipient, "recipient null");
        Assert.notNull(phone, "phone null");
        Assert.notNull(address, "address null");

        System.err.println("stlId:" + stlId);
        System.err.println("stlAmount:" + stlAmount);
        System.err.println("recipient:" + recipient);
        System.err.println("phone:" + phone);
        System.err.println("address:" + address);
        try {
            return CommonResult.success(1);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getOrderInfo error:", e);
            return CommonResult.failed(e.getMessage());
        }

    }


    @RequestMapping("/payError")
    public void payError(HttpServletRequest request) {

        // 获取传递回来的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null && parameterMap.size() > 0) {
            log.error("---------begin---------");
            parameterMap.forEach((k, v) -> log.error("errorParam,key:[{" + k + "}],value:[{" + Arrays.toString(v) + "}]"));
            log.error("---------end---------");
        }

    }

}
