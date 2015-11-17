/**
 * 运营单个审核处理
 */
function singleDealAuditInOper(id, status) {
	var managerDesc = document.getElementById(id + "managerDesc");// 运营说明
	var managerMoney = document.getElementById(id + "managerMoney")// 运营确认金额
	var applyMoney = document.getElementById(id + "applyMoney")// 申请金额
	var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
	var msg = "";

	if (!doubleReg.test(managerMoney.value)) {
		alert("运营确认金额不能小于1元并且必须是整数或者小数，小数最多保留两位！");
		managerMoney.focus();
		return;
	} else if (managerMoney.value < 1) {
		alert("运营确认金额不能小于1元并且必须是整数或者小数，小数最多保留两位！");
		managerMoney.focus();
		return;
	} else if (managerMoney.value > applyMoney.value * 1) {
		alert("运营确认金额不能大于申请金额并且必须是整数或者小数，小数最多保留两位！");
		managerMoney.focus();
		return;
	}

	if (status == 1) {// 通过
		msg = "确认要审核通过吗？";
		var flag = confirm(msg);
		if (flag) {
			document.getElementById("mDesc").value = " ";
			document.getElementById("mMoney").value = managerMoney.value;
			// document.getElementById("id").value = id;
			// document.getElementById("status").value = status;
			$("#status").attr("value", status);
			$("#id").attr("value", id);
			$("#batchForm").attr("action", "manage!singleAuditOper.do")
					.submit();
		}
	} else {// 不通过
		var reason = window.prompt("请填写驳回理由（20个字符以内）", "");
		if (reason && reason.length < 50) {
			document.getElementById("mDesc").value = reason;
			document.getElementById("mMoney").value = managerMoney.value;
			$("#status").attr("value", status);
			$("#id").attr("value", id);
			$("#batchForm").attr("action", "manage!singleAuditOper.do")
					.submit();
		} else {
			return;
		}
	}
}

/**
 * 运营批量审核处理
 */
function batchDealAuditInOper(status) {
	var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
	var checkedLength = $("input:checked").length;// 被选中的数量
	if (checkedLength > 0) {
		var msg = "";
		var mMoneys = new Array()// 存放金额数组
		var mDescs = new Array()// 存放说明数组
		for ( var i = 0; i < checkedLength; i++) {
			var id = $("input:checked")[i].value;
			mDescs[i] = " ";
			// 验证金额
			var mMoney = document.getElementById(id + "managerMoney").value;// 运营确认金额
			var applyMoney = document.getElementById(id + "applyMoney")// 申请金额

			if (!doubleReg.test(mMoney)) {// 金额，小数点后边最多两位小数
				alert("运营确认金额不能小于1元并且必须是整数或者小数，小数最多保留两位！");
				document.getElementById(id + "managerMoney").focus();
				return;
			} else if (mMoney < 1) {
				alert("运营确认金额不能小于1元并且必须是整数或者小数，小数最多保留两位！");
				document.getElementById(id + "managerMoney").focus();
				return;
			} else if (mMoney > applyMoney.value * 1) {
				alert("运营确认金额不能大于申请金额并且必须是整数或者小数，小数最多保留两位！");
				document.getElementById(id + "managerMoney").focus();
				return;
			}
			mMoneys[i] = mMoney;
		}

		if (status == 1) {
			msg = "确认要审核通过吗？";
			var flag = confirm(msg);
			if (flag) {
				document.getElementById("mMoneys").value = mMoneys;
				document.getElementById("mDescs").value = mDescs;
				// document.getElementById("status").value = status;
				$("#status").attr("value", status);
				$("#batchForm").attr("action", "manage!batchAuditOper.do")
						.submit();
			}
		} else {// 不通过
			var reason = window.prompt("请填写驳回理由（20个字符以内）", "");
			if (reason && reason.length < 20) {
				for ( var i = 0; i < checkedLength; i++) {
					mDescs[i] = reason;
				}
				document.getElementById("mMoneys").value = mMoneys;
				document.getElementById("mDescs").value = mDescs;
				// document.getElementById("status").value = status;
				$("#status").attr("value", status);
				$("#batchForm").attr("action", "manage!batchAuditOper.do")
						.submit();
			} else {
				return;
			}
		}

	} else {
		alert("请选择要操作的数据！");
		return;
	}
}


