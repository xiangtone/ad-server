<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<title>赚钱列表</title>
	<link rel="stylesheet" type="text/css"	href="../ioswall/iosWallDetail.css?v=20140618" />
	<link rel="stylesheet" type="text/css"	href="../ioswall/loading.css?v=201406128" />
	<script src="../ioswall/global.js?v=2014061338"></script>
	<script src="../ioswall/ioswall_detail.js?v=11zaaw8221" charset="utf-8"></script>
	<script type="text/javascript">
		function adClick(){
			window.open(url, windowName, windowFeatures, optionalArg4)
			window.location.href='';
		}
	</script>
	<style>
		*{margin:0;padding:0;}
		li{list-style:none;}
		.m-slider{width:300px;margin:20px 10px;overflow:hidden;}
		.m-slider .cnt{position:relative;left:0;width:3000px;}
		.m-slider .cnt li{float:left;width:256px;}
		.m-slider .cnt img{display:block;width:100%;height:246px;}
		.m-slider .cnt p{margin:20px 0;}
		.m-slider .icons{text-align:center;color:#000;}
		.m-slider .icons span{margin:0 5px;}
		.m-slider .icons .curr{color:red;}
		.f-anim{-webkit-transition:left .2s linear;}
	</style>
</head>
<body>
	<!-- 头 部分-->
	<div class="head_div" style="display: none;">
		<div class="headertitle">${vo.ad_name}</div>
		<p class="head_back" id="head_back" onclick="homeback(this)">&lt;返回</p>
	</div>
	<!-- 奖励任务部分 -->
	
	<div class="mbody">
		<div class="wall_list" id="wall_list">
			<div class="divider" id="divider0">
			</div>
			<div class="box" id="box0">
				<div class="boxLM" id="boxLM0">
					<div class="boxL" id="boxL">
						<img class="logo" src="${vo.icon_url}">
					</div>
					<div class="boxM" id="boxM">
						<div id="title0" class="title">${vo.ad_name}</div>
						<div id="desc0" class="desc">下载注册，试玩两分钟获取积分</div>
					</div>
				</div>
				<div class="boxR" id="boxR0">
					<div class="downbgroung" id="downbgroung0">0</div>
				</div>
			</div>
			<div class="divider_up"></div>
			<div class="divider_bottom" id="divider1"></div>
			<div style="width: 100%;height: 100px;" >
				${vo.score_long_desc}
			</div>
			<div class="divider_up"></div>
			<div style="width: 100%;height: 380px;" >
				<div class="m-slider">
				    <ul class="cnt" id="slider">
				        <li>
				            <img src="${pageContext.request.contextPath}/ioswall/1_854_854.jpg" style="height: 370px;width: 246px;">
				        </li>
				        <li>
				            <img src="${pageContext.request.contextPath}/ioswall/2_854_854.jpg" style="height: 370px;width: 246px;">
				        </li>
				        <li>
				            <img src="${pageContext.request.contextPath}/ioswall/3_854_854.jpg" style="height: 370px;width: 246px;">
				        </li>
				    </ul>
				    <div class="icons" id="icons">
				        <span class="curr">1</span>
				        <span>2</span>
				        <span>3</span>
				    </div>
				</div>
				    
				<!--  
				<div style="height: 400px;display: block;overflow:hidden;overflow: scroll;" id="slider">
					<span style="width: 900px;display: block;display: block;">
						<img alt="" src="../ioswall/1_854_854.jpg" style="height: 370px;width: 246px;margin: 0;padding: 0;float: left;">
						<img alt="" src="../ioswall/2_854_854.jpg" style="height: 370px;width: 246px;margin: 0;padding: 0;float: left;margin-left: 30px;">
						<img alt="" src="../ioswall/3_854_854.jpg" style="height: 370px;width: 246px;margin: 0;padding: 0;float: left;margin-left: 30px;">
					</span>
					
				</div>
				-->
			</div>
			<div class="divider_up"></div>
			<div style="height: 80px;width: 100%;padding-top: 10px;">
				<span style="width: 200px;height: 50px;display: block;margin-left: auto;margin-right: auto;">
					<a href="${vo.click_url}" target="_blank">
						<img alt="" src="../ioswall/download.png" height="80%" width="80%" style="cursor: pointer;" />
					</a>
				</span>
				<span style="width: 250px;height: 50px;display: block;margin-left: auto;margin-right: auto;font-size: 12px;">
					*试玩过程中请保存【应用先锋】在后台运行中
				</span>
			</div>
			<div style="width: 100%;background-color: #EF8133;height: 190px;">
				<span style="width: 290px;height: 80px;display: block;margin-left: auto;margin-right: auto;color: white;padding-top: 20px;">
					试玩提示：<br>
					<span style="font-size: 10px;line-height: 20px;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="1px;">☆</font>请按照任务要求完成任务，一般需要打开运行一段时间或注册账户才能得到任务完成的确认。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;☆确保该应用从未在设备上下载安装过，卸载重新安装的不能获得奖励。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;☆返回此界面刷新，任务消失即可得到奖金。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;☆一个微信号只能在一台设备上使用，如果用在多台设备会导致玩任务但没有奖励。<br>
					</span>
				</span>
			</div>
		</div>
	</div>
	<!--  
	<div class="footer">
	</div>
	-->
	
	<script>
var slider = {
    //判断设备是否支持touch事件
    touch:('ontouchstart' in window) || window.DocumentTouch && document instanceof DocumentTouch,
    slider:document.getElementById('slider'),

    //事件
    events:{
        index:0,     //显示元素的索引
        slider:this.slider,     //this为slider对象
        icons:document.getElementById('icons'),
        icon:this.icons.getElementsByTagName('span'),
        handleEvent:function(event){
            var self = this;     //this指events对象
            if(event.type == 'touchstart'){
                self.start(event);
            }else if(event.type == 'touchmove'){
                self.move(event);
            }else if(event.type == 'touchend'){
                self.end(event);
            }
        },
        //滑动开始
        start:function(event){
            var touch = event.targetTouches[0];     //touches数组对象获得屏幕上所有的touch，取第一个touch
            startPos = {x:touch.pageX,y:touch.pageY,time:+new Date};    //取第一个touch的坐标值
            isScrolling = 0;   //这个参数判断是垂直滚动还是水平滚动
            this.slider.addEventListener('touchmove',this,false);
            this.slider.addEventListener('touchend',this,false);
        },
        //移动
        move:function(event){
            //当屏幕有多个touch或者页面被缩放过，就不执行move操作
            if(event.targetTouches.length > 1 || event.scale && event.scale !== 1) return;
            var touch = event.targetTouches[0];
            endPos = {x:touch.pageX - startPos.x,y:touch.pageY - startPos.y};
            isScrolling = Math.abs(endPos.x) < Math.abs(endPos.y) ? 1:0;    //isScrolling为1时，表示纵向滑动，0为横向滑动
            if(isScrolling === 0){
                event.preventDefault();      //阻止触摸事件的默认行为，即阻止滚屏
                this.slider.className = 'cnt';
                this.slider.style.left = -this.index*256 + endPos.x + 'px';
            }
        },
        //滑动释放
        end:function(event){
            var duration = +new Date - startPos.time;    //滑动的持续时间
            if(isScrolling === 0){    //当为水平滚动时
                this.icon[this.index].className = '';
                if(Number(duration) > 10){     
                    //判断是左移还是右移，当偏移量大于10时执行
                    if(endPos.x > 10){
                        if(this.index !== 0) this.index -= 1;
                    }else if(endPos.x < -10){
                        if(this.index !== this.icon.length-1) this.index += 1;
                    }
                }
                this.icon[this.index].className = 'curr';
                this.slider.className = 'cnt f-anim';
                this.slider.style.left = -this.index*256 + 'px';
            }
            //解绑事件
            this.slider.removeEventListener('touchmove',this,false);
            this.slider.removeEventListener('touchend',this,false);
        }
    },
    
    //初始化
    init:function(){
        var self = this;     //this指slider对象
        if(!!self.touch) self.slider.addEventListener('touchstart',self.events,false);    //addEventListener第二个参数可以传一个对象，会调用该对象的handleEvent属性
    }
};

slider.init();
</script>
</body>
</html>