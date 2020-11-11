package com.kairong.util.ali;

public class AliPayConfig {

	public static final String RSA2_PRIVATE = "";
	public static final String RSA_PRIVATE = "";

	public static final String PID = "";
	public static final String TARGET_ID = "";
	// 商户appid
	public static final String APPID = "";
	// app端支付宝支付异步通知结果；服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static final String notify_url = "";
	// 支付完成跳转页面
	public static final String return_url = "";
	// 请求网关地址
	public static final String URL = "https://openapi.alipaydev.com/gateway.do";
	// 编码格式
	public static final String CHARSET = "UTF-8";
	// 返回格式
	public static final String FORMAT = "json";
	// 支付宝公钥
	public static final String ALIPAY_PUBLIC_KEY = "";
	// 应用私钥
	public static final String APP_PRIVATE_KEY = "";
	// 加密类型RSA2
	public static final String SIGNTYPE = "RSA2";
}