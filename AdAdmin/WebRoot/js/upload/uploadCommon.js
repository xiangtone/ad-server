	//开发者修改应用
	function appUpdate(data){
		var errMsg;
		if(typeof(data.fall) != 'undefined'){
			$(".uploadMsg").html("<font color='red'>"+data.fall[0].errorMsg+"</font>");
		}else{
			var path = data.succeed[0].path;
			var newFileName = data.succeed[0].newFileName;
			var savePath = path+newFileName;
			var temp = path.substring(0,path.length-1);
			var appid =  temp.substring(temp.lastIndexOf("/")+1);
			savePath = encodeURIComponent(savePath);//编码
			//解析返回数据
			var url="updateAppDate.do?id="+appid+"&path="+savePath;
			url = encodeURI(url);//url编码
			//ajax修改后台应用信息
			if(typeof(data.succeed) != 'undefined'){
				//alert("成功");
				$.ajax({
					type:"get",   
					url:url,   
					dataType : "json", 
					success:function(msg){
					//alert("请求成功："+msg);
						if(msg.status!="err"){
							//显示下载链接
							$(".uploadMsg").html("<a href='"+msg.urlPrefix+msg.path+"'>"+newFileName+"</a>");
						}else{
							errMsg = msg.errMag;
							$(".uploadMsg").html("<font color='red'>"+errMsg+"</font>");
						}
			 	
					}   
				});
			}
		}
	}
	
	
	
	function appIconUpdate(data){
		var errMsg;
		if(typeof(data.fall) != 'undefined'){
			$(".iconMsg").html("<font color='red'>"+data.fall[0].errorMsg+"</font>");
		}else{
			var path = data.succeed[0].path;
			var newFileName = data.succeed[0].newFileName;
			var savePath = data.succeed[0].path+data.succeed[0].newFileName;
			var temp = path.substring(0,path.length-1);
			var appid =  temp.substring(temp.lastIndexOf("/")+1);
			
			//解析返回数据
			var url="updateAppIcon.do?id="+appid+"&path="+savePath;
			//ajax修改后台应用信息
			if(typeof(data.succeed) != 'undefined'){
				//alert("成功");
				$.ajax({
					type:"post",   
					url:url,   
					dataType : "json", 
					success:function(msg){
					//alert("请求成功："+msg);
						if(msg.status!="err"){
							//显示预览
							$(".iconMsg").html("<img alt='icon' src='"+msg.urlPrefix+msg.path+"'>");
						}else{
							errMsg = msg.errMag;
							$(".iconMsg").html("<font color='red'>"+errMsg+"</font>");
						}
			 	
					}   
				});
			}
		}
	}
		
	function adIconUpdate(data){
		var errMsg;
		if(typeof(data.fall) != 'undefined'){
			$(".iconMsg").html("<font color='red'>"+data.fall[0].errorMsg+"</font>");
		}else{
			var path = data.succeed[0].path;
			var newFileName = data.succeed[0].newFileName;
			var savePath = data.succeed[0].path+data.succeed[0].newFileName;
			var temp = path.substring(0,path.length-1);
			var appid =  temp.substring(temp.lastIndexOf("/")+1);
				
			//解析返回数据
			var url="updateAdIcon.do?id="+appid+"&path="+savePath;
			//ajax修改后台应用信息
			if(typeof(data.succeed) != 'undefined'){
				//alert("成功");
				$.ajax({
					type:"post",   
					url:url,   
					dataType : "json", 
					success:function(msg){
					//alert("请求成功："+msg);
						if(msg.status!="err"){
							//显示预览
							$(".iconMsg").html("<img alt='icon' src='"+msg.urlPrefix+msg.path+"'>");
						}else{
							errMsg = msg.errMag;
							$(".iconMsg").html("<font color='red'>"+errMsg+"</font>");
						}
				 	
					}   
				});
			}
		}
	}
	
	function appss1(data){
		appScreenshotAdd(data,1);
	}
	function appss2(data){
		appScreenshotAdd(data,2);
	}
	function appss3(data){
		appScreenshotAdd(data,3);
	}
	
	function appScreenshotAdd(data,sort){
		var errMsg;
		if(typeof(data.fall) != 'undefined'){
			$(".previewMsg"+sort).html("<font color='red'>"+data.fall[0].errorMsg+"</font>");
		}else{
			//alert(data.succeed);
			var succeeds = data.succeed;
			var paths = new Array();
			for(key in succeeds){
				paths.push(succeeds[key].path+succeeds[key].newFileName);
				temp = succeeds[key].path.substring(0,succeeds[key].path.length-1);
			}
			var temp = data.succeed[0].path.substring(0,data.succeed[0].path.length-1);
			var appid =  temp.substring(temp.lastIndexOf("/")+1);
			//解析返回数据
			var url="updateAppScreenshot.do?id="+appid+"&paths="+paths+"&sort="+sort;
			//ajax修改后台应用信息
			if(typeof(data.succeed) != 'undefined'){
				//alert("成功");
				$.ajax({
					type:"post",   
					url:url,   
					dataType : "json", 
					success:function(msg){
					//alert("请求成功："+msg);
						if(msg.status!="err"){
							//显示预览
							$(".previewMsg"+sort).html("<img alt='preview' src='"+msg.urlPrefix+msg.path+"'>");
						}else{
							errMsg = msg.errMag;
							$(".previewMsg"+sort).html("<font color='red'>"+errMsg+"</font>");
						}
				 	
					}   
				});
			}
		}
	}
	
	
	function adss1(data){
		adScreenshotAdd(data,1)
	}
	function adss2(data){
		adScreenshotAdd(data,2)
	}
	function adss3(data){
		adScreenshotAdd(data,3)
	}
	
	function adScreenshotAdd(data,sort){
		var errMsg;
		if(typeof(data.fall) != 'undefined'){
			$(".previewMsg"+sort).html("<font color='red'>"+data.fall[0].errorMsg+"</font>");
		}else{
			//alert(data.succeed);
			var succeeds = data.succeed;
			var paths = new Array();
			for(key in succeeds){
				paths.push(succeeds[key].path+succeeds[key].newFileName);
				temp = succeeds[key].path.substring(0,succeeds[key].path.length-1);
			}
			var temp = data.succeed[0].path.substring(0,data.succeed[0].path.length-1);
			var appid =  temp.substring(temp.lastIndexOf("/")+1);
			//解析返回数据
			var url="updateAdScreenshot.do?id="+appid+"&paths="+paths+"&sort="+sort;
			//ajax修改后台应用信息
			if(typeof(data.succeed) != 'undefined'){
				//alert("成功");
				$.ajax({
					type:"post",   
					url:url,   
					dataType : "json", 
					success:function(msg){
					//alert("请求成功："+msg);
						if(msg.status!="err"){
							//显示预览
							$(".previewMsg"+sort).html("<img alt='preview' src='"+msg.urlPrefix+msg.path+"'>");
						}else{
							errMsg = msg.errMag;
							$(".previewMsg"+sort).html("<font color='red'>"+errMsg+"</font>");
						}
				 	
					}   
				});
			}
		}
	}
		
