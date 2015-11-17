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
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/regist/validate.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	
	dg.addBtn("save","保存草稿",function(){
		formSubmit();
	});
	
	dg.addBtn("sub","提交审核",function(){
		$("#act_id").val(1);
		formSubmit();
	
	});
	dg.SetCancelBtn('关闭',function(){
		dg.curWin.refresh();
	});
	
	function formSubmit(){
		if(vaildateForm("addPubAct")){
			$("#addPubAct").ajaxSubmit(function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("操作成功！！");
						dg.curWin.refresh();
					}else if(dataObj.status=-1){
						
					}else{
						alert("操作失败！！");
					}
				}else{
					alert("操作失败！！");
				}
				
			});
		}
	}
	
	$(document).ready(function(){
		$("#category_id").blur(function(){
			if(!$(this).val()){
				$(this).siblings("span").show();
			}
		});
	});
	
	function priceCharge(){
			var t=$("input[name='charge_type']:checked").val(); 
		 	var str=price.value;
		if(t=='CPT'){
			 var reg=/(^[0-9]{1,6})+([.]\d{1,2})?$/;
			 if ( str.match(reg)== null){
	             $("#sper").html("请输入1-6位数单价！");
	             return false;
	          }else{
	        	  $("#sper").html("");
		             return true;
	          }
		}else{
			 var res=/(^[0-9]{1,3})+([.]\d{1,2})?$/;
			 if ( str.match(res)== null){
				 $("#sper").html("请输入1-3位数单价！");
	             return false;
	          }else{
	        	  $("#sper").html("");
		             return true;
	          }	
		}
	}
	
	$(document).ready(function(){
	
		//dsp业务
		$("#is_dsp_suv input[@type=radio][name='is_dsp']").click(function(){
			var t=$("input[name='is_dsp']:checked").val();  
			if(t==0){
				$("#is_dsp_0").attr("checked","checked");
				$("#dsp_id").attr("disabled",true);
				$("#dsp_id").attr("value","");
			}else{
				$("#is_dsp_1").attr("checked","checked");
				$("#dsp_id").attr("disabled",false);
				//dspName();
			}
		});
	
	});	
</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<div class="content_right admin_right">							
				<form id="addPubAct" name="addPubAct" action="manage!saveCampaign.do" method="post"	enctype="multipart/form-data">
					<input type="hidden" name="adv_email" id="adv_email" value="${advEmail}" />
					<input type="hidden" name="adv_id" id="adv_id" value="${adv_id}" />
					<input type="hidden" name="isSubmit" id="act_id" value="0" />
					<table style="width: 600px;">
					       <tr>
								<td width="23%" height="30" align="right">广告主：</td>
								<td width="80%" align="left" >${advEmail}</td>
							</tr>
						<tr>
							<td align="right">活动名称：</td>
							<td align="left" class="campaign_name">
								<input type="text" id="campaign_name" name="campaign_name"	value="${advertisement.adName}" maxlength="20" reg="^[\u4e00-\u9fa5\w\W]{2,20}$" tip="请输入2-20个字符  "/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="right">活动类型：</td>
							<td align="left">
								<input name="campaign_type" type="radio" value="0" checked="checked" />下载类 
								<input name="campaign_type"
								type="radio" value="1" />注册类</td>
						</tr>
						<tr>
							<td align="right">开始时间：</td>
							<td align="left">
								<input type="text" value="${today}" name="plan_start" id="sche_start_time" class="Wdate" readonly="readonly"	onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'sche_end_time\')}',minDate:'${today}'});" /> 
							</td>
							</tr>
						<tr>
							<td align="right">结束时间：</td>
							<td align="left">
								<input type="text" name="plan_end"	id="sche_end_time" class="Wdate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'sche_start_time\')}'});" />
							</td>
						</tr>
						<tr>
							<td align="right">活动来源：</td>
							<td align="left">
								<select name="res_type" id="res_type" reg="[^0]" style="width: 153px" id="res_type">
									<option value="">请选择</option>
									<option value="1">直客</option>
									<option value="2">代理</option>
								</select>
								<font color="red">*</font> 
								<span style="display: none;"><font color="red">请选择活动 来源！！</font></span>
							</td>
						</tr>
						<tr>
							<td align="right">活动分类：</td>
							<td align="left">
								<select name="category_id" id="type" reg="[^0]" style="width: 153px" id="category_id">
									<option value="">请选择</option>
									<c:forEach items="${sList}" var="entry">
										<option value="${entry.id}"	<c:if test="${vo.category_id eq entry.id}"> selected="selected" </c:if>>${entry.name}</option>
									</c:forEach>
								</select>
								<font color="red">*</font> 
								<span style="display: none;"><font color="red">请选择活动分类！！</font></span>
							</td>
						</tr>
						<tr>
							<td align="right">活动预算：</td>
							<td class="budget" align="left">
								<input type="text" name="budget" id="budget" reg="(^[0-9]{1,8})+([.]\d{1,2})?$" tip="请输入数字" maxlength="11" value="100000.00" /> 元</td>
						</tr>
						<tr>
							<td align="right">平台类型：</td>
							<td align="left">
								<div>
									<input name="os" type="radio" value="android" checked="checked" />ANDROID
									<input name="os" type="radio" value="ios" />IOS
								</div>
							</td>
						</tr>
						<tr>
							<td align="right">计费方式：</td>
							<td align="left"  >
								<input name="charge_type" type="radio" value="CPA" checked="checked" />CPA
								<input name="charge_type" type="radio" value="CPC" />CPC
								<input name="charge_type" type="radio" value="CPD" />CPD
								<input name="charge_type" type="radio" value="CPM" />CPM
								<input name="charge_type" type="radio" value="CPT" />CPT
							</td>
						</tr>
						<tr>
								<td align="right">接入单价：</td>
								<td align="left">
									<input type="text" name="price" id="price"  maxlength="9" onblur="priceCharge();"/>元*
									<span id="sper" style="color: red;"></span>
									</td>
						</tr>
						<tr>
							<td align="right">确认方式：</td>
							<td>
								<select name="confirm_mode" style="width: 155px;">
									<option value="1">线上</option>
									<option value="0">线下</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">销售负责人：</td>
							<td align="left">
								<select name="salesman_id" style="width: 153px;">
									<option value="">请选择</option>					
									<c:forEach items="${salesmanList}" var="entity">
										<option value="${entity.id}" <c:if test="${vo.salesman_id eq entity.id}"> selected="selected" </c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">DSP业务：</td>
							<td align="left">
								<div id="is_dsp_suv">
									<input name="is_dsp" id="is_dsp_0" type="radio" value="0" checked="checked" />否
									<input name="is_dsp" id="is_dsp_1"  type="radio" value="1" />是
									<select name="dsp_id" id="dsp_id" style="width: 155px;" disabled="disabled" reg="[^0]">
											<option value="">请选择</option>
										<c:forEach items="${dspList}" var="entry">
											<option value="${entry.dsp_id}">${entry.dsp_id}--${entry.dsp_name}</option>
										</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						<!-- 
						<tr>
							<td align="right">结算周期：</td>
							<td class="balance_cycle" align="left">
								<input type="text" name="balance_cycle" id="balance_cycle" reg="(^[0-9]{1,2})+([.]\d{1,2})?$" tip="亲,请输入结算周期哦！！" maxlength="5"/>
							</td>
						</tr>
						 -->
						<tr>
							<td align="right">活动要求：</td>
							<td align="left" class="campaign_required">
								<textarea rows="5" cols="40" name="campaign_required"></textarea>
								<font color="red"></font>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>