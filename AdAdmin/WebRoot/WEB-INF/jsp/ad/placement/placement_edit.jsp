<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告主发布广告</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	function initButton(){
		dg.addBtn("save","保存",function(){
			submit_form();
		});
	}
	
	function submit_form(){
		$("#addPubAct").ajaxSubmit(function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status==1){
					alert("提交成功！！");
					dg.curWin.refresh();
				}else if(dataObj.status=-1){
					$("#error_tip").html(dataObj.error);
					$("#error_tip").css("display","block");
				}else{
					alert("操作失败，请重试！！");
				}
			}else{
				alert("操作失败，请重试！！");
			}
		});
	}
	
	$(function() {
		$('.tab_block2 li a').click(
				function() {
					$(this).parent('li').addClass('click').siblings('li')
							.removeClass('click');
					$(this).addClass('click').parent('li').siblings('li').children(
							'a').removeClass('click');
					$(this).children('span').addClass('click').parent('a').parent(
							'li').siblings('li').children('a').children('span')
							.removeClass('click');
				});
		$('.sub_1').click(function() {
			$('.subblock_1').show().siblings('div').hide();
			initButton();
		});

		$('.sub_2').click(function() {
			$('.subblock_2').show().siblings('div').hide();
			remove();
		});
		$('.sub_3').click(function() {
			$('.subblock_3').show().siblings('div').hide();
			remove();
		});
		$('.sub_4').click(function() {
			$('.subblock_4').show().siblings('div').hide();
		});
		$('.sub_5').click(function() {
			$('.subblock_5').show().siblings('div').hide();
		});
	});
	
	function remove(){
		dg.removeBtn("save");
		dg.removeBtn("audit");
	}

	
	$(document).ready(function(){
		
		initButton();
		var page_type='${page_type}';
		var b=false;
		   if(page_type){
			   if(page_type.indexOf("0")!=-1){
				   b=true;
			   }
			   if(page_type.indexOf(",")!=-1){
				   var arr=page_type.split(",");
				   for(var i=0;i<arr.length;i++){
					   $("#pagetype_"+arr[i]).attr("checked","checked");
					   $("#pagetype_"+arr[i]).attr("readonly","readonly");
					   $("#pagetype_"+arr[i]).click(function(){
						   //覆盖系统事件，模拟只读效果
						   return false;
					   });
				   }
			   }else{
				   $("#pagetype_"+page_type).attr("checked","checked");
				   $("#pagetype_"+page_type).attr("readonly","readonly");
				   $("#pagetype_"+page_type).click(function(){
					   return false; //覆盖系统事件，模拟只读效果
				   });
			   }
		   }
		   if(!b){
			   $("#pagetype_0").click(function(){
				   poo();
			   });		   }
		   
		   poon();
	});
	
	function poon(){
		var popular_score=$("#pagetype_1").is(':checked');
		if(popular_score){
			$("#popular_1").show();
		}else{
			$("#popular_1").hide();
		}
	}
	
	function poo(){
		var popular_score=$("#pagetype_1").is(':checked');
		if(popular_score){
			$("#popular_1").show();
		}else{
			$("#popular_1").hide();
		}
	}
	
