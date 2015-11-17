/*
 *js多选框操作 
 */


// 反选
$(function() {
	$("#btn3").click(function() {
		$("[name='campaign_date']").each(function() {
			if (!$(this).is(":disabled")) {
				if ($(this).attr("checked")) {
					$(this).removeAttr("checked");
				} else {
					$(this).attr("checked", 'true');
				}
			}
		});
	});
});

$(function() {
	$("#btn4").click(function() {
		$("[name='checkboxtag']").each(function() {
			if (!$(this).is(":disabled")) {
				if ($(this).attr("checked")) {
					$(this).removeAttr("checked");
				} else {
					$(this).attr("checked", 'true');
				}
			}
		});
	});
});

//反选
$(function() {
	$("#btn5").click(function() {
		$("[name='checkbox']").each(function() {
			if (!$(this).is(":disabled")) {
				if ($(this).attr("checked")) {
					$(this).removeAttr("checked");
				} else {
					$(this).attr("checked", 'true');
				}
			}
		});
	});
});

//反选
$(function() {
	$("#btn6").click(function() {
		$("[name='dev_checkbox']").each(function() {
			if (!$(this).is(":disabled")) {
				if ($(this).attr("checked")) {
					$(this).removeAttr("checked");
				} else {
					$(this).attr("checked", 'true');
				}
			}
		});
	});
});

//反选
$(function() {
	$("#btn7").click(function() {
		$("[name='com_checkbox']").each(function() {
			if (!$(this).is(":disabled")) {
				if ($(this).attr("checked")) {
					$(this).removeAttr("checked");
				} else {
					$(this).attr("checked", 'true');
				}
			}
		});
	});
});