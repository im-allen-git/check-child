

$( function () {
    getItems()
})
function goBack(){
    js.changeActive( "5" );//1,我的模型 2 商城 3 模型库首页 4 创建模型 5 返回上一页
}
function getItems(){
    $.ajax({
            type: "GET",
            url:"./assets/shopping.json",
            data:{},
            dataType: "JSON",
            beforeSend: function () {

            },
            success:function(res){
                if(true){
                    itemHTML(res.data)
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
        printerHtml += '<div class="3dPrinter row clearfix">';
        for(var i in printer){
            printerHtml += '<div class="col-xs-12 col-sm-6 each_good"><div class="good_wrapper clearfix">';
            printerHtml += '<div class="col-xs-4 img_wrapper"><img src="" alt=""></div>';
            printerHtml += '<div class="col-xs-8">';
            printerHtml += '<div class="good_title">'+printer[i].title+'</div>';
            printerHtml += '<div class="good_price">￥'+printer[i].price+'</div>';
            printerHtml += '<div class="good_buy" onclick="buyThis(\''+printer[i].id+'\',\''+printer[i].price+'\')"></div>';
            printerHtml += '</div></div></div>';
        }
        printerHtml += '</div>';
        $(".printer").html(printerHtml)
    }
    else{
        $(".printer").hide();
    }
    if(module){
        var moduleHtml = '<div class="title row clearfix">高级模型</div>';
        moduleHtml += '<div class="module row clearfix">';
        for(var i in module){
            moduleHtml += '<div class="col-xs-6 col-sm-3 each_module">';
            moduleHtml += '<div class="each_module_wrapper">';

            moduleHtml += '<div class="img_wrapper"><img src="" alt=""></div>';
            moduleHtml += '<div class="good_title text-center">'+module[i].title+'</div>';
            moduleHtml += '<div class="good_price text-center">￥'+module[i].price+'</div>';
            moduleHtml += '<div class="good_buy" onclick="buyThis(\''+printer[i].id+'\',\''+printer[i].price+'\')"></div>';
            moduleHtml += '</div></div>';
        }
        moduleHtml += '</div>';
        $(".module_wrapper").html(moduleHtml)
    } else{
        $(".module_wrapper").hide();
    }
}

function buyThis(id,price){
    window.location.href = './addressPay.html?id='+id + '&price='+price;
}