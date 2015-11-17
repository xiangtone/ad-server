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
	
	function form_submit(){
		if(!$("#select_salesman").val()){
			alert("请选择销售人员！！");
			$("#select_salesman").focus();
			return;
		}
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
			<form id="addPubAct" name="addPubAct" action="manage!updateCampaign.do" method="post" enctype="multipart/form-data">
				<div>
					<input type="hidden" name="id" id="id" value="${vo.id}" />
					<input type="hidden" name="placement_id" id="id" value="${vo.placement_id}" />
					<input type="hidden" name="status" id="campaign_status" value="${vo.status}" />
					<input type="hidden" id="dsp_sta" value="${vo.is_dsp}" />
					<input type="hidden" name="price_update" id="price_update" value="${vo.price}"/>
				</div>
				<div class="content_right admin_right">
				 <ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">基本信息</span></a></li>
    			</ul>
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td valign="top">活动名称：</td>
							<td colspan="3" class="actv_name">
								<input type="text" id="actv_name" name="campaign_name" value="${vo.campaign_name}" maxlength="20" reg="^[\u4e00-\u9fa5\w\W]{2,20}$" tip="请输入2-20个字符  "/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td valign="top">活动类型：</td>
							<td>
								<input name="campaign_type" value="0" type="radio" <c:if test="${0==vo.campaign_type}">checked="checked"</c:if> />下载类 
								<font color="red">*</font>
								<input name="campaign_type" type="radio" value="1" <c:if test="${1==vo.campaign_type}">checked="checked"</c:if>/>注册类
							</td>
						</tr>
						<tr>
							<td align="right">活动来源：</td>
							<td align="left">
								<select name="res_type" id="res_type" reg="[^0]" style="width: 153px" id="res_type">
									<option value="">请选择</option>
									<option value="1" <c:if test="${vo.res_type==1}"> selected="selected"</c:if>>直客</option>
									<option value="2" <c:if test="${vo.res_type==2}"> selected="selected"</c:if>>代理</option>
								</select>
								<font color="red">*</font> 
								<span style="display: none;"><font color="red">请选择活动分类！！</font></span>
							</td>
						</tr>
						<tr>
							<td valign="top">活动分类：</td>
							<td colspan="3">
								<select name="category_id" id="type" reg="[^0]" style="width: 153px" id="category_id">
									<option value="">请选择</option>
									<c:forEach items="${sList}" var="entry">
										<option value="${entry.id}"	<c:if test="${vo.category_id eq entry.id}">  selected="selected" </c:if>>${entry.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td valign="top">活动时间：</td>
							<td colspan="3">
								<input type="text" name="plan_start" id="sche_start_time" class="Wdate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'sche_end_time\')}',minDate:'${today}'});" value='<fmt:formatDate value="${vo.plan_start}" type="date" dateStyle="long" pattern="yyyy-MM-dd"/>' /> 
								至
								<input type="text" name="plan_end" id="sche_end_time" class="Wdate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'sche_start_time\')}'});" value='<fmt:formatDate value="${vo.plan_end}" type="date" dateStyle="long" pattern="yyyy-MM-dd"/>'/>
							</td>
						</tr>
						<tr>
							<td>活动预算：</td>
							<td><input type="text" name="budget" id="actv_money" value="${vo.budget }" reg="(^[0-9]{1,8})+([.]\d{1,2})?$" tip="请输入数字" maxlength="11"/> 元</td>
						</tr>
						<tr>
							<td>平台类型:</td>
							<td>
								<div>
									<input name="os" type="radio" value="android" <c:if test="${vo.os eq 'android' }">checked="checked"</c:if>/>ANDROID
									<input name="os" type="radio" value="ios" <c:if test="${vo.os eq 'ios' }">checked="checked"</c:if>/>IOS
								</div>
							</td>
						</tr>
						<tr>
							<td valign="top">计费方式：</td>
							<td>
							<input name="charge_type" type="radio" value="CPA" <c:if test="${vo.charge_type eq 'CPA' }">checked="checked"</c:if>/>CPA 
							<input name="charge_type" type="radio"value="CPC" <c:if test="${vo.charge_type eq 'CPC' }">checked="checked"</c:if>/>CPC 
							<input name="charge_type" type="radio" value="CPD" <c:if test="${vo.charge_type eq 'CPD' }">checked="checked"</c:if>/>CPD
							<input name="charge_type" type="radio" value="CPM" <c:if test="${vo.charge_type eq 'CPM' }">checked="checked"</c:if>/>CPM
							<input name="charge_type" type="radio" value="CPT" <c:if test="${vo.charge_type eq 'CPT' }">checked="checked"</c:if>/>CPT
							</td>
						</tr>
						<tr>
							<td>接入单价：</td>
							<td>
								<input type="text" name="price" id="price" value="${vo.price}"  maxlength="9" onblur="priceCharge();"/>元*
								<span id="sper" style="color: red;"></span>
							</td>
						</tr>
						<tr>
							<td align="right">确认方式：</td>
							<td align="left">
								<select name="confirm_mode" id="confirm_mode" style="width: 153px">							
									<option value="0" <c:if test="${vo.confirm_mode eq 0 }">   selected="selected" </c:if>>线下</option>
									<option value="1" <c:if test="${vo.confirm_mode eq 1 }">   selected="selected" </c:if>>线上</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">销售负责人：</td>
							<td align="left">
								<select id="select_salesman" name="salesman_id" style="width: 153px;">
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
									<input name="is_dsp" id="is_dsp_0" type="radio" value="0" <c:if test="${vo.is_dsp==0 }"> checked="checked"</c:if>/>否
									<input name="is_dsp" id="is_dsp_1"  type="radio" value="1" <c:if test="${vo.is_dsp==1 }">
											    checked="checked"</c:if>/>是
									<select name="dsp_id" id="dsp_id" style="width: 155px;" reg="[^0]">
											<option value="">请选择</option>
											<c:forEach items="${dspList}" var="entry">
												<option value="${entry.dsp_id}" <c:if test="${vo.dsp_id eq entry.dsp_id}"> selected="selected" </c:if>>${entry.dsp_id}--${entry.dsp_name}</option>
											</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						<!--  
						<tr>
							<td align="right">结算周期：</td>
							<td class="balance_cycle" align="left">
								<input type="text" name="balance_cycle" id="balance_cycle" reg="(^[0-9]{1,2})+([.]\d{1,2})?$" value="${vo.balance_cycle}" tip="亲,请加价率内哦！！" maxlength="5"/></td>
						</tr>
						-->
						<tr>
							<td valign="top">限量要求：</td>
							<td colspan="3" class="campaign_required">
								<textarea rows="5" cols="40" name="campaign_required">${vo.campaign_required}</textarea>
								<font color="red">*</font>	
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