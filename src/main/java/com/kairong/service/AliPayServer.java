package com.kairong.service;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.service
 * @date:2020-08-12
 */
public interface AliPayServer {

    String getOrderInfo(String stlId, Double stlAmount);

    String getAuthInfo();
}
