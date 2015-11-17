/*
 * 加载页js弹出层
 * 用于反应缓慢时的中间呈现效果
 */
$(document).ready(function(){
	/*$(this).submit(function(){
		setTimeout(loading,1500);
	});*/
	//弹出层();
	var doucment_dom = document.documentElement?document.documentElement:document.body;
	//alert(doucment_dom);
	$.fn.extend({
		loading:function(){
			var doucment_left = document.documentElement.scrollLeft||document.body.scrollLeft;
			var doucment_top = document.documentElement.scrollTop||document.body.scrollTop;
			var ifram = document.createElement("iframe");
			$("body").append(ifram);
			ifram.id = "loading";
			$("#loading").css({"position":"absolute","border":"0","top":"0","left":"0","z-index":"9999","background-color":"#000","width":doucment_dom.clientWidth,"height":document.body.scrollHeight,"opacity":"0","filter":"progid:DXImageTransform.Microsoft.Alpha(opacity=50)","-ms-filter": "progid:DXImageTransform.Microsoft.Alpha(opacity=50)"});
			$("#loading").fadeTo("normal","0.5",function(){
				var img = document.createElement("img");
				img.src = "js/loading/loading.gif";
				img.id = "load_img";
				var div = document.createElement("div");
				div.id = "load_div";
				$(div).html("加载中...<br /><img src='js/loading/loading.gif' id='load_img' />");
				$(div).css({"position":"absolute","color":"#fff","z-index":"999999","top":(doucment_dom.clientHeight-$("#load_img").height())/2+doucment_top,"left":(doucment_dom.clientWidth-$("#load_img").width())/2});$("body").append(div);
			});
		}
	});
	
	$(window).resize(function(){
		var doucment_left = document.documentElement.scrollLeft||document.body.scrollLeft;
		var doucment_top = document.documentElement.scrollTop||document.body.scrollTop;
	 	$("#loading").css({"width":$("body").width()+doucment_left,"height":document.body.scrollHeight});
		$("#load_div").css({"top":(doucment_dom.clientHeight-$("#load_img").height())/2+doucment_top,"left":(doucment_dom.clientWidth-$("#load_img").width())/2+doucment_left});
	});
	$(window).scroll(function(){
		var doucment_left = document.documentElement.scrollLeft||document.body.scrollLeft;
		var doucment_top = document.documentElement.scrollTop||document.body.scrollTop;
	 	$("#loading").css({"width":doucment_dom.clientWidth+doucment_left,"height":document.body.scrollHeight});
		$("#load_div").css({"top":(doucment_dom.clientHeight-$("#load_img").height())/2+doucment_top,"left":(doucment_dom.clientWidth-$("#load_img").width())/2+doucment_left});
	});
	//弹出层end;
	
	//隐藏层
	function hideLoad(){
		if($("iframe").length!=0){
			$("#loading,#load_div").hide();
		}
	}
	
});