/**
 * 运营渠道单个审核处理
 */
function singleChaDealAuditInOper(id, status) {
	var managerDesc = document.getElementById(id + "managerDesc");// 运营说明
	var managerMoney = document.getElementById(id + "managerMoney");// 运营确认金额
	var invoice_status = document.getElementById(id + "invoice_status");// 发票状态
	var invoice_suv = document.getElementById(id + "invoice_name");// 发票号
	var applyMoney = document.getElementById(id + "applyMoney");// 申请金额
	var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
	var msg = "";

	if (!doubleReg.test(managerMoney.value)) {
		alert("运营确认金额不能小于1元并且必须是整数或者小数，小数最多保留两位！");
		managerMoney.focus();
		return;
	} else if (managerMoney.value < 1) {
		alert("运营确认金额不能小于1元并且必须是整数或者小数，小数最多保留两位！");
		managerMoney.focus();
		return;
	} else if (managerMoney.value > applyMoney.value * 1) {
		alert("运营确认金额不能大于申请金额并且必须是整数或者小数，小数最多保留两位！");
		managerMoney.focus();
		return;
	}

	if (status == 1) {// 通过
		msg = "确认要审核通过吗？";
		var flag = confirm(msg);
		if (flag) {
			document.getElementById("mDesc").value = " ";
			document.getElementById("mMoney").value = managerMoney.value;
			document.getElementById("invoice_suv").value = invoice_suv.value;
			document.getElementById("invoice_statu").value = invoice_status.value;
			// document.getElementById("id").value = id;
			// document.getElementById("status").value = status;
			$("#status").attr("value", status);
			$("#id").attr("value", id);
			$("#batchForm").attr("action", "manage!singleChaAuditOper.do")
				.submit();
		}
	} else {// 不通过
		var reason = window.prompt("请填写驳回理由（20个字符以内）", "");
		if (reason && reason.length < 50) {
			document.getElementById("mDesc").value = reason;
			document.getElementById("mMoney").value = managerMoney.value;
			document.getElementById("invoice_suv").value = invoice_suv.value;
			document.getElementById("invoice_statu").value = invoice_status.value;
			$("#status").attr("value", status);
			$("#id").attr("value", id);
			$("#batchForm").attr("action", "manage!singleChaAuditOper.do")
					.submit();
		} else {
			return;
		}
	}
}
/**
 * 运营渠道批量审核处理
 */
