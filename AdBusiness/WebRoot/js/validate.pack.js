$(function() {
	var xOffset = -20; // x distance from mouse
	var yOffset = 20; // y distance from mouse
	$("input[reg],textarea[reg]").blur(function() {
		validate($(this), true);
	});
	$("select[reg]").blur(function() {
		validate($(this), true);
	});
	

});

function validate(obj, ajaxFlag) {
	var reg = new RegExp(obj.attr("reg"));
	var objValue = obj.attr("value");
	var objdefValue = obj.attr("defvalue");
	var objTip = obj.attr("tip");
	var objTips = obj.attr("tips");
	// 取得提示信息
	var objectName = (obj.context.name)?(obj.context.name):obj.attr("name");
	var spanName = '.' + objectName + 'Text';
	var span = $(spanName);
	if (obj.parent().parent().css("display") == "none") {
		return true;
	}
	if (objValue != '' || objdefValue == undefined) {
		if (!reg.test(objValue)) {
			if (obj.is(":input") || obj.is(":textarea")) {
				obj.addClass("input_validation-failed");
				if (span.length == 1) {
					span.remove();
				}
				$(
						'<span class="' + objectName
								+ 'Text" style="color: red">' + objTip
								+ '</span>').appendTo('.' + objectName);
			} else {
				obj.addClass("select_validation-failed");
			}
			return false;
		} else {
			if (obj.is(":input") || obj.is(":textarea")) {
				if (objectName == "confimPassword") {
					if ($("#confimPassword").val() != $("#password").val()) {
						obj.addClass("input_validation-failed");
						if (span.length == 1) {
							span.remove();
						}
						$(
								'<span class="' + objectName
										+ 'Text" style="color:red">' + objTips
										+ '</span>').appendTo(
								'.' + obj.context.name);
						return false;
					}
				}
				if (objectName == "email") {
					if (ajaxFlag) {
						exists(obj, span);
						var emailText = $(".emailText");
						if ($(".emailText").length >= 1) {
							return false;
						}
					}
				}
				if (span.length == 1) {
					span.remove();
				}
				obj.removeClass("input_validation-failed");
			} else {
				obj.removeClass("select_validation-failed");
			}
			return true;
		}
	} else {
		if (!reg.test(objdefValue)) {
			if (obj.is(":input") || obj.is(":textarea")) {
				obj.addClass("input_validation-failed");
				if (span.length == 1) {
					span.remove();
				}
				$(
						'<span class="' + objectName
								+ 'Text" style="color:red">' + objTip
								+ '</span>').appendTo('.' + obj.context.name);
			}
			return false;
		}
	}
	return true;
}

// 检查用户是否存在
function exists(obj, span) {
	var objectName = obj.context.name;
	var objValue = obj.context.value;
	var objTips = obj.attr("tips");
	var url = "ajax/checkEmail.action?email=" + objValue;
	if (objectName == "email") {
		if (objValue) {
			// $.get(url, {
			// "email" : objValue
			// }, function(data) {
			// alert(data);
			// }, 'json');
			jQuery.ajax({
				type : 'POST',
				contentType : 'application/json',
				url : url,
				data : {
					email : objValue
				},
				dataType : 'json',
				success : function(data) {
					if (data == true) {
						obj.addClass("input_validation-failed");
						if (span.length >= 1) {
							span.remove();
						}
						$(
								'<span class="' + objectName
										+ 'Text" style="color:red">' + objTips
										+ '</span>').appendTo(
								'.' + obj.context.name);
					} else if (data == false) {
						span.remove();
						if (span.length >= 1) {
							obj.removeClass("input_validation-failed");
						}
					}
				},
				error : function(data) {
				}
			});
		}
	}
}

// 检查code是否正确
function checkCode(obj, span) {
	var objectName = obj.context.name;
	var objValue = obj.context.value;
	var objTips = obj.attr("tips");
	var url = "ajax/checkCode.do?code=" + objValue;
	if (objectName == "code") {
		if (objValue) {
			// $.get(url, {
			// "email" : objValue
			// }, function(data) {
			// alert(data);
			// }, 'json');
			jQuery.ajax({
				type : 'POST',
				contentType : 'application/json',
				url : url,
				data : {
					code : objValue
				},
				dataType : 'json',
				success : function(data) {
					if (data == false) {
						obj.addClass("input_validation-failed");
						if (span.length >= 1) {
							span.remove();
						}
						$(
								'<span class="' + objectName
										+ 'Text" style="color:red">' + objTips
										+ '</span>').appendTo(
								'.' + obj.context.name);
					} else if (data == true) {
						span.remove();
						obj.removeClass("input_validation-failed");
					}
				},
				error : function(data) {
				}
			});
		}
	}
}

function vaildateForm(form){
	var isSubmit = true;
	var form=$('#'+form).find('input');
	for ( var i = 0; i < form.length; i++) {
		var input = $(form[i]);
		if (input.attr("reg") != null) {
			if (input.attr("disabled") != "disabled") {
				if (input.is(":input") || input.is(":textarea")) {
					if (!validate(input, false)) {
						isSubmit = false;
					} else if (input.context.name == "email") {
						if ($(".emailText").length >= 1) {
							isSubmit = false;
						}
					}
				} else if (input.is(":select")) {
					if (!validate(input, false)) {
						isSubmit = false;
					}
				}
			}
		}
	}
	if (isSubmit == true) {
		// document.getElementById("submit").disabled = true;
		$("#submit").attr("disabled", true);
		$(":input[type=button]").attr("disabled", "disabled");
	}
	return isSubmit;
}