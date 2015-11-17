/**
 * 功能描述：<br>
 * 平台分类级联js
 * @author guoyongxiang
 */
$(function() {
	
	//切换分类类别
	$("#type").change(function() {
		var id = $(this).val();
		var url="provinceCitySort.do?id="+id+"&t="+new Date().getTime();
		if(id !=""&& id != 0){
			$.get(url, function(msg){
				$("#platform option").each(function() {                              
					$(this).remove();//移除原有项                          
				});
				//alert(msg);
				$("<option value=\"0\">=请选择=</option>").appendTo("#platform");   //添加一个默认项  
				$(msg).appendTo("#platform");//将返回来的项添加到下拉菜单中
				
				$("#category option").each(function() { //移除分类                             
					$(this).remove();//移除原有项                          
				});
				$("<option value=\"0\">=请选择=</option>").appendTo("#category");
			}); 
		}
	});
	
//切换分类平台
	$("#platform").change(function() {
		var platform = $(this).val();
		var type = $("#type").val();
		
		var url="getCategory.do?type="+type+"&platform="+platform;
		if(type!="" && type!= 0 && platform!=0){
			$.get(url, function(msg){
				$("#category option").each(function() {                              
					$(this).remove();//移除原有项                          
				});
				//alert(msg);
				$("<option value=\"0\">=请选择=</option>").appendTo("#category");   //添加一个默认项  
				$(msg).appendTo("#category");//将返回来的项添加到下拉菜单中
			});
		}else{
			$("#category option").each(function() { //移除分类                             
				$(this).remove();//移除原有项                          
			});
			$("<option value=\"0\">=请选择=</option>").appendTo("#category");
		}
	});
	//验证是否选择了分类
	$("#category").change(function() {
		var categoryId = $(this).val();
		//alert(categoryId);
		if(categoryId=='0' || categoryId==""){
			$(".tip_category").html("<font class='red'>*请选择分类！</font>");
		}else{
			$(".tip_category").html("");
		}
	});
	
});
//根据分类id获取分类信息
function getCategoryById(categoryId){
	var url="getCityId.do?categoryId="+categoryId+"&t="+new Date().getTime();
	var type;
	var platform;
	if(categoryId!=""){
		 $.ajax({   
             type:"post",   
             url:url,   
             dataType : "json", 
             success:function(msg){
			 	type = msg.parent_Id;
			 	platform = msg.id;
			 	$("#type").val(type);
			 	//获取平台
			 	getPlatform(type,platform);
             }   
         });   
	}
}

function getPlatform(type,platform){
	var url="provinceCitySort.do?id="+type+"&selectId="+platform;
	if(type !="" && type!= 0){
		$.get(url, function(msg){
			$("#platform option").each(function() {                              
				$(this).remove();//移除原有项                          
			});
			$("<option value=\"0\">=请选择=</option>").appendTo("#platform");   //添加一个默认项  
			$(msg).appendTo("#platform");//将返回来的项添加到下拉菜单中
			//$("#platform").append(msg);
			setTimeout(function() {//为兼容ie6特有的BUG所做的设置 
				$("#platform").val(platform);//选中默认的平台
			},1);	
		}); 
	}
}

