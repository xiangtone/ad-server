$(function() {
	var winh = $(window).height(), currentpage = 1, // 当前所在位置
	pagecount = 5, // 总页数
	isscroll = true;
	var _isMobile = /android|ipad|iphone|midp|rv:1.2.3.4|ucweb|windows ce|windows mobile/i
			.test(navigator.userAgent); // true：移动设备
	// var ie = !-[1,]; //ie8及以下
	var ie = navigator.userAgent.indexOf('MSIE') >= 0 ? true : false;
	var common = function() {
		if (currentpage == (pagecount - 1)) {
			// alert(5);
			setTimeout(function() {
				$(".page5 .content .users span").css({
					"transform" : "rotateY(0deg)"
				});
				$(".page5 .content .users span").css({
					"-webkit-transform" : "rotateY(0deg)"
				});
				$(".page5 .content .users span").css({
					"-moz-transform" : "rotateY(0deg)"
				});
				$(".page5 .content .users span").css({
					"-o-transform" : "rotateY(0deg)"
				});
			}, 0);
		}
	};

	window.onresize = function() {
		winh = $(window).height();
		winw = $(window).width();
		$(".section").css(
				{
					"transform" : "translate3d(0,"
							+ (-(currentpage - 1) * winh) + "px,0)"
				});
		$(".section").css(
				{
					"-webkit-transform" : "translate3d(0,"
							+ (-(currentpage - 1) * winh) + "px,0)"
				});
		$(".section").css(
				{
					"-moz-transform" : "translate3d(0,"
							+ (-(currentpage - 1) * winh) + "px,0)"
				});
		$(".section").css(
				{
					"-o-transform" : "translate3d(0,"
							+ (-(currentpage - 1) * winh) + "px,0)"
				});
		$(".section").css(
				{
					"-ms-transform" : "translate3d(0,"
							+ (-(currentpage - 1) * winh) + "px,0)"
				});
	}

	// if(ie){
	// $("html,body").css("overflow","visible");
	// }

	// if(ie) {
	if (getQuery("index", "", "?")) {
		currentpage = getQuery("index", "", "?");
		$(".section").css(
				{
					"transform" : "translate3d(0,"
							+ (-(currentpage - 1) * winh) + "px,0)"
				});
		$(".section").css(
				{
					"-webkit-transform" : "translate3d(0,"
							+ (-(currentpage - 1) * winh) + "px,0)"
				});
		$(".section").css(
				{
					"-moz-transform" : "translate3d(0,"
							+ (-(currentpage - 1) * winh) + "px,0)"
				});
		$(".section").css(
				{
					"-o-transform" : "translate3d(0,"
							+ (-(currentpage - 1) * winh) + "px,0)"
				});
		$(".section").css(
				{
					"-ms-transform" : "translate3d(0,"
							+ (-(currentpage - 1) * winh) + "px,0)"
				});
		common();
		if (currentpage > 1) {
			$(".header").addClass("m-header");
		} else {
			$(".header").removeClass("m-header");
		}
		$(".tag-list ul li").removeClass("on");
		$(".tag-list ul li").eq(currentpage - 1).addClass("on");
	}
	// }

	// 切换page小按钮
	$(".tag-list ul li").each(
			function(index) {
				$(this).click(
						function() {
							$(".tag-list ul li").removeClass("on");
							$(this).addClass("on");
							$(".section").css(
									{
										"transform" : "translate3d(0,"
												+ (-index * winh) + "px,0)"
									});
							$(".section").css(
									{
										"-webkit-transform" : "translate3d(0,"
												+ (-index * winh) + "px,0)"
									});
							$(".section").css(
									{
										"-moz-transform" : "translate3d(0,"
												+ (-index * winh) + "px,0)"
									});
							$(".section").css(
									{
										"-o-transform" : "translate3d(0,"
												+ (-index * winh) + "px,0)"
									});
							$(".section").css(
									{
										"-ms-transform" : "translate3d(0,"
												+ (-index * winh) + "px,0)"
									});
							currentpage = index + 1;
							changeUrl(currentpage);
							if (currentpage > 1) {
								$(".header").addClass("m-header");
							} else {
								$(".header").removeClass("m-header");
							}
							common();
						})
			});

	// 上下键监听
	$(document)
			.keydown(
					function(event) {
						// alert(event.keyCode);
						if (event.keyCode == 38) { // 上
							if (currentpage != 1 && isscroll) {
								isscroll = false;
								$(".section")
										.css(
												{
													"transform" : "translate3d(0,"
															+ (-(currentpage - 2) * winh)
															+ "px,0)"
												});
								$(".section")
										.css(
												{
													"-webkit-transform" : "translate3d(0,"
															+ (-(currentpage - 2) * winh)
															+ "px,0)"
												});
								$(".section")
										.css(
												{
													"-moz-transform" : "translate3d(0,"
															+ (-(currentpage - 2) * winh)
															+ "px,0)"
												});
								$(".section")
										.css(
												{
													"-o-transform" : "translate3d(0,"
															+ (-(currentpage - 2) * winh)
															+ "px,0)"
												});
								$(".section")
										.css(
												{
													"-ms-transform" : "translate3d(0,"
															+ (-(currentpage - 2) * winh)
															+ "px,0)"
												});
								currentpage--;
								// if(currentpage == pagecount){
								// $("html,body").css("overflow","hidden");
								// }
								if (currentpage > 1) {
									$(".header").addClass("m-header");
								} else {
									$(".header").removeClass("m-header");
								}
								$(".tag-list ul li").removeClass("on");
								$(".tag-list ul li").eq(currentpage - 1)
										.addClass("on");
								changeUrl(currentpage);
								setTimeout(function() {
									isscroll = true;
								}, 300);
							}
							common();
						} else if (event.keyCode == 40) { // 下
							if (currentpage != pagecount && isscroll) {
								isscroll = false;
								$(".section").css(
										{
											"transform" : "translate3d(0,"
													+ (-currentpage * winh)
													+ "px,0)"
										});
								$(".section")
										.css(
												{
													"-webkit-transform" : "translate3d(0,"
															+ (-currentpage * winh)
															+ "px,0)"
												});
								$(".section").css(
										{
											"-moz-transform" : "translate3d(0,"
													+ (-currentpage * winh)
													+ "px,0)"
										});
								$(".section").css(
										{
											"-o-transform" : "translate3d(0,"
													+ (-currentpage * winh)
													+ "px,0)"
										});
								$(".section").css(
										{
											"-ms-transform" : "translate3d(0,"
													+ (-currentpage * winh)
													+ "px,0)"
										});
								currentpage++;
								// if(currentpage == pagecount){
								// $("html,body").css("overflow","visible");
								// }
								if (currentpage > 1) {
									$(".header").addClass("m-header");
								} else {
									$(".header").removeClass("m-header");
								}
								$(".tag-list ul li").removeClass("on");
								$(".tag-list ul li").eq(currentpage - 1)
										.addClass("on");
								changeUrl(currentpage);
								setTimeout(function() {
									isscroll = true;
								}, 300);
							}
							common();
						}
					});

	// 根据屏幕高度设定
	$(".section .page .content").each(function(index) {
		// alert($(this).height());
		// alert(winh);

		var this_h = $(this).height();
		if (index == (pagecount - 1)) {
			$(this).css("padding-top", (winh) / 12);
		} else {
			$(this).css("padding-top", (winh) / 12);
		}
	});
	$(".section .page1 .content .immg").each(function(index) {
		$(this).css("margin-top", (5 * (winh - (winh / 12))) / 7);
	});
	$(".section .page2 .content .pay").each(function(index) {
		$(this).css("padding-top", winh / 16);
	});
	$(".section .page2 .content .thumbnails").each(function(index) {
		$(this).css("padding-top", winh/ 60);
	});
	$(".section .page2 .container .none1").each(function(index) {
		$(this).css("padding-top", (winh-$(".section .page2 .content").height()- winh/12-2*$(this).height()) / 4);
	});
	$(".section .page .gotop").each(function(index) {
		$(this).css("padding-top", (4 * winh) / 5);
	});
	$(".section .page3 .content").each(function(index) {
		$(this).css("padding-top", winh / 16);
	});
	$(".section .page3 .content .uul").each(function(index) {

		$(this).css("padding-top", winh/2.8);
	});
	$(".section .page4 .production").each(function(index) {
		$(this).css("padding-top",(winh - $(".section .page4 .content").height()- (winh / 12) - 517) / 2);
	});
	$(".section .page6 .content").each(function(index) {
		$(this).css("padding-bottom", (winh) / 24);
	});
	$(".section .page6 .content .con").each(function(index) {
		$(this).css("margin-top", (winh / 20)+(winh/24));
	});
	$(".section .page6 .content .right").each(function(index) {
		$(this).css("margin-top", (winh / 20)+(winh/24)+15);
	});
	$("#footer").each(function(index) {
		$(this).css("margin-top",winh - $(".section .page6 .content").height()- $(this).height()- $(".section .page6 .footer-record").height()- (winh / 24) - 30 - (winh / 20));
	});

	// 导航 更多
	if (_isMobile) {
		$(".header .nav ul li.more").bind("click", function() {
			$(this).find("div").toggle();
		})
	}

	// var tag_list = $(this).height();
	$(".tag-list").css("top", (winh - $(".tag-list").height()) / 2);

	function scrollEvent(e) {
		e = e || window.event;
		if (e.wheelDelta > 0 || e.detail < 0) { // 此处向上的动作
			// alert("上");
			if (currentpage != 1 && isscroll) {
				isscroll = false;
				$(".section").css(
						{
							"transform" : "translate3d(0,"
									+ (-(currentpage - 2) * winh) + "px,0)"
						});
				$(".section").css(
						{
							"-webkit-transform" : "translate3d(0,"
									+ (-(currentpage - 2) * winh) + "px,0) "
						});
				$(".section").css(
						{
							"-moz-transform" : "translate3d(0,"
									+ (-(currentpage - 2) * winh) + "px,0)"
						});
				$(".section").css(
						{
							"-o-transform" : "translate3d(0,"
									+ (-(currentpage - 2) * winh) + "px,0)"
						});
				$(".section").css(
						{
							"-ms-transform" : "translate3d(0,"
									+ (-(currentpage - 2) * winh) + "px,0)"
						});
				$(".section").css({
					"-webkit-perspective" : "1000"
				});
				$(".section").css({
					"-moz-perspective" : "1000"
				});
				$(".section").css({
					"perspective" : "1000"
				});
				currentpage--;
				// if(currentpage == pagecount){
				// $("html,body").css("overflow","hidden");
				// }
				if (currentpage > 1) {
					$(".header").addClass("m-header");
				} else {
					$(".header").removeClass("m-header");
				}
				$(".tag-list ul li").removeClass("on");
				$(".tag-list ul li").eq(currentpage - 1).addClass("on");
				changeUrl(currentpage);
				setTimeout(function() {
					isscroll = true;
				}, 1300);
			}
			common();
		} else if (e.wheelDelta < 0 || e.detail > 0) { // 此处向下的动作
			// alert("下");
			if (currentpage != pagecount && isscroll) {
				isscroll = false;
				$(".section").css(
						{
							"transform" : "translate3d(0,"
									+ (-currentpage * winh) + "px,0)"
						});
				$(".section").css(
						{
							"-webkit-transform" : "translate3d(0,"
									+ (-currentpage * winh) + "px,0)"
						});
				$(".section").css(
						{
							"-moz-transform" : "translate3d(0,"
									+ (-currentpage * winh) + "px,0)"
						});
				$(".section").css(
						{
							"-o-transform" : "translate3d(0,"
									+ (-currentpage * winh) + "px,0)"
						});
				$(".section").css(
						{
							"-ms-transform" : "translate3d(0,"
									+ (-currentpage * winh) + "px,0)"
						});

				$(".section").css({
					"-webkit-perspective" : "1000"
				});
				$(".section").css({
					"-moz-perspective" : "1000"
				});
				$(".section").css({
					"perspective" : "1000"
				});
				currentpage++;
				// if(currentpage == pagecount){
				// $("html,body").css("overflow","visible");
				// }
				if (currentpage > 1) {
					$(".header").addClass("m-header");
				} else {
					$(".header").removeClass("m-header");
				}
				$(".tag-list ul li").removeClass("on");
				$(".tag-list ul li").eq(currentpage - 1).addClass("on");
				changeUrl(currentpage);
				setTimeout(function() {
					isscroll = true;
				}, 1300);
			}
			common();
		}
	}

	// 立即体验
	$("#demo").bind("click", function() {
		$.ajax({
			type : "POST",
			url : "/common/data/demo.php",
			data : {},
			datatype : "json",
			success : function(data) {
				data = JSON.parse(data);
				// if(data.resultCode == 0){
				// location.href = "/dashboard/";
				// }
				location.href = "/dashboard/";
			},
			error : function() {
				alert("error");
			}
		});
	});

	// function isIE(){
	// if(!!window.ActiveXObject || "ActiveXObject" in window){
	// return true;
	// }else{
	// return false;
	// }
	// }
	//
	// function IEVersion(){
	// var rv = -1;
	// if (navigator.appName == 'Microsoft Internet Explorer'){
	// var ua = navigator.userAgent;
	// var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
	// if (re.exec(ua) != null)
	// rv = parseFloat( RegExp.$1 );
	// }else if (navigator.appName == 'Netscape'){
	// var ua = navigator.userAgent;
	// var re = new RegExp("Trident/.*rv:([0-9]{1,}[\.0-9]{0,})");
	// if (re.exec(ua) != null)
	// rv = parseFloat( RegExp.$1 );
	// }
	// return rv;
	// }
	// if (isIE()) {
	// if (IEVersion() < 10) {
	// if (IEVersion() > 7) {
	// window.location.href = "";
	// }else{
	// window.location.href = "";
	// };
	// };
	// };

	// if(navigator.userAgent.indexOf('MSIE') >= 0){
	// alert(1);
	// }

	// if(!ie){
	if (document.addEventListener) {
		document.addEventListener('DOMMouseScroll', scrollEvent, false);
	}
	document.onmousewheel = scrollEvent;
	// }

	// 移动适配
	$(".header .nav button").bind("click", function() {
		var isShow = $(this).attr("data-isshow");
		if (isShow == "false") {
			$(".header .nav .loginbar,.header .nav ul").addClass("show");
			$(this).attr("data-isshow", "true");
		} else {
			$(".header .nav .loginbar,.header .nav ul").removeClass("show");
			$(this).attr("data-isshow", "false");
		}
	});
	// 获取链接中参数
	function getQuery(name, def, symbol) {
		symbol = symbol || '#';
		var reg = new RegExp("(^|\\" + symbol + "|&)" + name
				+ "=([^&]*)(\\s|&|$)", "i");
		if (reg.test(location.href))
			return decodeURI(RegExp.$2.replace(/\+/g, " "));

		return def;
	}
	// 改变url便于返回首页到之前位置
	function changeUrl(index) {
		history.replaceState && history.replaceState({}, '', "?index=" + index);
	}
});

function getArrayItems(arr, num) {
	// 新建一个数组,将传入的数组复制过来,用于运算,而不要直接操作传入的数组;
	var temp_array = new Array();
	for ( var index in arr) {
		temp_array.push(arr[index]);
	}
	// 取出的数值项,保存在此数组
	var return_array = new Array();
	for (var i = 0; i < num; i++) {
		// 判断如果数组还有可以取出的元素,以防下标越界
		if (temp_array.length > 0) {
			//在数组中产生一个随机索引
			var arrIndex = Math.floor(Math.random() * temp_array.length);
			//将此随机索引的对应的数组元素值复制出来
			return_array[i] = temp_array[arrIndex];
			//然后删掉此索引的数组元素,这时候temp_array变为新的数组
			temp_array.splice(arrIndex, 1);
		} else {
			//数组中数据项取完后,退出循环,比如数组本来只有10项,但要求取出20项.
			break;
		}
	}
	return return_array;
}