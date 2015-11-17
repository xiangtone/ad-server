$(document).ready(
		
	function(){
	    
		//初始化时间控件
	    $("input[name$='Time']").daterangepicker({
				arrows : true,
				datepickerOptions : {
					changeMonth : true,
					changeYear : true,
					changeWeek : true,
		 			changeDay : true,
					minDate : Date.parse('2011-01-01'),
					maxDate : Date.parse('today')
				},
				presets:{
					dateRange:'自定义时间段'
				}
		});
	}
);
