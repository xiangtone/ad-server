<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台Ios确认数录入</title>
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript">
	//
	function confirm(id,income_num,media_type) {
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
				
		//相邻的成本输入框先置空
		$("#cost_"+id).val('');
		
		
		var overplus_number = parseFloat(parseFloat(original.value)-num);//判断输入的值的总和是否大于可输入的值
		if (overplus_number >= 0) {
			$("#overplus").val(overplus_number);
			//如果录入的数据是有效的，成本输入框填入默认值（收入）。
			if(media_type==0){
				$("#cost_"+id).val(0);//平台成本先设置成0，这个不用输入 
			}else{
				$("#cost_"+id).val(income_num);
			}
			
		} else {
			alert("分数不能确认的总数！");
			$("input[name='confirm_num']").val("");  
			$("#overplus").val(parseFloat(original.value));
		}
	}

	
	//保存
	var dg = frameElement.lhgDG;
	dg.addBtn("save", "保存", function() {
		num();
	});
	

function num(){
	var values = document.getElementById("overplus");
	var flag=true;
	if(values.value!=0){
		alert("当天确认的总数必须全部分完！");
		flag=false;
		return;
	}
	
	//判断每个输入框是否有值
	$("#tb input[name='comfirm_cost']").each(function(){
		if(!($(this).val()>=0)){
			alert($(this).attr("id")+"---"+$(this).val());
			flag=false;
		}
	});
	if(!flag){
		alert("请录入每个渠道对应的成本！！");
		return;
	}
	
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
	}
	
}
 
 
 function cost_change(id,cost_input){
	 //输入的数据不合法，输入框清空
	 if(parseFloat(cost_input)>parseFloat($("#"+id+"_confirm_num").val())){
		 $("#cost_"+id).val('');
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
							<td>当天确认的总数：</td>
							<td>
							<input id="original" 
								value="${income_amount}" disabled="disabled" />
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>
								<div align="right">剩余没分的数：</div></td>
							<td><input id="overplus" style="color: red"	value="${income_amount}" disabled="disabled" /></td>
						</tr>
					</table>
					<form name="update" id="confirm_form" method="post"	action="manage!saveConfIos.do">
						<div style="display: none;">
							<input name="fraction_id" value="${id}" type="hidden" />
						</div>
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="table_bod1 font_stat">
							<tr class="tr_td tb_head">
								<td>媒体ID</td>
								<td>媒体名称</td>
								<td>媒体形式</td>
								<td>点击数</td>
								<td>广告主确认数</td>
								<td>成本确认数</td>
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
									<tr>
										<input name="id" value="${vo.id}" type="hidden" />
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
										<td>
											${vo.click}
											<c:if test="${vo.media_type==0}">
												(${vo.activate})
											</c:if>
										</td>
										<div id="c_ftaction">
											<td>
												<input onchange="confirm('${vo.id}',this.value,'${vo.media_type}')" id="${vo.id}_confirm_num" name="confirm_num"  type="text" id="" style="width: 60px;"	onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="7" value="${vo.confirm_num}" />
											</td>
										</div>
										<td>
											<c:choose>
												<c:when test="${vo.media_type==0}">
													<input name="comfirm_cost" type="text" id="cost_${vo.id}" style="width: 60px;" readonly="readonly" value="0" />
												</c:when>
												<c:otherwise>
													<input name="comfirm_cost" type="text" id="cost_${vo.id}" style="width: 60px;" onchange="cost_change('${vo.id}',this.value);" />
												</c:otherwise>
											</c:choose>
										</td>
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
