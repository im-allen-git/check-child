package com.kairong.service.impl;

import com.kairong.service.AliPayServer;
import com.kairong.util.ali.AliPayConfig;
import com.kairong.util.ali.OrderInfoUtil2_0;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.service.impl
 * @date:2020-08-12
 */
@Service
public class AliPayServerImpl implements AliPayServer {
    @Override
    public String getOrderInfo(String stlId, Double stlAmount) {

        boolean rsa2 = AliPayConfig.RSA2_PRIVATE.length() > 0;
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(AliPayConfig.APPID, rsa2, stlId, stlAmount);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? AliPayConfig.RSA2_PRIVATE : AliPayConfig.RSA_PRIVATE;
        // String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        String sign = "sign";
        return orderParam + "&" + sign;
    }

    @Override
    public String getAuthInfo() {
        boolean rsa2 = (AliPayConfig.RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(AliPayConfig.PID, AliPayConfig.APPID, AliPayConfig.TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? AliPayConfig.RSA2_PRIVATE : AliPayConfig.RSA_PRIVATE;
        // String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        String sign = "sign";
        return info + "&" + sign;
    }
}
