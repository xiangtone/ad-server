<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/user-profile.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/form-wizard.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fuelux.wizard.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/dev/jquery-hcheckbox.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/dev/jquery.mousewheel.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/dev/jquery.jscrollpane.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/poshytip/jquery.poshytip.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/upload/js/swfobject.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/upload/js/jquery.uploadify.v2.0.3.js"></script>
		<script type="text/javascript">
			//是否已上传应用标识
			var upload_flag=false;
			$(document).ready(function() {
				$("#uploadify").uploadify({
					'uploader'       : '${pageContext.request.contextPath}/upload/swf/uploadify.swf',
					'cancelImg'      : '${pageContext.request.contextPath}/upload/images/cancel.png',
					'queueID'        : 'fileQueue',
					'wmode'          : 'transparent',
					'buttonText'     : '浏览...',
					'buttonImg'      : '${pageContext.request.contextPath}/upload/images/browse.jpg',
					'method'         : 'GET',//如果要传参数，就必须改为GET  
					'auto'           : false,
					'multi'          : false,
					'script'         : '${pageContext.request.contextPath}/upload/uf',
					'scriptData'     : {"po":"0","pa":"/${dev.email}/${vo.id}/","ic":"1"},
					'queueSizeLimit' : 1,//允许最大上传队列长度
					'simUploadLimit' : 1,//同时处理上传任务数
					'fileExt'        : '*.apk;*.ipa;*.pkg',//允许访问的文件格式
					'fileDesc'       : '支持格式：*.apk,*.ipa,*.pkg',
					onComplete       : function (event, queueID, fileObj, response, data){
						//var json = JSON.parse(response);
						var json = eval('('+response+')');
						if(typeof(json.error) != 'undefined'){
							alert(json.error);
						}else{
							try{
								appUpdate(json);
							}catch(e){
								alert(e.message);
							};
						};
					},
					onError          : function(event, queueID, fileObj){
						alert("文件: " + fileObj.name + " 上传失败！");
					},
					onCancel         : function(event, queueID, fileObj){
						alert("取消文件： " + fileObj.name + " 上传！");
					}
			    });
			});
			//开发者修改应用
			function appUpdate(data){
				var errMsg;
				if(typeof(data.fall) != 'undefined') {
					$("#uploadMsg").html("<font color='red'>"+data.fall[0].errorMsg+"</font>");
				} else {
					var path = data.succeed[0].path;
					var newFileName = data.succeed[0].newFileName;
					var savePath = path+newFileName;
					var temp = path.substring(0,path.length-1);
					var appid =  temp.substring(temp.lastIndexOf("/")+1);
					savePath = encodeURIComponent(savePath);//编码
					//解析返回数据
					var url="updateAppDate.action?id="+appid+"&path="+savePath;
					url = encodeURI(url);//url编码
					//ajax修改后台应用信息
					if(typeof(data.succeed) != 'undefined'){
						$.ajax({
							type:"get",   
							url:url,   
							dataType : "json", 
							success:function(msg){
								if(msg.status!="err"){
									upload_flag=true;
									//显示下载链接
									$("#uploadMsg").html("<a href='"+msg.urlPrefix+msg.path+"'>"+newFileName+"</a>");
								}else{
									errMsg = msg.errMag;
									$("#uploadMsg").html("<font color='red'>"+errMsg+"</font>");
								}
							}   
						});
					}
				}
			}
			function submitForm(){
				if(!upload_flag){
					alert("请填上传应用！");
					return;
				}
				var b=true;
				if(b){
					$("#addDevApp_form").submit();
				}
			}
		</script>
	</head>
	<body>
	    <jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<div class="content">
			<div id="pad-wrapper" class="user-profile">
				<div class="row header">
                	<div class="col-md-12"><h3>添加应用</h3></div>
				</div>
				<div class="row form-wrapper">
					<div class="col-md-9 with-sidebar">
						<div class="container">
							<div id="fuelux-wizard" class="wizard row">
								<ul class="wizard-steps">
									<li class="active"><span class="step">1</span><span class="title">基本信息</span></li>
									<li class="active"><span class="step">2</span><span class="title">获取ID下载SDK</span></li>
									<li class="active"><span class="step">3</span><span class="title">上传应用</span></li>
								</ul>
							</div>
							<form id="addDevApp_form" method="post" action="addApplication3.action" enctype="multipart/form-data">
								<input type="hidden" value="${vo.id}" name="id"/>
								<div class="col-md-12 field-box">
									<label>开发者ID：</label><p>${vo.dev_id}</p> 
								</div>
								<div class="col-md-12 field-box">
									<label>应用ID：</label><p>${vo.id}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>AppKey：</label><p>${vo.appKey}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>应用平台：</label><p>${vo.os}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>应用名称：</label><p>${vo.name}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>投放形式：</label><p>${pageNames}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>应用类别：</label><p>${vo.categoryName}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>应用简介：</label><textarea id="area2" name="longDesc" reg="^[\w\W\u4e00-\u9fa5]{2,500}$" tip="应用简介2-500字符" readonly="readonly">${vo.longDesc}</textarea>
								</div>
								<div class="col-md-12 field-box">
									<label>应用状态：</label><p background-color=:"#b5b5b5">${vo.statusName}</p>
									<span class="alert-a">*现在该应用还未上传审核，只能获取测试广告，无法获取收入，请嵌入广告后上传并等待审核</span>
								</div>
								<c:if test="${vo.os =='android'}">
									<div class="col-md-12 field-box">
										<label>上传应用：</label>
										<div id="uploadMsg">
											<c:if test="${fileUrl ne null && fileUrl ne ''}">
												<a href="${fileUrl}" target="_blank">${vo.fileName}&nbsp;</a>
											</c:if>
											<c:if test="${fileUrl eq null || fileUrl eq ''}">
												${vo.name}&nbsp;(未上传应用)
											</c:if>
										</div>
									</div>
									<div class="col-md-12 field-box">
										<label>上传应用：</label>
										<div>
											<div id="fileQueue"></div>
											<input type="file" name="uploadify" id="uploadify" />
											<a href="javascript:jQuery('#uploadify').uploadifyUpload()"><img src="upload/images/startupload.jpg"/></a>&nbsp;
											<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()"><img src="upload/images/cancelupload.jpg"/></a>
										</div>
									</div>
								</c:if>
								<c:if test="${vo.os=='ios'}">
									<div class="col-md-12 field-box">
										<label>APP Store地址：</label>
										<input id="marketUrl" name="marketUrl" type="text" class="form-control" value="${vo.marketUrl}"/>
									</div>
									<div class="col-md-12 field-box">
										<label>上传应用：</label>
										<div id="uploadMsg">
											<c:if test="${fileUrl ne null && fileUrl ne ''}">
												<a href="${fileUrl}" target="_blank">${vo.fileName}&nbsp;</a>
											</c:if>
											<c:if test="${fileUrl eq null || fileUrl eq ''}">
												${vo.name}&nbsp;(未上传应用)
											</c:if>
										</div>
										<div>
											<div id="fileQueue"></div>
											<input type="file" name="uploadify" id="uploadify" />
											<a href="javascript:jQuery('#uploadify').uploadifyUpload()"><img src="upload/images/startupload.jpg"/></a>&nbsp;
											<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()"><img src="upload/images/cancelupload.jpg"/></a>
										</div>
									</div>
									<font style="font-size: 9px;">请您上传应用名称不要有汉字或特殊字符！</font>
								</c:if>
								<div class="wizard-actions">
									<a href="toAddApplication2.action?app_id=${vo.id}"><button type="button" class="btn-glow primary btn-prev"><i class="icon-chevron-left"></i>上一步</button></a>
									<a href="javascript:void(0);" onclick="submitForm();"><button type="button" class="btn-glow success btn-next">确认保存</button></a>
								</div>
							</form>
						</div>
					</div>
               		<div class="col-md-3_1 col-xs-12 address pull-right">
						<h6>
							<div class="alert alert-info hidden-tablet">
								<i class="icon-lightbulb"></i>常见问题
							</div>
						</h6>
						<ul>
							<li class="ico-li"><a href="#">怎么下载SDK？</a></li>
							<li class="ico-li"><a href="#">怎么上传审核应用？</a></li>
							<li class="ico-li"><a href="#">如何添加新应用？</a></li>
							<li class="ico-li"><a href="#">审核一般需要多长时间？</a></li>
							<li class="ico-li"><a href="#">财务信息可以不是本人吗？</a></li>
							<li class="ico-li"><a href="#">怎么查看账户财务状态？</a></li>
							<li class="ico-li"><a href="#">开发者提现须知有哪些？</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu2_1");
		</script>
	</body>
</html>