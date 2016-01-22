<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>管理平台</title>
<link href="${pageContext.request.contextPath}/operamasks-ui/css/default/om-default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/operamasks-ui/js/operamasks-ui.min.js?v=${version}"></script>
<style type="text/css">
/**
整体页面布局
*/
*{
	padding: 0;
	margin: 0;
}
a{
	outline: none;
}
html,body {		
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
	overflow: hidden;	
}
#layout {
	float:left;
	padding: 1px 0 1px 1px;
}
#header{
	float:left;
	border-right: 0;
	overflow: hidden;	
}

#header {
	background:#95bbea url(images/sys/headerBg.png) top repeat-x;
	height:130px;   
	position:relative;
	border: 1px solid #4586de;
	border-width: 0 1px;				
}
#topHeader{ width:100%; height: 30px; float: left;}
.top_nav{
	float:right;
	width: 153px;  
	height: 22px;
	padding: 4px 0px; 
	height:22px;
}

#top_line{
	width: 50%;	
	float: left;
	height: 40px;
	margin-left:80px;
	line-height: 40px;
	font-weight: bold;
	text-indent: 40px; 
	color: #fff;	
	font-size: 14px; 	
	padding-left: 13px;	
}
.icon_r_top {	
}
.top_nav{
	float:right;
	width: 183px;  
	height: 22px;
	padding: 4px 0px; 
	height:22px;
}
#header .top_nav li{	
	display: inline; 		
	padding: 0;
	float: right;		
}

#header .top_nav a{	
	width: 37px;	
	height: 35px;
	display: block;
	float:right;
	margin-right: -5px;	
	text-indent: -9999em;
}
div#rightShine{
	position:absolute;
	top: 0;	
	right: 0;
	height: 29px;
	width: 29px;
	background:url(images/sys/rightShine.png) no-repeat;	
}

#header .top_nav a.help{
	background: url(images/sys/help.png) no-repeat;
}

#header .top_nav a.help:hover{
	background: url(images/sys/help_h.png) no-repeat;
}

#header .top_nav a.settings{
	background: url(images/sys/set.png) no-repeat;
}

#header .top_nav a.settings:hover{
	background: url(images/sys/set_h.png) no-repeat;
}

#header .top_nav a.logout{
	background: url(images/sys/closeSystem.png) no-repeat;
}
#header .top_nav a.logout:hover{
	background: url(images/sys/closeSystem_h.png) no-repeat;
}
#header .top_nav a.annnoucE{
	background: url(images/sys/announce.png) no-repeat;
}
#header .top_nav a.annnoucE:hover{
	background: url(images/sys/announce_h.png) no-repeat;
}


p.announce em{
	font-style:normal;
	color: #ff6c00;	
	font-size: 11px;
}
#usrNameArea{
	margin-left:30px;		
	float:left;			
	width: 95px;								
	overflow: hidden;
	text-overflow:ellipsis;
	-o-text-overflow:ellipsis;	
	-icab-text-overflow: ellipsis;
	-khtml-text-overflow: ellipsis;	
	-moz-text-overflow: ellipsis;
	-webkit-text-overflow: ellipsis; 		
}
.announce {
	float: right;
	height:30px;
	line-height:30px;
	padding-left:10px;
	color:#4258bd;
	font-size: 12px;
	text-align:left;
	z-index:9999; 
	width: auto;
	
}

.announce b {
	font-weight:bold;
	padding-top:2px;
	color: #fff;
	font-weight:normal;
}
#dd1,#dd2{
	width: 5px;
}
.nav{
	float:left;
}

.nav {
	position:relative;
	width:100%;
	float:left;
	background: url(images/sys/top_bg.png) left  top repeat-y;
	height:28px;
	padding-top: 42px;
	z-index: 100;	
}
.nav ul, #footer p {	
	float:left;
	height:20px;
	padding-top:1px;
}
.nav ul {
	position:relative;
	float:left;
	height:28px;
	padding-left:10px;
}

.nav ul li {
	float:left;
	width:auto;
	height:28px;
	display:inline;
	margin-right:10px;
	
}
.nav ul li a {
	display:block;
	height:28px;
	color:#fff;
	font-size:12px;
	width:auto;
	line-height:28px;
	text-align:center;
	overflow:hidden;
	padding:0 4px 0 6px;
	font-size:14px;
	margin:0;
	font-weight: bold;
	color: #000;
	min-Width: 70px;
	text-decoration:none;
}
.nav ul li:hover, .nav ul li.hover {
	background:url(images/sys/navliBg.png) left top no-repeat;
}
.nav ul li a:hover, .nav ul li a.hover_nav {
	background:url(images/sys/navliABg.png) right top no-repeat;
	color:#222;
	font-size:14px;
}
.nav ul li ul li {
	padding:0;
	margin:0 2px;
	height:18px;
	width:auto;
	line-height:18px;
	width:auto;
	float:left;
	overflow:hidden;
}

