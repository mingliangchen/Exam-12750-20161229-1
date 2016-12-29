var seckill = {	
    URL: {
        now: function () {
            return '/seckill/seckill/time/now';
        },
        exposer: function(seckillId){
            return '/seckill/seckill/' + seckillId + '/exposer';
        },
        execution: function(seckillId, md5){
            return '/seckill/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },

    handleSeckill: function(seckillId, node){
        node.hide().html('<button class="btn bg-primary btn-lg" id="killBtn">start seckill</button>');
        $.post(seckill.URL.exposer(seckillId),{}, function(result){
            console.log(result.success);
            console.log(result.data);
            if(result && result['success']){
                var exposer = result['data'];
                if(exposer['exposed']){
                    //开启秒杀
                    //获取秒杀地址
                	var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    //打印
                    console.log("killUrl:"+killUrl);
                    //只绑定一次点击事件
                    $('#killBtn').one('click', function(){
                    	//禁用按钮
                        $(this).addClass('disabled');
                        //发送请求执行秒杀
                        $.post(killUrl, {}, function(result){
                            if(result && result['success']){
                                var killResult = result['data'];
                                var status = killResult['status'];
                                var stateInfo = killResult['statusInfo'];

                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    //显示按钮
                    node.show();
                }else{
                    //do not start seckill
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    //重新走一遍计时逻辑
                    seckill.countdown(seckillId, now, start, end);
                }
            }else{
                console.log('result : ' + result);
            }
        });
    },

    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    countdown : function(seckillId, nowTime, startTime, endTime){
        var seckillBox = $('#seckill-box');
        if(nowTime > endTime){
            seckillBox.html('seckill is over');
        }else if(nowTime < startTime){
            var killTime = new Date(Number(startTime) + 1000);
            seckillBox.countdown(killTime, function(event){
                var format = event.strftime('Distance Seckill Time： %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function(){
            	//获取秒杀地址，
                seckill.handleSeckill(seckillId, seckillBox);
            });
        }else{
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页的初始化
        init: function (params) {
            var killPhone = $.cookie('killPhone');
            //console.info(killPhone);
            //模拟登录
            if (!seckill.validatePhone(killPhone)) {
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true, //显示弹出层
                    backdrop: 'static', //禁止位置关闭
                    keyboard: false  //关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killphoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                    	//电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                    	
                        $('#killphoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });
            }

            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            //计时交互
           $.get(seckill.URL.now(), {}, function (result) {
                if(result && result['success']){
                    var nowTime = result['data'];
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                }else{
                    console.log('result : ' + result);
                }
            });
        }
    }
};
