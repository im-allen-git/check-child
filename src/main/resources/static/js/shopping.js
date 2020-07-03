

$( function () {

})
function goBack(){
    js.changeActive( "5" );//1,我的模型 2 商城 3 模型库首页 4 创建模型 5 返回上一页
}
function getItems(){
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
            }
        })
    // itemHTML(data);
}
function itemHTML(data){
    var printer = data.printer;
    var module = data.module;
    if(printer){
        var printerHtml = '<div class="title row clearfix">3D打印机/配件</div>';
        printerHtml += '<div class="3dPrinter row clearfix">'
        for(var i in printer){
            printerHtml += '<div class="col-xs-12 each_good"><div class="good_wrapper clearfix">';
            printerHtml += '<div class="col-xs-4 img_wrapper"><img src="" alt=""></div>';
            printerHtml += '<div class="col-xs-8">';
            printerHtml += '<div class="good_title">带蓝牙、Wi-Fi、USB的3D打印 机，用于教育</div>';
            printerHtml += '<div class="good_price">￥1200.00</div>';
            printerHtml += '<div class="good_buy"></div>';
            printerHtml += '</div></div></div>';
        }
        $(".printer").html(printerHtml)
    }
    if(module){
        var moduleHtml = '<div class="title row clearfix">高级模型</div>';
        moduleHtml += '<div class="module row clearfix">'
        for(var i in module){
            moduleHtml += '<div class="col-xs-6 each_module">';
            moduleHtml += '<div class="each_module_wrapper">';
            moduleHtml += '<div class="img_wrapper"><img src="" alt=""></div>';
            moduleHtml += '<div class="good_title text-center">带翅膀的女孩</div>';
            moduleHtml += '<div class="good_price text-center">￥5.00</div>';
            moduleHtml += '<div class="good_buy"></div>';
            moduleHtml += '</div></div>';
        }
        $(".module_wrapper").html(moduleHtml)
    }
}