function batchChaDealAuditInOper(status) {
	var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
	var checkedLength = $("input:checked").length;// 被选中的数量
	if (checkedLength > 0) {
		var msg = "";
		var mMoneys = new Array();// 存放金额数组
		var mDescs = new Array();// 存放说明数组
		var invoiceSuvS = new Array();// 存放发票号
		var invoice_statusS = new Array();// 存放发票状态
		for ( var i = 0; i < checkedLength; i++) {
			var id = $("input:checked")[i].value;
			mDescs[i] = " ";
			// 验证金额
			var mInvoice_status = document.getElementById(id + "invoice_status").value;// 存放发状态
			var mInvoice = document.getElementById(id + "invoice_name").value;// 存放发票号
			var mMoney = document.getElementById(id + "managerMoney").value;// 运营确认金额
			var applyMoney = document.getElementById(id + "applyMoney")// 申请金额

			if (!doubleReg.test(mMoney)) {// 金额，小数点后边最多两位小数
				alert("运营确认金额不能小于1元并且必须是整数或者小数，小数最多保留两位！");
				document.getElementById(id + "managerMoney").focus();
				return;
			} else if (mMoney < 1) {
				alert("运营确认金额不能小于1元并且必须是整数或者小数，小数最多保留两位！");
				document.getElementById(id + "managerMoney").focus();
				return;
			} else if (mMoney > applyMoney.value * 1) {
				alert("运营确认金额不能大于申请金额并且必须是整数或者小数，小数最多保留两位！");
				document.getElementById(id + "managerMoney").focus();
				return;
			}
			mMoneys[i] = mMoney;
			invoiceSuvS[i] = mInvoice;
			invoice_statusS[i] = mInvoice_status;
		}

		if (status == 1) {
			msg = "确认要审核通过吗？";
			var flag = confirm(msg);
			if (flag) {
				document.getElementById("mMoneys").value = mMoneys;
				document.getElementById("mDescs").value = mDescs;
				document.getElementById("invoice_suvS").value = invoiceSuvS;
				document.getElementById("invoice_statusS").value = invoice_statusS;
				// document.getElementById("status").value = status;
				$("#status").attr("value", status);
				$("#batchForm").attr("action", "manage!batchChaAuditOper.do")
						.submit();
			}
		} else {// 不通过
			var reason = window.prompt("请填写驳回理由（20个字符以内）", "");
			if (reason && reason.length < 20) {
				for ( var i = 0; i < checkedLength; i++) {
					mDescs[i] = reason;
				}
				document.getElementById("mMoneys").value = mMoneys;
				document.getElementById("mDescs").value = mDescs;
				// document.getElementById("status").value = status;
				$("#status").attr("value", status);
				$("#batchForm").attr("action", "manage!batchChaAuditOper.do")
						.submit();
			} else {
				return;
			}
		}

	} else {
		alert("请选择要操作的数据！");
		return;
	}
}

function checkInput(obj) {
	obj = $.trim(obj);
	var doubleReg = /^￥[-]?[0-9]+$|^￥[-]?[0-9]+[\.][0-9]{0,2}$/;
	if (doubleReg.test(obj)) {
		obj = obj.substring(1, obj.length);
	}
	return obj;
}

/**
 * 财务单个审核处理
 */
function singleDealAuditInFinance(id, status) {
	var managerMoney = document.getElementById(id + "managerMoney");
	var finance_tax = document.getElementById(id + "finance_tax");
	var finance_dues = document.getElementById(id + "finance_dues");
	var pay_type = document.getElementById(id + "pay_type");
	var balance = document.getElementById(id + "balance");

	var financeMoney = parseFloat(
			(checkInput(managerMoney.firstChild.nodeValue))
					- checkInput(finance_tax.value)
					- checkInput(finance_dues.value)).toFixed(2);
	var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
	var msg = "";
	if (!doubleReg.test(finance_tax.value)) {
		alert("税率输入不合法！");
		finance_tax.focus();
		return;
	}
	if (!doubleReg.test(finance_dues.value)) {
		alert("手续费输入不合法！");
		finance_dues.focus();
		return;
	}
	if (financeMoney < 0) {
		alert("税率和手续费之和不能大于申请支付！");
		financeMoney.focus();
		return;
	}
	if (status == 2) {// 通过
		msg = "确认要审核通过吗？";
		var flag = confirm(msg);
		if (flag) {
			document.getElementById("fDesc").value = " ";
			document.getElementById("fMoney").value = financeMoney;
			document.getElementById("id").value = id;
			document.getElementById("pay_type").value = pay_type.value;

			document.getElementById("finance_tax").value = finance_tax.value;
			document.getElementById("finance_dues").value = finance_dues.value;
			// document.getElementById("balance").value =
			// checkInput(balance.innerHTML);
			$("#exp_status").attr("value", status);
			$("#batchForm").attr("action", "manage!singleAuditFinance.do")
					.submit();
		}
	} else {// 不通过
		var reason = window.prompt("请填写驳回理由", "");
		if (reason && reason.length < 20) {
			document.getElementById("fDesc").value = reason;
			document.getElementById("fMoney").value = 0;
			document.getElementById("id").value = id;
			document.getElementById("pay_type").value = pay_type.value;
			document.getElementById("finance_tax").value = finance_tax.value;
			document.getElementById("finance_dues").value = finance_dues.value;
			// document.getElementById("balance").value =
			// checkInput(balance.innerHTML);
			$("#exp_status").attr("value", status);
			$("#batchForm").attr("action", "manage!singleAuditFinance.do")
					.submit();
		} else {
			return;
		}
	}
}

