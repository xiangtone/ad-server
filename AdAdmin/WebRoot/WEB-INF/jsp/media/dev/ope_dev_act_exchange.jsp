<%@ page language="java" import="java.util.*,cn.adwalker.util.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者应用审核</title>
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
	src="${pageContext.request.contextPath}/js/upload/uploadCommon.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			getCategoryById('${app.categoryId}');//获取默认分类
		});
		function applyDevAppManager(status,appId){
			var url = "manage!auditDevApp.do?status="+status+"&appId="+appId;
			if(confirm("确认要执行该操作吗？")){
				if(status=="NOTPASS"){
					var reason = window.prompt("请填写驳回理由", "");
					if(reason){	
					reason = encodeURIComponent(reason);			
					var url =url+"&reason="+reason
						//window.location.href=encodeURI(url);//转码
						jump(encodeURI(url));
					}else{
						return;
					}
				}else{
					//window.location.href=url;//不是驳回直接跳转
					jump(url);
				}
			}//取消操作
		}
		function jump(url){
			var a = document.createElement("a");
			if(!a.click) {
	     		 window.location = url;
	     		 return;
	   		 }
	   		a.setAttribute("href", url);
	   		a.style.display = "none";
	   		document.body.appendChild(a);
	   		a.click();
		}
		
		function checkbudgetN(){
			document.updateAppExchangeManage.dailyBudgetLin.disabled=true;
			document.getElementById("dailyBudget").value="-1";
		}
		function checkbudgetD(){
			
			document.updateAppExchangeManage.dailyBudgetLin.disabled=false;
		}
		
		function fuzhi(){
			if(isNaN(document.getElementById("dailyBudgetLin").value)){
				document.getElementById("dailyBudgetLin").value="";
			}else{
				document.getElementById("dailyBudget").value = document.getElementById("dailyBudgetLin").value;
			}
			
		}
</script>
</head>

<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<div class="bord_bom1px">开发者审核</div>
				<div class="back_link">
					<a href="manage!appList.do">应用管理</a>&nbsp;&nbsp;${app.name
					}
				</div>
				<table class="mar_bom10 margtop10">
					<tr>
						<td>开发者：</td>
						<td>${dev.email }</td>
						<td>联系人：</td>
						<td>${dev.realName }</td>
					</tr>
					<tr>
						<td>提交时间：</td>
						<td><fmt:formatDate value="${app.createTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>上线时间：</td>
						<td><fmt:formatDate value="${app.releaseTime}" type="date"
								dateStyle="medium" /> <c:if test="${app.releaseTime == null}">
  				--
  		</c:if></td>
					</tr>
					<tr>
						<td>活跃指数</td>
						<td><input type="text" value="1" name="" />
						</td>
						<td></td>
					</tr>
					<tr>
						<td>推广应用黑名单</td>
						<td><input type="text" value="" name="" />
						</td>
						<td style="color: red;">输入开发者应用ID，以空格间隔</td>
					</tr>
				</table>
				<form name="updateAppExchangeManage"
					action="manage!updateAppExchangeManage.do" id="updateAppManage"
					method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" value="${app.id}" />
					<input type="hidden" name="devEmail" value="${app.devEmail}" />
					<input type="hidden" name="developerId" value="${dev.id}" />
					<input type="hidden" name="ceid" value="${app.categoryId}" />
					<input type="hidden" name="status" value="${app.status}"/>
					<div class="bord_bom1px">应用详情</div>
					<div>开发者ID：${dev.id}</div>
					<div>应用ID：${app.id }</div>
					<div>应用KEY:${app.appkey }</div>
					<div class="appFile">
						应用程序： <span class="uploadMsg"> <c:if
								test="${fileUrl ne null && fileUrl ne ''}">
								<a href="${fileUrl}" target="_blank">${devAppVo.fileName
									}&nbsp;</a>
							</c:if> <c:if test="${fileUrl eq null || fileUrl eq ''}">
				${app.name }&nbsp;(未上传应用)
	</c:if> </span><br />
						<%--<ax:head/>
	<ax:body maxFileN="1" maxAllFileSize="104857600" extensionName="*.apk;*.sis;*.sisx;" showLogoTxt="false" opeId="${opeId}" appId="${app.id }" appType="dev"/>
	<!--<input name="appFile" type="file" class="file" id="appFile" reg=".*sis|sisx|jar|apk$" tip="只允许sis,sisx,jar,apk文件"/>
	<span class="tip_file"></span>
