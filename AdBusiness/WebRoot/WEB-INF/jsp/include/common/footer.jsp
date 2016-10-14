<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<style type="text/css">

.footer-link a{color:#fff;}

.footer-link a:link {
	color:#ccc;
	text-decoration:none;
}
.footer-link a:visited {
	color:#ccc;
	text-decoration:none;
}
.footer-link a:hover {
	color:#f7a300;
	text-decoration:none;
}
.footer-link a:active {
	color:#ccc;
	text-decoration:none;
}
@media ( min-width : 168px) {
.footer-record{
	text-align:center;
	line-height:66px;
	font-size:20px;
	color:#434343;
	margin-bottom:0px;
}
}
@media ( min-width : 520px) {
.footer-record{
	text-align:center;
	line-height:66px;
	font-size:20px;
	color:#434343;
	margin-bottom:0px;
}
}
@media ( min-width : 767px) {
.footer-record{
	text-align:center;
	line-height:66px;
	font-size:20px;
	color:#434343;
	margin-bottom:0px;
}
}
@media ( min-width : 997px) {

.footer-record{
	text-align:center;
	line-height:69px;
	font-size:20px;
	color:#434343;
	background-color:#fff;
	margin-bottom:0px;
}
}
@media ( min-width : 1200px) {
.footer-record{
	text-align:center;
	line-height:60px;
	font-size:20px;
	color:#434343;
	background-color:#fff;
	margin-bottom:0px;
	height:60px;
}
}
</style>

	<script language="javascript"> 
		function openSignupagreement(){
			window.open ('${pageContext.request.contextPath}/signupagreement.jsp', 'newwindow', 'height=500, width=400, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
			}
		
		function conceal(){
			window.open ('${pageContext.request.contextPath}/yinsitiaokuan.jsp', 'newwindow', 'height=500, width=400, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
			}
		</script>
 <div id="footer">
        <div class="container">
            <%-- <div class="footer-link">
				<span>|</span>
				<span><a href="${pageContext.request.contextPath}/aboutus.jsp">关于我们</a></span>
				<span>|</span>
				<span><a href="#" onclick="conceal()">隐私条款</a></span>
				<span>|</span>
				<span><a href="#" onclick="openSignupagreement()">服务条款</a>	</span>			
				<span>|</span>
				<span><a href="${pageContext.request.contextPath}/jionus.jsp" target="_blank">加入我们</a></span>
				<span>|</span>
                
            </div>
            <hr /> --%>
            <div class="footer-copy">
			  <span>
				<p class="footer-copylineone">合作伙伴<%-- Copyright &copy; 2011-<script>var today = new Date();document.write(today.getFullYear());</script> <%=company%>广告 All Rights Reserved. --%></p>
			 	
			  </span>
		    </div>
		   
        </div>
    </div>
     <p class="footer-record">浩天支付&copy;2016 - 粤ICP备12058124号</p>
     <script type="text/javascript">

function active(){
	
	var url = this.location.href;
	var htmlName = url.split("/");
	var name=htmlName[htmlName.length - 1].split("?")[0]; 
	if (name == "index.jsp"||name == "") {
		$("#footer").css("min-height","141px");	
	}else {
		$("#footer").css("min-height","130px");
	}
}	
	window.onload=active;active();

</script>