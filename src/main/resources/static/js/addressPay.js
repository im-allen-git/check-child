var buyItemId;
var buyItemPrice;
var saveAddressFlag = 0;// 0不保存 1保存
var currentPayMethod = 2; //2:wechat 1:alipay
$(function () {
    buyItemId = getUrlParam("id");
    buyItemPrice = getUrlParam("price");
    $("#total_price").text("￥" + buyItemPrice);
    getAddress();
});

function clearAddress() {
    $("#receiveName").val("");
    $("#receivePhone").val("");
    $("#receiveProvince").val("");
    $("#receiveCity").val("");
    $("#receiveOther").val("");
    $(".clear_info").removeClass("color_red");
    $(".note").hide();
}

function getAddress() {
    var info = js.getAddress();
    if (info) {
        var json = eval("(" + info + ")");
        $("#receiveName").val(json["recipient"]);
        $("#receivePhone").val(json["phone"]);
        var address = json["address"];
        if (address && address.indexOf("@") > -1) {
            var list = address.split("@");
            $("#receiveProvince").val(list[0]);
            $("#receiveCity").val(list[1]);
            $("#receiveOther").val(list[2]);
        }
    }

}

function ifInput(obj) {
    var receiveName = $("#receiveName").val();
    var receivePhone = $("#receivePhone").val();
    var receiveProvince = $("#receiveProvince").val();
    var receiveCity = $("#receiveCity").val();
    var receiveOther = $("#receiveOther").val();
    if (receiveName || receivePhone || receiveProvince || receiveCity || receiveOther) {
        $(".clear_info").addClass("color_red");
    } else {
        $(".clear_info").addClass("color_red");
    }
    if (receivePhone && receivePhone.length > 11) {
        $("#receivePhone").val(receivePhone.substring(0, receivePhone.length - 1));
    }
    $(".note").hide();
}

function saveAddress() { //saveAddressFlag=0;// 0不保存 1保存

    if (saveAddressFlag > 0) {
        saveAddressFlag = 0;
        $(".save_address .iconfont").removeClass("selectedSave");
    } else {
        saveAddressFlag = 1;
        $(".save_address .iconfont").removeClass("selectedSave").addClass("selectedSave");
    }
}

function payMethodChange(type, obj) {
    $(".active_pay").removeClass("active_pay");
    if (type == 0) {
        $(".wechat_wrapper").addClass("active_pay");
        currentPayMethod = 2; // 1 alipay 2 wechatpay
    } else {
        $(".alipay_wrapper").addClass("active_pay");
        currentPayMethod = 1; //1 alipay 2 wechatpay
    }
}

function payNow() {
    var receiveName = $("#receiveName").val();
    var receivePhone = $("#receivePhone").val();
    var receiveProvince = $("#receiveProvince").val();
    var receiveCity = $("#receiveCity").val();
    var receiveOther = $("#receiveOther").val();
    // if(receivePhone&&!(/^1[34578]\d{9}$/.test(receivePhone))){
    if (!receiveName) {
        $(".note").text("*请输入姓名").show();
        return
    }
    if (!receivePhone) {
        $(".note").text("*请输入手机号码").show();
        return
    }
    if (receivePhone && !(/^1(3|4|5|7|8)\d{9}$/.test(receivePhone))) {
        $(".note").text("*手机号码错误，请检查").show();
        return
    }
    if (!receiveProvince) {
        $(".note").text("*请输入省份").show();
        return
    }
    if (!receiveCity) {
        $(".note").text("*请输入城市").show();
        return
    }
    if (!receiveOther) {
        $(".note").text("*请输入详细地址").show();
        return
    }
    var payAddress = '';
    if (receiveName && receivePhone && receiveProvince && receiveCity && receiveOther) {
        $("#loading_data").show();
        payAddress = receiveProvince + "@" + receiveCity + "@" + receiveOther;

        //String stlId, String stlAmount, String recipient, String phone,String address, String type, String saveFlag :saveFlag 0不保存 1保存
        var payStatus = js.readToPay(buyItemId, buyItemPrice, receiveName, receivePhone, payAddress, currentPayMethod, saveAddressFlag);
        if (payStatus) {
            $("#loading_data").hide();
        } else {
            $("#loading_data").hide();
            alert("支付失败，请重试")
        }
    }
}

function goBack() {
    js.changeActive("111");//1,我的模型 2 商城 3 模型库首页 4 创建模型 5 返回上一页
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
} 