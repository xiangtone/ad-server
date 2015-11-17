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
<title>运营管理后台平台管理平台设置</title>
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>	
	
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"
	rel="stylesheet" type="text/css" />
		<style type="text/css">
			.reg_ok,.reg_no{ 
				background:url(${pageContext.request.contextPath}/images/icons-signup.png) no-repeat;
				padding-left:25px;
				display:inline-block; 
				height:23px;
				line-height:23px;
				font-size:14px;
			}
			.reg_ok{
				background-position:5px 1px;
				color:#090;
			}
			.reg_no{
				background-position:5px -50px;
				color:red;
			}
			.suggest_link {
				background-color: #FFFFFF;
				padding: 2px 6px 2px 6px;
			}
			.suggest_link_over {
				background-color: #E8F2FE;
				padding: 2px 6px 2px 6px;
			}
			
			.suggest_link_sel {
				background-color: #039CEA;
				padding: 2px 6px 2px 6px;
			}
			#search_suggest {
				position: absolute; 
				background-color: #FFFFFF; 
				width:298px;
				border:1px solid #000000;			
			}
			#search_suggest2 {
				position: absolute; 
				background-color: #FFFFFF; 
				width:298px;
				border:1px solid #000000;			
			}			
		</style>
<script type="text/javascript">
		function search_ActCycleClean() {
			var searchActCycleBegin = document
					.getElementById("searchActCycleBegin");
			var searchActCycleEnd = document.getElementById("searchActCycleEnd");
			searchActCycleBegin.value = "";
			searchActCycleEnd.value = "";
		}
		function entry_report() {
			$("#InsertPayingEntering").attr("action",
					"manage!InsertPayingEntering.do").submit();
		}
		function search_report() {
			$("#searchChannelPayingEntering").attr("action",
					"manage!ChannelPayingEntering.do").submit();
		}
		