/**
 * 财务批量审核处理
 */
function batchDealAuditInFinance(status) {
	var checkedLength = $("input:checked").length;// 被选中的数量
	if (checkedLength > 0) {
		var msg = "";
		var fMoneys = new Array()// 存放金额数组
		var fDescs = new Array()// 存放说明数组

		var ids = new Array()// 存放说明数组

		var pay_types = new Array()// 存放支付类型数组
		var finance_taxs = new Array()// 存放税率数组
		var finance_duess = new Array()// 存放手续费数组

		// var balances = new Array()//存放余额数组
		var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
		for ( var i = 0; i < checkedLength; i++) {
			var id = $("input:checked")[i].value;
			var pay_type = document.getElementById(id + "pay_type").value;
			var finance_tax = document.getElementById(id + "finance_tax").value;//
			var finance_dues = document.getElementById(id + "finance_dues").value;//
			var managerMoney = document.getElementById(id + "managerMoney");
			// var balance = document.getElementById(id+"balance");
			var financeMoney = parseFloat(
					(checkInput(managerMoney.firstChild.nodeValue))
							- checkInput(finance_tax)
							- checkInput(finance_dues)).toFixed(2);
			if (!doubleReg.test(finance_tax)) {
				alert("税率输入不合法！");
				financeMoney.focus();
				return;
			}
			if (!doubleReg.test(finance_dues)) {
				alert("手续费输入不合法！");
				financeMoney.focus();
				return;
			}

			if (financeMoney < 0) {
				alert("税率和手续费之和不能大于申请支付！");
				financeMoney.focus();
				return;
			}

			ids[i] = id;
			fDescs[i] = " ";
			pay_types[i] = pay_type;
			finance_taxs[i] = finance_tax;
			finance_duess[i] = finance_dues;
			fMoneys[i] = financeMoney;
			// balances[i] = checkInput(balance.innerHTML);
		}

		if (status == 2) {
			msg = "确认要支付吗？";
			var flag = confirm(msg);
			if (flag) {
				document.getElementById("fMoneys").value = fMoneys;
				document.getElementById("fDescs").value = fDescs;

				document.getElementById("pay_types").value = pay_types;
				document.getElementById("finance_taxs").value = finance_taxs;
				document.getElementById("finance_duess").value = finance_duess;

				document.getElementById("ids").value = ids;
				$("#exp_status").attr("value", status);
				$("#batchForm").attr("action", "manage!batchAuditFinance.do")
						.submit();
			}
		} else {
			var reason = window.prompt("请填写驳回理由（20个字符以内）", "");
			if (reason && reason.length < 20) {
				for ( var i = 0; i < checkedLength; i++) {
					fDescs[i] = reason;
					fMoneys[i] = 0;
				}
				document.getElementById("fMoneys").value = fMoneys;
				document.getElementById("fDescs").value = fDescs;
				document.getElementById("pay_types").value = pay_types;
				document.getElementById("finance_taxs").value = finance_taxs;
				document.getElementById("finance_duess").value = finance_duess;

				document.getElementById("ids").value = ids;
				$("#exp_status").attr("value", status);
				$("#batchForm").attr("action", "manage!batchAuditFinance.do")
						.submit();
			} else {
				return;
			}
		}
	} else {
		alert("请选择要操作的数据！");
		return;
	}
}

/**
 * 财务渠道单个审核处理
 */
