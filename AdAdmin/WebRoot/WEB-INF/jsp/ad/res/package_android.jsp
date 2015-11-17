<%@ page language="java" import="java.util.*,cn.adwalker.util.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告主广告审核</title>
<link href="css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/upload/css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/upload/js/swfobject.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/upload/js/jquery.uploadify.v2.0.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/upload/uploadCommon.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		 $("#uploadify").uploadify({
             'uploader'       : '${pageContext.request.contextPath}/upload/swf/uploadify.swf',
             'cancelImg'      : 'upload/images/cancel.png',
             'queueID'        : 'fileQueue',
				'wmode'          : 'transparent',
             'buttonText'     : '浏览...',
             'buttonImg'      : '${pageContext.request.contextPath}/upload/images/browse.jpg',
             'method'         : 'GET',//如果要传参数，就必须改为GET  
             'auto'           : false,
             'multi'          : false,
             'script'         : '${pageContext.request.contextPath}/upload/uf',
             'scriptData'     : {"po":"0","pa":"/${adv.adv_email}/${adv.adv_id}/","ic":"1"},
             'queueSizeLimit' : 1,//允许最大上传队列长度
             'simUploadLimit' : 1,//同时处理上传任务数
             'fileExt'        : '*.apk',//允许访问的文件格式
             'fileDesc'       : '支持格式：*.apk',
				
				onComplete: function (event, queueID, fileObj, response, data){
					var json = eval('('+response+')');
					if(typeof(json.error) != 'undefined'){
						alert(response);
					}else{
						try{
							adUpdate(json);
						}catch(e){
							alert(e.message);
						}
					}
				},
				onError: function(event, queueID, fileObj){
    				alert("文件: " + fileObj.name + " 上传失败！");
    			},
				onCancel: function(event, queueID, fileObj){
					alert("取消文件： " + fileObj.name + " 上传！");
				}
         });
	});
	
	//文件上传后的回调函数
	function adUpdate(data){
		var errMsg;
		if(typeof(data.fall) != 'undefined'){
			$(".uploadMsg").html("<font color='red'>"+data.fall[0].errorMsg+"</font>");
		}else{
			var path = data.succeed[0].path;
			var newFileName = data.succeed[0].newFileName;
			var savePath = path+newFileName;
			var temp = path.substring(0,path.length-1);
			var adid =  temp.substring(temp.lastIndexOf("/")+1);
			savePath = encodeURIComponent(savePath);//编码
			//解析返回数据
			var url="updateAdDate.do?id=${placement_id}&path="+savePath;
			url = encodeURI(url);//url编码
			//ajax修改后台应用信息
			if(typeof(data.succeed) != 'undefined'){
				//alert("成功");
				$.ajax({
					type:"get",   
					url:url,   
					dataType : "json", 
					success:function(obj){
						if(obj.status=="1"){
							//显示下载链接
							$(".uploadMsg").html("<a href=''>"+obj.data.file_name+"</a>");
							insert_row(obj.data);
						}else{
							errMsg = obj.error;
							$(".uploadMsg").html("<font color='red'>"+errMsg+"</font>");
						}
			 	
					}   
				});
			}
		}
	}
	
	
	function insert_row(data){
		if(data){
			var str='<tr id="tr_'+data.id+'"><td align="center" >'+data.id+'</td><td align="center">'
			+'<a href="manage!editDev.do?dev_id=${dev.id}" class="yylba"><font color="blue">'+data.file_name+'</font></a></td>'
			+'<td align="center">'+data.package_name+'</td><td align="center">'+data.create_time+'</td><td align="center"><input type="text" name="code" id="ipt_'+data.id+'" value="'+data.code+'"></td>'
			+'<td align="center"><input type="button" value="保存" onclick="update_code('+data.id+');" style="cursor: pointer;"/>'
			+'<input type="button" value="删除" onclick="del_package('+data.id+');" style="cursor: pointer;" /></td></tr>';
			$("#table_list tr:last-child").after(str);
			if($("#no_record")){
				$("#no_record").remove();
			}
			
		}
		
	}
	
	function del_package(id){
		$.ajax({
			url:'${pageContext.request.contextPath}/manage!delPackage.do',
			type:'POST',
			data:'id='+id,
			dataType:'text',
			beforeSend:function(){
					//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
			},
			success:function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						$("#tr_"+id).remove();
					}else if(dataObj.status=-1){
						alert("操作失败，请重试！！");
					}else{
						alert("操作失败，请重试！！");
					}
				}else{
					alert("操作失败，请重试！！");
				}
			}
		});
	}
	
	function update_code(id){
		$.ajax({
			url:'${pageContext.request.contextPath}/manage!updatePackageCode.do',
			type:'POST',
			data:'id='+id+"&code="+$("#ipt_"+id).val(),
			dataType:'text',
			beforeSend:function(){
					//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
			},
			success:function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("保存成功！！");
					}else if(dataObj.status=-1){
						alert(dataObj.error);
					}else{
						alert("操作失败，请重试！！");
					}
				}else{
					alert("操作失败，请重试！！");
				}
			}
		});
		
		
	}
	
	
	
