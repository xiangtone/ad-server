<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>加入我们-<%=company%>广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/external-pages.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/blog.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<body>
    <a href="#" class="scrolltop">
        <span>up</span>
    </a>
    <!-- begins navbar -->
	<jsp:include page="/WEB-INF/jsp/include/common/top.jsp"></jsp:include>
    <!-- ends navbar -->

    <div id="blog_wrapper">
        <div class="container">
        
            <h2 class="section_header">
                <hr class="left visible-desktop"  style="margin-top:25px"/>
                <span>加入我们</span>
                <hr class="right visible-desktop" style="margin-top:25px"/>
            </h2>
            <div class="row map">
                <div class="span12">
                    <div style="width: 100%;height: 200px;border: 3px solid #fff;border-radius: 6px;box-shadow: 0px 0px 1px 1px #CFCFCF;">
                        <img style="width: 100%; height: 100%; border: none;" src="${pageContext.request.contextPath}/img/help-center.jpg">
                    </div>
                </div>
            </div>

            <div class="row" id="faq">
                <div class="span3 sidebar offset1">


                    <h3 class="sidebar_header">
                     		   我们
                    </h3>

                    <ul class="sidebar_menu">
                        <li><a href="${pageContext.request.contextPath}/contactus.jsp">联系我们</a></li>
                        <li><a href="${pageContext.request.contextPath}/jionus.jsp">加入我们</a></li>
                        <li><a href="${pageContext.request.contextPath}/aboutus.jsp">关于我们</a></li>
                    </ul>


                </div>
                <div class="span8">
            <!-- header -->
                    <h4 class="header" id="加入我们">
                    	    加入我们
                        <hr />
                    </h4>

                     <div class="container" style="width:100%">
            <!-- list -->
            <div class="row">
                <div class="span12" style="width:96%;">
                    <div class="faq">
                        <div style="color:#2c3339;font-weight:bold;font-size: 17px;">
                          	  运营专员
                        </div>
                        <div class="answer" style="display:inherit;">
						                        【岗位职责】<br />
						1、负责为公司的产品寻找合适的推广渠道（APP推广渠道）；<br />
						2、负责规划产品的渠道投放计划，统计分析各类移动互联网推广渠道数据；<br />
						3、负责其他有关相关合作事宜。<br />
						【任职资格】<br />
						1、善于沟通，总结，能快速反应处理问题，有很强的执行力和团队精神；<br />
						2、能够有计划主动跟进项目，发现问题并独立解决问题；有相关渠道资源者优先。<br />
					   可发送简历至：<a href="<%=join_us_email_url %>" class="send" style="color:#06C"> <%=join_us_email %></a><br />
                        </div>
                    </div>
                    <div class="faq">
                        <div style="color:#2c3339;font-weight:bold;font-size: 17px;">
                         	 商务媒介经理
                        </div>
                        <div class="answer" style="display:inherit;">
							                        【岗位职责】<br />
							1、 根据公司战略， 拓展、挖掘有利于移动广告平台的APP媒介资源（iOS和andriod），评估资源的可合作性；<br />
							2、 负责APP媒介合作的策划、谈判和执行，完成业绩目标；<br />
							3、 维护媒介关系，并挖掘继续深入合作的可能性；<br />
							4、 关注市场和竞争对手趋势，为公司制定战略提供支持。<br />
							【职位要求】<br />
							1、 有应用商店、开发者服务平台、手机游戏平台工作经历和资源者优先；<br />
							2、 热爱移动互联网，有1年以上移动互联网相关工作经验；<br />
							3、 形象气质佳，具有优秀文字表达能力和媒介策划能力；<br />
							4、 具有良好的商务谈判能力、创新意识、协调能力、和交际能力；<br />
							5、 有团队合作精神，能承担较大工作压力, 富有亲和力和感染力；<br />
							6、 工作细心，责任心强，有上进心，思维清晰，学习能力强。<br />
					      可发送简历至：<a href="<%=join_us_email_url %>" class="send" style="color:#06C"><%=join_us_email %></a><br />                      
                        </div>
                    </div>
                    <div class="faq">
                        <div style="color:#2c3339;font-weight:bold;font-size: 17px;">
							iOS开发工程师                        
					</div>
                        <div class="answer" style="display:inherit;">
						                        【岗位职责】<br />
						1、根据产品需求，完成iOS平台手机的应用软件开发工作；<br />
						2、修正并验证测试中发现的问题；<br />
						3、负责基于iOS平台产品的维护和升级；<br />
						4、学习和研究新技术以满足产品的需求；<br />
						5、能够快速完成高质量的代码，注重代码细节；<br />
						6、根据开发过程中的体验对产品提出改进建议。<br />
						                    【职位要求】<br />
						1、大学专科及以上学历，计算机等相关专业；<br />
						2、具有二年以上iOS开发经验，具备C/Objective-C开发经验；<br />
						3、熟悉Objective-C程序设计，了解iPhone SDK及相关开发工具；<br />
						4、熟练运用XCode，Interface Builder等开发工具，掌握界面、后台运行、数据存储等原理；<br />
						5、熟悉移动终端网络编程，了解3G、WiFi等技术，熟悉xml，掌握http、TCP/IP协议；<br />
						6、有3个以上App store上线应用者优先；<br />
						7、工作认真，细心，有条理，具有较强的沟通能力及团队合作精神；<br />
						8、喜欢接受挑战，对新产品或新技术有很强的兴趣，喜欢尝试最新技术。<br />
					  可发送简历至：<a href="<%=join_us_email_url %>" class="send" style="color:#06C"><%=join_us_email %></a><br />                              
                        </div>
                    </div>
                    
                    <div class="faq">
                        <div style="color:#2c3339;font-weight:bold;font-size: 17px;">
							 web前端工程师    
					</div>
                        <div class="answer" style="display:inherit;">
						【岗位职责】<br /> 
						1、研发基于HTML5的移动网站前端；<br /> 
						2、研发基于HTML5的APP内嵌网页。<br /> 
						【职位要求】<br /> 
						1、两年以上的工作经验，大专及以上学历； <br /> 
						2、能独立切图制作html网页，可处理浏览器的兼容性； <br /> 
						3、熟练使用html、javascript、css进行网页开发，熟悉html5 css3的新功能； <br /> 
						4、能快速掌握HTML5 和 Mobile Web开发；<br />  
						5、熟悉交互设计，对可用性、可访问性等用户体验知识有相当的了解和实践经验； <br /> 
						6、具备良好的服务意识、责任心、较强的学习能力、优秀的团队沟通与协作能力、能承受一定的工作压力。<br /> 
					  可发送简历至：<a href="<%=join_us_email_url %>" class="send" style="color:#06C"><%=join_us_email %></a><br />                              
                        </div>
                    </div>
                    


                    
                </div>
            </div>
        </div>                    
        <div class="pagination">
                    </div>

                </div>

            </div>
        </div>
    </div>

    <!-- starts footer -->
<jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
    
</body>
</html>