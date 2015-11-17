<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>行云广告平台--添加应用</title>
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/user-profile.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/form-wizard.css" type="text/css" media="screen" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fuelux.wizard.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-hcheckbox.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mousewheel.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jscrollpane.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/poshytip/jquery.poshytip.min.js"></script>
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
				var cgi= $("#categoryId").val();
				if(cgi){
					$("#sp").html("");
				}else{
					b=false;
					$("#sp").html("请选择应用类别！");
				}
				if($("input:checkbox[name=pageType][checked]").length<=0){
					$("#wall_type_tip").css("display","");
					b=false;
				} else {
					$("#wall_type_tip").css("display","none");
				}
				if(!vaildateForm("addDevApp_form")){
					b=false;
				}
				if(!validate($("#area2"), true)){
					b=false;
				}
				if(b){
					$("#addDevApp_form").submit();
				}
			}
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<!-- main container -->
		<div class="content">
			<!-- settings changer -->
			<div id="pad-wrapper" class="user-profile">
				<!-- header -->
				<div class="row header">
					<div class="col-md-12">
						<h3>添加应用</h3>
					</div>
				</div>
				<div class="row form-wrapper">
					<div class="col-md-9 with-sidebar">
						<div class="container">
							<div id="fuelux-wizard" class="wizard row">
								<ul class="wizard-steps">
									<li class="active"><span class="step">1</span><span class="title">基本信息</span></li>
									<li><span class="step">2</span><span class="title">获取ID下载SDK</span></li>
									<li><span class="step">3</span><span class="title">上传应用</span></li>
								</ul>
							</div>
							<form name="addDevApp" id="addDevApp_form" action="addApplication1.action" method="post">
								<input type="hidden" id="id" name="id" value="${vo.id}"/>
								<div class="col-md-12 field-box">
									<label>应用平台：</label>
									<input type="radio" name="os" value="android" <c:if test="${vo.os!='ios'}">checked="checked"</c:if>/>&nbsp;Android&nbsp;
									<input type="radio" name="os" value="ios" <c:if test="${vo.os=='ios'}">checked="checked"</c:if>/>&nbsp;iOS 
								</div>
								<div class="col-md-12 field-box">
									<label>应用名称：</label>
									<div class="name" style="overflow-x:hidden;">
										<input id="name" name="name" type="text" class="form-control" reg="^[a-zA-Z0-9\u4e00-\u9fa5\.]{2,20}$" tip="应用名称2-20字符" value="${vo.name}"/>
									</div>
								</div>
								<div class="col-md-12 field-box">
									<label>应用类别：</label>
									<select id="categoryId" name="categoryId">
										<option value="">请选择一个类型</option>
										<c:forEach items="${ecList}" var="entry">
											<option id="status_${entry.id}" value="${entry.id}" <c:if test="${vo.categoryId==entry.id}">selected</c:if>>${entry.fname}-${entry.name}</option>
										</c:forEach>
									</select>
									<span id="sp" style="color: red;"></span>
								</div>
								<div class="col-md-12 field-box">
									<label>投放形式：</label>
									<input id="pageType_0" name="pageType" type="checkbox" value="0"/>&nbsp;积分墙
									<input id="pageType_1" name="pageType" type="checkbox" value="1"/>&nbsp;推荐墙
									<input id="pageType_5" name="pageType" type="checkbox" value="5"/>&nbsp;插屏 
									<span id="wall_type_tip" style="display: none;"><font color="#f00">请选择广告形式</font></span>
								</div>
								<div id="jifen_d1" class="col-md-12 field-box">
									<label>虚拟货币单位：</label><input id="virtual_currency_name" name="virtual_currency_name" type="text" class="form-control" value="${vo.virtual_currency_name}"/>
								</div>
								<div id="jifen_d2" class="col-md-12 field-box">
									<label>汇率设置：</label><input id="exchange_rate_rmb" name="exchange_rate_rmb" type="text" class="form-control" value="${vo.exchange_rate_rmb}"/>
								</div>
								<div id="jifen_d3" class="col-md-12 field-box">
									<label>积分回调URL：</label><input id="response_url" name="response_url" type="text" class="form-control" value="${vo.response_url}"/>&nbsp;&nbsp;例:http://i.adwalker.cn/callback.do
								</div>
								<div class="col-md-12 field-box">
									<label>应用简介：</label>
									<div class="longDesc">
										<textarea id="area2" name="longDesc" rows="5" cols="2000" reg="^[\w\W\u4e00-\u9fa5]{2,500}$" tip="应用简介2-500字符">${vo.longDesc}</textarea>
									</div>
								</div>
								<div class="wizard-actions">
									<button type="button" disabled class="btn-glow primary btn-prev"><i class="icon-chevron-left"></i>上一步</button>
									<a href="javascript:void(0);" onclick="submitForm();"><button type="button" class="btn-glow primary btn-next" data-last="Finish">下一步 <i class="icon-chevron-right"></i></button></a>
								</div>
							</form>
						</div>
					</div>
					<!-- side address column -->
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