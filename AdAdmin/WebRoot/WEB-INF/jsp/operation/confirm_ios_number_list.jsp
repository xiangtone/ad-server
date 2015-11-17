<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="order" uri="/WEB-INF/tld/orderTag.tld"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台确认数录入</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">
.img01{
	display: none;
}
</style>
<script type="text/javascript">

//分数
function fractionIos(id,income_amount,str){		
	var url = "manage!fractionIos.do?income_amount="+income_amount+"&id="+id;
	new $.dialog({
		title:'IOS分数'+str,
		page:url,
		width:675,
		height:420,
		drag:true,
		resize:true,
		cover:true,
		
		maxBtn:false}).ShowDialog();
}

//重置
function restIosNum(id){
	var url = "manage!restConfIosNum.do?id="+id;
	if(confirm("确认要执行该操作吗？")){
	$.ajax({
		type:"get",   
		url:url,   
		dataType : "json", 
		success:function(data){
		if(data){
			if(data.status==1){
				alert("保存成功！！");
				refresh();
			}else if(data.status=-1){
				alert(data.error);
			}else{
				alert("操作失败，请重试！！");
			}
		}else{
			alert("操作失败，请重试！！");
			}
		}   
	  });
	}
}
//删除
function delIosNum(id){
	var url = "manage!delConfIosNum.do?id="+id;
	if(confirm("确认要执行该操作吗？")){
	$.ajax({
		type:"get",   
		url:url,   
		dataType : "json", 
		success:function(data){
		if(data){
			if(data.status==1){
				alert("保存成功！！");
				refresh();
			}else if(data.status=-1){
				alert(data.error);
			}else{
				alert("操作失败，请重试！！");
			}
		}else{
			alert("操作失败，请重试！！");
			}
		}   
	  });
	}
}



//保存
function confNumIos(id){
	var number = $("#"+id+"").val();
	if(number){
	var url = "manage!confIosNum.do?id="+id+"&number="+number;
	if(confirm("确认要执行该操作吗？")){
	$.ajax({
		type:"get",   
		url:url,   
		dataType : "json", 
		success:function(data){
		if(data){
			if(data.status==1){
				alert("保存成功！！");
				refresh();
			}else if(data.status=-1){
				alert(data.error);
			}else{
				alert("操作失败，请重试！！");
			}
		}else{
			alert("操作失败，请重试！！");
			}
		}   
	  });
	}
	//取消操作
	}else{
		alert("广告主确认数不能为空！");
	}
}

//提交
function sumit_num(){
	if($("#tb input:checkbox[checked][@value]").length<=0){
		alert("提示：请选择要提交的数据！！");
		return;
	}
	var ids=getCheckedId();
	$.ajax({
		url:'${pageContext.request.contextPath}/manage!submitConfNumIos.do',
		type:'POST',
		data:'ids='+ids,
		dataType:'text',
		beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					alert("操作成功！！");
					refresh();
				}
			}
			
		}
	});
}



/**
 * 全选
 **/
function selectAll(value){
	var b=value;
	$("#tb input:checkbox[disabled!='disabled']").attr("checked", b);
}

//获取所有选中的id
function getCheckedId(){
	var ids="";
	if($("#tb input:checkbox[checked][@value]").length>0){
		$("#tb input:checkbox[checked][@value]").each(function(){
			if(ids){
				ids=ids+","+$(this).val();
			}else{
				ids=$(this).val();
			}
		});
	}
	return ids;
}

$(document).ready(function (){
	initOrder();
});

function refresh(){
	$("#iosNumList").submit();
}

