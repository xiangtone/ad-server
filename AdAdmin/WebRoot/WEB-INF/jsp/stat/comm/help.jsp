<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../comm/basePath.jsp"%>
<html>
  <head>
    <link href="<%=basePath%>/css/admin.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath%>/css/1.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" media="all" href="<%=basePath%>/css/calendar-win2k-cold-1.css" title="win2k-cold-1">
    <script language="JavaScript" type="text/JavaScript" src="<%=basePath%>/js/calendar.js?v=${version}"></script>
    <script language="JavaScript" type="text/javascript" src="<%=basePath%>/js/calendar-zh.js?v=${version}"></script>
    <script language="JavaScript" type="text/javascript" src="<%=basePath%>/js/calendar-setup.js?v=${version}"></script>
    <title>帮助信息</title>
  </head>
  <body>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" bordercolor="#999999" class="style_table">
  <tr>
  <th>编号</th><th>规则</th>
  </tr>
  <tr>
  <td>1</td><td>Table设置中搜索词必须大写，如START_DATE,END_DATE(可以没有)，搜索项A B C 例如:<br/>
                select stat_date,sum(pv) as pv from stat_table where datediff(stat_date,'START_DATE')>=0<br/>
                and  datediff(stat_date,'END_DATE')<=0 group by stat_date TYPE AREA</td>
  </tr>
  
  <tr>
  <td>2</td><td>ITEM设置中必须包含A B C，例如：<br/>
                TYPE:  SQL名:TYPE 页面名称:类型   页面选择方式:input   SQL搜索方式:=搜索  是否链接：否<br/>
                AREA:  SQL名:AREA 页面名称:地区   页面选择方式:input  SQL搜索方式:=搜索  是否链接:是<br/>
                        数据源SQL: select id as id, name as value from area <font color="red"> 必须是id和value组成，页面返回搜索值id</font><br/>
                        链接地址:stat.do?op=showList&id=5&&tt=get&NAME<font color="red">必须有tt=get以处理乱码问题，&NAME用于传递页面id值</font>
                        </td>
  </tr>
  </table>
  </body>

</html>
