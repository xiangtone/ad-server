<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn("save","保存",function(){
			form_submit();
		});
		
		//判断，如果活动是草稿状态的时候才会有提交审核按钮
		var campaign_status='${vo.status}';
		if(campaign_status==-50){
			dg.addBtn("_submit","提交审核",function(){
				$("#campaign_status").val(-40);
				form_submit();
			});
			
		}
		var category_str='${category_str}';
		if(category_str){
			var arr=category_str.split(",");
			for(var i=0;i<arr.length;i++){
				$("#category_"+arr[i]).attr("checked","checked");
				$.each($("#category_"+arr[i]).parent().parent().parent().parent().find(".sel01"),function(){
					if($(this).val()==1){
						$(this).attr("checked","checked");
					}
				});
			}
		}
		
	});
	
	function form_submit(){
		var dg=frameElement.lhgDG;
		if(vaildateForm("addPubAct")){
			$("#addPubAct").ajaxSubmit(function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("提交成功！！");
						dg.curWin.refresh();
						//dg.cancel();
					}else if(dataObj.status=-1){
						
					}else{
						alert("操作失败，请重试！！");
					}
				}else{
					alert("操作失败，请重试！！");
				}
			});
		};
	}
	
	
	$(document).ready(function(){
		dsp_info();
		$("#tb input:checkbox[name='category']").click(function(){
			if($(this).parent().parent().parent().parent().find("input:checkbox[name='category'][checked]").length>0){
				$.each($(this).parent().parent().parent().parent().find(".sel01"),function(){
					if($(this).val()==1){
						$(this).attr("checked","checked");
					}
				});
			}else{
				$.each($(this).parent().parent().parent().parent().find(".sel01"),function(){
					if($(this).val()==0){
						$(this).attr("checked",true);
					}
				});	
			}
		});
		
		$("#tb input:radio").click(function(){
				if($(this).val()==0){
					$.each($(this).parent().parent().parent().parent().find("input:checkbox[name='category'][checked]"),function(){
						$(this).attr("checked",false);
						
					});
				}
				
		});
		
		
	});
	function dsp_info(){
		var aa=$("#dsp_sta").val();
		if(aa==0){
			$("#dsp_id").attr("disabled",true);
		}
		$("#is_dsp_suv input[@type=radio][name='is_dsp']").click(function(){
			var t=$("input[name='is_dsp']:checked").val();  
			if(t==0){
				$("#is_dsp_0").attr("checked","checked");
				$("#dsp_id").attr("disabled",true);
				$("#dsp_id").attr("value","");
			}else{
				$("#is_dsp_1").attr("checked","checked");
				$("#dsp_id").attr("disabled",false);
			}
		});
	}
</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<form id="addPubAct" name="addPubAct" action="updateCampaign111.do" method="post" enctype="multipart/form-data">
				<div>
					<input type="hidden" name="id" id="id" value="${id}" />
				</div>
				<div class="content_right admin_right">
				 <ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">投放信息</span></a></li>
    			</ul>
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 976px;border-top: 0;height: 482px;">
					<table id="tb">
						<tr>
							<td>终端类型：</td>
							<td>
								<input class="sel01" name="terminal_type" id="terminal_type_0" type="radio" value="0" checked="checked"/>不限 
								<input class="sel01" name="terminal_type" id="terminal_type_1" type="radio" value="1"/>限制
							</td>
							<td>
								<div style="display: block;width: 600px;height: 22px;font-size: 10px;">
									<c:forEach items="${map['0']}" var="obj">
										<span style="display: block;width: 120px;float: left;height: 22px;font-size: 10px;">
											<input type="checkbox" name="category" id="category_${obj.id}" value="${obj.id}" />${obj.content_value}
										</span>
									</c:forEach>
								</div>
							</td>
						</tr>
						<tr>
							<td>SDK版本：</td>
							<td>
								<input class="sel01" name="sdk_version"  type="radio" value="0" checked="checked" />不限 
								<input class="sel01" name="sdk_version"  type="radio" value="1" />限制</td>
							<td>
								<div style="display: block;height: 100px;width: 600px;height: 22px;">
								<c:forEach items="${mapc['1']}" var="obj">
									<span style="width: 120px;float: left;">
										<input type="checkbox" name="category" id="category_${obj.id}" value="${obj.id}" />${obj.name}
									</span>
								</c:forEach>
								</div>
							</td>
						</tr>
						<tr>
							<td>运营商：</td>
							<td>
								<input class="sel01" name="operat_agencies" type="radio" value="0" checked="checked" />不限
								<input class="sel01" name="operat_agencies" type="radio" value="1" />限制
							</td>
							<td>
								<div style="display: block;width: 600px;height: 22px;">
									<c:forEach items="${map['2']}" var="obj">
										<span style="display: block;width: 120px;float: left;">
											<input type="checkbox" name="category" id="category_${obj.id}" value="${obj.id}" />${obj.content_value}
										</span>
									</c:forEach>
								</div>
							</td>
						</tr>
						<tr>
							<td>手机品牌：</td>
							<td>
								<input class="sel01" name="phone_brand" type="radio" value="0" checked="checked" />不限 
								<input class="sel01" name="phone_brand" type="radio" value="1" />限制
							</td>
							<td>
								<div style="display: block;width: 600px;height: 22px;">
									<c:forEach items="${map['3']}" var="obj">
										<span style="display: block;width: 120px;border-bottom-color: blue;float: left;">
											<input type="checkbox" name="category" id="category_${obj.id}" value="${obj.id}" />${obj.content_value}
										</span>
									</c:forEach>
								</div>
							</td>
						</tr>
						
						<tr>
							<td>应用类型：</td>
							<td>
								<input class="sel01" name="app_type" type="radio" value="0" checked="checked" />不限
								<input class="sel01" name="app_type" type="radio" value="1" />限制
							</td>
							<td>
								<div style="display: block;width: 800px;height: 22px;">
									<c:forEach items="${map['4']}" var="obj">
										<span style="display: block;width: 120px;border-bottom-color: blue;float: left;line-height: 16px;">
											<input type="checkbox" name="category" id="category_${obj.id}" value="${obj.id}" style="cursor: pointer;" />${obj.name}
										</span>
									</c:forEach>
								</div>
							</td>
						</tr>
						 
						<tr>
							<td>时段定向：</td>
							<td>
								<input class="sel01" name="time_directional" type="radio" value="0"	checked="checked" />不限 
								<input class="sel01" name="time_directional" type="radio" value="1" />限制
							</td>
							<td>
								<div style="display: block;height: 100px;width: 800px;height: 22px;">
									<c:forEach items="${map['5']}" var="obj">
										<span style="display: block;width: 120px;border-bottom-color: blue;float: left;">
											<input type="checkbox" name="category" id="category_${obj.id}" value="${obj.id}" />${obj.name}
										</span>
									</c:forEach>
								</div>
							</td>
						</tr>
						<tr>
							<td>区域定向：</td>
							<td>
								<input class="sel01" name="area_directional" type="radio" value="0"	checked="checked" />不限 
								<input class="sel01" name="area_directional" type="radio" value="1" />限制
							</td>
							<td>
								<div style="display: block;height: 100px;width: 800px;height: 22px;">
									<c:forEach items="${mapc['6']}" var="obj">
										<span style="width: 120px;float: left;">
											<input type="checkbox" name="category" id="category_${obj.id}" value="${obj.id}" />${obj.name}
										</span>
									</c:forEach>
								</div>
							</td>
						</tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>