function initOrder(){
	 $(".tb_head th").css("cursor","pointer");
	 var code=$.cookie("table_order");
	 if(code){
		 $("#"+code).removeClass('img01');
	 }

	 $(".tb_head th").click(function(){
		 if($(this).attr("order")&&($(this).attr("order")=='false')){
			 return;
		 }
		 var obj;
		 if($(this).find(".img01").length==2){
			 obj=$(this).find("img").eq(0);
		 }else{
			 obj=$(this).find(".img01");
			 obj.removeClass('img01');
			 obj.siblings().addClass('img01');
		 }
		 changeOrder(obj.attr("id"),obj.attr("url"));
	 });    	 
}
function changeOrder(key,url){
	 $.cookie("table_order",key);
	 window.location.href=url;
}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>IOS日确认数录入</legend>
						<div id="search_bar">
						<form action="manage!ConfirmationIosNumList.do" method="post" id="iosNumList">
							<div>
								<input type="hidden" name="pageRecord" value="${pageInfo.pageSize}"/>
								<input type="hidden" name="order"  value="${pageInfo.orderParam}"/>
							</div>
							<table width="100%">
								<tr>
									<td>
										<table width="100%">
											<tr>
												<td>发生日期</td>
												<td>
													<input name="stat_date" id="stat_date" type="text"	value="${bean.stat_date}" class="Wdate" readonly="readonly" onclick="WdatePicker()" />
													至<input name="end_date" id="end_date" type="text"	value="${bean.end_date}" class="Wdate" readonly="readonly" onclick="WdatePicker()" />
												</td>
												<td>状态</td>
												<td>
													<select name="status">
														<option value="">全部</option>
														<option value="0" <c:if test="${bean.status ==0}">selected="selected" </c:if> >待录数</option>
														<option value="-1" <c:if test="${bean.status ==-1}">selected="selected" </c:if> >待分数</option>
														<option value="2" <c:if test="${bean.status ==2}">selected="selected" </c:if> >待提交</option>
														<option value="9" <c:if test="${bean.status ==9}">selected="selected" </c:if>>已提交</option>
													</select>
												</td>
											</tr>
											<tr>
												<td>活动ID/名称</td>
												<td>
													<input name="campaign" id="campaign" type="text" value="${bean.campaign}" maxlength="50" />
												</td>
											</tr>
										</table>
									</td>
									<td width="15%" valign="middle" align="right">
										<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
											<button onclick="search('iosNumList');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
											<button onclick="resetAll('iosNumList');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</fieldset>
					</form>
					<div>
						<input type="button" value="提交" onclick="sumit_num();" <escore:security code="ACTIVATE_NUM_SUMINT" type="button" /> />
					</div>
					<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
						<tr class="tr_td tb_head">
							<th width="3%"  order="false"><input id="checkbox" type="checkbox" value="${k.id}" onclick="selectAll(this.checked);" /></th>
							<th width="5%" order="false">ID</th>
							<th width="10%">发生时间<order:order url='${pageInfo.url}'  orderName='static_date' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
							<th width="5%" order="false">活动id</th>
							<th width="10%">活动名称<order:order url='${pageInfo.url}'  orderName='campaign_name' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
							<th order="false">接入单价</th>
							<th width="5%" order="false">状态</th>
							<th order="false">媒体</th>
							<th order="false">个数</th>
							<th order="false">点击数</th>
							<th order="false">激活数</th>
							<th order="false">广告主确认数</th>
							<th order="false">录入数</th>
							<th width="8%"  order="false">操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td style="text-align: center;"><input id="checkbox" name="app" type="checkbox" value="${vo.id}" <c:if test="${vo.status!=2}"> disabled</c:if>/></td>
									<td>${vo.id}</td>
									<td>${vo.static_date}</td>
									<td>${vo.campaign_id}</td>
									<td>${vo.campaign_name}</td>
									<td>${vo.price}</td>
									<td>
										<c:choose>
											<c:when test="${vo.status == 0}">
												待录数
											</c:when>
											<c:when test="${vo.status == -1}">
								     			 待分数
											</c:when>
											<c:when test="${vo.status == 2}">
								     			 待提交
											</c:when>
											<c:otherwise>
								 				已提交
											</c:otherwise>
										</c:choose>
									</td>
									<td><escore:subStr len="25" value="${vo.medias}" /></td>
									<td>${vo.media_num}</td>
									<td>${vo.click}</td>
									<td>${vo.activate}</td>
									<td>${vo.confirm_amount}</td>
									<td>
										<input type="text" id="${vo.id}" style="width: 60px;" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="7" <c:if test="${vo.status==9}"> disabled</c:if> value="<c:if test="${vo.status==0}">${vo.activate}</c:if>"/>
									</td>
									<td>
										<input type="button" value="保存" onclick="confNumIos('${vo.id}');" <c:if test="${vo.status==9}"> disabled</c:if> <escore:security code="ACTIVATE_NUM_SAVE" type="button" /> />
										<input type="button" value="分数" onclick="fractionIos('${vo.id}','${vo.confirm_amount}','【${vo.static_date}】--${vo.campaign_id}--${vo.campaign_name}')"  <c:if test="${vo.status!=-1}"> disabled</c:if> <escore:security code="ACTIVATE_NUM_DIV" type="button" /> />
										<input type="button" value="重录" onclick="restIosNum('${vo.id}')"  <c:if test="${vo.status==0}"> disabled</c:if> />
										<input type="button" value="删除" onclick="delIosNum('${vo.id}')"  />
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty list}">
							<td colspan="17" align="center" style="text-align: center;">暂无记录！</td>
						</c:if>
					</table>
					</div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
		</div>
	</div>
</body>
</html>