<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${business_title}</title>
<link type="text/css" rel="stylesheet" id="cssfile"	href="<%=request.getContextPath()%>/css/landscape.css?v=${version}" />
<link href="${pageContext.request.contextPath}/css/tabTool.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		if(dg){
			dg = frameElement.lhgDG;
			dg.SetCancelBtn("关闭",function(){
				dg.cancel();
			});
			dg.addBtn("audit","提交审核",function(){
				$("#placement_status").val(-10);
				submit_form();
				
			});
		}
		
		init_wall_type('${page_type}');
	});
	
	
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
	
	
	
	function init_wall_type(page_str){
		var wall_type_3=0;
		var wall_type_4=1;
		var wall_type_5=4;
		var wall_type_6=5;
		if(page_str){
			var arr=page_str.split(",");
			for(var i=3;i<7;i++){
				var b=false;
				for(var j=0;j<arr.length;j++){
					if(eval("wall_type_"+i)==arr[j]){
						b=true;
						break;
					}
				}
				if(!b){
					$(".sub_"+i).hide();
				}
			}
		}else{
			for(var i=2;i<6;i++){
				$(".sub_"+i).hide();
			}
		}
	}
	
</script>
</head>
<body style="overflow: hidden;">
	<div id="content" style="padding: 0; height: 540px;overflow: hidden;">
		<div class="main_right" style="height: 450px; overflow: hidden;">
			<ul class="tab_block2">    
      			<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click">
      				<span class="click">基本信息</span></a>
      			</li>
      			<li>
      				<a href="javascript:void(0)" class="sub_2">
      					<span>素材--应用</span>
      				</a>
      			</li>
      			<li>
      				<a href="javascript:void(0)" class="sub_3">
      					<span>积分墙</span>
      				</a>
      			</li>
      			<li>
      				<a href="javascript:void(0)" class="sub_4">
      					<span>推荐墙</span>
      				</a>
      			</li>
      			<li>
      				<a href="javascript:void(0)" class="sub_5">
      					<span>banner</span>
      				</a>
      			</li>
      			<li>
      				<a href="javascript:void(0)" class="sub_6">
      					<span>插屏</span>
      				</a>
      			</li>
      			<li>
      				<a href="javascript:void(0)" class="sub_7">
      					<span>投放信息</span>
      				</a>
      			</li>
    		</ul>
    		<div class="subblock_1" style="display:block;border: 0px solid #9dbaff;float: left;width: 1055px;border-top: 0;height: 412px;">
    			<div style="display: block;height: 280px;width: 1055px;">
    				<table width="900" style="border: none;">
					<tr>
						<td>
							<table width="900" border="0">
								<tr>
									<td width="70" bgcolor="#cfe1e2" align="right">投放名称：</td>
									<td width="180" align="left">${vo.name}</td>
									<td width="70" bgcolor="#cfe1e2" align="right">平台类型：</td>
									<td width="180" align="left">${vo.os}</td>
									<td width="70" bgcolor="#cfe1e2" align="right">星级：</td>
									<td width="180" align="left">
										<c:choose>
											<c:when test="${vo.star_level == 5}">
												五星
											</c:when>
											<c:when test="${vo.star_level == 4.5}">
												四星半
											</c:when>
											<c:when test="${vo.star_level == 4.0}">
												四星
											</c:when>
											<c:when test="${vo.star_level == 3.5}">
												三星半
											</c:when>
											<c:when test="${vo.star_level == 3.0}">
												三星
											</c:when>
											<c:when test="${vo.star_level == 2.5}">
												二星半
											</c:when>
											<c:when test="${vo.star_level == 2.0}">
												二星
											</c:when>
											<c:when test="${vo.star_level == 1.5}">
												一星半
											</c:when>
											<c:otherwise>
												一星
											</c:otherwise>
										</c:choose>
										<c:if test="${vo.priority==1}">(优先推荐)</c:if>
									</td>
									<td width="79" bgcolor="#cfe1e2" align="right">投放类别：</td>
									<td width="212" align="left">
										<c:forEach items="${ecList}" var="entry">
											<c:if test="${vo.category_id eq entry.id}">${entry.fname}-${entry.name}</c:if>
										</c:forEach>
									</td>
								</tr>
								<tr>
									
								</tr>
								<tr>
									<td bgcolor="#cfe1e2" align="right">广告语：</td>
									<td align="left">
										<escore:subStr len="20" value="${vo.slogan}" />
									</td>
									<td bgcolor="#cfe1e2" align="right">关键词：</td>
									<td align="left">${vo.keyword}</td>
									<td bgcolor="#cfe1e2" align="right">活动分类：</td>
									<td align="left">
										<c:forEach items="${sList}" var="entry">
											<c:if test="${campaignInfoVo.category_id eq entry.id}">${entry.name}</c:if>
										</c:forEach>
									</td>
									<td bgcolor="#cfe1e2" align="right"></td>
									<td align="left"></td>
								</tr>
								<tr>
								
								</tr>
								<tr>
									<td bgcolor="#cfe1e2" align="right">投放形式：</td>
									<td align="left">${vo.typeStr}</td>
									<td bgcolor="#cfe1e2" align="right">投放排期：</td>
									<td align="left">
										<fmt:formatDate value="${vo.plan_start}" type="date" dateStyle="long" pattern="yyyy-MM-dd"/>--<fmt:formatDate value="${vo.plan_end}" type="date" dateStyle="long" pattern="yyyy-MM-dd"/>
									</td>
									<td bgcolor="#cfe1e2" align="right">活动名称：</td>
									<td align="left">${campaignInfoVo.campaign_name}</td>
									<td bgcolor="#cfe1e2" align="right">平台类型：</td>
									<td align="left">${campaignInfoVo.os}</td>
								</tr>
								<tr>
									<td bgcolor="#cfe1e2" align="right">活动类型：</td>
									<td align="left">
										<c:choose>
											<c:when test="${campaignInfoVo.campaign_type == 0}">
												下载类
											</c:when>
											<c:when test="${campaignInfoVo.campaign_type ==1}">
										     	 注册类
											</c:when>
										</c:choose>
									</td>
									<td bgcolor="#cfe1e2" align="right">计费方式：</td>
									<td align="left">${campaignInfoVo.charge_type}</td>
									<td bgcolor="#cfe1e2" align="right">总预算：</td>
									<td align="left">
										${campaignInfoVo.budget}
									</td>
									<td bgcolor="#cfe1e2" align="right">接入单价：</td>
									<td align="left">${campaignInfoVo.price}元</td>
								</tr>
								<tr>
								<td bgcolor="#cfe1e2" align="right">是否是新应用：</td>
									<td align="left">
										<c:choose>
											<c:when test="${vo.new_app == 0}">
												否
											</c:when>
											<c:when test="${vo.new_app == 1}">
										     	是
											</c:when>
										</c:choose>
									</td>	
									<td bgcolor="#cfe1e2" align="right">是否热门推荐：</td>
									<td align="left">
										<c:choose>
											<c:when test="${vo.popular_app == 0}">
												否
											</c:when>
											<c:when test="${vo.popular_app == 1}">
										     	是
											</c:when>
										</c:choose>
									</td>
									<td bgcolor="#cfe1e2" align="right">是否链接：</td>
									<td align="left">
										<c:choose>
											<c:when test="${vo.is_url_params == 0}">
												否
											</c:when>
											<c:when test="${vo.is_url_params == 1}">
										     	是
											</c:when>
										</c:choose>
									</td>		
									<td bgcolor="#cfe1e2" align="right">SDK版本：</td>
								</tr>
								<tr>
									<td bgcolor="#cfe1e2" align="right">活动要求：</td>
									<td align="left" colspan="3">
										${campaignInfoVo.campaign_required}
									</td>
									<td bgcolor="#cfe1e2" align="right">活动介绍：</td>
									<td align="left" colspan="3">
										<textarea rows="5" cols="83" readonly="readonly">${vo.campaign_desc}</textarea>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				</div>
			</div>
			<div class="subblock_2" style="display:none;border: 0px solid #9dbaff;float: left;width: 1055px;border-top: 0;height: 412px;">
				<div><img id="iconimg" <c:if test="${vo.iconimg_url ne null}"> src="${images_url_prefix}/${vo.iconimg_url}"</c:if><c:if test="${vo.iconimg_url eq null}"> src="${pageContext.request.contextPath}/images/ico/nopic.gif"</c:if> onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" width="135px" height="135px" style="cursor: pointer;" /></div>
				<div>
					<img id="appimg01" <c:if test="${vo.appimg_url_01 ne null}"> src="${images_url_prefix}/${vo.appimg_url_01}"</c:if><c:if test="${vo.appimg_url_01 eq null}"> src="${pageContext.request.contextPath}/images/ico/nopic.gif"</c:if> onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" width="135px" height="135px" style="cursor: pointer;" />
					<img id="appimg02" <c:if test="${vo.appimg_url_02 ne null}"> src="${images_url_prefix}/${vo.appimg_url_02}"</c:if><c:if test="${vo.appimg_url_02 eq null}"> src="${pageContext.request.contextPath}/images/ico/nopic.gif"</c:if> onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" width="135px" height="135px" style="cursor: pointer;" />
					<img id="appimg03" <c:if test="${vo.appimg_url_03 ne null}"> src="${images_url_prefix}/${vo.appimg_url_03}"</c:if><c:if test="${vo.appimg_url_03 eq null}"> src="${pageContext.request.contextPath}/images/ico/nopic.gif"</c:if> onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" width="135px" height="135px" style="cursor: pointer;" />
				</div>
				
			</div>
			<div class="subblock_3" style="display:none;border: 0px solid #9dbaff;float: left;width: 1055px;border-top: 0;height: 412px;">
				<table width="950" style="border: 0;">
					<tr>
						<td>
							<table width="950" border="0">
								<tr>
									<td width="75" bgcolor="#cfe1e2" align="right">响应方式：</td>
									<td width="220" align="left">
										<c:if test="${vo.wall_score_response_category==0}">下载
											<c:choose>
												<c:when test="${vo.wall_score_response_type == 0}">
													(app推广)
												</c:when>
												<c:when test="${vo.wall_score_response_type == 2}">
													(app推广下载)
												</c:when>
												<c:when test="${vo.wall_score_response_type == 3}">
													(直接下载)
												</c:when>
											</c:choose>
										</c:if>
										<c:if test="${vo.wall_score_response_category==1}">注册（${wall_score_redirect_url}）
										</c:if>
									</td>
									<td width="79" bgcolor="#cfe1e2" align="right">权重：</td>
									<td width="212" align="left">${vo.wall_score_weight}</td>
								</tr>
								<tr>
									<td width="75" bgcolor="#cfe1e2" align="right">积分获取简要说明：</td>
									<td width="220" align="left">${vo.wall_score_desc}</td>
									<td width="79" bgcolor="#cfe1e2" align="right">积分获取详细说明：</td>
									<td width="212" align="left">${vo.wall_score_long_desc}</td>
								</tr>
								<tr>
									<td width="75" bgcolor="#cfe1e2" align="right">积分延时：</td>
									<td width="220" align="left">${vo.score_delay}</td>									
								</tr>
								<tr>
									<td width="75" bgcolor="#cfe1e2" align="right">墙BANNER：</td>
									<td align="left" colspan="3">
										<img id="wall_score_banner" src="${images_url_prefix}/${vo.wall_score_banner_url}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<div class="subblock_4" style="display:none;border: 0px solid #9dbaff;float: left;width: 1055px;border-top: 0;height: 412px;">
				<table width="950" style="border: 0;">
					<tr>
						<td>
							<table width="950" border="0">
								<tr>
									<td width="75" bgcolor="#cfe1e2" align="right">响应方式：
									</td>
									<td width="220" align="left">
										<c:if test="${vo.wall_list_response_category==0}">下载
											<c:choose>
												<c:when test="${vo.wall_list_response_type == 0}">
													(app推广)
												</c:when>
												<c:when test="${vo.wall_list_response_type == 2}">
													(app推广下载)
												</c:when>
												<c:when test="${vo.wall_list_response_type == 3}">
													(直接下载)
												</c:when>
											</c:choose>
										</c:if>
										<c:if test="${vo.wall_list_response_category==1}">注册（${wall_list_redirect_url}）
										</c:if>
									</td>
									<td width="79" bgcolor="#cfe1e2" align="right">权重：</td>
									<td width="212" align="left">${vo.wall_list_weight}</td>
								</tr>
								<tr>
									<td width="75" bgcolor="#cfe1e2" align="right">墙BANNER：</td>
									<td align="left" colspan="3">
										<img id="wall_list_banner" src="${images_url_prefix}/${vo.wall_list_banner_url}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
			</div>
			<div class="subblock_5" style="display:none;border: 0px solid #9dbaff;float: left;width: 1055px;border-top: 0;height: 412px;">
				<table width="950" style="border: 0;">
					<tr>
						<td>
							<table width="950" border="0">
								<tr>
									<td width="75" bgcolor="#cfe1e2" align="right">响应方式：</td>
									<td width="220" align="left">
										<c:if test="${vo.banner_response_category==0}">下载
											<c:choose>
												<c:when test="${vo.banner_response_type == 0}">
													(app推广)
												</c:when>
												<c:when test="${vo.banner_response_type == 2}">
													(app推广下载)
												</c:when>
												<c:when test="${vo.banner_response_type == 3}">
													(直接下载)
												</c:when>
											</c:choose>
										</c:if>
										<c:if test="${vo.banner_response_category==1}">注册（${banner_redirect_url}）
										</c:if>
									</td>
									<td width="79" bgcolor="#cfe1e2" align="right">权重：</td>
									<td width="212" align="left">${vo.banner_weight}</td>
								</tr>
								<tr>
									<td width="75" bgcolor="#cfe1e2" align="right">BANNER图片：</td>
									<td align="left" colspan="3">
										<img id="banner01" src="${images_url_prefix}/${vo.beanner_img_url_first}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" />
									<img id="banner02" src="${images_url_prefix}/${vo.beanner_img_url_second}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" />
									<img id="banner03" src="${images_url_prefix}/${vo.beanner_img_url_third}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<div class="subblock_6" style="display:none;border: 0px solid #9dbaff;float: left;width: 1055px;border-top: 0;height: 412px;">
				<table width="950" style="border: 0;">
					<tr>
						<td>
							<table width="950" border="0">
								<tr>
									<td width="75" bgcolor="#cfe1e2" align="right">响应方式：</td>
									<td width="220" align="left">
										<c:if test="${vo.chartboost_response_category==0}">下载
											<c:choose>
												<c:when test="${vo.chartboost_response_type == 0}">
													(app推广)
												</c:when>
												<c:when test="${vo.chartboost_response_type == 2}">
													(app推广下载)
												</c:when>
												<c:when test="${vo.chartboost_response_type == 3}">
													(直接下载)
												</c:when>
											</c:choose>
										</c:if>
										<c:if test="${vo.chartboost_response_category==1}">注册（${chartboost_redirect_url}）
										</c:if>
										
									</td>
									<td width="79" bgcolor="#cfe1e2" align="right">权重：</td>
									<td width="212" align="left">${vo.chartboost_weight}</td>
								</tr>
								<tr>
									<td width="75" bgcolor="#cfe1e2" align="right">横屏：</td>
									<td width="220" align="left">
										<img id="chartboost_x" src="${images_url_prefix}/${vo.img_horizontal}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" />
									</td>
									<td width="79" bgcolor="#cfe1e2" align="right">竖屏：</td>
									<td width="212" align="left">
										<img id="chartboost_y" src="${images_url_prefix}/${vo.img_vertical}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<div class="subblock_7"  style="display:none;border: 0px solid #9dbaff;float: left;width: 1055px;border-top: 0;height: 412px;">
				<div style="background-color: red;display: block;height: 412px;width: 1061px;">
					<iframe frameborder="0" height="412px;" width="1061px;" src="manage!adByPlacementList.do?placement_id=${placement_id}"></iframe>
				</div>
			</div>
		</div>
		<div>
			<form id="addPubAct" name="addPubAct" action="manage!submitPlacement.do" method="post">
				<input name="id" value="${vo.id}" type="hidden" />
			</form>
		</div>
	</div>
</body>
</html>