function singleChannelFinance(id, status) {
	var managerMoney = document.getElementById(id + "managerMoney");
	var finance_tax = document.getElementById(id + "finance_tax");
	var finance_dues = document.getElementById(id + "finance_dues");
	var pay_type = document.getElementById(id + "pay_type");
	var balance = document.getElementById(id + "balance");

	var financeMoney = parseFloat(
			(checkInput(managerMoney.firstChild.nodeValue))
					- checkInput(finance_tax.value)
					- checkInput(finance_dues.value)).toFixed(2);
	var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
	var msg = "";
	if (!doubleReg.test(finance_tax.value)) {
		alert("税率输入不合法！");
		finance_tax.focus();
		return;
	}
	if (!doubleReg.test(finance_dues.value)) {
		alert("手续费输入不合法！");
		finance_dues.focus();
		return;
	}
	if (financeMoney < 0) {
		alert("税率和手续费之和不能大于申请支付！");
		financeMoney.focus();
		return;
	}
	if (status == 2) {// 通过
		msg = "确认要审核通过吗？";
		var flag = confirm(msg);
		if (flag) {
			document.getElementById("fDesc").value = " ";
			document.getElementById("fMoney").value = financeMoney;
			document.getElementById("id").value = id;
			document.getElementById("pay_type").value = pay_type.value;

			document.getElementById("finance_tax").value = finance_tax.value;
			document.getElementById("finance_dues").value = finance_dues.value;
			// document.getElementById("balance").value =
			// checkInput(balance.innerHTML);
			$("#exp_status").attr("value", status);
			$("#batchForm").attr("action", "manage!singleAuditChaFinance.do")
					.submit();
		}
	} else {// 不通过
		var reason = window.prompt("请填写驳回理由", "");
		if (reason && reason.length < 20) {
			document.getElementById("fDesc").value = reason;
			document.getElementById("fMoney").value = 0;
			document.getElementById("id").value = id;
			document.getElementById("pay_type").value = pay_type.value;
			document.getElementById("finance_tax").value = finance_tax.value;
			document.getElementById("finance_dues").value = finance_dues.value;
			// document.getElementById("balance").value =
			// checkInput(balance.innerHTML);
			$("#exp_status").attr("value", status);
			$("#batchForm").attr("action", "manage!singleAuditChaFinance.do")
					.submit();
		} else {
			return;
		}
	}
}

/**
 * 财务渠道批量审核处理
 */
function batchDealAuditChannelFinance(status) {
	var checkedLength = $("input:checked").length;// 被选中的数量
	if (checkedLength > 0) {
		var msg = "";
		var fMoneys = new Array()// 存放金额数组
		var fDescs = new Array()// 存放说明数组

		var ids = new Array()// 存放说明数组

		var pay_types = new Array()// 存放支付类型数组
		var finance_taxs = new Array()// 存放税率数组
		var finance_duess = new Array()// 存放手续费数组

		// var balances = new Array()//存放余额数组
		var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
		for ( var i = 0; i < checkedLength; i++) {
			var id = $("input:checked")[i].value;
			var pay_type = document.getElementById(id + "pay_type").value;
			var finance_tax = document.getElementById(id + "finance_tax").value;//
			var finance_dues = document.getElementById(id + "finance_dues").value;//
			var managerMoney = document.getElementById(id + "managerMoney");
			// var balance = document.getElementById(id+"balance");
			var financeMoney = parseFloat(
					(checkInput(managerMoney.firstChild.nodeValue))
							- checkInput(finance_tax)
							- checkInput(finance_dues)).toFixed(2);
			if (!doubleReg.test(finance_tax)) {
				alert("税率输入不合法！");
				financeMoney.focus();
				return;
			}
			if (!doubleReg.test(finance_dues)) {
				alert("手续费输入不合法！");
				financeMoney.focus();
				return;
			}

			if (financeMoney < 0) {
				alert("税率和手续费之和不能大于申请支付！");
				financeMoney.focus();
				return;
			}

			ids[i] = id;
			fDescs[i] = " ";
			pay_types[i] = pay_type;
			finance_taxs[i] = finance_tax;
			finance_duess[i] = finance_dues;
			fMoneys[i] = financeMoney;
			// balances[i] = checkInput(balance.innerHTML);
		}

		if (status == 2) {
			msg = "确认要支付吗？";
			var flag = confirm(msg);
			if (flag) {
				document.getElementById("fMoneys").value = fMoneys;
				document.getElementById("fDescs").value = fDescs;

				document.getElementById("pay_types").value = pay_types;
				document.getElementById("finance_taxs").value = finance_taxs;
				document.getElementById("finance_duess").value = finance_duess;

				document.getElementById("ids").value = ids;
				$("#exp_status").attr("value", status);
				$("#batchForm").attr("action", "manage!batchAuditChaFinance.do")
						.submit();
			}
		} else {
			var reason = window.prompt("请填写驳回理由（20个字符以内）", "");
			if (reason && reason.length < 20) {
				for ( var i = 0; i < checkedLength; i++) {
					fDescs[i] = reason;
					fMoneys[i] = 0;
				}
				document.getElementById("fMoneys").value = fMoneys;
				document.getElementById("fDescs").value = fDescs;
				document.getElementById("pay_types").value = pay_types;
				document.getElementById("finance_taxs").value = finance_taxs;
				document.getElementById("finance_duess").value = finance_duess;

				document.getElementById("ids").value = ids;
				$("#exp_status").attr("value", status);
				$("#batchForm").attr("action", "manage!batchAuditChaFinance.do")
						.submit();
			} else {
				return;
			}
		}
	} else {
		alert("请选择要操作的数据！");
		return;
	}
}

