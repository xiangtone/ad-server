$(document).ready(function(){
	$(".news_three li").click(function(){
		$(this).addClass("on").siblings().removeClass();
		var at = $(this).attr("at");
		$(".newul_list").hide();
		$("#newul_list"+at).show();
	});
	$(".login_two li").click(function(){
		$(this).addClass("on").siblings().removeClass();
		var at = $(this).attr("at");
		$(".login_box").hide();
		$("#login_box"+at).show();
	});
	$("#check_user input:radio").click(function(){
		var va = $(this).val();
		if(va==2){
			$(".company").show();
		}else{
			$(".company").hide();
		}
		
	});
	$(".head_nav li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
	});
	
	function request(paras){
		var url = location.href; 
		var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
		var paraObj = {} 
		for (i=0; j=paraString[i]; i++){ 
		paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
		} 
		var returnValue = paraObj[paras.toLowerCase()]; 
		if(typeof(returnValue)=="undefined"){ 
		return ""; 
		}else{ 
		return returnValue; 
		} 
	}
	
	var logor = request("target");
	if(logor=="reg"){
		$(".login_two li:last-child").addClass("on").siblings().removeClass();
		$("#login_box1").hide();
		$("#login_box2").show();
	}
	//控制菜单选中状态
	if(logor=="index"){
		$(".head_nav li").eq(0).addClass("on").siblings().removeClass();
	}else if(logor=="adv"){
		$(".head_nav li").eq(1).addClass("on").siblings().removeClass();
	}else if(logor=="dev"){
		$(".head_nav li").eq(2).addClass("on").siblings().removeClass();
	}else if(logor=="about"){
		$(".head_nav li").eq(3).addClass("on").siblings().removeClass();
	}
	
	//首页轮播图片
	var num_i = 0,st;
	
	$(".ul_img").append($(".ul_img").html());
	$(".ul_img").width($(".adver_img").width()*$(".ul_img li").length);
	//翻到第几张图片
	function scrolPage(m){
		var d_width = $(".adver_img").width();
		num_i = m;
		$(".ul_img").animate({"left":-m*d_width},function(){
			if(m==3){num_i = 0;$(".ul_img").css({"left":0});$(".ul_num li").eq(num_i).addClass("on").siblings().removeClass("on");}
			
		});
		$(".ul_num li").eq(num_i).addClass("on").siblings().removeClass("on");
	}
	//alert(i);
	//鼠标事件
	$(".ul_num li").mouseover(function(){
		clearInterval(st);
		var num = $(this).attr("alt");
		$(".ul_img").stop();
		scrolPage(num);
		$(this).addClass("on").siblings().removeClass("on");
	});
	$(".ul_num li").mouseout(function(){
		setTscrol();
	});
	//自动滚动
	function setTscrol(){
		st = setInterval(function(){scrolPage(1+parseInt(num_i))},5000);
	}
	setTscrol();
	//首页轮播图片end
});