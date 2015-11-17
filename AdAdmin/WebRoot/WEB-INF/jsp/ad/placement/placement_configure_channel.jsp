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
<title>运营管理后台活动查询统计</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
/**
 * 全选
 **/
function selectAll(value){
	$("#tb input:checkbox").attr("checked", value);
	
}

$(document).ready(function(){
	//selectAll(0);
});





function addAdvertiseList(id){
	$("#error_tip").html("");
	if($("#tb input:checkbox[checked][@value][name='media']").length<=0){
		$("#error_tip").html("提示：请选择渠道！！");
		return;
	}
	
	//活动开始时间
	var sche_start_time=$("#sche_start_time").val();
	if(!sche_start_time){
		sche_start_time='';
		$("#error_tip").html("提示：请选择排期开始时间及结算时间");
		return;
	}
	
	//活动结束时间
	var sche_end_time=$("#sche_end_time").val();
	if(!sche_end_time){
		sche_end_time='';
		$("#error_tip").html("提示：请选择排期开始时间及结算时间");
		return;
	}
	
	var package_id=$("#package_for_select select").val();
	if(!package_id){
		$("#error_tip").html("提示：请选择包号！！");
		return;
	}
	
	var budget_day=$("#budget_day").val();
	if(!budget_day){
		budget_day='';
		$("#error_tip").html("提示：请输入日投放量及单位！！");
		return;
		
	}
	
	var budget_type=$("#budget_type_for_sel select").val();
	if(!budget_type){
		budget_type='';
		$("#error_tip").html("提示：请输入日投放量及单位！！");
		return;
	}
	
	var blance_mode=$("#blance_mode").val();
	if(!blance_mode){
		blance_mode='';
		$("#error_tip").html("提示：请选择结算方式！！");
		return;
	}
	
	var blance_price=$("#blance_price").val();
	if(!blance_price){
		blance_price='';
		$("#error_tip").html("提示：请输入结算单价！！");
		return;
	}

	$.each($("#tb input:checkbox[checked][@value]"), function(i, item){
		var id=$(item).attr("value");
		var name=$(item).attr("objname");
		var page_type_value=$("#selTable input[name='page_type']:checked").val();
		var page_type_name=$("#selTable input[name='page_type']:checked").attr("sname");
		addAd(id,page_type_value,name,page_type_name,sche_start_time,sche_end_time,package_id,budget_day,budget_type,blance_mode,blance_price);
		
	});
	
}

//更新广告
function updateAd(id){
	var param_data='id='+id;
	var start_time=$('#'+id+'_start_time').val();
	if(start_time){
		param_data=param_data+'&start_time='+start_time;
	}
	
	var end_time=$('#'+id+'_end_time').val();
	if(end_time){
		param_data=param_data+'&end_time='+end_time;
	}
	
	var package_id=$('#'+id+'_package_id select').val();
	if(package_id){
		param_data=param_data+'&package_id='+package_id;
	}
	var budget_day=$('#'+id+'_budget_day').val();
	if(budget_day){
		param_data=param_data+'&budget_day='+budget_day;
	}
	
	var budget_type=$('#'+id+'_budget_type select').val();
	if(budget_type){
		param_data=param_data+'&budget_type='+budget_type;
	}
	
	var blance_price=$('#'+id+'_blance_price').val();
	if(blance_price){
		param_data=param_data+'&blance_price='+blance_price;
	}
	var blance_mode=$('#'+id+'_blance_mode select').val();
	if(blance_mode){
		param_data=param_data+'&blance_mode='+blance_mode;
	}else{
		alert("结算方式不能为空！！");
		return;
	}
	param_data=param_data+'&t'+(new Date()).getTime();
	$.ajax({
		url:'${pageContext.request.contextPath}/manage!updateAdChannel.do',
		type:'POST',
		data:param_data,
		dataType:'text',
		beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status==1){
					alert("保存成功！！");
				}else if(dataObj.status=-1){
					alert("操作失败，请重试！！");
				}else{
					alert("操作失败，请重试！！");
				}
			}else{
				alert("操作失败，请重试！！");
			}
		}
	});
	
}


