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
<script type="text/javascript">

/**
 * 全选
 **/
function selectAll(value){
	var b=value;
	$("#tb input:checkbox[name='app']").attr("checked", b);
}

$(document).ready(function(){
	selectAll(0);
	$("#page_type_div input:radio[name='page_type']").click(function (){
		var param_data='os=${placement.os}'+'&type_id='+$(this).val();
		$.ajax({
			url:'${pageContext.request.contextPath}/manage!selectApp.do',
			type:'POST',
			data:param_data,
			dataType:'text',
			beforeSend:function(){
					//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
			},
			success:function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					$(".sel_app").remove();
					if(dataObj.length>0){
						var str="";
						for(var i=0;i<dataObj.length;i++){
							var temp='<tr style="text-align: center;" class="sel_app">'
								+'<td align="center"><input id="checkbox" name="app" type="checkbox" checked="checked" value="'+dataObj[i].id+'" objname="'+dataObj[i].name+'" /></td><td>'+dataObj[i].id+'</td><td>'+dataObj[i].name+'</td></tr>';
							if(str){
								str=str+temp;
							}else{
								str=temp;
							}
						}
						$("#tb tr:last-child").after(str);
					}
				}
				
			}
		});
	});
	
	var type_str='${page_type}';
	var wall_type_1=0;
	var wall_type_2=1;
	var wall_type_3=4;
	var wall_type_4=5;
	var arr=type_str.split(",");
	var has_sel=false;
	for(var i=1;i<5;i++){
		var b=false;
		for(var j=0;j<arr.length;j++){
			if(eval("wall_type_"+i)==arr[j]){
				b=true;
				break;
			}
		}
		if(!b){
			$("#page_type_"+i).hide();
		}else{
			if(!has_sel){
				$("#page_type_"+i+" input:radio").attr("checked","checked");//="checked"
				has_sel=true;
			}
		}
	}
});


