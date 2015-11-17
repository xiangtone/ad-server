/**
	 * tb效果
	 *	
	 **/
	$(document).ready(function (){
		$("#tb tr").hover(function (){
			 $(this).addClass('bgclor01');
		},function (){
			  $(this).removeClass('bgclor01');  
		});
		var trs =  $("#tb").find("tr");
		for(i = 0;i<trs.length;i++){
			var obj = trs[i];
			if(i %2 == 0){
				obj.style.backgroundColor ='#E8EEF7';
			}
				 
		}
	});
	
	
	
	//重置所有
	function resetAll(form_id){
		$("#"+form_id+" :input[type!='button'][type!='submit']").each(function(){
			$(this).attr("value","");
		});
	}
	//查询
	function search(form_id){
		$("#"+form_id).submit();
	}
	
	$(document).keydown(function(event){
		if(event.keyCode==13){
			refresh();
		}
	}); 