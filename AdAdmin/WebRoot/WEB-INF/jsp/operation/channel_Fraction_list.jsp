<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告包确认数录入</title>

<link
	href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript">
	//
	function confirm(id) {
		var confirm_num ;
		var overplus = document.getElementById("overplus");
		var original = document.getElementById("original");
		if (confirm.value == "") {
			confirm.value = 0;
		}
		if (overplus.value <= 0) {
			alert("渠道包分数为0！");
		}	
		var num=0; 
		$("#tb input[name='confirm_num'][@value]").each(function(){
			if($(this).val()){
			confirm_num=parseFloat($(this).attr("value"));
			num=num+confirm_num;
			}
		});
		var overplus_number = parseFloat(parseFloat(original.value)-num);
		if (overplus_number >= 0) {
			$("#overplus").val(overplus_number);
		} else {
			$("input[name='confirm_num']").val("");  
			$("#overplus").val(parseFloat(original.value));
			alert("分数不能确认的总数！");
		}
	}

	
	//保存
	var dg = frameElement.lhgDG;
	dg.addBtn("save", "保存", function() {
		num();
	});
	

 function num(){
	 var values = document.getElementById("overplus");
		if(values.value==0){
			if (vaildateForm("confirm_form")) {
				$("#confirm_form").ajaxSubmit(function(data) {
					if (data) {
						var dataObj = eval("(" + data + ")");//转换为json对象 
						if (dataObj.status == 1) {
							alert("操作成功！！");
							dg.curWin.refresh();
						} else if (dataObj.status = -1) {
							alert("操作失败" + dataObj.error);
							dg.curWin.refresh();
						} else {
							alert("操作失败，请重试！！");
							dg.curWin.refresh();
						}
					} else {
						alert("操作失败，请重试！！");
						dg.curWin.refresh();
					}
				});
			}
			}else{
				alert("当天包确认的总数必须全部分完！");
		}
	 }
</script>
</head>
<body>

	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<table>
						<tr>
							<td>当天包确认的总数：</td>
							<td>
							<input id="original" 
								value="${income_amount}" disabled="disabled" />
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>
								<div align="right">剩余没分的数：</div></td>
							<td><input id="overplus" style="color: red"
								value="${income_amount}" disabled="disabled" /></td>
						</tr>
					</table>
					<form name="update" id="confirm_form" method="post"
						action="manage!saveConfirm.do">
						<input name="fraction_id" value="${id}" type="hidden" />
						 <input name="price" value="${price}" type="hidden" />
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"
							class="table_bod1 font_stat">
							<tr class="tr_td tb_head">
								<td>效果时间</td>
								<td>渠道包号</td>
								<td>媒体ID</td>
								<td>媒体名称</td>
								<td>媒体形式</td>
								<td>平台激活数</td>
								<td>广告主确认数</td>
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
									<tr>
										<input name="id" value="${vo.id}" type="hidden" />
										<td>${vo.static_date}</td>
										<td>${vo.package_id}</td>
										<td>${vo.media_id}</td>
										<c:choose>
											<c:when test="${vo.media_name ne null}">
												<td>${vo.media_name}</td>
											</c:when>
											<c:otherwise>
												<td>平台媒体</td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${vo.type_id == 0}">
												<td>积分墙</td>
											</c:when>
											<c:when test="${vo.type_id == 1}">
												<td>推荐墙</td>
											</c:when>
											<c:when test="${vo.type_id == 4}">
												<td>Banner</td>
											</c:when>
											<c:otherwise>
												<td>插屏</td>
											</c:otherwise>
										</c:choose>
										<td>${vo.sys_activate}</td>
										<div id="c_ftaction">
										<td>
											<input  onchange="confirm('${vo.id }')" id="${vo.id}_confirm_num" name="confirm_num"  type="text" id="" style="width: 60px;"
											onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="7" value="${vo.confirm_num}" />
										</td>
										</div>
										<td></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<td colspan="17" align="center" style="text-align: center;">暂无记录！</td>
							</c:if>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