</script>
</head>
<body>
	<div class="head_bj head_bj_bt">
		<div class="header rel admin_head">
			<div class="clearfix land_top">
				<div class="land_nav fl">运营管理&nbsp;>&nbsp;渠道付款录入</div>
			</div>
		</div>
	</div>
	<div class="main">
		<div class="content clearfix">
			<jsp:include page="../../../jsp/manage/common/manage_left.jsp"></jsp:include>
			<div class="admin_right">
				<div class="content_right content_new">
				<div class="">渠道付款</div>

				<form id="InsertPayingEntering" method="post">
					<fieldset>
						<legend>渠道付款录入</legend>
						<table>							
						      <div>
						   <font color=#ff0000 size=5>${message}</font>
					           </div>					
							<tr>
								<td><font style="margin-right: 14px;" color="blue">渠道名称</font></td>
								<td class="dev_email">
									<input id="dev_email" name="dev_email" type="text" maxlength="50"style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									  value="${channel.dev_email}"  reg="^\S{1,20}$" tip="请输入渠道名称(请勿输入空) !"/>
								</td>
							</tr>
							<tr>
							<td><font style="margin-right: 14px;" color="blue">证件类型</font></td>
		                      <td class="entryDevId"><select name="card_type"  id="card_type">
								<c:if test="${card_type==null ||cardType==1}">
									<option value="1" selected="selected">身份证</option>
									<option value="2">公司号</option>
								</c:if>
								<c:if test="${card_type==2}">
									<option value="1">身份证</option>
									<option value="2" selected="selected">公司号</option>
								</c:if>
							</select>
		                    </td>
							</tr>						
							<tr>
								<td><font style="margin-right: 11px;" color="blue">证件号</font></td>
								<td class="card_code"><input id="card_code"
									name="card_code" type="text"   maxlength="50"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									 value="${channel.card_code}"  reg="^\S{1,20}$" tip="请输入证件号(请勿输入空) !"/></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">开户名</font></td>
								<td class="kaihu_name"><input id="kaihu_name"
									name="kaihu_name" type="text" maxlength="50"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${channel.kaihu_name}" reg="^\S{1,20}$" tip="请输入开户名(请勿输入空) !"/></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">开户行城市</font></td>
								<td class="city"><input id="city"
									name="city" type="text" maxlength="50"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${channel.city}" reg="^\S{1,20}$" tip="请输入开户行城市(请勿输入空) !"/></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">银行名称</font></td>
								<td class="bank_name"><input id="bank_name"
									name="bank_name" type="text" maxlength="50"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${channel.bank_name}" reg="^\S{1,20}$" tip="请输入银行名称(请勿输入空) !"/></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">开户行名称</font></td>
								<td class="bank_subbranch"><input id="bank_subbranch"
									name="bank_subbranch" type="text" maxlength="50"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${channel.bank_subbranch}" reg="^\S{1,20}$" tip="请输入开户行名称(请勿输入空)!"/></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">银行账号</font></td>
								<td class="bank_account"><input id="bank_account"
									name="bank_account" type="text" maxlength="50"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${channel.bank_account}" reg="^\S{1,20}$" tip="请输入银行账户(请勿输入空) !"/></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">网站主合同号</font></td>
								<td class="contract_code"><input id="contract_code"
									name="contract_code" type="text" maxlength="50"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									 value="${channel.contract_code}"/></td>
							</tr>
							<tr>
								<td><font style="margin-right: 53px;" color="blue">付款金额</font></td>
								<td class="apply_money"><input id="apply_money"
									name="apply_money" type="text" maxlength="50"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${channel.apply_money}" reg="^\d+\.\d{2}$|^[0-9]+$|^\d+\.\d{1}$" tip="请输入正确金额(两位小数) !"/></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">付款说明</font></td>
								<td class="pay_desc"><input id="pay_desc"
									name="pay_desc" type="text" maxlength="50"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${channel.pay_desc}" reg="^\S{1,20}$" tip="请输入付款说明(请勿输入空) !"/></td>
									<td><input name="entryButton" id="entryButton"
									type="button" onclick="entry_report()" value="添加" /></td>
									<td><div>
						                       <font color=#ff0000 size=5>${sus_yes}</font>
						                 </div></td>
							</tr>
						</table>						
					</fieldset>
				</form>

				<form id="searchChannelPayingEntering">
					<fieldset>
						<legend>渠道付款查询</legend>
						<table>
							<tr>
								<td><font style="margin-right: 14px;" color="blue">申请单号</font></td>
								<td class="searchDevId"><input id="searchId" name="searchId"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo.id }"/></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">渠道名称</font></td>
								<td>
									<div>
										<input id="searchdev_email"  name="searchdev_email" type="text" maxlength="20" style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;" value="${vo.dev_email }"/>
									</div>									
								</td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">证件号</font></td>
								<td><input id="searchCard_code" name="searchCard_code"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo.card_code }" /></td>
							</tr>
							<tr>
								<td><font style="margin-right: 39px;" color="blue">开户名</font></td>
								<td><input id="searchKaihu_name" name="searchKaihu_name"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo.kaihu_name}" /></td>
							</tr>
							<tr>
								<td><font style="margin-right: 39px;" color="blue">合同号</font></td>
								<td><input id="searchContract_code" name="searchContract_code"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo.contract_code }" /></td>
							</tr>							
							<tr>
								<td><font style="margin-right: 25px;" color="blue">支付状态</font></td>
							<td>
							<select name="status">
									<c:choose>
										<c:when test="${vo.status == 0}">
											<option value="-3">全部</option>
											<option value="0" selected="selected">待审核</option>
											<option value="-1">通过</option>
											<option value="1">不通过</option>
											<option value="2">付款成功</option>
											<option value="-2">付款失败</option>
											
										</c:when>
										<c:when test="${vo.status == 1}">
											<option value="-3">全部</option>
											<option value="0">待审核</option>
											<option value="1" selected="selected">通过</option>
											<option value="-1" >不通过</option>
											<option value="2" >付款成功</option>
											<option value="-2">付款失败</option>								
										</c:when>
										<c:when test="${vo.status == -1}">
											<option value="-3">全部</option>
											<option value="">全部</option>
											<option value="0">待审核</option>
											<option value="1">通过</option>
											<option value="-1" selected="selected">不通过</option>
											<option value="2" >付款成功</option>
											<option value="-2">付款失败</option>									
										</c:when>		
										<c:when test="${vo.status == 2}">
											<option value="-3">全部</option>
											<option value="0">待审核</option>
											<option value="1">通过</option>
											<option value="-1">不通过</option>
											<option value="2" selected="selected">付款成功</option>
											<option value="-2">付款失败</option>											
										</c:when>		
										<c:when test="${vo.status == -2}">
											<option value="-3">全部</option>
											<option value="0">待审核</option>
											<option value="1">通过</option>
											<option value="-1">不通过</option>
											<option value="2">付款成功</option>
											<option value="-2" selected="selected">付款失败</option>										
										</c:when>										
										<c:otherwise>
											<option value="-3" selected="selected">全部</option>											
											<option value="0">待审核</option>
											<option value="1">通过</option>
											<option value="-1">不通过</option>
											<option value="2">付款成功</option>
											<option value="-2">付款失败</option>						
										</c:otherwise>
									</c:choose>
							</select>
							</td>
						</tr>
							</tr>
							<tr>
								<td><font style="margin-right: 39px;" color="blue">申请人</font></td>
								<td><input id="searchProposer" name="searchProposer"
									type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									value="${vo.proposer}" /></td>
							</tr>
						</table>
						<table>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">申请日期</font></td>
								<td><font style="margin-right: 5px;">从</font></td>
								<td><input type="text" id="searchSetDateBegin"
									name="searchSetDateBegin" maxlength="20"
									onfocus="WdatePicker()" class="Wdate" value="${vo.stat_date_begin }" /></td>
								<td><font style="margin-left: 5px; mamargin-right: 5px;">至</font></td>
								<td><input type="text" id="searchSetDateEnd"
									name="searchSetDateEnd" maxlength="20" onfocus="WdatePicker()"
									class="Wdate" value="${vo.stat_date_end }" /></td>
								<td><input name="searchSetDateClean"
									id="searchSetDateClean" type="button"
									style="margin-left: 10px;" onclick="search_SetDateClean()"
									value="清空" /></td>
									<td width="30px"></td>
									<td><input name="searchButton" id="searchButton"
									type="button" onclick="search_report()" value="查询" style="width:100px;height:50px"/></td>		
							</tr>							
						</table>						
					</fieldset>
				${pageInfo.pageInfoStr}
				</form>
				<table width="100%" cellpadding="0" cellspacing="1"
					class="table_bod1 font_stat">
					<tr class="tr_td">
						<td>申请单单号</td>
						<td>渠道名称</td>
						<td>证件号</td>
						<td>开户名</td>
						<td>城市</td>
						<td>银行名称</td>
						<td>开户行名称</td>
						<td>银行账号</td>
						<td>合同号</td>
						<td>申请金额</td>
						<td>支付状态</td>
						<td>付款金额</td>
						<td>付款说明</td>
						<td>申请时间</td>
						<td>申请人</td>
					</tr>
					<c:if test="${!empty voList}">
						<td align="center">汇总</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center"><escore:formatMoney value="${sum_Apply_money }" maxFractionDigits="2" /></td>
						<td align="center">--</td>
						<td align="center"><escore:formatMoney value="${sum_finance_money}" maxFractionDigits="2" /></td>
						<td align="center">--</td>
						<td align="center">--</td>
						<td align="center">--</td>
					</c:if>
					<c:if test="${!empty voList}">
						<c:forEach items="${voList}" var="report">
							<tr>
								<td align="center">${report.id }</td>
								<td align="center">${report.dev_email}</td>
								<td align="center">${report.card_code }</td>
								<td align="center">${report.kaihu_name }</td>
								<td align="center">${report.city }</td>
								<td align="center">${report.bank_name }</td>
								<td align="center">${report.bank_subbranch }</td>
								<td align="center">${report.bank_account }</td>
								<td align="center">${report.contract_code }</td>
								<td align="center"><escore:formatMoney value="${report.apply_money }" maxFractionDigits="2" /></td>
									<c:choose>
									<c:when test="${report.status == 0}">
										<td align="center">待审核</td>
									</c:when>
									<c:when test="${report.status == 1}">
										<td align="center">通过</td>
									</c:when>	
									<c:when test="${report.status == -1}">
										<td align="center">不通过</td>
									</c:when>									
									<c:when test="${report.status == 2}">
										<td align="center">付款成功</td>
									</c:when>
									<c:when test="${report.status ==- 2}">
										<td align="center">付款失败</td>
									</c:when>								
								</c:choose>
								<td align="center">
								<c:if test="${report.finance_money != null and report.finance_money !=0 }">
									<escore:formatMoney value="${report.finance_money }" maxFractionDigits="2" />
								</c:if>
								</td>
								<td align="center">${report.pay_desc}</td>
								<td align="center"><fmt:formatDate
										value="${report.create_time }" pattern="yyyy-MM-dd" /></td>
								<td align="center">${report.proposer }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty voList}">
						<td colspan="15" align="center" style="text-align: center;">暂无记录！</td>
					</c:if>
				</table>
				${pageInfo.pageInfoStr}
			</div>
			<span class="clear_span"></span>
		</div>
		</div>
	</div>
</body>
</html>
