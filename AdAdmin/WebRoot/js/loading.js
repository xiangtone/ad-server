/**
 * 系统loading：遮罩当前页面，页面全部加装完毕后销毁。
 * (页面引入该文件即可)
 */
//系统加载中的遮罩层
document.write('<div id="waitingDiv"></div>');
//系统加载中进度条
document.write('<div id="loadingLayer" style="display:none;border:3px solid #98baec;width:261px;height:76px;background:#98baec;position:absolute;top:50%;left:50%;margin:-43px 0 0 -130px;z-index:9999;margin-left:-20px;">');
document.write('	<div style="background:#fff;border:1px solid #6599e9;background:#c9dfff url(images/lbs.gif) repeat-x;width:100%;height:100%;">');
document.write('		<span style="height:30px;display:inline-block;padding:10px 0 0 8px;font-size:12px">系统正在加载,请稍等...</span>');
document.write('		<b style="width:236px;height:14px;background:url(images/loading.gif) no-repeat;display:block;margin:8px 0 0 8px;"></b>');
document.write('	</div>');
document.write('</div>');

	 /*属性*/
   $("#waitingDiv").css({"position":"absolute", "text-align":"center", "top":"40px", 
	   					 "left":"180px", "right":"0px", "bottom":"0px", "background":"#eceaea",
	   					 "visibility":"visible", "filter":"Alpha(opacity=0.5)", "z-index":"999","opacity":"0.5"
   });
   /*高为屏幕的高*/
   $("#waitingDiv").css({
       height: function () {
           return $(document).height();
       },
       width:"100%"
   });
	$("#loadingLayer").show();
//}

	function hideLoadingDiv(){
		$("#waitingDiv").css({ "background": "", "visibility": "hidden", "filter": "", "z-index":""});
		$("#loadingLayer").hide();
	}
	function showLoadingDiv(){
		$("#waitingDiv").css({ "background": "#eceaea", "visibility": "visible", "filter": "Alpha(opacity=0.5)", "z-index":"9999","opacity":"0.5"
		});
		$("#loadingLayer").show();
	}
	
	function isPageReady(){
		if(document.readyState=="complete"){
			this.clearInterval(t_id);
			hideLoadingDiv();
		}
	}
	//var t_id = setInterval(isPageReady,20);