function insertRow(id,app_id,name,page_type,page_type_name,sche_start_time,sche_end_time,budget_day,blance_price){
	var str='<tr style="text-align: center;" id="tr_'+id+'">'
	+'<td>'+app_id+'<input type="hidden" id="app_type_'+app_id+'_'+page_type+'" value="true"></td><td>'+name+'</td><td>'+page_type_name+'</td><td colspan="1">'
	+'<input type="text" name="sche_start_time" style="width:100px;" id="'+id+'_start_time" class="Wdate" readonly="readonly" onClick="WdatePicker({dateFmt:\'yyyy-MM-dd\',maxDate:\'#F{$dp.$D(\\\''+id+'_end_time\\\')}\',minDate:\'#F{$dp.$D(\\\'min_date\\\')}\'});" value="'+sche_start_time+'" />至'
	+'<input type="text" name="sche_end_time" style="width:100px;" id="'+id+'_end_time" class="Wdate" readonly="readonly" onClick="WdatePicker({dateFmt:\'yyyy-MM-dd\',maxDate:\'#F{$dp.$D(\\\'max_date\\\')}\',minDate:\'#F{$dp.$D(\\\''+id+'_start_time\\\')}\'});" value="'+sche_end_time+'" /></td>'
	+'<td><span id="'+id+'_package_id">'
	+$("#package_for_select").html()
	+'</span></td>'
	+'<td><input type="text" id="'+id+'_budget_day" name="budget_day" maxlength="10" style="width: 40px;" value="'+budget_day+'" /></td>'
	+'<td><span id="'+id+'_budget_type">'+$("#budget_type_for_sel").html()
	+'</span></td>'
	+'<td><input type="text" id="'+id+'_blance_price" name="settlement_price" style="width:40px;" maxlength="5" value="'+blance_price+'" /></td>'
	+'<td><span id="'+id+'_blance_mode">'
	+$("#blance_mode_for_sel").html()
	+'</td>'
	+'<td><a href="javascript:void(0);" onclick="updateAd('+id+');" >保存</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="delAd('+id+');" >删除</a>'
	+'</td></tr>';
	$("#table_list tr:last-child").after(str);
	$("#"+id+"_package_id select").val($("#package_for_select select").val());
	$("#"+id+"_budget_type select").val($("#budget_type_for_sel select").val());
	$("#"+id+"_blance_mode select").val($("#blance_mode_for_sel select").val());
	
}




function addAd(id,page_type,name,page_type_name,sche_start_time,sche_end_time,package_id,budget_day,budget_type,blance_mode,blance_price){
	//判断所选的组合是否已经存在app_type_${ad.app_id}_${ad.type_id==0}
	if($('#app_type_'+id+'_'+page_type)&&$('#app_type_'+id+'_'+page_type).val()=='true'){
		if(confirm("渠道id为"+id+"的"+page_type_name+"形式的投放已存在,是否继续？")){
		}else{
			return;
		}
	}
	var param_data='placement_id=${placement_id}&appid='+id+'&page_type='+page_type+'&start_time='+sche_start_time;
	param_data=param_data+'&end_time='+sche_end_time+'&package_id='+package_id+'&budget_day='+budget_day+'&budget_type='+budget_type;
	param_data=param_data+'&blance_mode='+blance_mode+'&blance_price='+blance_price+'&t='+(new Date()).getTime();
	$.ajax({
		url:'${pageContext.request.contextPath}/manage!addAdChannel.do',
		type:'POST',
		data:param_data,
		dataType:'text',
		beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			insertRow(data,id,name,page_type,page_type_name,sche_start_time,sche_end_time,budget_day,blance_price);
		}
	});
}


function delAd(id){
	if(window.confirm("确定删除该条广告！！")){
		$.ajax({
			url:'${pageContext.request.contextPath}/manage!delAd.do',
			type:'POST',
			data:'id='+id,
			dataType:'text',
			beforeSend:function(){
					//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
			},
			success:function(data){
				$('#tr_'+id).remove();
			}
		});
	}	
}



