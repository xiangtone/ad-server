<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><%=company%>广告平台</title>
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/user-profile.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/form-wizard.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fuelux.wizard.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-hcheckbox.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mousewheel.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jscrollpane.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/poshytip/jquery.poshytip.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/upload/js/swfobject.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/upload/js/jquery.uploadify.v2.0.3.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#jifen_d1").toggle();
				$("#jifen_d2").toggle();
				$("#jifen_d3").toggle();
				$("#pageType_0").click(function() {
					$("#jifen_d1").toggle();
					$("#jifen_d2").toggle();
					$("#jifen_d3").toggle();
				});
				<c:if test="${!empty pageList}">
					<c:forEach items="${pageList}" var="page" varStatus="status">
						checkType('${page.type_id}');
					</c:forEach>
				</c:if>
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
					'scriptData'     : {"po":"0","pa":"/${dev.email}/${app.id}/","ic":"1"},
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
			function checkType(id){
				if(id==0){
					$("#jifen_d1").toggle();
					$("#jifen_d2").toggle();
					$("#jifen_d3").toggle();
				}
				$("#pageType_"+id).attr("checked","checked");
			}
			function submitForm(){
				var b=true;
				var  cgi= $("#categoryId").val();
				if(cgi){
					$("#sp").html("");
				}else{
					$("#sp").html("请选择应用类别！");
					b=false;
				}
				if(b){
					$("#updateApplicationForm").ajaxSubmit(function(data){
						if(data){
							var dataObj=eval("("+data+")");//转换为json对象 
							if(dataObj.status==1){
								alert("操作成功！！");
								window.location.href='applicationList.action';
							}else if(dataObj.status=-1){
								alert("操作失败请重试 ");
							}else{
								alert("操作失败，请重试！！");
							}
						}
					});
				}
			}
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
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<div class="content">
			<div id="pad-wrapper" class="user-profile">
				<div class="row header">
					<div class="col-md-12"><h3>编辑应用</h3></div>
				</div>
				<div class="row form-wrapper">
					<div class="col-md-9 with-sidebar">
						<div class="container">
							<form name="updateApplicationForm" id="updateApplicationForm" action="updateApplication.action" method="post" onsubmit="check_AddDevApp();" enctype="multipart/form-data">
								<input type="hidden" id="id" name="id" value="${app.id}" />
								<input type="hidden" id="os" name="os" value="${app.os}" />
								<div class="col-md-12 field-box">
									<label>AppKey：</label>${app.appKey}
								</div>
								<div class="col-md-12 field-box">
									<label>开发者ID：</label>${dev.id}
								</div>
								<div class="col-md-12 field-box">
									<label>应用ID：</label>${app.id}
								</div>
								<div class="col-md-12 field-box">
									<label>应用平台：</label><p>${app.os}</p>
								</div>
								<div class="col-md-12 field-box">
									<label>应用名称：</label><input id="name" name="name" type="text" class="form-control" value="${app.name}"/>
								</div>
								<div class="col-md-12 field-box">
									<label>投放形式：</label>									
									<input id="pageType_0" type="checkbox" value="0" name="pageType"/>&nbsp;积分墙
									<input id="pageType_1" type="checkbox" value="1" name="pageType"/>&nbsp;推荐墙
									<input id="pageType_5" type="checkbox" value="5" name="pageType"/>&nbsp;插屏
								</div>
								<div id="jifen_d1" class="col-md-12 field-box">
									<label>虚拟货币单位：</label><input id="virtual_currency_name" name="virtual_currency_name" type="text" class="form-control" value="${app.virtual_currency_name}"/>
								</div>
								<div id="jifen_d2" class="col-md-12 field-box">
									<label>汇率设置：</label><input id="exchange_rate_rmb" name="exchange_rate_rmb" type="text" class="form-control" value="${app.exchange_rate_rmb}"/>
								</div>
								<div id="jifen_d3" class="col-md-12 field-box">
								<label>积分回调URL：</label><input id="response_url" name="response_url" type="text" class="form-control" value="${app.response_url}"/>&nbsp;&nbsp;例:http://i.adwalker.cn/callback.do
								</div>
								<div class="col-md-12 field-box">
									<label>应用类别：</label>
									<select id="categoryId" name="categoryId" title="请选择一个类型">
										<option value="">请选择一个类型</option>
										<c:forEach items="${ecList}" var="entry">
											<option value="${entry.id}" <c:if test="${app.categoryId==entry.id}">selected</c:if>>${entry.fname}-${entry.name}</option>
										</c:forEach>
									</select>
								</div>
								<c:if test="${app.os =='android'}">
									<div class="col-md-12 field-box">
										<label>上传应用：</label>
										<div id="uploadMsg">
											<c:if test="${fileUrl ne null && fileUrl ne ''}">
												<a href="${fileUrl}" target="_blank">${app.fileName}&nbsp;</a>
											</c:if>
											<c:if test="${fileUrl eq null || fileUrl eq ''}">
												${app.name }&nbsp;(未上传应用)
											</c:if>
										</div>
									</div>
									<div class="col-md-12 field-box">
										<label>上传应用：</label>
										<div>
											<div id="fileQueue"></div>
											<input type="file" name="uploadify" id="uploadify" />
											<a href="javascript:jQuery('#uploadify').uploadifyUpload()"><img src="upload/images/startupload.jpg" style="vertical-align:baseline;" /></a>&nbsp;
											<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()"><img src="upload/images/cancelupload.jpg" style="vertical-align:baseline;" /></a>
										</div>
									</div>
								</c:if>
								<c:if test="${app.os=='ios'}">
									<div class="col-md-12 field-box">
										<label>APP Store地址：</label>
										<input type="text" class="form-control" value="${app.marketUrl}"/>
									</div>
									<div class="col-md-12 field-box">
										<label>上传应用：</label>
										<div id="uploadMsg">
											<c:if test="${fileUrl ne null && fileUrl ne ''}">
												<a href="${fileUrl}" target="_blank">${app.fileName}&nbsp;</a>
											</c:if>
											<c:if test="${fileUrl eq null || fileUrl eq ''}">
												${app.name}&nbsp;(未上传应用)
											</c:if>
										</div>
										<div>
											<div id="fileQueue"></div>
											<input type="file" name="uploadify" id="uploadify" />
											<a href="javascript:jQuery('#uploadify').uploadifyUpload()"><img src="upload/images/startupload.jpg" style="vertical-align:baseline;" /></a>&nbsp;
											<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()"><img src="upload/images/cancelupload.jpg" style="vertical-align:baseline;"/></a>
										</div>
									</div>
									<font style="font-size: 9px;">请您上传应用名称不要有汉字或特殊字符！</font>
								</c:if>
								<div class="col-md-12 field-box">
									<label>应用简介：</label>
									<textarea id="area2" name="longDesc" reg="^[\w\W\u4e00-\u9fa5]{2,500}$" tip="应用简介2-500字符" >${app.longDesc}</textarea>
								</div>
								<div class="wizard-actions">
									<a href="applicationList.action"><button type="button" class="btn-glow primary btn-prev"><i class="icon-chevron-left"></i>返回</button></a>
									<a href="javascript:void(0);" onclick="submitForm();"><button type="button" class="btn-glow success btn-next">确认保存</button></a>
								</div>
							</form>
						</div>
					</div>
					<div class="col-md-3_1 col-xs-12 address pull-right">
						<h6><div class="alert alert-info hidden-tablet"><i class="icon-lightbulb"></i>常见问题</div></h6>
						<ul>
							<li class="ico-li"><a href="#">如何创建应用？</a></li>
							<li class="ico-li"><a href="#">如何在应用内展示广告？</a></li>
							<li class="ico-li"><a href="#">什么是测试 Publisher ID？</a></li>
							<li class="ico-li"><a href="#">如何查看报表？</a></li>
							<li class="ico-li"><a href="#">如何产生收益？</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu2_2");
		</script>
	</body>
</html>