.nav ul ul li a {
	height:18px;
	line-height:18px;
	float:left;
	width:auto;
	display:block;
	font-size:12px;
	padding:0 2px;
	margin:0;
	color:#333;
	padding-left: 12px;
	font-weight: normal;
	background:url(images/sys/navSubA.png) 5px center no-repeat;
}
.nav ul li ul li a:hover, .nav li ul li a.over {
	background:none;
	background:url(images/sys/navSubAHover.png) left center no-repeat;
	color: #b30405;
	font-size:12px;
	margin:0;
}
.nav ul ul li:hover, .nav ul ul li.hover {
	background:none
}
.nav ul ul {
	float:left;
	width:100%;
	height:20px;
	position:absolute;
	bottom:-23px;
	padding:4px 0 1px 0;
	left:0px;
	background:#fff url(images/sys/navSubUlBg.png) top repeat-x;
	overflow:hidden;
	display:none;
}
#footer {
	background: url(images/sys/footerBg.png) repeat-x;
	height:25px;
	overflow:hidden;	
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	//主界面	
	initLayout();
	initMenu();
});

function initMenu(){
	/* This code is executed after the DOM has been completely loaded */
	
	/* Changing thedefault easing effect - will affect the slideUp/slideDown methods: */
	$.easing.def = "easeOutBounce";

	/* Binding a click event handler to the links: */
	$('li.menu_button a').click(function(e){
		/* Finding the drop down list that corresponds to the current section: */
		var dropDown = $(this).parent().next();
		/* Closing all other drop down sections, except the current one */
		$('.dropdown').not(dropDown).slideUp('fast');
		dropDown.slideToggle('fast');
		/* Preventing the default event (which would be to navigate the browser to the link's address) */
		e.preventDefault();
	});
	$("#on_check").parent().parent().parent().parent().find(".blue").trigger("click");
	//resizeWin();
    //$(window).resize(function() {
    //	resizeWin();
	 //});
    loadMenu();
    initTimer();
}

function loadMenu(){
	var defurl = '${sessionScope.curCheckMenu}';
	if (defurl) {
		defurl='${pageContext.request.contextPath}'+defurl;
		munuclick(defurl);
	}
	
	$("#numue_list a").click(function(){
		$(this).parent().parent().find("a").css("color","#1068c2");
		//$("#numue_list a").css("color","1068c2");
		$(this).css("color","orange");
		munuclick($(this).attr("url"));
	});
}



function initTimer(){
	window.setTimeout(closeMenu,15000);
}

function munuclick(url) {
	if(url){
		if(url.indexOf('?')!=-1){
			url=url+'&t='+(new Date()).getTime();
		}else{
			url=url+'?t='+(new Date()).getTime();
		}
		//showLoadingDiv();
		$("#main_content").attr("src", url);
	}
}

function closeMenu(){
	$('#layout').omBorderLayout('collapseRegion', 'west');
}


//初始化面板
function initLayout(){
	$('#layout').omBorderLayout( { fit : true,	
		fit : true,	
		panels : [ {
			id : "content",
			header : false,
			url:"",	
			region : "center"	
		},{
			id : "header",
			title : "north",
			region : "north",
			height : 120,		
			collapsible : false,			
			resizable : false,						
			header : false

		}, {
			id : "footer",
			title : "south",	
			region : "south",
			height : 27,
			collapsible : false,
			resizable : false,
			header : false			
			
		} ],
		hideCollapsBtn : false,
		spacing : 7
	});		
}

function setCwinHeight() {
	var iframeid = document.getElementById("main_content"); //iframe id 
	iframeid.height = "10px";//先给一个够小的初值,然后再长高. 
	if(document.getElementById){
		if(iframeid && !window.opera){
			if(iframeid.contentDocument && iframeid.contentDocument.body.offsetHeight){
				iframeid.height = iframeid.contentDocument.body.offsetHeight;
			}else if(iframeid.Document&&iframeid.Document.body.scrollHeight){
				iframeid.height = iframeid.Document.body.scrollHeight;
			}
			//hideLoadingDiv();
		}
	} 
}


function manageLogout(){
	window.location.href="manage!manageLogout.do";
}

function setSrc(url){
	if(url.indexOf("?")!=-1){
		url=url+"&runtime="+ new Date().getTime();
	}else{
		url=url+"?runtime="+ new Date().getTime();
	}
	$("#main_content").attr("src",'${pageContext.request.contextPath}/'+url);
}

