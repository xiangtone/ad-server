/**
 * 功能描述：<br>
 * 平台分类级联js
 * @author guoyongxiang
 */
$(function() {

	//切换分类类别
	$("#type").change(function() {  
		var parent_id = $(this).val();
		
		var url="/AdPlatform/getPlatform.do?parent_id="+parent_id;
		if(parent_id !="" && parent_id != 0){
			$.get(url, function(msg){
				$("#platform option").each(function() {                              
					$(this).remove();//移除原有项                          
				});
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


function getPlatform(type,platform,categoryId){
	var url="getPlatform.do?parent_id="+type;
	if(type !="" && type!= 0){
		$.get(url, function(msg){
			$("#platform option").each(function() {                              
				$(this).remove();//移除原有项                          
			});
			//alert(msg);
			$("<option value=\"0\">=请选择=</option>").appendTo("#platform");   //添加一个默认项  
			$(msg).appendTo("#platform");//将返回来的项添加到下拉菜单中
			//$("#platform").append(msg);
			setTimeout(function() {//为兼容ie6特有的BUG所做的设置 
				$("#platform").val(platform);//选中默认的平台
			},1);	
			
			
			$("#category option").each(function() { //移除分类                             
				$(this).remove();//移除原有项                          
			});
			$("<option value=\"0\">=请选择=</option>").appendTo("#category");
			//查询分类
			var url1="getCategory.do?type="+type+"&platform="+platform;
			if(type!="" && type!= 0 && platform!=0){
				$.get(url1, function(msg){
					$("#category option").each(function() {                              
						$(this).remove();//移除原有项                          
					});
					//alert(msg);
					$("<option value=\"0\">=请选择=</option>").appendTo("#category");   //添加一个默认项  
					$(msg).appendTo("#category");//将返回来的项添加到下拉菜单中
					setTimeout(function() {//为兼容ie6特有的BUG所做的设置 
						$("#category").val(categoryId);//选中默认的分类
					},1);	
				});
			}
			//======
		}); 
	}
}
//付费条件显示的判断
function getPoint_desc_statusById(point_desc_status,point_desc){
	if(point_desc_status!=""){
		 $.ajax({   
	        success:function(msg){   
			 	$("#point_desc_status").val(point_desc_status);
	        }   
	    });
		 if(point_desc_status=="3"){
			 point_desc.style.display="";
		 }
	}
	}
//虚拟货币校验
function abcdef(id) {
	var tag = document.getElementById("id");
	    tag.value=id;
	var url="getCurrency.do?id="+id;
	if(id !="" && id != 0){
		$.get(url, function(msg){
			var control = document.getElementById("control");
			var control_img = document.getElementById("control_img");
			var status = document.getElementById("status");
			var message = document.getElementById("message");
			if(msg!="null"){
				var money_score = document.getElementById("money_score_rate").value;
				var tt=eval("(" + msg + ")");
				message.innerHTML = "建议开发者设置商品单价为<font color=#ff0000 size=5>"+tt.exchange_rate_rmb/money_score+"</font>的倍数,提高货币转换精度。";
				
				$("#virtual_currency_name").val(tt.virtual_currency_name);
				$("#exchange_rate_rmb").val(tt.exchange_rate_rmb);
				$("#application").val(tt.application);
				
				
				if(tt.status==0){
					control_img.src="images/off.png";
					status.value=0;
					control.innerHTML="关闭";
				}else{
					control_img.src="images/on.png";
					status.value=1;
					control.innerHTML="开启";
				}
				msg = null;
				
			}else{
				message.innerHTML = "";
				$("#virtual_currency_name").val("");
				$("#exchange_rate_rmb").val("");
				$("#application").val("");
				control_img.src="images/on.png";
				status.value=1;
				control.innerHTML="开启";
			}
			
		});
	}
}
