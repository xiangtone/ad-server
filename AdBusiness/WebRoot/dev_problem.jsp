<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>开发者常见问题-帮助中心-<%=company%>广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/external-pages.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/blog.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
  	 <script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme.js"></script>
    
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
                <span>帮助中心</span>
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
                        帮助
                    </h3>
                    <ul class="sidebar_menu">
                        <li><a href="${pageContext.request.contextPath}/help_center.jsp">平台常见问题</a></li>
                        <li><a href="${pageContext.request.contextPath}/dev_problem.jsp">开发者常见问题</a></li>
                    </ul>


                </div>
                <div class="span8">
            <!-- header -->
                    <h4 class="header" id="开发者常见问题">
                       	 开发者常见问题
                        <hr />
                    </h4>

                     <div class="container" style="width:100%">
            <!-- list -->
            <div class="bar-warning">
                <div class="span12" style="width:96%;">
                    <div class="faq">
                        <div class="question">
                     	       <%=company%>开发者服务平台支持哪些广告形式？
                        </div>
                        <div class="answer">
                        
  							 平台目前支持横幅，插屏，推荐墙，积分墙，视频广告等多种广告形式，同时更多全新广告即将推出。<br />                            
                        </div>
                    </div>
                    <div class="faq">
                        <div class="question">
                          	 如何添加新应用？
                        </div>
                        <div class="answer">
						    （1）登录平台后，点击导航边栏的“应用中心”中的“添加应用”，或者默认页上的添加应用按钮。<br /> 
						    （2）进入页面后，选择对应的应用平台，并填写相关应用信息。<br />  
						    （3）下载对应的SDK，并完成代码嵌入，应用创建即完成，并得到对应的应用ID，开发者ID，appKEY。 <br /> 
                          </div>
                    </div>
                    <div class="faq">
                        <div class="question">
                          	 怎么上传审核应用？
                        </div>
                        <div class="answer">
							创建应用，嵌入SDK测试完毕后，即可选择“应用列表”中的应用，点击“编辑”，上传嵌入SDK后的应用，并点击“确认保存”。<br />
                       </div>
                    </div>
                    <div class="faq">
                        <div class="question">
                            	审核一般需要多长时间？
                        </div>
                        <div class="answer">
							平台会在2个工作日之内处理完，节假日顺延。如果超过2个工作日，可能是由于任务较多，请您稍加等待，或联系平台客服。<br /> 
                       </div>
                    </div>
                    <div class="faq">
                        <div class="question">
                         	   怎么查看账户财务状态？
                        </div>
                        <div class="answer">
						登录平台后，点击导航边栏的“财务中心”的“提款申请”，即可查看当前可提款收入。   <br />                        </div>
                    </div>
                    <div class="faq">
                        <div class="question">
                   	      	   财务银行卡信息可以不是本人吗？
                        </div>
                        <div class="answer">
						平台会往您注册账户信息中的有效银行账户里打款。如果您提供发票的话，需发票的开票账户与平台账户的注册信息一致。   <br />
                        </div>
                    </div>
                    <div class="faq">
                        <div class="question">
                            	开发者提现须知。
                        </div>
                        <div class="answer">
						（1）平台不限制开发者的提款次数，开发者可以随时提交提款申请，<%=company%>平台会在每月月初统一处理本批次的提款申请；   <br /> 
						（2）当您的可提款收入高于100元时方可申请提款，最低提款金额是10元。个人开发者每人每次提款最高提款金额为50000元；<br /> 
						（3）有发票的提款，请尽早提交申请，<%=company%>平台将会在收到发票后支付，到账时间取决于您的开户银行的处理时间。  <br />
						
                       </div>
                    </div>
                </div>
            </div>
        </div>                    
                </div>

            </div>
        </div>
    </div>

    <!-- starts footer -->
     <jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>

</body>
</html>