</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
				<div class="content_right admin_right">
				<form id="addPubAct" name="addPubAct" action="manage!modifyPlacement.do" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" id="adv_id" value="${placement_id}" />
					<input type="hidden" name="status" id="placement_status" value="-20" />
				 <ul class="tab_block2">    
      					<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">基本信息</span></a></li>
      					<li>
      						<a href="javascript:void(0)" class="sub_2">
      						<span>平台投放</span>
      						</a>
      					</li>
      					<li>
      						<a href="javascript:void(0)" class="sub_3">
      						<span>渠道投放</span>
      						</a>
      					</li>
     					
    				</ul>
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td valign="top" align="right">投放名称：</td>
							<td colspan="3" class="name">
								<input type="text" id="campaign_name" name="name" value="${vo.name}"  maxlength="20" reg="^[\u4e00-\u9fa5\w\W]{2,20}$" tip="请输入2-20个字符 "/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<!-- 分类start -->
							<td valign="top" align="right">应用类别：</td>
							<td>
								<select name="category_id" id="type" reg="[^0]" style="width: 153px">
									<c:forEach items="${ecList}" var="entry">
										<option value="${entry.id}"	<c:if test="${vo.category_id eq entry.id}">   selected="selected" </c:if>>${entry.fname}-${entry.name}</option>
									</c:forEach>
								</select>
								* 
							</td>
								<!-- 分类end -->
						</tr>
						<tr>
							<td valign="top" align="right">广告形式：</td>
							<td>
								<span id="page_type">
									<input id="pagetype_0" type="checkbox" name="types" value="0" />积分墙
									<input id="pagetype_1" type="checkbox" name="types" value="1" onclick="poon()"/>推荐墙
									<input id="pagetype_4" type="checkbox" name="types" value="4" />BANNER
									<input id="pagetype_5" type="checkbox" name="types" value="5" />插屏
								</span>
								<span style="color: red;display: none;" id="page_type_tip">
									请选择投放形式
								</span>
							</td>
						</tr>
						<tr>
							<td valign="top"  align="right">星级：</td>
								<td><select name="star_level" id="point_desc_status" style="width: 153px">
									<option value="5.0"
										<c:if test="${vo.star_level eq 5.0 }">
											    selected="selected"
											</c:if>>五星</option>
									<option value="4.5"
										<c:if test="${vo.star_level eq 4.5 }">
											    selected="selected"
											</c:if>>四星半
									</option>
									<option value="4.0"
										<c:if test="${vo.star_level eq 4.0 }">
											    selected="selected"
											</c:if>>四星</option>
									<option value="3.5"
										<c:if test="${vo.star_level eq 3.5 }">
											    selected="selected"
											</c:if>>三星半
									</option>
									<option value="3.0"
										<c:if test="${vo.star_level eq 3.0 }">
											    selected="selected"
											</c:if>>三星</option>
									<option value="2.5"
										<c:if test="${vo.star_level eq 2.5 }">
											    selected="selected"
											</c:if>>二星半
									</option>
									<option value="2.0"
										<c:if test="${vo.star_level eq 2.0 }">
											    selected="selected"
											</c:if>>二星</option>
									<option value="1.5"
										<c:if test="${vo.star_level eq 1.5 }">
											    selected="selected"
											</c:if>>一星半
									</option>
									<option value="1.0"
										<c:if test="${vo.star_level eq 1.0 }">
											    selected="selected"
											</c:if>>一星</option>
							</select>
							</td>
						</tr>
						<tr id="popular_1" style="display: none;">
							<td valign="top" align="right">是否热门推荐：</td>
							<td>
								<input type="radio" name="popular_app" value="1" checked="checked"  <c:if test="${vo.popular_app ==1}">checked="checked"</c:if> />是
								<input type="radio" name="popular_app" value="0" <c:if test="${vo.popular_app ==0}">checked="checked"</c:if> />否
							</td>
						</tr>
						<tr>
							<td valign="top" align="right">是否优先推荐：</td>
							<td>
								<input type="radio" name="priority" value="1" checked="checked" <c:if test="${vo.priority ==1}">checked="checked"</c:if> />是
								<input type="radio" name="priority" value="0" <c:if test="${vo.priority ==0}">checked="checked"</c:if> />否
							</td>
						</tr>
						<tr>
							<td valign="top" align="right">是否是新应用：</td>
							<td>
								<input type="radio" name="new_app" value="1" checked="checked" <c:if test="${vo.new_app ==1}">checked="checked"</c:if> />是
								<input type="radio" name="new_app" value="0" <c:if test="${vo.new_app ==0}">checked="checked"</c:if> />否
							</td>
						</tr>
						<tr>
							<td valign="top" align="right">是否是链接：</td>
							<td>
								<input type="radio" name="is_url_params" value="1"  <c:if test="${vo.is_url_params ==1}">checked="checked"</c:if> />是
								<input type="radio" name="is_url_params" value="0" <c:if test="${vo.is_url_params ==0}">checked="checked"</c:if> />否
							</td>
						</tr>
						<tr>
							<td valign="top" align="right">广告语：</td>
							<td class="slogan">
								<input name="slogan" id="slogan" type="text" maxlength="22" value="${vo.slogan}" reg="^[\u4e00-\u9fa5\w\W]{2,22}$" tip="请输入2-22个字符 "/>*
							</td>
						</tr>
						<tr>
							<td valign="top" align="right">关键字：</td>
							<td class="keyword">
								<input name="keyword" id="keyword" type="text" value="${vo.keyword}" />
							</td>
						</tr>
						<tr>
							<td valign="top"></td>
							<td>*活动介绍使用的文字描述，2-1000个汉字，用户将在墙的详情页中看到*</td>
						</tr>
						<tr>
							<td valign="middle" align="right">活动介绍：</td>
							<td class="campaign_desc">
								<textarea name="campaign_desc" cols="50" rows="5" id="campaign_desc" reg="^[\w\W\u4e00-\u9fa5]{2,1000}$" tip="请输入2-1000个字符 " maxlength="1000">${vo.campaign_desc}</textarea>*
								<span id="longDescMes" style="color: #DC143C"></span>
							</td>
						</tr>
					</table>
				</div>
				<div class="subblock_2" style="display:none;border: 1px solid #9dbaff;float: left;width: 1866px;border-top: 0;height: 612px;">
					<iframe frameborder="0" height="720" width="940" src="manage!advertiseConfigureTemp.do?placement_id=${placement_id}"></iframe>
    			</div>
    			<div class="subblock_3" style="display:none;border: 1px solid #9dbaff;float: left;width: 1866px;border-top: 0;height: 612px;">
					<iframe frameborder="0" height="720" width="940" src="manage!advertiseConfigureChannel.do?placement_id=${placement_id}"></iframe>
    			</div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>