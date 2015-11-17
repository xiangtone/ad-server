$(function() {
	//文件格式验证
	$(".img").change(function(){//图片格式
		var url = this.value; 
		var fileext=url.substring(url.lastIndexOf("."),url.length);      
		fileext=fileext.toLowerCase();   
		if((fileext!='.jpg')&&(fileext!='.gif')&&(fileext!='.jpeg')&&(fileext!='.png')&&(fileext!='.bmp')){   
		       //alert("对不起，系统仅支持标准格式的照片，请您调整格式后重新上传！"); 
			$(".tip_img").html("<span class='red'>*图片格式不正确</span>");
		}else{
			$(".tip_img").html("");
		}
	});
	$(".file").change(function(){//安装包格式
		var url = this.value; 
		var fileext=url.substring(url.lastIndexOf("."),url.length);      
		fileext=fileext.toLowerCase();   
		//if((fileext!='.sis')&&(fileext!='.sisx')&&(fileext!='.apk')){
		if(fileext!='.apk'){   
		       // alert("格式不正确，请您调整格式后重新上传！");
			$(".tip_file").html("<span class='red'>*文件格式不正确</span>");
		}else{
			$(".tip_file").html("");
		}
	});
	$(".appSubmit").click(function(){
		var isSubmit = true;
		var url = $(".file").val(); 
		if(url!=""){
			var fileext=url.substring(url.lastIndexOf("."),url.length);      
			fileext=fileext.toLowerCase();   
			//if((fileext!='.sis')&&(fileext!='.sisx')&&(fileext!='.apk')){
			if(fileext!='.apk'){   
			       // alert("格式不正确，请您调整格式后重新上传！");
				isSubmit = false;
				$(".tip_file").html("<span class='red'>*文件格式不正确</span>");
			}else{
				$(".tip_file").html("");
			}
		}
		//===分类验证===
		var categoryId = $("#category").val();
		if(categoryId=='0' || categoryId==""){
			$(".tip_category").html("<font class='red'>*请选择分类！</font>");
			isSubmit = false;
		}else{
			$(".tip_category").html("");
		}
		//=============
		if(isSubmit){
			$("#updateAppManage").submit();
			setTimeout(function(){$(window).loading();},1500);
		}else{
			return false;
		}
	});
	$(".addAppSubmit").click(function(){
		var isSubmit = true;
		var url = $(".file").val(); 
		if(url!=""){
			var fileext=url.substring(url.lastIndexOf("."),url.length);      
			fileext=fileext.toLowerCase();   
			//if((fileext!='.sis')&&(fileext!='.sisx')&&(fileext!='.apk')){
			if(fileext!='.apk'){   
			       // alert("格式不正确，请您调整格式后重新上传！");
				isSubmit = false;
				$(".tip_file").html("<span class='red'>*文件格式不正确</span>");
			}else{
				$(".tip_file").html("");
			}
		}else{
			//isSubmit = false;
			//$(".tip_file").html("<span class='red'>*请选择安装包</span>");
		}
		//===分类验证===
		var categoryId = $("#category").val();
		if(categoryId=='0' || categoryId==""){
			$(".tip_category").html("<font class='red'>*请选择分类！</font>");
			isSubmit = false;
		}else{
			$(".tip_category").html("");
		}
		//=============
		if(isSubmit){
			$("#addAppManage").submit();
			setTimeout(function(){$(window).loading();},1500);
		}else{
			return false;
		}
	});
	$(".finaSubmit").click(function(){
		var isSubmit = true;
		var url = $(".img").val(); 
		if(url!=""){
			var fileext=url.substring(url.lastIndexOf("."),url.length);      
			fileext=fileext.toLowerCase();   
			if((fileext!='.jpg')&&(fileext!='.gif')&&(fileext!='.jpeg')&&(fileext!='.png')&&(fileext!='.bmp')){   
				//alert("对不起，系统仅支持标准格式的照片，请您调整格式后重新上传！");
				isSubmit = false;
				$(".tip_img").html("<span class='red'>*图片格式不正确</span>");
			}else{
				$(".tip_img").html("");
			}
		}
		if(isSubmit){
			$("#updateFnancialInfoManage").submit();
		}else{
			return false;
		}
	});
	
});