</script>

</head>

<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<form action="manage!advertisementExamine.do" method="post"	enctype="multipart/form-data" name="form" id="form">
				<input type="hidden" name="userAdvertiser" value="${userAdvertiser}" />
				<input type="hidden" name="status" value="${advertisement.status}" />
				<div class="content_right admin_right">
					<div>
						活动推广程序： <span class="uploadMsg"> <c:if
								test="${advertisement.resourceSize ne null && advertisement.resourceSize ne 0}">
								<a href="${fileUrl}">${advertisement.fileName}</a>
							</c:if> <c:if
								test="${advertisement.resourceSize eq null || advertisement.resourceSize eq 0}">(未上传程序)</c:if>
						</span>
					</div>
					<div class="appFile">
						<div id="fileQueue"></div>
						<input type="file" name="uploadify" id="uploadify" /> <a
							href="javascript:jQuery('#uploadify').uploadifyUpload()"><img
							src="<%=request.getContextPath() %>/upload/images/startupload.jpg" />
						</a>&nbsp; <a
							href="javascript:jQuery('#uploadify').uploadifyClearQueue()"><img
							src="<%=request.getContextPath() %>/upload/images/cancelupload.jpg" />
						</a>
					</div>
				</div>
			</form>
			<div style="display: block;overflow-y: scroll;height: 450px;">
				<table width="100%" cellpadding="0" cellspacing="1" class="table_bod1 font_stat" id="table_list">
					<tr class="tr_td">
									<td>ID</td>
									<td>文件名</td>
									<td>包名</td>
									<td>更新时间</td>
									<td>编号</td>
									<td>操作</td>
								</tr>
								<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
								<tr id="tr_${vo.id}">
									<td align="center" >
                   							${vo.id}
                					</td>
                					<td align="center">
                						<a href="${resources_url_prefix}${vo.res_url}" class="yylba">
                   							<font color="blue">${vo.file_name}</font>
                   						</a>
                					</td>
                					<td align="center">
                   							${vo.package_name}
                					</td>
                					<td align="center"><fmt:formatDate value="${vo.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td align="center">
										<input type="text" name="code" id="ipt_${vo.id}" value="${vo.code}" />
									</td>
									<td align="center">
										<input type="button" value="保存" onclick="update_code('${vo.id}');" style="cursor: pointer;"/>
										<input type="button" value="删除" onclick="del_package('${vo.id}');" style="cursor: pointer;" />
									</td>
								</tr>
								</c:forEach>
								</c:if>
								<c:if test="${empty list}">
									<tr id="no_record">
										<td align="center" colspan="10" style="text-align: center;">暂无记录！</td>
									</tr>
								</c:if>
							</table>
			
			</div>
		</div>
	</div>
</body>
</html>