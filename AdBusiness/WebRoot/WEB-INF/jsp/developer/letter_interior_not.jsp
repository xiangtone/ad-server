<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		 <!-- libraries -->
		<link href="${pageContext.request.contextPath}/css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/ui-elements.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/letter.min.css" type="text/css" media="screen" />
		<script type="text/javascript">
		    function delm(id){
				var param_data='id='+id;
				   $.ajax({
					url:'deleteInteriorMail.action',
					type:'POST',
					data:param_data,
					dataType:'text',
					beforeSend:function(){
					},
					success:function(data){
						if(data=="false"){
							alert("数据删除失败！！");
							window.location.href='toInteriorMailList.action';
						}else{
							alert("数据删除成功！！");
							window.location.href='toInteriorMailList.action';
						}	
					}
				});	
			}
		    function updatem(id){
				var param_data='id='+id;
				   $.ajax({
					url:'updatemInteriorMail.action',
					type:'POST',
					data:param_data,
					dataType:'text',
					beforeSend:function(){
					},
					success:function(data){
					}
				});	
			}
			$(function() {
				$("#d_b").click(function(){
					if($("#orag").hasClass('expand')){
						$("#orag").next().hide();
						$("#orag").attr('class','message-header clearfix');;
						return;
					}
					$('dd').hide();
					$('dt').attr('class','message-header clearfix');
					$("#orag").attr('class','expand');
					$("#orag").next().show();
				});
			});
			$(function() {
				$("#d_btwo").click(function(){
				alert();
					if($("#orag_two").hasClass('expand')){
						$("#orag_two").next().hide();
						$("#orag_two").attr('class','message-header clearfix unread');;
						return;
					}
					$("#dd_c").hide();
					$('dt').attr('class','message-header clearfix unread');
					$("#orag_two").attr('class','expand');
					$("#orag_two").next().show();
				});
			});
			function interiorall(){
				window.location.href = 'toInteriorMailAllList.action';
			}
			function interiorNot(interior_status){
				window.location.href = 'toInteriorMailNotList.action?interior_status=' + interior_status;
			}
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<div class="content">
			<div class="settings-wrapper" id="pad-wrapper">
				<div class="row header">
					<h3>站内信</h3>
				</div>
				<div class="table-products section">
					<div class="row ctrls">
						<ul class="nav nav-tabs">
							<li ><a href="#all" onclick="interiorall();">全部信件（${count_hava}）</a></li>
							<li class="active"><a href="#unread"  onclick="interiorNot(0);">未读信件（${count_not }）</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane" id="unread">
								<div class="row">
									<ul class="table table-hover">
										<c:if test="${!empty list}">
											<c:forEach items="${list}" var="aml" varStatus="status">
												<c:if test="${aml.status==0}">
													<li>
														<dl>
															<dt class="message-header clearfix unread" id="orag_two">
																<span class="message-title">【公告】${aml.title}</span> <span class="pull-right" style="margin-right: 5px;" data-msg-id="#"> <a href="javascript:void(0)" id="d_btwo" class="view-message" onclick="updatem(${aml.id});">查看详情&gt;</a> <a href="javascript:void(0)" class="delete delete-message" onclick="delm(${aml.id});">删除</a>
																</span>
															</dt>
															<dd id="dd_c" style="display: none;" class="message-content">
																致${email}开发者： <br>&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;${aml.content} <br>
															</dd>
														</dl>
													</li>
												</c:if>
											</c:forEach>
										</c:if>
										<c:if test="${empty list}">
											<li>
												<p align="center" colspan="13" style="text-align: center;">暂无记录！</p>
											</li>
										</c:if>
									</ul>
								</div>
								<ul class="pagination">${pageInfo}</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu5_3");
	    </script>
	</body>
</html>