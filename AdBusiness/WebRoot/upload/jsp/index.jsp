<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
Boolean auto = (Boolean)request.getAttribute("auto");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
      <head>
        <base href="${basePath }">
        <title>文件上传组件</title>
        <link href="upload/css/uploadify.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="upload/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="upload/js/swfobject.js"></script>
        <script type="text/javascript" src="upload/js/jquery.uploadify.v2.0.3.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
            $("#uploadify").uploadify({
                'uploader'       : 'upload/swf/uploadify.swf',
                'cancelImg'      : 'upload/images/cancel.png',
                'queueID'        : 'fileQueue',
				'wmode'          : 'transparent',
                'buttonText'     : '浏览...',
                'buttonImg'      : 'upload/images/browse.jpg',
                'method'         : 'GET',//如果要传参数，就必须改为GET  
                'auto'           : ${auto},
                'multi'          : false,
                'script'         : '${script}',
                'scriptData'     : ${scriptData},
                'queueSizeLimit' : 1,//允许最大上传队列长度
                'simUploadLimit' : 1,//同时处理上传任务数
                'fileExt'        : '${fileExt}',//允许访问的文件格式
                'fileDesc'       : '支持格式：${fileExt}',
				
				onComplete       : function (event, queueID, fileObj, response, data){
					//var json = JSON.parse(response);
					var json = eval('('+response+')');
					if(typeof(json.error) != 'undefined'){
						alert(json.error);
					}else{
						try{
							parent.${functionName}(json);
						}catch(e){
							alert(e.message);
						}
					}
				},

				onError          : function(event, queueID, fileObj){
       				alert("文件: " + fileObj.name + " 上传失败！");
       			},

				onCancel         : function(event, queueID, fileObj){
					alert("取消文件： " + fileObj.name + " 上传！");
				}
            });
        });
        </script>
    </head>
    
    <body>
      
    </body>
</html>