-->
	<iframe frameborder="0" height="120" width="390" src="<%=ConfigUtil.getString("upload.file.path") %>qsl=1&fe=<%=ConfigUtil.getString("upload.file.type") %>&po=<%=ConfigUtil.getString("upload.file.rename") %>&pa=${dev.email}/${app.id}/&ic=1&fn=appUpdate"></iframe>
--%>
						<div>
							应用状态：
							<c:if test="${app.status == STATUS_NOTPASS}">
  				${app.appStatusName }&nbsp;&nbsp;驳回理由：${app.checkMsg }
  			</c:if>
							<c:if test="${app.status != STATUS_NOTPASS}">
  				${app.appStatusName }
  			</c:if>
						</div>
						<div class="bord_bom1px">推广设置</div>

						<table>
							<tr>
								<td>预算金额:</td>
								<td>${exchangeDetailVo.generalBudget }易币</td>
								<td></td>
							</tr>

							<tr>
								<td>信用额度:</td>
								<td colspan="2">
									<table border="0">
										<tr>
											<td style="width: 150px;">${exchangeInfoVo.creditLimit
												}易币</td>
											<td style="width: 80px;">信用余额:</td>
											<td>${exchangeInfoVo.accountBalance }易币</td>
										</tr>
									</table></td>

							</tr>
							<%-- <tr>
		<td>推广单价:</td>
		<td>${exchangeDetailVo.price }易币</td>
		<td></td>
	</tr> --%>
							<tr>
								<td>推广单价:</td>
								<td><input type="text" name="preModifiedPrice"
									id="preModifiedPrice"
									onkeyup="if(isNaN(value))execCommand('undo')"
									onafterpaste="if(isNaN(value))execCommand('undo')"
									value="${exchangeDetailVo.price }" />易币</td>
								<td style="color: red;">最低0.2易币</td>
							</tr>
							<tr>
								<td>日预算:</td>
								<td colspan="2">
									<input type="radio" name="budgetN" value="1"
									<c:if test="${exchangeDetailVo.dailyBudget == -1 || exchangeDetailVo.dailyBudget == 0|| exchangeDetailVo.dailyBudget ==null  }">checked="checked"</c:if>
									onclick="checkbudgetN();">不限制 <input type="radio"
									name="budgetN" value="2"
									<c:if test="${exchangeDetailVo.dailyBudget  >0 }">checked="checked"</c:if>
									onclick="checkbudgetD();">日预算 <c:if
										test="${exchangeDetailVo.dailyBudget == -1 || exchangeDetailVo.dailyBudget == 0 || exchangeDetailVo.dailyBudget == null}">
										<input type="hidden" name="dailyBudget" id="dailyBudget"
											value="-1" />

										<input type="text" class="inp-form" id="dailyBudgetLin"
											value="0" disabled="disabled" onkeyup="fuzhi()">
									</c:if> <c:if test="${exchangeDetailVo.dailyBudget >0 }">
										<input type="hidden" name="dailyBudget" id="dailyBudget"
											value="${exchangeDetailVo.dailyBudget }" />

										<input type="text" class="inp-form" id="dailyBudgetLin"
											value="${exchangeDetailVo.dailyBudget }" maxlength="9"
											onkeyup="fuzhi()">
									</c:if>
								</td>
								<td>
									<div id="name_error2" style="visibility: hidden; color: red">
										<div class="error-left"></div>
										<div class="error-inner">最小值0，最大值999999999易币</div>
									</div></td>
							</tr>
							<tr>
								<td>总预算:</td>
								<td>
									<input type="text" name="generalBudget" id="generalBudget" value="${exchangeDetailVo.generalBudget}"
									onkeyup="if(isNaN(value))execCommand('undo')"
									onafterpaste="if(isNaN(value))execCommand('undo')">易币</td>
								<td></td>
							</tr>


						</table>

						<div class="bord_bom1px">活动信息</div>
						<div>
							<input name="os" type="radio" value="android" checked="checked" />ANDROID
							<input name="os" type="radio" value="symbian" disabled />SYMBIAN
							<input name="os" type="radio" value="ios" disabled />IOS <input
								name="os" type="radio" value="wp7" disabled />WP7 暂时只支持android平台
						</div>
						<table>
							<tr>
								<td width="100" valign="top">应用名称：</td>
								<td class="name"><input disabled="disabled" name="name"
									id="name" type="text" reg="^(?=.*?\S)[\s\S]{0,20}$"
									tip="应用名不能为空" value="${app.name }" maxlength="20" />* <font
									color="red">${errMsg_appName}</font></td>
							</tr>
							<tr>
								<td valign="top"></td>
								<td>20个字符以内</td>
							</tr>
							<tr>
								<td valign="top">关键字：</td>
								<td class="keyword"><input disabled="disabled"
									name="keyword" id="keyword" type="text"
									reg="^[a-zA-Z0-9\u4e00-\u9fa5\,]{0,50}$"
									tip="请输入不超过50位的字符 多个关键字用英文逗号分开" value="${app.keyword }"
									maxlength="50" /></td>
							</tr>
							<tr>
								<td valign="top"></td>
								<td>多个关键字请用空格分隔</td>
							</tr>
							<tr>
								<td valign="top">简单介绍：</td>
								<td class="shortDesc"><input disabled="disabled"
									name="shortDesc" id="shortDesc" reg="^(?=.*?\S)[\s\S]{0,15}$"
									tip="简单介绍不能为空" type="text" value="${app.shortDesc }"
									maxlength="15" />* <font color="red">${errMsg_appShortDesc}</font>
								</td>
							</tr>
							<tr>
								<td valign="top"></td>
								<td>推广使用的文字描述，不超过15个汉字，用户将在广告墙中看到推广</td>
							</tr>
							<tr>
								<!-- 分类start -->
								<td valign="top">应用类别：</td>
								<td><select disabled="disabled" name="type" id="type"
									reg="[^0]">
										<option value="0">=请选择=</option>
										<option value="29">工具</option>
										<option value="30">生活</option>
										<option value="31">娱乐</option>
								</select> <select disabled="disabled" name="platform" id="platform"
									reg="[^0]">
										<option value="0">=请选择=</option>
								</select> <select disabled="disabled" name="categoryId" id="category"
									reg="[^0]">
										<option value="0">=请选择=</option>
								</select>* <!--<span class="tip_category"></span>
		-->
								</td>
								<!-- 分类end -->
							</tr>
							<tr>
								<td valign="top">详细介绍：</td>
								<td class="longDesc"><textarea disabled="disabled"
										name="longDesc" id="longDesc" reg="^(?=.*?\S)[\s\S]{0,100}$"
										tip="详细描述不能为空" cols="50" rows="5" maxlength="100">${app.longDesc }</textarea>*
									<font color="red">${errMsg_appLongDesc}</font></td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td valign="top">应用图标(135*135):</td>
								<td valign="top" class="icon"><c:if
										test="${iconUrl != null}">
										<img id="idImg" height="135" width="135"
											src="<escore:loadImg/>${iconUrl}" />
										<input disabled="disabled" name="icon" id="icon"
											reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$"
											tip="允许png,gif,jpg,jpeg文件" defvalue="${iconUrl}" type="file" />
									</c:if> <c:if test="${iconUrl == null}">
										<input disabled="disabled" name="icon" id="icon"
											reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$"
											tip="允许png,gif,jpg,jpeg文件" type="file" />
									</c:if></td>
							</tr>
							<tr>
								<td valign="top">应用截图(480*800):</td>
								<td valign="top" class="previewMap">

									<table width="100%" border="0">
										<tr>
											<c:forEach items="${previews}" var="pre" varStatus="index">
												<div>
													<td><a href="<escore:loadImg/>${pre.imgUrl}"
														target="_blank" border="0"> <img id="idImg" border="0"
															src="<escore:loadImg/>${pre.imgUrl}" width="120px"
															height="200px" />
													</a><br>
													</td>
												</div>
											</c:forEach>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td valign="top"></td>
								<td><input name="" type="submit" class="appSubmit"
									value="保存" /> <c:if test="${app.status == STATUS_UNCHECK}">
										<input type="button" value="审核通过"
											onclick="applyDevAppManager('PASS',${app.id })">
										<input type="button" value="审核不通过"
											onclick="applyDevAppManager('NOTPASS',${app.id })">
									</c:if> <c:if test="${app.status != STATUS_UNCHECK}">
										<input type="button" value="审核通过" disabled="disabled">
										<input type="button" value="审核不通过" disabled="disabled">
									</c:if></td>
							</tr>
						</table>
				</form>

			</div>
		</div>
	</div>
</body>
</html>