//添加到投放列表
function addAdvertiseList(){
	$("#error_tip").html("");
	if($("#tb input:checkbox[checked][@value][name=app]").length<=0){
		$("#error_tip").html("提示：请选择媒体！！");
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
	
	$.each($("#tb input:checkbox[checked][@value][name=app]"), function(i, item){
		var id=$(item).attr("value");
		var name=$(item).attr("objname");
		var page_type_value=$("#selTable input[name='page_type']:checked").val();
		var page_type_name=$("#selTable input[name='page_type']:checked").attr("sname");
		addAd(id,page_type_value,name,page_type_name,package_id,budget_day,budget_type,blance_mode,blance_price);
	});
}

//更新广告
function updateAd(id){
	var param_data='id='+id;
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
		url:'${pageContext.request.contextPath}/manage!updateAd.do',
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


function insertRow(id,app_id,name,page_type,page_type_name,budget_day,blance_price){
	var str='<tr style="text-align: center;" id="tr_'+id+'">'
	+'<td>'+app_id+'<input type="hidden" id="app_type_'+app_id+'_'+page_type+'" value="true"></td><td>'+name+'</td><td>'+page_type_name+'</td>'
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
	+'<td><a href="javascript:void(0);" onclick="updateAd('+id+');">保存</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="delAd('+id+');" >删除</a>'
	+'</td></tr>';
	$("#table_list tr:last-child").after(str);
	$("#"+id+"_package_id select").val($("#package_for_select select").val());
	$("#"+id+"_budget_type select").val($("#budget_type_for_sel select").val());
	$("#"+id+"_blance_mode select").val($("#blance_mode_for_sel select").val());
}

function addAd(id,page_type,name,page_type_name,package_id,budget_day,budget_type,blance_mode,blance_price){
	//判断所选的组合是否已经存在app_type_${ad.app_id}_${ad.type_id==0}
	if($('#app_type_'+id+'_'+page_type)&&$('#app_type_'+id+'_'+page_type).val()=='true'){
		alert("应用id为"+id+"的"+page_type_name+"形式的投放已存在");
		return;
	}
	
	var param_data='placement_id=${placement.id}&appid='+id+'&page_type='+page_type;
	param_data=param_data+'&package_id='+package_id+'&budget_day='+budget_day+'&budget_type='+budget_type;
	param_data=param_data+'&blance_mode='+blance_mode+'&blance_price='+blance_price+'&t='+(new Date()).getTime();
	$.ajax({
		url:'${pageContext.request.contextPath}/manage!addAd.do',
		type:'POST',
		data:param_data,
		dataType:'text',
		beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			insertRow(data,id,name,page_type,page_type_name,budget_day,blance_price);
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

function getSelectPackageCode(){
	var code;
	$.each($("#package_for_select option[selected='selected']"), function(i, item){
		code=$(this).html();
	});
	return code;
}
</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<div style="display: none;">
				</div>
				<div style="display: block;width: 1020px;height: 200px;overflow: hidden;" >
					<div style="display:block;position: relative;height: 200px;">
						<table width="400px;" class="table_bod1 font_stat" style="border: none;" id="tb">
							<tr class="tr_td">
								<td>
									<input id="checkbox" type="checkbox" value="${k.id}" checked="checked" onclick="selectAll(this.checked);" />全选
								</td>
								<td>应用ID</td>
								<td>应用名称</td>
							</tr>
							<c:forEach items="${media_list}" var="k">
							<tr style="text-align: center;" class="sel_app">
								<td align="center">
									<input id="checkbox" name="app" type="checkbox" value="${k.id}" objname="${k.name}"  />
								</td>
								<td>${k.id}</td>
								<td>${k.name}</td>
							</tr>
							</c:forEach>
							<c:if test="${empty media_list}">
							<tr class="sel_app">
								<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
							</tr>
							</c:if>
						</table>
					</div>
					<div style="width: 600px;display: block;position: relative;left: 420px;top: -200px;">
						<table id="selTable">
							<tr>
								<td width="83px;">投放形式</td>
								<td width="380px;">
									<div id="page_type_div">
										<span id="page_type_1"><input name="page_type" type="radio" checked="checked" value="0" sname="积分墙" />积分墙</span>
										<span id="page_type_2"><input name="page_type" type="radio" value="1" sname="推荐墙 " />推荐墙</span>
										<span id="page_type_3"><input name="page_type" type="radio" value="4" sname="Banner"/>Banner</span>
										<span id="page_type_4"><input name="page_type" type="radio" value="5" sname="插屏 "/>插屏</span>
									</div>
								</td>
								<td width="343">&nbsp;</td>
							</tr>
							<tr>
								<td width="83px;">包号</td>
								<td width="360px;" id="package_for_select">
									<span id="package_for_select">
										<select name="settlement_mode">
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
						<div style="width: 380px;">
							<span id="error_tip" style="color: red;display: block;width: 250px;float: left;">
							</span>
							<span style="width: 30px;float: right;"><input type="button" value="添加到投放列表" onclick="addAdvertiseList()" /></span>
						</div>
					</div>
				</div>
				<div style="display: block;width: 920px;height: 290px;overflow-y: scroll;float: left;position: static;">
						<table cellpadding="0" cellspacing="" class="table_bod1 font_stat" id="table_list" width="100%">
							<tr class="tr_td">
								<td width="50px;">应用ID</td>
								<td width="120px;">应用名称</td>
								<td width="50px;">形式</td>
								<td width="50px;">包号</td>
								<td width="50px;">日投放量</td>
								<td width="50px;">单位</td>
								<td width="25px;">单价</td>
								<td width="50px;">结算方式</td>
								<td width="100px;">操作</td>
							</tr>
							<c:forEach items="${ad_media_list}" var="ad">
							<tr style="text-align: center;" id="tr_${ad.id}">
								<td>
									${ad.app_id}
									<input type="hidden" id="app_type_${ad.app_id}_${ad.type_id}" value="true" />
								</td>
								<td>${ad.app_name}</td>
								<td>
									 <c:if test="${ad.type_id==0}">
									 	积分墙
									</c:if>
									<c:if test="${ad.type_id==1}">推荐墙</c:if>
									<c:if test="${ad.type_id==4}">BANNER</c:if>
									<c:if test="${ad.type_id==5}">插屏</c:if>
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
									<input type="button" value="保存" onclick="updateAd('${ad.id}');" style="cursor: pointer;" />
									<input type="button" value="删除" onclick="delAd('${ad.id}');" style="cursor: pointer;" <c:if test="${placement_status!=-40}">disabled="disabled" title="已上线,不能删除！" </c:if>  />
								</td>
							</tr>
							</c:forEach>
						</table>
					</div>
			</div>
		</div>
	</div>
</body>
</html>