$(function() {
	// 财务审核列表查询
	$("#financeApplymoney").click(
			function() {
				$("#listDMFinanceAudit").attr("action",
						"manage!financeApplymoneyList.do").submit();
			});
	// 财务审核列表下载
	$("#financeApplymoneyDownload")
			.click(
					function() {
						var checkedLength = $("input[name='checkboxtag']").length;
						if (checkedLength > 0) {
							var ids = new Array();// 存放说明数组
							var finance_taxs = new Array();// 存放税率数组
							var finance_duess = new Array();// 存放手续费数组

							var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
							var doubleReg2 = /^￥[-]?[0-9]+$|^￥[-]?[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
							for ( var i = 0; i < checkedLength; i++) {
								var id = $("input[name='checkboxtag']")[i].value;
								var finance_tax = document.getElementById(id
										+ "finance_tax").value;
								var finance_dues = document.getElementById(id
										+ "finance_dues").value;
								var managerMoney = document.getElementById(id
										+ "managerMoney").innerHTML;

								if (doubleReg2.test(finance_tax)) {
									finance_tax = finance_tax.substring(1,
											finance_tax.length);
								}
								if (doubleReg2.test(finance_dues)) {
									finance_dues = finance_dues.substring(1,
											finance_dues.length);
								}
								if (doubleReg2.test(managerMoney)) {
									managerMoney = managerMoney.substring(1,
											managerMoney.length);
								}

								if (!doubleReg.test(finance_tax)) {
									alert("税率输入不合法！");
									financeMoney.focus();
									return;
								}
								if (!doubleReg.test(finance_dues)) {
									alert("手续费输入不合法！");
									financeMoney.focus();
									return;
								}
								var finance_moneyTag = parseFloat(
										parseFloat(managerMoney)
												- parseFloat(finance_tax)
												- parseFloat(finance_dues))
										.toFixed(2);
								if (finance_moneyTag < 0) {
									alert("税率和手续费之和不能大于申请支付！");
									financeMoney.focus();
									return;
								}

								ids[i] = id;
								finance_taxs[i] = finance_tax;
								finance_duess[i] = finance_dues;
							}
							document.getElementById("finance_taxs").value = finance_taxs;
							document.getElementById("finance_duess").value = finance_duess;

							document.getElementById("ids").value = ids;

							document.getElementById("exp_payType").value = document
									.getElementById("payType").value;
							document.getElementById("exp_status").value = document
							.getElementById("statusm").value;
							 document.getElementById("exp_dev_email").value =
								 document.getElementById("dev_email").value;
							
							 document.getElementById("exp_begin").value =document.getElementById("begin").value;
							 document.getElementById("exp_end").value =document.getElementById("end").value;
							 
							 document.getElementById("exp_ope_begin").value =document.getElementById("ope_begin").value;
							 document.getElementById("exp_ope_end").value =document.getElementById("ope_end").value;
							 
							document.getElementById("exp_operatorBegin").value = document
									.getElementById("operatorBegin").value;
							document.getElementById("exp_operatorEnd").value = document
									.getElementById("operatorEnd").value;
							
							 document.getElementById("exp_operatorMan").value = document.getElementById("operatorMan").value;
							$("#batchForm").attr("action",
									"manage!financeApplyDownloadList.do")
									.submit();

						} else {
							return;
						}

					});

});


$(function() {
	// 财务渠道审核列表查询
	$("#financeChaApplymoney").click(
			function() {
				$("#listDMFinanceAudit").attr("action",
						"manage!financeChannekApplyList.do").submit();
			});
	// 财务渠道列表下载
	$("#financeApplyChaDownload")
			.click(
					function() {
						var checkedLength = $("input[name='checkboxtag']").length;
						if (checkedLength > 0) {
							var ids = new Array();// 存放说明数组
							var finance_taxs = new Array();// 存放税率数组
							var finance_duess = new Array();// 存放手续费数组

							var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
							var doubleReg2 = /^￥[-]?[0-9]+$|^￥[-]?[0-9]+[\.][0-9]{0,2}$/;// 金额，小数点后边最多两位小数
							for ( var i = 0; i < checkedLength; i++) {
								var id = $("input[name='checkboxtag']")[i].value;
								var finance_tax = document.getElementById(id
										+ "finance_tax").value;
								var finance_dues = document.getElementById(id
										+ "finance_dues").value;
								var managerMoney = document.getElementById(id
										+ "managerMoney").innerHTML;

								if (doubleReg2.test(finance_tax)) {
									finance_tax = finance_tax.substring(1,
											finance_tax.length);
								}
								if (doubleReg2.test(finance_dues)) {
									finance_dues = finance_dues.substring(1,
											finance_dues.length);
								}
								if (doubleReg2.test(managerMoney)) {
									managerMoney = managerMoney.substring(1,
											managerMoney.length);
								}

								if (!doubleReg.test(finance_tax)) {
									alert("税率输入不合法！");
									financeMoney.focus();
									return;
								}
								if (!doubleReg.test(finance_dues)) {
									alert("手续费输入不合法！");
									financeMoney.focus();
									return;
								}
								var finance_moneyTag = parseFloat(
										parseFloat(managerMoney)
												- parseFloat(finance_tax)
												- parseFloat(finance_dues))
										.toFixed(2);
								if (finance_moneyTag < 0) {
									alert("税率和手续费之和不能大于申请支付！");
									financeMoney.focus();
									return;
								}

								ids[i] = id;
								finance_taxs[i] = finance_tax;
								finance_duess[i] = finance_dues;
							}
							document.getElementById("finance_taxs").value = finance_taxs;
							document.getElementById("finance_duess").value = finance_duess;

							document.getElementById("ids").value = ids;

							document.getElementById("exp_payType").value = document
									.getElementById("payType").value;
							document.getElementById("exp_status").value = document
							.getElementById("statusm").value;
							
							
							 document.getElementById("exp_begin").value =document.getElementById("begin").value;
							 document.getElementById("exp_end").value =document.getElementById("end").value;
							 
							 document.getElementById("exp_ope_begin").value =document.getElementById("ope_begin").value;
							 document.getElementById("exp_ope_end").value =document.getElementById("ope_end").value;
							 
							document.getElementById("exp_operatorBegin").value = document
									.getElementById("operatorBegin").value;
							document.getElementById("exp_operatorEnd").value = document
									.getElementById("operatorEnd").value;
							 document.getElementById("exp_operatorMan").value = document.getElementById("operatorMan").value;
							$("#listDMFinanceAudit").attr("action",
									"manage!financeApplyChaDownloadList.do")
									.submit();

						} else {
							return;
						}

					});

});