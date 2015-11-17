<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
</style>

	<script language="javascript"> 
		function openSignupagreement(){
			window.open ('${pageContext.request.contextPath}/signupagreement.html', 'newwindow', 'height=500, width=400, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
			}
		
		function conceal(){
			window.open ('${pageContext.request.contextPath}/yinsitiaokuan.html', 'newwindow', 'height=500, width=400, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
			}
		</script>
 <div id="footer">
        <div class="container">
            <div class="footer-link">
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
            <hr />
            <div class="footer-copy">
			  <span>
				<p class="footer-copylineone">Copyright © 2011-2013 北京行者天下科技有限公司 All Rights Reserved.</p>
			  </span>
		    </div>
        </div>
    </div>