//给二级菜单加上鼠标点击事件，并控制页面跳转
//给一级菜单加上鼠标点击事件，触发二级菜单的第一个节点的点击事件
//文档加载是触发第一个一级菜单的点击事件
$(document).ready(function(){
	var li_arr=$(".first_class");//获取所有的li块
	//循环
	li_arr.each(function(){
		var child_a_arr=$(this).find("li>a");//查找li下 在li下的a标签
		//设置每一个超链接的参数，控制跳转
		child_a_arr.click(function(){//给每一个添加点击事件
			var url;//定义临时变量
			//判断是否设置行数，如不用设置行数，则路径参数中不添加
			if(this.attributes['rows']){
				//获取url，并加上行数参数。eval(),根据字符串获取js变量的值。
				if(this.attributes['url'].nodeValue.indexOf("?")!=-1){
					url=this.attributes['url'].nodeValue +"&pageSize="+eval(this.attributes['rows'].nodeValue);
				}else{
					url=this.attributes['url'].nodeValue +"?pageSize="+eval(this.attributes['rows'].nodeValue);
				}
			}else{
				url=this.attributes['url'].nodeValue;
			}
			setSrc(url);//调用父窗体的方法设置路径
		});
		
		//设置大模块的点击事件
		$(this).find("a").first().click(function(){
			//触发子元素的第一个节点
			child_a_arr.first().trigger("click");
		});
		//去掉点击后的虚线框
		$(this).find("a").first().focus(function(){
			this.blur();
		});
	});
	//文档加载时触发第一个模块的点击事件
	li_arr.first().find("a").first().trigger("click");
	/*******
	 *  Nav导航
	 *  
	 *  ************/
	$(".nav ul>li>a").click(function(){
		$(this).addClass('hover_nav').parent('li').siblings('li').children('a').removeClass('hover_nav');
		$(this).parent('li').addClass('hover').siblings('li').removeClass('hover');
		$('.first_class').children('ul').children('li').children('a').removeClass('over');
		$(this).parent('li').children('ul').children('li:first').children('a').addClass('over');
	});
	$(".nav ul > li>ul>li > a").click(function(){
		$(this).addClass('over').parent('li').siblings('li').children('a').removeClass('over');
		$(this).removeClass('hover_nav');
	});
	$('.nav ul>li').click(function(){
		$(this).children('ul').show().parent('li').siblings('li').children('ul').hide();
	});
		
	$('.nav ul>li>ul>li').click(function(){
		$(this).addClass('over').siblings('li').removeClass('over');
	});
});
</script>
</head>
<body>
	<div id="layout">
		<div id="header">
  			<div id="topHeader">
  				<!-- 系统以及工具栏等信息 -->
   				<div id="top_line">
   					管理平台
   				</div>
				<span style="float: right">
					<div style="clear: both;"></div>
					<div class="top_nav">
						<span><a href="javascript:void(0)" class="display"></a></span>
	  					<ul class="icon_r_top">
	   						<li><a href="javascript:void(0);" onclick="manageLogout();" target="_parent" class="logout" title="退出系统"></a></li>
	    					<li><a href="javascript:void(0);" onclick="showSysHelp()" class="help" title="系统帮助"></a></li>
	   						<li><a href="javascript:void(0);" onclick="showPersonalConfig()" class="settings" title="个人设置"></a></li>
	   						<li><a href="javascript:void(0);" onclick="showReceiveMsg()" class="annnoucE" title="系统公告"></a></li>
	   					</ul>
	   				</div>
   					<span style="padding-left:10px;float:left;">
   						<em style=" float:right;color: #f94802">
   						<img src="images/sys/inforCoolector.gif" alt= "" style="float: left;margin: 6px 3px 0 3px;" />
   							<span style="line-height: 30px;">${manageUser.userName},你好！</span>
   						</em>
   					</span>
				</span>
				<!-- 地方特征信息 -->
				<div>
					<div id="addFeatures"></div>
				</div>
  			</div>     
		    <div class="nav">    
		      <ul style="width: 100%;">
		      	<c:forEach items="${myPurviewMap}" var="entry">
		        <li class="first_class">
		        	<a href="javascript:void(0);" target="main_content">${entry.key.name}</a>
		        	<ul style="width: 100%;">
		        		<c:forEach items="${entry.value}" var="value">
		        			<li><a href="javascript:void(0);" url="${value.url}">${value.name}</a></li>
		        		</c:forEach>
		       		</ul>
		        </li>
		        </c:forEach>
		      </ul>
    		</div>
    	</div>
		<div id="content">
			<div class="content_div">
				<iframe src="index.do" name="main_content" id="main_content" class="iFrame" marginheight="0" width="100%"  scrolling="no" marginwidth="0"  frameborder="0"  onload="javascript:setCwinHeight();"></iframe>
			</div>
		</div>
		<div id="footer">
<%-- 			<p>Copyright © 2014-2016 <%=ConfigManager.getConfigData("COMPANY") %>广告 All Rights Reserved</p> --%>
		</div>
	</div>
</body>
</html>