function query_channel(){
	var channel=$("#channel_id_name").val();
	
		$.ajax({
			url:'${pageContext.request.contextPath}/manage!adConfigureChannel_query.do',
			type:'POST',
			data:'placement_id=${placement_id}&channel='+channel,
			dataType:'text',
			beforeSend:function(){
					//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
			},
			success:function(data){
				$('.row').remove();
				var dataObj=eval("("+data+")");
				var str="";
				for(var i=0;i<dataObj.length;i++){
					 str=str+'<tr style="text-align: center;" class="row"><td align="center"><input id="checkbox" name="media" type="checkbox" value="'+dataObj[i].id+'" objname="'+dataObj[i].name+'" /></td><td>'+dataObj[i].id+'</td><td>'+dataObj[i].name+'</td></tr>';
					
				}
				$("#tb tr:last-child").after(str);
			}
		});
}
</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<div style="display: none;">
					<input type="hidden" id="min_date" value="<fmt:formatDate value="${placement.plan_start}" type="date" dateStyle="long" pattern="yyyy-MM-dd" />" />
					<input type="hidden" id="max_date" value="<fmt:formatDate value="${placement.plan_end}" type="date" dateStyle="long" pattern="yyyy-MM-dd" />" />
				</div>
				<div style="display: block;height: 200px;overflow: hidden;width: 1000px;" >
					<div style="display: block;width: 400px;height: 200px;float: left; overflow-y: scroll;overflow-x:hidden;">
						<div style="display: block;">
							渠道名称/ID<input type="text" name="channel" id="channel_id_name"/>
								<input  type="button" value="查询" onclick="query_channel();" />
							<input type="hidden" name="os" value="os" />
						</div>
						<table class="table_bod1 font_stat" style="border: none;width:380px;" id="tb" >
								<tr class="tr_td">
									<td>
										<input id="checkbox" type="checkbox" onclick="selectAll(this.checked);" />全选
									</td>
									<td>渠道ID</td>
									<td>渠道名称</td>
								</tr>
								<c:forEach items="${list}" var="k">
								<tr style="text-align: center;" class="row">
									<td align="center">
										<input id="checkbox" name="media" type="checkbox" value="${k.id}" objname="${k.name}" /></td>
									<td>${k.id}</td>
									<td>${k.name}</td>
								</tr>
								</c:forEach>
								<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
								</tr>
								</c:if>
							</table>
					</div>
					<div style="display: block;width: 580px;height: 200px;float:right;">
						<div style="height: 150px;">
							<table id="selTable">
								<tr>
									<td width="103">投放形式</td>
									<td width="560">
										<input name="page_type" type="radio" checked="checked" value="0" sname="积分墙" />积分墙
										<input name="page_type" type="radio" value="1" sname="推荐墙 " />推荐墙 
										<input name="page_type" type="radio" value="4" sname="Banner"/>Banner
										<input name="page_type" type="radio" value="5" sname="插屏"/>插屏</td>
									<td width="343">&nbsp;</td>
								</tr>
								<tr>
									<td width="83px;">排期</td>
									<td width="380px;">
										<input type="text" id="sche_start_time" name="sche_start_time" value="<fmt:formatDate value="${placement.plan_start}" type="date" dateStyle="long" pattern="yyyy-MM-dd" />" style="width:100px;" id="${ad.id}_start_time" class="Wdate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'sche_end_time\')}',minDate:'#F{$dp.$D(\'min_date\')}'});" />
										至
										<input type="text" id="sche_end_time" name="sche_end_time" style="width:100px;" value="<fmt:formatDate value="${placement.plan_end}" type="date" dateStyle="long" pattern="yyyy-MM-dd" />" id="${ad.id}_end_time" class="Wdate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'max_date\')}',minDate:'#F{$dp.$D(\'sche_start_time\')}'});" />
									</td>
								</tr>
								<tr>
									<td width="83px;">包号</td>
									<td width="360px;" id="package_for_select">
										<span id="package_for_select">
											<select name="settlement_mode" style="width: 150px;">
												<option value="" >请选择</option>
												<c:forEach items="${packages}" var="entry">
												<option value="${entry.id}"	<c:if test="${ad.package_id eq entry.id}">   selected="selected" </c:if>>${entry.code}</option>
												</c:forEach>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td width="83px;">日投放量</td>
									<td width="360px;">
										<input type="text" id="budget_day" name="budget_day" maxlength="10" style="width: 40px;" value="${ad.budget_day}" />
										<span id="budget_type_for_sel">
											<select name="budget_type" style="width: 53px;">
												<option value="">请选择</option>
												<option value="A" >A</option>
												<option value="D" >D</option>
												<option value="M" >M</option>
												<option value="C" >C</option>
												<option value="T" >T</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td width="83px;">结算方式</td>
									<td width="360px;">
										<span id="blance_mode_for_sel">
											<select name="settlement_mode" id="blance_mode" style="width: 56px;">
												<option value="">请选择</option>
												<option value="CPA">CPA</option>
												<option value="CPD">CPD</option>
												<option value="CPC">CPC</option>
												<option value="CPM">CPM</option>
												<option value="CPT">CPT</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<td width="83px;">结算单价</td>
									<td width="360px;">
										<input type="text" id="blance_price" name="settlement_price" style="width:100px;" maxlength="5" value="${ad.blance_price}" />
									</td>
								</tr>
							</table>
						</div>
						<div style="background-color: red;">
							<span style="width: 30px;float: right;right: 200px;padding-right: 200px; ">
								<input type="button" value="添加到投放列表" onclick="addAdvertiseList('${k.id}')" />
							</span>
							<span id="error_tip" style="display: block;width: 250px;float:right;color: red;">
							</span>
						</div>
					</div>
				</div>
				<div style="display: block;padding-top: 10px;height: 300px;width:920px;overflow: hidden;overflow-y:scroll; ">
					<form action="" method="post" id="saveAdvertise">
					<input type="hidden" name="actId" id="actId" value="${actId}" />
					<table  cellpadding="0" cellspacing="" class="table_bod1 font_stat" id="table_list">
						<tr class="tr_td">
							<td width="50px;">渠道编号</td>
							<td width="100px;">渠道名称</td>
							<td width="50px;">投放形式</td>
							<td width="250px;">排期</td>
							<td width="50px;">包渠道号</td>
							<td width="50px;">日投放量</td>
							<td width="50px;">单位</td>
							<td width="50px;">结算单价</td>
							<td width="50px;">结算方式</td>
							<td>操作</td>
						</tr>
						<c:forEach items="${adList}" var="ad">
						<tr style="text-align: center;" id="tr_${ad.id}">
							<td>
								${ad.channel_id}
								<input type="hidden" id="app_type_${ad.channel_id}_${ad.type_id}" value="true" />
							</td>
							<td>${ad.channel_name}</td>
							<td>
								 <c:if test="${ad.type_id==0}">
								 	积分墙
								</c:if>
								<c:if test="${ad.type_id==1}">推荐墙</c:if>
								<c:if test="${ad.type_id==3}">九宫格</c:if>
								<c:if test="${ad.type_id==4}">BANNER</c:if>
								<c:if test="${ad.type_id==5}">插屏</c:if>
								
							</td>
							<td colspan="1">
								<input type="text" name="sche_start_time" value="${ad.start_time}" style="width:100px;" id="${ad.id}_start_time" class="Wdate" readonly="readonly" onclick="WdatePicker();" />
								至
								<input type="text" name="sche_end_time" style="width:100px;" value="${ad.end_time}" id="${ad.id}_end_time" class="Wdate" readonly="readonly" onclick="WdatePicker();" />
							</td>
							<td>
								<span id="${ad.id}_package_id">
									<select name="package_id" style="width:150px;">
										<option value="" >请选择</option>
											<c:forEach items="${packages}" var="entry">
												<option value="${entry.id}"	<c:if test="${ad.package_id eq entry.id}">   selected="selected" </c:if>>${entry.code}</option>
											</c:forEach>
									</select>
								</span>
							</td>
							<td>
								<input type="text" id="${ad.id}_budget_day" name="budget_day" maxlength="10" style="width: 40px;" value="${ad.budget_day}" />
							</td>
							<td>
								<span id="${ad.id}_budget_type">
										<select name="budget_type">
											<option value="">请选择</option>
											<option value="A" <c:if test="${ad.budget_type=='A'}">selected="selected"</c:if>>A</option>
											<option value="D" <c:if test="${ad.budget_type=='D'}">selected="selected"</c:if>>D</option>
											<option value="M" <c:if test="${ad.budget_type=='M'}">selected="selected"</c:if>>M</option>
											<option value="C" <c:if test="${ad.budget_type=='C'}">selected="selected"</c:if>>C</option>
											<option value="T" <c:if test="${ad.budget_type=='T'}">selected="selected"</c:if>>T</option>
										</select>
								</span>
							</td>
							<td>
								<input type="text" id="${ad.id}_blance_price" name="settlement_price" style="width:40px;" maxlength="5" value="${ad.blance_price}" />
							</td>
							<td>
								<span id="${ad.id}_blance_mode">
										<select name="settlement_mode" >
											<option value="" >请选择</option>
											<option value="CPA" <c:if test="${ad.blance_mode eq 'CPA'}">selected="selected"</c:if>>CPA</option>
											<option value="CPD" <c:if test="${ad.blance_mode eq 'CPD'}">selected="selected"</c:if>>CPD</option>
											<option value="CPC" <c:if test="${ad.blance_mode eq 'CPC'}">selected="selected"</c:if>>CPC</option>
											<option value="CPM" <c:if test="${ad.blance_mode eq 'CPM'}">selected="selected"</c:if>>CPM</option>
											<option value="CPT" <c:if test="${ad.blance_mode eq 'CPT'}">selected="selected"</c:if>>CPT</option>
										</select>
								</span>
							</td>
							<td>
								<a href="javascript:void(0);" onclick="updateAd('${ad.id}');" >保存</a>&nbsp;&nbsp;
								<a href="javascript:void(0);"  onclick="delAd('${ad.id}');" style="cursor: pointer;" >删除</a>
							</td>
						</tr>
						</c:forEach>
					</table>
				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>