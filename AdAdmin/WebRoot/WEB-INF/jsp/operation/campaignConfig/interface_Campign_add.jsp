<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control"  content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ios广告接口配置添加</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"	charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	dg.addBtn("save", "保存", function() {
		if(vaildateForm("colloca")){
			$("#colloca").ajaxSubmit(function(data) {
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("操作成功！！");
						dg.curWin.refresh();
					}else if(dataObj.status=-1){
						alert("操作失败"+dataObj.error);
						dg.curWin.refresh();
					}else{
						alert("操作失败，请重试！！");
						dg.curWin.refresh();
					}
				}else{
					alert("操作失败，请重试！！");
					dg.curWin.refresh();
				}

			});
		}
	});
</script>
</head>
<body>
	<form name="colloca" id="colloca" method="post" action="manage!saveCampaignConfig.do">
		 <fieldset class="search_fieldset" >
			<legend>配置信息 </legend>
					<table width="100%" >
						<tr>
							<td align="right">AD_KEY ：</td>
							<td>
								<input type="text" name="ad_key" id="ad_key"/>*
							</td>
						</tr>
						<tr>
							<td align="right">广告名称 ：</td>
							<td>
								<input type="text" name="ad_name" id="ad_name" />*
							</td>
						</tr>
						<tr>
							<td align="right">URL ：</td>
							<td>
								<input type="text" name="url" id="adid_para" size="70"/>
							</td>
						</tr>
						<tr>
							<td align="right">AD_KEY参数名称 ：</td>
							<td  >
								<input type="text" name="adid_str" id=adid_str/>
							</td>
						</tr>
						<tr>
							<td align="right">广告单价 ：</td>
							<td>
								<input type="text" name="adv_price" id=adv_price  value=""/>*(0.0)
							</td>
						</tr>
						<tr>
							<td align="right">设备参数名称：</td>
							<td  >
								<input type="text" name="deviceid_para" id="deviceid_para"/>
							</td>
						</tr>
						<tr>
							<td align="right">MAC参数名称：</td>
							<td  >
								<input type="text" name="udid" id="udid"/>
							</td>
						</tr>
						<tr>
							<td align="right">扩展参数 ：</td>
							<td>
								<input type="text" name="sourse_str" id="sourse_str" size="70"/>
							</td>
						</tr>
						<tr>
							<td align="right">时间戳参数名 ：</td>
							<td >
								<input type="text" name="eventtime_para" id="eventtime_para" />
							</td>
						</tr>
						
						<tr>
							<td align="right">请求方式 ：</td>
							<td >
								<input type="text" name="send_type" id="send_type"/>
							</td>
						</tr>
						<!-- tr>
							<td align="right">渠道名称 ：</td>
							<td >
								<input type="text" name="source" id="source"/>
							</td>
						</tr-->
						<tr>
							<td align="right">ip参数名称 ：</td>
							<td >
								<input type="text" name="client_ip" id="client_ip"/>
							</td>
						</tr>
						<tr>
							<td align="right">OPENUDID参数名称 ：</td>
							<td >
								<input type="text" name="openudid" id="openudid"/>
							</td>
						</tr>
						<tr>
							<td align="right">IDFA参数名：</td>
							<td >
								<input type="text" name="idfa" id="idfa" />
							</td>
						</tr>
						<!-- tr>
							<td align="right">IDFV参数名 ：</td>
							<td >
								<input type="text" name="idfv" id="idfv"/>
							</td>
						</tr-->
						<tr>
							<td align="right">回调接口参数名 ：</td>
							<td >
								<input type="text" name="callback" id="callback" />
							</td>
						</tr>
						<tr>
							<td align="right">投放ID ：</td>
							<td >
								<input type="text" name="placement_id" id="placement_id"/>
							</td>
						</tr>
						<tr>
							<td align="right">发送请求服务名 ：</td>
							<td >
								<input type="text" name="service_name" id="service_name"/>
							</td>
						</tr>
						<tr>
							<td align="right">激活通过率 (0.0-1.0)：</td>
							<td >
								<input type="text" name="pass_rate" id="pass_rate" />
							</td>
						</tr>
					</table>
					</fieldset>
				</form>
</body>
</html>