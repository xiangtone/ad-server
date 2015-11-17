<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>开发者服务-行云移动广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/external-pages.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme.js"></script>
	</head>
<body>
    <a href="#" class="scrolltop">
        <span>up</span>
    </a>
    <!-- begins navbar -->
	<jsp:include page="/WEB-INF/jsp/include/common/top.jsp"></jsp:include>
    <!-- ends navbar -->

    <!-- features -->
    <div id="contact" class="contact_page">
        <div class="container">
            <!-- header -->
            <h2 class="section_header">
                <hr class="left visible-desktop"  style="margin-top:25px"/>
                <span>开发者</span>
                <hr class="right visible-desktop" style="margin-top:25px"/>
            </h2>

            <div class="row map">
                <div class="span12">
                    <div class="gmaps">
                       <img style="width: 100%; height: 100%; border: none;" src="${pageContext.request.contextPath}/img/dev-bnner.png">
                    </div>
                </div>
            </div>

            <div class="row form" style="border-bottom:1px solid #BEC0C3">
                <div class="span6" style="margin-bottom:45px;">
<img src="${pageContext.request.contextPath}/img/chatu1.png"/ style="margin-left:150px;">
                </div>
                <div class="span5 offset1">
                    <div class="address">
                        <h3>海量优质广告资源</h3>
                        <p style="font-size:16px">
                            众多实力广告主亲情加盟，超高的广告填充率和单价，             <br /> 确保了开发者的收益
               
                        </p>
                    </div>
                </div>
            </div>
            <div class="row form" style="border-bottom:1px solid #BEC0C3">
                <div class="span5 offset1" style=" margin-left:200px;">
                    <div class="address">
                        <h3>智能精准定向提高转化率</h3>
                        <p style="font-size:16px">
                            智能数据处理，标签受众人群，精准广告投放，<br /> 点击率更高，开发者收入更多
               
                        </p>
                    </div>
                </div>
                <div class="span6" style="margin-bottom:45px; width:360px">
<img src="${pageContext.request.contextPath}/img/002.png"/>
                </div>
            </div>
            <div class="row form" style="border-bottom:1px solid #BEC0C3; ">
                <div class="span6" style="margin-bottom:45px;">
<img src="${pageContext.request.contextPath}/img/003.png"/ style="margin-left:150px;">
                </div>
                <div class="span5 offset1">
                    <div class="address">
                        <h3>详细透明精确的数据统计</h3>
                        <p style="font-size:16px">
                            公开透明的数据分析系统，提供精准的收入报表，             <br />确保开发者收益最大化，也为开发者优化应用提供数据支撑
               
                        </p>
                    </div>
                </div>
            </div>
            <div class="row form" style="border-bottom:1px solid #BEC0C3; margin-bottom:72px;">
                <div class="span5 offset1" style=" margin-left:200px;">
                    <div class="address">
                        <h3>多种广告形式选择，带来优质用户体验</h3>
                        <p style="font-size:16px">
                            多种广告展示形式，应用与广告属性高强度匹配，<br />精美的广告素材，确保了优化的用户体验
               
                        </p>
                    </div>
                </div>
                <div class="span6" style="margin-bottom:45px; width:360px">
<img src="${pageContext.request.contextPath}/img/004.png"/>
                </div>
            </div>

        </div>
    </div>

   

    <!-- starts footer -->
   <jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
</body>
</html>