function resizeWin(){
	var c_height=document.documentElement.clientHeight;
	var c_head=40;
	var c_content=c_height-c_head;
	$("#scroll_bar").css("height",c_content-5);
	$('.admin_right').css("height",c_content-5);
	//$('.admin_main').css("height",c_content-100);
	var w=document.documentElement.clientWidth;
	var c_width;
	if(!-[1,]&&!window.XMLHttpRequest){//判断是否是ie6
		$('body').css("height",c_height);
		$('body').css("overflow","hidden");
		c_width=w-193;
	}else{
		c_width=w-190;
	}
	$('.admin_right').css("width",c_width);
}