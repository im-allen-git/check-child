var buyItemId;
var saveAddressFlag=false;
$( function () {
    buyItemId = getUrlParam("id");
    getAddress();
});

function clearAddress(){
    $("#receiveName").val("");
    $("#receivePhone").val("");
    $("#receiveProvince").val("");
    $("#receiveCity").val("");
    $("#receiveOther").val("");
    $(".clear_info").removeClass("color_red");
}

function getAddress(){
    $.ajax({
            type: "GET",
            url:"",
            data:{},
            dataType: "JSON",
            beforeSend: function () {

            },
            success:function(res){
                if(res.code == 200){

                }
            },
            error:function(res){
                console.log(res);
                for(var i=0; i<4;i++){

                }
            }
        })
}
function ifInput(obj){
    var receiveName = $("#receiveName").val();
    var receivePhone = $("#receivePhone").val();
    var receiveProvince = $("#receiveProvince").val();
    var receiveCity = $("#receiveCity").val();
    var receiveOther = $("#receiveOther").val();
    if(receiveName || receivePhone  || receiveProvince || receiveCity || receiveOther){
        $(".clear_info").addClass("color_red");
    }
    else{
        $(".clear_info").addClass("color_red");
    }
}
function saveAddress(){
    if(saveAddressFlag){
        saveAddressFlag = false;
        $(".save_address .iconfont").removeClass("selectedSave");
    }
    else{
        saveAddressFlag = true;
        $(".save_address .iconfont").removeClass("selectedSave").addClass("selectedSave");
    }
}
function payMethodChange(type, obj) {
    $(".active_pay").removeClass("active_pay");
    if(type == 0 ){
        $(".wechat_wrapper").addClass("active_pay")
    }
    else{
        $(".alipay_wrapper").addClass("active_pay")
    }
}
function goBack(){
    js.changeActive( "111" );//1,我的模型 2 商城 3 模型库首页 4 创建模型 5 返回上一页
}
function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r!=null) return unescape(r[2]); return null; //返回参数值
} 